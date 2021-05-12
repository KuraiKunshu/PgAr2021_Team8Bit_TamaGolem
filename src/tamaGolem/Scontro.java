package tamaGolem;

import java.util.LinkedList;

import it.unibs.fp.mylib.InputDati;

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
    	g2.scegliPietre(pietreDisponibili);
       controlloCaricatore(n);
    }

    public void attacco(){
        Elemento pietraGolem1 = g1.getGolems().getFirst().ruotaCaricatore();
        Elemento pietraGolem2 = g2.getGolems().getFirst().ruotaCaricatore();
        int danno = mondo.getDanni(pietraGolem1, pietraGolem2);
        if(danno<0) {
            int vita= g2.getGolems().getFirst().getHp()+danno;
            g2.getGolems().getFirst().setHp(vita);
            if(g2.getGolems().getFirst().isAlive()) {
              System.out.println("Il TamaGolem di " + g1.getNome() + " infligge " + (-1)*danno + " danni al TamaGolem di "+g2.getNome());	
            }
            
        }
        if(danno>0) {
            int vita=g1.getGolems().getFirst().getHp()-danno;
            g1.getGolems().getFirst().setHp(vita);
            if(g1.getGolems().getFirst().isAlive()) {
                System.out.println("Il TamaGolem di " + g2.getNome() + " infligge " + danno +" danni al TamaGolem di "+g1.getNome());	
              }
        }

    }
    public void controlloCaricatore(int n) {
    	
    	int spazioCaricatore = g1.getGolems().getFirst().getNumeroCaricatore(n); 
    	for(int i=0; i<spazioCaricatore;i++) {
    		Elemento pietra1 = g1.getGolems().getFirst().getCaricatore().getLast();
        	Elemento pietra2 = g2.getGolems().getFirst().getCaricatore().getLast();
        	Elemento newPietra2 = null;
        	if(pietra1.equals(pietra2)) {
        		System.out.println("La pietra di tipo "+pietra2.getNome()+" in posizione "+(i+1)+" comprometterebbe la partita, quindi scegli un'altra pietra che vada a sostituirla");
        		do{ 
        			Interazione.stampaPietreDisponibili(pietreDisponibili);
            	    int indice = InputDati.leggiIntero(g2.getNome() + ". Inserisci l'indice dell'elemento con cui vuoi sostituirlo:", 0, pietreDisponibili.size());
                    newPietra2 = new Elemento(indice);
                    if (pietreDisponibili.contains(newPietra2)){
                       g2.getGolems().getFirst().getCaricatore().addLast(newPietra2);
                       pietreDisponibili.remove(newPietra2);
                       pietreDisponibili.add(pietra2);
                       g2.getGolems().getFirst().getCaricatore().removeLastOccurrence(pietra2);
                    }
    			    else System.out.println(Interazione.MSG_PIETRA_ASSENTE);	
                } while(newPietra2.equals(pietra2));
        	}
        	g1.getGolems().getFirst().ruotaCaricatore();
        	g2.getGolems().getFirst().ruotaCaricatore();
    	}
    
    	
//    	if(j==spazioCaricatore) {
//    		return false;
//    	}
//    	return true;
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
