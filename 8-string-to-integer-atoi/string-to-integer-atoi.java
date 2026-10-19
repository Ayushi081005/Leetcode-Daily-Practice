class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int i = 0;
        int n = s.length();

        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        if (i == n) {
            return 0;
        }

        int sign = 1;
        char firstChar = s.charAt(i);
        if (firstChar == '-') {
            sign = -1;
            i++;
        } else if (firstChar == '+') {
            i++;
        }

        long result = 0; 
        while (i < n) {
            char c = s.charAt(i);
          
            if (c < '0' || c > '9') {
                break;
            }

            int digit = c - '0';
            result = result * 10 + digit;

            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            i++;
        }

        return (int) (sign * result);
    }
}