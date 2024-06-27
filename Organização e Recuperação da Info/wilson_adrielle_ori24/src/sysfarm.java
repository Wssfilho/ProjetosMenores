import java.sql.*;
//import java.util.concurrent.ExecutionException;
//import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sysfarm extends JFrame {
    private JTextArea contador, areadetexto;
    private JRadioButton botaoag, botaosoma, botaocontar, butaoListCli, botaoListMed;
    JPanel panelRadio, panelEsquerdo;
    JTable table;

    public sysfarm() {
        setSize(950, 600);
        setTitle("Consulta de Farmacia");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        butaoListCli = new JRadioButton("Listar todos clientes");
        botaoListMed = new JRadioButton("Listar todos medicamentos");
        botaoag = new JRadioButton("Agrupar & Ordenar");
        botaosoma = new JRadioButton("Somar");
        botaocontar = new JRadioButton("Contar");
        // Criando o JComboBox com as opções de escolha
        String[] opcoesMenu = { "Máx de Clientes", "Máx de Medicamentos" };
        JComboBox<String> comboBoxMenu = new JComboBox<>(opcoesMenu);
        // Definindo o tamanho preferido do comboBoxMenu
        comboBoxMenu.setPreferredSize(new Dimension(128, 25));
        // Adicionando ActionListener ao JComboBox
        comboBoxMenu.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selecionado = (String) comboBoxMenu.getSelectedItem();
                // Implementada a lógica baseada na seleção
                if ("Máx de Clientes".equals(selecionado)) {
                    //System.out.println("Calculando o máximo de clientes...");
                    infomaxcli();
                } else if ("Máx de Medicamentos".equals(selecionado)) {
                    //System.out.println("Calculando o máximo de medicamentos...");
                    infomaxmed();
                }
            }
        });
        String[] opcoesMenumin = { "Mín de Clientes", "Mín de Medicamentos" };
        JComboBox<String> comboBoxMenumin = new JComboBox<>(opcoesMenumin);
        // Definindo o tamanho preferido do comboBoxMenu
        comboBoxMenumin.setPreferredSize(new Dimension(128, 25));
        // Adicionando ActionListener ao JComboBox
        comboBoxMenumin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selecionado = (String) comboBoxMenumin.getSelectedItem();
                // Implementada a lógica baseada na seleção
                if ("Mín de Clientes".equals(selecionado)) {
                    //System.out.println("Calculando o máximo de clientes...");
                    infomincli();
                } else if ("Mín de Medicamentos".equals(selecionado)) {
                    //System.out.println("Calculando o máximo de medicamentos...");
                    infominmed();
                }
            }
        });
        contador = new JTextArea(2, 5);
        contador.setEditable(false);
        panelEsquerdo = new JPanel();
        panelEsquerdo.setLayout(new BoxLayout(panelEsquerdo, BoxLayout.X_AXIS));
        areadetexto = new JTextArea(30, 25);
        panelEsquerdo.add(new JScrollPane(areadetexto));
        panelRadio = new JPanel();
        panelRadio.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 10));
        panelRadio.add(butaoListCli);
        panelRadio.add(botaoListMed);
        panelRadio.add(botaoag);
        panelRadio.add(botaosoma);
        panelRadio.add(botaocontar);
        panelRadio.add(comboBoxMenu);
        panelRadio.add(comboBoxMenumin);
        panelRadio.add(contador);

        Container contentPane = getContentPane();
        contentPane.add(panelRadio, BorderLayout.NORTH);
        contentPane.add(panelEsquerdo, BorderLayout.SOUTH);

        setVisible(true);
        setResizable(false);
        butaoListCli.addActionListener(e -> vertodoscli());
        botaoListMed.addActionListener(e -> vertodosmed());
        botaoag.addActionListener(e -> agrupor());
        botaosoma.addActionListener(e -> sumcli());
        botaocontar.addActionListener(e -> contarcli());
    }
    protected void contarcli()
    {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("SELECT COUNT(Id) AS contar FROM cliente");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("").append(rs.getString("contar"));
            }
            contador.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void vertodoscli() {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM cliente");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("  - ID: ").append(rs.getString("iD")).append("\n")
                        .append("  - Nome: ").append(rs.getString("Nome")).append("\n")
                        .append("  - Telefone: ").append(rs.getString("Telefone")).append("\n");
            }
            areadetexto.setText(sb.toString());
        } catch (Exception e) {

        }
    }
    protected void sumcli()
    {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("SELECT SUM(Id) AS soma FROM cliente");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("").append(rs.getString("soma"));
            }
            contador.setText(sb.toString());
        } catch (Exception e) {

        }
        
    }

    private void vertodosmed() {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM medicamentos");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("  - Nome Medicamento: ").append(rs.getString("Nome_med")).append("\n")
                        .append("  - Tipo: ").append(rs.getString("tipo")).append("\n")
                        .append("  - cod_fk: ").append(rs.getString("cod_fk")).append("\n");
            }
            areadetexto.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void infomaxcli() {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("SELECT MAX(Id) as max FROM Cliente");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("").append(rs.getString("max"));
            }
            contador.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void infomaxmed() {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("SELECT MAX(Cod_med) as max FROM medicamentos");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("").append(rs.getString("max"));
            }
            contador.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void infominmed() {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("SELECT MIN(Cod_med) as min FROM Medicamentos ");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("").append(rs.getString("min"));
            }
            contador.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void infomincli() {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("SELECT MIN(Id) as min FROM Cliente ");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("").append(rs.getString("min"));
            }
            contador.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void agrupor() {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:BD_Farmacia.db");
            Statement stm = c.createStatement();

            // Modifiquei a consulta para incluir ORDER BY para ordenar os resultados
            ResultSet rs = stm.executeQuery(
                    "SELECT * FROM Cliente, Medicamentos WHERE Cliente.id = Medicamentos.cod_fk GROUP BY Cliente.nome ORDER BY Cliente.nome");

            StringBuilder sb = new StringBuilder();
            String previousCustomerName = ""; // Armazena o nome do cliente anterior

            while (rs.next()) {
                String currentCustomerName = rs.getString("Nome");

                // Verifica se é um novo cliente e adiciona uma nova linha se necessário
                if (!previousCustomerName.equals(currentCustomerName)) {
                    if (!previousCustomerName.isEmpty()) {
                        sb.append("\n"); // Adiciona uma nova linha para o cliente anterior
                    }
                    sb.append("ID Cliente: ").append(rs.getString("id")).append("\n")
                            .append("Nome Cliente: ").append(currentCustomerName).append("\n");
                    previousCustomerName = currentCustomerName;
                }

                // Anexa detalhes do medicamento
                sb.append("  - Telefone: ").append(rs.getString("Telefone")).append("\n")
                        .append("  - Cod_med: ").append(rs.getString("Cod_med")).append("\n")
                        .append("  - Nome Medicamento: ").append(rs.getString("Nome_med")).append("\n")
                        .append("  - Tipo: ").append(rs.getString("tipo")).append("\n")
                        .append("  - cod_fk: ").append(rs.getString("cod_fk")).append("\n");
            }

            // Adiciona uma nova linha para o último cliente (se houver)
            if (!previousCustomerName.isEmpty()) {
                sb.append("\n");
            }

            areadetexto.setText(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new sysfarm();
    }
}
