import java.io.*;
import javax.swing.*;

public class Atividade4 {
    public static File diretorio;
    public static void main(String[] args) {
//        questao2();
//        criarDiretorio();
//        criarArquivos();
        deletar();

    }
    //QUESTAO 1
    public static void criarDiretorio() { // Cria Diretorio
        File diretorio = new File("Diretorio");
        // Verifica se um dos diretórios já existe.
        if (diretorio.exists()) { // Se sim, exibe uma mensagem informando que um dos diretórios já existe e encerra a função.
            JOptionPane.showMessageDialog(null, "O diretório já existe");
            return;
        }
        // Caso contrário, cria os diretórios
        boolean statusDir = diretorio.mkdir();
        System.out.println("Diretorio criado..." + statusDir);
        JOptionPane.showMessageDialog(null, "Diretório criado com sucesso: " + diretorio);

        // Criando subdiretórios
        File subDiretorio1 = new File(diretorio, "SubDiretorio1");
        File subDiretorio2 = new File(diretorio, "SubDiretorio2");

        boolean statusSubDir1 = subDiretorio1.mkdir();
        boolean statusSubDir2 = subDiretorio2.mkdir();

        System.out.println("SubDiretorio1 criado..." + statusSubDir1);
        System.out.println("SubDiretorio2 criado..." + statusSubDir2);

        JOptionPane.showMessageDialog(null, "Subdiretórios criados com sucesso: " + subDiretorio1 + ", " + subDiretorio2);
    }
    //questao 2
    public static void questao2()
    {
        // Obter informações do usuário
        String caminho = JOptionPane.showInputDialog(null, "Informe o caminho do diretório:");
        String nomeDiretorio = JOptionPane.showInputDialog(null, "Informe o nome do diretório:");
        String nomeSubdiretorio = JOptionPane.showInputDialog(null, "Informe o nome do subdiretório:");

        // Verificar se o subdiretório existe
        boolean existeSubdiretorio = verificarSubdiretorioExiste(caminho, nomeDiretorio, nomeSubdiretorio);

        // Mostrar resultado ao usuário
        String mensagem = existeSubdiretorio ? "O subdiretório " + nomeSubdiretorio + " existe dentro do diretório " + nomeDiretorio :
                "O subdiretório " + nomeSubdiretorio + " não existe dentro do diretório " + nomeDiretorio;
        JOptionPane.showMessageDialog(null, mensagem);
    }
    private static boolean verificarSubdiretorioExiste(String caminho, String nomeDiretorio, String nomeSubdiretorio) {
        File diretorio = new File(caminho, nomeDiretorio);
        File subdiretorio = new File(diretorio, nomeSubdiretorio);
        return subdiretorio.exists();
    }


    public static void criarArquivos() {
        // Diretórios
        File diretorio = new File("Diretorio");
        File subDiretorio1 = new File(diretorio, "SubDiretorio1");
        File subDiretorio2 = new File(diretorio, "SubDiretorio2");

        // Arquivos para o SubDiretorio1
        File arquivoTxt1 = new File(subDiretorio1, "arquivo1.txt");
        File arquivoCsv1 = new File(subDiretorio1, "arquivo1.csv");

        // Arquivos para o SubDiretorio2
        File arquivoTxt2 = new File(subDiretorio2, "arquivo2.txt");
        File arquivoCsv2 = new File(subDiretorio2, "arquivo2.csv");

        try {
            // Cria os arquivos no SubDiretorio1
            if (arquivoTxt1.createNewFile()) {
                System.out.println("Arquivo TXT criado: " + arquivoTxt1.getName());
            } else {
                System.out.println("Arquivo TXT já existe.");
            }

            if (arquivoCsv1.createNewFile()) {
                System.out.println("Arquivo CSV criado: " + arquivoCsv1.getName());
            } else {
                System.out.println("Arquivo CSV já existe.");
            }

            // Cria os arquivos no SubDiretorio2
            if (arquivoTxt2.createNewFile()) {
                System.out.println("Arquivo TXT criado: " + arquivoTxt2.getName());
            } else {
                System.out.println("Arquivo TXT já existe.");
            }

            if (arquivoCsv2.createNewFile()) {
                System.out.println("Arquivo CSV criado: " + arquivoCsv2.getName());
            } else {
                System.out.println("Arquivo CSV já existe.");
            }

        } catch (IOException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
        }
    }
    //QUESTÃO 3 & 4 & 6
    public static void criarArquivos3() {
        // Recebe o nome dos arquivos do usuário
        String[] arquivos = new String[3];
        for (int i = 0; i < 3; i++) {
            arquivos[i] = JOptionPane.showInputDialog("Digite o nome do arquivo " + (i+1) + " (.txt):");
        }

        // Cria os arquivos no diretório
        for (String arquivo : arquivos) {
            File file = new File(diretorio, arquivo + ".txt");
            try {
                if (file.createNewFile()) {
                    System.out.println("Arquivo TXT criado: " + file.getName());
                } else {
                    System.out.println("Arquivo TXT já existe.");
                }
            } catch (IOException e) {
                System.out.println("Ocorreu um erro.");
                e.printStackTrace();
            }
        }


    }
    //QUESTAO 5
    public static void deletar()
    {
        File diretorio = new File("Diretorio\\SubDiretorio1"); //quando quiser acessar o subdiretorio, nome do diretorio e o nome do sub
        File arq1 = new File(diretorio, "arquivo1.csv");
        File ar1 = new File(diretorio, "arquivo1.txt");
        if(arq1.exists())
        {
            boolean delete = arq1.delete();
            boolean delete2 = ar1.delete();
            JOptionPane.showMessageDialog(null, delete);
        }
        else
            JOptionPane.showMessageDialog(null, "nao existe");
    }
    //QUESTAO 7
    public static void criarArq() throws IOException {
        File diretorio = new File("diretorio1");
        diretorio.mkdir();

        File arq = new File(diretorio, "arquivo.dat");
        arq.createNewFile();
        JOptionPane.showMessageDialog(null, diretorio.getPath());
        JOptionPane.showMessageDialog(null, arq.getPath());
        File arq2 = new File(diretorio, "arquivo.txt");
        File diretorio2 = new File("diretos");
        arq.renameTo(arq2);
        diretorio.renameTo(diretorio2);


    }





















}
