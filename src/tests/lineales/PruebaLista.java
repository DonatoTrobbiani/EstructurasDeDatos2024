package tests.lineales;
import java.util.Scanner;
import lineales.dinamicas.*;

public class PruebaLista {
    public static void main(String[] args) {
        Lista lista1 = new Lista();
        Lista lista2 = new Lista();
        Lista lista3 = new Lista();

        lista1.insertar(2, 1);
        lista1.insertar(4, 2);
        lista1.insertar(6, 3);

        lista2.insertar(5, 1);
        lista2.insertar(1, 2);
        lista2.insertar(6, 3);
        lista2.insertar(7, 4);

        lista3.insertar(9, 1);
        lista3.insertar(6, 2);
        lista3.insertar(5, 3);
        lista3.insertar(0, 4);
        lista3.insertar(9, 5);
        lista3.insertar(6, 6);
        lista3.insertar(5, 7);
        lista3.insertar(0, 8);
        lista3.insertar(5, 9);
        lista3.insertar(6, 10);
        lista3.insertar(9, 11);

        menuTest(lista1, lista2, lista3);
    }

    public static void menuTest(Lista lista1, Lista lista2, Lista lista3) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menú de pruebas");
            System.out.println("1. Test Concatenar");
            System.out.println("2. Test Comprobar");
            System.out.println("3. Test Invertir");
            System.out.println("4. Ver listas");
            System.out.println("0. Salir");
            
            opcion = sc.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("Concatenando lista 1 y lista 2");
                    Lista listaConcatenada = concatenar(lista1, lista2);
                    System.out.println(listaConcatenada.toString());
                    break;
                case 2:
                    System.out.println("Es la lista 1 válida? " + comprobar(lista1));
                    System.out.println("Es la lista 2 válida? " + comprobar(lista2));
                    System.out.println("Es la lista 3 válida? " + comprobar(lista3));
                    break;
                case 3:
                    System.out.println("Invertir lista 1");
                    Lista listaInvertida = invertir(lista1);
                    System.out.println(listaInvertida.toString());
                    System.out.println("Invertir lista 2");
                    listaInvertida = invertir(lista2);
                    System.out.println(listaInvertida.toString());
                    System.out.println("Invertir lista 3");
                    listaInvertida = invertir(lista3);
                    System.out.println(listaInvertida.toString());
                    break;
                case 4:
                    System.out.println("Lista 1: " + lista1.toString());
                    System.out.println("Lista 2: " + lista2.toString());
                    System.out.println("Lista 3: " + lista3.toString());
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 0);
        sc.close();
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

        boolean tieneCero = false;
        for (int i = 1; i <= listaAux.longitud(); i++) {
            if (listaAux.recuperar(i).equals(0)) {
                tieneCero = true;
                break;
            }
        }
        if (!tieneCero) {
            return false;
        }

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