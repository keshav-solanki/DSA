class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        long[] prefix = new long[n];

        prefix[0] = stones[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        // Alice must take at least 2 stones initially.
        long ans = prefix[n - 1];

        // Consider prefixes of length 2 to n-1
        for (int i = n - 2; i >= 1; i--) {
            ans = Math.max(ans, prefix[i] - ans);
        }

        return (int) ans;
    }
}