package com.adventofcode.year2015.day02;

import com.adventofcode.utility.FileUtils;
import com.adventofcode.utility.ResultPrinter;

import java.util.List;

public class Solution {
    private final String filePath;

    public Solution(final String filePath) { this.filePath = filePath; }

    public int part1() {
        int feet = 0;
        try {
            List<String> lines = FileUtils.readInputAsStringList(filePath);
            for (String line : lines) {
                String[] splittedLine = line.split("x");
                int l = Integer.parseInt(splittedLine[0]);
                int w = Integer.parseInt(splittedLine[1]);
                int h = Integer.parseInt(splittedLine[2]);

                feet += (2 * l * w) +
                        (2 * w * h) +
                        (2 * h * l) +
                        Math.min(Math.min(l * w, w * h), h * l);
            }
        } catch (Exception ignored) {}
        return feet;
    }

    public int part2() {
        int ribbon = 0;
        try {
            List<String> lines = FileUtils.readInputAsStringList(filePath);
            for (String line : lines) {
                String[] splittedLine = line.split("x");
                int l = Integer.parseInt(splittedLine[0]);
                int w = Integer.parseInt(splittedLine[1]);
                int h = Integer.parseInt(splittedLine[2]);

                int feet = Math.min((2 * (l + w)), Math.min((2 * (w + h)), (2 * (h + l))));
                ribbon += feet + l * w * h;
            }
        } catch (Exception ignored) {}
        return ribbon;
    }
    
    public static void main(String[] args) {
        Solution s = new Solution("inputs/year2015/day02/input");
        ResultPrinter.part1Formatter(s.part1());
        ResultPrinter.part2Formatter(s.part2());
    }
}
