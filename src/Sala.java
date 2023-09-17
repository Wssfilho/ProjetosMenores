public class Sala
{
    private int limDir, limInf;
    private char[][] mat;

    private Robo robo;

    public void setRobo(Robo r)
    {
        this.robo = r;
    }

    public int getLimDir()
    {
        return limDir;
    }
    public int getLimInf()
    {
        return limInf;
    }
    Sala()
    {
        this.limDir = 5;
        this.limInf = 5;
        mat = new char[this.limDir][this.limInf];
        int i, j;
        for(i=0; i<=4; i++)
        {
            for(j=0; j<=4; j++)
            {
                this.mat[i][j] = '.';
            }
        }
    }

    public void inserirObstaculo(int i, int j)
    {
        this.mat[i][j] = 'O';
    }

    public void excluirObstaculo(int i, int j)
    {
        this.mat[i][j] = '.';
    }

    public Boolean ehObstaculo(int i, int j)
    {
        if (this.mat[i][j] == 'O')
            return true;
        else
            return false;
    }

    public void imprimir()
    {
        int i, j;
        System.out.println();
        System.out.println();
        for(i=0; i<=4; i++)
        {
            for(j=0; j<=4; j++)
            {
                if ( (robo.getPosX() == i)
                        && (robo.getPosY() == j) )
                {
                    System.out.print("R  ");
                }
                else
                {
                    System.out.print(this.mat[i][j]+"  ");
                }
            }
            System.out.println();
        }
    }
}
