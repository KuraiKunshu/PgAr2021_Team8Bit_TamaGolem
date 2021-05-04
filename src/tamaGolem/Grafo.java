package tamaGolem;

import java.util.*;

public class Grafo {
    private Map<Arco,Integer> mappaDirezioni;
    private int numeroElementiAttivi;
    //numero di elementi che possono interagire insieme
    /**
     * numero di elementi che possono interagire insieme
     */
    private final int NUMERO_COMBINAZIONE=2;
    public Grafo(int numeroElementiAttivi) {
        this.mappaDirezioni = new HashMap<>();
        this.numeroElementiAttivi=numeroElementiAttivi;
    }

    public Map<Arco, Integer> getMappaDirezioni() {
        return mappaDirezioni;
    }

    public void generaEquilibrio(int vitaGolem){
        Random rand = new Random();
        //calcolo quanti archi ho
        int loop=calcoloNumeroArchi(numeroElementiAttivi);
        //genero degli archi casualmente (dovrei calcolare quando ci sono n-1 archi di uguale direzione) e invertire i e j
        Deque<Arco> archi=new ArrayDeque<>();
        Elemento e1;
        Elemento e2;
        while (mappaDirezioni.size()<loop){
            int i = rand.nextInt(numeroElementiAttivi);
            int j = rand.nextInt(numeroElementiAttivi);
            if(i==j)continue;
            e1=new Elemento(i);
            e2=new Elemento(j);
            if(!(mappaDirezioni.containsKey(new Arco(e1,e2))) && !(mappaDirezioni.containsKey(new Arco(e2,e1)))){
                int contaElementoPartenza=0;
                int contaElementoArrivo=0;
                for(int x=0;x<numeroElementiAttivi;x++){
                    if(x!=i&&mappaDirezioni.containsKey(new Arco(e1, new Elemento(x))))contaElementoPartenza++;
                    if(x!=i&&mappaDirezioni.containsKey(new Arco(new Elemento(x), e1)))contaElementoArrivo++;
                }
                int value= rand.nextInt(vitaGolem)+1;
                if(contaElementoPartenza==numeroElementiAttivi-2) mappaDirezioni.put(new Arco(e2,e1),null);
                if(contaElementoArrivo==numeroElementiAttivi-2) mappaDirezioni.put(new Arco(e1,e2), value);
                mappaDirezioni.put(new Arco(e1,e2), value);
            }
        }
        //faccio un ciclo, per ogni elemento, che calcola il totale dei danni che un elemento infligge e a quanti elemmenti lo infligge
        //poi, per esclusione, suddivido il valore del totale in parti pari al numero di "freccie" che dovrebbe subire e cambio questi valori in modo da
        //avere "casualità" anche tra i valori che subiscono
    }

    /**
     * ritorna il fattoriale di n
     * @param n
     * @return
     */
    public int fattoriale(int n){
        for(int i=n-1;i>0;i--){
            n*=i;
        }
        return n;
    }

    /**
     * data la formula della combinazione senza ripetizione, ritorna il risultato.
     * C(n,k) = n!/(k!*(n-k)!)
     * k è data dalla costante NUMERO_COMBINAZIONE
     * @param n
     * @return
     */
    public int calcoloNumeroArchi(int n){
        return (fattoriale(n))/(fattoriale(NUMERO_COMBINAZIONE)*fattoriale(n-NUMERO_COMBINAZIONE));
    }
}
