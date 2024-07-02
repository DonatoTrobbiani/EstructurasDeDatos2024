package tests.lineales;
import lineales.dinamicas.*;

public class TestLineales {
    public static void main(String[] args) {
        Cola cola = new Cola();
        
        cola.poner(0);
        cola.poner(1);
        cola.poner(2);
        cola.poner(3);
        cola.poner(4);
        cola.poner(5);
        cola.poner(6);
        cola.poner(7);
        cola.poner(8);
        cola.poner(9);

        System.out.println(cola.toString());
        System.out.println(generarSecuencia(cola, 4).toString());
    }

    public static Lista invertirVocalesDuplicarSiVocales(Cola q) {
        Cola clon = q.clone();
        Lista lista = new Lista();
        Pila pila = new Pila();
        Cola cola = new Cola();
        boolean tieneVocal = false;
        int pos = 1;

        while (!clon.esVacia()) {
            char elemento = (char) clon.obtenerFrente();
            clon.sacar();
            if(elemento != '#'){
                if(elemento == 'a' || elemento == 'e' || elemento == 'i' || elemento == 'o' || elemento == 'u'){
                    tieneVocal = true;
                    pila.apilar(elemento);
                }else{
                    cola.poner(elemento);
                }
            }else{
                if (tieneVocal) {
                    while(!pila.esVacia()){
                        lista.insertar(pila.obtenerTope(), pos);
                        pos++;
                        pila.desapilar();
                    }
                    tieneVocal = false;
                }else{
                    Cola aux = cola.clone();
                    while (!aux.esVacia()) {
                        lista.insertar(aux.obtenerFrente(), pos);
                        pos++;
                        aux.sacar();
                    }
                    while (!cola.esVacia()) {
                        lista.insertar(cola.obtenerFrente(), pos);
                        pos++;
                        cola.sacar();
                    }
                }
                pila.vaciar();
                cola.vaciar();
                lista.insertar('#', pos);
                pos++;
            }
        }
        return lista;
    }

    public static Lista generarSecuencia(Cola q, int t) {
        Cola aux = q.clone();
        Lista lista = new Lista();
        
        Cola cola = new Cola();
        Pila pila = new Pila();

        while (!aux.esVacia()) {
            int i = 0;
            int elemento;
            while (i < t && !aux.esVacia()) {
                elemento = (int) aux.obtenerFrente();
                cola.poner(elemento);
                pila.apilar(elemento);
                aux.sacar();
                i++;
            }
            while (!pila.esVacia()) {
                lista.insertar(pila.obtenerTope(), lista.longitud()+1);
                pila.desapilar();
            }
            while (!cola.esVacia()) {
                lista.insertar(cola.obtenerFrente(), lista.longitud()+1);
                cola.sacar();
            }
            if (!aux.esVacia()) {
                lista.insertar('$', lista.longitud()+1);
            }
        }
        return lista;
    }
}