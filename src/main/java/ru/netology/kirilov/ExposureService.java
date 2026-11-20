// Выборки и вызов расчётов над данными репозитория.
package ru.netology.kirilov;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/** Подготавливает данные и передаёт вычисления отдельным классам. */
public class ExposureService implements Analyzer {
    private final ExposureRepository repository;
    private final MaxCalculation maximumCalculation = new MaxCalculation();
    private final AverageCalculation averageCalculation = new AverageCalculation();
    private final List<Calculation<Double>> calculations = new ArrayList<>();

    /** Получает репозиторий и собирает список доступных расчётов. */
    public ExposureService(ExposureRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Не задан репозиторий.");
        }
        this.repository = repository;
        calculations.add(maximumCalculation);
        calculations.add(averageCalculation);
    }

    /** Возвращает максимальный общий индекс через MaxCalculation. */
    @Override
    public double calculateMaximum() {
        return maximumCalculation.calculate(getExposureIndexes());
    }

    /** Возвращает средний общий индекс через AverageCalculation. */
    @Override
    public double calculateAverage() {
        return averageCalculation.calculate(getExposureIndexes());
    }

    /** Возвращает заполненные общие индексы для расчётов. */
    @Override
    public List<Double> getExposureIndexes() {
        return collectIndexes(repository.findAll());
    }

    /** Возвращает копию списка расчётов для полиморфного вызова. */
    public List<Calculation<Double>> getCalculations() {
        return new ArrayList<>(calculations);
    }

    /** Возвращает первую страну с максимальным индексом в группе; без данных вернёт null. */
    @Override
    public ExposureRecord findMostExposedCountry(String incomeClassification) {
        List<ExposureRecord> selected = new ArrayList<>();
        for (ExposureRecord record : repository.findAll()) {
            if (record.getIncomeClassification().equalsIgnoreCase(incomeClassification)
                    && record.getExposureIndex() != null) {
                selected.add(record);
            }
        }
        if (selected.isEmpty()) {
            return null;
        }
        double maximum = maximumCalculation.calculate(collectIndexes(selected));
        for (ExposureRecord record : selected) {
            if (Double.compare(record.getExposureIndex(), maximum) == 0) {
                return record;
            }
        }
        return null;
    }

    /** Отбирает страны по индексу без помощи, ПИИ и продовольственного импорта. */
    @Override
    public List<ExposureRecord> findCountriesInRange(double minimum, double maximum) {
        return findCountriesInRange(minimum, maximum, null);
    }

    /** Возвращает выборку по диапазону и группе; null означает все группы. */
    @Override
    public List<ExposureRecord> findCountriesInRange(
            double minimum, double maximum, String incomeClassification) {
        if (!Double.isFinite(minimum) || !Double.isFinite(maximum) || minimum > maximum) {
            throw new IllegalArgumentException("Задан неверный диапазон индекса.");
        }
        List<ExposureRecord> selected = new ArrayList<>();
        for (ExposureRecord record : repository.findAll()) {
            Double index = record.getIndexExAidFdiAndFoodImports();
            boolean matchesIncome = incomeClassification == null
                    || record.getIncomeClassification().equalsIgnoreCase(incomeClassification);
            if (matchesIncome && index != null && index >= minimum && index <= maximum) {
                selected.add(record);
            }
        }
        return selected;
    }

    /** Возвращает неповторяющиеся группы дохода по алфавиту. */
    @Override
    public Set<String> getIncomeClassifications() {
        Set<String> groups = new TreeSet<>();
        for (ExposureRecord record : repository.findAll()) {
            groups.add(record.getIncomeClassification());
        }
        return groups;
    }

    /** Возвращает число записей по группам; страны без индекса тоже учитываются. */
    @Override
    public Map<String, Integer> countByIncomeClassification() {
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (ExposureRecord record : repository.findAll()) {
            String income = record.getIncomeClassification();
            counts.put(income, counts.getOrDefault(income, 0) + 1);
        }
        return counts;
    }

    private List<Double> collectIndexes(List<ExposureRecord> records) {
        List<Double> indexes = new ArrayList<>();
        for (ExposureRecord record : records) {
            if (record.getExposureIndex() != null) {
                indexes.add(record.getExposureIndex());
            }
        }
        return indexes;
    }
}
