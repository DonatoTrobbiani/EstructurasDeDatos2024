package lineales.dinamicas;

public class Lista {
    private Nodo cabecera;

    public Lista() {
        this.cabecera = null;
    }

    public boolean instertar(Object elem, int pos) {
        //Inserta el elemento nuevo en la posición pos
        //Detecta y reporta error de posición inválida
        boolean exito = true;
        
        if (pos < 1 || pos > this.longitud() + 1) {
            exito = false;
        } else {
            if (pos == 1) //Crea un nuevo nodo y lo enlaza en la cabecera
            {
                this.cabecera = new Nodo(elem, this.cabecera);
            } else { //Avanza hasta el elemento en la posición pos - 1
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                //Crea el nodo y lo enlaza
                Nodo nuevo = new Nodo(elem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }
        //Nunca hay error de lista llena, entonces devuelve true
        return exito;
    }
    
    public boolean eliminar(int pos) {
        boolean exito = true;
        if (pos < 1 || pos > this.longitud()) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
        }
        return exito;
    }

    public Object recuperar(int pos) {
        Object elem = null;
        if (pos >= 1 && pos <= this.longitud()) {
            Nodo aux = this.cabecera;
            int i = 1;
            while (i < pos) {
                aux = aux.getEnlace();
                i++;
            }
            elem = aux.getElem();
        }
        return elem;
    }

    public int localizar(Object elem) {
        int pos = -1;
        Nodo aux = this.cabecera;
        int i = 1;
        while (aux != null && pos == -1) {
            if (aux.getElem().equals(elem)) {
                pos = i;
            } else {
                aux = aux.getEnlace();
                i++;
            }
        }
        return pos;
    }

    public void vaciar() {
        this.cabecera = null;
    }

    public boolean esVacia() {
        return this.cabecera == null;
    }

    public Lista clonar() {
        Lista clon = new Lista();
        Nodo aux = this.cabecera;
        if (aux != null) {
            clon.cabecera = new Nodo(aux.getElem(), null);
            aux = aux.getEnlace();
            Nodo auxClon = clon.cabecera;
            while (aux != null) {
                auxClon.setEnlace(new Nodo(aux.getElem(), null));
                aux = aux.getEnlace();
                auxClon = auxClon.getEnlace();
            }
        }
        return clon;
    }

    public int longitud() {
        //Recorre la lista contando los nodos
        int longitud = 0;
        Nodo aux = this.cabecera;
        while (aux != null) {
            longitud++;
            aux = aux.getEnlace();
        }
        return longitud;
    }

    public String toString() {
        String cadena = "[ ";
        Nodo aux = this.cabecera;
        while (aux != null) {
            cadena += aux.getElem().toString() + " ";
            aux = aux.getEnlace();
        }
        cadena += "]";
        return cadena;
    }
}