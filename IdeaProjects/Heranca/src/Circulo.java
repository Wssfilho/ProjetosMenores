public class Circulo extends Figura2D
{
    private double raio;
    Circulo(double pp1x, double pp1y, double praio)
    {
        super(pp1x, pp1y);
        this.raio = praio;
    }
    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    @Override
    public double CalcularArea() {
        double area;

        area = Math.PI * Math.pow(this.raio, 2);

        return area;
    }
}
