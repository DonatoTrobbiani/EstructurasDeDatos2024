package lineales.dinamicas;

public class Cola {
    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object elem) {
        Nodo nuevo = new Nodo(elem, null);
        if (this.fin != null) {
            this.fin.setEnlace(nuevo);
        } else {
            this.frente = nuevo;
        }
        this.fin = nuevo;
        return true;
    }

    public boolean sacar() {
        boolean exito = false;
        if (this.frente != null) {
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
            exito = true;
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object elem = null;
        if (this.frente != null) {
            elem = this.frente.getElem();
        }
        return elem;
    }

    public boolean esVacia() {
        return this.frente == null;
    }

    public void vaciar() {
        this.frente = null;
        this.fin = null;
    }

    public Cola clone() {
        Cola clon = new Cola();
        Nodo aux = this.frente;
        if (aux != null) {
            clon.frente = new Nodo(aux.getElem(), null);
            aux = aux.getEnlace();
            Nodo auxClon = clon.frente;
            while (aux != null) {
                auxClon.setEnlace(new Nodo(aux.getElem(), null));
                aux = aux.getEnlace();
                auxClon = auxClon.getEnlace();
            }
            clon.fin = auxClon;
        }
        return clon;
    }


    public String toString() {
        String cadena = "[ ";
        if (this.frente != null) {
            Nodo aux = this.frente;
            while (aux != null) {
                cadena += aux.getElem().toString() + " ";
                aux = aux.getEnlace();
            }
        }
        cadena += "]";
        return cadena;
    }
}