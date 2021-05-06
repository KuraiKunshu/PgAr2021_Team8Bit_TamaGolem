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
        Elemento attaccante;
        Elemento subitore;
        //calcolo quanti archi ho
        int loop=calcoloNumeroArchi(numeroElementiAttivi);
        //genero degli archi in maniera casuale
        //potrei sostituire il while con 2 for e dato un valore casuale controllato decido la direzione (cioè lo genero se e solo se non mi interessa che direzione prende l'arco)
        while (mappaDirezioni.size()<loop){
            int indiceElementoAttaccante=rand.nextInt(numeroElementiAttivi);
            int indiceElementoSubitore=rand.nextInt(numeroElementiAttivi);
            if(indiceElementoAttaccante==indiceElementoSubitore)continue;
            attaccante = new Elemento(indiceElementoAttaccante);
            subitore = new Elemento(indiceElementoSubitore);
            if(!(mappaDirezioni.containsKey(new Arco(attaccante,subitore)))&&!(mappaDirezioni.containsKey(new Arco(subitore,attaccante)))){
                int numeroAttacchiAttaccante=0;
                int numeroAttacchiSubitore=0;
                int numeroSubitoSubitore=0;
                int numeroSubitoAttaccante=0;
                for(Map.Entry<Arco,Integer> entry : mappaDirezioni.entrySet()){
                    if(entry.getKey().getElementoDiArrivo().equals(attaccante))numeroSubitoAttaccante++;
                    if(entry.getKey().getElementoDiArrivo().equals(subitore))numeroSubitoSubitore++;
                    if(entry.getKey().getElementoDiPartenza().equals(subitore))numeroAttacchiSubitore++;
                    if(entry.getKey().getElementoDiPartenza().equals(attaccante))numeroAttacchiAttaccante++;
                }
                if((numeroAttacchiAttaccante<numeroElementiAttivi-2)&&(numeroSubitoSubitore<numeroElementiAttivi-2)) mappaDirezioni.put(new Arco(attaccante,subitore), null);
                else if((numeroAttacchiSubitore<numeroElementiAttivi-2)&&(numeroSubitoAttaccante<numeroElementiAttivi-2)) mappaDirezioni.put(new Arco(subitore,attaccante), null);
            }
        }
        Elemento elemento;
        for(int i=0;i<numeroElementiAttivi-1;i++){
            elemento = new Elemento(i);
            int totaleDanniAttacco=0;
            Deque<Elemento> elementiAttacanti = new ArrayDeque<>();
            Deque<Elemento> elementiSubitori = new ArrayDeque<>();
            for(Map.Entry<Arco,Integer> entry : mappaDirezioni.entrySet()){
                Arco attuale=entry.getKey();
                if(attuale.getElementoDiPartenza().equals(elemento)){
                    elementiSubitori.addFirst(attuale.getElementoDiArrivo());
                    if(entry.getValue()==null){
                        int value = rand.nextInt(vitaGolem)+1;
                        mappaDirezioni.put(entry.getKey(),value);
                        totaleDanniAttacco+=value;
                    }else totaleDanniAttacco+=entry.getValue();
                }
                if(attuale.getElementoDiArrivo().equals(elemento))
                    elementiAttacanti.addFirst(attuale.getElementoDiPartenza());
            }
            /*for(Elemento entry : elementiAttacanti){
                if(elementiAttacanti.size()>1){
                    int value = rand.nextInt(totaleDanniAttacco-(elementiAttacanti.size()-1))+1;
                    mappaDirezioni.put(new Arco(entry,elemento),value);
                    totaleDanniAttacco-=value;
                }else{
                    mappaDirezioni.put(new Arco(entry,elemento),totaleDanniAttacco);
                }
                elementiAttacanti.removeFirst();
            }*/
        }
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
