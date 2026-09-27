import java.util.*;

class Solution {
    public long maxEarnings(int[][] meetings) {

        // Sort by ending time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[1], b[1]));

        int n = meetings.length;

        // dp[i] = maximum earning when meeting i
        // is the last selected meeting
        long[] dp = new long[n];

        // prefixBest[i] =
        // maximum value of dp[j] - end[j]
        // for j <= i
        long[] prefixBest = new long[n];

        for (int i = 0; i < n; i++) {

            int start = meetings[i][0];
            int end = meetings[i][1];
            int revenue = meetings[i][2];

            // Select only this meeting
            dp[i] = revenue;

            // Find the last meeting whose end <= current start
            int j = findLastEnd(meetings, i - 1, start);

            if (j >= 0) {
                /*
                 * Previous earning:
                 *
                 * dp[j]
                 *
                 * Gap:
                 *
                 * start - end[j]
                 *
                 * Total:
                 *
                 * dp[j] + start - end[j] + revenue
                 *
                 * = start + revenue + (dp[j] - end[j])
                 */
                dp[i] = Math.max(
                    dp[i],
                    start + revenue + prefixBest[j]
                );
            }

            // Build prefix maximum
            long currentValue = dp[i] - (long) end;

            if (i == 0) {
                prefixBest[i] = currentValue;
            } else {
                prefixBest[i] = Math.max(
                    prefixBest[i - 1],
                    currentValue
                );
            }
        }

        long answer = 0;

        for (long value : dp) {
            answer = Math.max(answer, value);
        }

        return answer;
    }

    private int findLastEnd(int[][] meetings, int right, int start) {

        int left = 0;
        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (meetings[mid][1] <= start) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }
}