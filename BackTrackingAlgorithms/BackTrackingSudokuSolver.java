package casisudokuiterativo;

//Sudoku solver

import java.util.Scanner;

public class BackTrackingSudokuSolver {
    static int N = 0;
    static int[][] def = new int[9][9];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[9][9];
        for (int f=0; f<=8; f++) {
            for (int c=0; c<=8; c++) {
                board[f][c]=sc.nextInt();
            }
        }
        sudoku_aux(0,0,board);
        if (N==0) 
            System.out.println("imposible");
        else if(N==1) 
            mostrar(def);
        else 
            System.out.println("casi sudoku");
    }
    private static void sudoku_aux(int i, int j, int[][] board) {
        if (board[i][j]==0) {
            int k=1;
            while (k<=9 && N<2) {
                if (isFeasible(board,i,j,k)) {
                    board[i][j]=k;
                    if ((i==8) && (j==8)) {
                        N++;
                        if (N==1)
                            copiarMatrices(board);
                    }
                    if ((i<8) && (j==8)) {
                        sudoku_aux(i+1, 0, board);
                    }
                    if ((i<=8) && (j<8)) {
                        sudoku_aux(i, j+1, board);
                    }
                }
                board[i][j]=0;
                k++;
            }
        } else {
            if ((i==8) && (j==8)) {
                N++;
                if (N==1)
                    copiarMatrices(board);
            }
            if ((i<8) && (j==8)) {
                sudoku_aux(i+1, 0, board);
            }
            if ((i<=8) && (j<8)) {
                sudoku_aux(i, j+1, board);
            }
        }
    }
    public static boolean isFeasible (int[][] tablero, int fila, int col, int num){
        //Checks that one number cannot be two twice in a row or a column or a 3x3 square
        for (int i=0; i<tablero.length; i++){
            if (tablero[i][col]==num || tablero[fila][i] == num)
                return false;       
        }       
        int auxfila = fila - fila%3;
        int auxcol = col - col%3;
        for (int j=auxfila; j<auxfila+3; j++){
            for (int k=auxcol; k<auxcol+3; k++){
                if (tablero[j][k]==num)
                    return false;
            }        
        }   
        return true;    
    }
    private static void mostrar(int[][] board) {
        System.out.println(" ");
        for (int i=0; i<=8; i++) {
            for (int j=0; j<=8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
    public static void copiarMatrices (int[][] tablero){
        for (int i=0; i<tablero.length; i++){
            for (int j=0; j<tablero.length; j++){
                def[i][j] = tablero [i][j];                
            }
        }
    } 
}
