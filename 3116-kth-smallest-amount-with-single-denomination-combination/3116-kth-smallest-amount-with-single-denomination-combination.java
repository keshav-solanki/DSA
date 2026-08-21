import java.util.*;

class Solution {

    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);

        // Remove coins whose multiples are already covered
        // by a smaller coin.
        List<Integer> list = new ArrayList<>();

        for (int coin : coins) {
            boolean redundant = false;

            for (int x : list) {
                if (coin % x == 0) {
                    redundant = true;
                    break;
                }
            }

            if (!redundant) {
                list.add(coin);
            }
        }

        int n = list.size();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = list.get(i);
        }

        long low = 1;
        long high = (long) a[0] * k;

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (count(mid, a) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(long x, int[] coins) {
        int n = coins.length;
        long result = 0;

        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;

                    lcm = lcm(lcm, coins[i]);

                    // No multiple of this LCM can be <= x
                    if (lcm > x) {
                        break;
                    }
                }
            }

            if (lcm > x) {
                continue;
            }

            long cnt = x / lcm;

            if ((bits & 1) == 1) {
                result += cnt;
            } else {
                result -= cnt;
            }
        }

        return result;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}