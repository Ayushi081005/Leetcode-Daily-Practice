import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int distanceValue = 0;
        
        for (int x : arr1) {
            int idx = Arrays.binarySearch(arr2, x);
            
            boolean isValid = true;
            
            if (idx >= 0) {
                isValid = false;
            } else {
                int insertionPoint = -idx - 1;
                if (insertionPoint < arr2.length && Math.abs(arr2[insertionPoint] - x) <= d) {
                    isValid = false;
                }
                if (insertionPoint > 0 && Math.abs(arr2[insertionPoint - 1] - x) <= d) {
                    isValid = false;
                }
            }
            
            if (isValid) {
                distanceValue++;
            }
        }
        
        return distanceValue;
    }
}