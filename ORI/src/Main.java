import java.io.File;
import java.awt.BorderLayout;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.io.*;

public class Main extends JFrame {


    JButton btnCriarDir, btnCriarArq, btnRenomear, btnCopiar;


    Main() {


        btnCriarDir = new JButton("CriarDiretorio");
        btnCriarArq = new JButton("CriarArquivo");
        btnRenomear = new JButton("RenomearArquivo");
        btnCopiar = new JButton("Copiar Arquivo");


        JPanel formularioPane = new JPanel();
        // Gerenciador de Layout
        formularioPane.setLayout(new GridLayout(4, 2));
        // UMa borda em volta
        formularioPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Adicionando os controles
        formularioPane.add(btnCriarDir);
        formularioPane.add(btnCriarArq);
        formularioPane.add(btnRenomear);
        formularioPane.add(btnCopiar);
        btnCriarDir.addActionListener(e->criarDiretorio());
        btnCriarArq.addActionListener(e->criarArquivo());
        btnRenomear.addActionListener(e->renomearArquivo());
        Container contentPane = getContentPane();
        contentPane.add(formularioPane, BorderLayout.NORTH);


    }

    //RENOMEAR
   public void renomearArquivo()
   {
       File antigo1 = new File("C:\\adriele\\teste.txt"); // RENOMEAR PARA C:
       File novo1 = new File("C:\\adriele\\renomeadoteste1.txt");
       boolean success = antigo1.renameTo(novo1);
       File antigo2 = new File("C:\\adriele\\teste2.txt"); // RENOMEAR PARA C:
       File novo2 = new File("C:\\adriele\\renomeadoteste2.txt");
       boolean success2 = antigo2.renameTo(novo2);

   }

    public void criarArquivo()
    {
        File arquivo = new File("C:\\adriele", "teste.txt");
        File arquivo2 = new File("C:\\adriele", "teste2.txt");
        if(arquivo.exists() || arquivo2.exists())
        {
            JOptionPane.showMessageDialog (null, "Um dos arquivos já existe");
            return;
        }

        try
        {
            boolean statusArq = arquivo.createNewFile();
            boolean statusArq2 = arquivo2.createNewFile();

            System.out.println("Arquivo criado... :" + statusArq + statusArq2);

        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void criarDiretorio()
    {
        File diretorio = new File("C:\\adriele");
        File diretorio2 = new File("C:\\wilson");

        if(diretorio.exists() || diretorio2.exists())
        {
            JOptionPane.showMessageDialog(null, "Um dos diretórios já existe");
            return;
        }

        boolean statusDir = diretorio.mkdir();
        System.out.println("Diretorio criado..." + statusDir);

        boolean statusDir2 = diretorio2.mkdir();
        System.out.println("Diretorio criado..." + statusDir2);
    }


    public static void main(String[] args) {
        JFrame f = new Main();
        f.setTitle("Primeira Janela");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBounds(300, 300, 400, 400);
        f.setVisible(true);
    }


    // classe interna private para tratamento de evento


}





