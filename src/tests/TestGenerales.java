package tests;
import lineales.estaticas.Pila;

public class TestGenerales {
    public static void main(String[] args) {
        Pila pila = new Pila();
        pila.apilar(1);
        pila.apilar(1);
        pila.apilar(1);
        System.out.println(pila.toString());
    }
}