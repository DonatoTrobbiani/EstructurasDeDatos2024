package tests.jerarquicas;

import jerarquicas.dinamicas.ArbolBin;
import lineales.dinamicas.Lista;

public class TestArbolBin {
    public static void main(String[] args) {
        testingArbolBin();
    }

    public static void testingArbolBin() {
        System.out.println("TESTING ARBOL BINARIO");
        ArbolBin arbolBin = new ArbolBin();
        System.out.println(arbolBin.toString());

        System.out.println("PROBANDO INSERTAR/*");
        System.out.println("Inserta raiz A, espera TRUE: " + arbolBin.insertar('A', 'A', 'I'));
        System.out.println("Inserta B como hijo izquierdo de A, espera TRUE: " + arbolBin.insertar('B', 'A', 'I'));
        System.out.println("Inserta C como hijo derecho de A, espera TRUE: " + arbolBin.insertar('C', 'A', 'D'));
        System.out.println("Inserta D como hijo izquierdo de B, espera TRUE: " + arbolBin.insertar('D', 'B', 'I'));
        System.out.println("Inserta E como hijo derecho de B, espera TRUE: " + arbolBin.insertar('E', 'B', 'D'));
        System.out.println("Inserta F como hijo izquierdo de C, espera TRUE: " + arbolBin.insertar('F', 'C', 'I'));
        System.out.println("Inserta G como hijo derecho de C, espera TRUE: " + arbolBin.insertar('G', 'C', 'D'));
        System.out.println(arbolBin.toString());

        System.out.println("Inserta Z hijo izquierdo de A, espera FALSE: " + arbolBin.insertar('Z', 'A', 'I'));
        System.out.println("Inserta Y hijo derecho de C, espera FALSE: " + arbolBin.insertar('Y', 'C', 'D'));
        System.out.println(arbolBin.toString());
        System.out.println("/*FINALIZA PRUEBA INSERTAR");

        System.out.println("PROBANDO insertarPorPosicion/*");
        System.out.println("Inserta H como hijo izquierdo de D(3), espera TRUE: " + arbolBin.insertarPorPosicion('H', 3, 'I'));
        System.out.println("Inserta I como hijo derecho de D(3), espera TRUE: " + arbolBin.insertarPorPosicion('I', 3, 'D'));
        System.out.println("Inserta J como hijo izquierdo de E(6), espera TRUE: " + arbolBin.insertarPorPosicion('J', 6, 'I'));
        System.out.println("Inserta K como hijo derecho de E(6), espera TRUE: " + arbolBin.insertarPorPosicion('K', 6, 'D'));
        System.out.println(arbolBin.toString());

        System.out.println("Inserta X como hijo izquierdo de D(3), espera FALSE: " + arbolBin.insertarPorPosicion('X', 3, 'I'));
        System.out.println("Inserta W como hijo derecho de A(1), espera FALSE: " + arbolBin.insertarPorPosicion('W', 1, 'D'));
        System.out.println(arbolBin.toString());
        System.out.println("/*FINALIZA PRUEBA insertarPorPosicion");

        System.out.println("Comprueba si el arbol esta vacio, espera FALSE: " + arbolBin.esVacio());

        System.out.println("Retorna altura del arbol, espera 3: " + arbolBin.altura());

        System.out.println("Retorna nivel de F, espera 2: " + arbolBin.nivel('F'));
        System.out.println("Retorna nivel de A, espera 0: " + arbolBin.nivel('A'));

        System.out.println("Retorna padre de J, espera E: " + arbolBin.padre('J'));
        System.out.println("Retorna padre de A, espera NULL: " + arbolBin.padre('A'));

        System.out.println("Clona el arbol");
        ArbolBin clon = arbolBin.clone();
        System.out.println("Arbol clonado: ");
        System.out.println(clon.toString());

        System.out.println("Vacía el arbol origianl");
        arbolBin.vaciar();
        System.out.println("Arbol original: ");
        System.out.println(arbolBin.toString());
        System.out.println("Retorna altura del arbol, espera -1: " + arbolBin.altura());
        System.out.println("Comprueba si el arbol esta vacio, espera TRUE: " + arbolBin.esVacio());

        System.out.println("Lista el arbol clonado en preorden");
        Lista preorden = clon.listarPreorden();
        System.out.println(preorden.toString());
        System.out.println("Lista el arbol original en preorden y comprueba si es vacia, espera TRUE: " + arbolBin.listarPreorden().esVacia());

        System.out.println("Lista el arbol clonado en inorden");
        Lista inorden = clon.listarInorden();
        System.out.println(inorden.toString());
        System.out.println("Lista el arbol original en inorden y comprueba si es vacia, espera TRUE: " + arbolBin.listarInorden().esVacia());

        System.out.println("Lista el arbol clonado en posorden");
        Lista posorden = clon.listarPosorden();
        System.out.println(posorden.toString());
        System.out.println("Lista el arbol original en posorden y comprueba si es vacia, espera TRUE: " + arbolBin.listarPosorden().esVacia());

        System.out.println("Lista el arbol clonado por niveles");
        Lista porNiveles = clon.listarPorNiveles();
        System.out.println(porNiveles.toString());
        System.out.println("Lista el arbol original por niveles y comprueba si es vacia, espera TRUE: " + arbolBin.listarPorNiveles().esVacia());

        System.out.println("Lista con las hojas del arbol clonado");
        Lista frontera = clon.frontera();
        System.out.println(frontera.toString());
        System.out.println("Lista con las hojas del arbol original y comprueba si es vacia, espera TRUE: " + arbolBin.frontera().esVacia());

        System.out.println("Lista con los ancestros de K");
        Lista ancestros = clon.obtenerAncestros('K');
        System.out.println(ancestros.toString());
        System.out.println("Intenta obtener los ancestros de A y comprueba si es vacia, espera TRUE: " + clon.obtenerAncestros('A').esVacia());
        System.out.println("Intenta obtener los ancestros de 1000 y comprueba si es vacia, espera TRUE: " + clon.obtenerAncestros(1000).esVacia());

        System.out.println("Lista con los descendientes de B");
        Lista descendientes = clon.obtenerDescendientes('B');
        System.out.println(descendientes.toString());
        System.out.println("Intenta obtener los descendientes de K y comprueba si es vacia, espera TRUE: " + clon.obtenerDescendientes('K').esVacia());
        System.out.println("Intenta obtener los descendientes de 1000 y comprueba si es vacia, espera TRUE: " + clon.obtenerDescendientes(1000).esVacia());

        System.out.println("Ingrea por insertarPorPosicion al arbol vacio L y espera TRUE: " + arbolBin.insertarPorPosicion('L', 1, 'I'));
        System.out.println(arbolBin.toString());
    }
}
