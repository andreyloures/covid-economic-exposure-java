// Формирование текстового отчёта и запись в UTF-8.
package ru.netology.kirilov;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/** Оформляет результаты анализа без повторного вычисления формул. */
public class ReportWriter {
    private static final String SEPARATOR = "--------------------------------------------------\n";

    /** Возвращает отчёт с показателями стран и результатами операций с записями. */
    public String formatReport(Analyzer analyzer, int recordCount, String crudResult) {
        StringBuilder report = new StringBuilder();
        report.append("Экономическое воздействие COVID\n");
        report.append("Источник: учебный файл Exposure.csv, вариант 10.\n");
        report.append("Отсутствующие числовые значения в расчёты не включаются.\n\n");
        report.append(SEPARATOR);
        report.append(String.format("%-35s | %10s%n", "Показатель", "Значение"));
        report.append(SEPARATOR);
        report.append(String.format("%-35s | %10d%n", "Число стран", recordCount));
        report.append(String.format("%-35s | %10d%n", "Заполненные общие индексы",
                analyzer.getExposureIndexes().size()));
        report.append(String.format(Locale.ROOT, "%-35s | %10.2f%n", "Максимальный индекс",
                analyzer.calculateMaximum()));
        report.append(String.format(Locale.ROOT, "%-35s | %10.2f%n", "Средний индекс",
                analyzer.calculateAverage()));
        report.append(SEPARATOR);
        report.append("\nГруппы дохода: ").append(analyzer.getIncomeClassifications()).append('\n');
        report.append(String.format("%-35s | %10s%n", "Группа дохода", "Число стран"));
        Map<String, Integer> groups = analyzer.countByIncomeClassification();
        for (Map.Entry<String, Integer> group : groups.entrySet()) {
            report.append(String.format("%-35s | %10d%n", group.getKey(), group.getValue()));
        }
        ExposureRecord highest = analyzer.findMostExposedCountry("LIC");
        report.append("\nМаксимальная уязвимость среди LIC: ");
        if (highest == null) {
            report.append("нет данных\n");
        } else {
            report.append(String.format(Locale.ROOT, "%s (%.2f)%n", highest.getCountry(),
                    highest.getExposureIndex()));
        }
        List<ExposureRecord> selected = analyzer.findCountriesInRange(5.0, 6.0);
        report.append("\nИндекс без учёта помощи, ПИИ и продовольственного импорта\n");
        report.append("Диапазон от 5.00 до 6.00 включительно\n");
        report.append(String.format("%-35s | %10s%n", "Страна", "Индекс"));
        for (ExposureRecord record : selected) {
            report.append(String.format(Locale.ROOT, "%-35s | %10.2f%n", record.getCountry(),
                    record.getIndexExAidFdiAndFoodImports()));
        }
        report.append("Найдено стран: ").append(selected.size()).append('\n');
        report.append("Из них LIC: ")
                .append(analyzer.findCountriesInRange(5.0, 6.0, "LIC").size()).append('\n');
        report.append("\nПроверка операций с учебной записью (CRUD)\n").append(crudResult);
        report.append("Итоговые показатели рассчитаны после удаления учебной записи.\n");
        return report.toString();
    }

    /** Записывает отчёт в файл UTF-8; родительская папка должна существовать. */
    public void writeReport(Path path, String report) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(report);
        }
    }
}
