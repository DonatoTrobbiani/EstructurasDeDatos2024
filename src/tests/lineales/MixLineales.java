package tests.lineales;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;

public class MixLineales {
    public static void main(String[] args) {
        Cola cola = new Cola();
        cola.poner("A");
        cola.poner("B");
        cola.poner("$");
        cola.poner("C");
        cola.poner("$");
        cola.poner("D");
        cola.poner("E");
        cola.poner("F");
        System.out.println(cola.toString());
        Cola nuevaCola = generarOtraCola(cola);
        System.out.println(nuevaCola.toString());
    }

    public static Cola generarOtraCola(Cola c1) {
        Cola cola = c1.clone();
        Pila pilaAux = new Pila();
        Cola colaInvertida = new Cola();
        
        while(!cola.esVacia()) {
            Object elem = cola.obtenerFrente();
            if(elem != "$") {
            
                pilaAux.apilar(elem);
                colaInvertida.poner(elem);
                cola.sacar();
            
            } else {
                while (!pilaAux.esVacia()) {

                    colaInvertida.poner(pilaAux.obtenerTope());
                    pilaAux.desapilar();

                }
                colaInvertida.poner(elem);
                cola.sacar();  
            }
        }
        while (!pilaAux.esVacia()) {

            colaInvertida.poner(pilaAux.obtenerTope());
            pilaAux.desapilar();

        }
        return colaInvertida;
    }
}
