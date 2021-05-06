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

    public void riempiPietreDisponibili(int n){
    	for(int i=0; i<Elemento.getNumeroElementi();i++) {
    		for(int j=0; j<n/Elemento.getNumeroElementi(); j++) {
    			Elemento elemento = new Elemento(i);
    			pietreDisponibili.add(elemento);
    		}
    	}
    }

   

    public void attacco(TamaGolem t1, TamaGolem t2){
    	Elemento pietraGolem1 = t1.caricatore.getFirst();
    	Elemento pietraGolem2 = t2.caricatore.getFirst();
    	Grafo g = new Grafo();
    	int danno = g.getDanni(pietraGolem1, pietraGolem2);
    	if(danno<0) {
    		int vita= t1.getHp()+danno;
    		t1.setHp(vita);
    	}
    	if(danno>0) {
    		int vita= t2.getHp()-danno;
    		t2.setHp(vita);
    	}
    	if(danno==0) {
    		
    	}
    	
    }
}
