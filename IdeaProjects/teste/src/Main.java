
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        Conta c1, c2, c3;
        c1 = new Conta(12, 2, "wilson", 3.000);
        System.out.println("Qtd: " + Conta.Qtdcont);
        c2 = new Conta(1, 2, "alice", 2.000);
        System.out.println("Qtd: " + Conta.Qtdcont);
        c3 = new Conta(2, 3, "pedro", 2.000);
        System.out.println("Qtd: " + Conta.Qtdcont);



    }
}