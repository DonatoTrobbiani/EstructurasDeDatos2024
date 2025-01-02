package tests.conjuntistas;
import conjuntistas.ArbolAVL;

public class TestArbolAVL {
    public static void main(String[] args) {
        //testingInsertarAVL();
        testingEliminarAVL();
    }

    public static void testingInsertarAVL() {
        ArbolAVL arbol = new ArbolAVL();

        // Insert elements to cause right rotation
        System.out.println("Prueba de rotación derecha");
        arbol.insertar(30);
        arbol.insertar(20);
        System.out.println(arbol.toString());
        arbol.insertar(10); // Right rotation should occur here
        System.out.println(arbol.toString());

        // Insert elements to cause left rotation
        System.out.println("Prueba de rotación izquierda");
        arbol = new ArbolAVL();
        arbol.insertar(10);
        arbol.insertar(20);
        System.out.println(arbol.toString());
        arbol.insertar(30); // Left rotation should occur here
        System.out.println(arbol.toString());

        // Insert elements to cause left-right rotation
        System.out.println("Prueba de rotación izquierda-derecha");
        arbol = new ArbolAVL();
        arbol.insertar(30);
        arbol.insertar(10);
        System.out.println(arbol.toString());
        arbol.insertar(20); // Left-right rotation should occur here
        System.out.println(arbol.toString());

        // Insert elements to cause right-left rotation
        System.out.println("Prueba de rotación derecha-izquierda");
        arbol = new ArbolAVL();
        arbol.insertar(10);
        arbol.insertar(30);
        System.out.println(arbol.toString());
        arbol.insertar(20); // Right-left rotation should occur here
        System.out.println(arbol.toString());
    }

    public static void testingEliminarAVL() {
        ArbolAVL arbol = new ArbolAVL();

        System.out.println("Probando el elminar");
        arbol.insertar(75);
        arbol.insertar(20);
        arbol.insertar(15);
        arbol.insertar(80);
        arbol.insertar(93);
        arbol.insertar(77);
        arbol.insertar(18);
        arbol.insertar(78);
        arbol.insertar(13);
        arbol.insertar(14);
        arbol.insertar(25);
        arbol.insertar(16);
        System.out.println(arbol.toString());
        System.out.println("Eliminando 93, se hace una rotacion doble izquierda-derecha en la rama derecha, y luego una rotacion a derecha en la rama izquierda");
        System.out.println(arbol.eliminar(75));
        System.out.println(arbol.toString());
    }
}
