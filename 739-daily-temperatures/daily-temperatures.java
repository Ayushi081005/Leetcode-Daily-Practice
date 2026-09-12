class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> s = new Stack<>();
        int[] answer = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++){
            while (!s.isEmpty() && temperatures[s.peek()] < temperatures[i]){
                int index = s.pop();
                answer[index] = i - index;
            }

            s.push(i);
        }

        return answer;
    }
}