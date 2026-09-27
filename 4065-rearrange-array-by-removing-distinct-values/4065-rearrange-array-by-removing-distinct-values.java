

class Solution {
    public int[] rearrangeArray(int[] nums) {
        int[] freq = new int[101];

        // Count frequency of every number
        for (int num : nums) {
            freq[num]++;
        }

        int[] ans = new int[nums.length];
        int index = 0;

        // Keep performing operations until all elements are removed
        while (index < nums.length) {

            // Visit values in ascending order
            for (int i = 1; i <= 100; i++) {

                if (freq[i] > 0) {
                    ans[index++] = i;
                    freq[i]--;
                }
            }
        }

        return ans;
    }
}