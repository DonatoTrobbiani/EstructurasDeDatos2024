package tests.lineales;
import lineales.dinamicas.*;

public class TestLineales {
    public static void main(String[] args) {
        
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
}