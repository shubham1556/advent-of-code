import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// https://adventofcode.com/2023/day/3
// 557705
public class Day3Part1 {
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

    public static boolean isPart(Character partIdentifier) {
        return !Character.isDigit(partIdentifier) && !partIdentifier.equals('.');
    }

    public static boolean hasPartNearBy(int i, int j, List<List<Character>> engineSchematic) {
        // Check up
        if (i > 0) {
            if (isPart(engineSchematic.get(i - 1).get(j))) {
                return true;
            }
        }
        // Check left
        if (j > 0) {
            if (isPart(engineSchematic.get(i).get(j - 1))) {
                return true;
            }
        }

        // Check right
        if (j < (engineSchematic.get(i).size() - 1)) {
            if (isPart(engineSchematic.get(i).get(j + 1))) {
                return true;
            }
        }

        // Check down
        if (i < engineSchematic.size() - 1) {
            if (isPart(engineSchematic.get(i + 1).get(j))) {
                return true;
            }
        }

        // Check left diagonal up
        if (i > 0 && j > 0) {
            if (isPart(engineSchematic.get(i - 1).get(j - 1))) {
                return true;
            }
        }

        // Check left diagonal down
        if (i < engineSchematic.size() - 1 && j > 0) {
            if (isPart(engineSchematic.get(i + 1).get(j - 1))) {
                return true;
            }
        }

        // Check right diagonal up
        if (j < (engineSchematic.get(i).size() - 1) && i > 0 ) {
            if (isPart(engineSchematic.get(i - 1).get(j + 1))) {
                return true;
            }
        }

        // Check right diagonal down
        if (j < (engineSchematic.get(i).size() - 1) && i < engineSchematic.size() - 1) {
            if (isPart(engineSchematic.get(i + 1).get(j + 1))) {
                return true;
            }
        }
        return false;
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
            char[] lineArray = inputLine.toCharArray();
            List<Character> lineList = new ArrayList<>();
            for (int i = 0; i < lineArray.length; i++) {
                lineList.add(Character.valueOf(lineArray[i]));
            }
            engineSchematic.add(lineList);
        }

        boolean hasPartNearBy = false;
        long desiredSum = 0L;
        Stack<Integer> partNumber = new Stack<Integer>();
        for (int i = 0; i < engineSchematic.size(); i++) {
            for (int j = 0; j < engineSchematic.get(0).size(); j++) {
                Character currentChar = engineSchematic.get(i).get(j);
                if (Character.isDigit(currentChar)) {
                    hasPartNearBy = hasPartNearBy || hasPartNearBy(i, j, engineSchematic);
                    partNumber.push(Character.getNumericValue(currentChar));
                } else {
                    if (hasPartNearBy) {
                        long validPartNumber = getValidPartNumber(partNumber);
                        desiredSum += validPartNumber;
                    }
                    // reset all flags
                    hasPartNearBy = false;
                    partNumber.clear();
                }
            }
        }
        System.out.println(desiredSum);
    }
}
