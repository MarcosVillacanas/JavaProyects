package zpracticandocasilab;

//Program made to use BackTrackingAlgorithm, finds the best way to escape
//from a laberinth, showing how it has been solved

import java.util.Scanner;

public class BackTrackingLaberinthScape {
    
    static int solCorta = Integer.MAX_VALUE;
    static int[][] def;
    
    private static boolean isFeasible (int[][] lab, int i, int j){
        if (i>=0 && j>=0 && i<=lab.length-1 && j<=lab.length-1)
            return lab[i][j] == 0;
        return false;
    }
    private static void copiarMatrices (int[][] lab){
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab.length; j++) {
                def[i][j] = lab[i][j];
            }
        }
    }
    private static void lab (int[][] lab, int i, int j, int k){
        lab[i][j] = k;
        if (i==lab.length-1 && j==lab.length-1){
            if (k<solCorta){
                solCorta = k;
                copiarMatrices (lab);
            }    
        }
        else{
            if (isFeasible(lab,i+1,j))
                lab (lab, i+1, j, k+1);
            if (isFeasible(lab,i,j+1))
                lab (lab, i, j+1, k+1);
            if (isFeasible(lab,i-1,j))
                lab (lab, i-1, j, k+1);
            if (isFeasible(lab,i,j-1))
                lab (lab, i, j-1, k+1);            
        }
        lab[i][j] = 0;
    }
    private static void print (){
        for (int i = 0; i < def.length; i++) {
            for (int j = 0; j < def.length; j++) {
                System.out.print(def[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int dim = sc.nextInt();
        int [][] lab = new int [dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                lab[i][j] = sc.nextInt();
            }      
        }
        def = new int [dim][dim];
        lab (lab, 0, 0, 1);
        System.out.println(solCorta);       
        print();
    }
}
