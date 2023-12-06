import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Day5Part1 {
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }

    public static Long getDestination(Long sourceNumber, List<List<Long>> destinationToSourceMap) {
        Long destinationNumber = sourceNumber;
        for (int i = 0; i < destinationToSourceMap.size(); i++) {
            List<Long> groupItem = destinationToSourceMap.get(i);
            if (groupItem.get(1) <= sourceNumber && groupItem.get(1) + groupItem.get(2) - 1 >= sourceNumber) {
                long offset = sourceNumber - groupItem.get(1);
                destinationNumber = groupItem.get(0) + offset;
            }
        }
        return destinationNumber;
    }

    public static Long getLocationNumber(Long seedNumber, List<List<Long>> soilToSeedMap,
            List<List<Long>> fertilizerToSoilMap, List<List<Long>> waterToFertilizerMap,
            List<List<Long>> lightToWaterMap, List<List<Long>> temperatureToLightMap,
            List<List<Long>> humidityToTemperatureMap, List<List<Long>> locationToHumidityMap) {

        Long soilNumber = getDestination(seedNumber, soilToSeedMap);
        Long fertilizerNumber = getDestination(soilNumber, fertilizerToSoilMap);
        Long waterNumber = getDestination(fertilizerNumber, waterToFertilizerMap);
        Long lightNumber = getDestination(waterNumber, lightToWaterMap);
        Long temeratureNumber = getDestination(lightNumber, temperatureToLightMap);
        Long humidityNumber = getDestination(temeratureNumber, humidityToTemperatureMap);
        return getDestination(humidityNumber, locationToHumidityMap);
    }

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();

        String[] seedsString = sc.nextLine().split(" ");
        System.out.println("seeds done");
        List<Long> seeds = new ArrayList<>();
        for (int i = 0; i < seedsString.length; i++) {
            if (!seedsString[i].trim().isEmpty()) {
                seeds.add(Long.parseLong(seedsString[i].trim()));
            }
        }

        // seed-to-soil map:
        List<List<Long>> soilToSeedMap = new ArrayList<>();
        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                System.out.println("seed-to-soil done");
                break;
            }
            String[] string = inputLine.split(" ");
            List<Long> listItem = new ArrayList<>();
            for (int i = 0; i < string.length; i++) {
                if (!string[i].trim().isEmpty()) {
                    listItem.add(Long.parseLong(string[i].trim()));
                }
            }
            soilToSeedMap.add(listItem);
        }

        // soil-to-fertilizer map:
        List<List<Long>> fertilizerToSoilMap = new ArrayList<>();
        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                System.out.println("soil-to-fertilizer done");
                break;
            }
            String[] string = inputLine.split(" ");
            List<Long> listItem = new ArrayList<>();
            for (int i = 0; i < string.length; i++) {
                if (!string[i].trim().isEmpty()) {
                    listItem.add(Long.parseLong(string[i].trim()));
                }
            }
            fertilizerToSoilMap.add(listItem);
        }

        // fertilizer-to-water map:
        List<List<Long>> waterToFertilizerMap = new ArrayList<>();
        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                System.out.println("fertilizer-to-water done");
                break;
            }
            String[] string = inputLine.split(" ");
            List<Long> listItem = new ArrayList<>();
            for (int i = 0; i < string.length; i++) {
                if (!string[i].trim().isEmpty()) {
                    listItem.add(Long.parseLong(string[i].trim()));
                }
            }
            waterToFertilizerMap.add(listItem);
        }

        // water-to-light map:
        List<List<Long>> lightToWaterMap = new ArrayList<>();
        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                System.out.println("water-to-light done");
                break;
            }
            String[] string = inputLine.split(" ");
            List<Long> listItem = new ArrayList<>();
            for (int i = 0; i < string.length; i++) {
                if (!string[i].trim().isEmpty()) {
                    listItem.add(Long.parseLong(string[i].trim()));
                }
            }
            lightToWaterMap.add(listItem);
        }

        // light-to-temperature map:
        List<List<Long>> temperatureToLightMap = new ArrayList<>();
        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                System.out.println("light-to-temperature done");
                break;
            }
            String[] string = inputLine.split(" ");
            List<Long> listItem = new ArrayList<>();
            for (int i = 0; i < string.length; i++) {
                if (!string[i].trim().isEmpty()) {
                    listItem.add(Long.parseLong(string[i].trim()));
                }
            }
            temperatureToLightMap.add(listItem);
        }

        // temperature-to-humidity
        List<List<Long>> humidityToTemperatureMap = new ArrayList<>();
        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                System.out.println("temperature-to-humidity done");
                break;
            }
            String[] string = inputLine.split(" ");
            List<Long> listItem = new ArrayList<>();
            for (int i = 0; i < string.length; i++) {
                if (!string[i].trim().isEmpty()) {
                    listItem.add(Long.parseLong(string[i].trim()));
                }
            }
            humidityToTemperatureMap.add(listItem);
        }

        // humidity-to-location
        List<List<Long>> locationToHumidityMap = new ArrayList<>();
        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                System.out.println("humidity-to-location done");
                break;
            }
            String[] string = inputLine.split(" ");
            List<Long> listItem = new ArrayList<>();
            for (int i = 0; i < string.length; i++) {
                if (!string[i].trim().isEmpty()) {
                    listItem.add(Long.parseLong(string[i].trim()));
                }
            }
            locationToHumidityMap.add(listItem);
        }

        Long minLocation = Long.MAX_VALUE;
        for (int i = 0; i < seeds.size(); i++) {
            Long location = getLocationNumber(seeds.get(i), soilToSeedMap, fertilizerToSoilMap, waterToFertilizerMap,
                    lightToWaterMap, temperatureToLightMap, humidityToTemperatureMap, locationToHumidityMap);
            if (location < minLocation) {
                minLocation = location;
            }
        }
        System.out.println(minLocation);
    }
}
