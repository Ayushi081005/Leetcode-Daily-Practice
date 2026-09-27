class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        int letterCount = 0;

         for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int openIdx = stack.pop();
                pair[openIdx] = i;
                pair[i] = openIdx;
            } else {
                letterCount++;
            }
        }

       
        char[] result = new char[letterCount];
        int writeIdx = 0;
        int curr = 0;
        int dir = 1;

        while (curr < n) {
            char c = s.charAt(curr);
            if (c == '(' || c == ')') {
                curr = pair[curr]; 
                dir = -dir;       
            } else {
                result[writeIdx++] = c;
            }
            curr += dir;
        }

        return new String(result);
    }
}