package lineales.dinamicas;

public class Lista {
    private Nodo cabecera;

    public Lista() {
        this.cabecera = null;
    }

    public boolean insertar(Object elem, int pos) {
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

    public void invertir() {
        Nodo anterior = null;
        Nodo actual = this.cabecera;
        while (actual != null) {
            Nodo siguiente = actual.getEnlace();
            actual.setEnlace(anterior);
            anterior = actual;
            actual = siguiente;
        }
        this.cabecera = anterior;
    }

    public void eliminarApariciones(Object x) {
        Nodo aux = this.cabecera;
        Nodo ant = null;
        while (aux != null) {
            if (aux.getElem().equals(x)) {
                if (ant == null) {
                    this.cabecera = aux.getEnlace();
                } else {
                    ant.setEnlace(aux.getEnlace());
                }
            } else {
                ant = aux;
            }
            aux = aux.getEnlace();
        }
    }

    public Lista obtenerMultiplos(int num) {
        Lista lista = new Lista();
        if (num > 0) {
            Nodo aux = this.cabecera.getEnlace().getEnlace().getEnlace();
            while (aux != null) {
                lista.insertar(aux.getElem(), lista.longitud() + 1);
                aux = aux.getEnlace().getEnlace().getEnlace();
            }
        }
        return lista;
    }

    public void agregarElemento(Object nuevo, int x) {
        if (x > 0) {
            
            Nodo nodoNuevo = new Nodo(nuevo, null);
            Nodo aux = this.cabecera;

            nodoNuevo.setEnlace(aux);
            this.cabecera = nodoNuevo;

            int contador = 1;
            while (aux != null) {
                nodoNuevo = new Nodo(nuevo, null);
                if(contador % x == 0) {
                    nodoNuevo.setEnlace(aux.getEnlace());
                    aux.setEnlace(nodoNuevo);
                    aux = nodoNuevo.getEnlace();
                } else {
                    aux = aux.getEnlace();
                }
                contador++;
            }
        }
    }

    public boolean moverAAnteultimaPosesion(int pos) {
        boolean exito = false;
        if (pos < longitud()) {
            Nodo aux = this.cabecera;
            Nodo ant = null;
            while (pos > 1) {
                ant = aux;
                aux.getEnlace();
                pos--;
            }
            ant.setEnlace(aux.getEnlace());
            while (ant.getEnlace() != null) {
                ant = ant.getEnlace();
            }
            ant.setEnlace(aux);
            exito = true;
        } else if (pos == longitud()) {
            exito = true;
        }
        return exito;
    }
}