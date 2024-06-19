package tests.conjuntistas;
import conjuntistas.HeapMin;

public class TestHeapMin {
    public static void main(String[] args) {
        testingHeapMin();
    }

    public static void testingHeapMin() {
        System.out.println("TESTING HEAP MIN");
        HeapMin heapMin = new HeapMin();
        System.out.println(heapMin.toString());

        System.out.println("Inserta 1, espera TRUE: " + heapMin.insertar(1));
        System.out.println("Inserta 5, espera TRUE: " + heapMin.insertar(5));
        System.out.println("Inserta 7, espera TRUE: " + heapMin.insertar(7));
        System.out.println("Inserta 8, espera TRUE: " + heapMin.insertar(8));
        System.out.println("Inserta 11, espera TRUE: " + heapMin.insertar(11));
        System.out.println("Inserta 14, espera TRUE: " + heapMin.insertar(14));
        System.out.println("Inserta 19, espera TRUE: " + heapMin.insertar(19));

        System.out.println(heapMin.toString());

        System.out.println("Inserta 12, espera TRUE: " + heapMin.insertar(12));
        System.out.println("Inserta 3, espera TRUE: " + heapMin.insertar(3));

        System.out.println(heapMin.toString());

        System.out.println("Recupera cima, espera 1: " + heapMin.recuperarCima());
        System.out.println("Elimina cima, espera TRUE: " + heapMin.eliminarCima());
        System.out.println("Recupera cima, espera 3: " + heapMin.recuperarCima());
        System.out.println("Elimina cima, espera TRUE: " + heapMin.eliminarCima());
        System.out.println("Recupera cima, espera 5: " + heapMin.recuperarCima());
        System.out.println("Elimina cima, espera TRUE: " + heapMin.eliminarCima());
        System.out.println("Recupera cima, espera 7: " + heapMin.recuperarCima());

        System.out.println(heapMin.toString());

        System.out.println("Vacia el heap");
        heapMin = new HeapMin();

        System.out.println(heapMin.toString());
        
        System.out.println("Intenta elminar cima, espera FALSE: " + heapMin.eliminarCima());
        System.out.println("Intenta recuperar cima, espera NULL: " + heapMin.recuperarCima());

        System.out.println("Llena el heap");
        for (int i = 1; i < 20; i++) {
            System.out.println("Inserta " + i + ", espera TRUE: " + heapMin.insertar(i));
        }

        System.out.println(heapMin.toString());


        System.out.println("Intenta insertar 30, espera FALSE por heap lleno: " + heapMin.insertar(30));

        System.out.println("FINALIZA PRUEBA HEAP MIN");
    }
}
