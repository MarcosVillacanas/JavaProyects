package Prim;

//Cheapest way to connect some cities with bridges should be found, and i did it using prim algorithm

import java.util.*;

public class PrimAlgorithm {

    static int islas=0; 
    static class Solucion {
        public long gastoMax;
        public int carreterasMinimas;
        public int numeroIslas;

        public Solucion(long gastoMax, int carreterasMinimas, int numeroIslas) {
            this.gastoMax = gastoMax;
            this.carreterasMinimas = carreterasMinimas;
            this.numeroIslas = numeroIslas;
        }    
    }
    static class Edge implements  Comparable<Edge> {
        public int from;
        public int to;
        public int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge e) {
            if (this.weight > e.weight)
                return -1;
            return 1;
        } 
    }
    private static Set<Edge> prim(int nodos, List<Edge>[] graph) {     
        Set<Edge> edges = new HashSet<>();
        boolean visit[] = new boolean[graph.length];//(n+1)
        int dmin[] = new int[graph.length];
        Arrays.fill(dmin, Integer.MIN_VALUE);
        //Arista ficticia para inicializar el recorrido (se debe eliminanr al final)
        Set<Integer> ciudades = new HashSet<>(nodos);
        for (int i=0; i<nodos; i++){
            ciudades.add(i);
        }
        while (!ciudades.isEmpty()){
            islas++;
            Iterator ite = ciudades.iterator();
            int nodo = (int) ite.next();
            Edge fake_edge = new Edge(-1, nodo, 0);
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.add(fake_edge);
            while (!pq.isEmpty()) {
                Edge edge = pq.poll();
                if (!visit[edge.to]) {
                    visit[edge.to] = true;
                    ciudades.remove(edge.to);
                    edges.add(edge);
                    for (Edge adj : graph[edge.to]) {
                        if (!visit[adj.to] && adj.weight > dmin[adj.to]) {
                            dmin[adj.to] = adj.weight;
                            pq.add(adj);
                        }
                    }
                }
            }
            edges.remove(fake_edge);
        }    
        return edges;
    }
    public static void main(String[] args) {    
       Scanner sc = new Scanner(System.in);
       ArrayList<Solucion> solucionario = new ArrayList<>();//Almacen de las soluciones
       int nodos = sc.nextInt();
       while (nodos!=0){
            islas = 0;
            int carreteras = sc.nextInt();
            List<Edge> G [] = new List[nodos];
            for (int i=0; i<G.length; i++){
                G[i] = new ArrayList<>();
            }
            for (int i = 0; i < carreteras; i++) {
                int origen = sc.nextInt();
                int destino = sc.nextInt();
                int coste = sc.nextInt();
                G[origen].add(new Edge(origen,destino,coste));  
                G[destino].add(new Edge(destino,origen,coste));               
            }
            long costeTotal=0;
            int carreterasMinimas = 0;
            
            Set<Edge> edgeSet = prim(nodos, G);
            for (Edge edge : edgeSet) {
                    costeTotal += edge.weight;
                    carreterasMinimas++;
            }    
            solucionario.add(new Solucion(costeTotal,carreterasMinimas,islas));
            nodos = sc.nextInt();     
       }
       
        for (int i = 0; i < solucionario.size(); i++) {
            System.out.println(solucionario.get(i).numeroIslas+" "+solucionario.get(i).carreterasMinimas+" "+solucionario.get(i).gastoMax);
            System.out.println("---");
        }
    }   
}
