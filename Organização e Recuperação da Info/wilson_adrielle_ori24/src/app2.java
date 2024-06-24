import java.sql.*;
import java.util.concurrent.ExecutionException;

//import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import java.awt.*;

public class app2 extends JFrame {
    private JTextArea max, min, textArea3;
    private JRadioButton buttonOrder, buttonGroup, buttonSum, buttonCount, buttonMax, buttonMin, buttonListCli, buttonListMed;
    JPanel panelRadio, panelEsquerdo, panelDireito;
    JTable table;
    public app2() {
        setSize(900, 600);
        setTitle("Consulta de Farmacia");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        buttonListCli = new JRadioButton("Listar todos clientes");
        buttonListMed = new JRadioButton("Listar todos medicamentos");
        buttonOrder = new JRadioButton("Ordenar");
        buttonGroup = new JRadioButton("Agrupar");
        buttonSum = new JRadioButton("Somar");
        buttonCount = new JRadioButton("Contar");
        buttonMax = new JRadioButton("Máximo");
        buttonMin = new JRadioButton("Mínimo");
        max = new JTextArea(1, 5);
        max.setEditable(false);
        min = new JTextArea(1, 5);
        min.setEditable(false);

        panelEsquerdo = new JPanel();
        panelEsquerdo.setLayout(new BoxLayout(panelEsquerdo, BoxLayout.X_AXIS));
        textArea3 = new JTextArea(30, 25);
        panelEsquerdo.add(new JScrollPane(textArea3));
        panelRadio = new JPanel();
        panelRadio.setLayout(new FlowLayout( FlowLayout.LEFT, 1, 10));
        panelRadio.add(buttonListCli);
        panelRadio.add(buttonListMed);
        panelRadio.add(buttonOrder);
        panelRadio.add(buttonGroup);
        panelRadio.add(buttonSum);
        panelRadio.add(buttonCount);
        panelRadio.add(buttonMax);
        panelRadio.add(max);
        panelRadio.add(buttonMin);
        panelRadio.add(min);

        Container contentPane = getContentPane();
        contentPane.add(panelRadio, BorderLayout.NORTH);
        contentPane.add(panelEsquerdo, BorderLayout.SOUTH);
        
       
        setVisible(true);
        setResizable(false);
        buttonListMed.addActionListener(e -> seeallmed());
        buttonMax.addActionListener(e -> infomax());
        buttonMin.addActionListener(e -> infomin());
        buttonGroup.addActionListener(e -> groupby());
    }
    private void seeallmed()
    {
        try
        {
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM medicamentos");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("  - Nome Medicamento: ").append(rs.getString("Nome_med")).append("\n")
                  .append("  - Tipo: ").append(rs.getString("tipo")).append("\n")
                  .append("  - cod_fk: ").append(rs.getString("cod_fk")).append("\n");
            }
        textArea3.setText(sb.toString());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void infomax() {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("SELECT MAX(Id) as max FROM Cliente");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("").append(rs.getString("max"));
            }
        max.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void infomin() {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("SELECT MIN(Cod_med) as min FROM Medicamentos ");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("").append(rs.getString("min"));
            }
        min.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void groupby() {
        try {
           // textArea3.setText(" ");
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();
    
            ResultSet rs = stm.executeQuery("SELECT * FROM Cliente, Medicamentos WHERE Cliente.id = Medicamentos.cod_fk GROUP BY Cliente.nome");
            StringBuilder sb = new StringBuilder();
            String previousCustomerName = ""; // Store previous customer name
            
    
            while (rs.next()) {
                String currentCustomerName = rs.getString("Nome");
    
                // Check for new customer and add newline if needed
                if (!previousCustomerName.equals(currentCustomerName)) {
                    if (!previousCustomerName.isEmpty()) {
                        sb.append("\n"); // Add newline for previous customer
                    }
                    sb.append("ID Cliente: ").append(rs.getString("id")).append("\n")
                      .append("Nome Cliente: ").append(currentCustomerName).append("\n");
                    previousCustomerName = currentCustomerName;
                }
    
                // Append medication details
                sb.append("  - Telefone: ").append(rs.getString("Telefone")).append("\n")
                  .append("  - Cod_med: ").append(rs.getString("Cod_med")).append("\n")
                  .append("  - Nome Medicamento: ").append(rs.getString("Nome_med")).append("\n")
                  .append("  - Tipo: ").append(rs.getString("tipo")).append("\n")
                  .append("  - cod_fk: ").append(rs.getString("cod_fk")).append("\n");
            }
    
            // Add newline for the last customer (if any)
            if (!previousCustomerName.isEmpty()) {
                sb.append("\n");
            }
    
            textArea3.setText(sb.toString());
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String args[]) {
        new app2();
    }
}
