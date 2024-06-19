package conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean pertenece(Comparable elem) {
        return perteneceAux(this.raiz, elem);
    }

    private boolean perteneceAux(NodoAVL nodo, Comparable elem) {
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
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(elem, null, null);
        } else {
            exito = insertarAux(this.raiz, elem);
            balancear(this.raiz);
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL nodo, Comparable elem) {
        boolean exito = true;
        
        if (elem.compareTo(nodo.getElem()) == 0) {
            exito = false;
        } else {
            if (elem.compareTo(nodo.getElem()) < 0) {
                if (nodo.getIzquierdo() == null) {
                    nodo.setIzquierdo(new NodoAVL(elem, null, null));
                } else {
                    exito = insertarAux(nodo.getIzquierdo(), elem);
                }
            } else {
                if (nodo.getDerecho() == null) {
                    nodo.setDerecho(new NodoAVL(elem, null, null));
                } else {
                    exito = insertarAux(nodo.getDerecho(), elem);
                }
            }
        }
        return exito;
    }

    private void balancear(NodoAVL nodo) {
        if (nodo != null) {
            balancear(nodo.getIzquierdo());
            balancear(nodo.getDerecho());
            nodo.recalcularAltura();
            int balance = balance(nodo);
            if (balance == 2) {
                if (balance(nodo.getDerecho()) < 0) {
                    nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
                }
                nodo = rotarIzquierda(nodo);
            } else if (balance == -2) {
                if (balance(nodo.getIzquierdo()) > 0) {
                    nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
                }
                nodo = rotarDerecha(nodo);
            }
        }
    }

    private int balance(NodoAVL nodo) {
        int altIzq = nodo.getIzquierdo() != null ? nodo.getIzquierdo().getAltura() : -1;
        int altDer = nodo.getDerecho() != null ? nodo.getDerecho().getAltura() : -1;
        return altDer - altIzq;
    }

    private NodoAVL rotarIzquierda(NodoAVL nodo) {
        NodoAVL h = nodo.getDerecho();
        NodoAVL temp = h.getIzquierdo();
        h.setIzquierdo(nodo);
        nodo.setDerecho(temp);
        return h;
    }

    private NodoAVL rotarDerecha(NodoAVL nodo) {
        NodoAVL h = nodo.getIzquierdo();
        NodoAVL temp = h.getDerecho();
        h.setDerecho(nodo);
        nodo.setIzquierdo(temp);
        return h;
    }

    public boolean eliminar(Comparable elem) {
        return eliminarAux(this.raiz, null, elem);
    }

    private boolean eliminarAux(NodoAVL nodo, NodoAVL padre, Comparable elem) {
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

    private boolean eliminarAuxNuevo(NodoAVL nodo, NodoAVL padre) {
        boolean exito = false;
        NodoAVL HI = nodo.getIzquierdo();
        NodoAVL HD = nodo.getDerecho();

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
            NodoAVL reemplazo = HD;
            NodoAVL padreReemplazo = nodo;
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

    private void listarAux(NodoAVL nodo, Lista lista) {
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

    private void listarRangoAux(NodoAVL nodo, Lista lista, Comparable elemMin, Comparable elemMax) {
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

    private Comparable minimoElemAux(NodoAVL nodo) {
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

    private Comparable maximoElemAux(NodoAVL nodo) {
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

    private String toStringAux(NodoAVL nodo) {
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
}