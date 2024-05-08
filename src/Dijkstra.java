import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    public static void main(String[] args) {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex S = new Vertex("S");


        addConnection(A, B, 20);
        addConnection(B, C, 10);
        addConnection(C, A, 50);
        addConnection(A, D, 15 );
        addConnection(S, A, 20);
        addConnection(S, D, 10);
        addConnection(C, D, 50);

        shortest_path(A, S);

    }

    static void addConnection(Vertex vertex1, Vertex vertex2, int weight) {
        vertex1.neighbors.add(new Edge(vertex2, weight));
        vertex2.neighbors.add(new Edge(vertex1, weight));

        vertex1.previousV = vertex2;
        vertex2.previousV = vertex1;
    }

    static void shortest_path(Vertex startV, Vertex endV) {
        startV.distance = 0;
        startV.previousV = null;
        HashSet<Vertex> visits = new HashSet<>();
        PriorityQueue<Vertex> pq = new PriorityQueue();

        visits.add(startV);
        for(Edge edge : startV.neighbors) {
            edge.connectedV.distance = edge.weight;
            edge.connectedV.previousV = startV;
            pq.add(edge.connectedV);
        }

        while (!pq.isEmpty()) {
            Vertex currentV = pq.poll();

            visits.add(currentV);

            for (Edge edge : currentV.neighbors) {
                //System.out.println(edge.connectedV.previousV.name + currentV.name);
                if(visits.contains(edge.connectedV)) {
                    continue;
                }

                if((edge.weight + currentV.distance) < edge.connectedV.distance) {
                    edge.connectedV.distance = edge.weight + currentV.distance;
                    edge.connectedV.previousV = currentV;
                }

                pq.add(edge.connectedV);
            }
        }

        Vertex currentV = endV;
        System.out.print(endV.name);

        while(currentV.previousV != null) {
           System.out.print( "<-" + currentV.previousV.name );
           currentV = currentV.previousV;
        }

        System.out.println(" " + endV.distance);

    }

    static class Vertex implements  Comparable<Vertex> {

        String name;
        List<Edge> neighbors = new ArrayList<>();
        int distance;

        Vertex previousV;

        public Vertex(String name) {
            this.name = name;
            this.distance = Integer.MAX_VALUE;
        }

        @Override
        public int compareTo(Vertex o) {
            return o.distance;
        }
    }

    static class Edge {
        int weight;
        Vertex connectedV;

        public Edge(Vertex connectedV, int weight) {
            this.connectedV = connectedV;
            this.weight = weight;
        }
    }
}
