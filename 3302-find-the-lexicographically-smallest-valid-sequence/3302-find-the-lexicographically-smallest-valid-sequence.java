import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // next[i] = first occurrence of word2[i]
        // from position >= current position is handled by scanning.
        //
        // suf[i] = latest index in word1 from which
        // word2[i..m-1] can be matched exactly.
        int[] suf = new int[m];
        Arrays.fill(suf, -1);

        int p = n - 1;

        for (int i = m - 1; i >= 0; i--) {
            while (p >= 0 && word1.charAt(p) != word2.charAt(i)) {
                p--;
            }

            if (p < 0) {
                break;
            }

            suf[i] = p;
            p--;
        }

        int[] ans = new int[m];

        int prev = -1;
        boolean used = false;

        for (int i = 0; i < m; i++) {

            boolean found = false;

            for (int j = prev + 1; j < n; j++) {

                // If we use a mismatch here, the remaining
                // characters must match EXACTLY.
                if (word1.charAt(j) != word2.charAt(i)) {

                    if (used) {
                        continue;
                    }

                    if (i == m - 1) {
                        ans[i] = j;
                        prev = j;
                        used = true;
                        found = true;
                        break;
                    }

                    // Remaining word2[i+1...] must match exactly
                    // after j.
                    if (suf[i + 1] != -1 && suf[i + 1] > j) {
                        ans[i] = j;
                        prev = j;
                        used = true;
                        found = true;
                        break;
                    }

                } else {

                    // Exact match.
                    //
                    // We don't need the suffix to be exact here,
                    // because the one mismatch can still be used later.
                    ans[i] = j;
                    prev = j;
                    found = true;
                    break;
                }
            }

            if (!found) {
                return new int[0];
            }
        }

        return ans;
    }
}