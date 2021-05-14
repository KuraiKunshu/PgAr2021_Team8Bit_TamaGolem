package tamaGolem;

import java.util.LinkedList;

public class Scontro {
	
	
    private LinkedList<Elemento> pietreDisponibili;
    private Giocatore g1;
    private Giocatore g2;
    private Grafo mondo;

    public Scontro (){
        this.pietreDisponibili = new LinkedList<Elemento>();
        this.mondo = new Grafo();
    }

    public Giocatore getG1() {
        return g1;
    }

    public void setG1(Giocatore g1) {
        this.g1 = g1;
    }

    public Giocatore getG2() {
        return g2;
    }

    public void setG2(Giocatore g2) {
        this.g2 = g2;
    }

    public Grafo getMondo() {
        return mondo;
    }

    public void setMondo(Grafo mondo) {
        this.mondo = mondo;
    }

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
     * @param g numero di TamaGolem per giocatore
     * @param p numero di pietre per caricatore
     * @return numero di pietre presenti a inizio partita in pietreDisponibili
     */
    public static int getNumeroTotalePietreDisponibili(int n, int g, int p){
        return (int)Math.ceil((2.0 * g * p) / n) * n;
    }

    public void riempiPietreDisponibili(int n){
        int pietrePerCaricatore = TamaGolem.getNumeroCaricatore(n);
        int pietreTotali = getNumeroTotalePietreDisponibili(n, Giocatore.getNumeroGolem(n, pietrePerCaricatore), pietrePerCaricatore);
    	for(int i=0; i<n;i++) {
    		for(int j=0; j<pietreTotali/n; j++) {
    			Elemento elemento = new Elemento(i);
    			pietreDisponibili.add(elemento);
    		}
    	}
    }
    
    public void evocazioneIniziale(int n) {
    	mondo.generaEquilibrio(n);
    	g1.scegliPietre(pietreDisponibili);
        System.out.println("\nAdesso tocca a " + g2.getNome());
    	g2.scegliPietre(pietreDisponibili,g1);
        //controlloCaricatore(n, g1, g2);
    }

    public void attacco(){
        Elemento pietraGolem1 = g1.getGolems().getFirst().ruotaCaricatore();
        Elemento pietraGolem2 = g2.getGolems().getFirst().ruotaCaricatore();
        int danno = mondo.getDanni(pietraGolem1, pietraGolem2);
        if(danno<0) {
            int vita= g2.getGolems().getFirst().getHp()+danno;
            g2.getGolems().getFirst().setHp(vita);
            if(g2.getGolems().getFirst().isAlive()) {
              System.out.println(String.format(Interazione.MSG_DANNI, g1.getNome(), (-1)*danno, g2.getNome()));	
            }
        }
        if(danno>0) {
            int vita=g1.getGolems().getFirst().getHp()-danno;
            g1.getGolems().getFirst().setHp(vita);
            if(g1.getGolems().getFirst().isAlive()) {
                System.out.println(String.format(Interazione.MSG_DANNI, g2.getNome(), danno, g1.getNome()));	
              }
        }
        Interazione.aCapo();
    }

    public String vincitore() {
    	if(g1.getGolems().isEmpty()) {
    		return g2.getNome();
    	}
    	if(g2.getGolems().isEmpty()) {
    		return g1.getNome();
    	}
    return "";
    }
}
