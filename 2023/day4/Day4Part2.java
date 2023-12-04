import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

// https://adventofcode.com/2023/day/4
public class Day4Part2 {
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

    public static void incrementCardCount(int cardIndex, int incrementCount, Map<Integer, Integer> cardCountByNumber) {
        if (cardCountByNumber.containsKey(cardIndex)) {
            int currentCount = cardCountByNumber.get(cardIndex);
            cardCountByNumber.put(cardIndex, currentCount + incrementCount);
        } else {
            cardCountByNumber.put(cardIndex, incrementCount);
        }
    }

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();

        Map<Integer, Integer> cardCountByNumber = new HashMap<>(); 
        int cardIndex = 0;
        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                break;
            }
            cardIndex++;
            incrementCardCount(cardIndex, 1, cardCountByNumber);
            cardCountByNumber.putIfAbsent(cardIndex, 1);
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
            for (int wonCardNumber = cardIndex + 1; wonCardNumber <= cardIndex + matchCount; wonCardNumber++) {
                incrementCardCount(wonCardNumber, cardCountByNumber.get(cardIndex), cardCountByNumber);
            }
        }
        int desiredSum = 0;
        for(Integer cardCount : cardCountByNumber.values()) {
            desiredSum += cardCount;
        }
        System.out.println(desiredSum);
    }

}
