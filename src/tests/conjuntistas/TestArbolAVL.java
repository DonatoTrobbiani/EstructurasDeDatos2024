package tests.conjuntistas;
import conjuntistas.ArbolAVL;

public class TestArbolAVL {
    public static void main(String[] args) {
        testingArbolAVL();
    }

    public static void testingArbolAVL() {
        System.out.println("TESTING ARBOL AVL");
        ArbolAVL arbolAVL = new ArbolAVL();
        System.out.println(arbolAVL.toString());

        System.out.println("PROBANDO INSERTAR");
        System.out.println("Inserta 10, espera TRUE: " + arbolAVL.insertar(10));
        System.out.println("Inserta 5, espera TRUE: " + arbolAVL.insertar(5));
        System.out.println("Inserta 15, espera TRUE: " + arbolAVL.insertar(15));
        System.out.println("Inserta 3, espera TRUE: " + arbolAVL.insertar(3));
        System.out.println("Inserta 7, espera TRUE: " + arbolAVL.insertar(7));
        System.out.println("Inserta 13, espera TRUE: " + arbolAVL.insertar(13));
        System.out.println("Inserta 17, espera TRUE: " + arbolAVL.insertar(17));
        System.out.println("Inserta 2, espera TRUE: " + arbolAVL.insertar(2));
        System.out.println("Inserta 4, espera TRUE: " + arbolAVL.insertar(4));
        System.out.println("Inserta 6, espera TRUE: " + arbolAVL.insertar(6));
        System.out.println("Inserta 8, espera TRUE: " + arbolAVL.insertar(8));
        System.out.println("Inserta 12, espera TRUE: " + arbolAVL.insertar(12));
        System.out.println("Inserta 14, espera TRUE: " + arbolAVL.insertar(14));
        System.out.println("Inserta 16, espera TRUE: " + arbolAVL.insertar(16));
        System.out.println("Inserta 18, espera TRUE: " + arbolAVL.insertar(18));
        System.out.println("Inserta 1, espera TRUE: " + arbolAVL.insertar(1));
        System.out.println("Inserta 9, espera TRUE: " + arbolAVL.insertar(9));
        System.out.println("Inserta 11, espera TRUE: " + arbolAVL.insertar(11));
        System.out.println("Inserta 19, espera TRUE: " + arbolAVL.insertar(19));

        System.out.println(arbolAVL.toString());

        System.out.println("Inserta 20, tiene que hacer rotacion simple y 19 tiene que quedar como padre de 18 y 20, espera TRUE: " + arbolAVL.insertar(20));

        System.out.println(arbolAVL.toString());
    }
}
