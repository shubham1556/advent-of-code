import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day8Part1 {

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
    static class RouteNode {
        String left;
        String right;

        public RouteNode(String l, String r) {
            this.left = l;
            this.right = r;
        }
    }

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        String instructions = null;
        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                System.out.println("Instructions done");
                break;
            }
            instructions = inputLine.trim();
        }

        Map<String, RouteNode> map = new HashMap<>();
        while (true) {
            String inputLine = sc.nextLine().replaceAll("[^\\sA-Z]", "");
            if (inputLine == null || inputLine.isEmpty()) {
                System.out.println("Pairs done");
                break;
            }
            String[] inputArr = inputLine.split(" ");
            int count = 0;
            String key = null, left = null, right = null;
            for (int i = 0; i < inputArr.length; i++) {
                if (!inputArr[i].isEmpty()) {
                    count++;
                    if (count == 1) {
                        key = inputArr[i];
                    } else if (count == 2) {
                        left = inputArr[i];
                    } else {
                        right = inputArr[i];
                    }
                }
            }
            if (key != null && left != null && right != null) {
                map.put(key, new RouteNode(left, right));
            }
        }
    
        String start = "AAA";
        int steps = 0;
        int instructionIndex = 0;
        char[] instructionArray = instructions.toCharArray();
        while (start.equals("ZZZ")) {
            char next = instructionArray[instructionIndex % instructionArray.length];
            instructionIndex++;
            if (next == 'L') {
                start = map.get(start).left;
            } else {
                start = map.get(start).right;
            }
            steps++;
        }
        System.out.println(steps);
    }
}