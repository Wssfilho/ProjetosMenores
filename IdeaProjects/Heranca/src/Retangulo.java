public class Retangulo extends Figura2D
{
    protected double p2x, p2y;

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
        System.out.println("PONTO 1: (" + px + "," + py + ")");
        System.out.println("PONTO 2: (" + p2x + "," + p2y + ")");
    }
}
