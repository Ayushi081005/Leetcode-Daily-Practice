class Solution {
    public int minAddToMakeValid(String s) {
        int openBrackets = 0;
        int minAdds = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openBrackets++;
            } else {
                if (openBrackets > 0) {
                    openBrackets--;
                } else {
                    minAdds++;
                }
            }
        }
        
        return minAdds + openBrackets;
    }
}