package com.adventofcode.year2015.day09;

import com.adventofcode.utility.FileUtils;
import com.adventofcode.utility.ResultPrinter;
import com.sun.source.tree.Tree;

import java.io.File;
import java.util.*;

public class Solution {
    private final String filePath;


    public Solution(String filePath) {
        this.filePath = filePath;
    }

    public int part1() {
        Map<String, HashMap<String, Integer>> map = new HashMap<>();
        int totalDistance = 0;

        try {
            List<String> lines = FileUtils.readInputAsStringList(filePath);
            for (String line : lines) {
                String[] splitted = line.split(" to ");
                String[] splitted2 = splitted[1].split(" = ");
                String start = splitted[0];
                String dest = splitted2[0];
                int dist = Integer.parseInt(splitted2[1]);

                map.computeIfAbsent(start, k -> new HashMap<>()).put(dest, dist);
                map.computeIfAbsent(dest, k -> new HashMap<>()).put(start, dist);
            }

            totalDistance = computePath(map, true);

        } catch (Exception ignore) {}

        return totalDistance;
    }

    private int computePath(Map<String, HashMap<String, Integer>> map, boolean minMax) {
        List<String> cities = new ArrayList<>(map.keySet());
        List<List<String>> permutations = generatePerm(cities);
        int minMaxDst = minMax ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        for (List<String> p : permutations) {
            int currDst = 0;
            for (int i = 0; i < p.size() - 1; i++) {
                String startCity = p.get(i);
                String destCity = p.get(i + 1);
                 currDst += map.get(startCity).get(destCity);
            }
            minMaxDst = minMax ? Math.min(minMaxDst, currDst) : Math.max(minMaxDst, currDst);
        }
        return minMaxDst;
    }

    private List<List<String>> generatePerm(List<String> cities) {
        List<List<String>> perms = new ArrayList<>();
        permute(cities, 0, perms);
        return perms;
    }

    private void permute(List<String> cities, int k, List<List<String>> perms) {
        if (k == cities.size())
            perms.add(new ArrayList<>(cities));
        else {
            for (int i = 0; i < cities.size(); i++) {
                Collections.swap(cities, i, k);
                permute(cities, k + 1, perms);
                Collections.swap(cities, k, i);
            }
        }
    }

    public int part2() {
        Map<String, HashMap<String, Integer>> map = new HashMap<>();
        int totalDistance = 0;

        try {
            List<String> lines = FileUtils.readInputAsStringList(filePath);
            for (String line : lines) {
                String[] splitted = line.split(" to ");
                String[] splitted2 = splitted[1].split(" = ");
                String start = splitted[0];
                String dest = splitted2[0];
                int dist = Integer.parseInt(splitted2[1]);

                map.computeIfAbsent(start, k -> new HashMap<>()).put(dest, dist);
                map.computeIfAbsent(dest, k -> new HashMap<>()).put(start, dist);
            }

            totalDistance = computePath(map, false);

        } catch (Exception ignore) {}

        return totalDistance;
    }

    public static void main(String[] args) {
        Solution s = new Solution("inputs/year2015/day09/input");
        ResultPrinter.part1Formatter(s.part1());
        ResultPrinter.part2Formatter(s.part2());
    }
}