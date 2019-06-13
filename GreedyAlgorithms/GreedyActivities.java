package GreedyActividades;

//This proyect was made in order to practict Greedy Algorithms, as entry we have a group of activities with two hours
//The start and the finish time of them, we also have a limit time to attend as many activities as possible
//This algorithm solves this problem, showing the maximum number of activities we could attend

import java.util.*;

public class GreedyActivities {

    public static class actividad implements Comparable<actividad>{
        int inicio; //Start time
        int fin; //Finish time

        public actividad(int inicio, int fin) {
            this.inicio = inicio;
            this.fin = fin;
        }
        
        @Override
        public int compareTo(actividad a){ //The way activities are sorted
            if(a.fin<this.fin)
                return 1;
            if(a.fin>this.fin)
                return -1;
            if(a.fin-a.inicio<this.fin-this.inicio)
                return 1;
            if(a.fin-a.inicio>this.fin-this.inicio)
                return -1;
            return 0;    
        }
    }
    public static Integer Mochila(ArrayList<actividad> diario){ //Greedy Algorithm
        ArrayList<actividad> mochila = new ArrayList();
        while (!diario.isEmpty()){
            if(mochila.isEmpty())
                mochila.add(diario.get(0));
            else if(diario.get(0).inicio >= mochila.get(mochila.size()-1).fin)
                mochila.add(diario.get(0));
            diario.remove(0);
        }  
        return mochila.size();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int numerodias = sc.nextInt();
        Integer [] calendario = new Integer [numerodias];
        for (int i=0; i<numerodias; i++){
            int numeroactividades = sc.nextInt();
            ArrayList<actividad> diario = new ArrayList(numeroactividades);
            for (int j=0; j<numeroactividades; j++){
                int inicio = sc.nextInt();
                int fin = sc.nextInt();
                actividad act = new actividad(inicio,fin);
                diario.add(act);
            }
            Collections.sort(diario);
            calendario[i] = Mochila(diario);
        }
        for (int k=0; k<calendario.length; k++){
            System.out.println(calendario[k]);
        }
    }   
}
    
