package com.adventofcode.year2015.day13;

import com.adventofcode.utility.FileUtils;
import com.adventofcode.utility.ResultPrinter;

import java.sql.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    private final String filePath;
    private final static Pattern CHANGING_HAPPINESS =
            Pattern.compile("^([A-Z][a-z]+) would (lose|gain) (-?\\d+) .*?([A-Z][a-z]+)\\.$");
    private record Happiness(String guest, int happiness) {};
    private final HashMap<String, List<Happiness>> chain = new HashMap<>();
    private final Set<String> guests = new TreeSet<>();
    private int BEST_VALUE = Integer.MIN_VALUE;
    private List<String> BEST_PERMUTATION = new ArrayList<>();

    public Solution(String filePath) {
        this.filePath = filePath;
    }

    public int part1() {
        try {
            List<String> lst = FileUtils.readInputAsStringList(filePath);
            fill(lst);
        } catch (Exception ignored) {}

        return BEST_VALUE;
    }

    private void fill(List<String> lst) {
        for (String str : lst) {
            Matcher matcher = CHANGING_HAPPINESS.matcher(str);
            if (!matcher.find()) continue;
            String fst = matcher.group(1);
            guests.add(fst);
            int happiness = Integer.parseInt(matcher.group(3));
            String snd = matcher.group(4);

            Happiness h = new Happiness(snd, matcher.group(2).equals("lose") ? -happiness : happiness);
            chain.computeIfAbsent(fst, k -> new ArrayList<>()).add(h);
        }

        List<String> lstGuest = new ArrayList<>(guests);
        permuteAndEvaluate(lstGuest, 0);
    }

    private void permuteAndEvaluate(List<String> lst, int start) {
        if (start == lst.size() - 1) {
            int value = calculateHappiness(lst, 0);
            if (value > BEST_VALUE) {
                BEST_VALUE = value;
                BEST_PERMUTATION = new ArrayList<>(lst);
            }
            return;
        }

        for (int i = start; i < lst.size(); i++) {
            Collections.swap(lst, start, i);
            permuteAndEvaluate(lst, start + 1);
            Collections.swap(lst, start, i);
        }
    }

    private int calculateHappiness(List<String> lst, int index) {
        if (index == lst.size() - 1) {
            String current = lst.get(index);
            String next = lst.get(0);
            return getHappiness(current, next) +
                    getHappiness(next, current);
        }
        String current = lst.get(index);
        String next = lst.get(index + 1);
        return getHappiness(current, next) +
                getHappiness(next, current) +
                calculateHappiness(lst, index + 1);
    }

    private int getHappiness(String fst, String snd) {
        return chain.get(fst)
                .stream()
                .filter(x -> x.guest.equals(snd))
                .mapToInt(Happiness::happiness)
                .findFirst()
                .orElse(0);
    }

    public int part2() {
        try {
            List<String> lst = FileUtils.readInputAsStringList(filePath);
            BEST_VALUE = Integer.MIN_VALUE;
            BEST_PERMUTATION = new ArrayList<>();
            chain.clear();
            guests.clear();

            fill2(lst);
        } catch (Exception ignored) {}

        return BEST_VALUE;
    }

    private void fill2(List<String> lst) {
        for (String str : lst) {
            Matcher matcher = CHANGING_HAPPINESS.matcher(str);
            if (!matcher.find()) continue;
            String fst = matcher.group(1);
            guests.add(fst);
            int happiness = Integer.parseInt(matcher.group(3));
            String snd = matcher.group(4);

            Happiness h = new Happiness(snd, matcher.group(2).equals("lose") ? -happiness : happiness);
            chain.computeIfAbsent(fst, k -> new ArrayList<>()).add(h);
        }

        String me = "Me";
        guests.add(me);
        for (String guest : guests) {
            chain.computeIfAbsent(me, k -> new ArrayList<>())
                    .add(new Happiness(guest, 0));

            chain.computeIfAbsent(guest, k -> new ArrayList<>())
                    .add(new Happiness(me, 0));
        }

        List<String> lstGuest = new ArrayList<>(guests);
        permuteAndEvaluate(lstGuest, 0);
    }


    public static void main(String[] args) {
        Solution s = new Solution("inputs/year2015/day13/input");
        ResultPrinter.part1Formatter(s.part1());
        ResultPrinter.part2Formatter(s.part2());
    }
}