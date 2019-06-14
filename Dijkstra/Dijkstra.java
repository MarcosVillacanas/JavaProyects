package Dijkstra;

//Program thats shows how to code the djikstra algorithm, very important in networks

import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Edge> graph[] = new List[n];
        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int v1 = sc.nextInt()-1;
            int v2 = sc.nextInt()-1;
            int w = sc.nextInt();
            graph[v1].add(new Edge(v1, v2, w));
            graph[v2].add(new Edge(v2, v1, w));
        }
        int[] sol = new int [graph.length];
        sol = findShortestPath (0, graph);
    }

    public static class Edge {
        public int from;
        public int to;
        public int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static int[] findShortestPath(int v_start, List<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        int[] previous = new int[graph.length];
        Arrays.fill(previous, -1);
        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);

        Edge fake_edge = new Edge(-1, v_start, 0);
        Comparator<Edge> compareWeight = Comparator.comparing(edge -> edge.weight);
        PriorityQueue<Edge> pq = new PriorityQueue<>(compareWeight);
        pq.add(fake_edge);
        while (!pq.isEmpty()) {
            Edge e1 = pq.poll();
            if (!visited[e1.to]) {
                visited[e1.to] = true;
                distances[e1.to] = e1.weight;
                previous[e1.to] = e1.from;
                for (Edge e2 : graph[e1.to]) {
                    if (e1.weight + e2.weight < distances[e2.to]) {
                        pq.add(new Edge(e2.from, e2.to, e1.weight + e2.weight));
                        distances[e2.to] = e1.weight + e2.weight;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(previous));
        System.out.println(Arrays.toString(distances));
        return previous;
    }
    
}