// Чтение исходного файла из ресурсов приложения.
package ru.netology.kirilov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/** Читает строки ресурса, сохраняя их исходный порядок. */
public class ExposureReader {
    private static final Charset CSV_CHARSET = Charset.forName("windows-1252");
    private final String resourceName;

    /** Запоминает путь ресурса внутри classpath, например /Exposure.csv. */
    public ExposureReader(String resourceName) {
        if (resourceName == null || resourceName.isBlank()) {
            throw new IllegalArgumentException("Не задан путь к CSV-файлу.");
        }
        this.resourceName = resourceName;
    }

    /** Возвращает все строки файла; при отсутствии ресурса выбрасывает IOException. */
    public List<String> readFile() throws IOException {
        List<String> lines = new ArrayList<>();
        InputStream stream = ExposureReader.class.getResourceAsStream(resourceName);
        if (stream == null) {
            throw new IOException("Ресурс не найден: " + resourceName);
        }
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream, CSV_CHARSET))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
