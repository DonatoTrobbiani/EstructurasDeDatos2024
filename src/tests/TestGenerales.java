package tests;
import conjuntistas.ArbolBB;
import jerarquicas.dinamicas.ArbolBin;
import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

public class TestGenerales {
    public static void main(String[] args) {
        ArbolGen arbolGen = new ArbolGen();

        arbolGen.insertar(1, "padre");
        arbolGen.insertar(2, 1);
        arbolGen.insertar(3, 1);
        arbolGen.insertar(4, 1);
        arbolGen.insertar(5, 2);
        arbolGen.insertar(6, 2);
        arbolGen.insertar(7, 2);
        arbolGen.insertar(8, 3);
        arbolGen.insertar(9, 3);

        System.out.println(arbolGen.toString());

        System.out.println();
        System.out.println();
        System.out.println();
        arbolGen.clone();

        System.out.println(arbolGen.toString());
    }
}