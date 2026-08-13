class Solution {

    static class Node {
        char leftChar;
        char rightChar;

        int prefix;
        int suffix;
        int max;
        int len;

        Node() {}

        Node(char ch) {
            leftChar = rightChar = ch;
            prefix = suffix = max = len = 1;
        }
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        int n = s.length();
        int k = queryIndices.length;

        arr = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            // Update only if character actually changes
            if (arr[index] != ch) {
                arr[index] = ch;
                update(1, 0, n - 1, index, ch);
            }

            ans[i] = tree[1].max;
        }

        return ans;
    }

    private void build(int node, int l, int r) {

        if (l == r) {
            tree[node] = new Node(arr[l]);
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int index, char ch) {

        if (l == r) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, r, index, ch);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node merge(Node a, Node b) {

        Node res = new Node();

        res.len = a.len + b.len;

        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        // Initially, maximum is from either side
        res.prefix = a.prefix;
        res.suffix = b.suffix;
        res.max = Math.max(a.max, b.max);

        if (a.rightChar == b.leftChar) {

            // Middle substring
            res.max = Math.max(res.max, a.suffix + b.prefix);

            // Entire prefix belongs to same character
            if (a.prefix == a.len) {
                res.prefix = a.len + b.prefix;
            }

            // Entire suffix belongs to same character
            if (b.suffix == b.len) {
                res.suffix = b.len + a.suffix;
            }
        }

        return res;
    }
}