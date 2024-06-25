import java.util.Collections;
import java.util.Arrays;

public class Main {

    private static boolean isValidPosition(String[][] board, int row, int col, String color) {
        // Verifica se (row, col) está dentro dos limites do tabuleiro
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }

        // Verifica se a célula está vazia
        if (!board[row][col].isEmpty()) {
            return false;
        }

        // Verifica os vizinhos (cima, baixo, esquerda, direita) para a mesma cor
        int[][] neighbors = {{row - 1, col}, {row + 1, col}, {row, col - 1}, {row, col + 1}};
        for (int[] neighbor : neighbors) {
            int r = neighbor[0];
            int c = neighbor[1];
            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c].equals(color)) {
                return false;
            }
        }

        // Verifica a diagonal principal
        if (row == col) {
            for (int i = 0; i < row; i++) {
                if (!board[i][i].isEmpty() && !board[i][i].equals(color)) {
                    return false;
                }
            }
        }

        // Verifica a diagonal secundária
        if (row + col == board.length - 1) {
            for (int i = 0; i < row; i++) {
                if (!board[i][board.length - 1 - i].isEmpty() && !board[i][board.length - 1 - i].equals(color)) {
                    return false;
                }
            }
        }

        // Se todas as condições forem satisfeitas, é uma posição válida
        return true;
    }

    private static boolean solveBoard(String[][] board, String[] colors, int row, int col) {
        // Caso base: se preenchemos todas as linhas, retorna verdadeiro
        if (row == board.length) {
            return true;
        }

        // Calcula os índices da próxima linha e coluna
        int nextRow = col < board[0].length - 1 ? row : row + 1;
        int nextCol = (col + 1) % board[0].length;

        // Embaralha as cores para tentar em ordem diferente a cada chamada
        Collections.shuffle(Arrays.asList(colors));

        // Tenta cada cor na posição atual
        for (String color : colors) {
            if (isValidPosition(board, row, col, color)) {
                board[row][col] = color; // Coloca a cor no tabuleiro

                // Tenta resolver recursivamente para as próximas posições
                if (solveBoard(board, colors, nextRow, nextCol)) {
                    return true; // Se a solução foi encontrada, retorna verdadeiro
                }

                board[row][col] = ""; // Retrocede: reseta a posição do tabuleiro
            }
        }

        return false; // Nenhuma cor válida encontrada para esta posição
    }

    public static void main(String[] args) {
        int size = 7; // Pode ser alterado para diferentes tamanhos de tabuleiro
    String[] colors = {"1", "2", "3", "4", "5"};
        String[][] board = new String[size][size];

        // Inicializa o tabuleiro com strings vazias
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = "";
            }
        }

        // Mede o tempo de execução
        long startTime = System.nanoTime();

        // Resolve o problema de coloração do tabuleiro
        if (solveBoard(board, colors, 0, 0)) {
            // Imprime o tabuleiro solução
            for (String[] row : board) {
                System.out.println(String.join(" ", row));
            }
        } else {
            System.out.println("Não há solução.");
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Converte para milissegundos
        System.out.println("Tempo de execução: " + duration + " ms");
    }
}