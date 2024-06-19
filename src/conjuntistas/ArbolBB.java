package conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolBB {
    
    private NodoABB raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    public boolean pertenece(Comparable elem) {
        return perteneceAux(this.raiz, elem);
    }

    private boolean perteneceAux(NodoABB nodo, Comparable elem) {
        boolean res = false;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem()) == 0) {
                res = true;
            } else {
                if (elem.compareTo(nodo.getElem()) < 0) {
                    res = perteneceAux(nodo.getIzquierdo(), elem);
                } else {
                    res = perteneceAux(nodo.getDerecho(), elem);
                }
            }
        }
        return res;
    }

    public boolean insertar(Comparable elem) {
        return insertarAux(this.raiz, elem);
    }

    private boolean insertarAux(NodoABB nodo, Comparable elem) {
        boolean exito = true;
        if (nodo == null) {
            this.raiz = new NodoABB(elem, null, null);
        } else {
            if (elem.compareTo(nodo.getElem()) == 0) {
                exito = false;
            } else {
                if (elem.compareTo(nodo.getElem()) < 0) {
                    if (nodo.getIzquierdo() == null) {
                        nodo.setIzquierdo(new NodoABB(elem, null, null));
                    } else {
                        exito = insertarAux(nodo.getIzquierdo(), elem);
                    }
                } else {
                    if (nodo.getDerecho() == null) {
                        nodo.setDerecho(new NodoABB(elem, null, null));
                    } else {
                        exito = insertarAux(nodo.getDerecho(), elem);
                    }
                }
            }
        }
        return exito;
    }

    public boolean eliminar(Comparable elem) {
        return eliminarAux(this.raiz, null, elem);
    }

    private boolean eliminarAux(NodoABB nodo, NodoABB padre, Comparable elem) {
        boolean exito = false;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem()) == 0) {
                eliminarAuxNuevo(nodo, padre);
            } else {
                padre = nodo;
                if (elem.compareTo(nodo.getElem()) < 0) {
                    exito = eliminarAux(nodo.getIzquierdo(), padre, elem);
                } else {
                    exito = eliminarAux(nodo.getDerecho(), padre, elem);
                }
            }
        }
        return exito;
    }

    private boolean eliminarAuxNuevo(NodoABB nodo, NodoABB padre) {
        boolean exito = false;
        NodoABB HI = nodo.getIzquierdo();
        NodoABB HD = nodo.getDerecho();

        //Primer caso: el nodo es una hoja
        if(HI == null && HD == null) {
            if(padre == null) {
                this.raiz = null;
            } else {
                if(padre.getIzquierdo() == nodo) {
                    padre.setIzquierdo(null);
                } else {
                    padre.setDerecho(null);
                }
            }
            exito = true;
        } 
        
        //Segundo caso: el nodo tiene un solo hijo
        else if ((HI != null && HD == null) || (HI == null && HD != null)) {
            if(padre == null) {
                if(HI != null) {
                    this.raiz = HI;
                } else {
                    this.raiz = HD;
                }
            } else {
                if(padre.getIzquierdo() == nodo) {
                    if(HI != null) {
                        padre.setIzquierdo(HI);
                    } else {
                        padre.setIzquierdo(HD);
                    }
                } else {
                    if(HI != null) {
                        padre.setDerecho(HI);
                    } else {
                        padre.setDerecho(HD);
                    }
                }
            }
            exito = true;
        }

        //Tercer caso: el nodo tiene dos hijos
        else {
            NodoABB reemplazo = HD;
            NodoABB padreReemplazo = nodo;
            while(reemplazo.getIzquierdo() != null) {
                padreReemplazo = reemplazo;
                reemplazo = reemplazo.getIzquierdo();
            }

            nodo.setElem(reemplazo.getElem());
            if(padreReemplazo == nodo) {
                nodo.setDerecho(reemplazo.getDerecho());
            } else {
                padreReemplazo.setIzquierdo(reemplazo.getDerecho());
            }
            exito = true;
        }
        
        return exito;
    }

    public Lista listar() {
        Lista lista = new Lista();
        listarAux(this.raiz, lista);
        return lista;
    }

    private void listarAux(NodoABB nodo, Lista lista) {
        if (nodo != null) {
            listarAux(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            listarAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarRango(Comparable elemMin, Comparable elemMax) {
        Lista lista = new Lista();
        listarRangoAux(this.raiz, lista, elemMin, elemMax);
        return lista;
    }

    private void listarRangoAux(NodoABB nodo, Lista lista, Comparable elemMin, Comparable elemMax) {
        if (nodo != null) {
            if (nodo.getElem().compareTo(elemMin) > 0) {
                listarRangoAux(nodo.getIzquierdo(), lista, elemMin, elemMax);
            }
            if (nodo.getElem().compareTo(elemMin) >= 0 && nodo.getElem().compareTo(elemMax) <= 0) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
            if (nodo.getElem().compareTo(elemMax) < 0) {
                listarRangoAux(nodo.getDerecho(), lista, elemMin, elemMax);
            }
        }
    }

    public Comparable minimoElem() {
        return this.raiz == null ? null : minimoElemAux(this.raiz);
    }

    private Comparable minimoElemAux(NodoABB nodo) {
        Comparable elem;
        if(nodo.getIzquierdo() == null) {
            elem = nodo.getElem();
        } else {
            return minimoElemAux(nodo.getIzquierdo());
        }
        return elem;
    }

    public Comparable maximoElem() {
        return this.raiz == null ? null : maximoElemAux(this.raiz);
    }

    private Comparable maximoElemAux(NodoABB nodo) {
        Comparable elem;
        if(nodo.getDerecho() == null) {
            elem = nodo.getElem();
        } else {
            return maximoElemAux(nodo.getDerecho());
        }
        return elem;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoABB nodo) {
        String str = "";
        if (nodo != null) {
            str += "Nodo: " + nodo.getElem() + "\n";
            str += "HI de " + nodo.getElem() + ": " + (nodo.getIzquierdo() == null ? "null" : nodo.getIzquierdo().getElem()) + "\n";
            str += "HD de " + nodo.getElem() + ": " + (nodo.getDerecho() == null ? "null" : nodo.getDerecho().getElem()) + "\n";
            str += toStringAux(nodo.getIzquierdo());
            str += toStringAux(nodo.getDerecho());
        }
        return str;
    }

    public void elminarMinimo() {
        if(this.raiz != null) {
            if(this.raiz.getIzquierdo() == null) {
                this.raiz = this.raiz.getDerecho();
            } else {
                eliminarMinimoAux(this.raiz, null);
            }
        }
    }

    private void eliminarMinimoAux(NodoABB nodo, NodoABB padre) {
        if(nodo.getIzquierdo() != null) {
            padre = nodo;
            eliminarMinimoAux(nodo.getIzquierdo(), padre);
        } else {
            padre.setIzquierdo(nodo.getDerecho());
        }
    }

    public ArbolBB clonarParteInvertida(Comparable elem) {
        NodoABB nodoElemento = null;
        ArbolBB subArbol = new ArbolBB();
        if (this.raiz != null) {
            // El arbol no está vacío.
            nodoElemento = buscarSubarbol(this.raiz, elem);
            if (nodoElemento != null) {
                // se encontró el elemento.
                subArbol.raiz = clonarInvertido(nodoElemento);
            }
        }
        return subArbol;
    }

    private NodoABB clonarInvertido(NodoABB nodoElemento) {
        NodoABB clon = null;
        if (nodoElemento != null) {
            // Se crea un nuevo nodo con el elemento del nodo actual.
            // Se invierten los hijos del nodo actual, llamando recursivamente a la función
            // (en los h. de las hojas se devolvería null).
            clon = new NodoABB(nodoElemento.getElem(), clonarInvertido(nodoElemento.getDerecho()),
                    clonarInvertido(nodoElemento.getIzquierdo()));
        }
        return clon;
    }

    private NodoABB buscarSubarbol(NodoABB nodo, Comparable elem) {
        NodoABB ret = null;
        if (nodo != null) {
            // Si el elemento es menor al actual, se busca en el subárbol izquierdo.
            Comparable x = nodo.getElem();
            if (elem.compareTo(x) < 0) {
                ret = buscarSubarbol(nodo.getIzquierdo(), elem);
            }
            // Si el elemento es mayor al actual, se busca en el subárbol derecho.
            else if (elem.compareTo(x) > 0) {
                ret = buscarSubarbol(nodo.getDerecho(), elem);
            }
            // Si el elemento es igual al actual, se devuelve el nodo actual.
            else {
                ret = nodo;
            }
        }
        return ret;
    }
}