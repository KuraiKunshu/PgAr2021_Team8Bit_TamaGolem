package tamaGolem;

import it.unibs.fp.mylib.InputDati;

import java.util.LinkedList;

public class Interazione {
	
	private static final String NUOVA_PARTITA="Vuoi iniziare una nuova partita di TamaGolem?\n";
	private static final String SCEGLI_DIFFICOLTA = "Scegli la difficolta:";
	private static final int ELEMENTI_FACILE = 5;
	private static final int ELEMENTI_MEDIO = 7;
	private static final int ELEMENTI_DIFFICILE = 10;
	private static final int DIFFICOLTA0 = 0;
	private static final int DIFFICOLTA1 = 1;
	private static final int DIFFICOLTA2 = 2;
	private static final int VITA_GOLEM_FACILE = 25;
	private static final int VITA_GOLEM_MEDIO = 50;
	private static final int VITA_GOLEM_DIFFICILE = 100;
	private static final String FACILE = DIFFICOLTA0 + ". Facile (" + ELEMENTI_FACILE + " elementi)";
	private static final String MEDIO = DIFFICOLTA1 + ". Medio (" + ELEMENTI_MEDIO + " elementi)";
	private static final String DIFFICILE = DIFFICOLTA2 + ". Difficile (" + ELEMENTI_DIFFICILE + " elementi)";

	private static final String GIOCATORE1 = "Giocatore1";
	private static final String GIOCATORE2 = "Giocatore2";
	private static final String RICHIESTA_NOME = "%s. Inserisci il tuo nome:";
	
	private static final String ERRORE_OMONIMIA = "Non puoi inserire lo stesso nome per entrambi i giocatori.";
	public static final String ERRORE_PIETRA = "NON PUOI SCEGLIERE LO STESSO TIPO DI PIETRA.";

	public static final String PIETRE_DISPONIBILI = "Pietre disponibili: ";
	public static final String RICHIESTA_INDICE = "%s. Inserisci l'indice dell'elemento che desideri:";
	public static final String MSG_PIETRA_ASSENTE = "La pietra scelta non e' disponibile.";
	public static final String MSG_CAMBIA_PIETRA = "La pietra di tipo %s in posizione %d comprometterebbe la partita, quindi scegli un'altra pietra che vada a sostituirla";
	public static final String MSG_INIZIO_BATTAGLIA = "LA BATTAGLIA HA INIZIO";
	public static final String MSG_DANNI="Il golem di %s infligge %d danni al golem di %s";
	public static final String MSG_DEAD = "IL GOLEM DI %s E' MORTO.";
	public static final String MSG_GOLEM_FINITI = "%s ha esaurito i suoi golem.";
    
	public static final String MSG_WINNER = "IL VINCITORE DI QUESTO SCONTRO E': ";
	public static final String MSG_END = "Programma golem terminato. Autodistruzione in corso...";
	public static final String STAMPA_EQUILIBRIO = "Questo e' l'equilibrio del sistema:";


	/**
	 * chiede all'utente se vuole fare una nuova partita
	 * @return ritorna il valore boolean della risposta
	 */
	public static boolean nuovaPartita(){
		return InputDati.yesOrNo(NUOVA_PARTITA);
	}

	/**
	 * date le 3 difficolta (facile, medio, difficile), l'utente sceglie che difficoltà mettere e imposta la vita dei golem e il numero degli elementi i relazione a quello
	 */
	public static void sceltaDifficolta(){
		System.out.println(SCEGLI_DIFFICOLTA);
		System.out.println(FACILE);
		System.out.println(MEDIO);
		System.out.println(DIFFICILE);
		int valoreScelto = InputDati.leggiIntero("Scegli:", DIFFICOLTA0, DIFFICOLTA2);
		if (valoreScelto==DIFFICOLTA0) {
			Main.setNumeroElementi(ELEMENTI_FACILE);
			Main.setVitaGolem(VITA_GOLEM_FACILE);
		}
		else if (valoreScelto==DIFFICOLTA1) {
			Main.setNumeroElementi(ELEMENTI_MEDIO);
			Main.setVitaGolem(VITA_GOLEM_MEDIO);
		}
		else {
			Main.setNumeroElementi(ELEMENTI_DIFFICILE);
			Main.setVitaGolem(VITA_GOLEM_DIFFICILE);
		}
	}

	/**
	 * dato lo scontro, chiede agli utenti il nick e inizializza i 2 giocatori.
	 * @param s
	 */
	public static void inserimentoNomi(Scontro s){
		int n = Main.getNumeroElementi();
		int golemPerGiocatore = Giocatore.getNumeroGolem(n, TamaGolem.getNumeroCaricatore(n));
		String nome1, nome2;
		//per problemi di input, abbiamo dovuto aggiungere questa linea di comando
		InputDati.leggiLine("");
		nome1 = InputDati.leggiLineNonVuota(String.format(RICHIESTA_NOME, GIOCATORE1));
		Giocatore g1 = new Giocatore(nome1, golemPerGiocatore);
		s.setG1(g1);
		do{
			nome2 = InputDati.leggiLineNonVuota(String.format(RICHIESTA_NOME, GIOCATORE2));
			if (nome1.equalsIgnoreCase(nome2)) System.out.println(ERRORE_OMONIMIA);
		}while (nome1.equalsIgnoreCase(nome2));
		Giocatore g2 = new Giocatore(nome2, golemPerGiocatore);
		s.setG2(g2);
	}

	/**
	 * data una List di Elemento, stampa quanti elementi ci sono per ogni elemento possibile (dato dalla difficoltà)
	 * @param pietre
	 */
	public static void stampaPietreDisponibili(LinkedList<Elemento> pietre) {
		System.out.println(PIETRE_DISPONIBILI);
		for(int i=0; i < Main.getNumeroElementi(); i++) {
			int numPietreUguali=0;
			Elemento elemento = new Elemento(i);
			for(Elemento e : pietre) {
				if(e.equals(elemento)) numPietreUguali++;
			}
			System.out.println(i + "-" + elemento.getNome() + "("+ numPietreUguali +")");
		}
	}

	/**
	 * va a capo di una riga in modo da poter lasciare piu spazio tra una stampa e l'altra
	 */
	public static void aCapo(){
		System.out.println();
	}
}
