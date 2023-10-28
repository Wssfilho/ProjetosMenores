import java.nio.channels.Pipe;

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

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public double Bonificacao()
    {
        if(getID() >= 10)
        {
            this.saldo += 50.0;
        }
        if(getID() < 10)
        {
            this.saldo += 100.0;
        }
        return this.saldo;
    }
    @Override
    public void imprimir()
    {
        super.imprimir();
        System.out.println("ID: " + getID());
    }
}
