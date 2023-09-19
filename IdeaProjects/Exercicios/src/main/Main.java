package main;


public class Main {
    public static void main(String[] args) {
        lampada c1, c2, c3;
        int n = 0;
        c1 = new lampada(n);
        c2 = new lampada(c1.getNum());
        c3 = new lampada(c2.getNum());
        c3.imprimir();




    }
}