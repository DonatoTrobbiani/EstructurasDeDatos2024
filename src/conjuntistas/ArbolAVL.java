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
        boolean[] exito = { true };
        if (this.raiz == null) {
            this.raiz = new NodoAVL(elem, null, null);
        } else {
            NodoAVL resultado = insertarAux(this.raiz, elem, exito);
            if (resultado != null) {
                this.raiz = resultado;
            }
        }
        return exito[0];
    }

    private NodoAVL insertarAux(NodoAVL nodo, Comparable elem, boolean[] exito) {  
        if (elem.compareTo(nodo.getElem()) == 0) {
            exito[0] = false;
        } else {
            if (elem.compareTo(nodo.getElem()) < 0) {
                if (nodo.getIzquierdo() == null) {
                    nodo.setIzquierdo(new NodoAVL(elem, null, null));
                } else {
                    nodo.setIzquierdo(insertarAux(nodo.getIzquierdo(), elem, exito));
                }
            } else {
                if (nodo.getDerecho() == null) {
                    nodo.setDerecho(new NodoAVL(elem, null, null));
                } else {
                    nodo.setDerecho(insertarAux(nodo.getDerecho(), elem, exito));
                }
            }
        }
        // actualizar altura
        nodo.recalcularAltura();
        // balancear
        int balance = balance(nodo);
        if (Math.abs(balance) > 1) {
            // Balancear el nodo
            nodo = balancear(nodo, balance);
        }
        return nodo;
    }

    private NodoAVL balancear(NodoAVL nodo, int balance) {
        if (balance == 2) {
            if (balance(nodo.getIzquierdo()) == -1) {
                nodo = rotarIzqDer(nodo);
            } else {
                nodo = rotarDerecha(nodo);
            }
        } else if (balance == -2) {
            if (balance(nodo.getDerecho()) == 1) {
                nodo = rotarDerIzq(nodo);
            } else {
                nodo = rotarIzquierda(nodo);
            }
        }
        nodo.recalcularAltura(); 
        return nodo;
    }

    private int balance(NodoAVL nodo) {
        int altIzq = nodo.getIzquierdo() != null ? nodo.getIzquierdo().getAltura() : -1;
        int altDer = nodo.getDerecho() != null ? nodo.getDerecho().getAltura() : -1;
        return altIzq - altDer;
    }

    private NodoAVL rotarIzquierda(NodoAVL r) {
        NodoAVL d = r.getDerecho();
        NodoAVL temp = d.getIzquierdo();
        d.setIzquierdo(r);
        r.setDerecho(temp);
        r.recalcularAltura();
        d.recalcularAltura();
        return d;
    }

    private NodoAVL rotarDerecha(NodoAVL r) {
        NodoAVL i = r.getIzquierdo();
        NodoAVL temp = i.getDerecho();
        i.setDerecho(r);
        r.setIzquierdo(temp);
        r.recalcularAltura();
        i.recalcularAltura();
        return i;
    }

    private NodoAVL rotarIzqDer(NodoAVL r) {
        r.setIzquierdo(rotarIzquierda(r.getIzquierdo()));
        return rotarDerecha(r);
    }

    private NodoAVL rotarDerIzq(NodoAVL r) {
        r.setDerecho(rotarDerecha(r.getDerecho()));
        return rotarIzquierda(r);
    }

    public boolean eliminar(Comparable elem) {
        boolean[] exito = { false };
        if (this.raiz != null) {
            this.raiz = eliminarAux(this.raiz, elem, exito);
        }
        return exito[0];
    }

    private NodoAVL eliminarAux(NodoAVL nodo, Comparable elem, boolean[] exito) {
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem()) == 0) {
                exito[0] = true;
                if (nodo.getIzquierdo() == null || nodo.getDerecho() == null) {
                    // El nodo tiene 0 o 1 hijo.
                    nodo = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo() : nodo.getDerecho();
                } else {
                    // El nodo tiene 2 hijos.
                    NodoAVL reemplazo = nodo.getDerecho();
                    NodoAVL padreReemplazo = nodo;
                    while (reemplazo.getIzquierdo() != null) {
                        padreReemplazo = reemplazo;
                        reemplazo = reemplazo.getIzquierdo();
                    }
                    if (padreReemplazo != nodo) {
                        padreReemplazo.setIzquierdo(reemplazo.getDerecho());
                        reemplazo.setDerecho(nodo.getDerecho()); // nodo.getDerecho = padreReemplazo
                    }
                    reemplazo.setIzquierdo(nodo.getIzquierdo()); // nodo.getIzquierdo = raiz.getIzquierdo
                    nodo = reemplazo;
                }
            } else {
                if (elem.compareTo(nodo.getElem()) < 0) {
                    nodo.setIzquierdo(eliminarAux(nodo.getIzquierdo(), elem, exito));
                } else {
                    nodo.setDerecho(eliminarAux(nodo.getDerecho(), elem, exito));
                }
            }
            
            if (nodo != null) {
                // actualizar altura
                nodo.recalcularAltura();
                int balance = balance(nodo);
                if (Math.abs(balance) > 1) {
                    // Balancear el nodo
                    nodo = balancear(nodo, balance);
                }
            }
        }
        return nodo;
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
        return nodo.getIzquierdo() == null ? nodo.getElem() : minimoElemAux(nodo.getIzquierdo());
    }

    public Comparable maximoElem() {
        return this.raiz == null ? null : maximoElemAux(this.raiz);
    }

    private Comparable maximoElemAux(NodoAVL nodo) {
        return nodo.getDerecho() == null ? nodo.getElem() : maximoElemAux(nodo.getDerecho());
    }

    public void vaciar() {
        this.raiz = null;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoAVL nodo) {
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