package tamaGolem;

import java.util.HashMap;
import java.util.Map;

public class Grafo {
    private Map<Arco,Freccia> mappaDirezioni;
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

    public Map<Arco, Freccia> getMappaDirezioni() {
        return mappaDirezioni;
    }

    public void generaEquilibrio(){
        //popolamento e inserimento valori
        for(int i=0;i<numeroElementiAttivi;i++){
            int numeroDirezioni=0;
            int j;
            for(j=0; j<numeroElementiAttivi;j++){
                if(i!=j){
                    mappaDirezioni.put(new Arco(new Elemento(i),new Elemento(j)),new Freccia());
                    if(mappaDirezioni.get(new Arco(new Elemento(i),new Elemento(j))).getDirezione())numeroDirezioni++;
                }
            }
            if(numeroDirezioni>=this.numeroElementiAttivi-1)mappaDirezioni.put(new Arco(new Elemento(i),new Elemento(j)),new Freccia(false));
        }
    }

    public int[] suddividiDanni(int totaleDanniIn, int numeroDanniOut){
        int[] danniOut=new int[numeroDanniOut];
        //genera un numero casuale da 0 a il numero massimo - (numerodanniout) + 1
        for (int i=0; i<numeroDanniOut; i++);
        return null;
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
