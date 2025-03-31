package com.adventofcode.year2015.day03;

import com.adventofcode.utility.FileUtils;
import com.adventofcode.utility.ResultPrinter;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    private final String filePath;

    public Solution(String filePath) { this.filePath = filePath; }

    public int part1() {
        Set<String> houses = new HashSet<>();

        try {
            String input = FileUtils.readInputAsString(filePath);
            int x = 0, y = 0;
            houses.add(x + "," + y);
            for (char c : input.toCharArray()) {
                switch (c) {
                    case '^': y++; break;
                    case 'v': y--; break;
                    case '>': x++; break;
                    case '<': x--; break;
                }
                houses.add(x + "," + y);
            }
        } catch (Exception ignored) {}

        return houses.size();
    }

    public int part2() {
        Set<String> houses = new HashSet<>();

        try {
            String input = FileUtils.readInputAsString(filePath);
            StringBuilder santaPath = new StringBuilder(), robotPath = new StringBuilder();

            for (int i = 0; i < input.length(); i++)
                if (i % 2 == 0)
                    santaPath.append(input.charAt(i));
                else
                    robotPath.append(input.charAt(i));

            houses.add("0,0");

            moveAndAddPosition(santaPath, houses);
            moveAndAddPosition(robotPath, houses);

        } catch (Exception ignored) {}

        return houses.size();
    }

    private void moveAndAddPosition(StringBuilder path, Set<String> houses) {
        int x = 0, y = 0;

        for (int i = 0; i < path.length(); i++) {
            switch (path.charAt(i)) {
                case '^': y++; break;
                case 'v': y--; break;
                case '>': x++; break;
                case '<': x--; break;
            }
            houses.add(x + "," + y);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution("inputs/year2015/day03/input");
        ResultPrinter.part1Formatter(s.part1());
        ResultPrinter.part2Formatter(s.part2());
    }
}
