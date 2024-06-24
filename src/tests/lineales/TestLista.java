package tests.lineales;
import lineales.dinamicas.Lista;

public class TestLista {
    public static void main(String[] args) {
        testingLista();
    }


    public static void testingLista(){
        System.out.println("TESTING LISTA");
        Lista lista = new Lista();
        System.out.println(lista.toString());

        System.out.println("Inserta 1 en pos 1, espera TRUE y [1]: " + lista.insertar(1, 1) + lista.toString());
        System.out.println("Inserta 2 en pos 2, espera TRUE y [1, 2]: " + lista.insertar(2, 2) + lista.toString());
        System.out.println("Inserta 3 en pos 3, espera TRUE y [1, 2, 3]: " + lista.insertar(3, 3) + lista.toString());
        System.out.println("Inserta 4 en pos 4, espera TRUE y [1, 2, 3, 4]: " + lista.insertar(4, 4) + lista.toString());
        System.out.println("Inserta 5 en pos 5, espera TRUE y [1, 2, 3, 4, 5]: " + lista.insertar(5, 5) + lista.toString());

        System.out.println("Inserta 1 en pos 3, espera TRUE y [1, 2, 1, 3, 4, 5]: " + lista.insertar(1, 3) + lista.toString());
        System.out.println("Inserta 7 en pos 1, espera TRUE y [7, 1, 2, 1, 3, 4, 5]: " + lista.insertar(7, 1) + lista.toString());
        System.out.println("Inserta 8 en pos 9, espera FALSE y [7, 1, 2, 1, 3, 4, 5]: " + lista.insertar(8, 9) + lista.toString());
        System.out.println("Inserta 9 en pos 0, espera FALSE y [7, 1, 2, 1, 3, 4, 5]: " + lista.insertar(9, 0) + lista.toString());

        System.out.println("Recupera pos 4, espera 1: " + lista.recuperar(4));
        System.out.println("Recupera pos 10, espera NULL: " + lista.recuperar(10));
        System.out.println("Localiza 1, espera 2: " + lista.localizar(1));
        System.out.println("Localiza 8, espera -1: " + lista.localizar(8));

        System.out.println("Obtiene longitud, espera 7: " + lista.longitud());

        Lista listaClon = lista.clonar();
        System.out.println("Clona lista, espera [7, 1, 2, 1, 3, 4, 5]: " + listaClon.toString());

        System.out.println("Elimina pos 1, espera TRUE y [1, 2, 1, 3, 4, 5]: " + lista.eliminar(1) + lista.toString());
        System.out.println("Elimina pos 5, espera TRUE y [1, 2, 1, 3, 5]: " + lista.eliminar(5) + lista.toString());
        System.out.println("Elimina pos 0, espera FALSE y [1, 2, 1, 3, 5]: " + lista.eliminar(0) + lista.toString());
        System.out.println("Elimina pos 5, espera FALSE y [1, 2, 1, 3, 5]: " + lista.eliminar(5) + lista.toString());

        lista.vaciar();
        System.out.println("Vacía lista, espera lista vacía: "+ lista.toString());

        System.out.println("Verfica si la lista es vacia, espera TRUE: " + lista.esVacia());
        System.out.println("Verfica si la lista clon es vacia, espera FALSE: " + listaClon.esVacia());

        System.out.println("Verifica lista clon, espera [7, 1, 2, 1, 3, 4, 5]: " + listaClon.toString());

        System.out.println("OBTENER MULTIPLOS DE 3");
        System.out.println(listaClon.obtenerMultiplos(3).toString());


        listaClon.vaciar();
        System.out.println("Vacia lista clon, espera lista vacía: " + listaClon.toString());

        System.out.println("Obtiene longitud de lista clon, espera 0: " + listaClon.longitud());
    }
}