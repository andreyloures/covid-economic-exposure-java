// Запуск чтения, анализа и сохранения отчёта.
package ru.netology.kirilov;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Собирает приложение и выполняет последовательность обработки CSV. */
public class Main {
    private static final String RESOURCE_NAME = "/Exposure.csv";
    private static final String REPORT_FILE = "report.txt";
    private static final String DEMO_COUNTRY = "Учебная страна (CRUD)";

    /** Выполняет анализ; единственный необязательный аргумент задаёт путь отчёта. */
    public static void main(String[] args) {
        if (args.length > 1) {
            System.err.println("Использование: java -jar covid-economic-exposure.jar"
                    + " [отчёт.txt]");
            System.exit(1);
        }
        try {
            ExposureReader reader = new ExposureReader(RESOURCE_NAME);
            ExposureParser parser = new ExposureParser();
            ExposureRepository repository = new ExposureRepository();
            ExposureService service = new ExposureService(repository);
            ReportWriter writer = new ReportWriter();
            List<ExposureRecord> records = parseRecords(reader.readFile(), parser);
            for (ExposureRecord record : records) {
                try {
                    repository.create(record);
                } catch (IllegalArgumentException exception) {
                    System.err.println("Запись пропущена: " + exception.getMessage());
                }
            }
            String crudResult = demonstrateCrud(repository);
            String report = writer.formatReport(
                    service, repository.findAll().size(), crudResult);
            Path reportPath = Path.of(args.length == 0 ? REPORT_FILE : args[0]);
            writer.writeReport(reportPath, report);
            System.out.print(report);
            System.out.println("\nРасчёты через общий тип Calculation<Double>:");
            List<Double> indexes = service.getExposureIndexes();
            for (Calculation<Double> calculation : service.getCalculations()) {
                calculation.print(indexes);
            }
            System.out.println("\nОтчёт сохранён: " + reportPath.toAbsolutePath());
        } catch (IOException | IllegalArgumentException exception) {
            System.err.println("Не удалось выполнить анализ: " + exception.getMessage());
            System.exit(1);
        }
    }

    // Заголовок проверяется отдельно; остальные строки разбираются независимо друг от друга.
    static List<ExposureRecord> parseRecords(List<String> lines, ExposureParser parser)
            throws IOException {
        if (lines.isEmpty() || !lines.get(0).startsWith("country;GHRP;")) {
            throw new IOException("Нет ожидаемого заголовка Exposure.csv.");
        }
        List<ExposureRecord> records = new ArrayList<>();
        for (int lineIndex = 1; lineIndex < lines.size(); lineIndex++) {
            try {
                records.add(parser.parseLine(lines.get(lineIndex)));
            } catch (IllegalArgumentException exception) {
                System.err.println("Строка " + (lineIndex + 1) + " пропущена: "
                        + exception.getMessage());
            }
        }
        return records;
    }

    private static String demonstrateCrud(ExposureRepository repository) {
        StringBuilder result = new StringBuilder();
        ExposureRecord example = new ExposureRecord(DEMO_COUNTRY, false, "MIC");
        repository.create(example);
        result.append("create: запись создана.\n");
        ExposureRecord found = repository.findById(DEMO_COUNTRY);
        result.append("findById: ").append(found.getCountry()).append(", группа ")
                .append(found.getIncomeClassification()).append('\n');
        ExposureRecord changed = new ExposureRecord(DEMO_COUNTRY, true, "LIC");
        repository.update(DEMO_COUNTRY, changed);
        result.append("update: группа изменена на ")
                .append(repository.findById(DEMO_COUNTRY).getIncomeClassification()).append('\n');
        boolean deleted = repository.delete(DEMO_COUNTRY);
        result.append("delete: ").append(deleted ? "запись удалена." : "запись не найдена.")
                .append('\n');
        result.append("Число записей после удаления (findAll): ")
                .append(repository.findAll().size()).append('\n');
        return result.toString();
    }
}
