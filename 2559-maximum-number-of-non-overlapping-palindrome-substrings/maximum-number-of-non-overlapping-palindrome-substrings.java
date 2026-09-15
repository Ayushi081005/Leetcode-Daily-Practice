class Solution {
    public int maxPalindromes(String s, int k) {
        int count = 0;
        int End = -1;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int left = i - k / 2;
            int right = i + k / 2 - (k % 2 == 0 ? 1 : 0);

            if (left > End && isValidPalindrome(s, left, right)) {
                count++;
                End = right;
                continue;
            }
            left = i - k / 2;
            right = i + k / 2 + (k % 2 == 0 ? 0 : 1);

            if (left > End && isValidPalindrome(s, left, right)) {
                count++;
                End = right;
            }
        }

        return count;
    }

    private boolean isValidPalindrome(String s, int left, int right) {
        if (left < 0 || right >= s.length()) return false;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}