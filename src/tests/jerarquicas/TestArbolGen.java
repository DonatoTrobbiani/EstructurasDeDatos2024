package tests.jerarquicas;
import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

public class TestArbolGen {
    public static void main(String[] args) {
        testingArbolGen();
    }

    public static void testingArbolGen() {
        System.out.println("TESTING ARBOL GENERAL");
        ArbolGen arbolGen = new ArbolGen();
        System.out.println(arbolGen.toString());

        System.out.println("PROBANDO INSERTAR/*");
        System.out.println("Inserta raiz \"D\":, espera TRUE: " + arbolGen.insertar("D", "padre"));
        System.out.println("Inserta \"Program Files\" como hijo de \"D\", espera TRUE: " + arbolGen.insertar("Program Files", "D"));
        System.out.println("Inserta \"Windows\" como hijo de \"D\", espera TRUE: " + arbolGen.insertar("Windows", "D"));
        System.out.println("Inserta \"System32\" como hijo de \"Windows\", espera TRUE: " + arbolGen.insertar("System32", "Windows"));
        System.out.println("Inserta \"Dona\" como hijo de \"D\", espera TRUE: " + arbolGen.insertar("Dona", "D"));

        System.out.println("Inserta \"Git\" como hijo de \"Program Files\", espera TRUE: " + arbolGen.insertar("Git", "Program Files"));
        System.out.println("Inserta \"Java\" como hijo de \"Program Files\", espera TRUE: " + arbolGen.insertar("Java", "Program Files"));
        System.out.println("Inserta \"Python\" como hijo de \"Program Files\", espera TRUE: " + arbolGen.insertar("Python", "Program Files"));

        System.out.println("Inserta \"Estructuras\" como hijo de \"Java\", espera TRUE: " + arbolGen.insertar("Estructuras", "Java"));
        System.out.println("Inserta \"Desarrollo\" como hijo de \"Java\", espera TRUE: " + arbolGen.insertar("Desarrollo", "Java"));

        System.out.println("Inserta \"Carpeta\" como hijo de \"System32\", espera TRUE: " + arbolGen.insertar("Carpeta", "System32"));

        System.out.println("Inserta \"app\" como hijo de \"Windows\", espera TRUE: " + arbolGen.insertar("app", "Windows"));
        System.out.println("Inserta \"drivers\" como hijo de \"Windows\", espera TRUE: " + arbolGen.insertar("drivers", "Windows"));

        System.out.println(arbolGen.toString());

        System.out.println("Inserta \"Hola\" como hijo de \"NADA\", espera FALSE: " + arbolGen.insertar("Hola", "NADA"));
        System.out.println(arbolGen.toString());
        System.out.println("/*FINALIZA PRUEBA INSERTAR");

        System.out.println("PROBANDO INSERTAR POR POSICION/*");
        System.out.println("Inserta \"Documents\" como hijo de \"Dona(13)\", espera TRUE: " + arbolGen.insertarPorPosicion("Documents", 13));
        System.out.println("Inserta \"ArbolGen\" como hijo de \"Estucturas(5)\", espera TRUE: " + arbolGen.insertarPorPosicion("ArbolGen", 5));

        System.out.println(arbolGen.toString());

        System.out.println("Inserta \"Hola\" como hijo de \"NADA(0)\", espera FALSE: " + arbolGen.insertarPorPosicion("Hola", 0));

        System.out.println(arbolGen.toString());
        System.out.println("/*FINALIZA PRUEBA INSERTAR POR POSICION");

        System.out.println("Comprueba si el arbol esta vacio, espera FALSE: " + arbolGen.esVacio());

        System.out.println("Retorna altura del arbol, espera 4: " + arbolGen.altura());

        System.out.println("Comprueba si \"Java\" pertenece al arbol, espera TRUE: " + arbolGen.pertenece("Java"));
        System.out.println("Comprueba si \"NADA\" pertenece al arbol, espera FALSE: " + arbolGen.pertenece("NADA"));

        System.out.println("Retorna nivel de \"Program Files\", espera 1: " + arbolGen.nivel("Program Files"));
        System.out.println("Retorna nivel de \"Desarrollo\", espera 3: " + arbolGen.nivel("Desarrollo"));
        System.out.println("Retorna nivel de \"NADA\", espera -1: " + arbolGen.nivel("NADA"));

        System.out.println("Retorna padre de \"Desarrollo\", espera \"Java\": " + arbolGen.padre("Desarrollo"));
        System.out.println("Retorna padre de \"NADA\", espera NULL: " + arbolGen.padre("NADA"));

        System.out.println("Clona el arbol");
        ArbolGen clon = arbolGen.clone();
        System.out.println("Arbol clonado: ");
        System.out.println(clon.toString());

        System.out.println("Vacía el arbol origianl");
        arbolGen.vaciar();
        System.out.println("Arbol original: ");
        System.out.println(arbolGen.toString());
        System.out.println("Retorna altura del arbol, espera -1: " + arbolGen.altura());
        System.out.println("Comprueba si el arbol esta vacio, espera TRUE: " + arbolGen.esVacio());

        System.out.println("Lista con los ancestros de \"Desarrollo\"");
        Lista ancestros = clon.ancestros("Desarrollo");
        System.out.println(ancestros.toString());

        System.out.println("Lista con los ancestros de \"NADA\", espera lista vacia");
        ancestros = clon.ancestros("NADA");
        System.out.println(ancestros.toString());

        System.out.println("Lista el arbol clonado en preorden");
        Lista preorden = clon.listarPreorden();
        System.out.println(preorden.toString());

        System.out.println("Lista el arbol clonado en inorden");
        Lista inorden = clon.listarInorden();
        System.out.println(inorden.toString());

        System.out.println("Lista el arbol clonado en posorden");
        Lista posorden = clon.listarPosorden();
        System.out.println(posorden.toString());

        System.out.println("Lista el arbol clonado por niveles");
        Lista porNiveles = clon.listarPorNiveles();
        System.out.println(porNiveles.toString());
    }
}
