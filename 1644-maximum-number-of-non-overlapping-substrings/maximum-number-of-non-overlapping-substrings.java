class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }

        List<int[]> validIntervals = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;

            int left = first[i];
            int right = last[i];
            boolean isValid = true;

            for (int j = left; j <= right; j++) {
                int c = s.charAt(j) - 'a';

                if (first[c] < left) {
                    isValid = false;
                    break;
                }
                right = Math.max(right, last[c]);
            }

            if (isValid) {
                validIntervals.add(new int[] {left, right});
            }
        }
        validIntervals.sort((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(b[0], a[0]);
        });

        List<String> result = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : validIntervals) {
            int start = interval[0];
            int end = interval[1];

            boolean isNested = false;
            for (int i = result.size() - 1; i >= 0; i--) {
                String prevSub = result.get(i);

                }

            if (start > prevEnd) {
                result.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }

        return result;
    }
}