class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
       
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;
       
        if (n == 2) return -1;
        
        char mn = arr[0], mx = arr[0];
        for (char c : arr) {
            if (c < mn) mn = c;
            if (c > mx) mx = c;
        }
        
        if (arr[0] == mn || arr[n - 1] == mx) {
            return 1;
        }
        
        if (arr[0] == mx && arr[n - 1] == mn) {
            boolean hasOtherMn = false;
            boolean hasOtherMx = false;
            for (int i = 1; i < n - 1; i++) {
                if (arr[i] == mn) hasOtherMn = true;
                if (arr[i] == mx) hasOtherMx = true;
            }
            if (!hasOtherMn && !hasOtherMx) {
                return 3;
            }
        }
        
        return 2;
    }
}