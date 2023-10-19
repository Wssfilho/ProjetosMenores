public class Circulo extends Figura2D
{
    private double raio;
    Circulo(double pp1x, double pp1y, double praio)
    {
        super(pp1x, pp1y);
        this.raio = praio;
    }
    Circulo()
    {
        super();
        this.raio = 1.0;
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

    @Override //o que significa: significa uma diretiva que indica que voce está sobrescrevendo um
    // metodo se existir na class pai(evitar que voce erre um método), se nao existir da erro!
    public void imprimir() {
        super.imprimir();
    }
}
