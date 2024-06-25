#include <stdio.h>
#include <stdbool.h>

#define N 5 // Tamanho do tabuleiro (7x7)
int cont = 0;
int C = 5;

bool verificacoes(int board[N][N], int x, int y, int color) {
    // Verifica se a cor já está presente na mesma linha ou coluna
    for (int i = 0; i < N; i++) {
        if (board[x][i] == color || board[i][y] == color) {
            return false;
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



    // Verifica células adjacentes
    int directions[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Cima, Baixo, Esquerda, Direita
    for (int i = 0; i < 4; i++) {
        int adjX = x + directions[i][0];
        int adjY = y + directions[i][1];
        if (0 <= adjX && adjX < N && 0 <= adjY && adjY < N && board[adjX][adjY] == color) {
            return false;
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
