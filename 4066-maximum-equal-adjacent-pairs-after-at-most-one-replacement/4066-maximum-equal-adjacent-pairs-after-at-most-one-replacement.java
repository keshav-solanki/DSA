import java.util.*;

class Solution {
    public int maxEqualAdjacentPairs(int[] nums) {

        int original = 0;

        // Count adjacent pairs of different values.
        // key = unordered pair (a, b)
        Map<Long, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length - 1; i++) {

            int a = nums[i];
            int b = nums[i + 1];

            if (a == b) {
                original++;
            } else {
                int min = Math.min(a, b);
                int max = Math.max(a, b);

                long key = ((long) min << 32) | (max & 0xffffffffL);

                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        int maxNewPairs = 0;

        for (int count : map.values()) {
            maxNewPairs = Math.max(maxNewPairs, count);
        }

        return original + maxNewPairs;
    }
}