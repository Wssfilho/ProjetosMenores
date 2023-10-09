import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Retangulo f1 = new Retangulo();
        Scanner s = new Scanner(System.in);
        f1.setPx(2.0);
        f1.setPy(4.0);
        f1.setP2x(6.0);
        f1.setP2y(8.0);
        f1.imprimir();
        System.out.println("AREA: " + f1.CalcularArea());
        }
}
