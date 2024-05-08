import java.util.*;

public class Graphs {

    private class Graph {
        Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();

        private void addVertex(Vertex vertex) {
            adjVertices.putIfAbsent(vertex, new ArrayList<>());
        }

        private void addEdge_direct(Vertex vertex1, Vertex vertex2) {
            adjVertices.get(vertex1).add(vertex2);
        }
        private void addEdge_undirect(Vertex vertex1, Vertex vertex2) {
            adjVertices.get(vertex1).add(vertex2);
            adjVertices.get(vertex2).add(vertex1);
        }

        private void dfs_iterative(Vertex startVertex, int ttl) {
            Stack<Vertex> stack = new Stack<>();
            stack.push(startVertex);
            Vertex currentV;
            List<Integer> result = new ArrayList<>();

            while(stack.size() > 0 && ttl>0) {

                currentV = stack.pop();
                result.add(currentV.id);

                for (int i = 0; i<adjVertices.get(currentV).size(); i++) {
                    stack.push(adjVertices.get(currentV).get(i));
                }

                ttl--;
            }

            System.out.println(result);
        }

        private void dfs_recursive(Vertex vertex) {
            System.out.println(vertex.id);
            for (int i = 0; i < adjVertices.get(vertex).size(); i++) {
                dfs_recursive(adjVertices.get(vertex).get(i));
            }
        }

        private void bfs_iterative(Vertex vertex) {
            Queue<Vertex> queue = new LinkedList<>();
            queue.offer(vertex);
            Vertex currentV;

            List<Integer> result = new ArrayList<>();

            while(queue.size() > 0) {
                currentV = queue.poll();
                result.add(currentV.id);

                 for (int i = 0; i < adjVertices.get(currentV).size(); i++) {
                     queue.offer(adjVertices.get(currentV).get(i));
                }
            }

            System.out.println(result);
        }

        private boolean hasPath(Vertex vertexSrc, Vertex vertexDst) {
            System.out.println(vertexSrc.id);
            if(vertexSrc == vertexDst) {
                return true;
            }

            for (int i = 0; i < adjVertices.get(vertexSrc).size(); i++) {
                if(hasPath(adjVertices.get(vertexSrc).get(i), vertexDst) == true) {
                    return true;
                }
            }

            return false;
        }

        private boolean hasPath_guarded(Vertex vertexSrc, Vertex vertexDst) {
            Stack<Vertex> stack = new Stack<>();
            List<Vertex> visits = new ArrayList<>();
            stack.push(vertexSrc);
            Vertex currentV;

            while(stack.size() > 0) {

                currentV = stack.pop();
                visits.add(currentV);
                System.out.println(currentV.id);

                if(currentV == vertexDst) {
                    return true;
                }

                for (int i = 0; i<adjVertices.get(currentV).size(); i++) {
                    if(!visits.contains(adjVertices.get(currentV).get(i))) {
                        stack.push(adjVertices.get(currentV).get(i));
                    }
                }
            }

            return false;
        }

        private boolean dfs_guarded(Vertex vertex, ArrayList<Vertex> visits) {
            //For connected components: If one vertex is connected to a vertex that was already visited, then it belongs already to a component. So the count shouldn't be increased.
            if(visits.contains(vertex)) {
                System.out.println(vertex.id + " already visited!");
                return false;
            }

            visits.add(vertex);

            System.out.println("--START FOR " + vertex.id);

            for (int i = 0; i < adjVertices.get(vertex).size(); i++) {
                System.out.println("Checking for " + adjVertices.get(vertex).get(i).id);
                dfs_guarded(adjVertices.get(vertex).get(i), visits);
            }

            System.out.println("--Everything okay with "+ vertex.id);
            return true;
        }

        private int connected_components_count() {
            ArrayList<Vertex> visits = new ArrayList<>();
            int count = 0;

            for (Vertex vertex : adjVertices.keySet()) {
                System.out.println("-----------------NEXT-------------------");
                if(dfs_guarded(vertex, visits) == true) {
                    count++;
                    System.out.println("COUNT++" + count);
                }
                else {
                    System.out.println("STAYS SAME" + count);
                }
            }

            return count;
        }

        private int getComponentSize(Vertex vertex, ArrayList<Vertex> visits) {
            if(visits.contains(vertex)) {
                return 0;
            }

            visits.add(vertex);

            int size = 1;

            for(Vertex neighborV : adjVertices.get(vertex)) {
                //Basically size += size(of the neighbor), size(of the neighbor) += size(of the neighbor's neighbor) ... Repeat that for every neighbor
                size += getComponentSize(neighborV, visits);
            }

            return size;
        }

