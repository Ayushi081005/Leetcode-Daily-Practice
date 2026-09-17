class Solution {
    public int kthFactor(int n, int k) {
        List<Integer> divisors = new ArrayList<>();
        int sqrt = (int) Math.sqrt(n);
        
        for (int i = 1; i <= sqrt; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i * i != n) {
                    divisors.add(n / i);
                }
            }
        } 
        if (divisors.size() < k) {
            return -1;
        }
        
        Collections.sort(divisors);
        return divisors.get(k - 1);
    }
}