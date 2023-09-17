
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        Conta c1;
        c1 = new Conta(s.nextInt(), s.nextInt(), "Wilson", s.nextDouble());
        c1.Imprimir();

























        /* System.out.println("Insira o numero");
        c1 = new Conta(); //criamos o objeto indicando a class
        c1.setNumero(29);
        c1.getNumero();
        c1.Nome = "estrela";
        //peguei o numero de c1;
        System.out.println("numero: " + c1.getNumero()); //ele retorna o valor de numero
        System.out.println("Nome: " + c1.Nome);
        c2 = c1;
        c2.setNumero(s.nextInt());
        c2.getNumero();
        c2.Nome = "StarBoy";
        System.out.println("numero novo: " + c2.getNumero());
        System.out.println("Nome: " + c2.Nome);
        */
        //############################ END ########################
        /*
        c1.setNumero(25); //chamamos set para mudar o valor numero la na class ja que esse atributo está como private
        c1.setAgencia(29);
        c1.Nome = "WILSON";
        c1.Depositar(500.0);
        c1.Sacar(300.0);
        System.out.println("numero: " + c1.getNumero()); //ele retorna o valor de numero
        System.out.println("Agencia: " + c1.getAgencia());//e retorna o valor de agência

        */
        //c1.Imprimir();
    }
}