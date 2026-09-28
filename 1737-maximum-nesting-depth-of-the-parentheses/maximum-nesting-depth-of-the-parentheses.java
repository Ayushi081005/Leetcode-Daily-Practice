class Solution {
    public int maxDepth(String s) {
        int maxDepth = 0;
        int currentDepth = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                currentDepth++;
                maxDepth = Math.max(maxDepth, currentDepth);
            } else if (ch == ')') {
                currentDepth--;
            }
        }

        return maxDepth;
    }
}