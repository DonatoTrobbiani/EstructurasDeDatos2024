package tests.lineales;
import lineales.dinamicas.*;

public class Matematica {
    public static void main(String[] args) {
        Cola q = new Cola();
        q.poner("{");
        q.poner("5");
        q.poner("+");
        q.poner("[");
        q.poner("8");
        q.poner("*");
        q.poner("9");
        q.poner("-");
        q.poner("(");
        q.poner("4");
        q.poner("/");
        q.poner("2");
        q.poner(")");
        q.poner("+");
        q.poner("7");
        q.poner("]");
        q.poner("-");
        q.poner("1");
        q.poner("}");
        System.out.println("Cola original: " + q.toString());
        System.out.println("Balanceo: " + verificarBalanceo(q));
    }

    public static boolean verificarBalanceo(Cola q) {
        boolean respuesta = true;
        Cola aux = q.clone();
        Pila pila = new Pila();
        while (!aux.esVacia() && respuesta) {
            if(aux.obtenerFrente() == "(" || aux.obtenerFrente() == "[" || aux.obtenerFrente() == "{") {
                pila.apilar(aux.obtenerFrente());
                aux.sacar();
            } else if (aux.obtenerFrente() == ")") {
                if (pila.obtenerTope() == "(") {
                    pila.desapilar();
                    aux.sacar();
                } else {
                    respuesta = false;
                }
            } else if (aux.obtenerFrente() == "]") {
                if (pila.obtenerTope() == "[") {
                    pila.desapilar();
                    aux.sacar();
                } else {
                    respuesta = false;
                }
            } else if (aux.obtenerFrente() == "}") {
                if (pila.obtenerTope() == "{") {
                    pila.desapilar();
                    aux.sacar();
                } else {
                    respuesta = false;
                }
            } else {
                aux.sacar();
            }
        }
        if (!pila.esVacia()) {
            respuesta = false;
        }
        return respuesta;
    }
}
