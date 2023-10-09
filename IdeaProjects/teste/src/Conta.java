
public class Conta {
    private final int Numero;
    private int Agencia;
    public String Nome;
    private double Saldo;
    private static int Qtdcont = 0; //atributo de classe pertencente a classe
    Conta(int pNumro, int pAgencia, String pNome, double pLimite)
    {
        Qtdcont++;
        this.Numero = Qtdcont;
        System.out.println("Numero eh autoincrementado");
        this.Agencia = pAgencia;
        this.Nome = pNome;
        this.Saldo = pLimite;
        System.out.println("Construtor 2 invocado");

    }
    public static int getQtdcont() //porque nos queremos encapsular
    {
        return Qtdcont;
    }
    public int getAgencia()
    {
        return this.Agencia;
    }

    public int getNumero() {
        return this.Numero;
    }

    //saldo ja tinha metodos de gerenciamento;
    public void Sacar(double pValor)
    {
        this.Saldo -= pValor;
    }
    public void Depositar(double pValor)
    {
        this.Saldo += pValor;
    }
    //método independente
    public void Imprimir()
    {
        System.out.println(" Numero  : " + this.Numero);
        System.out.println(" Agência : " + this.Agencia);
        System.out.println(" Titular : " + this.Nome);
        System.out.println(" Saldo   : " + this.Saldo);
        System.out.println(" Quantidade: " + Conta.getQtdcont()); //chamar a qtd de conta que está vinculado a classe
    }


}