import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class colorir {
    static int tamanho;
    static int numCores;
    static int primeiraSolucaoEncontrada;

    public static void adicionarSubgrade(int[][] grade, int linha, int coluna, Set<String> subgrades) {
        if (linha > 0 && coluna > 0) {
            String subgrade = criarStringSubgrade(grade, linha, coluna);
            subgrades.add(subgrade);
        }
    }

    public static void removerSubgrade(int[][] grade, int linha, int coluna, Set<String> subgrades) {
        if (linha > 0 && coluna > 0) {
            String subgrade = criarStringSubgrade(grade, linha, coluna);
            subgrades.remove(subgrade);
        }
    }

    public static String criarStringSubgrade(int[][] grade, int linha, int coluna) {
        return obterCima(grade, linha, coluna) + "," + obterEsquerda(grade, linha, coluna) + "," + obterDiagonal(grade, linha, coluna) + "," + grade[linha][coluna];
    }

    private static int obterCima(int[][] grade, int linha, int coluna) {
        return grade[linha - 1][coluna];
    }

    private static int obterEsquerda(int[][] grade, int linha, int coluna) {
        return grade[linha][coluna - 1];
    }

    private static int obterDiagonal(int[][] grade, int linha, int coluna) {
        return grade[linha - 1][coluna - 1];
    }

    public static int[] encontrarCelulaVazia(int[][] grade, int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (grade[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
    
    public static void exibirGrade(int[][] grade) {
        for (int[] linha : grade) {
            for (int celula : linha) {
                System.out.print(celula + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static boolean ehColocacaoValida(int[][] grade, int cor, int linha, int coluna, int tamanho, Set<String> subgrades) {
        int[][] direcoesAdjacentes = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int[] direcao : direcoesAdjacentes) {
            int linhaAdj = linha + direcao[0];
            int colunaAdj = coluna + direcao[1];
            if (0 <= linhaAdj && linhaAdj < tamanho && 0 <= colunaAdj && colunaAdj < tamanho && grade[linhaAdj][colunaAdj] == cor) {
                return false;
            }
        }
        
        if ((linha == coluna && grade[linha][tamanho - 1 - linha] != cor && grade[linha][tamanho - 1 - linha] != 0) ||
            (linha + coluna == tamanho - 1 && grade[linha][linha] != cor && grade[linha][linha] != 0)) {
            return false;
        }
        
        if (linha > 0 && coluna > 0) {
            grade[linha][coluna] = cor;
            String subgrade = criarStringSubgrade(grade, linha, coluna);
            grade[linha][coluna] = 0;
            if (!subgrade.equals("0,0,0,0") && subgrades.contains(subgrade)) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void preencherGrade(int[][] grade, int[] cores, int[] solucoes, int tamanho, Set<String> subgrades) {
        int[] celulaVazia = encontrarCelulaVazia(grade, tamanho);
        int linha = celulaVazia[0];
        int coluna = celulaVazia[1];
        if (linha == -1 && coluna == -1) {
            solucoes[0]++;
            if ((tamanho == 5 || tamanho == 7 || tamanho == 3) && primeiraSolucaoEncontrada < 10) { // Passo 2
                System.out.println("Primeira solução para tamanho " + tamanho + ":");
                exibirGrade(grade);
                primeiraSolucaoEncontrada++; // Passo 3
            }
            return;
        }
        
       
        /*Resumo do Processo:
            Tentar colocar uma cor em uma célula.
            Verificar se essa colocação é válida.
            Se válida, adicionar a subgrade correspondente ao conjunto e chamar a função recursiva para preencher o restante da grade.
            Após a recursão, reverter a colocação (backtrack) removendo a subgrade e restaurando a célula vazia.
            Este processo permite explorar todas as combinações possíveis de preenchimento da grade, garantindo que todas as soluções válidas sejam encontradas. 
            Aqui está o trecho de código comentado com estas explicações: */
        for (int cor : cores) {
            if (ehColocacaoValida(grade, cor, linha, coluna, tamanho, subgrades)) {
                grade[linha][coluna] = cor;
                adicionarSubgrade(grade, linha, coluna, subgrades);
                preencherGrade(grade, cores, solucoes, tamanho, subgrades);
                removerSubgrade(grade, linha, coluna, subgrades);
                grade[linha][coluna] = 0;
            }
        }

    }
    
    public static int calcularTotalSolucoes(int tamanho, int[] cores) {
        int[][] grade = new int[tamanho][tamanho];
        int[] solucoes = {0};
        Set<String> subgrades = new HashSet<>();
        
        preencherGrade(grade, cores, solucoes, tamanho, subgrades);
        return solucoes[0];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 

        System.out.print("Digite o tamanho da grade (NxN): ");
        tamanho = scanner.nextInt();
        System.out.print("Digite o número de cores: ");
        numCores = scanner.nextInt();
        scanner.close();

        int[] cores = new int[numCores];
        for (int i = 0; i < numCores; i++) {
            cores[i] = i + 1;
        }

        int totalSolucoes = calcularTotalSolucoes(tamanho, cores);
        System.out.println("Total de soluções encontradas para uma grade de " + tamanho + "x" + tamanho + " com " + numCores + " cores: " + totalSolucoes);
    }
}
