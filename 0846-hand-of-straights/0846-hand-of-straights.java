
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) return false;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int h : hand) {
            map.put(h, map.getOrDefault(h, 0) + 1);
        }

        Arrays.sort(hand);

        for (int i = 0; i < n; i++) {
            // Skip cards that have already been fully used in prior groups
            if (map.get(hand[i]) == 0) {
                continue;
            }

            int x = hand[i];
            // Directly try to form a group starting at x
            for (int j = 0; j < groupSize; j++) {
                if (!map.containsKey(x) || map.get(x) <= 0) {
                    return false;
                }
                
                map.put(x, map.get(x) - 1);
                x++;
            }
        }
        return true;
    }
}
