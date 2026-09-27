class Solution {
    public int maxSubarray(int[] nums) {
        int n = nums.length;

        int[] freq = new int[501];

        int left = 0;
        int ans = 0;

        for (int right = 0; right < n; right++) {
            int x = nums[right];

            while (isInvalid(freq, x)) {
                freq[nums[left]]--;
                left++;
            }

            freq[x]++;
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }

    private boolean isInvalid(int[] freq, int x) {

        // a + b = x
        for (int a = 1; a < x; a++) {
            int b = x - a;

            if (freq[a] > 0 && freq[b] > 0) {
                if (a != b || freq[a] >= 2) {
                    return true;
                }
            }
        }

        // x + a = b
        for (int a = 1; x + a <= 500; a++) {
            int b = x + a;

            if (freq[a] > 0 && freq[b] > 0) {
                return true;
            }
        }

        return false;
    }
}