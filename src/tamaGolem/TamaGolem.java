package tamaGolem;

import java.util.Deque;

public class TamaGolem {
    private int hp;
    Deque<Elemento> caricatore;

    /**
     * Ritorna gli hp del TamaGolem
     * @return
     */
    public int getHp() {
        return hp;
    }

    /**
     * Imposta gli hp del TamaGolem
     * @param hp punti vita del TamaGolem
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Dato il numero di elementi, ritorna il numero di pietre che il caricatore del TamaGolem può contenere,
     * secondo la formula:
     * ⎡(n + 1) / 3⎤ + 1
      * @param n numero di elementi
     * @return
     */
    public static int getNumeroCaricatore(int n){
        return (int)Math.ceil((n+1.0)/3)+1;
    }

    /**
     * Se il TamaGolem ha almeno 1 hp ritorna true, altrimenti ritorna false
     * @return
     */
    public boolean isAlive(){
        if (this.getHp() <= 0)
            return false;
        else return true;
    }
    public TamaGolem(int hp){
        this.setHp(hp);
    }
}
