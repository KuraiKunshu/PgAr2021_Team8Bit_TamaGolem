package tamaGolem;

public class Elemento {
    private String nome;
    private static final String[] ELEMENTI={"Fuoco","Acqua","Eletro","Terra","Luce","Morte","Natura","Lotta","Veleno","Unknown"};
    //private static final String[] ELEMENTI={"Goku","Naruto","Yoda","Shrek","Tarzan","Spongebob","Obama","Superman","Batman","Trump"};

    public Elemento(int id) {
        this.nome = ELEMENTI[id];
    }

    public Elemento(String nome) {
        this.nome = nome;
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

    /**
     * ritorna il numero massimo di elementi dell'array.
     * @return
     */
    public static int getNumeroElementi(){
        return ELEMENTI.length;
    }

    /**
     * confronta un oggetto java con un oggetto Elemento
     * @param obj oggetto java
     * @return ritorna true se i 2 oggetti toString coincidono
     */
    @Override
    public boolean equals(Object obj){
        return this.toString().equals(obj.toString());
    }

    /**
     * ritorna in formato String la classe
     * @return ritorna il paramentro nome di elemento
     */
    @Override
    public String toString(){
        return this.getNome();
    }
}
