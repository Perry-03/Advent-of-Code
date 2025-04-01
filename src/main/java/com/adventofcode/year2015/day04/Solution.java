package com.adventofcode.year2015.day04;

import com.adventofcode.utility.FileUtils;
import com.adventofcode.utility.ResultPrinter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    private final String filePath;

    public Solution(String filePath) { this.filePath = filePath; }

    public int part1() {
        int n = 0;
        try {
            String input = FileUtils.readInputAsString(filePath);

            String concat = input + n;
            Pattern pattern = Pattern.compile("^0{5}.+");
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            while (true) {
                byte[] digestBytes = md5.digest(concat.getBytes(StandardCharsets.UTF_8));

                String hexDigest = bytesToHex(digestBytes);

                if (pattern.matcher(hexDigest).find()) {
                    break;
                }
                n++;
                concat = input + n;
            }

        } catch (Exception ignored) {}

        return n;
    }

    public int part2() {
        int n = 0;
        try {
            String input = FileUtils.readInputAsString(filePath);

            String concat = input + n;
            Pattern pattern = Pattern.compile("^0{6}.+");
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            while (true) {
                byte[] digestBytes = md5.digest(concat.getBytes(StandardCharsets.UTF_8));

                String hexDigest = bytesToHex(digestBytes);

                if (pattern.matcher(hexDigest).find()) {
                    break;
                }
                n++;
                concat = input + n;
            }

        } catch (Exception ignored) {}

        return n;
    }

    public String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);  // Maschera con 0xff per gestire il byte come un numero positivo
            if (hex.length() == 1) {
                hexString.append('0');  // Aggiungi uno zero iniziale per un byte singolo
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }



    public static void main(String[] args) {
         Solution s = new Solution("inputs/year2015/day04/input");
        ResultPrinter.part1Formatter(s.part1());
        ResultPrinter.part2Formatter(s.part2());
    }
}
