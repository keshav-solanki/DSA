import java.util.*;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        // A valid palindrome can have at most one character with an odd frequency
        if (oddCount > 1) {
            return "";
        }

        int halfLen = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        char[] half = new char[halfLen];
        Boolean[][][] memo = new Boolean[halfLen + 1][2][26];

        if (dfs(0, true, half, halfCount, midChar, target, memo)) {
            StringBuilder sb = new StringBuilder();
            sb.append(new String(half));
            if (n % 2 != 0) {
                sb.append(midChar);
            }
            for (int i = halfLen - 1; i >= 0; i--) {
                sb.append(half[i]);
            }
            return sb.toString();
        }

        return "";
    }

    private boolean dfs(int idx, boolean isPrefixEqual, char[] half, int[] halfCount, 
                        char midChar, String target, Boolean[][][] memo) {
        int n = target.length();
        int halfLen = half.length;

        if (idx == halfLen) {
            if (!isPrefixEqual) {
                return true;
            }
            // If prefix matches completely up to half, check middle (if odd) and second half
            if (n % 2 != 0) {
                char targetMid = target.charAt(halfLen);
                if (midChar > targetMid) return true;
                if (midChar < targetMid) return false;
            }
            // Check second half in reversed order against the remainder of target
            for (int i = halfLen - 1; i >= 0; i--) {
                char targetChar = target.charAt(n - 1 - i);
                if (half[i] > targetChar) return true;
                if (half[i] < targetChar) return false;
            }
            return false; // Exactly equal to target
        }

        int eqState = isPrefixEqual ? 1 : 0;
        int firstAvail = -1;
        for (int i = 0; i < 26; i++) {
            if (halfCount[i] > 0) {
                firstAvail = i;
                break;
            }
        }

        if (firstAvail != -1 && memo[idx][eqState][firstAvail] != null) {
            return memo[idx][eqState][firstAvail];
        }

        char targetChar = target.charAt(idx);
        int startChar = isPrefixEqual ? (targetChar - 'a') : 0;

        for (int i = startChar; i < 26; i++) {
            if (halfCount[i] == 0) continue;

            char c = (char) ('a' + i);
            half[idx] = c;
            halfCount[i]--;

            boolean nextIsPrefixEqual = isPrefixEqual && (c == targetChar);
            if (dfs(idx + 1, nextIsPrefixEqual, half, halfCount, midChar, target, memo)) {
                return true;
            }

            halfCount[i]++; // Backtrack
        }

        if (firstAvail != -1) {
            memo[idx][eqState][firstAvail] = false;
        }
        return false;
    }
}