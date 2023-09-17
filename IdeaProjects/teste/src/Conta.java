
public class Conta {
    private int Numero;
    private int Agencia;
    public String Nome;
    private double Saldo;
    Conta()
    {
        //construtor
        this.Numero = 1;
        this.Agencia = 1;
        this.Nome = "NOVO NOME";
        this.Saldo = 200;
        System.out.println("Criando objeto conta");
        //sobrecarga de métodos
    }
    Conta(int pNumro, int pAgencia, String pNome, double pLimite)
    {
        this.Numero = pNumro;
        this.Agencia = pAgencia;
        this.Nome = pNome;
        this.Saldo = pLimite;
        System.out.println("Construtor 2 invocado");
    }

    public void setAgencia(int n)
    {
        this.Agencia = n;
    }
    public int getAgencia()
    {
        return this.Agencia;
    }

    public void setNumero(int n)
    {
        this.Numero = n;
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
    }



}