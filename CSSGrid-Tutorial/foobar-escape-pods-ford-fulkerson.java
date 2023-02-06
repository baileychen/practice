import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.Queue;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Collections;

public class MyClass {
    
    public class Solution {
        static int source;
        static int sink;
        public static int solution(int[] entrances, int[] exits, int[][] path) {
            /*
            add synthetic source and sink node
            */
            source = path[0].length;
            sink = source + 1;
            int numNodes = path[0].length + 2;
            int[][] residualGraph = new int[numNodes][numNodes];
            for (int entrance : entrances) {
                residualGraph[source][entrance] = Integer.MAX_VALUE;
            }
            for (int exit : exits) {
                residualGraph[exit][sink] = Integer.MAX_VALUE;
            }
            for (int i = 0; i < path.length; i++) {
                for (int j = 0; j < path.length; j++) {
                    residualGraph[i][j] = path[i][j];  
                }
            }
            return solve(residualGraph);
        }
        private static int solve(int[][] residualGraph) {
            int total = 0;
            List<Integer> path;
            while (!(path = hasFlow(residualGraph)).isEmpty()) {
                int flow = Integer.MAX_VALUE;
                for (int i = 0; i < path.size() - 1; i++) {
                    System.out.println(String.format("residualGraph value, %d", residualGraph[path.get(i)][path.get(i+1)]));
                    flow = Math.min(flow, residualGraph[path.get(i)][path.get(i+1)]);
                }
                // update residual graph
                for (int i = 0; i < path.size() - 1; i++) {
                    residualGraph[path.get(i)][path.get(i+1)] -= flow;
                    residualGraph[path.get(i+1)][path.get(i)] += flow;
                }
                System.out.println(String.format("flow %d", flow));
                total += flow;
            }
            return total;
        }
        private static List<Integer> hasFlow(int[][] residualGraph) {
            // bfs
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(source);
            int[] parent = new int[residualGraph.length];
            Arrays.fill(parent, -1);
            parent[source] = 0;

            while (!queue.isEmpty()) {
                Integer value = queue.poll();
                for (int neighbor = 0; neighbor < residualGraph[value].length; neighbor++) {
                    if (parent[neighbor] == -1 && residualGraph[value][neighbor] > 0) {
                        parent[neighbor] = value;
                        queue.add(neighbor);
                    }
                }
            }

            if (parent[sink] == -1) {
                System.out.println("No path found");
                return new ArrayList<>();
            }
            List<Integer> path = new ArrayList<>();
            int i = sink;
            // path.add(sink);
            while (parent[i] != source) {
                path.add(parent[i]);
                i = parent[i];
            }
            // path.add(source);
            Collections.reverse(path);
            System.out.println(String.format("path: %s", path));
            return path;
        }
    } 
    public static void main(String args[]) {
        int[] entrances = {0, 1};
        int[] exits = {4, 5};
        int[][] path = {{0, 0, 4, 6, 0, 0}, {0, 0, 5, 2, 0, 0}, {0, 0, 0, 0, 4, 4}, {0, 0, 0, 0, 6, 6}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}};
        System.out.println(Solution.solution(entrances, exits, path));
    }
}