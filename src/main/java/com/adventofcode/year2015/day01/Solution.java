package com.adventofcode.year2015.day01;

import com.adventofcode.utility.FileUtils;
import com.adventofcode.utility.ResultPrinter;

public class Solution {
    private final String filePath;

    public Solution(final String filePath) { this.filePath = filePath; }

    public int part1() {
        int floor = 0;
        try {
            String input = FileUtils.readInputAsString(filePath);
            for (char c : input.toCharArray())
                if (c == '(')
                    floor++;
                else if (c == ')')
                    floor--;
        } catch (Exception ignored) {}
        return floor;
    }

    public int part2() {
        int floor = 0;
        int pos = 0;

        try {
            String input = FileUtils.readInputAsString(filePath);
            for (char c : input.toCharArray()) {
                if (floor == -1)
                    return pos;
                pos++;
                if (c == '(')
                    floor++;
                else if (c == ')')
                    floor--;
            }

        } catch (Exception ignored) {}
        return pos;
    }

    public static void main(String[] args) {
        Solution s = new Solution("inputs/year2015/day01/input");
        ResultPrinter.part1Formatter(s.part1());
        ResultPrinter.part2Formatter(s.part2());
    }


}
