import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.put(row, map.getOrDefault(row, 0) | (1 << (col - 1)));
        }

        // Rows with no reservations can always accommodate 2 groups
        long ans = (long) (n - map.size()) * 2;

        for (int mask : map.values()) {

            // Seats 2,3,4,5
            boolean left = (mask & 0b000011110) == 0;

            // Seats 4,5,6,7
            boolean middle = (mask & 0b001111000) == 0;

            // Seats 6,7,8,9
            boolean right = (mask & 0b111100000) == 0;

            if (left && right) {
                ans += 2;
            } else if (left || middle || right) {
                ans += 1;
            }
        }

        return (int) ans;
    }
}