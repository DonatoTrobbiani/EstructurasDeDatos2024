package tests;
import conjuntistas.ArbolBB;
import jerarquicas.dinamicas.ArbolBin;
import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

public class TestGenerales {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();

        arbol.insertar('A', 0);
        arbol.insertar('H', 'A');
        arbol.insertar('R', 'A');
        arbol.insertar('P', 'A');
        arbol.insertar('J', 'A');
        arbol.insertar('E', 'R');
        arbol.insertar('X', 'R');
        arbol.insertar('Y', 'R');
        arbol.insertar('Z', 'J');

        System.out.println(arbol.toString());
        System.out.println(arbol.jerarquizar('Y'));
        System.out.println(arbol.toString());
    }
}