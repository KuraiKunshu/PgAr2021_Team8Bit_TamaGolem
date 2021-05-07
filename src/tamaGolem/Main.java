package tamaGolem;

import java.util.HashMap;
import java.util.Map;

public class Main {
	
	static int NumeroElementi=7;
	
    public static void main(String[] args) {
        Map<Arco,Integer> mappa= new HashMap<>();
/*
        //Stampa matrice elementi
        Grafo g = new Grafo();
        int n = 4;
        int[][] m= g.generaEquilibrio(n);
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println("");
        }
        int contatore=0;
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                if (m[i][j] != 0) {
                    contatore++;
                    System.out.println(contatore + ". " + new Elemento(i).getNome() + " fa danno " + m[i][j] + " a " + new Elemento(j).getNome());
                }
            }
        }
        System.out.println(g.getMappaDirezioni().size());
        System.out.println(g.getMappaDirezioni().get(new Arco(new Elemento(0),new Elemento(1))));
        System.out.println(g.getMappaDirezioni().get(new Arco(new Elemento(1),new Elemento(0))));

        //Test costanti definite rispetto al valore di n numero di elementi
        /*int n = 6;
        int p = TamaGolem.getNumeroCaricatore(n);
        int g = Giocatore.getNumeroGolem(n, p);
        int s = Scontro.getNumeroTotalePietreDisponibili(n, p, g);
        int e = s/n;
        System.out.println("Numero elementi: "+n);
        System.out.println("Numero caricatore: "+p);
        System.out.println("Numero golem per giocatore: "+g);
        System.out.println("Numero totale pietre disponibili: "+s);
        System.out.println("Numero pietre per elemento: "+e);*/


        /*Arco a1 = new Arco(new Elemento("aq"),new Elemento("bq"));
        Arco a2 = new Arco(new Elemento("aq"),new Elemento("bq"));
        mappa.put(a1,2);
        mappa.put(a2,4);
        System.out.println(a1.equals(a2));
        System.out.println(mappa.get(new Arco(new Elemento("aq"),new Elemento("bq"))));
        System.out.println(mappa.size());*/

        /*Arco a1 = new Arco(new Elemento(0),new Elemento(1));
        Arco a2 = new Arco(new Elemento(1),new Elemento(0));
        mappa.put(a1,0);
        mappa.put(a2,4);
        System.out.println(a1.equals(a2));
        System.out.println(mappa.get(new Arco(new Elemento(0),new Elemento(1))));
        System.out.println(mappa.size());*/
    }
}
