package tests.lineales;
import lineales.estaticas.Pila;

public class TestPila {
    public static void main(String[] args) {
        testingPila();
        comprobarCapicua();
    }


    public static void testingPila() {
        
        System.out.println("TESTING PILA");
        Pila pila = new Pila();
        System.out.println(pila.toString());

        System.out.println("Apila 1, espera TRUE y [1]: " + pila.apilar(1) + pila.toString());
        System.out.println("Apila 2, espera TRUE y [1, 2]: " + pila.apilar(2) + pila.toString());
        System.out.println("Apila 3, espera TRUE y [1, 2, 3]: " + pila.apilar(3) + pila.toString());
        System.out.println("Apila 4, espera TRUE y [1, 2, 3, 4]: " + pila.apilar(4) + pila.toString());
        System.out.println("Apila 5, espera TRUE y [1, 2, 3, 4, 5]: " + pila.apilar(5) + pila.toString());
        System.out.println("Apila 6, espera TRUE y [1, 2, 3, 4, 5, 6]: " + pila.apilar(6) + pila.toString());
        System.out.println("Apila 7, espera TRUE y [1, 2, 3, 4, 5, 6, 7]: " + pila.apilar(7) + pila.toString());
        System.out.println("Apila 8, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 8]: " + pila.apilar(8) + pila.toString());
        System.out.println("Apila 9, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 8, 9]: " + pila.apilar(9) + pila.toString());
        System.out.println("Apila 10, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]: " + pila.apilar(10) + pila.toString());
        System.out.println("Apila 11, espera FALSE en estatica y TRUE en dinamica: " + pila.apilar(11) + pila.toString());
        if ((int) pila.obtenerTope() == 11) {
            System.out.println("Si pudo apilar 11, lo saca para continuar");
            pila.desapilar();
        }

        System.out.println("Recupera tope, espera 10: "+pila.obtenerTope());

        System.out.println("Desapila, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 8, 9]: " + pila.desapilar() + pila.toString());
        System.out.println("Desapila, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 8]: " + pila.desapilar() + pila.toString());
        System.out.println("Desapila, espera TRUE y [1, 2, 3, 4, 5, 6, 7]: " + pila.desapilar() + pila.toString());
        System.out.println("Apila 2, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 2]: " + pila.apilar(2) + pila.toString());

        System.out.println("Recupera tope, espera 2: "+pila.obtenerTope());

        Pila pila2 = pila.clone();
        System.out.println("Copia pila, espera [1, 2, 3, 4, 5, 6, 7, 2]: " + pila2.toString());

        while (!pila.esVacia()) {
            System.out.println("Desapila, espera TRUE: " + pila.desapilar());
            System.out.println(pila.toString());
        }

        System.out.println("Se vació la pila");
        System.out.println(pila.toString());
        System.out.println("Desapila en pila vacia, espera FALSE: " + pila.desapilar());
        System.out.println("Obtiene tope en pila vacia, espera NULL: " + pila.obtenerTope());

        System.out.println("Verifica pila copia, espera [1, 2, 3, 4, 5, 6, 7, 2]: " + pila2.toString());
        System.out.println("Apila 5, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 2, 5]: " + pila2.apilar(5) + pila2.toString());
        System.out.println("Apila 12, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 2, 5, 12]: " + pila2.apilar(12));
        System.out.println("Apila 17, espera TRUE en dinamica y FALSE en estatica: " + pila2.apilar(17));

        if((int) pila2.obtenerTope() == 17){
            System.out.println("Si pudo apilar 17, lo saca para continuar");
            pila2.desapilar();
        }

        System.out.println("Verifica pila copia, espera [1, 2, 3, 4, 5, 6, 7, 2, 5, 12]: " + pila2.toString());
        System.out.println("Desapila y espera TRUE:" + pila2.desapilar());
        System.out.println("Desapila, espera TRUE y [1, 2, 3, 4, 5, 6, 7, 2]: " + pila2.desapilar());
        System.out.println(pila.toString());
        pila2.vaciar();
        System.out.println("Vacia pila copia, espera pila vacia: " + pila2.toString());
    }

    public static void comprobarCapicua() {
        System.out.println("COMPROBAR CAPICUA");
        Pila pila = new Pila();
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);
        pila.apilar(4);
        pila.apilar(5);
        pila.apilar(4);
        pila.apilar(3);
        pila.apilar(2);
        pila.apilar(1);
        System.out.println("Pila: " + pila.toString());

        System.out.println("La pila es capicua: "+esCapicua(pila));
    }

    public static boolean esCapicua(Pila pila) {
        //Clono la pila original 2 veces para no tocar la original
        Pila pilaAux = pila.clone();
        Pila pilaClon = pila.clone();
        Pila pilaAux2 = new Pila();
        boolean comprueba = true;
        
        //Invierto una pila
        while (!pilaClon.esVacia()) {
            pilaAux2.apilar(pilaClon.obtenerTope());
            pilaClon.desapilar();
        }

        //Voy comparando las pilas hasta encontrar una diferencia, si lo hace no es capicua
        while (comprueba && !pilaAux.esVacia()) {
            if (!pilaAux.obtenerTope().equals(pilaAux2.obtenerTope())) {
                comprueba = false;
            }
            pilaAux.desapilar();
            pilaAux2.desapilar();
        }
        return comprueba;
    }
}