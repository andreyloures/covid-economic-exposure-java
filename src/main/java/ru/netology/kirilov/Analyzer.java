// Доступные операции анализа показателей стран.
package ru.netology.kirilov;

import java.util.List;
import java.util.Map;
import java.util.Set;

/** Описывает операции анализа без реализации расчётных формул. */
public interface Analyzer {
    /** Возвращает максимум общего индекса, пропуская отсутствующие значения. */
    double calculateMaximum();

    /** Возвращает среднее общего индекса по заполненным значениям. */
    double calculateAverage();

    /** Возвращает список заполненных значений общего индекса. */
    List<Double> getExposureIndexes();

    /** Возвращает страну с наибольшим общим индексом в группе или null. */
    ExposureRecord findMostExposedCountry(String incomeClassification);

    /** Возвращает страны с последним индексом в замкнутом диапазоне. */
    List<ExposureRecord> findCountriesInRange(double minimum, double maximum);

    /** Возвращает страны выбранной группы с последним индексом в диапазоне. */
    List<ExposureRecord> findCountriesInRange(
            double minimum, double maximum, String incomeClassification);

    /** Возвращает уникальные группы дохода по алфавиту. */
    Set<String> getIncomeClassifications();

    /** Возвращает количество стран в каждой группе дохода. */
    Map<String, Integer> countByIncomeClassification();
}
