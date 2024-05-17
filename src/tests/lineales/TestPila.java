package tests.lineales;
import lineales.estaticas.Pila;
import java.util.Scanner;

public class TestPila {
    public static void main(String[] args) {
        testingPila();
    }


    public static void testingPila() {
        
        System.out.println("TESTING PILA");
        Pila pila = new Pila();
        pila.toString();

        System.out.println("Apila 1, espera TRUE y [1]: " + pila.apilar(1));
        pila.toString();
        System.out.println("Apila 2, espera TRUE y [1, 2]: " + pila.apilar(2));
        pila.toString();
        System.out.println("Apila 3, espera TRUE y [1, 2, 3]: " + pila.apilar(3));
        pila.toString();
        System.out.println("Apila 4, espera TRUE y [1, 2, 3, 4]: " + pila.apilar(4));
        pila.toString();
        System.out.println("Apila 5, espera TRUE y [1, 2, 3, 4, 5]: " + pila.apilar(5));
        pila.toString();
        System.out.println("Apila 6, espera TRUE y [1, 2, 3, 4, 5, 6]: " + pila.apilar(6));
        pila.toString();
        System.out.println("Apila 7, espera TRUE y [1, 2, 3, 4, 5, 6, 7]: " + pila.apilar(7));
        pila.toString();
        System.out.println("Apila 8, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 8]: " + pila.apilar(8));
        pila.toString();
        System.out.println("Apila 9, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 8, 9]: " + pila.apilar(9));
        pila.toString();
        System.out.println("Apila 10, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]: " + pila.apilar(10));
        pila.toString();
        System.out.println("Apila 11, espera FALSE en estatica y TRUE en dinamica: " + pila.apilar(11));
        pila.toString();
        if ((int) pila.obtenerTope() == 11) {
            System.out.println("Si pudo apilar 11, lo saca para continuar");
            pila.desapilar();
        }

        System.out.println("Recupera tope, espera 10: "+pila.obtenerTope());

        System.out.println("Desapila, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 8, 9]: " + pila.desapilar());
        pila.toString();
        System.out.println("Desapila, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 8]: " + pila.desapilar());
        pila.toString();
        System.out.println("Desapila, espera TRUE y [1, 2, 3, 4, 5, 6, 7]: " + pila.desapilar());
        pila.toString();
        System.out.println("Apila 2, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 2]: " + pila.apilar(2));
        pila.toString();

        System.out.println("Recupera tope, espera 2: "+pila.obtenerTope());

        Pila pila2 = pila.clone();
        System.out.println("Copia pila, espera [1, 2, 3, 4, 5, 6, 7, 2]: " + pila2.toString());

        while (!pila.esVacia()) {
            System.out.println("Desapila, espera TRUE: " + pila.desapilar());
            pila.toString();
        }

        System.out.println("Se vació la pila");
        pila.toString();
        System.out.println("Desapila en pila vacia, espera FALSE: " + pila.desapilar());
        System.out.println("Obtien tope en pila vacia, espera NULL: " + pila.obtenerTope());

        System.out.println("Verifica pila copia, espera [1, 2, 3, 4, 5, 6, 7, 2]: " + pila2.toString());
        System.out.println("Apila 5, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 2, 5]: " + pila2.apilar(5));
        System.out.println("Verifica pila copia, espera [1, 2, 3, 4, 5, 6, 7, 2, 5]: " + pila2.toString());
        System.out.println("Apila 12, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 2, 5, 12]: " + pila2.apilar(12));
        System.out.println("Apila 17, espera TRUE en dinamica y FALSE en estatica: " + pila2.apilar(17));

        if((int) pila2.obtenerTope() == 17){
            System.out.println("Si pudo apilar 17, lo saca para continuar");
            pila2.desapilar();
        }

        System.out.println("Verifica pila copia, espera [1, 2, 3, 4, 5, 6, 7, 2, 5, 12]: " + pila2.toString());
        System.out.println("Desapila y espera TRUE:" + pila2.desapilar());
        System.out.println("Desapila, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 2]: " + pila2.desapilar());
        pila2.toString();
        pila2.vaciar();
        System.out.println("Vacia pila copia, espera pila vacia: " + pila2.toString());
    }
}