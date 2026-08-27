class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Try to make the answer greater at position i
        // Start from the rightmost position
        for (int i = n - 1; i >= 0; i--) {

            int[] count = freq.clone();

            // Match target's prefix [0 ... i-1]
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int idx = target.charAt(j) - 'a';

                if (count[idx] == 0) {
                    possible = false;
                    break;
                }

                count[idx]--;
            }

            if (!possible) {
                continue;
            }

            // At position i, choose the smallest character
            // greater than target[i]
            int targetIndex = target.charAt(i) - 'a';

            for (int c = targetIndex + 1; c < 26; c++) {

                if (count[c] > 0) {

                    StringBuilder ans = new StringBuilder();

                    // Same prefix as target
                    for (int j = 0; j < i; j++) {
                        ans.append(target.charAt(j));
                    }

                    // First greater character
                    ans.append((char) ('a' + c));
                    count[c]--;

                    // Remaining characters in sorted order
                    for (int x = 0; x < 26; x++) {
                        while (count[x] > 0) {
                            ans.append((char) ('a' + x));
                            count[x]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}