package tamaGolem;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;

public class Giocatore {
    private String nome;
    private ArrayList<TamaGolem> golems;

    public Giocatore(String nome, ArrayList<TamaGolem> golems) {
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
    public ArrayList<TamaGolem> getGolems() {
        return golems;
    }

    /**
     * imposta l'intera lista dei golem
     * @param golems
     */
    public void setGolems(ArrayList<TamaGolem> golems) {
        this.golems = golems;
    }
}
