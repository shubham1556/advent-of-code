package com.aoc.practice.sorting;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://adventofcode.com/2023/day/1
// ans = 54076
public class Day1 {

    static class MyRecord {
        int num;
        int idx;

        MyRecord(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }

        public int getNum() {
            return num;
        }

        public int getIdx() {
            return idx;
        }
    }

    public static MyRecord getFirstEnglishNumber(String rawString, boolean findFirst) {
        String[] englishNumerals = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        int[] numerals = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        int index = 8;
        MyRecord desiredRecord = null;

        while (true) {
            int foundIndex = -1;
            if (findFirst == true) {
                foundIndex = rawString.indexOf(englishNumerals[index]);
            } else {
                foundIndex = rawString.lastIndexOf(englishNumerals[index]);
            }
            
            if (foundIndex != -1) {
                MyRecord foundRecord = new MyRecord(numerals[index], foundIndex);
                if (desiredRecord == null) {
                    desiredRecord = foundRecord;
                } else {
                    if (findFirst) {
                        if (desiredRecord.getIdx() > foundIndex) {
                            desiredRecord = foundRecord;
                        }
                    } else {
                        if (desiredRecord.getIdx() < foundIndex) {
                            desiredRecord = foundRecord;
                        }
                    }
                }
            }
            index--;
            if (index == -1) {
                break;
            }
        }
        return desiredRecord;
    }

    public static int getNumber(String rawString) {
        int first = -1;
        int firstIndex = -1;
        int lastIndex = -1;
        int last = -1;
        if (rawString == null) {
            return 0;
        }

        char[] chars = rawString.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            if (Character.isDigit(chars[index])) {
                if (first == -1) {
                    first = Character.getNumericValue(chars[index]);
                    firstIndex = index;
                } else {
                    last = Character.getNumericValue(chars[index]);
                    lastIndex = index;
                }
            }
        }

        MyRecord firstNumber = getFirstEnglishNumber(rawString, true);
        MyRecord lastNumber = getFirstEnglishNumber(rawString, false);

        int finalLast = last;
        int finalFirst = first;

        if (firstNumber != null) {
            if (first == -1) {
                finalFirst = firstNumber.getNum();
            } else if(firstNumber.getIdx() < firstIndex) {
                finalFirst = firstNumber.getNum();
            }
        }

        if (lastNumber != null) {
            if (last == -1) {
                if (first == -1) {
                    finalLast = lastNumber.getNum();
                } else {
                    lastIndex = firstIndex;
                    if (lastNumber.getIdx() > lastIndex) {
                        finalLast = lastNumber.getNum();
                    } else {
                        finalLast = first;
                    }
                }
            } else if (lastNumber.getIdx() > lastIndex) {
                finalLast = lastNumber.getNum();
            }
        }

        if (finalLast == -1) {
            if (finalFirst == -1) {
                return 0;
            }
            return finalFirst * 10 + finalFirst;
        } else {
            return finalFirst * 10 + finalLast;
        }
    }

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        long sum = 0;

        while (true) {
            String callibrationString = sc.nextLine();

            long nextNumber = (long) getNumber(callibrationString);
            sum = sum + nextNumber;
            if (callibrationString == null || callibrationString.isEmpty()) {
                break;
            }
        }
        System.out.println(sum);
    }

    // -----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;

    // -----------MyScanner class for faster input----------
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
    // --------------------------------------------------------
}
