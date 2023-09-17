import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in); //um scanner
        int opcao;
        Robo r1 = new Robo();
        Sala sl = new Sala();
        r1.setSala(sl);
        sl.setRobo(r1);
        do
        {
            System.out.println(" Opções ");
            System.out.println(" (1) Inicializar sala e robô (padrão) ");
            System.out.println(" (2) Inicializar sala e robô (usuário) ");
            System.out.println(" (3) Mover esquerda ");
            System.out.println(" (4) Mover direita ");
            System.out.println(" (5) Mover cima ");
            System.out.println(" (6) Mover baixo ");
            System.out.println(" (7) Imprimir ");
            System.out.println(" (9) Sair ");
            System.out.println(" Digite a opção : ");
            opcao = s.nextInt();

            switch(opcao)
            {
                case 1:
                {
                    r1.setPosX(1);
                    r1.setPosY(1);

                    sl.inserirObstaculo(3, 2);
                    sl.inserirObstaculo(2, 2);
                    sl.inserirObstaculo(2, 3);
                    sl.inserirObstaculo(2, 4);
                    sl.imprimir();
                }break;
                case 2:
                {
                    int op;
                    System.out.println("Insira as posições x e y respectivamente: ");
                    r1.setPosX(s.nextInt());
                    r1.setPosY(s.nextInt());
                    System.out.println("Quantos Obstáculos voce quer: ");
                    op = s.nextInt();
                    for(int i = 0; i < op; i++)
                    {
                        System.out.println("Insira os obstáculos: " + i);
                        sl.inserirObstaculo(s.nextInt(), s.nextInt());
                    }
                    sl.imprimir();
                    // Para exercício para alunos
                }break;
                case 3:
                {
                    r1.MoverEsq();
                    sl.imprimir();
                }break;
                case 4:
                {
                    r1.MoverDir();
                    sl.imprimir();
                }break;
                case 5:
                {
                    r1.MoverCima();
                    sl.imprimir();
                }break;
                case 6:
                {
                    r1.MoverBaixo();
                    sl.imprimir();
                }break;
                case 7:
                {
                    sl.imprimir();
                }break;


            }
        }while(opcao != 9);
    }
}
