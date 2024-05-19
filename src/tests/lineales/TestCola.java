package tests.lineales;
import lineales.dinamicas.Cola;

public class TestCola {
    public static void main(String[] args) {
        testingCola();
    }


    public static void testingCola() {
        System.out.println("TESTING COLA");
        Cola cola = new Cola();
        System.out.println(cola.toString());

        System.out.println("Pone 1, espera TRUE y [1]: " + cola.poner(1) + cola.toString());
        System.out.println("Pone 2, espera TRUE y [1, 2]: " + cola.poner(2) + cola.toString());
        System.out.println("Pone 3, espera TRUE y [1, 2, 3]: " + cola.poner(3) + cola.toString());
        System.out.println("Pone 4, espera TRUE y [1, 2, 3, 4]: " + cola.poner(4) + cola.toString());
        System.out.println("Pone 5, espera TRUE y [1, 2, 3, 4, 5]: " + cola.poner(5) + cola.toString());
        System.out.println("Pone 6, espera TRUE y [1, 2, 3, 4, 5, 6]: " + cola.poner(6) + cola.toString());
        System.out.println("Pone 7, espera TRUE y [1, 2, 3, 4, 5, 6, 7]: " + cola.poner(7) + cola.toString());
        System.out.println("Pone 8, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 8]: " + cola.poner(8) + cola.toString());
        System.out.println("Pone 9, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 8, 9]: " + cola.poner(9) + cola.toString());
        System.out.println("Pone 10, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]: " + cola.poner(10) + cola.toString());
        System.out.println("Pone 11, espera FALSE en estatica y TRUE en dinamica: " + cola.poner(11) + cola.toString());

        System.out.println("Recupera frente, espera 1: "+cola.obtenerFrente());

        System.out.println("Saca, espera TRUE y [2, 3, 4, 5, 6, 7, 8, 9, 10(, 11)]: " + cola.sacar() + cola.toString());
        System.out.println("Saca, espera TRUE y [3, 4, 5, 6, 7, 8, 9, 10(, 11)]: " + cola.sacar() + cola.toString());
        System.out.println("Saca, espera TRUE y [4, 5, 6, 7, 8, 9, 10(, 11)]: " + cola.sacar() + cola.toString());
        System.out.println("Pone 20, espera TRUE y [4, 5, 6, 7, 8, 9, 10,( 11,) 20]: " + cola.poner(20) + cola.toString());

        System.out.println("Recupera frente, espera 4: "+cola.obtenerFrente());

        Cola cola2 = cola.clone();
        System.out.println("Copia cola, espera [4, 5, 6, 7, 8, 9, 10,( 11,) 20]: " + cola2.toString());

        while (!cola.esVacia()) {
            System.out.println("Saca, espera TRUE: " + cola.sacar());
            System.out.println(cola.toString());
        }

        System.out.println("Se vació la cola");
        System.out.println(cola.toString());
        System.out.println("Saca en cola vacia, espera FALSE: " + cola.sacar());
        System.out.println("Obtiene frente en cola vacia, espera NULL: " + cola.obtenerFrente());

        System.out.println("Verifica cola copia, espera [4, 5, 6, 7, 8, 9, 10,( 11,) 20]: " + cola2.toString());
        System.out.println("Pone 5, espera TRUE y [4, 5, 6, 7, 8, 9, 10,( 11,) 20, 5]: " + cola2.poner(5) + cola2.toString());
        System.out.println("Pone 17, espera TRUE y [4, 5, 6, 7, 8, 9, 10,( 11,) 20, 5, 17] " + cola2.poner(17) + cola2.toString());
        System.out.println("Pone 25, espera TRUE en dinamica y FALSE en estatica: " + cola2.poner(25) + cola2.toString());

        System.out.println("Verifica cola copia, espera [4, 5, 6, 7, 8, 9, 10,( 11,) 20, 5, 17(, 25)]: " + cola2.toString());
        System.out.println("Saca y espera TRUE: " + cola2.sacar());
        System.out.println("Saca, espera TRUE y [6, 7, 8, 9, 10,( 11,) 20, 5, 17(, 25)]: " + cola2.sacar() + cola2.toString());
        cola2.vaciar();
        System.out.println("Vacia cola copia, espera cola vacia: " + cola2.toString());
    }
}