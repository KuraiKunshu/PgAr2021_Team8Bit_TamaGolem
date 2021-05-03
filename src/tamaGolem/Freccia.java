package tamaGolem;

import java.util.Random;

public class Freccia {
    private int intensita;
    private boolean direzione;

    public Freccia(boolean direzione) {
        Random rand = new Random();
        this.intensita = rand.nextInt(5)+1;
        this.direzione = direzione;
    }

    public Freccia() {
        Random rand = new Random();
        int i = rand.nextInt(5)+1;
        this.intensita = i;
        this.direzione = i%2==0;
    }

    public int getIntensita() {
        return intensita;
    }

    public void setIntensita(int intensita) {
        this.intensita = intensita;
    }

    public boolean getDirezione() {
        return direzione;
    }

    public void setDirezione(boolean direzione) {
        this.direzione = direzione;
    }
}
