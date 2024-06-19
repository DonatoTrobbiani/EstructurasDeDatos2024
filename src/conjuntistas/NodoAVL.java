package conjuntistas;

public class NodoAVL {
    private Comparable elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Comparable elem, NodoAVL izquierdo, NodoAVL derecho) {
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public Comparable getElem() {
        return this.elem;
    }

    public NodoAVL getIzquierdo() {
        return this.izquierdo;
    }

    public NodoAVL getDerecho() {
        return this.derecho;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }

    public int getAltura() {
        return this.altura;
    }

    public void recalcularAltura() {
        int altIzq = this.izquierdo != null ? this.izquierdo.getAltura() : -1;
        int altDer = this.derecho != null ? this.derecho.getAltura() : -1;
        this.altura = 1 + Math.max(altIzq, altDer);
    }
}
