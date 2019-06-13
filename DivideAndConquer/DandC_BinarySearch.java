package darksoulssesion3;

import java.util.Scanner;

//This proyect uses a DarkSouls game feature to practice BinarySearch
//It has to be found a element in a sorted array, perfect issue to be solved 
//with tis algorithm

public class DandC_BinarySearch {
    
    public static class objetoenemigo{

        public int experiencia;
        public long experienciaacumulada;

        public objetoenemigo(int experiencia, long experienciaacumulada) {
            this.experiencia = experiencia;
            this.experienciaacumulada = experienciaacumulada;
        }
    } 
    
    public static int BinarySearch (objetoenemigo v [], int q){
        return BinarySearch (v,0,v.length-1,q);
    }
    
    public static int BinarySearch (objetoenemigo v [], int i, int j, int q){
        int k=(i+j)/2;
        if (i>j)
            return k;   
        if ((v[k].experiencia==q))
            return k;
        else if (v[k].experiencia<q)
            return BinarySearch(v,k+1,j,q);
        else
            return BinarySearch(v,i,k-1,q);           
    }
    
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int experiencia;
       int numeroenemigos = sc.nextInt();
       objetoenemigo [] vector = new objetoenemigo[numeroenemigos];      
       for (int i=0; i<numeroenemigos; i++){
           experiencia = sc.nextInt();
           if (i==0)
               vector[i]= new objetoenemigo(experiencia,experiencia);
           else
               vector[i]=new objetoenemigo (experiencia,  vector[i-1].experienciaacumulada+experiencia);
       }  
       int numerooleadas = sc.nextInt();    
       long [] solucion = new long[numerooleadas*2];
       int x=0;
       for (int i=0; i<numerooleadas; i++){
           int numero = sc.nextInt();
           int indice = BinarySearch(vector,numero);
           if (numero<vector[0].experiencia){
               solucion[x]=0;
               x++;
               solucion[x]=0;
           }
           else{
               solucion[x]=indice+1;
               x++;
               solucion[x]=vector[indice].experienciaacumulada;
           }
           x++;
       }
       for (int i=0; i<numerooleadas*2; i++){
           System.out.println(solucion[i]+" "+solucion[i+1]);
           i++;
       }
    }
    
}
