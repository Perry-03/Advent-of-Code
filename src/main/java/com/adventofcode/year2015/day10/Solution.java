package com.adventofcode.year2015.day10;

import com.adventofcode.utility.FileUtils;
import com.adventofcode.utility.ResultPrinter;

import java.util.List;

public class Solution {
    private final String filePath;

    public Solution(String filePath) {
        this.filePath = filePath;
    }

    public int part1() {
        StringBuilder res = calculateLookAndSay(40);

        return res.length();
    }

    private StringBuilder calculateLookAndSay(int steps) {
        StringBuilder res = new StringBuilder();
        try {
            String line = FileUtils.readInputAsString(filePath);
            res = new StringBuilder(line);
            for (int j = 0; j < steps; j++) {
                StringBuilder tmp = new StringBuilder();
                int i = 0;
                while (i < res.length()) {
                    char curr = res.charAt(i);
                    int next = i + 1;
                    int times = 1;
                    while (next < res.length() && res.charAt(next) == curr) {
                        times++;
                        next++;
                    }
                    i = next;
                    tmp.append(times).append(curr);
               }
                res = tmp;
            }
        } catch (Exception ignored) {}
        return res;
    }

    public int part2() {
        return calculateLookAndSay(50).length();
    }

    public static void main(String[] args) {
        Solution s = new Solution("inputs/year2015/day10/input");
        ResultPrinter.part1Formatter(s.part1());
        ResultPrinter.part2Formatter(s.part2());
    }
}