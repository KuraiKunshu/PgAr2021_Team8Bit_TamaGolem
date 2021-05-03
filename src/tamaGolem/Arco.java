package tamaGolem;

public class Arco {
    private Elemento elementoDiPartenza;
    private Elemento elementoDiArrivo;

    public Arco(Elemento elementoDiPartenza, Elemento elementoDiArrivo) {
        this.elementoDiPartenza = elementoDiPartenza;
        this.elementoDiArrivo = elementoDiArrivo;
    }

    /**
     * ritorna l'oggetto dell'elemento di partenza dell'arco
     * @return
     */
    public Object getElementoDiPartenza() {
        return elementoDiPartenza;
    }

    /**
     * imposta l'oggetto dell'elemento di partenza dell'arco
     * @param elementoDiPartenza
     */
    public void setElementoDiPartenza(Elemento elementoDiPartenza) {
        this.elementoDiPartenza = elementoDiPartenza;
    }

    /**
     * ritorna l'oggetto dell'elemento di arrivo dell'arco
     * @return
     */
    public Object getElementoDiArrivo() {
        return elementoDiArrivo;
    }

    /**
     * imposta l'oggetto dell'elemento di arrivo dell'arco
     * @param elementoDiArrivo
     */
    public void setElementoDiArrivo(Elemento elementoDiArrivo) {
        this.elementoDiArrivo = elementoDiArrivo;
    }

    /**
     * dato un oggetto controllo che siano entrambi della classe Arco, e poi confronto i 2 metodi hashCode.
     * se sono uguali ritorna true
     * @param Obj
     * @return
     */
    @Override
    public boolean equals(Object Obj){
        if(Obj.getClass().equals(this.getClass())) if (this.hashCode() == Obj.hashCode()) return true;
        return false;
    }

    /**
     * ritorna una stringa formata da elementodiArrivo:elementodiPartenza
     * @return
     */
    @Override
    public String toString(){
        return this.elementoDiArrivo.getNome() + ":" + this.elementoDiPartenza.getNome();
    }

    /**
     * dati i 2 elementi dell'oggetto, calcola l'hashcode dei nomi degli elementi(che sono stringe) e li somma.
     * cosi anche se l'elemento di arrivo e di partenza sono invertiti, restituisce comunque un valore equivalente
     * @return
     */
    @Override
    public int hashCode(){
        return this.elementoDiArrivo.getNome().hashCode()+this.elementoDiPartenza.getNome().hashCode();
    }
}
