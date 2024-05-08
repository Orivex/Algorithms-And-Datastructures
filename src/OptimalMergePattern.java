import java.util.*;
import java.util.PriorityQueue;

public class OptimalMergePattern {
    public static void main(String[] args) {
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0));
        queue.add(new Node(1));
        queue.add(new Node(3));
        queue.add(new Node(4));
        queue.add(new Node(8));

        final Node root;

        while(queue.size() > 1) {
            System.out.println(queue.peek().length);
            queue.add(new Node(queue.poll(), queue.poll()));
            System.out.println(queue.peek().length);
        }

        root = queue.poll();
        System.out.println("------");


        List<Integer> result = new ArrayList<>();
        traverse(root, result);

        System.out.println(result);

    }

    public static void traverse(Node root, List<Integer> result) {
        if(root == null) {
            return;
        }

        result.add(root.length);
        traverse(root.left, result);
        traverse(root.right, result);

    }

    public static class Node implements Comparable<Node> {
        private int length;

        private Node left;
        private Node right;

        public Node(Node left, Node right) {
            this.length = left.getLength() + right.getLength();
            this.left = left;
            this.right = right;
        }

        public Node(int length) {
            this.length = length;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(length, other.length);
        }

        private Node getLeft() {
            return left;
        }

        private Node getRight() {
            return right;
        }

        private int getLength() {
            return length;
        }
    }
}
