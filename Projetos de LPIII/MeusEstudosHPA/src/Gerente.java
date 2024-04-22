

public class Gerente extends Funcionario{
    private int ID;

    Gerente()
    {
        super();
        this.ID = 0;
    }
    Gerente(int pIdade, String pNome, double pSaldo, int pID)
    {
        super(pIdade, pNome, pSaldo);
        this.ID = pID;
    }

    public int getID() {
        return ID;
    }

    @Override
    public void Bonificacao()
    {
        if(getID() >= 10)
        {
            this.saldo += 50.0;
        }
        if(getID() < 10)
        {
            this.saldo += 100.0;
        }
    }
    @Override
    public void imprimir()
    {
        super.imprimir();
        System.out.println("ID: " + getID());
    }
}
