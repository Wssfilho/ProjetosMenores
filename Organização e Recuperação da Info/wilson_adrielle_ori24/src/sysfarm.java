import java.sql.*;
//import java.util.concurrent.ExecutionException;
//import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sysfarm extends JFrame 
{
    // Declaração dos componentes da interface gráfica
    private JTextArea contador, areadetexto;
    private JRadioButton botaoag, botaosoma, botaocontar, butaoListCli, botaoListMed;
    JPanel panelRadio, panelEsquerdo;
    JTable table;

    /**
     * Função principal do programa, cria a janela e os botões
     * @param none
     */
    public sysfarm() 
    {
        // Configuração inicial da janela
        setSize(950, 600);
        setTitle("Consulta de Farmacia");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        // Criação dos botões de rádio
        butaoListCli = new JRadioButton("Listar todos clientes");
        botaoListMed = new JRadioButton("Listar todos medicamentos");
        botaoag = new JRadioButton("Agrupar & Ordenar");
        botaosoma = new JRadioButton("Somar");
        botaocontar = new JRadioButton("Contar");
        
        // Criação do JComboBox com as opções de escolha
        String[] opcoesMenu = { "Máx de Clientes", "Máx de Medicamentos" };
        JComboBox<String> comboBoxMenu = new JComboBox<>(opcoesMenu);
        comboBoxMenu.setPreferredSize(new Dimension(128, 25));
        
        // Adicionando ActionListener ao JComboBox para as opções de máximo
        comboBoxMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selecionado = (String) comboBoxMenu.getSelectedItem();
                if ("Máx de Clientes".equals(selecionado)) {
                    infomaxcli();
                } else if ("Máx de Medicamentos".equals(selecionado)) {
                    infomaxmed();
                }
            }
        });
        
        // Criação do JComboBox com as opções de mínimo
        String[] opcoesMenumin = { "Mín de Clientes", "Mín de Medicamentos" };
        JComboBox<String> comboBoxMenumin = new JComboBox<>(opcoesMenumin);
        comboBoxMenumin.setPreferredSize(new Dimension(128, 25));
        
        // Adicionando ActionListener ao JComboBox para as opções de mínimo
        comboBoxMenumin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selecionado = (String) comboBoxMenumin.getSelectedItem();
                if ("Mín de Clientes".equals(selecionado)) {
                    infomincli();
                } else if ("Mín de Medicamentos".equals(selecionado)) {
                    infominmed();
                }
            }
        });
        
        // Configuração dos componentes de texto
        contador = new JTextArea(2, 5);
        contador.setEditable(false);
        panelEsquerdo = new JPanel();
        panelEsquerdo.setLayout(new BoxLayout(panelEsquerdo, BoxLayout.X_AXIS));
        areadetexto = new JTextArea(30, 25);
        panelEsquerdo.add(new JScrollPane(areadetexto));
        
        // Configuração do painel de botões de rádio
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

        // Adicionando painéis ao contentPane
        Container contentPane = getContentPane();
        contentPane.add(panelRadio, BorderLayout.NORTH);
        contentPane.add(panelEsquerdo, BorderLayout.SOUTH);

        // Configuração final da janela
        setVisible(true);
        setResizable(false);

        // Adicionando ActionListeners aos botões de rádio
        butaoListCli.addActionListener(e -> vertodoscli());
        botaoListMed.addActionListener(e -> vertodosmed());
        botaoag.addActionListener(e -> agrupor());
        botaosoma.addActionListener(e -> sumcli());
        botaocontar.addActionListener(e -> contarcli());
    }

    // Método para contar o número de clientes
    protected void contarcli() {
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

    // Método para listar todos os clientes
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
            e.printStackTrace();
        }
    }

    // Método para somar o ID dos clientes
    protected void sumcli() {
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
            e.printStackTrace();
        }
    }

    // Método para listar todos os medicamentos
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

    // Método para obter o máximo ID de clientes
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

    // Método para obter o máximo código de medicamentos
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

    // Método para obter o mínimo código de medicamentos
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

    // Método para obter o mínimo ID de clientes
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

    // Método para agrupar e ordenar clientes e medicamentos
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

    // Método main para iniciar a aplicação
    public static void main(String args[]) {
        new sysfarm();
    }
}
