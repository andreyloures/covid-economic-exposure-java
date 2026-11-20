// Хранилище записей с ключом по названию страны.
package ru.netology.kirilov;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Выполняет CRUD-операции с записями в памяти. */
public class ExposureRepository {
    private final Map<String, ExposureRecord> records = new LinkedHashMap<>();

    /** Добавляет запись; повторное название страны вызывает исключение. */
    public void create(ExposureRecord record) {
        validateRecord(record);
        String country = record.getCountry();
        if (records.containsKey(country)) {
            throw new IllegalArgumentException("Страна уже существует: " + country);
        }
        records.put(country, record);
    }

    /** Возвращает запись по стране или null, если такой записи нет. */
    public ExposureRecord findById(String country) {
        return records.get(country);
    }

    /** Заменяет существующую запись с тем же ключом; иначе выбрасывает исключение. */
    public void update(String country, ExposureRecord record) {
        validateRecord(record);
        if (!records.containsKey(country)) {
            throw new IllegalArgumentException("Страна не найдена: " + country);
        }
        if (!country.equals(record.getCountry())) {
            throw new IllegalArgumentException("При изменении нельзя менять ключ страны.");
        }
        records.put(country, record);
    }

    /** Удаляет запись; возвращает true, если запись существовала. */
    public boolean delete(String country) {
        return records.remove(country) != null;
    }

    /** Возвращает новый список всех записей в порядке загрузки. */
    public List<ExposureRecord> findAll() {
        return new ArrayList<>(records.values());
    }

    private void validateRecord(ExposureRecord record) {
        if (record == null || record.getCountry() == null || record.getCountry().isBlank()) {
            throw new IllegalArgumentException("У записи должно быть название страны.");
        }
        if (record.getIncomeClassification() == null
                || record.getIncomeClassification().isBlank()) {
            throw new IllegalArgumentException("У записи должна быть группа дохода.");
        }
    }
}
