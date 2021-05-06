package tamaGolem;



import java.util.Deque;
import java.util.LinkedList;

public class Giocatore {
    private String nome;
    private Deque<TamaGolem> golems;

    public Giocatore(String nome, Deque<TamaGolem> golems) {
        this.nome = nome;
        this.golems = golems;
    }

    /**
     * ritorna il nome del giocatore
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * imposta il nome del giocatore
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * ritorna la lista di golem
     * @return
     */
    public Deque<TamaGolem> getGolems() {
        return golems;
    }

    /**
     * imposta l'intera lista dei golem
     * @param golems
     */
    public void setGolems(Deque<TamaGolem> golems) {
        this.golems = golems;
    }

    /**
     * Ritorna il numero di TamaGolem che il giocatore possiede a inizio partita, dato il numero di elementi e il
     * numero di pietre che il caricatore del TamaGolem può contenere, secondo la formula:
     * ⎡(n - 1)(n - 2) / (2 * p)⎤
     * @param n numero di elementi
     * @param p numero di pietre che il caricatore del TamaGolem può contenere
     * @return
     */
    public static int getNumeroGolem(int n, int p){
        return (int)Math.ceil((n-1.0)*(n-2)/(2*p));
    }

    public void rimuoviGolem(){
        this.getGolems().remove();
    }

    public void caricaPietreScelte(LinkedList<Elemento> pietreScelte) {
    	this.golems.getFirst().setCaricatore(pietreScelte);
    }
    
   
}
