package grafo;

public class NodoVert {
    private Object elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;

    public NodoVert(Object x, NodoVert sv) {
        elem = x;
        sigVertice = sv;
    }

    public Object getElem() {
        return elem;
    }

    public NodoVert getSigVertice() {
        return sigVertice;
    }

    public NodoAdy getPrimerAdy() {
        return primerAdy;
    }

    public void setElem(Object x) {
        elem = x;
    }

    public void setSigVertice(NodoVert sv) {
        sigVertice = sv;
    }

    public void setPrimerAdy(NodoAdy pa) {
        primerAdy = pa;
    }
}