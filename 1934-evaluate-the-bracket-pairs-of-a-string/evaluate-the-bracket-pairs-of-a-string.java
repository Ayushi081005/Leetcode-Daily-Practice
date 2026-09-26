class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean inBracket = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                inBracket = true;
                key.setLength(0);
            } else if (c == ')') {
                inBracket = false;
                
                result.append(map.getOrDefault(key.toString(), "?"));
            } else {
                if (inBracket) {
                    key.append(c);
                } else {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}