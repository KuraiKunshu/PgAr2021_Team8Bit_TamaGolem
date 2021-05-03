package tamaGolem;

public class Arco {
    private Object elementoDiPartenza;
    private Object elementoDiArrivo;

    public Arco(Object elementoDiPartenza, Object elementoDiArrivo) {
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
    public void setElementoDiPartenza(Object elementoDiPartenza) {
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
    public void setElementoDiArrivo(Object elementoDiArrivo) {
        this.elementoDiArrivo = elementoDiArrivo;
    }
}
