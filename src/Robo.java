public class Robo
{
    private int x, y;

    Robo(int px)
    {
        System.out.println("Criando robo padrao: ");
        this.x = 0;
        this.y = 0;
    }
    Robo(int px, int py)
    {
        System.out.println("Criando propio");
        this.x = px;
        this.y = py;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        if(x >= 0){
            this.x = x;

        }
        else {
            System.out.println("Valor de x invalido");
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if(y >= 0)
        {
            this.y = y;

        }
        else {
            System.out.println("Valor de y invalido");

        }

    }

    public void imprimir()
    {
        System.out.print("Robo: (");
        System.out.print(this.x + " , ");
        System.out.print(this.y + ")");
    }
    public void MoverCim()
    {
        System.out.println("Movendo para cima");
        if(this.x > 0)
        {
            this.x--;
        }
        else
        {
            System.out.println("Nao eh permitido mover");
        }
    }
    public void MoverEsq()
    {
        System.out.println("Movendo para esquerda");
        if(this.y > 0)
        {
            this.y--;
        }
        else {
            System.out.println("Nao eh permitido mover");
        }
    }
    public void MoverBaixo()
    {
        System.out.println(" Movendo para baixo");
        this.y++;

    }

    public void MoverDireita()
    {
        System.out.println(" Movendo para dir.");
        this.y++;
    }
}
