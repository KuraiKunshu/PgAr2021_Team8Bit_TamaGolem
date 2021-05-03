package tamaGolem;

public class Elemento {
    private String nome;
    private static final String[] ELEMENTI={"Fuoco","Acqua","Eletro","Terra","Luce","Morte","Natura","Lotta","Veleno","Unknown"};

    public Elemento(int id) {
        this.nome = ELEMENTI[id];
    }

    /**
     * ritorna il nome dell'elemento
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * imposta il nome dell'elemento
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * ritorna l'array costante dei nomi di tutti i possibili elementi
     * @return
     */
    public static String[] getElementi(){
        return ELEMENTI;
    }

    public static int getNumeroElementi(){
        return ELEMENTI.length;
    }
}
