package tests;
import java.util.HashMap;

public class TestGenerales {
    public static void main(String[] args) {
        HashMap<Integer, Object> resultados = new HashMap<Integer, Object>();
        resultados.put(1, "HOla");
        resultados.put(1, "chau");
        System.out.println(resultados.get(1));
    }
}