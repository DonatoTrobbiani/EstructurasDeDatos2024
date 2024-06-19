package tests.conjuntistas;

import conjuntistas.ArbolBB;
import lineales.dinamicas.Lista;

public class TestArbolBB {
    public static void main(String[] args) {
        testingArbolBB();
    }

    public static void testingArbolBB() {
        System.out.println("TESTING ARBOL BB");
        ArbolBB arbolBB = new ArbolBB();
        System.out.println(arbolBB.toString());

        System.out.println("Inserta 1, espera TRUE: " + arbolBB.insertar(1));
        System.out.println("Inserta 5, espera TRUE: " + arbolBB.insertar(5));
        System.out.println("Inserta 7, espera TRUE: " + arbolBB.insertar(7));
        System.out.println("Inserta 8, espera TRUE: " + arbolBB.insertar(8));
        System.out.println("Inserta 11, espera TRUE: " + arbolBB.insertar(11));
        System.out.println("Inserta 14, espera TRUE: " + arbolBB.insertar(14));
        System.out.println("Inserta 19, espera TRUE: " + arbolBB.insertar(19));

        System.out.println(arbolBB.toString());

        System.out.println("Inserta 12, espera TRUE: " + arbolBB.insertar(12));
        System.out.println("Inserta 3, espera TRUE: " + arbolBB.insertar(3));

        System.out.println(arbolBB.toString());

        System.out.println("Inserta 1, espera FALSE: " + arbolBB.insertar(1));
        System.out.println("Inserta 11, espera FALSE: " + arbolBB.insertar(11));

        System.out.println("Pertenece 1, espera TRUE: " + arbolBB.pertenece(1));
        System.out.println("Pertenece 14, espera TRUE: " + arbolBB.pertenece(14));

        System.out.println("Pertenece 2, espera FALSE: " + arbolBB.pertenece(2));

        Lista lista = arbolBB.listar();
        System.out.println("Lista con los elementos del arbol: " + lista.toString());

        System.out.println("Listar rango 5 a 14: " + arbolBB.listarRango(5, 14).toString());

        System.out.println("Minimo: " + arbolBB.minimoElem());
        System.out.println("Maximo: " + arbolBB.maximoElem());

        System.out.println("Comprueba si el arbol es vacio, espera FALSE: " + arbolBB.esVacio());

        System.out.println("Elimina 1, espera TRUE: " + arbolBB.eliminar(1));
        
    }
}