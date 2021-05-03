package tamaGolem;

public class Elemento {
    private String nome;

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
}
