import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2Part2 {
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

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        /*
         * int n = sc.nextInt(); // read input as integer
         * long k = sc.nextLong(); // read input as long
         * double d = sc.nextDouble(); // read input as double
         * String str = sc.next(); // read input as String
         * String s = sc.nextLine(); // read whole line as String
         * 
         * int result = 3*n;
         * out.println(result); // print via PrintWriter
         */
        // Game 1: 4 blue, 4 red, 16 green; 14 green, 5 red; 1 blue, 3 red, 5 green

        // -----------PrintWriter for faster output---------------------------------

        Pattern numberRegex = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE);
        long desiredSum = 0L;

        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                break;
            }
            String gameString = inputLine.split(":")[0];
            String cubesGrabString = inputLine.split(":")[1];

            int gameNumber = Integer.parseInt(gameString.split(" ")[1]);
            String[] grabArray = cubesGrabString.split(";");

            int maxRed = 0, maxGreen = 0, maxBlue = 0;
            for (int chance = 0; chance < grabArray.length; chance++) {
                String grab = grabArray[chance].trim();
                String[] cubesArray = grab.split(",");

                int blue = 0, green = 0, red = 0;
                for (int caseIndex = 0; caseIndex < cubesArray.length; caseIndex++) {
                    if (cubesArray[caseIndex].contains("green")) {
                        Matcher numberMatcher = numberRegex.matcher(cubesArray[caseIndex]);
                        if (numberMatcher.find()) {
                            green = Integer.parseInt(numberMatcher.group(0));
                        }
                    }

                    if (cubesArray[caseIndex].contains("blue")) {
                        Matcher numberMatcher = numberRegex.matcher(cubesArray[caseIndex]);
                        if (numberMatcher.find()) {
                            blue = Integer.parseInt(numberMatcher.group(0));
                        }
                    }

                    if (cubesArray[caseIndex].contains("red")) {
                        Matcher numberMatcher = numberRegex.matcher(cubesArray[caseIndex]);
                        if (numberMatcher.find()) {
                            red = Integer.parseInt(numberMatcher.group(0));
                        }
                    }
                }
                if (red > maxRed) {
                    maxRed = red;
                }
                if (green > maxGreen) {
                    maxGreen = green;
                }
                if (blue > maxBlue) {
                    maxBlue = blue;
                }
            }
            desiredSum = desiredSum + (maxBlue * maxRed * maxGreen);
        }
        System.out.println(desiredSum);
    }

}
