// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args)
    {
        Funcionario [] f1 = new Funcionario[4];
        // NAO PODE POR SER ABSTRATOf1[0] = new Funcionario();
        f1[0] = new caixista();
        f1[1] = new Gerente();
        f1[2] = new Gerente(20, "Wilson", 140.00, 10);
        f1[3] = new caixista(20, "Pedro", 1000.00, 5);
        for(int i = 0; i < 4; i++)
        {
            f1[i].Bonificacao();
            f1[i].imprimir();

        }
        }
    }
