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

    /**
     * ritorna il giocatore 1
     * @return
     */
    public Giocatore getG1() {
        return g1;
    }

    /**
     * imposta le pietre disponibili dato un Giocatore già inizializzato
     * @param g1 Giocatore pre-inizializzato
     */
    public void setG1(Giocatore g1) {
        this.g1 = g1;
    }

    /**
     * ritorna il giocatore 2
     * @return
     */
    public Giocatore getG2() {
        return g2;
    }

    /**
     * imposta le pietre disponibili dato un Giocatore già inizializzato
     * @param g2 Giocatore pre-inizializzato
     */
    public void setG2(Giocatore g2) {
        this.g2 = g2;
    }

    /**
     * ritorna il grafo dello scontro, contenente l'equilibrio
     * @return
     */
    public Grafo getMondo() {
        return mondo;
    }

    /**
     * imposta le pietre disponibili dato un grafo già inizializzato
     * @param mondo grafo pre-inizializzato
     */
    public void setMondo(Grafo mondo) {
        this.mondo = mondo;
    }

    /**
     * ritorna la lista delle pietre disponibili
     * @return
     */
    public LinkedList<Elemento> getPietreDisponibili() {
        return pietreDisponibili;
    }

    /**
     * imposta le pietre disponibili dato un array già inizializzato
     * @param pietreDisponibili array pre-inizializzato
     */
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

    /**
     * dato il numero degli elementi, popola la linkedList in base alle formule prestabilite
     */
    public void riempiPietreDisponibili(){
        int numeroElementi = Main.getNumeroElementi();
        int capienzaCaricatore = TamaGolem.getNumeroCaricatore(numeroElementi);
        int pietreTotali = Scontro.getNumeroTotalePietreDisponibili(numeroElementi, Giocatore.getNumeroGolem(numeroElementi, capienzaCaricatore), capienzaCaricatore);
    	for(int i=0; i < numeroElementi; i++) {
    		for(int j=0; j < pietreTotali/numeroElementi; j++) {
    			Elemento elemento = new Elemento(i);
    			pietreDisponibili.add(elemento);
    		}
    	}
    }

    /**
     * chiama il metodo per generare l'equilibrio e fa scegliere ai giocatori che pietre mettere nei golem
     */
    public void evocazioneIniziale() {
    	mondo.generaEquilibrio();
    	g1.scegliPietre(pietreDisponibili);
        System.out.println("ADESSO TOCCA A " + g2.getNome() + ".");
    	g2.scegliPietre(pietreDisponibili,g1);
    }

    /**
     * dati gli ultimi 2 elementi aggiunti al caricatore ne calcola i danni e se sono negativi, vuol dire che l'elemento scelto dal
     * giocatore 2 è più forte di quello del giocatore 1, quindi infligge danni al primo golem del giocatore 1.
     * se i danni sono positivi succede esattamente l'opposto.
     * nel caso un golem muore non stampa i danni che un elemento infligge ad un altro
     */
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

    /**
     * ritorna il vincitore della partita, se non dovesse trovarne una, ritorna una stringa vuota
     * @return
     */
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
