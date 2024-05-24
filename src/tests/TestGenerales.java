package tests;
import jerarquicas.dinamicas.ArbolBin;

public class TestGenerales {
    public static void main(String[] args) {
        ArbolBin arbol = new ArbolBin();
        System.out.println("Arbol vacio: " + arbol.toString());
        System.out.println("Insertar 1 en raiz: " + arbol.insertar(1, null, ' '));
        System.out.println("Insertar 2 en raiz: " + arbol.insertar(2, null, ' '));
        System.out.println("Insertar 3 en hijo izquierdo de raiz: " + arbol.insertar(3, 1, 'I'));
        System.out.println("Insertar 4 en hijo derecho de raiz: " + arbol.insertar(4, 1, 'D'));
        System.out.println(arbol.toString());
    }
}