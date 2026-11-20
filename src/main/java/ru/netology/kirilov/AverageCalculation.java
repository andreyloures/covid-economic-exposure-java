// Среднее арифметическое числового ряда.
package ru.netology.kirilov;

import java.util.List;

/** Считает среднее только по заполненным значениям. */
public class AverageCalculation extends Calculation<Double> {
    /** Задаёт название расчёта среднего индекса. */
    public AverageCalculation() {
        super("Средний индекс");
    }

    /** Возвращает среднее без null; при отсутствии чисел возвращает 0.0. */
    @Override
    public double calculate(List<Double> data) {
        double sum = 0.0;
        int count = 0;
        if (data == null) {
            return 0.0;
        }
        for (Double value : data) {
            if (value != null) {
                sum += value;
                count++;
            }
        }
        return count == 0 ? 0.0 : sum / count;
    }
}
