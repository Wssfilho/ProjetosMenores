import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Figura2D [] desenho = new Figura2D[4];
        desenho[0] = new Figura2D(); //com abstract some
        desenho[1] = new Circulo(2.0, 3.0, 4.0);
        desenho[2] = new Circulo(); //sobrecarga de Poli
        desenho[3] = new Retangulo(2.0, 3.0, 5.0, 7.0);
        int i;
        for(i = 0; i < 4; i++)
        {
            double area;
            area = desenho[i].CalcularArea();
            System.out.println("Area: " + area);
        }

        }
}
