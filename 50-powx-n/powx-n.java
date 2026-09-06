class Solution {
    public double myPow(double x, int n) {
        long longN = n;
        if (longN < 0) {
            x = 1 / x;
            longN = -longN;
        }
       double result = 1.0;
        double currentProduct = x;
        
        while (longN > 0) {
            if (longN % 2 == 1) {
                result *= currentProduct;
            }
            currentProduct *= currentProduct;
            longN /= 2;
        }
        
        return result;
    }
}