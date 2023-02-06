import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.Queue;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class MyClass {
    public static class Edge {
        int value;
        int residualFlow; // remaining flow that can be added
        int capacity;
        int reverse;
        
        Edge(int value, int residualFlow, int capacity, int reverse) {
            this.value = value;
            this.residualFlow = residualFlow;
            this.capacity = capacity;
            this.reverse = reverse;
        }
    }
    public static class Graph {
        Map<Integer, List<Edge>> adj = new HashMap<>();
        Graph(int numVertexes) {
            for (int i = 0; i < numVertexes; i++) {
                adj.put(i, new ArrayList<>());
            }
        }
        void addEdge(int u, int v, int capacity) {
            int numU = adj.get(u).size();
            int numV = adj.get(v).size();
            
            adj.get(u).add(new Edge(v, capacity, capacity, numV));
            adj.get(v).add(new Edge(u, 0, 0, numU));
        }
        List<Edge> getEdges(int value) {
            return adj.get(value);
        }
        
    }
    
    public class Solution {
        static int[] levels;
        static int source;
        static int sink;
        public static int solution(int[] entrances, int[] exits, int[][] path) {
            /*
            add synthetic source and sink node
            build Residual graph off of original graph
            */
            source = path[0].length;
            sink = source + 1;
            int numNodes = path[0].length + 2;
            levels = new int[numNodes];
            Arrays.fill(levels, -1);
            Graph graph = new Graph(numNodes);
            for (int entrance : entrances) {
                graph.addEdge(source, entrance, Integer.MAX_VALUE);
            }
            for (int exit : exits) {
                graph.addEdge(exit, sink, Integer.MAX_VALUE);
            }
            for (int i = 0; i < path.length; i++) {
                for (int j = i + 1; j < path.length; j++) {
                    if (path[i][j] > 0) {
                        graph.addEdge(i, j, path[i][j]);   
                    }
                }
            }
            return solveWithDincs(graph);
        }
        private static int solveWithDincs(Graph graph) {
        
            /*
            total = 0
            while there is flow from level graph (w/ BFS):
                Run dfs on whole level graph while Keep track of path so far & limiting flow:
                    if path has flow:
                        total += flow
                        adjust residual graph
                if there are NO paths with flows: return total flow
            */
            int total = 0;
            while (hasFlow(graph)) {
                while (true) {
                    int flow = sendFlow(graph, Integer.MAX_VALUE, source);
                    if (flow == 0) {
                        break;
                    }
                    total += flow;
                }
            }
            return total;
        }
        
        private static int sendFlow(Graph graph, int flow, int start) {
            /*
            dfs
            2 = 8 (C) - 6 (Forward Flow) > 0
            C > F
            6 = 0 (C) - 6 (Forward Flow)
            
            total = 0
            for each neighbor edge:
                if level[edge.value] == level[start] + 1:
                    int next_flow = sendFlow(start, edge, flow);
                    int actualFlow = min(flow, nextFlow)
                    // update flows of forward and reverse edge
                    edge.flow += actualFlow
                    graph.getEdges(edge.v).get(edge.reverse).flow -= actualFlow
                    
                    total += actualFlow
            return total
            */
            int total = 0;
            if (start == sink) {
                return flow;
            }
            for (Edge edge : graph.getEdges(start)) {
                if (flow > 0 && levels[edge.value] == levels[start] + 1 && edge.flow < edge.capacity) {
                    int nextFlow = sendFlow(graph, Math.min(flow, edge.capacity - edge.flow), edge.value);
                    int actualFlow = Math.min(flow, nextFlow);
                    edge.flow += actualFlow;
                    graph.getEdges(edge.value).get(edge.reverse).flow -= actualFlow;
                    
                    total += actualFlow;
                    
                    // get remaining flow to send
                    flow -= actualFlow;
                }
            }
            return total;
            
        }
        
        private static boolean hasFlow(Graph graph) {
            /*
            Run bfs & update Levels
            */
            Arrays.fill(levels, -1);
            
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(source);
            levels[source] = 0;
            while (!queue.isEmpty()) {
                Integer value = queue.poll();
                for (Edge edge : graph.getEdges(value)) {
                    if (levels[edge.value] == -1 && edge.flow < edge.capacity) {
                        levels[edge.value] = levels[value] + 1;
                        queue.add(edge.value);
                    }
                }
            }
            return levels[sink] > -1;
            
        }
    }
    public static void main(String args[]) {
        int[] entrances = {0};
        int[] exits = {3};
        int[][] path = {{0, 7, 0, 0}, {0, 0, 6, 0}, {0, 0, 0, 8}, {9, 0, 0, 0}};
        System.out.println(Solution.solution(entrances, exits, path));
    }
}