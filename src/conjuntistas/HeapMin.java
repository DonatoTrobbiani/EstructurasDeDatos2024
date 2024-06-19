package conjuntistas;

public class HeapMin {

    private Comparable[] heap;
    private int ultimo;
    private int tamanio = 20;

    public HeapMin() {
        this.heap = new Comparable[tamanio];
        this.ultimo = 0; //La posicion 0 nunca es utilizada
    }

    public boolean insertar(Comparable elem) {
        boolean exito;
        if (this.ultimo + 1 >= this.tamanio) {
            //El arreglo esta lleno, no se puede insertar
            exito = false;
        } else {
            //Agrega el elemento al final del arreglo
            this.ultimo++;
            this.heap[ultimo] = elem;
            //Reestablece la propiedad de heap minimo
            hacerSubir(ultimo);
            exito = true;
        }
        return exito;
    }

    private void hacerSubir(int posHijo) {
        int posPadre;
        Comparable temp = this.heap[posHijo];
        boolean salir = false;

        while (posHijo > 1 && !salir) {
            posPadre = posHijo / 2;
            //compara al hijo con el padre
            if (temp.compareTo(this.heap[posPadre]) < 0) {
                //el padre es mayor que el hijo, los intercambia
                this.heap[posHijo] = this.heap[posPadre];
                posHijo = posPadre;
            } else {
                //el padre es menor que el hijo, esta en su lugar
                salir = true;
            }
        }
        this.heap[posHijo] = temp;
    }

    public boolean eliminarCima() {
        boolean exito;
        if (this.ultimo == 0) {
            //Estructura vacia
            exito = false;
        } else {
            //saca la raiz y pone la ultima hoja en su lugar
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            //Reestablece la propiedad de heap minimo
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    private void hacerBajar(int posPadre) {
        int posHijo;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posHijo = posPadre * 2;
            if (posHijo <= this.ultimo) {
                //temp tiene al menos un hijo (izq) y lo considera menor
                
                if (posHijo < this.ultimo) {
                    //hijo menor tiene hermano derecho
                    
                    if (this.heap[posHijo + 1].compareTo(this.heap[posHijo]) < 0) {
                        //el hijo derecho es el menor de los dos
                        posHijo++;
                    }
                }
                
                //compara al hijo menor con el padre
                if (this.heap[posHijo].compareTo(temp) < 0) {
                    //el hijo es menor que el padre, los intercambia
                    this.heap[posPadre] = this.heap[posHijo];
                    posPadre = posHijo;
                } else {
                    //el padre es menor que sus hijos, esta en su lugar
                    salir = true;
                }
            } else {
                //temp es hoja, esta en su lugar
                salir = true;
            }
        }
    }

    public Comparable recuperarCima() {
        return this.heap[1];
    }

    public String toString() {
        String s = "[ ";
        for (int i = 1; i <= this.ultimo; i++) {
            s += this.heap[i] + " ";
        }
        s += "]";
        return s;
    }
}