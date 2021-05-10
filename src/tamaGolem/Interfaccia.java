package tamaGolem;

import it.unibs.fp.mylib.InputDati;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Interfaccia {

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


	public static int nuovaPartita(){
		return InputDati.leggiIntero("Vuoi iniziare una nuova partita di TamaGolem?\n0. Si\n1. No", 0,1);
	}

	public static void sceltaDifficolta(){
		System.out.println(SCEGLI_DIFFICOLTA);
		System.out.println(FACILE);
		System.out.println(MEDIO);
		System.out.println(DIFFICILE);
		int valoreScelto = 0;
		int VitaGolem;
		int n = InputDati.leggiIntero("Scegli:", DIFFICOLTA0, DIFFICOLTA2);
		if (valoreScelto==DIFFICOLTA0) {
			n = ELEMENTI_FACILE;
			VitaGolem = VITA_GOLEM_FACILE;
		}
		else if (valoreScelto==DIFFICOLTA1) {
			n = ELEMENTI_MEDIO;
			VitaGolem = VITA_GOLEM_MEDIO;
		}
		else {
			n = ELEMENTI_DIFFICILE;
			VitaGolem = VITA_GOLEM_DIFFICILE;
		}
		Main.NumeroElementi = n;
	}

	public static Deque<TamaGolem> CreaGolems (){
		int n = Main.NumeroElementi;
		int golemPerGiocatore = Giocatore.getNumeroGolem(n, TamaGolem.getNumeroCaricatore(n));
		Deque<TamaGolem> golems = new ArrayDeque<>();
		for (int i = 0; i<golemPerGiocatore; i++){
			TamaGolem t = new TamaGolem(Main.VitaGolem);
			golems.add(t);
		}
		return golems;
	}

	public static void inserimentoNomi(Scontro s){
		Deque<TamaGolem> golems = CreaGolems();
		String nome1, nome2;
		nome1 = InputDati.leggiStringaNonVuota("Giocatore 1. Inserisci il tuo nome:");
		Giocatore g1 = new Giocatore(nome1, golems);
		s.setG1(g1);
		do{
			nome2 = InputDati.leggiStringaNonVuota("Giocatore 2. Inserisci il tuo nome:");
			if (nome1.equalsIgnoreCase(nome2)) System.out.println("Non puoi inserire lo stesso nome per entrambi i giocatori.");
		}while (nome1.equalsIgnoreCase(nome2));
		Giocatore g2 = new Giocatore(nome2, golems);
		s.setG2(g2);
	}
	
	public static void stampaPietreDisponibili(LinkedList<Elemento> pietre) {
		System.out.println("Scegli tra le pietre disponibili digitando l'indice numerico:");
		for(int i=0; i < Main.NumeroElementi; i++) {
			int numPietreUguali=0;
			for(int j=0; j<pietre.size(); j++) {
				if(pietre.get(j).getNome().equals(new Elemento(i).getNome())) {
					numPietreUguali++;
				}
			}
			System.out.println(i + "-" +(new Elemento(i)).getNome() + "("+ numPietreUguali +")");
		}
	}
}
