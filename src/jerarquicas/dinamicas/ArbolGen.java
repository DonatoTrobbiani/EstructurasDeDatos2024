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
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && resultado == null) {
                    resultado = obtenerNodo(hijo, buscado);
                    hijo = hijo.getHermanoDerecho();
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
}