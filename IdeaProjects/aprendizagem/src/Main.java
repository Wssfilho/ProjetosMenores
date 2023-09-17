import java.util.Scanner;
public class Main {
    public static void main(String[] args)
    {
        int op;
        Scanner s = new Scanner(System.in);
        conta c1;
        c1 = new conta();


        do
        {
            System.out.println("Bem vindo ao banco, escolha a opcao que desejar: ");
            System.out.println("(1) Inserir saldo");
            System.out.println("(2) Atualizar saldo");
            System.out.println("(3) Imprimir saldo");
            op = s.nextInt();
            switch (op)
            {
                case 1:
                {
                    System.out.println("Insira sua idade e seu saldo ");
                    c1.setIdade(s.nextInt());
                    c1.setSaldo(s.nextDouble());
                    break;
                }
                case 2:
                {
                    if(c1.getSaldo() == 0.0)
                    {
                        System.out.println("Nao eh possivel atualizar o saldo");
                        continue;
                    }
                    else{
                        System.out.println("Quanto voce quer atualizar? ");
                        c1.At_saldo(s.nextDouble());
                    }
                    break;
                }
                case 3:
                {
                    c1.imprimir();
                    break;
                }
            }
        }while (op != 9);


    }

}