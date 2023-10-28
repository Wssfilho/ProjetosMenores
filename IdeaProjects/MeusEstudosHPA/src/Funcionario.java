public abstract class  Funcionario {
    protected int idade;
    protected String nome;
    protected double saldo;

    Funcionario()
    {
        this.idade = 0;
        this.nome = "nome";
        this.saldo = 0.0;
    }
    Funcionario(int pIdade, String pNome, double pSaldo)
    {
        this.idade = pIdade;
        this.nome = pNome;
        this.saldo = pSaldo;
    }
    public abstract double Bonificacao();
    public void imprimir()
    {
        System.out.println("Nome: " + this.nome);
        System.out.println("Saldo: " + this.saldo);
        System.out.println("Idade: " + this.idade);

    }
}
