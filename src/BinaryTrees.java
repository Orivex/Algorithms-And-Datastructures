import java.lang.invoke.MethodHandle;
import java.util.*;

public class BinaryTrees {

    public static void main(String[] args) {
        TreeNode G = new TreeNode(2);
        TreeNode F = new TreeNode(2, G, null);
        TreeNode E = new TreeNode(5);
        TreeNode D = new TreeNode(4);
        TreeNode C = new TreeNode(3, F, null);
        TreeNode B = new TreeNode(2, D, E);
        TreeNode A = new TreeNode(1, B, C);
        System.out.println(isBalanced(A));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean isBalanced(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if(current == null) {
                continue;
            }

            queue.add(current.left);
            queue.add(current.right);

            if(Math.abs(getHeight(current.left, 0) - getHeight(current.right, 0)) <= 1) {
                System.out.println(Math.abs(getHeight(current.left, 0) - getHeight(current.right, 0)) + " <= 1");
                continue;
            }

            System.out.println(Math.abs(getHeight(current.left, 0) - getHeight(current.right, 0)) + " NOT <= 1");
            return false;
        }

        return true;
    }

    public static int getHeight(TreeNode node, int height) {
        if(node == null) {
            return 0;
        }
        height = Math.max(getHeight(node.left, height), getHeight(node.right, height)) + 1;
        return height;
    }

    public static List<Integer> inorder(TreeNode root) {

        List<Integer> result = new ArrayList<Integer>();

        if(root == null) {
            return result;
        }

        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();

        while(current != null || stack.size() > 0) {
            while(current != null) {
                stack.push(current);
                current = current.left;
            }

            result.add(null);

            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }

        return result;
    }
}
