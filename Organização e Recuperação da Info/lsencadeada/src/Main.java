import javax.swing.*;
import java.awt.*;

public class Main extends JFrame
{
    JButton Binserir, Bremover, Bbuscar;
    JTextField ent;
    public static final JFrame frame = new Main();
    Main()
    {
        ent = new JTextField();
        Binserir= new JButton("Inserir ordenado");
        Bremover= new JButton("Remover nó (chave)");
        Bbuscar= new JButton("Buscar chave");
        JPanel Jpjanela = new JPanel();
        Jpjanela.setLayout(new GridLayout(1, 2));
        Jpjanela.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Jpjanela.add(ent);
        Jpjanela.add(Binserir);
        Jpjanela.add(Bremover);
        Jpjanela.add(Bbuscar);
        Container contentPane = getContentPane();// Obtém o painel de conteúdo da janela principal
        contentPane.add(Jpjanela, BorderLayout.NORTH);

    }
    private class NoB {
        public int chave;
        public NoB pagina;
        public NoB proximo;
        public NoB anterior;
        //construtor padrao
        protected NoB(int chave, NoB pagina, NoB proximo, NoB anterior) {
            this.chave = chave;
            this.pagina = pagina;
            this.proximo = proximo;
            this.anterior = anterior;
        }
    }
  public static void main(String[] args) {
      frame.setTitle("Lista encadeada");// Definindo o título da janela
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configurando a ação padrão ao fechar a janela
      frame.setBounds(300, 300, 400, 100);// Definindo as dimensões e posição da janela
      frame.setVisible(true);// Tornando a janela visível
    }

}
