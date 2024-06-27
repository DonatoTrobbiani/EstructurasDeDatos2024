package jerarquicas.dinamicas;
import lineales.dinamicas.*;

public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = true;
        if (this.raiz == null) {
            //Si el arbol está vacío, pone elemNuevo en la raíz
            this.raiz = new NodoGen(elemNuevo, null, null);
        } else {
            //Si el arbol no está vacío, busca el padre
            NodoGen nodoPadre = obtenerNodo(this.raiz, elemPadre);
            
            if (nodoPadre != null) {
                NodoGen nodoNuevo = new NodoGen(elemNuevo, null, null);
                if (nodoPadre.getHijoIzquierdo() == null) {
                    nodoPadre.setHijoIzquierdo(nodoNuevo);
                } else {
                    NodoGen hijo = nodoPadre.getHijoIzquierdo();
                    while (hijo.getHermanoDerecho() != null) {
                        hijo = hijo.getHermanoDerecho();
                    }
                    hijo.setHermanoDerecho(nodoNuevo);
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoGen obtenerNodo(NodoGen nodo, Object buscado) {
        NodoGen resultado = null;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                resultado = nodo;
            } else {
                resultado = obtenerNodo(nodo.getHijoIzquierdo(), buscado);
                if (resultado == null) {
                    resultado = obtenerNodo(nodo.getHermanoDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public boolean insertarPorPosicion(Object elemNuevo, int posPadre) {
        boolean exito = true;
        if (posPadre == 1) {
            if (this.raiz == null) {
                this.raiz = new NodoGen(elemNuevo, null, null);
            } else {
                exito = false;
            }
        } else {
            exito = insertarPorPosicionAux(this.raiz, elemNuevo, posPadre - 1);
        }
        return exito;
    }

    private boolean insertarPorPosicionAux(NodoGen nodo, Object elemNuevo, int posPadre) {
        boolean exito = false;
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null && !exito) {
                posPadre--;
                if (posPadre == 0) {
                    NodoGen nodoNuevo = new NodoGen(elemNuevo, null, null);
                    nodoNuevo.setHermanoDerecho(hijo);
                    nodo.setHijoIzquierdo(nodoNuevo);
                    exito = true;
                } else {
                    exito = insertarPorPosicionAux(hijo, elemNuevo, posPadre);
                }
                hijo = hijo.getHermanoDerecho();
            }
        }
        return exito;
    }

    public boolean pertenece(Object elem) {
        return obtenerNodo(this.raiz, elem) != null;
    }

    public Lista ancestros(Object elem) {
        Lista lista = new Lista();
        ancestrosAux(this.raiz, elem, lista);
        return lista;
    }

    private boolean ancestrosAux(NodoGen nodo, Object elem, Lista lista) {
        boolean encontrado = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                encontrado = true;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && !encontrado) {
                    encontrado = ancestrosAux(hijo, elem, lista);
                    if (encontrado) {
                        lista.insertar(nodo.getElem(), 1);
                    }
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return encontrado;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public int altura() {
        return alturaAux(this.raiz);
    }

    private int alturaAux(NodoGen nodo) {
        int altura = -1;
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                int alturaHijo = alturaAux(hijo);
                if (alturaHijo > altura) {
                    altura = alturaHijo;
                }
                hijo = hijo.getHermanoDerecho();
            }
            altura++;
        }
        return altura;
    }

    public int nivel(Object elem) {
        return nivelAux(this.raiz, elem, 0);
    }

    private int nivelAux(NodoGen nodo, Object elem, int nivel) {
        int nivelElem = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                nivelElem = nivel;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && nivelElem == -1) {
                    nivelElem = nivelAux(hijo, elem, nivel + 1);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return nivelElem;
    }

    public Object padre(Object elem) {
        Object padre = null;
        if (this.raiz != null) {
            if (!this.raiz.getElem().equals(elem)) {
                padre = padreAux(this.raiz, elem);
            }
        }
        return padre;
    }

    private Object padreAux(NodoGen nodo, Object elem) {
        Object padre = null;
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null && padre == null) {
                if (hijo.getElem().equals(elem)) {
                    padre = nodo.getElem();
                } else {
                    padre = padreAux(hijo, elem);
                }
                hijo = hijo.getHermanoDerecho();
            }
        }
        return padre;
    }

    public Lista listarPreorden() {
        Lista lista = new Lista();
        listarPreordenAux(this.raiz, lista);
        return lista;
    }

    private void listarPreordenAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            //Visitar el nodo
            lista.insertar(nodo.getElem(), lista.longitud() + 1);

            //Llamado recursivo a los hijos de nodo
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                listarPreordenAux(hijo, lista);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public Lista listarInorden() {
        Lista lista = new Lista();
        listarInordenAux(this.raiz, lista);
        return lista;
    }

    private void listarInordenAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            //Llamado recursivo al hijo izquierdo de nodo
            NodoGen hijo = nodo.getHijoIzquierdo();
            if (hijo != null) {
                listarInordenAux(hijo, lista);
            }

            //Visitar el nodo
            lista.insertar(nodo.getElem(), lista.longitud() + 1);

            //Llamado recursivo a los otros hijos de nodo
            if (nodo.getHijoIzquierdo() != null) {
                hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, lista);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPosorden() {
        Lista lista = new Lista();
        listarPosordenAux(this.raiz, lista);
        return lista;
    }

    private void listarPosordenAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            //Llamado recursivo a los hijos de nodo
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                listarPosordenAux(hijo, lista);
                hijo = hijo.getHermanoDerecho();
            }

            //Visitar el nodo
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
        }
    }

    public Lista listarPorNiveles() {
        Lista lista = new Lista();
        Cola q = new Cola();
        if (this.raiz != null) {
            q.poner(this.raiz);
            while (!q.esVacia()) {
                NodoGen nodo = (NodoGen) q.obtenerFrente();
                q.sacar();
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    q.poner(hijo);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return lista;
    }

    public ArbolGen clone() {
        ArbolGen clon = new ArbolGen();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }

    private NodoGen cloneAux(NodoGen nodo) {
        NodoGen clon = null;
        if (nodo != null) {
            clon = new NodoGen(nodo.getElem(), null, null);
            NodoGen hijo = nodo.getHijoIzquierdo();
            NodoGen ultimoClon = clon;
            while (hijo != null) {
                ultimoClon.setHijoIzquierdo(cloneAux(hijo));
                ultimoClon = ultimoClon.getHijoIzquierdo();
                hijo = hijo.getHermanoDerecho();
            }
        }
        return clon;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen nodo) {
        String cadena = "";
        if (nodo != null) {
            //Visita el nodo
            cadena += nodo.getElem().toString() + " -> ";
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                cadena += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }
            
            //Comienza recorrido de los hijos de nodo llamando recursivamente para que cada hijo agregue su subcadena a la general
            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                cadena += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return cadena;
    }

    public boolean verificarCamino(Lista lista){
        boolean exito = false;
        exito = verificarCaminoAux(lista, this.raiz, 1);
        return exito;
    }

    private boolean verificarCaminoAux(Lista lista, NodoGen nodo, int posElemActual){
        boolean exito = false;
        if (nodo != null) {
            NodoGen aux = nodo;
            while (aux != null && !exito) {
                if (aux.getElem().equals(lista.recuperar(posElemActual))) {
                    if (lista.longitud() == posElemActual) {
                        exito = true;
                    }else{
                        exito = verificarCaminoAux(lista, aux.getHijoIzquierdo(), posElemActual+1);
                    }
                }
                aux = aux.getHermanoDerecho();
            }
        }
        return exito;
    }

    public Lista listarEntreNiveles(int niv1, int niv2) {
        Lista lista = new Lista();
        listarEntreNivelesAux(this.raiz, lista, niv1, niv2, 0);
        return lista;
    }

    private void listarEntreNivelesAux(NodoGen nodo, Lista lista, int niv1, int niv2, int nivel) {
        if (nodo != null && nivel <= niv2) {
            if (nivel >= niv1 && nivel <= niv2) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                listarEntreNivelesAux(hijo, lista, niv1, niv2, nivel + 1);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public boolean eliminarDescendientes(Object elem) {
        boolean exito = false;
        if (this.raiz != null) {
            if (this.raiz.getElem().equals(elem)) {
                this.raiz = null;
                exito = true;
            } else {
                exito = eliminarDescendientesAux(this.raiz, null, elem);
            }
        }
        return exito;
    }

    private boolean eliminarDescendientesAux(NodoGen nodo, NodoGen anterior, Object elem) {
        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                if(anterior.getHermanoDerecho().equals(nodo)) {
                    anterior.setHermanoDerecho(nodo.getHermanoDerecho());
                    exito = true;
                } else {
                    anterior.setHijoIzquierdo(nodo.getHermanoDerecho());
                    exito = true;
                }
            } else {
                anterior = nodo;
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    if (eliminarDescendientesAux(hijo, anterior, elem)) {
                        hijo = null;
                    } else {
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
        return exito;
    }

    public int orden() {
        return ordenAux(this.raiz);
    }

    private int ordenAux(NodoGen nodo) {
        int orden = 0;
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            
            while (hijo != null) {
                orden++;
                hijo = hijo.getHermanoDerecho();
            }
            
            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                int gradoHijo = ordenAux(hijo);
                orden = Math.max(orden, gradoHijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return orden;
    }

    public void insertarEnPosicion(Object elem, NodoGen padre, int pos) {
        NodoGen nodo = obtenerNodo(this.raiz, padre.getElem());
        if (nodo != null) {
            insertarEnPosicionAux(nodo, elem, pos);
        }
    }

    private void insertarEnPosicionAux(NodoGen nodo, Object elemNuevo, int pos) {
        if (pos == 1) {
            NodoGen nuevo = new NodoGen(elemNuevo, null, nodo.getHijoIzquierdo());
            nodo.setHijoIzquierdo(nuevo);
        } else {
            nodo = nodo.getHijoIzquierdo();
            while (nodo.getHermanoDerecho() != null && pos > 1) {
                nodo = nodo.getHermanoDerecho();
                pos--;
            }
            if (pos == 1) {
                NodoGen nuevo = new NodoGen(elemNuevo, null, nodo.getHermanoDerecho());
                nodo.setHermanoDerecho(nuevo);
            } else {
                NodoGen nuevo = new NodoGen(elemNuevo, null, null);
                nodo.setHermanoDerecho(nuevo);
            }
        }
    }

    public boolean esSobrino(Object elem, Object tio) {
        boolean exito = false;
        NodoGen nodo = obtenerNodo(this.raiz, tio);
        if (nodo != null) {
            while (nodo != null && !exito){
                nodo = nodo.getHermanoDerecho();
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && !exito) {
                    if (hijo.getElem().equals(elem)) {
                        exito = true;
                    }
                    hijo = hijo.getHermanoDerecho();
                }
                nodo = nodo.getHermanoDerecho();
            }
        }
        return exito;
    }

    public boolean jerarquizar(Object elem) {
        boolean exito = false;
        exito = jerarquizarAux(elem, this.raiz, null);
        return exito;
    }

    private boolean jerarquizarAux(Object elem, NodoGen nodo, NodoGen padre) {
        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                if (!(padre == this.raiz || padre == null)) {
                    if (padre.getHijoIzquierdo().equals(nodo)) {
                        padre.setHijoIzquierdo(nodo.getHermanoDerecho());
                        nodo.setHermanoDerecho(padre.getHermanoDerecho());
                        padre.setHermanoDerecho(nodo);
                    } else {
                        NodoGen aux = padre.getHijoIzquierdo();
                        while (aux.getHermanoDerecho() != nodo) {
                            aux = aux.getHermanoDerecho();
                        }
                        aux.setHermanoDerecho(nodo.getHermanoDerecho());
                        nodo.setHermanoDerecho(padre.getHermanoDerecho());
                        padre.setHermanoDerecho(nodo);
                    }
                    exito = true;
                }
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && !exito) {
                    exito = jerarquizarAux(elem, hijo, nodo);
                    hijo = hijo.getHermanoDerecho();
                }
            }   
        }
        return exito;
    }
}