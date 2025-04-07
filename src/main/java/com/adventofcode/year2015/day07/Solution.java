package com.adventofcode.year2015.day07;

import com.adventofcode.utility.FileUtils;
import com.adventofcode.utility.ResultPrinter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class Solution {
    private final String filePath;

    private final Map<String, String> expressions = new HashMap<>();
    private final Map<String, Integer> values = new HashMap<>();
    private final Map<String, BiFunction<Integer, Integer, Integer>> operations = new HashMap<>();

    public Solution(String filePath) {
        this.filePath = filePath;
    }

    private void setUpMapping() {
        operations.put("AND",    (a, b) -> a & b);
        operations.put("OR",     (a, b) -> a | b);
        operations.put("RSHIFT", (a, b) -> a >> b);
        operations.put("LSHIFT", (a, b) -> a << b);
    }

    public int part1() {
        setUpMapping();
        try {
            List<String> lines = FileUtils.readInputAsStringList(filePath);
            for (String command : lines) {
                String[] splitted = command.split(" -> ");
                String part1 = splitted[0];
                String part2 = splitted[1];
                expressions.put(part2, part1);
            }

        } catch (Exception ignored) {}

        return eval("a");
    }

    public int part2() {
        int a = part1();
        values.clear();
        values.put("b", a);

        return eval("a");
    }

    private int eval(final String expr) {
        if (expr.matches("\\d+")) return Integer.parseInt(expr);
        if (values.containsKey(expr)) return values.get(expr);
        String e = expressions.get(expr);
        int val = 0;
        String[] tokens = e.split(" ");

        switch (tokens.length) {
            case 1 -> val = eval(tokens[0]);
            case 2 -> val = ~ eval(tokens[1]);
            case 3 -> {
                String expr1 = tokens[0];
                String op = tokens[1];
                String expr2 = tokens[2];
                val = operations.get(op).apply(eval(expr1), eval(expr2));
            }
        }

        values.put(expr, val);
        return val;
    }

    public static void main(String[] args) {
        Solution s = new Solution("inputs/year2015/day07/input");
        ResultPrinter.part1Formatter(s.part1());
        ResultPrinter.part2Formatter(s.part2());
    }
}