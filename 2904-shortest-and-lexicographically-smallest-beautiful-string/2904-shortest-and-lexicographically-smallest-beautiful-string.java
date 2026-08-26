class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();

        String ans = "";

        for (int i = 0; i < n; i++) {
            int ones = 0;

            for (int j = i; j < n; j++) {

                if (s.charAt(j) == '1') {
                    ones++;
                }

                // More than k ones -> no need to continue
                if (ones > k) {
                    break;
                }

                // Exactly k ones
                if (ones == k) {
                    String sub = s.substring(i, j + 1);

                    if (ans.equals("")
                            || sub.length() < ans.length()
                            || (sub.length() == ans.length()
                                && sub.compareTo(ans) < 0)) {
                        ans = sub;
                    }

                    // Adding more characters will only increase length
                    // while still having k ones, so no need to continue.
                    break;
                }
            }
        }

        return ans;
    }
}