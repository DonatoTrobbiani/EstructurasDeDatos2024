package tests;
import conjuntistas.ArbolBB;
import jerarquicas.dinamicas.ArbolBin;
import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

public class TestGenerales {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        ArbolBB arbolBB = new ArbolBB();
        Lista lista = new Lista();
        Lista lista2 = new Lista();
        ArbolBin arbolBinario = new ArbolBin();
        Lista patron = new Lista();

        arbol.insertar(20, null);
        arbol.insertar(13, 20);
        arbol.insertar(3, 20);
        arbol.insertar(18, 20);
        arbol.insertar(15, 13);
        arbol.insertar(12, 13);
        arbol.insertar(5, 3);
        arbol.insertar(22, 3);
        arbol.insertar(16, 5);
        arbol.insertar(1, 5);
        arbol.insertar(24, 5);
        arbol.insertar(9, 5);
        arbol.insertar(31, 5);
        arbol.insertar(11, 18);
        arbol.insertar(27, 18);
        arbol.insertar(8, 27);
        arbol.insertar(18, 27);

        System.out.println(arbol.toString());
        System.out.println(arbol.orden());

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

        arbolBinario.insertar(1, 0, 'I');
        arbolBinario.insertar(2, 1, 'I');
        arbolBinario.insertar(3, 1, 'D');
        arbolBinario.insertar(4, 2, 'I');
        arbolBinario.insertar(5, 2, 'D');
        arbolBinario.insertar(6, 3, 'I');
        arbolBinario.insertar(7, 3, 'D');

        patron.insertar(1, 1);
        patron.insertar(3, 2);
        patron.insertar(7, 3);

        lista.agregarElemento(1, 2);
        System.out.println(lista.toString());

        System.out.println(arbolBinario.toString());
        System.out.println("Invirtiendo arbol...");
        System.out.println(arbolBinario.clonarInvertido().toString());

        System.out.println(arbolBinario.toString());
        System.out.println("Es camino?" + arbolBinario.verificarPatron(patron));

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