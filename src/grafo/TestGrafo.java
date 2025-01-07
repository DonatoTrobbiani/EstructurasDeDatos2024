package grafo;

import lineales.dinamicas.Lista;

public class TestGrafo {
    public static void main(String[] args) {
        Grafo g = new Grafo();
        g.insertarVertice("A");
        g.insertarVertice("B");
        g.insertarVertice("C");
        g.insertarVertice("D");
        g.insertarVertice("E");

        System.out.println(g.toString());

        g.insertarArco("A", "B", 1);
        g.insertarArco("A", "C", 1);
        g.insertarArco("B", "C", 1);
        g.insertarArco("B", "D", 1);
        g.insertarArco("C", "D", 1);
        g.insertarArco("D", "E", 1);

        System.out.println(g.toString());

        Lista camino =  g.listarTodosLosCaminos("A", "E");
        System.out.println(camino.toString());
    }
}
