// Максимум числового ряда.
package ru.netology.kirilov;

import java.util.List;

/** Находит максимум среди переданных значений. */
public class MaxCalculation extends Calculation<Double> {
    /** Задаёт название расчёта максимального индекса. */
    public MaxCalculation() {
        super("Максимальный индекс");
    }

    /** Возвращает максимум; для null, пустого списка и списка из null возвращает 0.0. */
    @Override
    public double calculate(List<Double> data) {
        Double maximum = null;
        if (data == null) {
            return 0.0;
        }
        for (Double value : data) {
            if (value != null && (maximum == null || value > maximum)) {
                maximum = value;
            }
        }
        return maximum == null ? 0.0 : maximum;
    }
}
