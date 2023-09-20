package exercicio11;

import main.lampada;

public class Main {
    public static void main(String[] args)
    {

        Candidato c1, c2, c3;
        c1 = new Candidato(30.0, 20.0, 1.0, "wilson");
        c2 = new Candidato(51.0, 42.0, 53.0, "Alice");
        c3 = new Candidato(30.0, 20.0, 49.0, "Pedro");
        c1.verinota();
        c2.verinota();
        c3.verinota();
    }
}
