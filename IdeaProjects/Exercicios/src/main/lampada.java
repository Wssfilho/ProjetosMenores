package main;

public class lampada
{
    private int num;


    lampada(int n)
    {
        this.num = n;
    }

    public int getNum() {
        imprimir();
        return ++num;
    }

    public void imprimir()
    {
        System.out.println(this.num);
    }
}


