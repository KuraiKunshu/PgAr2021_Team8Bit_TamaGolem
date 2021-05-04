package tamaGolem;

import java.util.ArrayList;
import java.util.LinkedList;

public class Scontro {
    private LinkedList<Elemento> pietreDisponibili;
    private Giocatore g1;
    private Giocatore g2;

    public LinkedList<Elemento> getPietreDisponibili() {
        return pietreDisponibili;
    }

    public void setPietreDisponibili(LinkedList<Elemento> pietreDisponibili) {
        this.pietreDisponibili = pietreDisponibili;
    }

    /**
     * Ritorna quante pietre ci sono a inizio partita in pietreDisponibili dato il numero di elementi,
     * secondo la formula:
     * ⎡(2 * g * p) / n⎤ * n
     * @param n numero di elementi
     */
    public static int getNumeroTotalePietreDisponibili(int n, int g, int p){
        return (int)Math.ceil((2.0 * g * p) / n) * n;
    }

    public void riempiPietreDisponibili(){

    }

    public void evocazione(){

    }

    public int attacco(TamaGolem t1, TamaGolem t2){
        return 0;
    }
}
