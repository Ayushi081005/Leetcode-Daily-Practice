class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = new HashSet<>();
        int i = 0;
        
        int j = expression.indexOf('}');
        if (j == -1) {
            result.add(expression);
            return new ArrayList<>(result);
        }
      
        int k = j;
        while (k >= 0 && expression.charAt(k) != '{') {
            k--;
        }
    
        String before = expression.substring(0, k);
        String after = expression.substring(j + 1);
        String sub = expression.substring(k + 1, j);
        String[] parts = sub.split(",");
        
        for (String part : parts) {
            String expanded = before + part + after;
            result.addAll(braceExpansionII(expanded));
        }
        
        List<String> list = new ArrayList<>(result);
        Collections.sort(list);
        return list;
    }
}