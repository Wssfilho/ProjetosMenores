import java.sql.*;

import javax.swing.*;

public class App extends JFrame {
    public App() {
        setSize(600, 500);
        setTitle("Exemplo Java - SQLite - VSCode");
        setLocationRelativeTo(new JFrame());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();
            //Statement sts = c.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM cliente");
            //ResultSet rss2 = sts.executeQuery("SELECT * FROM medicamentos");
            //System.out.println(rss2.getInt("cod_med"));
            while (rs.next()) {
                JOptionPane.showMessageDialog(
                        null,
                        rs.getInt("Id") + "\n" +
                                rs.getString("Nome") + "\n" +
                                rs.getString("Telefone") + "\n");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("SQLite acessado com sucesso...");
    }
}
