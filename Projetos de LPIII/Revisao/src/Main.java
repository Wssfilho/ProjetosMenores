// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        motores [] tipos = new motores[5];
        tipos[0] = new MotElet();
        tipos[1] = new MotComb();
        tipos[2] = new MotElet(20, 4000, 12, 36 );
        tipos[3] = new MotElet();
        tipos[4] = new MotElet(1, 500, 234, 5);
        for (int i = 0; i < 5; i++)
        {
            double j = tipos[i].CalcularAutonimia();
            System.out.println(j);
        }
        //polimorfismo de sobreposicao
        }
    }
