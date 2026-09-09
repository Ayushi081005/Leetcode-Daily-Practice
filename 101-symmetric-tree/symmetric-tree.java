/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> levelValues = new ArrayList<>();
            boolean hasNonNullInNextLevel = false;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                if (node == null) {
                    levelValues.add(null);
                } else {
                    levelValues.add(node.val);
                    
                    queue.add(node.left);
                    queue.add(node.right);

                    if (node.left != null || node.right != null) {
                        hasNonNullInNextLevel = true;
                    }
                }
            }

            if (!isPalindrome(levelValues)) {
                return false;
            }

            if (!hasNonNullInNextLevel) {
                break;
            }
        }

        return true;
    }

    private boolean isPalindrome(List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            Integer val1 = list.get(left);
            Integer val2 = list.get(right);

            if (val1 == null && val2 == null) {
                left++;
                right--;
                continue;
            }
            if (val1 == null || val2 == null || !val1.equals(val2)) {
                return false;
            }

            left++;
            right--;
        }

        return true;

        
    }
}