package grafo;

import lineales.dinamicas.Lista;

public class Grafo {
    private NodoVert inicio;

    public Grafo() {
        inicio = null;
    }

    public boolean insertarVertice(Object buscado) {
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(buscado);
        // Si el vertice no existe, lo inserta al inicio de la lista
        if (aux == null) {
            this.inicio = new NodoVert(buscado, this.inicio);
            exito = true;
        }
        return exito;
    }

    private NodoVert ubicarVertice(Object buscado) {
        NodoVert aux = this.inicio;
        // Busca el vertice en la lista de vertices
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean eliminarVertice(Object buscado) {
        boolean exito = false;
        NodoVert aux = this.inicio;
        if (aux != null) {
            // El vertice esta en el inicio de la lista
            if (aux.getElem().equals(buscado)) {
                NodoAdy auxAdy = aux.getPrimerAdy();
                while (auxAdy != null) {
                    this.eliminarArcoVertice(auxAdy.getVertice(), aux);
                    auxAdy = auxAdy.getSigAdyacente();
                }
                this.inicio = aux.getSigVertice();
                exito = true;
            } else {
                // Busca el vertice en la lista
                while (aux.getSigVertice() != null && !aux.getSigVertice().getElem().equals(buscado)) {
                    aux = aux.getSigVertice();
                }
                // Si lo encuentra, elimina los arcos que lo contienen
                if (aux.getSigVertice() != null) {
                    NodoAdy auxAdy = aux.getSigVertice().getPrimerAdy();
                    while (auxAdy != null) {
                        this.eliminarArcoVertice(auxAdy.getVertice(), aux.getSigVertice());
                        auxAdy = auxAdy.getSigAdyacente();
                    }
                    aux.setSigVertice(aux.getSigVertice().getSigVertice());
                    exito = true;
                }
            }
        }
        return exito;
    }
    
    private void eliminarArcoVertice(NodoVert buscado, NodoVert vertice) {
        NodoVert aux = this.inicio;
        // Busca el vertice en la lista de vertices
        while (!aux.equals(buscado)) {
            aux = aux.getSigVertice();
        }
        NodoAdy auxAdy = aux.getPrimerAdy();
        // Si el primer arco es el que se busca, lo elimina
        if (auxAdy.getVertice().equals(vertice)) {
            aux.setPrimerAdy(auxAdy.getSigAdyacente());
        } else {
            // Busca el arco en la lista de adyacentes y lo elimina
            while (!auxAdy.getSigAdyacente().getVertice().equals(vertice)) {
                auxAdy = auxAdy.getSigAdyacente();
            }
            auxAdy.setSigAdyacente(auxAdy.getSigAdyacente().getSigAdyacente());
        }
    }

    public boolean insertarArco(Object origen, Object destino, Object etiqueta) {
        boolean exito = true;
        NodoVert auxOrigen = this.ubicarVertice(origen);
        NodoVert auxDestino = this.ubicarVertice(destino);
        // Si ambos vertices existen, inserta el arco
        if (auxOrigen != null && auxDestino != null) {
            NodoAdy auxAdy = auxOrigen.getPrimerAdy();
            if (auxAdy == null) {
                auxOrigen.setPrimerAdy(new NodoAdy(auxDestino, null, etiqueta));
            } else {
                // Si el arco no existe, lo inserta al inicio de la lista
                while (auxAdy.getSigAdyacente() != null && !auxAdy.getSigAdyacente().getVertice().equals(auxDestino)) {
                    auxAdy = auxAdy.getSigAdyacente();
                }
                if (auxAdy.getSigAdyacente() == null) {
                    auxAdy.setSigAdyacente(new NodoAdy(auxDestino, null, etiqueta));
                } else {
                    exito = false;
                }
            }
            //Ahora hace lo mismo con el vertice destino
            if (exito) {
                auxAdy = auxDestino.getPrimerAdy();
                if (auxAdy == null) {
                    auxDestino.setPrimerAdy(new NodoAdy(auxOrigen, null, etiqueta));
                } else {
                    while (auxAdy.getSigAdyacente() != null && !auxAdy.getSigAdyacente().getVertice().equals(auxOrigen)) {
                        auxAdy = auxAdy.getSigAdyacente();
                    }
                    if (auxAdy.getSigAdyacente() == null) {
                        auxAdy.setSigAdyacente(new NodoAdy(auxOrigen, null, etiqueta));
                    }
                }
            }
        } else {
            exito = false;
        }
        return exito;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        boolean exito = true;
        NodoVert auxOrigen = this.ubicarVertice(origen);
        NodoVert auxDestino = this.ubicarVertice(destino);
        // Si ambos vertices existen, elimina el arco
        if (auxOrigen != null && auxDestino != null) {
            NodoAdy auxAdy = auxOrigen.getPrimerAdy();
            // Si el arco es el primero, lo elimina
            if (auxAdy != null && auxAdy.getVertice().equals(auxDestino)) {
                auxOrigen.setPrimerAdy(auxAdy.getSigAdyacente());
            } else {
                // Busca el arco en la lista de adyacentes y lo elimina
                while (auxAdy != null && auxAdy.getSigAdyacente() != null && !auxAdy.getSigAdyacente().getVertice().equals(auxDestino)) {
                    auxAdy = auxAdy.getSigAdyacente();
                }
                if (auxAdy == null || auxAdy.getSigAdyacente() == null) {
                    exito = false;
                } else {
                    auxAdy.setSigAdyacente(auxAdy.getSigAdyacente().getSigAdyacente());
                }
            }
            // Ahora hace lo mismo con el vertice destino
            if (exito) {
                auxAdy = auxDestino.getPrimerAdy();
                // Si el arco es el primero, lo elimina
                if (auxAdy != null && auxAdy.getVertice().equals(auxOrigen)) {
                    auxDestino.setPrimerAdy(auxAdy.getSigAdyacente());
                } else {
                    // Busca el arco en la lista de adyacentes y lo elimina
                    while (auxAdy != null && auxAdy.getSigAdyacente() != null && !auxAdy.getSigAdyacente().getVertice().equals(auxOrigen)) {
                        auxAdy = auxAdy.getSigAdyacente();
                    }
                    if (auxAdy != null && auxAdy.getSigAdyacente() != null) {
                        auxAdy.setSigAdyacente(auxAdy.getSigAdyacente().getSigAdyacente());
                    }
                }
            }
        } else {
            exito = false;
        }
        return exito;
    }

    public boolean existeVertice(Object buscado) {
        return this.ubicarVertice(buscado) != null;
    }

    public boolean existeArco(Object origen, Object destino) {
        boolean exito = false;
        NodoVert auxOrigen = this.ubicarVertice(origen);
        NodoVert auxDestino = this.ubicarVertice(destino);
        // Si ambos vertices existen, busca el arco
        if (auxOrigen != null && auxDestino != null) {
            NodoAdy auxAdy = auxOrigen.getPrimerAdy();
            // Busca el arco en la lista de adyacentes
            while (auxAdy != null && !auxAdy.getVertice().equals(auxDestino)) {
                auxAdy = auxAdy.getSigAdyacente();
            }
            exito = auxAdy != null;
        }
        return exito;
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean exito = false;
        // verifica si ambos vertices existen
        NodoVert auxO = null;
        NodoVert auxD = null;
        NodoVert aux = this.inicio;
    
        while ((auxO == null || auxD == null) && aux != null) {
            if (aux.getElem().equals(origen)) auxO = aux;
            if (aux.getElem().equals(destino)) auxD = aux;
            aux = aux.getSigVertice();
        }
    
        if (auxO != null && auxD != null) {
            // si ambos vertices existen busca si existe camino entre ambos
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxO, destino, visitados);
        }
        return exito;
    }
    
