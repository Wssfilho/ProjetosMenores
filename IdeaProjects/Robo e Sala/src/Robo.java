public class Robo
{
    private int posX, posY;
    Sala sala;


    Robo()
    {
        System.out.print(" Criando robô (padrão)");
        this.posX = 0;
        this.posY = 0;
    }

    Robo(int pX, int pY)
    {
        System.out.print(" Criando robô (parâmetros)");
        this.posX = pX;
        this.posY = pY;
    }

    public void setSala(Sala s)
    {
        this.sala = s;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int pposX) {
        if (pposX >= 0)
        {
            this.posX = pposX;
        }
        else
        {   System.out.println(" Valor de X inválido.");}
    }
    public int getPosY() {
        return posY;
    }

    public void setPosY(int pposY) {
        if (pposY >= 0)
        {
            this.posY = pposY;
        }
        else
        {   System.out.println(" Valor de X inválido.");}
    }



    public void imprimir()
    {
        System.out.print("Robo: (");
        System.out.print(this.posX + " , ");
        System.out.println(this.posY + " ) ");
    }

    public void MoverCima()
    {
        System.out.println(" Movendo para cima");
        if ((this.posX > 0) && (!(this.sala.ehObstaculo(this.posX-1, this.posY))))
        {   this.posX--; }
        else
        {
            System.out.println("Robo na posição X = zero.");
        }
    }

    public void MoverEsq()
    {
        System.out.println(" Movendo para esq.");
        if ((this.posY > 0)  && (!(this.sala.ehObstaculo(this.posX, this.posY-1))))
        {   this.posY--; }
        else
        {
            System.out.println("Robo na posição Y = zero.");
        }
    }

    public void MoverBaixo()
    {
        if (this.posX < (this.sala.getLimInf()-1)) //get para saber qual o limite inferior
        {
            if ((!(this.sala.ehObstaculo(this.posX + 1, this.posY)))) {
                System.out.println(" Movendo para baixo");
                this.posX++;
            }
        }
        else
        {
            System.out.println("Nao pode mover");
        }
    }

    public void MoverDir()
    {
        if(this.posY < (this.sala.getLimDir() -1)) {
            if ((!(this.sala.ehObstaculo(this.posX, this.posY + 1)))) {
                System.out.println(" Movendo para dir.");
                this.posY++;
            }
        }
        else {
            System.out.println("Nao pode mover");
        }

    }

}
