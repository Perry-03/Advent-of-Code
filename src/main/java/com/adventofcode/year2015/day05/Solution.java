package com.adventofcode.year2015.day05;

import com.adventofcode.utility.FileUtils;
import com.adventofcode.utility.ResultPrinter;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Solution {
    private final String filePath;

    private final Predicate<String> RULE_ONE = (str) -> {
        Pattern pattern = Pattern.compile(".*([aeiou].*){3}");
        return pattern.matcher(str).find();
    };

    private final Predicate<String> RULE_TWO = (str) -> {
        Pattern pattern = Pattern.compile("([a-zA-Z])\\1");
        return pattern.matcher(str).find();
    };

    private final Predicate<String> RULE_THREE = (str) -> {
        Pattern pattern = Pattern.compile("(ab|cd|pq|xy)");
        return !pattern.matcher(str).find();
    };

    private final Predicate<String> RULE_FOUR = (str) -> {
        Pattern pattern = Pattern.compile("([a-zA-Z]{2}).*\\1");
        return pattern.matcher(str).find();
    };

    private final Predicate<String> RULE_FIVE = (str) -> {
        Pattern pattern = Pattern.compile("([a-zA-Z]).\\1");
        return pattern.matcher(str).find();
    };

    public Solution(String filePath) { this.filePath = filePath; }

    public int part1() {
        int niceStrings = 0;
        try {
            List<String> lines = FileUtils.readInputAsStringList(filePath);

            for (String line : lines)
                if (RULE_ONE.test(line) && RULE_TWO.test(line) && RULE_THREE.test(line))
                    niceStrings++;
        } catch (Exception ignored) {}

        return niceStrings;
    }

    public int part2() {
        int niceStrings = 0;
        try {
            List<String> lines = FileUtils.readInputAsStringList(filePath);

            for (String line : lines)
                if (RULE_FOUR.test(line) && RULE_FIVE.test(line))
                    niceStrings++;
        } catch (Exception ignored) {}

        return niceStrings;
    }

    public static void main(String[] args) {
        Solution s = new Solution("inputs/year2015/day05/input");
        ResultPrinter.part1Formatter(s.part1());
        ResultPrinter.part2Formatter(s.part2());
    }
}
