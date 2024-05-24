package jerarquicas.dinamicas;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
    }

        /*
     * Inserta elemNuevo como hijo del primer nodo encontrado en preorden igual a elemPdare, como hijo izquierdo (I) o derecho (D) segun lo indique el parametro lugar.
     */
    public boolean insertar(Object elemNuevo, Object elemPadre, char posHijo) {
        boolean exito = true;

        if (this.raiz == null) {
            //Si el arbol está vacío, pone elemNuevo en la raíz
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            //Si el arbol no está vacío, busca el padre
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);

            //Si padre existe y lugar no está ocupado lo pone, sino da error
            if (nodoPadre != null) {
                if (posHijo == 'I' && nodoPadre.getIzquierdo() == null) {
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (posHijo == 'D' && nodoPadre.getDerecho() == null) {
                        nodoPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                    } else {
                        exito = false;
                    }
            } else {
                exito = false;
            }
        }
        return exito;
    }
    
    private NodoArbol obtenerNodo(NodoArbol nodo, Object elem) {
        //Metodo PRIVADO que busca un elemento y devuelve el nodo que lo contiene. Si no lo encuentra devuelve null.
        
        NodoArbol resultado = null;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                //Si el nodo actual contiene el elemento buscado, lo devuelve
                resultado = nodo;
            } else {
                //Si no lo contiene, busca en el hijo izquierdo
                resultado = obtenerNodo(nodo.getIzquierdo(), elem);
                //Si no lo encuentra en el hijo izquierdo, busca en el hijo derecho
                if (resultado == null) {
                    resultado = obtenerNodo(nodo.getDerecho(), elem);
                }
            }
        }
        return resultado;
    }    
    
    public boolean insertarPorPosicion(Object elemNuevo, int posPadre, char posHijo) {
        boolean exito = true;
        
        if (this.raiz == null) {
            //Si el arbol está vacío, pone elemNuevo en la raíz
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            //Si el arbol no está vacío, busca el padre
            NodoArbol nodoPadre = obtenerNodoPorPosicion(this.raiz, posPadre);

            //Si padre existe y lugar no está ocupado lo pone, sino da error
            if (nodoPadre != null) {
                if (posHijo == 'I' && nodoPadre.getIzquierdo() == null) {
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (posHijo == 'D' && nodoPadre.getDerecho() == null) {
                    nodoPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodoPorPosicion(NodoArbol nodo, int pos) {
        //Metodo PRIVADO que busca un nodo por su posicion en preorden
        NodoArbol resultado = null;
        if (nodo != null) {
            if (pos == 1) {
                resultado = nodo;
            } else {
                resultado = obtenerNodoPorPosicion(nodo.getIzquierdo(), pos - 1);
                if (resultado == null) {
                    resultado = obtenerNodoPorPosicion(nodo.getDerecho(), pos - 1);
                }
            }
        }
        return resultado;
    }

    public Lista listarPreorden() {
        //Retorna una lista con los elementos del arbol en PREORDEN
        Lista lista = new Lista();
        listarPreordenAux(this.raiz, lista);
        return lista;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lista) {
        //Metodo PRIVADO recursivo porquesu parametro es de tipo NodoArbol
        if (nodo != null) {
            //Visita el elemento del nodo
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            //Recorre a sus hijos en preorden
            listarPreordenAux(nodo.getIzquierdo(), lista);
            listarPreordenAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarInorden() {
        //Retorna una lista con los elementos del arbol en INORDEN
        Lista lista = new Lista();
        listarInordenAux(this.raiz, lista);
        return lista;
    }

    private void listarInordenAux(NodoArbol nodo, Lista lista) {
        //Metodo PRIVADO recursivo porque su parametro es de tipo NodoArbol
        if (nodo != null) {
            //Recorre a su hijo izquierdo en inorden
            listarInordenAux(nodo.getIzquierdo(), lista);
            //Visita el elemento del nodo
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            //Recorre a su hijo derecho en inorden
            listarInordenAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarPosorden() {
        //Retorna una lista con los elementos del arbol en POSORDEN
        Lista lista = new Lista();
        listarPosordenAux(this.raiz, lista);
        return lista;
    }

    private void listarPosordenAux(NodoArbol nodo, Lista lista) {
        //Metodo PRIVADO recursivo porque su parametro es de tipo NodoArbol
        if (nodo != null) {
            //Recorre a sus hijos en posorden
            listarPosordenAux(nodo.getIzquierdo(), lista);
            listarPosordenAux(nodo.getDerecho(), lista);
            //Visita el elemento del nodo
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
        }
    }

    public Lista listarPorNiveles() {
        //Retorna una lista con los elementos del arbol en NIVELES
        Lista lista = new Lista();
        listarPorNivelesAux(this.raiz, lista);
        return lista;
    }

    private void listarPorNivelesAux(NodoArbol nodo, Lista lista) {
        //Metodo PRIVADO recursivo porque su parametro es de tipo NodoArbol
        Cola cola = new Cola();
        if (nodo != null) {
            cola.poner(nodo);
            while (!cola.esVacia()) {
                NodoArbol nodoActual = (NodoArbol) cola.obtenerFrente();
                cola.sacar();
                lista.insertar(nodoActual.getElem(), lista.longitud() + 1);
                if (nodoActual.getIzquierdo() != null) {
                    cola.poner(nodoActual.getIzquierdo());
                }
                if (nodo.getDerecho() != null) {
                    cola.poner(nodoActual.getDerecho());
                }
            }
        }
    }

    public boolean esVacio() {
        //Retorna true si el arbol está vacío, false en caso contrario
        return this.raiz == null;
    }

    public void vaciar() {
        //Vacia el arbol
        this.raiz = null;
    }

    public int altura() {
        //Retorna la altura del arbol
        return alturaAux(this.raiz);
    }

    private int alturaAux(NodoArbol nodo) {
        int alturaIzq, alturaDer, altura;
        if (nodo == null) {
            altura = -1;
        } else {
            alturaIzq = alturaAux(nodo.getIzquierdo());
            alturaDer = alturaAux(nodo.getDerecho());
            altura = 1 + Math.max(alturaIzq, alturaDer);
        }
        return altura;
    }

    public int nivel(Object elem) {
        //Retorna el nivel de un elemento en el arbol
        return nivelAux(this.raiz, elem, 0);
    }

    private int nivelAux(NodoArbol nodo, Object elem, int nivel) {
        int resultado = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                resultado = nivel;
            } else {
                resultado = nivelAux(nodo.getIzquierdo(), elem, nivel + 1);
                if (resultado == -1) {
                    resultado = nivelAux(nodo.getDerecho(), elem, nivel + 1);
                }
            }
        }
        return resultado;
    }

    public Object padre(Object elem) {
        //Retorna el padre de un elemento
        return padreAux(this.raiz, elem);
    }

    private Object padreAux(NodoArbol nodo, Object elem) {
        Object resultado = null;
        if (nodo != null) {
            if (nodo.getIzquierdo().getElem().equals(elem) || nodo.getDerecho().getElem().equals(elem)) {
                resultado = nodo.getElem();
            } else {
                resultado = padreAux(nodo.getIzquierdo(), elem);
                if (resultado == null) {
                    resultado = padreAux(nodo.getDerecho(), elem);
                }
            }
        }
        return resultado;
    }

    public ArbolBin clone() {
        //Retorna un arbol clon del actual
        ArbolBin clon = new ArbolBin();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }

    private NodoArbol cloneAux(NodoArbol nodo) {
        //Metodo PRIVADO recursivo porque su parametro es de tipo NodoArbol
        NodoArbol clon = null;
        if (nodo != null) {
            clon = new NodoArbol(nodo.getElem(), cloneAux(nodo.getIzquierdo()), cloneAux(nodo.getDerecho()));
        }
        return clon;
    }

    public String toString() {
        //Retorna un string con los elementos del arbol en PREORDEN
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoArbol nodo) {
        //Metodo PRIVADO recursivo porque su parametro es de tipo NodoArbol
        String resultado = "";
        if (nodo != null) {
            resultado += "Nodo: " + nodo.getElem();
            resultado += " HI: " + (nodo.getIzquierdo() != null ? nodo.getIzquierdo().getElem() : "-");
            resultado += " HD: " + (nodo.getDerecho() != null ? nodo.getDerecho().getElem() : "-");
            resultado += "\n";
            resultado += toStringAux(nodo.getIzquierdo());
            resultado += toStringAux(nodo.getDerecho());
        }
        return resultado;
    }
}