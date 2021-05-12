package tamaGolem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Grafo {
    private static final String MSG_INTERAZIONE_PIETRE = "La pietra di %s ha il sopravvento su quella di %s";
	private static final String ANNULLAMENTO = "Le pietre sono dello stesso tipo e si annullano a vicenda";
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
        
    }

    public void StampaEquilibrio() {
        for(Map.Entry<Arco,Integer> entry: mappaDirezioni.entrySet()) {
        	System.out.println( entry.getKey().toString() + "-->" + entry.getValue());
        }
    }

    /**
     * Crea una matrice costituita da valori che vanno da 0 alla vita massima del TamaGolem.
     * La somma dei valori di ogni riga è uguale alla somma dei valori della rispettiva colonna.
     * Il valore massimo nella matrice è sempre la vita massima del TamaGolem.
     * La diagonale è costituita da zeri.
     * Se un valore della matrice è maggiore di 0, allora il suo valore simmetrico rispetto alla diagonale è 0 e viceversa.
     * In questo modo ogni valore diverso da 0 rappresenta la potenza con cui l'Elemento con indice pari a quello della riga
     * danneggia quello con indice pari a qullo della colonna.
     * @param n numero di elementi
     */
    public void generaEquilibrio(int n){
        Random rand = new Random();
        int[][] m = new int[n][n];
        int vitaMax = Main.getVitaGolem();
        m = creaMatriceValida(m, n);
        boolean valoreMaxUgualeAVitaMax = false;
        do {
            do {
                //Prova a rendere la matrice equilibrata aumentando i valori diversi da zero
                for (int i = 0; i < n; i++) {
                    int sommaRiga = 0;
                    int sommaColonna = 0;
                    int contatoreValoriNonNulliRiga = 0;
                    int contatoreValoriNonNulliColonna = 0;
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
                    int differenza = sommaRiga - sommaColonna;
                    int valoreCasualeContatore;
                    //Se sommaColonna > sommaRiga aggiunge 1 ad uno dei valori non nulli della riga
                    if (differenza < 0) {
                        //Prende un numero casuale tra i valori non nulli della riga
                        valoreCasualeContatore = rand.nextInt(contatoreValoriNonNulliRiga) + 1;
                        int contatore = 0;
                        //Controlla i valori della riga e aumenta di 1 il contatore ogni volta che incontra un valore non nullo
                        for (int j = 0; j < n; j++) {
                            if (m[i][j] != 0) {
                                contatore++;
                            }
                            //Quando trova il valore non nullo corrispondente al valore casuale trovato
                            if (contatore == valoreCasualeContatore) {
                                //Se il valore è minore della vitaMax del TamaGolem aggiunge 1
                                if (m[i][j] < vitaMax) {
                                    m[i][j]++;
                                    break;
                                }
                                //Se il valore è uguale alla vitaMax del TamaGolem crea una nuova matrice valida
                                else if (m[i][j] == vitaMax) {
                                    m = creaMatriceValida(m, n);
                                }
                            }
                        }
                    }
                    //Se sommaRiga > sommaColonna aggiunge 1 ad uno dei valori non nulli della colonna
                    else if (differenza > 0) {
                        //Prende un numero casuale tra i valori non nulli della colonna
                        valoreCasualeContatore = rand.nextInt(contatoreValoriNonNulliColonna) + 1;
                        int contatore = 0;
                        //Controlla i valori della colonna e aumenta di 1 il contatore ogni volta che incontra un valore non nullo
                        for (int k = 0; k < n; k++) {
                            if (m[k][i] != 0) {
                                contatore++;
                            }
                            //Quando trova il valore non nullo corrispondente al valore casuale trovato
                            if (contatore == valoreCasualeContatore) {
                                //Se il valore è minore della vitaMax del TamaGolem aggiunge 1
                                if (m[k][i] < vitaMax) {
                                    m[k][i]++;
                                    break;
                                }
                                //Se il valore è uguale alla vitaMax del TamaGolem crea una nuova matrice valida
                                else if (m[k][i] == vitaMax) {
                                    m = creaMatriceValida(m, n);
                                }
                            }
                        }
                    }
                }
            //Continua il ciclo finchè non trova una matrice equilibrata
            } while (!isMatriceEquilibrata(m, n));

            //Trova il valore minimo (diverso da 0) presente nella matrice e la sua posizione
            int valoreMin = vitaMax;
            int rigaValoreMin = 0, colonnaValoreMin = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (m[i][j] < valoreMin && m[i][j] != 0){
                        valoreMin = m[i][j];
                        rigaValoreMin = i;
                        colonnaValoreMin = j;
                    }
                }
            }
            //Trova il valore massimo presente nella matrice e la sua posizione
            int valoreMax = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (m[i][j] > valoreMax){
                        valoreMax = m[i][j];
                    }
                }
            }
            //Se il valoreMax è uguale a vitaMax imposta valoreMaxUgualeAVitaMax = true
            if (valoreMax == vitaMax) {
                valoreMaxUgualeAVitaMax = true;
            }
            //Altrimenti aumenta il valoreMin della matrice di 1
            else{
                m[rigaValoreMin][colonnaValoreMin]++;
            }
        //Esce dal ciclo quando trova una matrice con valoreMax = vitaMax del TamaGolem
        }while (valoreMaxUgualeAVitaMax == false);
        //Imposta la mappaDirezioni con i valori nella matrice (zeri esclusi) prendendo gli elementi con l'id
        //corrispondente agli indici di riga e colonna
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++){
                if (m[i][j] != 0) mappaDirezioni.put(new Arco(new Elemento(i), new Elemento(j)), m[i][j]);
            }
        }
    }

    /**
     * Imposta i valori di una matrice con 0 e 1, simmetrica rispetto alla diagonale e con zeri nella diagonale
     * @param m matrice
     * @param n numero di elementi
     * @return matrice costituita da 0 oppure 1, simmetrica rispetto alla diagonale e con zeri nella diagonale
     */
    public int[][] creaMatriceValida(int[][] m, int n){
        Random rand = new Random();
        do {
            //Imposta la matrice (esclusa la diagonale) con 0 oppure 1 in modo casuale, simmetrica rispetto alla diagonale
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    m[i][j] = rand.nextInt(2);
                    //Rende la matrice simmetrica rispetto alla diagonale
                    if (m[i][j] == 0) m[j][i] = 1;
                    else m[j][i] = 0;
                }
            }
        //Continua il ciclo finchè non trova una matrice valida
        } while (!isMatriceElementiValida(m, n));
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
            if (sommaRiga != sommaColonna) return false;
        }
        return true;
    }

    public int getDanni(Elemento primo, Elemento secondo) {
    	if(primo.equals(secondo)) {
    		System.out.println(ANNULLAMENTO);
    		return 0;
    	}
    	if(mappaDirezioni.get(new Arco(primo,secondo))!=null) {
    		System.out.println(String.format(MSG_INTERAZIONE_PIETRE, primo.getNome(),secondo.getNome()));
    		return mappaDirezioni.get(new Arco(primo,secondo)); 
    	}
    	else {
    		System.out.println(String.format(MSG_INTERAZIONE_PIETRE, secondo.getNome(),primo.getNome()));
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
     * k Ã¨ data dalla costante NUMERO_COMBINAZIONE
     * @param n
     * @return
     */
    /*
    public int calcoloNumeroArchi(int n){
        return (fattoriale(n))/(fattoriale(NUMERO_COMBINAZIONE)*fattoriale(n-NUMERO_COMBINAZIONE));
    }*/

}
