// Общий контракт числового расчёта.
package ru.netology.kirilov;

import java.util.List;
import java.util.Locale;

/** Хранит название расчёта и предоставляет единый способ вывода результата. */
public abstract class Calculation<T extends Number> {
    private final String name;

    /** Сохраняет название расчёта для подписи в отчёте. */
    protected Calculation(String name) {
        this.name = name;
    }

    /** Возвращает название расчёта. */
    public String getName() {
        return name;
    }

    /** Возвращает результат расчёта; пустой набор даёт 0.0, null пропускается. */
    public abstract double calculate(List<T> data);

    /** Выводит название и результат расчёта в консоль с двумя знаками после точки. */
    public void print(List<T> data) {
        System.out.printf(Locale.ROOT, "%-30s | %10.2f%n", name, calculate(data));
    }
}
