package JogoVelha;

public class JogoDaVelha
{
    private char[][] tab;
    private int limDir, limInf;
    private int opX, opO;

    JogoDaVelha()
    {
        this.limDir = 3;
        this.limInf = 3;
        tab = new char[this.limDir][this.limInf];
        int i, j;
        for(i = 0; i <= 3; i++)
        {
            for(j = 0; j <= 3; j++)
            {
                this.tab[i][j] = '.';
            }
        }
    }





}
