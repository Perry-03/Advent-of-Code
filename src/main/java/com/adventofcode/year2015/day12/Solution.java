package com.adventofcode.year2015.day12;

import com.adventofcode.utility.FileUtils;
import com.adventofcode.utility.ResultPrinter;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jshell.execution.Util;


public class Solution {
    private final String filePath;

    public Solution(String filePath) {
        this.filePath = filePath;
    }

    private static final Pattern NUMBERS = Pattern.compile("(-?\\d+)");

    public int part1() {
        try {
            String input = FileUtils.readInputAsString(filePath);

            return NUMBERS.matcher(input)
                    .results()
                    .mapToInt(m -> Integer.parseInt(m.group()))
                    .sum();
        } catch (Exception ignored) {}

        return 0;
    }

    public int part2() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(FileUtils.readInputAsString(filePath));
            return sum(node);
        } catch (Exception ignored) {}
        return 0;
    }

    private int sum(JsonNode node) {
        if (node.isInt()) {
            return node.asInt();
        }
        if (node.isArray()) {
            int total = 0;
            for (JsonNode child : node) {
                total += sum(child);
            }
            return total;
        }
        if (node.isObject()) {
            // se uno dei valori è "red", ignora l'intero oggetto
            for (JsonNode value : node) {
                if (value.isTextual() && value.asText().equals("red")) {
                    return 0;
                }
            }
            int total = 0;
            for (JsonNode child : node) {
                total += sum(child);
            }
            return total;
        }
        // altrimenti (stringhe, null, ecc.) → niente da sommare
        return 0;
    }

    public static void main(String[] args) {
        Solution s = new Solution("inputs/year2015/day12/input");
        ResultPrinter.part1Formatter(s.part1());
        ResultPrinter.part2Formatter(s.part2());
    }
}