        private int largestComponent() {
            int largest = 0;
            ArrayList<Vertex> visits = new ArrayList<>();

            for(Vertex vertex : adjVertices.keySet()) {
                largest = Math.max(largest, getComponentSize(vertex, visits));
            }

            return largest;
         }

        private int shortestPath_bfs(Vertex startV, Vertex endV) {
            int pathLength = 0;

            Queue<Map.Entry<Vertex, Integer>> queue = new LinkedList<>();
            queue.add(new AbstractMap.SimpleEntry<>(startV, pathLength));

            List<Vertex> visits = new ArrayList<>();
            visits.add(queue.peek().getKey());

            while (queue.size() > 0) {
                Map.Entry<Vertex, Integer> currentV = queue.poll();

                System.out.println(currentV.getKey().id + " " + currentV.getValue());

                if(currentV.getKey() == endV) {
                    pathLength = currentV.getValue();
                    return pathLength;
                }

                for(Vertex neighborV : adjVertices.get(currentV.getKey())) {
                    if(!visits.contains(neighborV)) {
                        queue.add(new AbstractMap.SimpleEntry<>(neighborV, currentV.getValue()+1));
                        visits.add(neighborV);
                    }
                }
            }

            return 0;
        }

        //Kosaraju's algorithm
        private List<Set<Vertex>> strongly_connected_components() {
            //DFS, save stack
            Stack<Vertex> stack = new Stack<>();
            List<Vertex> visits = new ArrayList<>();

            for(Vertex vertex : adjVertices.keySet()) {
                if(visits.contains(vertex)) {
                    continue;
                }
                dfs_for_SCC(vertex, stack, visits);
            }


            List<Set<Vertex>> result = new ArrayList<>();

            visits.clear();

            while (stack.size() > 0) {
                Vertex currentV = stack.pop();

                if(visits.contains(currentV)) {
                    continue;
                }

                HashSet<Vertex> sccSet = new HashSet<>();
                dfs_reversedGraph(currentV, visits, sccSet, reverseGraph());
                result.add(sccSet);
            }

            return result;
        }

        private void dfs_for_SCC(Vertex vertex, Stack<Vertex> stack, List<Vertex> visits) {
            visits.add(vertex);

            for(Vertex neighborV : adjVertices.get(vertex)) {
                if(visits.contains(neighborV)) {
                    continue;
                }
                dfs_for_SCC(neighborV, stack, visits);
            }

            stack.push(vertex);
        }

        private void dfs_reversedGraph(Vertex vertex, List<Vertex> visits, HashSet<Vertex> resultSet, HashMap<Vertex, List<Vertex>> reversedAdjList) {
            visits.add(vertex);
            resultSet.add(vertex);

            for (Vertex neighbor : reversedAdjList.get(vertex)) {
                if(visits.contains(neighbor)) {
                    continue;
                }

                visits.add(neighbor);

                dfs_reversedGraph(neighbor, visits, resultSet, reversedAdjList);

                resultSet.add(neighbor);

            }
        }

        private HashMap<Vertex, List<Vertex>> reverseGraph() {
            HashMap<Vertex, List<Vertex>> newAdjVertices = new HashMap<>();

            for (Vertex vertex : adjVertices.keySet()) {
                newAdjVertices.put(vertex, new ArrayList<>());
            }

            //Smart
            for (Map.Entry<Vertex, List<Vertex>> entry : adjVertices.entrySet()) {
                for(Vertex listVal : entry.getValue()) {
                    newAdjVertices.get(listVal).add(entry.getKey());
                }
            }

            return newAdjVertices;

            //for (Vertex vertexKey: adjVertices.keySet()) {
            //    int key = vertexKey.id;
            //    List<Vertex> value = adjVertices.get(vertexKey);
//
            //    for(Vertex id : value) {
            //        System.out.println(key + "->" + id.id);
            //    }
            //}
//
            //System.out.println("--------------");
//
            //for (Vertex vertexKey: newAdjVertices.keySet()) {
            //    int key = vertexKey.id;
            //    List<Vertex> value = newAdjVertices.get(vertexKey);
//
            //    for(Vertex id : value) {
            //        System.out.println(key + "->" + id.id);
            //    }
            //}
        }

    }

    private class Vertex {
        int id;
        String name;
        int age;

        public Vertex(int id, String name, int age) {
            this.name = name;
            this.age = age;
            this.id = id;
        }
    }
}
