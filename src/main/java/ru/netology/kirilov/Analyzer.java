// Доступные операции анализа показателей стран.
package ru.netology.kirilov;

import java.util.List;
import java.util.Map;
import java.util.Set;

/** Объявляет доступные операции анализа показателей стран. */
public interface Analyzer {
    /** Возвращает максимум общего индекса, пропуская отсутствующие значения. */
    double calculateMaximum();

    /** Возвращает среднее общего индекса по заполненным значениям. */
    double calculateAverage();

    /** Возвращает список заполненных значений общего индекса. */
    List<Double> getExposureIndexes();

    /** Возвращает страну с наибольшим общим индексом в группе или null. */
    ExposureRecord findMostExposedCountry(String incomeClassification);

    /** Возвращает страны по диапазону индекса без помощи, ПИИ и продовольственного импорта. */
    List<ExposureRecord> findCountriesInRange(double minimum, double maximum);

    /** Возвращает ту же выборку с дополнительным условием по группе дохода. */
    List<ExposureRecord> findCountriesInRange(
            double minimum, double maximum, String incomeClassification);

    /** Возвращает уникальные группы дохода по алфавиту. */
    Set<String> getIncomeClassifications();

    /** Возвращает количество стран в каждой группе дохода. */
    Map<String, Integer> countByIncomeClassification();
}
