package grafo;

public class NodoAdy {
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Object etiqueta;

    public NodoAdy(NodoVert v, NodoAdy sa, Object et) {
        vertice = v;
        sigAdyacente = sa;
        etiqueta = et;
    }

    public NodoVert getVertice() {
        return vertice;
    }

    public NodoAdy getSigAdyacente() {
        return sigAdyacente;
    }

    public Object getEtiqueta() {
        return etiqueta;
    }

    public void setVertice(NodoVert v) {
        vertice = v;
    }

    public void setSigAdyacente(NodoAdy sa) {
        sigAdyacente = sa;
    }

    public void setEtiqueta(Object et) {
        etiqueta = et;
    }
}
