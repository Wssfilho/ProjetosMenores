public class Figura2D {
    protected double px, py;

    public double CalcularArea()
    {
       return 0.0;
    }
    public void imprimir()
    {
        System.out.println("PONTO 1: (" + px + "," + py + ")");
    }
    public void setPx(double px) {
        this.px = px;
    }

    public void setPy(double py) {
        this.py = py;
    }

    public double getPx() {
        return px;
    }

    public double getPy() {
        return py;
    }
}
