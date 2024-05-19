package lineales.estaticas;

public class Cola {
    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    public Cola() {
        this.arreglo = new Object[TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    public boolean poner(Object elem) {
        boolean exito = false;
        int proximoFin = (this.fin + 1) % TAMANIO;
        if (this.frente != proximoFin) {
            this.arreglo[this.fin] = elem;
            this.fin = proximoFin;
            exito = true;
        }
        return exito;
    }

    public boolean sacar() {
        boolean exito = false;
        if (this.frente != this.fin) {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % TAMANIO;
            exito = true;
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object elem = null;
        if (this.frente != this.fin) {
            elem = this.arreglo[this.frente];
        }
        return elem;
    }

    public boolean esVacia() {
        return this.frente == this.fin;
    }

    public void vaciar() {
        while (this.frente != this.fin) {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % TAMANIO;
        }
    }

    public Cola clone() {
        Cola clon = new Cola();
        clon.frente = this.frente;
        clon.fin = this.fin;
        for (int i = 0; i < TAMANIO; i++) {
            clon.arreglo[i] = this.arreglo[i];
        }
        return clon;
    }

    public String toString() {
        String cadena = "[ ";
        //Comprueba que no esté vacía
        if (this.frente != this.fin) {
            //Recorre el arreglo desde el frente hasta el fin, copiando los elementos en la cadena
            for (int i = this.frente; i != this.fin; i = (i + 1) % TAMANIO) {
                cadena += this.arreglo[i] + " ";
            }
        }
        cadena += "]";
        return cadena;
    }
}