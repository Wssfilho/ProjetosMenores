import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private JTextArea textArea;

    public App() {
        setSize(600, 500);
        setTitle("Exemplo Java - SQLite - VSCode");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        textArea = new JTextArea(20, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(scrollPane, gbc);

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
            textArea.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new App();
    }
}
