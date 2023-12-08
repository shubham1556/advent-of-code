import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day8Part2 {

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

    public static Long gcd(Long num1, Long num2) {
        if (num2 == 0)
            return num1;
        return gcd(num2, num1 % num2);
    }

    public static Long lcmArr(List<Long> arr) {
        Long lcm = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            Long num1 = lcm;
            Long num2 = arr.get(i);
            Long gcd = gcd(num1, num2);
            lcm = (lcm * arr.get(i)) / gcd;
        }
        return lcm;
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
            String inputLine = sc.nextLine().replaceAll("[^\\sA-Z0-9]", "");
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

        String[] starts = { "LCA", "NVA", "GCA", "SXA", "AAA", "GMA" };
        // 11567, 19637, 15871, 21251, 12643, 19099
        char[] instructionArray = instructions.toCharArray();
        List<Long> stepArray = new ArrayList<>();
        for (int k = 0; k < starts.length; k++) {
            Long steps = 0L;
            int instructionIndex = 0;
            while (starts[k].charAt(2) != 'Z') {
                char next = instructionArray[instructionIndex % instructionArray.length];
                instructionIndex++;
                if (next == 'L') {
                    starts[k] = map.get(starts[k]).left;
                } else {
                    starts[k] = map.get(starts[k]).right;
                }
                steps++;
            }
            stepArray.add(steps);
        }

        System.out.println(lcmArr(stepArray));
    }
}