package tamaGolem;

public class Main {
	private static int NumeroElementi;
	private static int VitaGolem;

    /**
     * ritorna il numero degli elementi
     * @return numero elementi
     */
    public static int getNumeroElementi() {
        return NumeroElementi;
    }

    /**
     * imposta il numero degli elementi
     * @param numeroElementi valore da sostituire
     */
    public static void setNumeroElementi(int numeroElementi) {
        NumeroElementi = numeroElementi;
    }

    /**
     * prende la vita del golem
     * @return vita golem
     */
    public static int getVitaGolem() {
        return VitaGolem;
    }

    /**
     * imposta la vita massima del golem
     * @param vitaGolem valore da sostituire
     */
    public static void setVitaGolem(int vitaGolem) {
        VitaGolem = vitaGolem;
    }

    /**
     * metto in pausa il thread del main in modo da poter vedere in modo migliore le stampe.
     */
    public static void pausa(){
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        while(Interazione.nuovaPartita()){
            Interazione.aCapo();
            Interazione.sceltaDifficolta();
            Interazione.aCapo();
            Scontro s = new Scontro();
            Interazione.inserimentoNomi(s);
            Interazione.aCapo();
            s.riempiPietreDisponibili();
            s.evocazioneIniziale();
            while (!(s.getG1().getGolems().isEmpty() || s.getG2().getGolems().isEmpty())){
                s.attacco();
                if(!(s.getG1().getGolems().getFirst().isAlive())){
                    System.out.println(String.format(Interazione.MSG_DEAD, s.getG1().getNome()));
                    Interazione.aCapo();
                    s.getG1().rimuoviGolem();
                    s.getG1().scegliPietre(s.getPietreDisponibili(), s.getG2());
                }
                if(!(s.getG2().getGolems().getFirst().isAlive())){
                    System.out.println(String.format(Interazione.MSG_DEAD, s.getG2().getNome()));
                    Interazione.aCapo();
                    s.getG2().rimuoviGolem();
                    s.getG2().scegliPietre(s.getPietreDisponibili(),s.getG1());
                }
                Main.pausa();
            }
            System.out.println(Interazione.MSG_WINNER+ s.vincitore());
            Interazione.aCapo();
            Main.pausa();
            System.out.println(Interazione.STAMPA_EQUILIBRIO);
            s.getMondo().StampaEquilibrio();
            Interazione.aCapo();
        }
        System.out.println(Interazione.MSG_END);
    }
}
