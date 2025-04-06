package com.adventofcode.year2015.day06;

import com.adventofcode.utility.FileUtils;
import com.adventofcode.utility.ResultPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Solution {
    private final String filePath;

    public Solution(String filePath) {
        this.filePath = filePath;
    }

    public int part1() {
        boolean[][] lights = new boolean[1000][1000];
        try {
            List<String> lines = FileUtils.readInputAsStringList(filePath);
            Pattern cmd = Pattern.compile("(turn on|turn off|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)");
            for (String line : lines) {
                Matcher matcher = cmd.matcher(line);
                if (matcher.matches()) {
                    String command = matcher.group(1);
                    int x1 = Integer.parseInt(matcher.group(2));
                    int y1 = Integer.parseInt(matcher.group(3));
                    int x2 = Integer.parseInt(matcher.group(4));
                    int y2 = Integer.parseInt(matcher.group(5));

                    switch (command) {
                        case "turn on":
                            turnOn(lights, x1, y1, x2, y2);
                            break;
                        case "turn off":
                            turnOff(lights, x1, y1, x2, y2);
                            break;
                        case "toggle":
                            toggle(lights, x1, y1, x2, y2);
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                }
            }
        } catch (Exception ignored) {}

        return (int) Arrays.stream(lights)
                .flatMapToInt(row -> IntStream.range(0, row.length)
                        .map(i -> row[i] ? 1 : 0))
                .filter(x -> x == 1)
                .count();
    }

    public int part2() {
        int[][] lights = new int[1000][1000];
        try {
            List<String> lines = FileUtils.readInputAsStringList(filePath);
            Pattern cmd = Pattern.compile("(turn on|turn off|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)");
            for (String line : lines) {
                Matcher matcher = cmd.matcher(line);
                if (matcher.matches()) {
                    String command = matcher.group(1);
                    int x1 = Integer.parseInt(matcher.group(2));
                    int y1 = Integer.parseInt(matcher.group(3));
                    int x2 = Integer.parseInt(matcher.group(4));
                    int y2 = Integer.parseInt(matcher.group(5));

                    switch (command) {
                        case "turn on":
                            turnOnBrightness(lights, x1, y1, x2, y2);
                            break;
                        case "turn off":
                            turnOffBrightness(lights, x1, y1, x2, y2);
                            break;
                        case "toggle":
                            toggleBrightness(lights, x1, y1, x2, y2);
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                }
            }
        } catch (Exception ignored) {}

        return Arrays.stream(lights)
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    private void turnOnBrightness(int[][] lights, int x1, int y1, int x2, int y2) {
        for (int x = x1; x <= x2; x++)
            for (int y = y1; y <= y2; y++)
                lights[x][y]++;
    }

    private void turnOffBrightness(int[][] lights, int x1, int y1, int x2, int y2) {
        for (int x = x1; x <= x2; x++)
            for (int y = y1; y <= y2; y++)
                if (lights[x][y] > 0)
                    lights[x][y]--;
    }

    private void toggleBrightness(int[][] lights, int x1, int y1, int x2, int y2) {
        for (int x = x1; x <= x2; x++)
            for (int y = y1; y <= y2; y++)
                lights[x][y] += 2;
    }

    private void turnOn(boolean[][] lights, int x1, int y1, int x2, int y2) {
        int startY = y1;
        while (x1 <= x2) {
            while (startY <= y2) {
                lights[x1][startY] = true;
                startY++;
            }
            startY = y1;
            x1++;
        }
    }

    private void turnOff(boolean[][] lights, int x1, int y1, int x2, int y2) {
        int startY = y1;
        while (x1 <= x2) {
            while (startY <= y2) {
                lights[x1][startY] = false;
                startY++;
            }
            startY = y1;
            x1++;
        }
    }

    private void toggle(boolean[][] lights, int x1, int y1, int x2, int y2) {
        int startY = y1;
        while (x1 <= x2) {
            while (startY <= y2) {
                lights[x1][startY] = !lights[x1][startY];
                startY++;
            }
            startY = y1;
            x1++;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution("inputs/year2015/day06/input");
        ResultPrinter.part1Formatter(s.part1());
        ResultPrinter.part2Formatter(s.part2());
    }
}