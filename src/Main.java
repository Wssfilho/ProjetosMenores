import java.util.Scanner;
public class Main {
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        Robo r1 = new Robo(2, 3);
        Sala a = new Sala();
        a.setRobo(r1);
        a.imprimir();
        System.out.println();
        System.out.println();
        r1.MoverCim();
        a.imprimir();

    }
}
