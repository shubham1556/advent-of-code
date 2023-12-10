import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day10Part1 {
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

    static class Coordinate {
        int x;
        int y;
        int hashCode;

        public Coordinate(int i, int j) {
            this.x = i;
            this.y = j;
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
            if (!(o instanceof Coordinate))
                return false;
            Coordinate other = (Coordinate) o;
            return this.x == other.x && this.y == other.y;
        }
    }

    public static boolean isValidRight(Character nextCharacter) {
        return nextCharacter == '-' || nextCharacter == 'J' || nextCharacter == '7';
    }

    public static boolean isValidLeft(Character nextCharacter) {
        return nextCharacter == '-' || nextCharacter == 'L' || nextCharacter == 'F';
    }

    public static boolean isValidDown(Character nextCharacter) {
        return nextCharacter == '|' || nextCharacter == 'L' || nextCharacter == 'J';
    }

    public static boolean isValidUp(Character nextCharacter) {
        return nextCharacter == '|' || nextCharacter == '7' || nextCharacter == 'F';
    }

    // | is a vertical pipe connecting north and south.
    // - is a horizontal pipe connecting east and west.
    // L is a 90-degree bend connecting north and east.
    // J is a 90-degree bend connecting north and west.
    // 7 is a 90-degree bend connecting south and west.
    // F is a 90-degree bend connecting south and east.
    // . is ground; there is no pipe in this tile.
    // S is the starting position of the animal; there is a pipe on this tile, but
    // your sketch doesn't show what shape the pipe has.
    public static List<Coordinate> getNeightbors(List<List<Character>> maze, Coordinate node) {
        List<Coordinate> neighbors = new ArrayList<>();
        int i = node.x;
        int j = node.y;
        Character currentChar = maze.get(i).get(j);
        if (currentChar == '|') {
            // Check up
            if (i > 0 && isValidUp(maze.get(i - 1).get(j))) {
                neighbors.add(new Coordinate(i - 1, j));
            }

            // Check down
            if (i < maze.size() - 1 && isValidDown(maze.get(i + 1).get(j))) {
                neighbors.add(new Coordinate(i + 1, j));
            }
        }

        if (currentChar == '-') {
            // Check left
            if (j > 0 && isValidLeft(maze.get(i).get(j - 1))) {
                neighbors.add(new Coordinate(i, j - 1));
            }

            // Check right
            if (j < (maze.get(i).size() - 1) && isValidRight(maze.get(i).get(j + 1))) {
                neighbors.add(new Coordinate(i, j + 1));
            }
        }

        if (currentChar == 'L') {
            // Check up
            if (i > 0 && isValidUp(maze.get(i - 1).get(j))) {
                neighbors.add(new Coordinate(i - 1, j));
            }
            // Check right
            if (j < maze.get(i).size() - 1 && isValidRight(maze.get(i).get(j + 1))) {
                neighbors.add(new Coordinate(i, j + 1));
            }
        }

        if (currentChar == 'J') {
            // Check up
            if (i > 0 && isValidUp(maze.get(i - 1).get(j))) {
                neighbors.add(new Coordinate(i - 1, j));
            }

            // Check left
            if (j > 0 && isValidLeft(maze.get(i).get(j - 1))) {
                neighbors.add(new Coordinate(i, j - 1));
            }
        }

        if (currentChar == '7') {
            // check left
            if (j > 0 && isValidLeft(maze.get(i).get(j - 1))) {
                neighbors.add(new Coordinate(i, j - 1));
            }

            // Check down
            if (i < maze.size() - 1 && isValidDown(maze.get(i + 1).get(j))) {
                neighbors.add(new Coordinate(i + 1, j));
            }
        }

        if (currentChar == 'F') {
            // check right
            if (j < maze.get(i).size() - 1 && isValidRight(maze.get(i).get(j + 1))) {
                neighbors.add(new Coordinate(i, j + 1));
            }

            // Check down
            if (i < maze.size() - 1 && isValidDown(maze.get(i + 1).get(j))) {
                neighbors.add(new Coordinate(i + 1, j));
            }
        }
        return neighbors;
    }

    // 6951
    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        List<List<Character>> maze = new ArrayList<>();
        int startX = -1, startY = -1, i = 0;
        String s = sc.nextLine();
        Character myStartChar = s.toCharArray()[0];
        System.out.println("Start set to " + myStartChar);
        while (true) {
            String inputLine = sc.nextLine();
            if (inputLine == null || inputLine.isEmpty()) {
                System.out.println("input done");
                break;
            }
            char[] mazeLine = inputLine.toCharArray();
            List<Character> mazeList = new ArrayList<>();
            int j = 0;
            for (char c : mazeLine) {
                if (c == 'S') {
                    startX = i;
                    startY = j;
                }
                mazeList.add(Character.valueOf(c));
                j++;
            }
            maze.add(mazeList);
            i++;
        }

        maze.get(startX).set(startY, myStartChar);

        Coordinate start = new Coordinate(startX, startY);
        Coordinate pointer1Previous = start;
        Coordinate pointer2Previous = start;
        int steps = 0;
        List<Coordinate> firstNext = getNeightbors(maze, start);
        Coordinate pointer1 = firstNext.get(0);
        Coordinate pointer2 = firstNext.get(1);
        while (!pointer1.equals(pointer2)) {
            steps++;
            Coordinate next1 = getNext(pointer1Previous, getNeightbors(maze, pointer1));
            if (next1 == null) {
                System.out.println("NULL next for pointer 1!!!");
            }
            pointer1Previous = pointer1;
            pointer1 = next1;

            Coordinate next2 = getNext(pointer2Previous, getNeightbors(maze, pointer2));
            if (next2 == null) {
                System.out.println("NULL next for pointer 2!!!");
            }
            pointer2Previous = pointer2;
            pointer2 = next2;
        }
        System.out.println(steps + 1);
    }

    static Coordinate getNext(Coordinate previous, List<Coordinate> neighbors) {
        if (neighbors.size() == 1 && !neighbors.get(0).equals(previous)) {
            return neighbors.get(0);
        }

        if (neighbors.size() == 2) {
            if (!neighbors.get(0).equals(previous)) {
                return neighbors.get(0);
            }
            if (!neighbors.get(1).equals(previous)) {
                return neighbors.get(1);
            }
        }
        return null;
    }
}
