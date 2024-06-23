#include <iostream>
#include <vector>

using namespace std;

const int N = 6; // Tamanho do tabuleiro
const int K = 5; // Número de cores

vector<vector<int>> board(N, vector<int>(N, -1)); // Tabuleiro inicializado com -1 (vazio)
int solutions = 0; // Contador de soluções

// Função para verificar se a cor 'color' é válida na posição (row, col)
bool isValid(int row, int col, int color) {
    // Verificar adjacências horizontais e verticais
    if (row > 0 && board[row - 1][col] == color) return false;
    if (col > 0 && board[row][col - 1] == color) return false;

    // Verificar subtabuleiros 2x2
    if (row > 0 && col > 0 && board[row - 1][col - 1] == color && board[row - 1][col] == color && board[row][col - 1] == color) 
        return false;

    // Verificar diagonais (se aplicável)
    if (row == col && row > 0 && board[row - 1][col - 1] == color) return false;
    if (row + col == N - 1 && row > 0 && board[row - 1][col + 1] == color) return false;

    return true;
}

// Função recursiva para explorar as possibilidades de coloração
void backtrack(int row, int col) {
    // Caso base: todas as posições foram preenchidas
    if (row == N - 1 && col == N) {
        solutions++;
        return;
    }

    // Próxima posição a ser preenchida
    int nextRow = (col == N - 1) ? row + 1 : row;
    int nextCol = (col == N - 1) ? 0 : col + 1;

    // Iterar sobre as cores possíveis
    for (int color = 0; color < K; color++) {
        if (isValid(row, col, color)) {
            board[row][col] = color;
            backtrack(nextRow, nextCol);
            board[row][col] = -1; // Desfazer a escolha (backtracking)
        }
    }
}

int main() {
    backtrack(0, 0); // Começar a busca a partir da primeira posição
    cout << "Número de soluções: " << solutions << endl;
    return 0;
}
