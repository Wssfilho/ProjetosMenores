import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class colorir {
    // Declarações de variáveis globais
    static int tamanho;  // Tamanho da grade NxN
    static int numCores;  // Número de cores disponíveis
    static int primeiraSolucaoEncontrada;  // Contador para a primeira solução encontrada
    
    // Função para adicionar um subtabuleiro 2x2 ao conjunto de subtabuleiros
    public static void adicionarSubgrade(int[][] grade, int linha, int coluna, Set<String> subgrades) {
        if (linha > 0 && coluna > 0) {  // Verifica se a linha e a coluna são maiores que 0
            String subgrade = criarStringSubgrade(grade, linha, coluna);  // Cria uma string com o subtabuleiro 2x2
            subgrades.add(subgrade);  // Adiciona o subtabuleiro ao conjunto de subtabuleiros
        }
    }

    // Função para remover um subtabuleiro 2x2 do conjunto de subtabuleiros
    public static void removerSubgrade(int[][] grade, int linha, int coluna, Set<String> subgrades) {
        if (linha > 0 && coluna > 0) {  // Verifica se a linha e a coluna são maiores que 0
            String subgrade = criarStringSubgrade(grade, linha, coluna);  // Cria uma string com o subtabuleiro 2x2
            subgrades.remove(subgrade);  // Remove o subtabuleiro do conjunto de subtabuleiros
        }
    }

    // Obtém os valores dos vizinhos da célula e retorna uma string representando o subtabuleiro 2x2
    public static String criarStringSubgrade(int[][] grade, int linha, int coluna) {
        return obterCima(grade, linha, coluna) + "," + obterEsquerda(grade, linha, coluna) + "," + obterDiagonal(grade, linha, coluna) + "," + grade[linha][coluna];
    }

    // Função para obter o valor da célula acima
    private static int obterCima(int[][] grade, int linha, int coluna) {
        return grade[linha - 1][coluna];
    }

    // Função para obter o valor da célula à esquerda
    private static int obterEsquerda(int[][] grade, int linha, int coluna) {
        return grade[linha][coluna - 1];
    }

    // Função para obter o valor da célula diagonal superior esquerda
    private static int obterDiagonal(int[][] grade, int linha, int coluna) {
        return grade[linha - 1][coluna - 1];
    }

    // Função para encontrar a próxima célula vazia na grade
    public static int[] encontrarCelulaVazia(int[][] grade, int tamanho) {
        for (int i = 0; i < tamanho; i++) {  // Itera sobre as linhas
            for (int j = 0; j < tamanho; j++) {  // Itera sobre as colunas
                if (grade[i][j] == 0) {  // Verifica se a célula está vazia
                    return new int[]{i, j};  // Retorna a posição da célula vazia
                }
            }
        }
        return new int[]{-1, -1};  // Retorna {-1, -1} se não houver células vazias
    }

    // Função para exibir a grade
    public static void exibirGrade(int[][] grade) {
        for (int[] linha : grade) {  // Itera sobre as linhas da grade
            for (int celula : linha) {  // Itera sobre as células da linha
                System.out.print(celula + " ");  // Imprime o valor da célula
            }
            System.out.println();  // Pula para a próxima linha
        }
        System.out.println();  // Linha extra para separar as grades
    }

    // Função para verificar se a colocação de uma cor em uma célula é válida
    public static boolean ehColocacaoValida(int[][] grade, int cor, int linha, int coluna, int tamanho, Set<String> subgrades) {
        int[][] direcoesAdjacentes = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // Direções adjacentes

        for (int[] direcao : direcoesAdjacentes) {  // Itera sobre as direções adjacentes
            int adjl = linha + direcao[0];  // Calcula a linha adjacente
            int adjc = coluna + direcao[1];  // Calcula a coluna adjacente
            if (0 <= adjl && adjl < tamanho && 0 <= adjc && adjc < tamanho && grade[adjl][adjc] == cor) { // Verifica se a célula adjacente está dentro dos limites e tem a mesma cor
                return false;  // Verifica se a célula adjacente tem a mesma cor
            }
        }

        // Verifica a diagonal principal e a diagonal secundária
        if ((linha == coluna && grade[linha][tamanho - 1 - linha] != cor && grade[linha][tamanho - 1 - linha] != 0) ||
            (linha + coluna == tamanho - 1 && grade[linha][linha] != cor && grade[linha][linha] != 0)) {
            return false;
        }

        // Verifica se o subtabuleiro 2x2 é válido
        if (linha > 0 && coluna > 0) {
            grade[linha][coluna] = cor;  // Coloca a cor na célula
            String subgrade = criarStringSubgrade(grade, linha, coluna);  // Cria a string do subtabuleiro 2x2
            grade[linha][coluna] = 0;  // Remove a cor da célula
            if (!subgrade.equals("0,0,0,0") && subgrades.contains(subgrade)) {
                return false;  // Verifica se o subtabuleiro 2x2 já foi utilizado
            }
        }

        return true;  // Retorna verdadeiro se a colocação for válida
    }

    // Função recursiva para preencher a grade
    public static void preencherGrade(int[][] grade, int[] cores, int[] solucoes, int tamanho, Set<String> subgrades) {
        int[] celulaVazia = encontrarCelulaVazia(grade, tamanho);  // Encontra a próxima célula vazia
        int linha = celulaVazia[0];  // Obtém a linha da célula vazia
        int coluna = celulaVazia[1];  // Obtém a coluna da célula vazia
        if (linha == -1 && coluna == -1) {  // Verifica se não há células vazias
            solucoes[0]++;  // Incrementa o contador de soluções
            if ((tamanho == 5 || tamanho == 7 || tamanho == 3) && primeiraSolucaoEncontrada < 10) {  // Passo 2
                System.out.println("Primeira solução para tamanho " + tamanho + ":");
                exibirGrade(grade);  // Exibe a primeira solução encontrada
                primeiraSolucaoEncontrada++;  // Incrementa o contador de primeiras soluções encontradas
            }
            return;
        }

        /* Resumo do Processo:
           Tentar colocar uma cor em uma célula.
           Verificar se essa colocação é válida.
           Se válida, adicionar a subgrade correspondente ao conjunto e chamar a função recursiva para preencher o restante da grade.
           Após a recursão, reverter a colocação (backtrack) removendo a subgrade e restaurando a célula vazia.
           Este processo permite explorar todas as combinações possíveis de preenchimento da grade, garantindo que todas as soluções válidas sejam encontradas. */
        for (int cor : cores) {  // Itera sobre as cores disponíveis
            if (ehColocacaoValida(grade, cor, linha, coluna, tamanho, subgrades)) {  // Verifica se a colocação é válida
                grade[linha][coluna] = cor;  // Coloca a cor na célula
                adicionarSubgrade(grade, linha, coluna, subgrades);  // Adiciona o subtabuleiro ao conjunto de subtabuleiros
                preencherGrade(grade, cores, solucoes, tamanho, subgrades);  // Chama a função recursiva para preencher o restante da grade
                removerSubgrade(grade, linha, coluna, subgrades);  // Remove o subtabuleiro do conjunto de subtabuleiros
                grade[linha][coluna] = 0;  // Remove a cor da célula (backtrack)
            }
        }
    }

    // Função para calcular o total de soluções para uma grade de tamanho NxN com cores disponíveis
    public static int calcularTotalSolucoes(int tamanho, int[] cores) {
        int[][] grade = new int[tamanho][tamanho];  // Inicializa a grade NxN
        int[] solucoes = {0};  // Inicializa o contador de soluções
        Set<String> subgrades = new HashSet<>();  // Inicializa o conjunto de subtabuleiros

        preencherGrade(grade, cores, solucoes, tamanho, subgrades);  // Chama a função para preencher a grade
        return solucoes[0];  // Retorna o total de soluções encontradas
    }

    // Função principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Cria um scanner para ler a entrada do usuário

        System.out.print("Digite o tamanho da grade (NxN): ");
        tamanho = scanner.nextInt();  // Lê o tamanho da grade
        System.out.print("Digite o número de cores: ");
        numCores = scanner.nextInt();  // Lê o número de cores
        scanner.close();  // Fecha o scanner

        int[] cores = new int[numCores];  // Inicializa o array de cores
        for (int i = 0; i < numCores; i++) {
            cores[i] = i + 1;  // Preenche o array de cores
        }

        int totalSolucoes = calcularTotalSolucoes(tamanho, cores);  // Calcula o total de soluções
        System.out.println("Total de soluções encontradas para uma grade de " + tamanho + "x" + tamanho + " com " + numCores + " cores: " + totalSolucoes);  // Imprime o total de soluções
    }
}
