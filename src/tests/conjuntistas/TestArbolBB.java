package tests.conjuntistas;

import conjuntistas.ArbolBB;
import lineales.dinamicas.Lista;

public class TestArbolBB {
    public static void main(String[] args) {
        testingArbolBB();
    }

    public static void testingArbolBB() {
        System.out.println("TESTING ARBOL BINARIO DE BUSQUEDA");
        ArbolBB arbolBB = new ArbolBB();

        // Test inserting elements
        System.out.println("Inserta 50: " + arbolBB.insertar(50));
        System.out.println("Inserta 30: " + arbolBB.insertar(30));
        System.out.println("Inserta 70: " + arbolBB.insertar(70));
        System.out.println("Inserta 20: " + arbolBB.insertar(20));
        System.out.println("Inserta 40: " + arbolBB.insertar(40));
        System.out.println("Inserta 60: " + arbolBB.insertar(60));
        System.out.println("Inserta 80: " + arbolBB.insertar(80));

        // Test tree structure
        System.out.println("Arbol: " + arbolBB.listar().toString());
        System.out.println(arbolBB.toString());

        // Test searching elements
        System.out.println("Buscar 40, espera true: " + arbolBB.pertenece(40));
        System.out.println("Buscar 90, espera false: " + arbolBB.pertenece(90));

        // Test deleting elements
        System.out.println("Eliminar 20, espera true: " + arbolBB.eliminar(20));
        System.out.println("Eliminar 30, espera true: " + arbolBB.eliminar(30));
        System.out.println("Eliminar 50, espera true: " + arbolBB.eliminar(50));
        System.out.println("Eliminar 90, espera false: " + arbolBB.eliminar(90));

        // Test tree structure after deletions
        System.out.println("Arbol después de eliminaciones: " + arbolBB.listar().toString());
        System.out.println(arbolBB.toString());

        // Test emptying the tree
        arbolBB.vaciar();
        System.out.println("Arbol vaciado, espera true: " + arbolBB.esVacio());
    }
}