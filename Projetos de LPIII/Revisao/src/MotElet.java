public class MotElet extends motores{

    private double capBat;
    private double conHora;
    private double velocidade_media;
    MotElet(double pot, double cap, double hr, double vel)
    {
        super(pot);
        this.capBat = cap;
        this.conHora = hr;
        this.velocidade_media = vel;
    }
    MotElet() //construtor com polimorfismo de sobrecarga
    {
        super();
        this.capBat = 0;
        this.conHora = 0;
        this.velocidade_media = 0;
    }
    public double getCapBat() {
        return capBat;
    }

    public double getConHora() {
        return conHora;
    }

    @Override
    public double CalcularAutonimia()
    {
        System.out.println("Execultando Calcular autunomia de MotorEletrico");
        //return this.capBat / this.ConHora;
        return getCapBat() / getConHora();
    }
}
