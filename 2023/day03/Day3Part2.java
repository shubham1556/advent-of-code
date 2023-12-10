import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

// https://adventofcode.com/2023/day/3#part2
public class Day3Part2 {
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

    public static class Coordinates {
        int x;
        int y;
        int hashCode;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
            this.hashCode = Objects.hash(x, y);
        }

        @Override
        public int hashCode() {
            return this.hashCode;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Coordinates))
                return false;
            Coordinates other = (Coordinates) o;
            return this.x == other.x && this.y == other.y;
        }
    }

    public static boolean isGear(Character partIdentifier) {
        return partIdentifier.equals('*');
    }

    public static Set<Coordinates> getNearByGearsCoordinates(int i, int j, List<List<Character>> engineSchematic) {
        Set<Coordinates> gearCoordinates = new HashSet<>();

        // Check up
        if (i > 0) {
            if (isGear(engineSchematic.get(i - 1).get(j))) {
                gearCoordinates.add(new Coordinates(i - 1, j));
            }
        }
        // Check left
        if (j > 0) {
            if (isGear(engineSchematic.get(i).get(j - 1))) {
                gearCoordinates.add(new Coordinates(i, j - 1));
            }
        }

        // Check right
        if (j < (engineSchematic.get(i).size() - 1)) {
            if (isGear(engineSchematic.get(i).get(j + 1))) {
                gearCoordinates.add(new Coordinates(i, j + 1));
            }
        }

        // Check down
        if (i < engineSchematic.size() - 1) {
            if (isGear(engineSchematic.get(i + 1).get(j))) {
                gearCoordinates.add(new Coordinates(i + 1, j));
            }
        }

        // Check left diagonal up
        if (i > 0 && j > 0) {
            if (isGear(engineSchematic.get(i - 1).get(j - 1))) {
                gearCoordinates.add(new Coordinates(i - 1, j - 1));
            }
        }

        // Check left diagonal down
        if (i < engineSchematic.size() - 1 && j > 0) {
            if (isGear(engineSchematic.get(i + 1).get(j - 1))) {
                gearCoordinates.add(new Coordinates(i + 1, j - 1));
            }
        }

        // Check right diagonal up
        if (j < (engineSchematic.get(i).size() - 1) && i > 0) {
            if (isGear(engineSchematic.get(i - 1).get(j + 1))) {
                gearCoordinates.add(new Coordinates(i - 1, j + 1));
            }
        }

        // Check right diagonal down
        if (j < (engineSchematic.get(i).size() - 1) && i < engineSchematic.size() - 1) {
            if (isGear(engineSchematic.get(i + 1).get(j + 1))) {
                gearCoordinates.add(new Coordinates(i + 1, j + 1));
            }
        }
        return gearCoordinates;
    }

    public static long getValidPartNumber(Stack<Integer> numberStack) {
        int multiplierIndex = 0;
        long number = 0;
        while (!numberStack.empty()) {
            number += numberStack.pop() * Math.pow(10, multiplierIndex);
            multiplierIndex++;
        }
        return number;
    }

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        List<List<Character>> engineSchematic = new ArrayList<>();

        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                break;
            }
            char[] lineArray = inputLine.trim().toCharArray();
            List<Character> lineList = new ArrayList<>();
            for (int i = 0; i < lineArray.length; i++) {
                lineList.add(Character.valueOf(lineArray[i]));
            }
            engineSchematic.add(lineList);
        }

        Set<Coordinates> gearCoordinates = new HashSet<>();
        long desiredSum = 0L;
        // Key - gear coordinate, Value - numbers near by
        Map<Coordinates, List<Long>> gearPartNumbers = new HashMap<>();
        Stack<Integer> partNumber = new Stack<Integer>();
        for (int i = 0; i < engineSchematic.size(); i++) {
            for (int j = 0; j < engineSchematic.get(0).size(); j++) {
                Character currentChar = engineSchematic.get(i).get(j);
                if (Character.isDigit(currentChar)) {
                    Set<Coordinates> currentCharNearByGears = getNearByGearsCoordinates(i, j, engineSchematic);
                    if (!currentCharNearByGears.isEmpty()) {
                        gearCoordinates.addAll(currentCharNearByGears);
                    }
                    partNumber.push(Character.getNumericValue(currentChar));
                } else {
                    if (!gearCoordinates.isEmpty()) {
                        long validPartNumber = getValidPartNumber(partNumber);
                        for (Coordinates coordinate : gearCoordinates) {
                            if (gearPartNumbers.containsKey(coordinate)) {
                                (gearPartNumbers.get(coordinate)).add(validPartNumber);
                            } else {
                                List<Long> newPartNumberList = new ArrayList<>();
                                newPartNumberList.add(validPartNumber);
                                gearPartNumbers.put(coordinate, newPartNumberList);
                            }
                        }
                    }
                    // reset all flags
                    gearCoordinates = new HashSet<>();
                    partNumber.clear();
                }
            }
        }

        for (Map.Entry<Coordinates, List<Long>> entry : gearPartNumbers.entrySet()) {
            if (entry.getValue().size() == 2) {
                desiredSum += entry.getValue().get(0) * entry.getValue().get(1);
            }
        }
        System.out.println(desiredSum);
    }
}
