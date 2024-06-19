package tests;
import conjuntistas.ArbolBB;
import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

public class TestGenerales {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        ArbolBB arbolBB = new ArbolBB();
        Lista lista = new Lista();
        Lista lista2 = new Lista();
        arbol.insertar(20, null);
        arbol.insertar(13, 20);
        arbol.insertar(15, 13);
        arbol.insertar(12, 13);
        arbol.insertar(54, 20);
        arbol.insertar(11, 54);
        arbol.insertar(27, 54);
        arbol.insertar(4, 54);
        arbol.insertar(17, 27);

        System.out.println(arbol.toString());

        arbolBB.insertar(56);
        arbolBB.insertar(13);
        arbolBB.insertar(7);
        arbolBB.insertar(24);
        arbolBB.insertar(15);
        arbolBB.insertar(78);
        arbolBB.insertar(100);


        lista.insertar(20, 1);
        lista.insertar(54, 2);
        lista.insertar(27, 3);
        lista.insertar(17, 4);

        System.out.println("Es camino: " + arbol.verificarCamino(lista));

        lista2 = arbol.listarEntreNiveles(1, 3);
        System.out.println(lista2.toString());

        arbol.eliminarDescendientes(54);
        System.out.println(arbol.toString());

        arbolBB.elminarMinimo();
        System.out.println(arbolBB.toString());

        ArbolBB clon = arbolBB.clonarParteInvertida(13);
        System.out.println(clon.toString());
    }
}