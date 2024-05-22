package tests.lineales;
import lineales.dinamicas.*;

public class PruebaLista {
    public static void main(String[] args) {
        Lista lista = new Lista();
    }


    public static Lista concatenar(Lista lista1, Lista lista2) {
        Lista lista3 = new Lista();
        while (!lista1.esVacia()) {
            lista3.insertar(lista1.recuperar(1), lista3.longitud() + 1);
            lista1.eliminar(1);
        }
        while (!lista2.esVacia()) {
            lista3.insertar(lista2.recuperar(1), lista3.longitud() + 1);
            lista2.eliminar(1);
        }
        return lista3;
    }

    public static boolean comprobar(Lista lista) {
        boolean exito = true;
        Pila pilaAux = new Pila();
        Cola colaAux = new Cola();
        Lista listaAux = lista.clonar();

        while (!listaAux.esVacia() && exito) {
            Object elem = listaAux.recuperar(1);
            if (elem.equals(0)) {
                listaAux.eliminar(1);
                while (!colaAux.esVacia() && exito) {
                    if (!colaAux.obtenerFrente().equals(listaAux.recuperar(1))) {
                        exito = false;
                    } else {
                        colaAux.sacar();
                        listaAux.eliminar(1);
                    }
                }
                listaAux.eliminar(1);
                while (!pilaAux.esVacia() && exito) {
                    if (!pilaAux.obtenerTope().equals(listaAux.recuperar(1))) {
                        exito = false;
                    } else {
                        pilaAux.desapilar();
                        listaAux.eliminar(1);
                    }
                }
            } else {
                pilaAux.apilar(elem);
                colaAux.poner(elem);
                listaAux.eliminar(1);
            }
        }
        return exito;
    }

    public static Lista invertir(Lista lista) {
        Lista listaAux = lista.clonar();
        Pila pilaAux = new Pila();
        while (!listaAux.esVacia()) {
            pilaAux.apilar(listaAux.recuperar(1));
            listaAux.eliminar(1);
        }
        while (!pilaAux.esVacia()) {
            listaAux.insertar(pilaAux.obtenerTope(), listaAux.longitud() + 1);
            pilaAux.desapilar();
        }
        return listaAux;
    }
}