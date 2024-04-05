import java.io.*;
import java.awt.*;
import java.nio.file.*;
import javax.swing.*;
public class Main extends JFrame {


    // declaração dos botoes
    JButton btnCriarDir, btnCriarArq, btnRenomear, btnCopiar, enviar;
    JButton btnnovo;
    JPanel formularioPane;
    JButton botao;
    JTextField texto;
    private static final JFrame f = new Main();// Criando uma instância de JFrame
    private static JFrame janela2;

    Main() {

        btnCriarDir = new JButton("CriarDiretorio");// Cria um botão com o texto "Criar Diretório"
        btnCriarArq = new JButton("CriarArquivo");// Cria um botão com o texto "Criar Arquivo"
        btnRenomear = new JButton("RenomearArquivo");// Cria um botão com o texto "Renomear Arquivo"
        btnCopiar = new JButton("Copiar Arquivo");// Cria um botão com o texto "Copiar Arquivo"
        btnnovo = new JButton("Nova janela");

        // Um JPanel é um componente de contêiner leve que pode ser usado para agrupar
        // outros componentes em uma interface gráfica.
        JPanel formularioPane = new JPanel();
        formularioPane.setLayout(new GridLayout(4, 2));// define o layout do painel como uma grade com 4 linhas e 2
                                                       // colunas.
        formularioPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Aqui, estamos definindo uma borda
                                                                                   // vazia (sem margens) para o painel.
        // Adicionando os controles no formulario. Ou seja na tela
        formularioPane.add(btnCriarDir);
        formularioPane.add(btnCriarArq);
        formularioPane.add(btnRenomear);
        formularioPane.add(btnCopiar);
        formularioPane.add(btnnovo);
        btnnovo.addActionListener(e -> novoframe());
        btnCopiar.addActionListener(e -> copiarArquivo());
        btnCriarDir.addActionListener(e -> criarDiretorio());// Configura um ouvinte de ação para o botão "Criar
                                                             // Diretório"
        btnCriarArq.addActionListener(e -> criarArquivo());// Configura um ouvinte de ação para o botão "Criar Arquivo"
        btnRenomear.addActionListener(e -> renomearArquivo());// Configura um ouvinte de ação para o botão "Renomear"
        Container contentPane = getContentPane();// Obtém o painel de conteúdo da janela principal
        contentPane.add(formularioPane, BorderLayout.NORTH);// Adiciona o painel do formulário ao painel de conteúdo,
                                                            // posicionando-o na parte superior (Norte)

    }

    
    public void novoframe() {
        botao = new JButton("Novo botao");
        enviar = new JButton("Enviar");
        janela2 = new JFrame();
        janela2.setBounds(0, 0, 400, 400);
        janela2.setVisible(true);
        janela2.setTitle("Primeira Janela");
        janela2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        texto = new JTextField();
        JPanel janelaJPanel = new JPanel();
        janelaJPanel.setLayout(new GridLayout(1, 2));
        janelaJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        janelaJPanel.add(botao);
        janelaJPanel.add(enviar);
        janelaJPanel.add(texto);
        f.dispose();

        enviar.addActionListener(e -> enviar());
        Container contentPane2 = janela2.getContentPane(); // janela dois porque é segunda janela
        contentPane2.add(janelaJPanel, BorderLayout.NORTH);
    }
    //retirar
    public void enviar() 
    {
        try {
            String a = texto.getText();
            texto.setText("");
            if (a.isEmpty()) {
                JOptionPane.showMessageDialog(null, "CAMPO vazio");
            } else {
                JOptionPane.showMessageDialog(rootPane, a);
            }       
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void copiarArquivo() {
        // Define o caminho do arquivo original
        Path arquivoOriginal = Paths.get("C:\\adriele\\renomeadoteste1.txt");

        // Define o caminho do arquivo de destino
        Path arquivoDestino = Paths.get("C:\\wilson\\renomeadoteste1.txt");
        Path arquivoOriginal1 = Paths.get("C:\\adriele\\renomeadoteste2.txt");

        // Define o caminho do arquivo de destino
        Path arquivoDestino1 = Paths.get("C:\\wilson\\renomeadoteste2.txt");
        Path arquivoOriginal3 = Paths.get("C:\\adriele\\teste.txt");

        // Define o caminho do arquivo de destino
        Path arquivoDestino3 = Paths.get("C:\\wilson\\teste.txt");
        Path arquivoOriginal4 = Paths.get("C:\\adriele\\teste2.txt");

        // Define o caminho do arquivo de destino
        Path arquivoDestino4 = Paths.get("C:\\wilson\\teste2.txt");

        // tratamento para caso aperte o botao de copiar sem renomear e renomeado

        // Verifica se o arquivo original existe antes de tentar copiá-lo
        if (Files.exists(arquivoOriginal) && Files.exists(arquivoOriginal1)) {
            // Copia o arquivo original para o destino
            try {
                Files.copy(arquivoOriginal, arquivoDestino, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(arquivoOriginal1, arquivoDestino1, StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(null, "Arquivo copiado");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        // caso o nome do arquivo for anterior ao clique do botão renomear
        if (Files.exists(arquivoOriginal3) && Files.exists(arquivoOriginal4)) {
            try {
                Files.copy(arquivoOriginal3, arquivoDestino3, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(arquivoOriginal4, arquivoDestino4, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void renomearArquivo() {
        // Cria um objeto File para o arquivo antigo "teste.txt"
        File antigo1 = new File("C:\\adriele\\teste.txt");

        // Cria um objeto File para o novo nome "renomeadoteste1.txt"
        File novo1 = new File("C:\\adriele\\renomeadoteste1.txt");

        /* Renomeia o arquivo antigo para o novo nome */
        boolean success = antigo1.renameTo(novo1);

        // Cria um objeto File para o arquivo antigo "teste2.txt"
        File antigo2 = new File("C:\\adriele\\teste2.txt");

        // Cria um objeto File para o novo nome "renomeadoteste2.txt"
        File novo2 = new File("C:\\adriele\\renomeadoteste2.txt");

        // Renomeia o segundo arquivo antigo para o novo nome
        boolean success2 = antigo2.renameTo(novo2);
        JOptionPane.showMessageDialog(null, "Arquivo(os) renomeados com sucesso!");

    }

    // a função `renomearArquivo()` renomeia dois arquivos:
    // "teste.txt" para "renomeadoteste1.txt" e "teste2.txt" para
    // "renomeadoteste2.txt".
    // O resultado da operação de renomeação é armazenado nas variáveis "success" e
    // `success2`, que são do tipo booleano (verdadeiro ou falso).
    // Se a renomeação for bem-sucedida, essas variáveis terão o valor "true", caso
    // contrário, terão o valor `false`.

    public void criarArquivo() { // cria dois objetos File para representar os arquivos “teste.txt” e
                                 // “teste2.txt” no diretório “C:\adriele”.
        File arquivo = new File("C:\\adriele", "teste.txt");
        File arquivo2 = new File("C:\\adriele", "teste2.txt");
        if (arquivo.exists() || arquivo2.exists())
        { // Verifica se algum dos arquivos já existe. Se sim, exibe uma
                                                     // mensagem informando que um dos arquivos já existe
            JOptionPane.showMessageDialog(null, "Um dos arquivos já existe");
            return;
        }

        try
        { // Tenta criar os arquivos usando o método createNewFile()
            arquivo.createNewFile();
            arquivo2.createNewFile();
            // Imprime no console se os arquivos foram criados com sucesso ou não.
            JOptionPane.showMessageDialog(null, "Arquivo criado com sucesso");

        } catch (IOException ex) // Em caso de exceção (por exemplo, se houver um problema ao criar os arquivos),
                                // imprime o rastreamento da pilha de erros.
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Arquivo não criado");
        }
    }

    private void criarDiretorio() { // Cria dois objetos File representando os diretórios “adriele” e “wilson” na
                                   // unidade C do sistema de arquivos.
        File diretorio = new File("C:\\adriele");
        File diretorio2 = new File("C:\\wilson");
        // Verifica se um dos diretórios já existe.
        if (diretorio.exists() || diretorio2.exists()) { // Se sim, exibe uma mensagem informando que um dos diretórios
                                                         // já existe e encerra a função.
            JOptionPane.showMessageDialog(null, "Um dos diretórios já existe");
            return;
        }
        // Caso contrário, cria os diretórios “adriele” e “wilson”.
        boolean statusDir = diretorio.mkdir();
        System.out.println("Diretorio criado..." + statusDir);

        boolean statusDir2 = diretorio2.mkdir();
        System.out.println("Diretorio criado..." + statusDir2);
        JOptionPane.showMessageDialog(null, "Diretório criado com sucesso " + diretorio + diretorio2);

    }

    public static void main(String[] args) {

        f.setTitle("Primeira Janela");// Definindo o título da janela
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configurando a ação padrão ao fechar a janela
        f.setBounds(300, 300, 400, 400);// Definindo as dimensões e posição da janela
        f.setVisible(true);// Tornando a janela visível
    }

}
