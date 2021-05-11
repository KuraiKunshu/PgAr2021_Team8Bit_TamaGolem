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

    /**
     * costruttore della classe Grafo
     */
    public Grafo() {
        this.mappaDirezioni = new HashMap<>();
        this.mappaDirezioni.put(null,0);
    }

    public Map<Arco, Integer> getMappaDirezioni() {
        return mappaDirezioni;
    }

    public int[][] generaEquilibrio(int n){
        Random rand = new Random();
        int[][] m = new int[n][n];
        int valoreMax = 0;
        int vitaMax = 25;
        do {
            //Imposta i valori della diagonale a 0
            for (int i = 0; i < n; i++) {
                m[i][i] = 0;
            }
            //Imposta la matrice (esclusa la diagonale) con 0 oppure 1 in modo casuale, simmetrica rispetto alla diagonale
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    m[i][j] = rand.nextInt(2);
                    if (m[i][j] == 0) m[j][i] = 1;
                    else m[j][i] = 0;
                }
            }
            //Continua il cilo finchè la matrice non è valida
        } while (!isMatriceElementiValida(m, n));
        boolean valoreMaxUgualeAVitaMax = false;
        do {
            do {
                //Prova a rendere la matrice equilibrata aumentando i valori diversi da zero
                int sommaRiga, sommaColonna, contatoreValoriNonNulliRiga, contatoreValoriNonNulliColonna;
                int valorePrecedente = 0, rigaValorePrecedente = 0, colonnaValorePrecedente = 0;
                for (int i = 0; i < n; i++) {
                    sommaRiga = 0;
                    sommaColonna = 0;
                    contatoreValoriNonNulliRiga = 0;
                    contatoreValoriNonNulliColonna = 0;
                    //Calcola somma dei valori nella riga e quanti valori non nulli ci sono
                    for (int j = 0; j < n; j++) {
                        sommaRiga += m[i][j];
                        if (m[i][j] != 0) {
                            contatoreValoriNonNulliRiga++;
                        }
                    }
                    //Calcola somma dei valori nella colonna e quanti valori non nulli ci sono
                    for (int k = 0; k < n; k++) {
                        sommaColonna += m[k][i];
                        if (m[k][i] != 0) {
                            contatoreValoriNonNulliColonna++;
                        }
                    }
                    //Calcola la differenza tra sommaRiga e sommaColonna
                    int differenza = sommaRiga - sommaColonna;
                    int valoreCasualeContatore;
                    //Se sommaColonna > sommaRiga aggiunge 1 ad uno dei valori non nulli della riga
                    if (differenza < 0) {
                        //Prende un numero casuale tra i valori non nulli della riga
                        valoreCasualeContatore = rand.nextInt(contatoreValoriNonNulliRiga) + 1;
                        for (int j = 0; j < n; j++) {
                            int contatore = 0;
                            if (m[i][j] != 0) {
                                contatore++;
                            }
                            //Quando trova il valore non nullo corrispondente al valore casuale trovato
                            if (contatore == valoreCasualeContatore) {
                                //Se il valore è minore della vitaMax del TamaGolem aggiunge 1
                                if (m[i][j] < vitaMax) {
                                    valorePrecedente = m[i][j];
                                    rigaValorePrecedente = i;
                                    colonnaValorePrecedente = j;
                                    m[i][j]++;
                                    break;
                                }
                                //Se il valore è uguale alla vitaMax del TamaGolem
                                else if (m[i][j] == vitaMax) {
                                    //Può essere che tutti i valori non nulli siano = vitaMax?
                                    //Se esistono almeno 2 valori casuali non nulli nella riga
                                    if (contatoreValoriNonNulliRiga != 1) {
                                        //Cerca un altro valore non nullo della riga finchè trova uno diverso da quello appena considerato
                                    /*do{
                                        valoreCasualeContatore = rand.nextInt(contatoreValoriNonNulliRiga) + 1;
                                    }while (contatore == valoreCasualeContatore);
                                    if ()*/
                                    }
                                    //Se esiste un solo valore non nullo nella riga
                                    else if (contatoreValoriNonNulliRiga == 1) {
                                        m[rigaValorePrecedente][colonnaValorePrecedente] = valorePrecedente;
                                        do{
                                            valoreCasualeContatore = rand.nextInt(contatoreValoriNonNulliColonna) + 1;
                                        }while (contatore == valoreCasualeContatore);
                                        contatore = 0;
                                        for(int l=0; l<contatoreValoriNonNulliColonna; l++){
                                            if (m[i][l] != 0) {
                                                contatore++;
                                            }
                                            if (contatore == valoreCasualeContatore){
                                                m[i][l]++;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //Se sommaRiga > sommaColonna aggiunge 1 ad uno dei valori non nulli della colonna
                    else if (differenza > 0) {
                        //Prende un numero casuale tra i valori non nulli della colonna
                        valoreCasualeContatore = rand.nextInt(contatoreValoriNonNulliColonna) + 1;
                        for (int k = 0; k < n; k++) {
                            int contatore = 0;
                            if (m[k][i] != 0) {
                                contatore++;
                            }
                            //Quando trova il valore non nullo corrispondente al valore casuale trovato
                            if (contatore == valoreCasualeContatore) {
                                //Se il valore è minore della vitaMax del TamaGolem aggiunge 1
                                if (m[k][i] < vitaMax) {
                                    valorePrecedente = m[k][i];
                                    rigaValorePrecedente = k;
                                    colonnaValorePrecedente = i;
                                    m[k][i]++;
                                    break;
                                }
                                //Se il valore è uguale alla vitaMax del TamaGolem
                                else if (m[k][i] == vitaMax) {
                                    //Può essere che tutti i valori non nulli siano = vitaMax?
                                    //Se esistono almeno 2 valori casuali non nulli nella riga
                                    if (contatoreValoriNonNulliColonna != 1) {
                                        //Cerca un altro valore non nullo della riga finchè trova uno diverso da quello appena considerato
                                    /*do{
                                        valoreCasualeContatore = rand.nextInt(contatoreValoriNonNulliColonna) + 1;
                                    }while (contatore == valoreCasualeContatore);
                                    if ()*/
                                    }
                                    //Se esiste un solo valore non nullo nella riga
                                    else if (contatoreValoriNonNulliColonna == 1) {
                                        m[rigaValorePrecedente][colonnaValorePrecedente] = valorePrecedente;
                                        do{
                                            valoreCasualeContatore = rand.nextInt(contatoreValoriNonNulliRiga) + 1;
                                        }while (contatore == valoreCasualeContatore);
                                        contatore = 0;
                                        for(int l=0; i<contatoreValoriNonNulliRiga; l++){
                                            if (m[l][i] != 0) {
                                                contatore++;
                                            }
                                            if (contatore == valoreCasualeContatore){
                                                m[l][i]++;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //Continua il ciclo finchè non trova una matrice equilibrata
            } while (!isMatriceEquilibrata(m, n));

            //Trova il valore massimo presente nella matrice
            valoreMax = 0;
            int rigaValoreMax = 0, colonnaValoreMax = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (m[i][j] > valoreMax){
                        valoreMax = m[i][j];
                        rigaValoreMax = i;
                        colonnaValoreMax = j;
                    }
                }
            }
            //Se il valoreMax è uguale a vitaMax imposta valoreMaxUgualeAVitaMax = true
            if (valoreMax == vitaMax) {
                valoreMaxUgualeAVitaMax = true;
            }
            //Altrimenti aumenta il valoreMax della matrice di 1
            else{
                m[rigaValoreMax][colonnaValoreMax]++;
            }
        }while (valoreMaxUgualeAVitaMax == false);
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

    public int getDanni(Elemento primo, Elemento secondo) {
    	if(primo.equals(secondo)) {
    		return 0;
    	}
    	if(mappaDirezioni.get(new Arco(primo,secondo))!=null) {
    		return mappaDirezioni.get(new Arco(primo,secondo)); 
    	}
    	else {
    		return (-1)*mappaDirezioni.get(new Arco(secondo,primo)); 
    	}
    }

    /*
    /**
     * ritorna il fattoriale di n
     * @param n
     * @return
     */
    /*
    public int fattoriale(int n){
        for(int i=n-1;i>0;i--){
            n*=i;
        }
        return n;
    }*/
    /*
    /**
     * data la formula della combinazione senza ripetizione, ritorna il risultato.
     * C(n,k) = n!/(k!*(n-k)!)
     * k è data dalla costante NUMERO_COMBINAZIONE
     * @param n
     * @return
     */
    /*
    public int calcoloNumeroArchi(int n){
        return (fattoriale(n))/(fattoriale(NUMERO_COMBINAZIONE)*fattoriale(n-NUMERO_COMBINAZIONE));
    }*/

}
