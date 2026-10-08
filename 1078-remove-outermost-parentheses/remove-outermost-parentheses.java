class Solution {
    public String removeOuterParentheses(String s) {
        char[] result = new char[s.length()];
        int depth = 0;
        int index = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                if (depth > 0) {
                    result[index++] = c;
                }
                depth++;
            } else {
                depth--;
                if (depth > 0) {
                    result[index++] = c;
                }
            }
        }

        return new String(result, 0, index);
    }
}