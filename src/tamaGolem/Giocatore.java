package tamaGolem;

import it.unibs.fp.mylib.InputDati;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Giocatore {
    private String nome;
    private Deque<TamaGolem> golems;

    public Giocatore(String nome, int golemPerGiocatore) {
        this.nome = nome;
        this.golems = new ArrayDeque<>();
        CreaGolems(golemPerGiocatore);
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
     * @return numero di TamaGolem che il giocatore possiede a inizio partita
     */
    public static int getNumeroGolem(int n, int p){
        return (int)Math.ceil((n-1.0)*(n-2)/(2*p));
    }

    /**
     * Dato il numero di golemPerGiocatore inserisce nel deque golems dei golem con vita massima e con caricatore vuoto
     * @param golemPerGiocatore numero dei golem disponibili per giocatore a inizio partita
     */
    public void CreaGolems (int golemPerGiocatore){
        for (int i = 0; i<golemPerGiocatore; i++){
            TamaGolem t = new TamaGolem(Main.getVitaGolem());
            this.golems.add(t);
        }
    }

    /**
     * Rimuove il primo golem dell'ArrayDeque del giocatore
     */
    public void rimuoviGolem(){
        this.getGolems().removeFirst();
    }

    /**
     * Mostrate all'utente quali pietre sono ancora disponibili, l'utente sceglie pietre fino a quando il caricatore è
     * pieno. Le pietre scelte vengono rimosse da pietreDisponibili.
     * @param pietreDisponibili La LinkedList in cui sono presenti le pietre tra cui il giocatore può scegliere
     */
    public void scegliPietre(LinkedList<Elemento> pietreDisponibili) {
        int CapienzaCaricatore = TamaGolem.getNumeroCaricatore(Main.getNumeroElementi());
        while(this.golems.getFirst().getCaricatore().size()<CapienzaCaricatore) {
            Interazione.stampaPietreDisponibili(pietreDisponibili);
            int n = InputDati.leggiIntero((String.format(Interazione.RICHIESTA_INDICE, this.getNome())), 0, pietreDisponibili.size());
            if (pietreDisponibili.contains(new Elemento(n))){
                this.golems.getFirst().setCaricatore(new Elemento(n));
                pietreDisponibili.remove(new Elemento(n));
            }
            else System.out.println(Interazione.MSG_PIETRA_ASSENTE);
        }
    }

    /**
     * Se il Giocatore ha ancora golem a disposizione, dovrà scegliere le pietre del prossimo golem. Le pietre scelte
     * verranno confrontate con quelle del golem avversario
     * @param pietreDisponibili La LinkedList in cui sono presenti le pietre tra cui il giocatore può scegliere
     * @param giocatoreDaConfrontare Giocatore avversario
     */
    public void scegliPietre(LinkedList<Elemento> pietreDisponibili, Giocatore giocatoreDaConfrontare) {
        if(this.golems.isEmpty()){
            System.out.println(String.format(Interazione.MSG_GOLEM_FINITI, this.nome));
        }else{
            scegliPietre(pietreDisponibili);
            this.controlloCaricatore(giocatoreDaConfrontare, pietreDisponibili);
        }
        Interazione.aCapo();
    }

    /**
     * Controlla le pietre dei due golem coinvolti nello scontro. Se i due golem hanno alcune pietre uguali viene chiesto
     * ad un Giocatore di sceglierne altre. Per rendere il cambio delle pietre pseudo-casuale viene chiesto al giocatore
     * di cambiarne una ogni due pietre uguali.
     * @param giocatoreConfronto Giocatore avversario
     * @param pietreDisponibili La LinkedList in cui sono presenti le pietre tra cui il giocatore può scegliere
     */
    public void controlloCaricatore(Giocatore giocatoreConfronto, LinkedList<Elemento> pietreDisponibili) {
        int spazioCaricatore = TamaGolem.getNumeroCaricatore(Main.getNumeroElementi());
        int j=0;
        for(int i=0; i<spazioCaricatore;i++) {
            Elemento pietra1 = giocatoreConfronto.getGolems().getFirst().getCaricatore().getLast();
            Elemento pietra2 = this.getGolems().getFirst().getCaricatore().getLast();
            Elemento newPietra2 = null;
            if(pietra1.equals(pietra2)) {
                j++;
                if(j%2==0) {
                    System.out.println(String.format(Interazione.MSG_CAMBIA_PIETRA, pietra2.getNome(), (i+1)));
                    do{
                        Interazione.stampaPietreDisponibili(pietreDisponibili);
                        int indice = InputDati.leggiIntero(String.format(Interazione.RICHIESTA_INDICE, this.getNome()), 0, pietreDisponibili.size());
                        newPietra2 = new Elemento(indice);
                        if (pietreDisponibili.contains(newPietra2)){
                            this.getGolems().getFirst().getCaricatore().addLast(newPietra2);
                            pietreDisponibili.remove(newPietra2);
                            pietreDisponibili.add(pietra2);
                            this.getGolems().getFirst().getCaricatore().removeLastOccurrence(pietra2);
                        }
                        else System.out.println(Interazione.MSG_PIETRA_ASSENTE);
                    } while(newPietra2.equals(pietra2));
                }
            }
            giocatoreConfronto.getGolems().getFirst().ruotaCaricatore();
            this.getGolems().getFirst().ruotaCaricatore();
        }
    }
}