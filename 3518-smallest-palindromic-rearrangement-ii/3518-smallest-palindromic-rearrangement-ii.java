class Solution {
    static final long LIMIT = (long) 1e18;
    long[][] nCr;

    public String smallestPalindrome(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray())
            cnt[c - 'a']++;

        char mid = 0;
        int halfLen = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1)
                mid = (char) ('a' + i);

            cnt[i] /= 2;
            halfLen += cnt[i];
        }

        buildNCr(halfLen);

        if (countWays(cnt, halfLen) < k)
            return "";

        StringBuilder half = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (cnt[c] == 0)
                    continue;

                cnt[c]--;

                long ways = countWays(cnt, halfLen - pos - 1);

                if (ways >= k) {
                    half.append((char) ('a' + c));
                    break;
                }

                k -= ways;
                cnt[c]++;
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(half);
        if (mid != 0)
            ans.append(mid);
        ans.append(new StringBuilder(half).reverse());

        return ans.toString();
    }

    void buildNCr(int n) {
        nCr = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            nCr[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                nCr[i][j] = Math.min(LIMIT, nCr[i - 1][j - 1] + nCr[i - 1][j]);
            }
        }
    }

    long countWays(int[] cnt, int total) {
        long ways = 1;
        int rem = total;

        for (int x : cnt) {
            if (x == 0) continue;
            ways = multiplyCap(ways, nCr[rem][x]);
            rem -= x;
        }

        return ways;
    }

    long multiplyCap(long a, long b) {
        if (a == 0 || b == 0) return 0;
        if (a > LIMIT / b) return LIMIT;
        return Math.min(LIMIT, a * b);
    }
}