    private boolean existeCaminoAux(NodoVert n, Object dest, Lista vis) {
        boolean exito = false;
        if (n != null) {
            // si vertice n es el destino: HAY CAMINO!
            if (n.getElem().equals(dest)) {
                exito = true;
            } else {
                // si no es el destino verifica si hay camino entre n y destino
                vis.insertar(n.getElem(), vis.longitud() + 1);
                NodoAdy ady = n.getPrimerAdy();
                while (!exito && ady != null) {
                    if (vis.localizar(ady.getVertice().getElem()) < 0) {
                        exito = existeCaminoAux(ady.getVertice(), dest, vis);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    public Lista listarEnProfundidad() {
        Lista visitados = new Lista();
        // define un vertice donde comenzar a recorrer
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                // si el vertice no fue visitado aun, avanza en profundidad
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert n, Lista vis) {
        if (n != null) {
            // marca al vertice n como visitado
            vis.insertar(n.getElem(), vis.longitud() + 1);
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null) {
                // visita en profundidad los adyacentes de n aun no visitados
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), vis);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public boolean esVacio() {
        return this.inicio == null;
    }

    public String toString() {
        String s = "";
        NodoVert aux = this.inicio;
        while (aux != null) {
            s += aux.getElem() + " -> ";
            NodoAdy auxAdy = aux.getPrimerAdy();
            while (auxAdy != null) {
                s += auxAdy.getVertice().getElem() + " ";
                auxAdy = auxAdy.getSigAdyacente();
            }
            s += "\n";
            aux = aux.getSigVertice();
        }
        return s;
    }

    public Lista listarTodosLosCaminos(Object origen, Object destino) {
        Lista caminos = new Lista();
        NodoVert auxO = this.ubicarVertice(origen);
        NodoVert auxD = this.ubicarVertice(destino);
        if (auxO != null && auxD != null) {
            Lista visitados = new Lista();
            listarTodosLosCaminosAux(auxO, destino, visitados, caminos);
        }
        return caminos;
    }

    private void listarTodosLosCaminosAux(NodoVert actual, Object destino, Lista visitados, Lista caminos) {
        if (actual != null) {
            visitados.insertar(actual.getElem(), visitados.longitud() + 1);
            if (actual.getElem().equals(destino)) {
                caminos.insertar(visitados.clonar(), caminos.longitud() + 1);
            } else {
                NodoAdy adyacente = actual.getPrimerAdy();
                while (adyacente != null) {
                    if (visitados.localizar(adyacente.getVertice().getElem()) < 0) {
                        listarTodosLosCaminosAux(adyacente.getVertice(), destino, visitados, caminos);
                    }
                    adyacente = adyacente.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
    }

    public Lista caminoMasCorto(Object origen, Object destino) {
        Lista caminos = listarTodosLosCaminos(origen, destino);
        Lista camino = new Lista();
        if (!caminos.esVacia()) {
            camino = (Lista) caminos.recuperar(1);
            int longitud = camino.longitud();
            for (int i = 2; i <= caminos.longitud(); i++) {
                Lista caminoAux = (Lista) caminos.recuperar(i);
                if (caminoAux.longitud() < longitud) {
                    camino = caminoAux;
                    longitud = caminoAux.longitud();
                }
            }
        }
        return camino;
    }
}