import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

 public class StatusGUI extends JFrame {

    private JButton btnStatus;

    public StatusGUI() {
        super("Verificar Status do Arquivo");

        // Criação do botão "Verificar Status"
        btnStatus = new JButton("Verificar Status");

        // Criação do painel principal
        JPanel formularioPane = new JPanel();
        formularioPane.setLayout(new FlowLayout());
        formularioPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formularioPane.add(btnStatus);

        // Ação do botão "Verificar Status" usando lambda expression
        btnStatus.addActionListener(e -> mostrarStatus());

        // Configurações da janela
        this.setContentPane(formularioPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void mostrarStatus() {
        // Cria um objeto File para o arquivo
        File arquivo = new File("C:\\adriele", "dados.csv");

        // Cria um StringBuilder para armazenar as informações de status
        StringBuilder sb = new StringBuilder();
        sb.append("Tamanho do arquivo: ").append(length(arquivo)).append(" bytes\n");
        sb.append("Última modificação: ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(lastModified(arquivo)))).append("\n");
        sb.append("Caminho do arquivo: ").append(toString(arquivo)).append("\n");
        sb.append("Status de delete: ").append((delete(arquivo)));

        // Exibe as informações de status em uma caixa de diálogo
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Métodos para obter informações do arquivo
    public static long length(File arquivo) {
        return arquivo.length();
    }

    public static long lastModified(File arquivo) {
        return arquivo.lastModified();
    }

    public static String toString(File arquivo) {
        return arquivo.getPath();
    }

    public static boolean delete(File arquivo) {
        return arquivo.delete();
    }

    public static void main(String[] args) {
        new StatusGUI();
    }
}
