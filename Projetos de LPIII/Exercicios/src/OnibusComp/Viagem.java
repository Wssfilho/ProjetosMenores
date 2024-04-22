package OnibusComp;

public class Viagem {
    private char origem;
    private char destino;
    private int Data;
    private int Hora;
    private int Poutrona;
    private char []map;

    Viagem()
    {
        map = new char[5];
        int i;
        for(i = 0; i < 5; i++)
        {
            this.map[i] = '.';

        }
    }

    public int getPoutrona() {
        return Poutrona;
    }

    public void venderPassagem(int x, int j)
    {
        if(this.Poutrona == x)
        {
         System.out.println("Poutrona vendida");
        }
        else
        {
            this.Poutrona = x;
        }
    }
    public void LiberarPassagem()
    {
        int i;
        for(i = 0; i < 5; i++)
        {
            this.map[this.Poutrona] = 'X';
        }
    }
}
