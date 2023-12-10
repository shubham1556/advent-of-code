import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day7Part1 {
    public static char[] charRank = { 'A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J' };

    public static int indexOf(char c) {
        for (int i = 0; i < charRank.length; i++) {
            if (charRank[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public static int compareToForSameTypeCards(String card1, String card2) {
        if (card1.equals(card2)) {
            return 0;
        }

        char[] card1Chars = card1.toCharArray();
        char[] card2Chars = card2.toCharArray();

        for (int i = 0; i < card1.length(); i++) {
            int rank1 = indexOf(card1Chars[i]);
            int rank2 = indexOf(card2Chars[i]);
            if (rank1 == rank2) {
                continue;
            }
            if (rank1 < rank2) {
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }

    // 7 Five of a kind, where all five cards have the same label: AAAAA
    // 6 Four of a kind, where four cards have the same label and one card has a
    // different label: AA8AA
    // 5 Full house, where three cards have the same label, and the remaining two
    // cards share a different label: 23332
    // 4 Three of a kind, where three cards have the same label, and the remaining
    // two cards are each different from any other card in the hand: TTT98
    // 3 Two pair, where two cards share one label, two other cards share a second
    // label, and the remaining card has a third label: 23432
    // 2 One pair, where two cards share one label, and the other three cards have a
    // different label from the pair and each other: A23A4
    // 1 High card, where all cards' labels are distinct: 23456
    public static int getCardTypeRank(Map<Character, Integer> cardMap) {
        if (cardMap.size() == 1) {
            return 7;
        }

        if (cardMap.size() == 2) {
            for (Map.Entry<Character, Integer> entry : cardMap.entrySet()) {
                if (entry.getValue() == 4) {
                    return 6;
                }
            }
            return 5;
        }

        if (cardMap.size() == 3) {
            int pairCount = 0;
            for (Map.Entry<Character, Integer> entry : cardMap.entrySet()) {
                if (entry.getValue() == 2) {
                    pairCount++;
                }
            }
            if (pairCount == 2) {
                return 3;
            }
            return 4;
        }

        if (cardMap.size() == 4) {
            return 2;
        }
        return 1;
    }

    static class Poker {
        public String hand;
        public Long bid;

        Poker(String h, Long b) {
            this.bid = b;
            this.hand = h;
        }
    }

    static class CardComparator implements java.util.Comparator<Poker> {
        @Override
        public int compare(Poker a, Poker b) {
            Map<Character, Integer> card1Map = new HashMap<>();
            Map<Character, Integer> card2Map = new HashMap<>();
            char[] c1 = a.hand.toCharArray();
            char[] c2 = b.hand.toCharArray();

            for (int i = 0; i < a.hand.length(); i++) {
                if (card1Map.get(c1[i]) != null) {
                    Integer count = card1Map.get(c1[i]) + 1;
                    card1Map.put(c1[i], count);
                } else {
                    card1Map.put(c1[i], 1);
                }

                if (card2Map.get(c2[i]) != null) {
                    Integer count = card2Map.get(c2[i]) + 1;
                    card2Map.put(c2[i], count);
                } else {
                    card2Map.put(c2[i], 1);
                }
            }

            int card1TypeRank = getCardTypeRank(card1Map);
            int card2TypeRank = getCardTypeRank(card2Map);
            if (card1TypeRank == card2TypeRank) {
                return compareToForSameTypeCards(a.hand, b.hand);
            }
            if (card1TypeRank < card2TypeRank) {
                return -1;
            }
            return 1;
        }
    }

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

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        List<Poker> cardsAndBid = new ArrayList<>();

        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                break;
            }
            String[] inputArray = inputLine.split(" ");
            cardsAndBid.add(new Poker(inputArray[0].trim(), Long.parseLong(inputArray[1].trim())));
        }

        Collections.sort(cardsAndBid, new CardComparator());
        Long desiredNum = 0L;
        for (int i = cardsAndBid.size() - 1; i >= 0; i--) {
            desiredNum += cardsAndBid.get(i).bid * (i + 1);
        }
        System.out.println("Sum");
        System.out.println(desiredNum);
    }
}