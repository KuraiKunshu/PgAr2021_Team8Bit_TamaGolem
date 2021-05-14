package tamaGolem;

import java.util.HashMap;
import java.util.Map;

public class Main {
	
	
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
        Map<Arco,Integer> mappa= new HashMap<>();
        while(Interazione.nuovaPartita()){
            Interazione.sceltaDifficolta();
            Scontro s = new Scontro();
            Interazione.inserimentoNomi(s);
            s.riempiPietreDisponibili(NumeroElementi);
            s.evocazioneIniziale(NumeroElementi);
            while (!(s.getG1().getGolems().isEmpty() || s.getG2().getGolems().isEmpty())){
                s.attacco();
                if(!(s.getG1().getGolems().getFirst().isAlive())){
                    System.out.println(String.format(Interazione.MSG_DEAD, s.getG1().getNome()));
                    s.getG1().rimuoviGolem();
                    s.getG1().scegliPietre(s.getPietreDisponibili());
                }
                if(!(s.getG2().getGolems().getFirst().isAlive())){
                    System.out.println(String.format(Interazione.MSG_DEAD, s.getG2().getNome()));
                    s.getG2().rimuoviGolem();
                    s.getG2().scegliPietre(s.getPietreDisponibili());
                }
                Main.pausa();
            }
            System.out.println(Interazione.MSG_WINNER+ s.vincitore());
            Main.pausa();
            s.getMondo().StampaEquilibrio();
            
            
        }
        System.out.println(Interazione.MSG_END);

     
    }
}
