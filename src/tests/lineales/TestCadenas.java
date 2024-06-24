package tests.lineales;
import lineales.dinamicas.*;

public class TestCadenas {
    public static void main(String[] args) {
        Cola c1 = new Cola();
        c1.poner("a");
        c1.poner("b");
        c1.poner("#");
        c1.poner("c");
        c1.poner("#");
        c1.poner("d");
        c1.poner("e");
        c1.poner("f");
        
        System.out.println("Cola original: " + c1.toString());
        System.out.println("Cola generada: " + generar(c1).toString());
    }

    public static Cola generar(Cola c1) {
        Cola aux = c1.clone();
        Cola colaNueva = new Cola();
        Cola colaAux = new Cola();
        Pila pilaAux = new Pila();
        while(!aux.esVacia()) {
            while(aux.obtenerFrente() != "#" && !aux.esVacia()) {
                colaNueva.poner(aux.obtenerFrente());
                colaAux.poner(aux.obtenerFrente());
                pilaAux.apilar(aux.obtenerFrente());
                aux.sacar();
            }
            while(!pilaAux.esVacia()) {
                colaNueva.poner(pilaAux.obtenerTope());
                pilaAux.desapilar();
            }
            while(!colaAux.esVacia()) {
                colaNueva.poner(colaAux.obtenerFrente());
                colaAux.sacar();
            }
            aux.sacar();
            if (!aux.esVacia())
                colaNueva.poner("#");
        }
        return colaNueva;
    }
}