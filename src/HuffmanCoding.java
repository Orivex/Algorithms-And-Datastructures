import java.security.cert.TrustAnchor;
import java.util.*;
import java.util.PriorityQueue;

public class HuffmanCoding {
    public static void main(String[] args) {
        String word = "HALLO ÄMIN";
        System.out.println("Normal: " + encode_normal(word));
        Huffman huffman = new Huffman(word);


        String encoded = huffman.encode();
        System.out.println("Compressed:" + encoded);
        System.out.println(huffman.decode(encoded));
    }

    public static String encode_normal(String word) {
        String encoded = "";
        for (int i = 0; i < word.length(); i++) {
            encoded+=(Integer.toBinaryString(word.charAt(i)));
        }

        return encoded;
    }

    public static class Huffman {

        private Node root;
        private final String text;
        private Map<Character, Integer> charFrequencies;
        private final Map<Character, String> huffmanCodes;

        public Huffman(String text) {
            this.text = text;
            fillCharFrequenciesMap();
            huffmanCodes = new HashMap<>();
        }

        private void fillCharFrequenciesMap() {
            charFrequencies = new HashMap<>();
            for (char character : text.toCharArray()) {
                Integer integer = charFrequencies.get(character);
                charFrequencies.put(character, integer != null ? integer + 1 : 1);
            }
        }

        public String encode() {
            Queue<Node> queue = new PriorityQueue<>();
            charFrequencies.forEach(((character, frequency) -> queue.add(new Leaf(character, frequency))));
            while (queue.size() > 1) {
                queue.add(new Node(queue.poll(), queue.poll()));
            }
            root = queue.peek();
            generateHuffmanCodes(queue.poll(), "");
            return getEncodedText();
        }

        public String decode(String encodedText) {
            StringBuilder sb = new StringBuilder();
            Node current = root;

            for(char character : encodedText.toCharArray()) {
                current = character == '0' ? current.getLeft() : current.getRight();

                if(current instanceof Leaf) {
                    sb.append(((Leaf) current).getCharacter());
                    current = root; //Next word
                }
            }

            return sb.toString();
        }

        private void generateHuffmanCodes(Node node, String code) {
            if(node instanceof Leaf) {
                huffmanCodes.put(((Leaf) node).getCharacter(), code);
                return;
            }

            generateHuffmanCodes(node.getLeft(), code.concat("0"));
            generateHuffmanCodes(node.getRight(), code.concat("1"));
        }

        private String getEncodedText() {
            StringBuilder sb = new StringBuilder();

            for(char character : text.toCharArray()) {
                sb.append(huffmanCodes.get(character));
            }

            return sb.toString();
        }
    }

    public static class Node implements Comparable<Node> {
        private int frequency = 0;
        private Node left;
        private Node right;

        public Node(Node left, Node right) {
            this.frequency = left.getFrequency() + right.getFrequency();
            this.left = left;
            this.right = right;
        }

        public Node(int frequency) {
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(frequency, node.getFrequency());
        }

        public int getFrequency() {
            return frequency;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

    public static class Leaf extends Node {

        private final char character;
        public Leaf(char character, int frequency) {
            super(frequency);
            this.character = character;
        }

        public char getCharacter() {
            return character;
        }
    }
}
