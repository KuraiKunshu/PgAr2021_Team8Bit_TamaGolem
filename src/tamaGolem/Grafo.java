package tamaGolem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Grafo {
    private Map<Arco,Integer> mappaDirezioni;
    //numero di elementi che possono interagire insieme
    /**
     * numero di elementi che possono interagire insieme
     */
    private final int NUMERO_COMBINAZIONE=2;
    public Grafo() {
        this.mappaDirezioni = new HashMap<>();
    }

    public Map<Arco, Integer> getMappaDirezioni() {
        return mappaDirezioni;
    }

    public int[][] generaEquilibrio(int n){
        Random rand = new Random();
        int[][] m = new int[n][n];
        int valoreMax = 0;
        int vitaMax = 10;
        do{
            do {
                //Imposta i valori della diagonale a 0
                for (int i=0; i<n; i++){
                    m[i][i]= 0;
                }
                //Imposta la matrice (esclusa la diagonale) con 0 oppure 1 in modo casuale, simmetrica rispetto alla diagonale
                for (int i=0; i<n-1; i++){
                    for (int j=i+1; j<n; j++){
                        m[i][j] = rand.nextInt(2);
                        if (m[i][j] == 0) m[j][i] = 1;
                        else m[j][i] = 0;
                    }
                }
            //Continua il cilo finchè la matrice non è valida
            }while (!isMatriceElementiValida(m, n));

            //Prova a rendere la matrice equilibrata aumentando i valori diversi da zero
            int sommaRiga, sommaColonna;
            for (int i=0; i<n; i++){
                sommaRiga = 0;
                sommaColonna = 0;
                for (int j=0; j<n; j++){
                    sommaRiga += m[i][j];
                }
                for (int k=0; k<n; k++){
                    sommaColonna += m[k][i];
                }
                int differenza = sommaRiga - sommaColonna;
                if (differenza < 0){
                    for (int j=0; j<n; j++){
                        if(m[i][j] != 0) {
                            m[i][j] += Math.abs(differenza);
                            break;
                        }
                    }
                }else if (differenza > 0){
                    for (int k=0; k<n; k++){
                        if(m[k][i] != 0) {
                            m[k][i] += differenza;
                            break;
                        }
                    }
                }
            }
            //Trova il valore massimo presente nella matrice
            valoreMax = 0;
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++){
                    if (m[i][j] > valoreMax) valoreMax = m[i][j];
                }
            }
        //Continua il ciclo finchè non trova una matrice equilibrata e con il valore massimo minore della vita del TamaGolem
        }while (!isMatriceEquilibrata(m, n) || valoreMax > vitaMax); //con n=5 vitaMax=5, con n=7 vitaMax = 9, con n=10 vitaMax=21

        //Crea simmetria positivi/negativi rispetto alla diagonale
        /*for (int i=0; i<n-1; i++){
            for (int j=i+1; j<n; j++){
                if (m[i][j] != 0) m[j][i] = -m[i][j];
                else m[i][j] = -m[j][i];
            }
        }*/
        //Imposta la mappaDirezioni con i valori nella matrice (zeri esclusi) prendendo gli elementi con l'id
        // corrispondente agli indici di riga e colonna
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++){
                if (m[i][j] != 0) mappaDirezioni.put(new Arco(new Elemento(i), new Elemento(j)), m[i][j]);
            }
        }
        return m;
    }

    /**
     * Ritorna true se tutti gli elementi svolgono il ruolo di elemento forte (1 nella riga rispettiva) o il ruolo di
     * elemento debole (1 nella colonna rispettiva) almeno una volta.
     * Ritorna false se trova almeno una riga o colonna che contiene n zeri oppure n-1 uni.
     * @param m matrice degli elementi, costituita da valori 0 oppure 1
     * @param n numero di elementi
     * @return true or false
     */
    public boolean isMatriceElementiValida(int[][] m, int n){
        for (int i=0; i<n; i++){
            int contatore0 = 0;
            for (int j=0; j<n; j++){
                if (m[i][j] == 0)
                    contatore0++;
            }
            if (contatore0 == n || contatore0 == 1)
                return false;
        }
        return true;
    }

    /**
     * Ritorna true se la somma dei valori di ogni riga è uguale alla somma dei valori della rispettiva colonna.
     * @param m matrice degli elementi, costituita da valori positivi (0 incluso)
     * @param n numero di elementi
     * @return true or false
     */
    public boolean isMatriceEquilibrata(int[][] m, int n){
        int sommaRiga, sommaColonna;
        for (int i=0; i<n; i++) {
            sommaRiga = 0;
            sommaColonna = 0;
            for (int j = 0; j < n; j++) {
                sommaRiga += m[i][j];
            }
            for (int k = 0; k < n; k++) {
                sommaColonna += m[k][i];
            }
            int differenza = sommaRiga - sommaColonna;
            if (differenza != 0) return false;
        }
        return true;
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
