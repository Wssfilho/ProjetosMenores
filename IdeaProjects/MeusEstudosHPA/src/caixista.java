public class caixista extends Funcionario{
    private int qtdHoras;

    caixista()
    {
        super();
        this.qtdHoras = 0;
    }
    caixista(int pIdade, String pNome, double pSaldo, int Pqtdhoras)
    {
        super(pIdade, pNome, pSaldo);
        this.qtdHoras = Pqtdhoras;
    }

    public int getQtdHoras() {
        return qtdHoras;
    }

    @Override
    public double Bonificacao()
    {
        if(getQtdHoras() >= 8)
        {
            this.saldo += 20.00;
        }
        if (getQtdHoras() < 8)
        {
            this.saldo += 10.00;
        }
        return this.saldo;
    }
    @Override
    public void imprimir()
    {
        super.imprimir();
        System.out.println("Qtd horas: " + getQtdHoras());
    }

}
