#include <iostream>
#include <vector>

const int N = 6; // Tamanho do tabuleiro (6x6)

// Função para verificar se uma cor é válida para uma posição específica
bool isValidColor(const std::vector<std::vector<int>>& board, int row, int col, int color) {


    // Verifica se a cor é diferente das diagonais principais
    if (row == col && board[0][0] == color) {
        return false;
    }
    if (row + col == N - 1 && board[0][N - 1] == color) {
        return false;
    }

    // Verifica se a cor é adjacente a ela mesma
    if (row > 0 && board[row - 1][col] == color) {
        return false;
    }
    if (col > 0 && board[row][col - 1] == color) {
        return false;
    }

    return true;
}


// Função para encontrar a próxima posição vazia no tabuleiro
bool findNextEmptyPosition(const std::vector<std::vector<int>>& board, int& row, int& col) {
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            if (board[i][j] == 0) {
                row = i;
                col = j;
                return true;
            }
        }
    }
    return false;
}

// Função principal para resolver o problema de coloração do tabuleiro
int solveBoardColoring(std::vector<std::vector<int>>& board, int row, int col) {
    // Caso base: todas as posições preenchidas
    if (row == N) {
        // Verifique se a solução atende aos critérios (subtabuleiros, diagonais, etc.)
        // Se sim, retorne 1 (uma solução válida), caso contrário, retorne 0.
        // Exemplo simplificado (não verifica todas as condições):
        return 1;
    }

    // Encontre a próxima posição vazia
    int nextRow, nextCol;
    if (!findNextEmptyPosition(board, nextRow, nextCol)) {
        // Não há mais posições vazias, retorne 0.
        return 0;
    }

    int totalSolutions = 0;
    for (int color = 1; color <= 5; ++color) {
        if (isValidColor(board, nextRow, nextCol, color)) {
            board[nextRow][nextCol] = color;
            totalSolutions += solveBoardColoring(board, nextRow, nextCol);
            board[nextRow][nextCol] = 0; // Desfaz a atribuição para explorar outras cores
        }
    }

    return totalSolutions;
}

int main() {
    std::vector<std::vector<int>> board(N, std::vector<int>(N, 0)); // Inicializa o tabuleiro vazio
    int solutions = solveBoardColoring(board, 0, 0);
    std::cout << "Quantidade de soluções: " << solutions << std::endl;
    return 0;
}
