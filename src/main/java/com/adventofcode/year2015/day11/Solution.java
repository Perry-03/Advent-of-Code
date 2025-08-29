package com.adventofcode.year2015.day11;

import com.adventofcode.utility.FileUtils;
import com.adventofcode.utility.ResultPrinter;

import java.io.IOException;
import java.util.stream.*;

public class Solution {
    private final String filePath;

    public Solution(String filePath) {
        this.filePath = filePath;
    }

    public String part1() {
        try {
            String psw = FileUtils.readInputAsString(filePath);

            return generateNextPsw(psw);

        } catch (Exception ignored) {}

        return "";
    }

    private String generateNextPsw(String psw) {
        char[] chars = psw.toCharArray();

        while (!isValid(psw)) {
            int i = psw.length() - 1;
            while (i >= 0) {
                if (chars[i] == 'z') {
                    chars[i] = 'a';
                    i--;
                } else {
                    chars[i]++;
                    break;
                }
            }

            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == 'i' || chars[j] == 'o' || chars[j] == 'l') {
                    chars[j]++;
                    for (int k = j + 1; k < chars.length; k++) chars[k] = 'a';
                    break;
                }
            }
            psw = new String(chars);
        }
        return psw;
    }

    public boolean oneIncreasingStraight(final String psw) {
        return IntStream.range(0, psw.length() - 2)
                .filter(c -> psw.charAt(c + 1) == psw.charAt(c) + 1
                        && psw.charAt(c + 2) == psw.charAt(c) + 2)
                .findAny()
                .isPresent();
    }

    public boolean bannedLetters(final String psw) {
        return psw.chars().noneMatch(c -> c == 'i' || c == 'o' || c == 'l');
    }

    public boolean twoNonOverlappingPairs(final String psw) {
        int count = 0;
        for (int i = 0; i < psw.length() - 1; i++) {
            if (psw.charAt(i) == psw.charAt(i+1)) {
                count++;
                i++;
            }
        }
        return count >= 2;
    }

    private boolean isValid(final String psw) {
        return oneIncreasingStraight(psw) &&
                bannedLetters(psw) &&
                twoNonOverlappingPairs(psw);
    }

    public String part2() { return generateNextPsw("hxbxxzaa"); }

    public static void main(String[] args) {
        Solution s = new Solution("inputs/year2015/day11/input");
        ResultPrinter.part1Formatter(s.part1());
        ResultPrinter.part2Formatter(s.part2());
    }
}