//classe pai
public abstract class motores {
    protected double potencia;

    /* motores(double Ppotencia)
    {
        this.potencia = Ppotencia;
    }

     */
    motores()
    {
        this.potencia = 0.0;
    }
    motores(double pot)
    {
        this.potencia = pot;
    }
    public abstract double CalcularAutonimia();


}
