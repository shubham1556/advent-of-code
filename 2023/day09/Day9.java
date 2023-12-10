import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day9 {
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
    // ----------------------------------------------------

    static Long getNextValue(List<Long> arr, boolean prev) {
        if (arr.stream().allMatch(item -> item == 0L)) {
            return 0L;
        }
        List<Long> nextArr = new ArrayList<>();
        for (int i = 0; i < arr.size() - 1; i++) {
            nextArr.add(arr.get(i + 1) - arr.get(i));
        }
        if (prev == true) {
            return arr.get(0) - getNextValue(nextArr, true);
        } else {
            return arr.get(arr.size() - 1) + getNextValue(nextArr, false);
        }

    }

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        Long desiredSumPrevious = 0L;
        Long desiredSumNext = 0L;
        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                System.out.println("input done");
                break;
            }

            List<Long> arr = new ArrayList<>();
            String[] inArr = inputLine.split(" ");
            for (int i = 0; i < inArr.length; i++) {
                if (!inArr[i].isEmpty()) {
                    arr.add(Long.parseLong(inArr[i].trim()));
                }
            }
            desiredSumPrevious += getNextValue(arr, true);
            desiredSumNext += getNextValue(arr, false);
        }
        System.out.println("previous " + desiredSumPrevious);
        System.out.println("next " + desiredSumNext);
    }
}