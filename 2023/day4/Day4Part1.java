import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.HashSet;
import java.util.StringTokenizer;

// https://adventofcode.com/2023/day/4
public class Day4Part1 {
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

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();

        long desiredSum = 0;
        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                break;
            }

            String cardString = inputLine.split(":")[1];
            String[] cardNumbers = cardString.split("\\|");
            String[] winningCardNumbers = cardNumbers[0].trim().split(" ");
            String[] myCardNumbers = cardNumbers[1].trim().split(" ");
            int matchCount = 0;
            Set<Long> winningNumberSet = new HashSet<>();
            for (int i = 0; i < winningCardNumbers.length; i++) {
                if (!winningCardNumbers[i].trim().isEmpty()) {
                    winningNumberSet.add(Long.parseLong(winningCardNumbers[i].trim()));
                }
            }
            for (int i = 0; i < myCardNumbers.length; i++) {
                if (!myCardNumbers[i].trim().isEmpty()) {
                    Long myNumber = Long.parseLong(myCardNumbers[i].trim());
                    if (winningNumberSet.contains(myNumber)) {
                        matchCount++;
                    }
                }
            }
            desiredSum += Math.pow(2, matchCount - 1);
        }
        System.out.println(desiredSum);
    }

}
