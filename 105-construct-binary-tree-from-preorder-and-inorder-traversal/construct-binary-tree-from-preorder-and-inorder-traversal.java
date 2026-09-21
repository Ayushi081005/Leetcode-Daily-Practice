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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, new int[]{0}, new int[]{0}, Integer.MAX_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int[] preIndex, int[] inIndex, int stop) {
        
        if (preIndex[0] >= preorder.length || inorder[inIndex[0]] == stop) {
            return null;
        }

        int rootVal = preorder[preIndex[0]++];
        TreeNode root = new TreeNode(rootVal);

        root.left = build(preorder, inorder, preIndex, inIndex, rootVal);

        inIndex[0]++;
        root.right = build(preorder, inorder, preIndex, inIndex, stop);

        return root;
    }
}