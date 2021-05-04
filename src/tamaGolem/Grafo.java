package tamaGolem;

import java.util.*;

public class Grafo {
    private Map<Arco,Integer> mappaDirezioni;
    private int numeroElementiAttivi;
    //numero di elementi che possono interagire insieme
    /**
     * numero di elementi che possono interagire insieme
     */
    private final int NUMERO_COMBINAZIONE=2;
    public Grafo(int numeroElementiAttivi) {
        this.mappaDirezioni = new HashMap<>();
        this.numeroElementiAttivi=numeroElementiAttivi;
    }

    public Map<Arco, Integer> getMappaDirezioni() {
        return mappaDirezioni;
    }

    public void generaEquilibrio(int vitaGolem){
        Random rand = new Random();
        //calcolo quanti archi ho
        int loop=calcoloNumeroArchi(numeroElementiAttivi);
        //genero degli archi casualmente (dovrei calcolare quando ci sono n-1 archi di uguale direzione) e invertire i e j
        Deque<Arco> archi=new ArrayDeque<>();
        while (archi.size()<loop){
            int i = rand.nextInt(numeroElementiAttivi);
            int j = rand.nextInt(numeroElementiAttivi);
            if(i==j)continue;
            if(!(archi.contains(new Arco(new Elemento(i),new Elemento(j)))) && !(archi.contains(new Arco(new Elemento(j),new Elemento(i))))){
                int contaElementoPartenza=0;
                for(int x=0;x<numeroElementiAttivi;x++)if(x!=i&&archi.contains(new Arco(new Elemento(i), new Elemento(x))))contaElementoPartenza++;
                if(contaElementoPartenza!=numeroElementiAttivi-2) archi.add(new Arco(new Elemento(i),new Elemento(j)));
                else archi.add(new Arco(new Elemento(j),new Elemento(i)));
            }
        }
        //genero i danni possibili per ogni elemento
    }

    public Deque<Integer> suddividiDanni(Deque<Arco> archi, int maxDamage){
        Deque<Integer> lista = new ArrayDeque<>();
        Random rand=new Random();
        int numeroDanniOut=0;
        int numeroFrecciePositive=0;
        /*for(int i=0;i<archi.size();i++){
            for(int j=0;j<numeroElementiAttivi;j++){
                if(archi.contains(new Arco(new Elemento(i),new Elemento(j))))numeroFrecciePositive++;
            }
            for(int j=0;j<numeroFrecciePositive;j++){
                int danno= rand.nextInt(maxDamage)+1;
                numeroDanniOut+=danno;
                lista.add(danno);
            }
            for (int j=0; j<numeroElementiAttivi-numeroFrecciePositive; j++){
                int danno=rand.nextInt(numeroDanniOut-numeroFrecciePositive);
                numeroDanniOut-=numeroDanniOut;
                lista.add(danno);
            }
        }*/
        return lista;
    }

    /**
     * ritorna il fattoriale di n
     * @param n
     * @return
     */
    public int fattoriale(int n){
        for(int i=n-1;i>0;i--){
            n*=i;
        }
        return n;
    }

    /**
     * data la formula della combinazione senza ripetizione, ritorna il risultato.
     * C(n,k) = n!/(k!*(n-k)!)
     * k è data dalla costante NUMERO_COMBINAZIONE
     * @param n
     * @return
     */
    public int calcoloNumeroArchi(int n){
        return (fattoriale(n))/(fattoriale(NUMERO_COMBINAZIONE)*fattoriale(n-NUMERO_COMBINAZIONE));
    }
}
