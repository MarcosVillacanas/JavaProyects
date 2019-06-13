package GreedyFinalStory;

//This proyect simulates a game in which we have a character with attack points and a bunch of enemies to beat
//Every round a enemy is still alive, our character suffers an attack too, the purpose is to calculate the minimum
//points we would lose beating the enemies in the best order possible

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class GreedyFinalStory {   
    
    public static class enemigo implements Comparable<enemigo> {
        public int vida; //enemy life
        public int ataque; //enemy attack
        public int ataquehumano; //atacks suffered by enemy
        
        public enemigo(int vida, int ataque, int ataquehumano) {
            this.vida = vida;
            this.ataque = ataque;
            this.ataquehumano = ataquehumano;    
        }    
        
        public double calculosCompare (int ataqueenemigo, int vidaenemigo ) { //aux func 
            int turnos2 = (int) Math.ceil( (float) vidaenemigo / this.ataquehumano);
            return ((double) ataqueenemigo / turnos2);      
        }
        
        @Override
        public int compareTo(enemigo x) { //the way enemies are sorted
            if (this.calculosCompare(this.ataque, this.vida) < this.calculosCompare(x.ataque, x.vida)) {
                return 1;
            }
            if (this.calculosCompare(this.ataque, this.vida) > this.calculosCompare(x.ataque, x.vida)) {
                return -1;
            }
            return 0;
        }
    }
    public static class objetocombate  { //Things each fight needs
        public int ataquehumano;
        public int numenemigos;
        public ArrayList<enemigo> objetos = new ArrayList<>(numenemigos);
        
        public objetocombate(int ataquehumano, int numenemigos, ArrayList<Integer> ataques, ArrayList<Integer> vidas ) {
            this.ataquehumano = ataquehumano;
            this.numenemigos = numenemigos;
            int longitud = ataques.size();
            for (int k=0; k<longitud; k++){
                enemigo x = new enemigo (vidas.get(k), ataques.get(k), ataquehumano);
                this.objetos.add(x);
            }
        }
    }      
    public static long start(objetocombate c){ //Greedy Algorithm
        long golpesrecibidos = 0;
        while (!(c.objetos.isEmpty())) {
            for (enemigo enemy : c.objetos) {
                golpesrecibidos += enemy.ataque;
            } 
            c.objetos.get(0).vida -= c.ataquehumano;
            if (c.objetos.get(0).vida <= 0)
                c.objetos.remove(0);
        }   
        return golpesrecibidos;
    }  
    
    public static void main(String[] args) {       
        Scanner sc = new Scanner(System.in);
        int numcombates = sc.nextInt();       
        ArrayList<Long> resultado = new ArrayList<>();
        
        for (int k = 0; k < numcombates; k++) {            
            int ataquejugador = sc.nextInt();
            int numeroenemigos = sc.nextInt();           
            ArrayList <Integer> ataques = new ArrayList <>(numeroenemigos);
            ArrayList <Integer> vidas = new ArrayList <>(numeroenemigos);
            
            for (int k2 = 0; k2 < numeroenemigos; k2++) {               
                ataques.add(k2, sc.nextInt());
            }
            
            for (int k2 = 0; k2 < numeroenemigos; k2++) {               
                vidas.add(k2, sc.nextInt());
            }     
            
            objetocombate combate = new objetocombate (ataquejugador, numeroenemigos, ataques, vidas);
            Collections.sort(combate.objetos);
            resultado.add(start(combate));
        }
        Iterator ite = resultado.iterator();
        while (ite.hasNext()) {
            System.out.println((long) ite.next());
        }   
    }      
}
