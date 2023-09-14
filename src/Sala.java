public class Sala
{
    private int LimitInf, LimitDir;
    private char[][] mat;
    private Robo robo;
    public void setRobo(Robo r)
    {
        this.robo = r;
    }
    Sala()
    {
        this.LimitDir = 5;
        this.LimitInf = 5;
        mat = new char[this.LimitDir][this.LimitInf];
        int i, j;
        for (i = 0; i <= 4; i++)
        {
            for (j = 0; j <=4; j++)
            {
                this.mat[i][j] = '.';
            }
        }
    }
    Sala(int pdir, int pinf)
    {
        this.LimitDir = pdir;
        this.LimitInf = pinf;
        mat = new char[this.LimitDir][this.LimitInf];
        int i, j;
        for (i = 0; i <= pdir; i++)
        {
            for (j = 0; j <= pinf; j++)
            {
                this.mat[i][j] = '.';
            }
        }
    }
    public void imprimir()
    {
        System.out.println();
        int i, j;
        for (i = 0; i <= 4; i++)
        {
            for (j = 0; j <= 4; j++)
            {
                if((robo.getX() == i) && robo.getY() == j)
                {
                    System.out.print("R  ");
                }
                else
                {
                    System.out.print(this.mat[i][j] + " ");
                }


            }
            System.out.println();
        }
    }
}
