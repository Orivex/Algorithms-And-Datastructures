import java.util.*;
import java.util.PriorityQueue;

public class MinSpanTree {

    public static void main(String[] args) {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("D");
        Vertex F = new Vertex("D");

        A.addNeighbor(C, 1);
        A.addNeighbor(D, 3);
        B.addNeighbor(C, 2);
        C.addNeighbor(D, 6);
        C.addNeighbor(B, 7);
        D.addNeighbor(A, 1);
        E.addNeighbor(A, 7);
        F.addNeighbor(A, 5);
        A.addNeighbor(B, 5);

        prims(A);
    }

    static void prims(Vertex startV) {
        PriorityQueue<Edge> pq = new PriorityQueue();
        for(Edge edge : startV.neighbors) {
            pq.add(edge);
        }

        HashSet<Vertex> visits = new HashSet<>();
        visits.add(startV);

        List<String> path = new ArrayList<>();
        path.add(startV.name);

        int cost = 0;

        while(!pq.isEmpty()) {
            Edge minEdge = pq.remove();

            if(visits.contains(minEdge.vertex)) {
                continue;
            }

            visits.add(minEdge.vertex);

            for(Edge edge : minEdge.vertex.neighbors) {
                pq.add(edge);
            }

            cost += minEdge.weight;

            path.add(minEdge.vertex.name);

        }

        for(String s : path) {
            System.out.print(s + "-");
        }
        System.out.println("Cost:" + cost);
    }

    static class Vertex {
        String name;
        List<Edge> neighbors = new ArrayList<>();

        public Vertex(String name) {
            this.name = name;
        }

        void addNeighbor(Vertex neighbor, int weight) {
            neighbors.add(new Edge(neighbor, weight));
        }
    }

    static class Edge implements Comparable<Edge> {
        int weight;
        Vertex vertex;

        public Edge(Vertex vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return o.weight;
        }
    }
}
