public class Day6Part1 {
    public static void main(String[] args) {
        Long[] maxTimes = { 46807866L };
        Long[] recordDistance = { 214117714021024L };

        Long desiredNumber = 1L;
        for (int gameNumber = 0; gameNumber < maxTimes.length; gameNumber ++) {
            Long ways = 0L;
            for (Long t = 1L; t < maxTimes[gameNumber]; t++) {
                if (t * (maxTimes[gameNumber] - t) > recordDistance[gameNumber]) {
                    ways ++;
                }
            }
            desiredNumber *= ways;
        }

        System.out.println(desiredNumber);
    }
}
