public class conta
{
    private double saldo;
    private int idade;


    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void At_saldo(double n_saldo)
   {
       /*if(this.saldo == 0)
       {
           System.out.println("Nao podemos atualizar, conta vazia");
           return;
       }
        */
       this.saldo += n_saldo;
   }

    public void imprimir()
    {
        System.out.println(this.idade);
        System.out.println("Saldo: " + this.getSaldo());
    }
}
