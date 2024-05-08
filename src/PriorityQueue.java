import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class PriorityQueue {
    public static void main(String[] args) {
        Queue<String> queue = new java.util.PriorityQueue<>();
        queue.offer("s");
        queue.offer("defd");
        queue.offer("434d");
        queue.offer("kdi");
        queue.offer("aa");

        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}
