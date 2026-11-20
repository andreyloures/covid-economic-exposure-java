// Разбор одной строки учебного CSV с разделителем «;».
package ru.netology.kirilov;

/** Преобразует строку с 22 колонками в запись о стране. */
public class ExposureParser {
    private static final int COLUMN_COUNT = 22;

    /** Возвращает запись; пустые, служебные и повреждённые строки отклоняет исключением. */
    public ExposureRecord parseLine(String line) {
        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("Пустая строка.");
        }
        String[] columns = line.split(";", -1);
        if (columns.length != COLUMN_COUNT) {
            throw new IllegalArgumentException("Ожидалось 22 поля, получено " + columns.length);
        }
        String country = columns[0].strip();
        String ghrp = columns[1].strip();
        String income = columns[2].strip();
        if (country.isEmpty()) {
            throw new IllegalArgumentException("Нет названия страны: служебная строка.");
        }
        if (!ghrp.equalsIgnoreCase("Yes") && !ghrp.equalsIgnoreCase("No")) {
            throw new IllegalArgumentException("Признак GHRP должен быть Yes или No.");
        }
        if (income.isEmpty()) {
            throw new IllegalArgumentException("Не указана группа дохода.");
        }
        ExposureRecord record = new ExposureRecord(country, ghrp.equalsIgnoreCase("Yes"), income);
        record.setAidReceived(parseNumber(columns[3]));
        record.setAidDependence(parseNumber(columns[4]));
        record.setRemittancesShare(parseNumber(columns[5]));
        record.setRemittanceDependence(parseNumber(columns[6]));
        record.setFoodImports(parseNumber(columns[7]));
        record.setFoodImportDependence(parseNumber(columns[8]));
        record.setCommodityExports(parseNumber(columns[9]));
        record.setCommodityExportDependence(parseNumber(columns[10]));
        record.setTourismShare(parseNumber(columns[11]));
        record.setTourismDependence(parseNumber(columns[12]));
        record.setGrossDebt(parseNumber(columns[13]));
        record.setDebtDependence(parseNumber(columns[14]));
        record.setReservesMonths(parseNumber(columns[15]));
        record.setReserveDependence(parseNumber(columns[16]));
        record.setForeignInvestment(parseNumber(columns[17]));
        record.setInvestmentDependence(parseNumber(columns[18]));
        record.setExposureIndex(parseNumber(columns[19]));
        record.setIndexExAidAndFdi(parseNumber(columns[20]));
        record.setIndexExAidFdiAndFoodImports(parseNumber(columns[21]));
        return record;
    }

    private Double parseNumber(String text) {
        String value = text.strip();
        if (value.isEmpty() || value.equalsIgnoreCase("x") || value.equalsIgnoreCase("No data")) {
            return null;
        }
        double number = Double.parseDouble(value.replace(',', '.'));
        if (!Double.isFinite(number)) {
            throw new IllegalArgumentException("Неконечное число: " + text);
        }
        return number;
    }
}
