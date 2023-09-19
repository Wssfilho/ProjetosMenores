package exercicio11;

public class Candidato
{
    private String nome;
    private double nota1;
    private double nota2;
    private double nota3;
    private double media;

    Candidato(double n1, double n2, double n3, String nome)
    {
        this.nota1 = n1;
        this.nota2 = n2;
        this.nota3 = n3;
        this.nome = nome;

    }

    public double getMedia()
    {
        this.media = (nota1+nota2+nota3)/3;
        return media;
    }

    public void verinota()
    {
        if(this.nota1 >= 50.0 || this.nota2 >= 50.0 || this.nota3 >= 50.0 || getMedia() >= 60.0)
        {
            System.out.println("Candidato aprovado: " + this.nome);
        }
        else {
            System.out.println("Reprovado: " + this.nome);
        }
    }
}
