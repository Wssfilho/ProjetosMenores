import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private JTextArea textArea1, textArea2, textArea3, textArea4, textArea5;
    private JLabel label1, label2, label3, label4, label5;
    public App() {
        setSize(800, 600);
        setTitle("Exemplo Java - SQLite - VSCode");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        JPanel panelEsquerdo = new JPanel();
        panelEsquerdo.setLayout(new BoxLayout(panelEsquerdo, BoxLayout.Y_AXIS));
        textArea1 = new JTextArea(5, 1);
        textArea2 = new JTextArea(5, 10);
        textArea3 = new JTextArea(5, 10);
        panelEsquerdo.add(new JLabel("Informações do Banco de Dados:"));
        panelEsquerdo.add(new JScrollPane(textArea1));
        panelEsquerdo.add(new JLabel("Em breve..."));
        panelEsquerdo.add(new JScrollPane(textArea2));
        panelEsquerdo.add(new JLabel("Em breve..."));
        panelEsquerdo.add(new JScrollPane(textArea3));

        JPanel panelDireito = new JPanel();
        panelDireito.setLayout(new BoxLayout(panelDireito, BoxLayout.Y_AXIS));
        textArea4 = new JTextArea(5, 10);
        textArea5 = new JTextArea(5, 10);
        panelDireito.add(new JLabel("Em breve..."));
        panelDireito.add(new JScrollPane(textArea4));
        panelDireito.add(new JLabel("Em breve..."));
        panelDireito.add(new JScrollPane(textArea5));

        add(panelEsquerdo);
        add(panelDireito);

        setVisible(true);
        exibirInformacoes();
    }

    private void exibirInformacoes() {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM Cliente, Medicamentos WHERE Cliente.id = Medicamentos.cod_fk");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID Cliente: ").append(rs.getString("id")).append("\n")
                  .append("Nome Cliente: ").append(rs.getString("Nome")).append("\n")
                  .append("Telefone: ").append(rs.getString("Telefone")).append("\n")
                  .append("Cod_med: ").append(rs.getString("Cod_med")).append("\n")
                  .append("Nome Medicamento: ").append(rs.getString("Nome_med")).append("\n")
                  .append("Tipo: ").append(rs.getString("tipo")).append("\n")
                  .append("cod_fk: ").append(rs.getString("cod_fk")).append("\n\n");
            }
            textArea1.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new App();
    }
}
