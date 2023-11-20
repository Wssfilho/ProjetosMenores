
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ExemploUI extends JFrame{

    JLabel      lblCodigo, lblNome, lblNumClientes;
    JTextField  txtCodigo, txtNome;
    JButton     btnSalvar, btnCancelar;

    Cliente cli;
    //;JFrame f ;
    ExemploUI() //construtor do ExemploUi
    {
        lblCodigo = new JLabel("Código");
        txtCodigo = new JTextField();
        lblNome = new JLabel("Nome");
        txtNome = new JTextField();
        btnSalvar   = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        lblNumClientes = new JLabel("Total clientes : 0");

        setLayout(new GridLayout(4, 2));
        add(lblCodigo);    add(txtCodigo);
        add(lblNome);      add(txtNome);
        add(btnSalvar);    add(btnCancelar);
        add(lblNumClientes);

        EventoHandler handler = new EventoHandler();
        btnSalvar.addActionListener(handler);
        btnCancelar.addActionListener(handler);
    }

    public void NovoCliente() //metodo do Exemplo ui para atribuir a classe cliente
    {
        // Capturar dados dos campos texto para
        // variáveis
        int cod;
        String nome;

        cod = Integer.parseInt(txtCodigo.getText());
        nome = txtNome.getText();

        // criar Objeto
        cli = new Cliente();
        cli.setCodigo(cod);
        cli.setNome(nome);

        JOptionPane.showMessageDialog(rootPane,
                "Objeto criado : " + cli.getNome());
        lblNumClientes.setText("Total clientes : 1");
    }

    public static void main(String[] args) //metodo main
    {
        JFrame f = new ExemploUI();
        f.setTitle("Primeira Janela");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBounds(300, 300, 400, 200);
        f.setVisible(true);
    }

    // classe interna private para tratamento de evento
    private class EventoHandler //classe para atribuir evento a botões
            implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == btnSalvar)
            {     NovoCliente(); }
            if (event.getSource() == btnCancelar)
            {     JOptionPane.showMessageDialog(null, "Cancelar"); }

        }
    }

    public class Cliente //classe cliente
    {
        int codigo;
        String nome;

        public int getCodigo() {
            return codigo;
        }

        public String getNome() {
            return nome;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void imprimir()
        {
            System.out.println(" Cliente ");
            System.out.println(" Cod : " + this.codigo);
            System.out.println("Nome : " + this.nome);
        }

    }

}