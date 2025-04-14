package com.adventofcode.year2015.day08;

import com.adventofcode.utility.FileUtils;
import com.adventofcode.utility.ResultPrinter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    private final String filePath;

    private final Pattern ESCAPE_REGEX = Pattern.compile("(\\\\([\"\\\\]|x[0-9a-fA-F]{2}))");

    public Solution(String filePath) {
        this.filePath = filePath;
    }

    public int part1() {
        int result = 0;

        try {
            List<String> lines = FileUtils.readInputAsStringList(filePath);
            for (String line : lines) {
                int nCode = line.length();
                int nMem = eval(line.substring(1, line.length() - 1));
                result += nCode - nMem;
            }
        } catch (Exception ignored) {}

        return result;
    }

    private int eval(final String content) {
        if (content.isEmpty())
            return 0;

        Matcher m = ESCAPE_REGEX.matcher(content);
        if (m.find() && m.start() == 0)
            return 1 + eval(content.substring(m.group().length()));
        else
            return 1 + eval(content.substring(1));
    }

    public int part2() {
        int result = 0;

        try {
            List<String> lines = FileUtils.readInputAsStringList(filePath);
            for (String line : lines) {
                int nMem = codeRep(line);
                int nCode = line.length();
                result += nMem - nCode;
            }
        } catch (Exception ignored) {}

        return result;
    }

    private int codeRep(final String line) {
        int extra = 2;
        for (char c : line.toCharArray())
            if (c == '"' || c == '\\')
                extra += 2;
            else
                extra += 1;

        return extra;
    }

    public static void main(String[] args) {
        Solution s = new Solution("inputs/year2015/day08/input");
        ResultPrinter.part1Formatter(s.part1());
        ResultPrinter.part2Formatter(s.part2());
    }
}