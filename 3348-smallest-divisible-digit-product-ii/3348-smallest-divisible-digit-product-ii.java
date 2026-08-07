import java.util.Arrays;

class Solution {
    // Static DP table to cache the best combination of 2s and 3s
    private static final int MAX_2 = 65;
    private static final int MAX_3 = 45;
    private static final int[][] memoLen = new int[MAX_2][MAX_3];
    private static final String[][] memoStr = new String[MAX_2][MAX_3];
    private static boolean isInitialized = false;

    // One-time precomputation of the optimal 2 and 3 combinations
    private static synchronized void initPrecomputation() {
        if (isInitialized) return;

        // Initialize table with default sentinel values
        for (int i = 0; i < MAX_2; i++) {
            for (int j = 0; j < MAX_3; j++) {
                memoLen[i][j] = 1000;
                memoStr[i][j] = "";
            }
        }

        // Generate all valid base combinations of digits 2, 3, 4, 6, 8, 9
        for (int n9 = 0; n9 <= 16; n9++) {
            for (int n8 = 0; n8 <= 16; n8++) {
                for (int n6 = 0; n6 <= 32; n6++) {
                    for (int n4 = 0; n4 <= 25; n4++) {
                        for (int n3 = 0; n3 <= 1; n3++) {
                            for (int n2 = 0; n2 <= 2; n2++) {
                                int total2 = n8 * 3 + n6 + n4 * 2 + n2;
                                int total3 = n9 * 2 + n6 + n3;
                                int count = n9 + n8 + n6 + n4 + n3 + n2;

                                if (total2 < MAX_2 && total3 < MAX_3) {
                                    // Generate the candidate string in naturally sorted order
                                    StringBuilder sb = new StringBuilder();
                                    for (int i = 0; i < n2; i++) sb.append('2');
                                    for (int i = 0; i < n3; i++) sb.append('3');
                                    for (int i = 0; i < n4; i++) sb.append('4');
                                    for (int i = 0; i < n6; i++) sb.append('6');
                                    for (int i = 0; i < n8; i++) sb.append('8');
                                    for (int i = 0; i < n9; i++) sb.append('9');
                                    String candidate = sb.toString();

                                    // Store if it's shorter or lexicographically smaller
                                    if (count < memoLen[total2][total3]) {
                                        memoLen[total2][total3] = count;
                                        memoStr[total2][total3] = candidate;
                                    } else if (count == memoLen[total2][total3]) {
                                        if (memoStr[total2][total3].isEmpty() || candidate.compareTo(memoStr[total2][total3]) < 0) {
                                            memoStr[total2][total3] = candidate;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Post-processing DP: Propagate optimal choices backwards to fill all requested sub-states
        for (int r2 = MAX_2 - 2; r2 >= 0; r2--) {
            for (int r3 = MAX_3 - 2; r3 >= 0; r3--) {
                // Check right state (more 2s available)
                updateState(r2, r3, r2 + 1, r3);
                // Check lower state (more 3s available)
                updateState(r2, r3, r2, r3 + 1);
            }
        }
        isInitialized = true;
    }

    private static void updateState(int r2, int r3, int n2, int n3) {
        int lenNext = memoLen[n2][n3];
        String strNext = memoStr[n2][n3];
        
        if (lenNext < memoLen[r2][r3]) {
            memoLen[r2][r3] = lenNext;
            memoStr[r2][r3] = strNext;
        } else if (lenNext == memoLen[r2][r3]) {
            if (memoStr[r2][r3].isEmpty() || strNext.compareTo(memoStr[r2][r3]) < 0) {
                memoStr[r2][r3] = strNext;
            }
        }
    }

    public String smallestNumber(String num, long t) {
        initPrecomputation();

        long temp = t;
        int[] targetCounts = new int[10];
        int[] primes = {2, 3, 5, 7};
        
        for (int p : primes) {
            while (temp % p == 0) {
                targetCounts[p]++;
                temp /= p;
            }
        }
        
        if (temp > 1) {
            return "-1";
        }

        int n = num.length();
        int[] digits = new int[n];
        int firstZeroPos = -1;
        for (int i = 0; i < n; i++) {
            digits[i] = num.charAt(i) - '0';
            if (digits[i] == 0 && firstZeroPos == -1) {
                firstZeroPos = i;
            }
        }

        int[][] prefixCounts = new int[n + 1][10];
        for (int i = 0; i < n; i++) {
            System.arraycopy(prefixCounts[i], 0, prefixCounts[i + 1], 0, 10);
            addFactors(digits[i], prefixCounts[i + 1], 1);
        }

        if (firstZeroPos == -1 && isSatisfied(prefixCounts[n], targetCounts)) {
            return num;
        }

        int maxCheckedPos = (firstZeroPos != -1) ? firstZeroPos : n - 1;
        for (int i = maxCheckedPos; i >= 0; i--) {
            int startDigit = digits[i] + 1;
            for (int d = startDigit; d <= 9; d++) {
                int[] currentCounts = prefixCounts[i].clone();
                addFactors(d, currentCounts, 1);
                
                int remLen = n - 1 - i;
                String suffix = getMinSuffix(currentCounts, targetCounts, remLen);
                if (suffix != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < i; j++) sb.append(digits[j]);
                    sb.append(d);
                    sb.append(suffix);
                    return sb.toString();
                }
            }
        }

        int requiredLen = n + 1;
        while (true) {
            int[] currentCounts = new int[10];
            String suffix = getMinSuffix(currentCounts, targetCounts, requiredLen);
            if (suffix != null) {
                return suffix;
            }
            requiredLen++;
        }
    }

    private void addFactors(int digit, int[] counts, int val) {
        if (digit <= 1) return;
        if (digit == 2 || digit == 4 || digit == 6 || digit == 8) counts[2] += (digit == 2 ? 1 : (digit == 4 ? 2 : (digit == 6 ? 1 : 3))) * val;
        if (digit == 3 || digit == 6 || digit == 9) counts[3] += (digit == 3 ? 1 : (digit == 6 ? 1 : 2)) * val;
        if (digit == 5) counts[5] += val;
        if (digit == 7) counts[7] += val;
    }

    private boolean isSatisfied(int[] current, int[] target) {
        return current[2] >= target[2] && current[3] >= target[3] && 
               current[5] >= target[5] && current[7] >= target[7];
    }

    private String getMinSuffix(int[] current, int[] target, int remLen) {
        int req2 = Math.max(0, target[2] - current[2]);
        int req3 = Math.max(0, target[3] - current[3]);
        int req5 = Math.max(0, target[5] - current[5]);
        int req7 = Math.max(0, target[7] - current[7]);

        if (req2 >= MAX_2 || req3 >= MAX_3) return null;

        StringBuilder reqDigits = new StringBuilder();
        for (int i = 0; i < req7; i++) reqDigits.append('7');
        for (int i = 0; i < req5; i++) reqDigits.append('5');

        // Look up the optimal 2 & 3 combinations in O(1) time
        int min23Digits = memoLen[req2][req3];
        if (min23Digits == 1000) return null;
        
        String best23Combination = memoStr[req2][req3];
        int totalDigitsNeeded = reqDigits.length() + min23Digits;
        if (totalDigitsNeeded > remLen) {
            return null; 
        }

        StringBuilder suffix = new StringBuilder();
        int paddingOnes = remLen - totalDigitsNeeded;
        for (int i = 0; i < paddingOnes; i++) suffix.append('1');
        
        suffix.append(reqDigits).append(best23Combination);
        char[] chars = suffix.toString().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
