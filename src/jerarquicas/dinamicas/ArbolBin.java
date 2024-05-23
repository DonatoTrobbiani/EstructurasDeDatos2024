package jerarquicas.dinamicas;

public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
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
    
    /*
     * Inserta elemNuevo como hijo del primer nodo encontrado en preorden igual a elemPdare, como hijo izquierdo (I) o derecho (D) segun lo indique el parametro lugar.
     */
    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        boolean exito = true;

        if (this.raiz == null) {
            //Si el arbol está vacío, pone elemNuevo en la raíz
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            //Si el arbol no está vacío, busca el padre
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);

            //Si padre existe y lugar no está ocupado lo pone, sino da error
            if (nodoPadre != null) {
                if (lugar == 'I' && nodoPadre.getIzquierdo() == null) {
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (lugar == 'D' && nodoPadre.getDerecho() == null) {
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
}