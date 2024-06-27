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
            int[] posPadreAux = {posPadre-1};
            NodoArbol nodoPadre = obtenerPosicion(this.raiz, posPadreAux);

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

    private NodoArbol obtenerPosicion(NodoArbol nodo, int[] pos) {
        //Metodo PRIVADO que busca un nodo por su posicion en preorden
        NodoArbol resultado = null;
        if (nodo != null && pos[0] >= 0) {
            if (pos[0] == 0) {
                resultado = nodo;
            } else {
                pos[0]--;
                resultado = obtenerPosicion(nodo.getIzquierdo(), pos);
                if (resultado == null) {
                    resultado = obtenerPosicion(nodo.getDerecho(), pos);
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
                if (nodoActual.getDerecho() != null) {
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
            boolean encontrado = false;
            if (nodo.getIzquierdo() != null && nodo.getIzquierdo().getElem().equals(elem)) {
                encontrado = true;
            } else if (nodo.getDerecho() != null && nodo.getDerecho().getElem().equals(elem)) {
                encontrado = true;
            }
            if (encontrado) {
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

    public Lista frontera() {
        //Retorna una lista con los elementos de la frontera del arbol
        Lista lista = new Lista();
        fronteraAux(this.raiz, lista);
        return lista;
    }

    private void fronteraAux(NodoArbol nodo, Lista lista) {
        //Metodo PRIVADO recursivo porque su parametro es de tipo NodoArbol
        if (nodo != null) {
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            } else {
                fronteraAux(nodo.getIzquierdo(), lista);
                fronteraAux(nodo.getDerecho(), lista);
            }
        }
    }

    public Lista obtenerAncestros(Object elem) {
        //Retorna una lista con los ancestros de un elemento
        Lista lista = new Lista();
        obtenerAncestrosAux(this.raiz, elem, lista);
        return lista;
    }

    private boolean obtenerAncestrosAux(NodoArbol nodo, Object elem, Lista lista) {
        //Metodo PRIVADO recursivo porque su parametro es de tipo NodoArbol
        boolean encontrado = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                encontrado = true;
            } else {
                if (obtenerAncestrosAux(nodo.getIzquierdo(), elem, lista) || obtenerAncestrosAux(nodo.getDerecho(), elem, lista)) {
                    lista.insertar(nodo.getElem(), lista.longitud() + 1);
                    encontrado = true;
                }
            }
        }
        return encontrado;
    }

    public Lista obtenerDescendientes(Object elem) {
        //Retorna una lista con los descendientes de un elemento
        Lista lista = new Lista();
        obtenerDescendientesAux(this.raiz, elem, lista);
        return lista;
    }

    private void obtenerDescendientesAux(NodoArbol nodo, Object elem, Lista lista) {
        //Metodo PRIVADO recursivo porque su parametro es de tipo NodoArbol
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                listarPreordenAux(nodo.getIzquierdo(), lista);
                listarPreordenAux(nodo.getDerecho(), lista);
            } else {
                obtenerDescendientesAux(nodo.getIzquierdo(), elem, lista);
                obtenerDescendientesAux(nodo.getDerecho(), elem, lista);
            }
        }
    }

    public boolean verificarPatron(Lista patron) {
        int pos = 1;
        return verificarPatronAux(this.raiz, patron, pos, patron.longitud());
    }

    private boolean verificarPatronAux(NodoArbol nodo, Lista lista, int pos, int longitud) {
        boolean resultado = false;
        if (nodo != null) {
            if (nodo.getElem().equals(lista.recuperar(pos))) {
                if(pos == longitud) {
                    resultado = true;
                } else {
                    pos++;
                    resultado = verificarPatronAux(nodo.getIzquierdo(), lista, pos, longitud);
                    if (!resultado) {
                        resultado = verificarPatronAux(nodo.getDerecho(), lista, pos, longitud);
                    }
                    if(!resultado) {
                        pos--;
                    }
                }
            }
        }
        return resultado;
    }

    public ArbolBin clonarInvertido() {
        ArbolBin clon = new ArbolBin();
        clon.raiz = clonarInvertidoAux(this.raiz);
        return clon;
    }

    private NodoArbol clonarInvertidoAux(NodoArbol nodo) {
        NodoArbol clon = null;
        if (nodo != null) {
            clon = new NodoArbol(nodo.getElem(), clonarInvertidoAux(nodo.getDerecho()), clonarInvertidoAux(nodo.getIzquierdo()));
        }
        return clon;
    }

    public boolean equals(ArbolBin otro) {
        return equalsAux(this.raiz, otro.raiz);
    }

    private boolean equalsAux(NodoArbol nodo1, NodoArbol nodo2) {
        boolean resultado = false;
        if(nodo1 == null && nodo2 == null) {
            resultado = true;
        } else if (nodo1 != null && nodo2 != null) {
            resultado = nodo1.getElem().equals(nodo2.getElem()) && equalsAux(nodo1.getIzquierdo(), nodo2.getIzquierdo()) && equalsAux(nodo1.getDerecho(), nodo2.getDerecho());
        }
        return resultado;
    }

    public boolean estaRepetido(Object elem) {
        int[] contador = {0};
        estaRepetidoAux(elem, this.raiz, contador);
        return contador[0] >= 2;
    }

    private void estaRepetidoAux(Object elem, NodoArbol nodo, int[] contador) {
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                contador[0]++;
            }
            if (contador[0] < 2) {
                estaRepetidoAux(elem, nodo.getIzquierdo(), contador);
                if (contador[0] < 2) {    
                    estaRepetidoAux(elem, nodo.getDerecho(), contador);
                }
            }
        }
    }
}