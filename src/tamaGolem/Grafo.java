package tamaGolem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Grafo {
    private static final String MSG_INTERAZIONE_PIETRE = "La pietra di %s ha il sopravvento su quella %s";
	private static final String ANNULLAMENTO = "Le 2 pietre %s si annullano a vicenda";
	private Map<Arco,Integer> mappaDirezioni;

    /**
     * costruttore della classe Grafo
     */
    public Grafo() {
        this.mappaDirezioni = new HashMap<>();
        
    }

    /**
     * stampa tutti gli archi e i suoi relativi danni
     */
    public void StampaEquilibrio() {
        for(Map.Entry<Arco,Integer> entry: mappaDirezioni.entrySet()) {
        	System.out.println( entry.getKey().toString() + "-->" + entry.getValue());
        }
    }

    /**
     * Crea una matrice costituita da valori che vanno da 0 alla vita massima del TamaGolem.
     * La somma dei valori di ogni riga ? uguale alla somma dei valori della rispettiva colonna.
     * Il valore massimo nella matrice ? sempre la vita massima del TamaGolem.
     * La diagonale ? costituita da zeri.
     * Se un valore della matrice ? maggiore di 0, allora il suo valore simmetrico rispetto alla diagonale ? 0 e viceversa.
     * In questo modo ogni valore diverso da 0 rappresenta la potenza con cui l'Elemento con indice pari a quello della riga
     * danneggia quello con indice pari a qullo della colonna.
     */
    public void generaEquilibrio(){
        Random rand = new Random();
        int n = Main.getNumeroElementi();
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
                                //Se il valore ? minore della vitaMax del TamaGolem aggiunge 1
                                if (m[i][j] < vitaMax) {
                                    m[i][j]++;
                                    break;
                                }
                                //Se il valore ? uguale alla vitaMax del TamaGolem crea una nuova matrice valida
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
                                //Se il valore ? minore della vitaMax del TamaGolem aggiunge 1
                                if (m[k][i] < vitaMax) {
                                    m[k][i]++;
                                    break;
                                }
                                //Se il valore ? uguale alla vitaMax del TamaGolem crea una nuova matrice valida
                                else if (m[k][i] == vitaMax) {
                                    m = creaMatriceValida(m, n);
                                }
                            }
                        }
                    }
                }
            //Continua il ciclo finch? non trova una matrice equilibrata
            } while (!isMatriceEquilibrata(m, n));

            //Scartato perch? risultavano troppi valori simili
            //Trova il valore minimo (diverso da 0) presente nella matrice e la sua posizione
            /*int valoreMin = vitaMax;
            int rigaValoreMin = 0, colonnaValoreMin = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (m[i][j] < valoreMin && m[i][j] != 0){
                        valoreMin = m[i][j];
                        rigaValoreMin = i;
                        colonnaValoreMin = j;
                    }
                }
            }*/

            //Trova il valore massimo presente nella matrice e la sua posizione
            int valoreMax = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (m[i][j] > valoreMax){
                        valoreMax = m[i][j];
                    }
                }
            }
            //Se il valoreMax ? uguale a vitaMax imposta valoreMaxUgualeAVitaMax = true
            if (valoreMax == vitaMax) {
                valoreMaxUgualeAVitaMax = true;
            }
            //Altrimenti aumenta di 1 un valore a caso della matrice, diverso da 0
            else{
                //Prende un numero casuale che indica una riga
                int valoreCasualeRiga = rand.nextInt(n);
                //Prende un secondo numero casuale che indica la colonna, va fino a n-1 perch? esclude la casella della diagonale
                int valoreCasualeColonna = rand.nextInt(n-1) + 1;
                int contatore = 0;
                //Trova il valoreCasualeColonna corrispondente escludendo la casella che si trova nella diagonale
                for (int i = 0; i < n; i++){
                    //Se il valore di i non ? quello dell'indice di colonna della riga presa in considerazione aumenta il contatore di 1
                    if (i != valoreCasualeRiga) contatore++;
                    //Quando arriva al valoreCasuale colonna corrispondente, lo riassegna
                    if (contatore == valoreCasualeColonna) valoreCasualeColonna = i;
                }
                //Se la casella trovata casualmente contine il valore 0 cambio gli indici, prendendo la casella in posizione simmetrica
                if (m[valoreCasualeRiga][valoreCasualeColonna] == 0){
                    int temp = valoreCasualeRiga;
                    valoreCasualeRiga = valoreCasualeColonna;
                    valoreCasualeColonna = temp;
                }
                m[valoreCasualeRiga][valoreCasualeColonna]++;
            }
        //Esce dal ciclo quando trova una matrice con valoreMax = vitaMax del TamaGolem
        }while (valoreMaxUgualeAVitaMax == false);

        //Stampa matrice elementi quando viene generata (per controllo valori)
        /*for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                System.out.printf("%3d ", m[i][j]);
            }
            System.out.println("");
        }
        Interazione.aCapo();*/

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
        //Continua il ciclo finch? non trova una matrice valida
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
     * Ritorna true se la somma dei valori di ogni riga ? uguale alla somma dei valori della rispettiva colonna.
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

    /**
     * dati 2 elementi fornisce il quantitativo di danno in base all'elemento predominante
     * @param primo primo elemento da confrontare
     * @param secondo secondo elemento da confrontare
     * @return ritorna il quantitativo di danni in base ai elementi
     */
    public int getDanni(Elemento primo, Elemento secondo) {
    	if(primo.equals(secondo)) {
    		System.out.println(String.format(ANNULLAMENTO,primo.getNome()));
    		return 0;
    	}
    	if(mappaDirezioni.containsKey(new Arco(primo,secondo))) {
    		System.out.println(String.format(MSG_INTERAZIONE_PIETRE, secondo.getNome(),primo.getNome()));
    		return mappaDirezioni.get(new Arco(primo,secondo)); 
    	}
    	else {
    		System.out.println(String.format(MSG_INTERAZIONE_PIETRE, primo.getNome(),secondo.getNome()));
    		return (-1)*mappaDirezioni.get(new Arco(secondo,primo)); 
    	}
    }
}
