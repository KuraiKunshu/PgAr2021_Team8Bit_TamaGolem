package tamaGolem;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Arco,Integer> mappa= new HashMap<>();

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

        /*
        Arco a1 = new Arco(new Elemento("aq"),new Elemento("bq"));
        Arco a2 = new Arco(new Elemento("aq"),new Elemento("bq"));
        mappa.put(a1,2);
        mappa.put(a2,4);
        System.out.println(a1.equals(a2));

        System.out.println(mappa.get(new Arco(new Elemento("aq"),new Elemento("bq"))));
        System.out.println(mappa.size());*/
    }
}
