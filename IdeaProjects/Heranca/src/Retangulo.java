public class Retangulo extends Figura2D
{
    protected double p2x, p2y;

    Retangulo(double P1x, double P1y, double P2x, double P2y) {
        super(P1x, P1y);
        this.p2x =  P2x;
        this.p2y = P2y;
    }

    public void setP2x(double p2x) {
        this.p2x = p2x;
    }
    public double getP2x() {
        return p2x;
    }

    public void setP2y(double p2y) {
        this.p2y = p2y;
    }
    public double getP2y() {
        return p2y;
    }
    @Override
    public double CalcularArea()
    {
        double area;
        area = (p2y - py) * (p2x - px);
        return area;
    }
    @Override
    public void imprimir()
    {
      super.imprimir();
      System.out.println("PONTO 2: (" + p2x + "," + p2y + ")");
    }
}
