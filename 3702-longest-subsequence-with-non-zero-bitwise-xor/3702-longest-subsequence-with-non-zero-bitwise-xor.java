class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }

        // If all elements are 0, no non-zero XOR subsequence can be formed.
        if (!hasNonZero) {
            return 0;
        }

        // If overall XOR sum is already non-zero, full array works (length n).
        // Otherwise, removing one non-zero element leaves non-zero XOR (length n - 1).
        return (totalXor != 0) ? nums.length : nums.length - 1;
    }
}