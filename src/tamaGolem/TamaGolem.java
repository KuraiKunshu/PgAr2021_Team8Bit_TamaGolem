package tamaGolem;

import java.util.ArrayDeque;
import java.util.Deque;

public class TamaGolem {
    private int hp;
    private Deque<Elemento> caricatore;

    public TamaGolem(int hp){
        this.setHp(hp);
        this.caricatore = new ArrayDeque<Elemento>();
    }

    /**
     * ritorna il caricatore del tamagolem
     * @return
     */
    public Deque<Elemento> getCaricatore() {
		return caricatore;
	}

    /**
     * imposta un caricatore pre-inizializzato
     * @param caricatore Deque di elementi pre-inizializzata
     */
	public void setCaricatore(Deque<Elemento> caricatore) {
		this.caricatore = caricatore;
	}

	/**
     * Ritorna gli hp del TamaGolem
     * @return
     */
    public int getHp() {
        return hp;
    }

    /**
     * Imposta gli hp del TamaGolem
     * @param hp punti vita del TamaGolem
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Dato il numero di elementi, ritorna il numero di pietre che il caricatore del TamaGolem può contenere,
     * secondo la formula:
     * ⎡(n + 1) / 3⎤ + 1
      * @param n numero di elementi
     * @return
     */
    public static int getNumeroCaricatore(int n){
        return (int)Math.ceil((n+1.0)/3)+1;
    }

    /**
     * dato un elemento aggiunge nel caricatore quest'ultimo in prima posizione
     * @param pietra
     */
    public void setCaricatore(Elemento pietra) {
    	this.caricatore.addFirst(pietra);
    }

    /**
     * Se il TamaGolem ha almeno 1 hp ritorna true, altrimenti ritorna false
     * @return
     */
    public boolean isAlive(){
        if (this.getHp() <= 0)
            return false;
        else return true;
    }

    /**
     * sposta il primo elemento del caricatore e lo mette in coda
     * @return ritorna l'elemento spostato
     */
    public Elemento ruotaCaricatore(){
        Elemento e = caricatore.removeLast();
        caricatore.addFirst(e);
        return e;
    }
}
