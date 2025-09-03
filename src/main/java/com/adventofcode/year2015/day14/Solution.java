package com.adventofcode.year2015.day14;

import com.adventofcode.utility.FileUtils;
import com.adventofcode.utility.ResultPrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    private final String filePath;
    private final static Pattern pattern = Pattern.compile(
            "^([A-Z][a-z]+).*?(\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds\\."
    );

    private final Map<String, Integer> points = new HashMap<>();

    public Solution(String filePath) {
        this.filePath = filePath;
    }

    public int part1() {
        final double seconds = 2503;
        int maxDistance = Integer.MIN_VALUE;
        try {
            List<String> lst = FileUtils.readInputAsStringList(filePath);
            for (String str : lst) {
                Matcher matcher = pattern.matcher(str);
                if (!matcher.find()) continue;
                int speed = Integer.parseInt(matcher.group(2));
                int timeFly = Integer.parseInt(matcher.group(3));
                int timeRest = Integer.parseInt(matcher.group(4));

                int completeTime = timeFly + timeRest;
                int fullTimes = (int) (seconds / completeTime);
                int remaining = (int) (seconds % completeTime);
                int lastFly = Math.min(remaining, timeFly);
                int thisDistance = fullTimes * timeFly * speed + lastFly * speed;

                if (maxDistance < thisDistance)
                    maxDistance = thisDistance;
            }
        } catch (Exception ignored) {}

        return maxDistance;
    }

    public int part2() {
        final int totalSeconds = 2503;
        List<String> lst;
        try {
            lst = FileUtils.readInputAsStringList(filePath);
        } catch (Exception e) {
            return 0;
        }

        class Reindeer {
            final String name;
            final int speed, flyTime, restTime;
            int points = 0;

            Reindeer(String name, int speed, int flyTime, int restTime) {
                this.name = name;
                this.speed = speed;
                this.flyTime = flyTime;
                this.restTime = restTime;
            }

            int distanceAt(int seconds) {
                int cycle = flyTime + restTime;
                int fullCycles = seconds / cycle;
                int remainder = seconds % cycle;
                int flying = fullCycles * flyTime + Math.min(remainder, flyTime);
                return flying * speed;
            }
        }

        // Parsing input
        List<Reindeer> reindeers = new ArrayList<>();
        for (String str : lst) {
            Matcher m = pattern.matcher(str);
            if (m.find()) {
                String name = m.group(1);
                int speed = Integer.parseInt(m.group(2));
                int flyTime = Integer.parseInt(m.group(3));
                int restTime = Integer.parseInt(m.group(4));
                reindeers.add(new Reindeer(name, speed, flyTime, restTime));
            }
        }

        // Simulazione
        for (int sec = 1; sec <= totalSeconds; sec++) {
            int finalSec = sec;
            int maxDist = reindeers.stream()
                    .mapToInt(r -> r.distanceAt(finalSec))
                    .max().orElse(0);

            for (Reindeer r : reindeers) {
                if (r.distanceAt(sec) == maxDist) {
                    r.points++;
                }
            }
        }

        return reindeers.stream().mapToInt(r -> r.points).max().orElse(0);
    }


    public static void main(String[] args) {
        Solution s = new Solution("inputs/year2015/day14/input");
        ResultPrinter.part1Formatter(s.part1());
        ResultPrinter.part2Formatter(s.part2());
    }
}