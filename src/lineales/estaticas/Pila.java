package lineales.estaticas;

public class Pila {
    private static final int tamanio = 10;
    private Object[] pila;
    private int tope;

    public Pila() {
        this.pila = new Object[tamanio];
        this.tope = -1;
    }

    //Agrega un elemento a la pila
    public boolean apilar(Object nuevoElem) {
        boolean exito = false;
        //Corrobora que la pila no esté llena
        if (this.tope + 1 < tamanio) {
            this.tope++;
            this.pila[this.tope] = nuevoElem;
            exito = true;
        }
        return exito;
    }

    //Quita un elemento de la pila
    public boolean desapilar() {
        boolean exito = false;
        //Corrobora que la pila no esté vacía
        if (this.tope > -1) {
            this.pila[this.tope] = null;
            this.tope--;
            exito = true;
        }
        return exito;
    }

    //Devuelve el elemento que está en el tope de la pila
    public Object obtenerTope() {
        Object elem = null;
        //Corrobora que la pila no esté vacía
        if (this.tope > -1) {
            elem = this.pila[this.tope];
        }
        return elem;
    }

    //Comprueba si la pila está vacía
    public boolean esVacia() {
        boolean exito = false;
        //Corrobora si la pila está vacía
        if (this.tope == -1) {
            exito = true;
        }
        return exito;
    }

    //Vacia la pila
    public void vaciar() {
        while (this.tope != -1) {
            this.pila[this.tope] = null;
            this.tope--;
        }
    }

    //Clona la pila
    public Pila clone() {
        Pila clon = new Pila();
        Pila aux = new Pila();
        //Se copian los elementos de la pila original a la auxiliar (en orden inverso)
        for (int i = 0; i <= this.tope; i++) {
            aux.apilar(this.pila[i]);
        }
        //Se copian los elementos de la pila auxiliar a la clon (en orden original)
        for (int i = 0; i <= aux.tope; i++) {
            clon.apilar(aux.pila[i]);
        }
        return clon;
    }

    //Muestra la pila
    public String toString() {
        String cadena = "[ ";
        for (int i = 0; i <= this.tope; i++) {
            cadena += this.pila[i] + " ";
        }
        cadena += "]";
        return cadena;
    }
}