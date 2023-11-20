
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

    JLabel      lblCodigo, lblNome;
    JTextField  txtCodigo, txtNome;
    JButton     btnSalvar, btnCancelar;

    Cliente cli;
    //;JFrame f ;
    ExemploUI()
    {
        lblCodigo = new JLabel("Código");
        txtCodigo = new JTextField();
        lblNome = new JLabel("Nome");
        txtNome = new JTextField();
        btnSalvar   = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        setLayout(new GridLayout(3, 2));
        add(lblCodigo);    add(txtCodigo);
        add(lblNome);      add(txtNome);
        add(btnSalvar);    add(btnCancelar);

        EventoHandler handler = new EventoHandler();
        btnSalvar.addActionListener(handler);
        //btnCancelar.addActionListener(handler);
    }

    public void NovoCliente()
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
    }

    public static void main(String[] args)
    {
        JFrame f = new ExemploUI();
        f.setTitle("Primeira Janela");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBounds(300, 300, 400, 200);
        f.setVisible(true);
    }

    // classe interna private para tratamento de evento
    private class EventoHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            NovoCliente();
            //JOptionPane.showMessageDialog(null, "Ops, houve um clique aqui!");
        }
    }

    public class Cliente
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
