class Solution {
    int[][] dp;
    int[] suffix;
    int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n + 1];

        // Suffix sum:
        // suffix[i] = total stones from i to end
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return solve(0, 1);
    }

    private int solve(int i, int M) {
        // All remaining stones can be taken
        if (i >= n) {
            return 0;
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        // If we can take all remaining piles
        if (2 * M >= n - i) {
            return dp[i][M] = suffix[i];
        }

        int maxStones = 0;

        // Try taking X piles, where 1 <= X <= 2M
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {

            // Current player gets X piles.
            // Opponent will get solve(i + X, max(M, X)).
            int opponent = solve(i + X, Math.max(M, X));

            int current = suffix[i] - opponent;

            maxStones = Math.max(maxStones, current);
        }

        return dp[i][M] = maxStones;
    }
}