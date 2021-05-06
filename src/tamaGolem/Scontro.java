package tamaGolem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Scontro {
    private LinkedList<Elemento> pietreDisponibili;
    private Giocatore g1;
    private Giocatore g2;
    private Grafo mondo;

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

    public void riempiPietreDisponibili(int n){
    	for(int i=0; i<Elemento.getNumeroElementi();i++) {
    		for(int j=0; j<n/Elemento.getNumeroElementi(); j++) {
    			Elemento elemento = new Elemento(i);
    			pietreDisponibili.add(elemento);
    		}
    	}
    }
    
    public void evocazioneIniziale(int n) {
    	mondo.generaEquilibrio(n);
    	g1.scegliPietre(pietreDisponibili);
    	g2.scegliPietre(pietreDisponibili);
    }
    
    

   

    public void attacco(){
    	
    	Elemento pietraGolem1 = g1.getGolems().getFirst().getCaricatore().getFirst();
    	Elemento pietraGolem2 = g2.getGolems().getFirst().getCaricatore().getFirst();
    	int danno = mondo.getDanni(pietraGolem1, pietraGolem2);
    	if(danno<0) {
    		int vita= g1.getGolems().getFirst().getHp()+danno;
    		g1.getGolems().getFirst().setHp(vita);
    	}
    	if(danno>0) {
    		int vita=g2.getGolems().getFirst().getHp()-danno;
    		g2.getGolems().getFirst().setHp(vita);
    	}
    	
    }
}
