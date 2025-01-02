package lineales.dinamicas;

public class Pila {
    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    public boolean apilar(Object nuevoElem) {
        
        //Crea un nuevo nodo delante de la antigua cabecera
        Nodo nuevo = new Nodo(nuevoElem, this.tope);
        
        //Actualiza el tope para que apunte al nodo nuevo
        this.tope = nuevo;
        
        //Nunca hay error de pila llena, tonces devuelve true
        return true;
    }

    public boolean desapilar() {
        boolean exito = false;
        
        //Corrobora que la pila no esté vacía
        if (this.tope != null) {
            //Actualiza el tope para que apunte al nodo siguiente
            this.tope = this.tope.getEnlace();
            exito = true;
        }
        
        return exito;
    }

    public Object obtenerTope() {
        Object elem = null;
        
        //Corrobora que la pila no esté vacía
        if (this.tope != null) {
            //Obtiene el elemento del tope
            elem = this.tope.getElem();
        }
        
        return elem;
    }

    //Comprueba si la pila está vacía
    public boolean esVacia() {
        return this.tope == null;
    }

    // Vacia la pila.
    // Por el gargabe collector, no es necesario eliminar todos los nodos.
    public void vaciar() {
        this.tope = null;
    }

    public Pila clone() {
        Pila clon = new Pila();
        Nodo aux = this.tope;
        if (aux != null) {
            clon.tope = new Nodo(aux.getElem(), null);
            Nodo auxClon = clon.tope;
            aux = aux.getEnlace();
            while (aux != null) {
                auxClon.setEnlace(new Nodo(aux.getElem(), null));
                auxClon = auxClon.getEnlace();
                aux = aux.getEnlace();
            }
        }
        return clon;
    }
        
    public String toString() {
        String cadena = "[ ";
        if (this.tope != null) {
            Nodo aux = this.tope;    
            while (aux != null) {
                cadena += aux.getElem().toString() + " ";
                aux = aux.getEnlace();
            }
        }
        return cadena += "]";
    }
}