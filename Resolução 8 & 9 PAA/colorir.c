#include <stdio.h>
#include <stdbool.h>

#define N 6 // Tamanho do tabuleiro (7x7)
int cont = 0;
int C = 5;

bool verificacoes(int board[N][N], int x, int y, int color) {
    // Verificação se a próxima cor é igual à cor anterior
    if (x > 0 && y > 0 && board[x][y - 1] == color) {
        return false;
    }
    // Verificação da diagonal principal
    if (x == y) {
        for (int i = 0; i < x; i++) {
            if (board[i][i] != 0 && board[i][i] != color) {
                return false;
            }
        }
    }

    // Verificação da diagonal secundária
    if (x + y == N - 1) {
        for (int i = 0; i < x; i++) {
            if (board[i][N - 1 - i] != 0 && board[i][N - 1 - i] != color) {
                return false;
            }
        }
    }

    // Verificação se a diagonal principal é igual à diagonal secundária
    if (x == y && x + y == N - 1) {
        for (int i = 0; i < N; i++) {
            if (board[i][i] != board[i][N - 1 - i]) {
                return false;
            }
        }
    }

    // Verificação dos subtabuleiros 2x2
    for (int i = 0; i < N - 1; i++) {
        for (int j = 0; j < N - 1; j++) {
            if (board[i][j] != 0 && board[i][j + 1] != 0 && board[i + 1][j] != 0 && board[i + 1][j + 1] != 0) {
                if (board[i][j] == color && board[i][j + 1] == board[i][j] && 
                    board[i + 1][j] == board[i][j] && board[i + 1][j + 1] == board[i][j]) {
                    return false;
                }
            }
        }
    }

    return true;
}

bool solveBoard(int board[N][N], int row, int col) {
    if (col == N) {
        row++;
        cont++;
        col = 0;
    }

    if (row == N) {
        cont++;
        return true;
    }

    for (int color = 1; color <= C; color++) {
        if (verificacoes(board, row, col, color)) {
            board[row][col] = color;

            if (solveBoard(board, row, col + 1)) {
                return true;
            }

            board[row][col] = 0;
        }
    }

    return false;
}

int main(void) {
    int board[N][N] = {0}; // Inicializa o tabuleiro com zeros
    if (solveBoard(board, 0, 0)) {
        printf("Tabuleiro colorido:\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                printf("%d ", board[i][j]);
            }
            printf("\n");
        }
        printf("Número de soluções: %d\n", cont);
    } else {
        printf("Não há solução.\n");
    }
    return 0;
}
