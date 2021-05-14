package tamaGolem;

import java.util.HashMap;
import java.util.Map;

public class Main {
	
<<<<<<< HEAD
	
=======
	private static final String MSG_END = "Programma TamaGolem terminato. Autodistruzione in corso...";
	private static final String MSG_WINNER = "Il vincitore di questo scontro è: ";
>>>>>>> c7c65b80651e959286986480ed1073eb8a1804d0
	private static int NumeroElementi;
	private static int VitaGolem;

    public static int getNumeroElementi() {
        return NumeroElementi;
    }

    public static void setNumeroElementi(int numeroElementi) {
        NumeroElementi = numeroElementi;
    }

    public static int getVitaGolem() {
        return VitaGolem;
    }

    public static void setVitaGolem(int vitaGolem) {
        VitaGolem = vitaGolem;
    }

    public static void pausa(){
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        while(Interazione.nuovaPartita()){
            Interazione.sceltaDifficolta();
            Scontro s = new Scontro();
            Interazione.inserimentoNomi(s);
            s.riempiPietreDisponibili(NumeroElementi);
            s.evocazioneIniziale(NumeroElementi);
            while (!(s.getG1().getGolems().isEmpty() || s.getG2().getGolems().isEmpty())){
                s.attacco();
                if(!(s.getG1().getGolems().getFirst().isAlive())){
<<<<<<< HEAD
                    System.out.println(String.format(Interazione.MSG_DEAD, s.getG1().getNome()));
=======
                    System.out.println("Il TamaGolem di " + s.getG1().getNome() + " è morto");
>>>>>>> c7c65b80651e959286986480ed1073eb8a1804d0
                    s.getG1().rimuoviGolem();
                    s.getG1().scegliPietre(s.getPietreDisponibili());
                }
                if(!(s.getG2().getGolems().getFirst().isAlive())){
<<<<<<< HEAD
                    System.out.println(String.format(Interazione.MSG_DEAD, s.getG2().getNome()));
=======
                    System.out.println("Il TamaGolem di " + s.getG2().getNome() + " è morto");
>>>>>>> c7c65b80651e959286986480ed1073eb8a1804d0
                    s.getG2().rimuoviGolem();
                    s.getG2().scegliPietre(s.getPietreDisponibili());
                }
                Main.pausa();
            }
            System.out.println(Interazione.MSG_WINNER+ s.vincitore());
            Main.pausa();
            s.getMondo().StampaEquilibrio();
        }
<<<<<<< HEAD
        System.out.println(Interazione.MSG_END);

     
=======
        System.out.println(MSG_END);
>>>>>>> c7c65b80651e959286986480ed1073eb8a1804d0
    }
}
