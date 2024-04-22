public class Figura2D {
    protected double px, py;

    Figura2D()
    {
        this.py = 0.0;
        this.px = 0.0;
    }
    Figura2D(double Px, double Py)
    {
        this.px = Px;
        this.py = Py;
    }
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
