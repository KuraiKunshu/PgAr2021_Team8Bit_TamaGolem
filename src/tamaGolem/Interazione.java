package tamaGolem;

import it.unibs.fp.mylib.InputDati;

import java.util.LinkedList;

public class Interazione {
	private static final String NUOVA_PARTITA="Vuoi iniziare una nuova partita di TamaGolem?\n";
	private static final String SCEGLI_DIFFICOLTA = "Scegli la difficoltà:";
	private static final int ELEMENTI_FACILE = 5;
	private static final int ELEMENTI_MEDIO = 7;
	private static final int ELEMENTI_DIFFICILE = 10;
	private static final int DIFFICOLTA0 = 0;
	private static final int DIFFICOLTA1 = 1;
	private static final int DIFFICOLTA2 = 2;
	private static final int VITA_GOLEM_FACILE = 5;
	private static final int VITA_GOLEM_MEDIO = 10;
	private static final int VITA_GOLEM_DIFFICILE = 15;
	private static final String FACILE = DIFFICOLTA0 + ". Facile (" + ELEMENTI_FACILE + " elementi)";
	private static final String MEDIO = DIFFICOLTA1 + ". Medio (" + ELEMENTI_MEDIO + " elementi)";
	private static final String DIFFICILE = DIFFICOLTA2 + ". Difficile (" + ELEMENTI_DIFFICILE + " elementi)";


	public static boolean nuovaPartita(){
		return InputDati.yesOrNo(NUOVA_PARTITA);
	}

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



	public static void inserimentoNomi(Scontro s){
		int n = Main.getNumeroElementi();
		int golemPerGiocatore = Giocatore.getNumeroGolem(n, TamaGolem.getNumeroCaricatore(n));
		String nome1, nome2;

		nome1 = InputDati.leggiStringaNonVuota("Giocatore 1. Inserisci il tuo nome:");
		Giocatore g1 = new Giocatore(nome1, golemPerGiocatore);
		s.setG1(g1);
		do{
			nome2 = InputDati.leggiStringaNonVuota("Giocatore 2. Inserisci il tuo nome:");
			if (nome1.equalsIgnoreCase(nome2)) System.out.println("Non puoi inserire lo stesso nome per entrambi i giocatori.");
		}while (nome1.equalsIgnoreCase(nome2));
		Giocatore g2 = new Giocatore(nome2, golemPerGiocatore);
		s.setG2(g2);
	}
	
	public static void stampaPietreDisponibili(LinkedList<Elemento> pietre) {
		System.out.println("Scegli tra le pietre disponibili digitando l'indice numerico:");
		for(int i=0; i < Main.getNumeroElementi(); i++) {
			int numPietreUguali=0;
			Elemento elemento = new Elemento(i);
			for(Elemento e : pietre) {
				if(e.equals(elemento)) numPietreUguali++;
			}
			System.out.println(i + "-" + elemento.getNome() + "("+ numPietreUguali +")");
		}
	}
}
