package mergesort;

import java.util.*;

//MergeSort example

public class DandC_MergeSort {

    private static int[] mS (int[] v){
        if (v.length==1)
            return v;
        int[] a = Arrays.copyOfRange(v, 0, v.length/2);
        int[] b = Arrays.copyOfRange(v, v.length/2, v.length);
        a = mS (a);
        b = mS (b);
        return mS (a, b);
    }
    private static int[] mS (int[] a, int[] b){
        int[] c = new int[a.length + b.length];
        int i=0, j=0, k=0;
        while (i<a.length && j<b.length){
            if (a[i] <= b[j]){
                c[k] = a[i];
                k++;
                i++;
            }
            else{
                c[k] = b[j];
                k++;
                j++;
            }
        }
        while (i<a.length){
            c[k] = a[i];
            k++;
            i++;
        }
        while (j<b.length){
            c[k] = b[j];
            j++;
            k++;
        }
        return c;
    }
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int numeroelementos = sc.nextInt();
       int [] vector = new int[numeroelementos];
       for (int i=0; i<numeroelementos; i++){
           vector[i] = sc.nextInt();
       }
       vector = mS(vector);
       for (int i=0; i<numeroelementos; i++){
           System.out.print(vector[i] + " ");
       }
    }
    
}
