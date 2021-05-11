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
                if((numeroAttacchiAttaccante<numeroElementiAttivi-2)&&(numeroSubitoSubitore<numeroElementiAttivi-2)) mappaDirezioni.put(new Arco(attaccante,subitore), 0);
                else if((numeroAttacchiSubitore<numeroElementiAttivi-2)&&(numeroSubitoAttaccante<numeroElementiAttivi-2)) mappaDirezioni.put(new Arco(subitore,attaccante), 0);
            }
        }
        Elemento elemento;
        while(!isEquilibrato()){
            for(int i=0;i<numeroElementiAttivi;i++) {
                elemento = new Elemento(i);
                int totaleDanniAttacco = 0;
                int totaleDanniSubiti = 0;
                List<Map.Entry<Arco,Integer>> archiAttacanti = new ArrayList<>();
                List<Map.Entry<Arco,Integer>> archiSubitori = new ArrayList<>();
                for (Map.Entry<Arco, Integer> entry : mappaDirezioni.entrySet()) {
                    Arco attuale = entry.getKey();
                    if(entry.getValue()==0)entry.setValue(rand.nextInt(vitaGolem)+1);
                    if (attuale.getElementoDiPartenza().equals(elemento)) {
                        archiAttacanti.add(entry);
                        totaleDanniAttacco += entry.getValue();
                    }
                    if (attuale.getElementoDiArrivo().equals(elemento)) {
                        totaleDanniSubiti += entry.getValue();
                        archiSubitori.add(entry);
                    }
                }
                if(totaleDanniAttacco==totaleDanniSubiti)continue;
                boolean x = rand.nextBoolean();
                if(totaleDanniAttacco>totaleDanniSubiti){
                    if(x)assegnaValore(archiSubitori,vitaGolem,true, totaleDanniAttacco-totaleDanniSubiti);
                    else assegnaValore(archiAttacanti,vitaGolem,false, totaleDanniAttacco-totaleDanniSubiti);
                }else if(totaleDanniSubiti>totaleDanniAttacco){
                    if(x)assegnaValore(archiSubitori,vitaGolem,false,totaleDanniSubiti-totaleDanniAttacco);
                    else assegnaValore(archiAttacanti,vitaGolem,true,totaleDanniSubiti-totaleDanniAttacco);
                }
            }
        }
    }

    private void assegnaValore(List<Map.Entry<Arco,Integer>> lista, int vitaGolem,boolean moltiplicatore, int niterazioni){
        Random rand = new Random();
        int indice=0;
        for(int i=0; i<niterazioni;i++) {
            indice = rand.nextInt(lista.size());
            if (moltiplicatore) {
                if(lista.get(indice).getValue() < vitaGolem)
                    mappaDirezioni.put(lista.get(indice).getKey(), lista.get(indice).getValue() + 1);
            } else {
                if(lista.get(indice).getValue() > 0) {
                    mappaDirezioni.put(lista.get(indice).getKey(), lista.get(indice).getValue() - 1);
                }
            }
        }
    }

    public boolean isEquilibrato(){
        Elemento elemento;
        for(int i=0;i<numeroElementiAttivi;i++){
            elemento = new Elemento(i);
            if(!isEquilibrato(elemento)) return false;
        }
        return true;
    }

    public boolean isEquilibrato(Elemento elemento){
        int totaleDanniAttacco=0;
        int totaleDanniSubiti=0;
        for(Map.Entry<Arco,Integer> entry : mappaDirezioni.entrySet()) {
            Arco attuale = entry.getKey();
            if (attuale.getElementoDiPartenza().equals(elemento)) {
                if(entry.getValue()==null||entry.getValue()<1)return false;
                else totaleDanniAttacco += entry.getValue();
            }
            if (attuale.getElementoDiArrivo().equals(elemento)) {
                if(entry.getValue()==null||entry.getValue()<1)return false;
                else totaleDanniSubiti += entry.getValue();
            }
        }
        if((totaleDanniSubiti-totaleDanniAttacco)!=0)return false;
        return true;
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
