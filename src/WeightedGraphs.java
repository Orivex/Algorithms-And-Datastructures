import java.util.*;
import java.util.PriorityQueue;

public class WeightedGraphs {
    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph();

        WeightedGraph.Vertex a = new WeightedGraph.Vertex("a");
        WeightedGraph.Vertex b = new WeightedGraph.Vertex("b");
        WeightedGraph.Vertex c = new WeightedGraph.Vertex("c");
        WeightedGraph.Vertex d = new WeightedGraph.Vertex("d");

        WeightedGraph.Edge ab = new WeightedGraph.Edge(a, b, 4);
        WeightedGraph.Edge bc = new WeightedGraph.Edge(b, c, 1);
        WeightedGraph.Edge cd = new WeightedGraph.Edge(c, d, 3);
        WeightedGraph.Edge da = new WeightedGraph.Edge(d, a, 2);

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);

        graph.addEdge(ab);
        graph.addEdge(bc);
        graph.addEdge(cd);
        graph.addEdge(da);

        graph.dijkstra(a);
    }

    static class WeightedGraph {

        public PriorityQueue<Edge> edges = new PriorityQueue<>();
        public List<Vertex> vertices = new ArrayList<>();

        public void addVertex(Vertex vertex) {
            vertices.add(vertex);
        }
        public void addEdge(Edge edge) {
            edges.add(edge);
        }
        public void dijkstra(Vertex sourceV) {

            sourceV.distance = 0;

            Set<Vertex> visits = new HashSet<>();

            Queue<Vertex> queue = new PriorityQueue<>();
            queue.add(sourceV);

            List<Vertex> vertices = new ArrayList<>();

            while (!queue.isEmpty()) {
                Vertex currentV = queue.poll();

                if (!visits.contains(currentV)) {
                    vertices.add(currentV);
                }

                for (Edge edge : edges) {
                    if (!visits.contains(edge.vertex2)) {
                        shortestPath_modify(edge.vertex2, edge.weight, currentV);
                        queue.add(edge.vertex2);
                    }
                }

                visits.add(currentV);
            }

            for (int i = 0; i < vertices.size(); i++) {
                System.out.println("Path to " + vertices.get(i).name + ": ");
                Vertex currentV = vertices.get(i);

                while (currentV != null) {
                    System.out.print("<-" + currentV.name);
                    currentV = currentV.previousV;
                }

                System.out.println(" ");
            }
        }

        private void shortestPath_modify(Vertex adjacentV, int weight, Vertex sourceV) {
            int newDistance = sourceV.distance + weight;
            if (newDistance < adjacentV.distance) {
                adjacentV.distance = newDistance;
                adjacentV.previousV = sourceV;
            }
        }

        public List<Edge> kruskal() {
            Map<Vertex, Vertex> parent = new HashMap<>();
            List<Edge> mst = new ArrayList<>();

            for (Vertex vertex : vertices) {
                parent.put(vertex, vertex);
                System.out.println("a");
            }

            for (Edge edge : edges) {
                Vertex vertex1Parent = findParent(edge.vertex1, parent);
                Vertex vertex2Parent = findParent(edge.vertex2, parent);

                //v1 != v2 ? UNION: continue;
                if (vertex1Parent != vertex2Parent) {
                    mst.add(edge);
                    parent.put(vertex1Parent, vertex2Parent);
                }
            }

            return mst;
        }

        Vertex findParent(Vertex vertex, Map<Vertex, Vertex> parent) {
            if (parent.get(vertex) == vertex) {
                return vertex;
            }
            return findParent(parent.get(vertex), parent);
        }

        boolean hasCycle() {
            return false;
        }

        static class Vertex implements Comparable<Vertex> {
            String name;
            int distance = Integer.MAX_VALUE;
            Vertex previousV = null;

            public Vertex(String name) {
                this.name = name;
            }

            @Override
            public int compareTo(Vertex vertex) {
                return Integer.compare(distance, vertex.distance);
            }
        }

        static class Edge implements Comparable<Edge> {
            int weight = 0;
            Vertex vertex1;
            Vertex vertex2;


            public Edge(Vertex srcV, Vertex dstV, int weight) {
                this.vertex1 = srcV;
                this.vertex2 = dstV;
                this.weight = weight;
            }

            @Override
            public int compareTo(Edge o) {
                return Integer.compare(weight, o.weight);
            }
        }

    }
}
