package com.adventofcode.utility;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.nio.file.Files;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileUtils {
    public static List<String> readInputAsStringList(String filePath) throws IOException {
        Objects.requireNonNull(filePath, "Path cannot be null");
        return Files.readAllLines(Paths.get(filePath));
    }

    public static List<Integer> readInputAsIntList(String filePath) throws IOException {
        Objects.requireNonNull(filePath, "Path cannot be null");
        return Files.lines(Paths.get(filePath))
                .map(Integer :: parseInt)
                .collect(Collectors.toList());
    }

    public static int[] readInputAsArrayOfInt(String filePath) throws IOException {
        Objects.requireNonNull(filePath, "Path cannot be null");
        return Files.lines(Paths.get(filePath))
                .mapToInt(Integer :: parseInt)
                .toArray();
    }

    public static String readInputAsString(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }
}
