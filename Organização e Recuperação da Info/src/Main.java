import java.awt.event.ActionListener;
import java.io.*;
import java.awt.*;
import java.nio.file.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends JFrame {
    // declaração dos botoes
    JButton btnCriarDir, btnCriarArq, btnRenomear, btnCopiar;
    JButton btnarquivos, btnarquivos2, mostrar;
    File arquivo;
    JFrame manipulacao, f;
    JPanel formularioPane3, formularioPane;
    StringBuilder sb;
    private static final JFrame janela2 = new Main();// Criando uma instância de JFrame

    Main()
    {
        mostrar = new JButton("Mostrar arquivo");
        btnarquivos = new JButton("Manipular arquivos");
        btnarquivos2 = new JButton("Escrita & Leitura");
        JPanel janelaJPanel = new JPanel();
        janelaJPanel.setLayout(new GridLayout(1, 2));
        janelaJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        janelaJPanel.add(btnarquivos);
        janelaJPanel.add(btnarquivos2);
        btnarquivos.addActionListener(e->novoframe());
        btnarquivos2.addActionListener(e->janela3());
        //mostrar.addActionListener(e->mostrarCSV());

        Container contentPane = getContentPane();// Obtém o painel de conteúdo da janela principal
        contentPane.add(janelaJPanel, BorderLayout.NORTH);
    }

    private void janela3()
    {

        manipulacao = new JFrame();
        criarjanela(manipulacao);
        formularioPane3 = new JPanel();
        JButton[] botoes = {
                new JButton("Mostrar"),
                new JButton("Criar"),
                new JButton("Verificar")
        };

        // Cria as ações
        ActionListener[] acoes = {
                e -> mostrarCSV(),
                e -> io(),
                e -> status()
        };

        // Cria o painel com os botões
        formularioPane3 = criarPainelComBotoes(botoes, acoes);
        Container contentPane3 = manipulacao.getContentPane();// Obtém o painel de conteúdo da janela principal
        contentPane3.add(formularioPane3, BorderLayout.NORTH);

    }

    public void status() {
        arquivo = new File("C:\\adriele", "dados.csv");
        sb = new StringBuilder(); // usada para criar e manipular strings dinamicamente.
        sb.append("Tamanho do arquivo: ").append(length(arquivo)).append(" bytes\n");
        sb.append("Última modificação: ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(lastModified(arquivo)))).append("\n");
        sb.append("Caminho do arquivo: ").append(toString(arquivo)).append("\n");
        sb.append("Status de delete: ").append((delete(arquivo)));
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Método para obter o tamanho do arquivo
    public long length(File arquivo) {
        return arquivo.length();
    }

    // Método para obter a data da última modificação do arquivo
    public long lastModified(File arquivo) {
        return arquivo.lastModified();
    }

    // Método para obter o caminho do arquivo como uma string
    public String toString(File arquivo) {
        return arquivo.getPath();
    }
    public boolean delete(File arquivo) {
        return arquivo.delete();
    }

    public void mostrarCSV() {
        String csvFile = "C:\\adriele\\dados.csv";
        String line = "";
        String csvSplitBy = ",";
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // lê e descarta a primeira linha
            sb.append(String.format("%-10s %-10s %-10s%n", "Nome:", "Idade:", "Cidade:"));
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(csvSplitBy);
                sb.append(String.format("%-10s %-10s %-10s%n", campos[0], campos[1], campos[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, sb.toString());

    }
    private JFrame criarjanela(JFrame f)
    {
        f.setBounds(0, 0, 400, 400);
        f.setVisible(true);
        f.setTitle("Primeira Janela");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return f;
    }
    private void io() {
        // Cria um objeto File para o arquivo CSV
        File arquivoCSV = new File("C:\\adriele\\dados.csv");
        if(arquivoCSV.exists())
        {
        JOptionPane.showMessageDialog(null, "Já existe o arquivo");
            return;
        }
        // Cria um PrintWriter para escrever no arquivo
        PrintWriter escritor = null;
        try {
            escritor = new PrintWriter(new FileOutputStream(arquivoCSV));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Escreve a cabeçalho do CSV
        escritor.println("Nome,Idade,Cidade");

        // Obtém os dados do usuário usando JOptionPane
        for (int i = 0; i < 5; i++) {
            String nome = JOptionPane.showInputDialog("Digite seu nome:");
            int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite sua idade:"));
            String cidade = JOptionPane.showInputDialog("Digite sua cidade:");

            // Escreve os dados no arquivo CSV
            escritor.println(nome + "," + idade + "," + cidade);
        }

        // Fecha o PrintWriter
        escritor.close();

        // Exibe uma mensagem de sucesso
        JOptionPane.showMessageDialog(null, "Dados gravados com sucesso no arquivo " + arquivoCSV.getPath());
    }
    private JPanel criarPainelComBotoes(JButton[] botoes, ActionListener[] acoes) {
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(botoes.length, 2)); // Define o layout do painel como uma grade com o número de linhas igual ao número de botões e 2 colunas.
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Aqui, estamos definindo uma borda

        // Adiciona os botões ao painel e as ações aos botões
        for (int i = 0; i < botoes.length; i++) {
            painel.add(botoes[i]);
            botoes[i].addActionListener(acoes[i]);
        }

        return painel;
    }

    private void novoframe() {
        /*Isso será um novo método, pois terá um programa de início na qual irá chamar os outros programas*/
        btnCriarDir = new JButton("CriarDiretorio");// Cria um botão com o texto "Criar Diretório"
        btnCriarArq = new JButton("CriarArquivo");// Cria um botão com o texto "Criar Arquivo"
        btnRenomear = new JButton("RenomearArquivo");// Cria um botão com o texto "Renomear Arquivo"
        btnCopiar = new JButton("Copiar Arquivo");// Cria um botão com o texto "Copiar Arquivo"
        f = new JFrame();
        criarjanela(f);
        formularioPane = new JPanel();
        JButton[] botoes = {
                new JButton("CriarDiretorio"),
                new JButton("CriarArquivo"),
                new JButton("RenomearArquivo"),
                new JButton("Copiar Arquivo"),
        };
        // Cria as ações
        ActionListener[] acoes = {
                e -> criarDiretorio(),
                e -> criarArquivo(),
                e -> renomearArquivo(),
                e -> copiarArquivo(),
        };

        // Cria o painel com os botões
        formularioPane = criarPainelComBotoes(botoes, acoes);
        Container contentPane2 = f.getContentPane();// Obtém o painel de conteúdo da janela principal
        contentPane2.add(formularioPane, BorderLayout.NORTH);
        // posicionando-o na parte superior (Norte)
        //janela2.dispose();

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
        JOptionPane.showMessageDialog(null, "Arquivo(os) renomeados com sucesso!" + success + success2);

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

        janela2.setTitle("Menu");// Definindo o título da janela
        janela2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configurando a ação padrão ao fechar a janela
        janela2.setBounds(300, 300, 400, 100);// Definindo as dimensões e posição da janela
        janela2.setVisible(true);// Tornando a janela visível
    }

}
