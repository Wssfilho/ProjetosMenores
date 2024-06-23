#include <iostream>
#include <vector>
#include <set>
#include <string>

using namespace std;

bool subtabuleiroValido(const vector<vector<string>>& tabuleiro, int linha, int coluna) {
    set<string> cores;
    for (int i = linha; i < linha + 2; ++i) {
        for (int j = coluna; j < coluna + 2; ++j) {
            cores.insert(tabuleiro[i][j]);
        }
    }
    return cores.size() == 4;
}

bool posicaoValida(const vector<vector<string>>& tabuleiro, int linha, int coluna, const string& cor) {
    if (!(0 <= linha && linha < tabuleiro.size() && 0 <= coluna && coluna < tabuleiro[0].size() && tabuleiro[linha][coluna] == "")) {
        return false;
    }

    for (const auto& direcao : vector<pair<int, int>>{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
        int l = linha + direcao.first;
        int c = coluna + direcao.second;
        if (0 <= l && l < tabuleiro.size() && 0 <= c && c < tabuleiro[0].size() && tabuleiro[l][c] == cor) {
            return false;
        }
    }

    if (linha < tabuleiro.size() - 1 && coluna < tabuleiro[0].size() - 1) {
        if (!subtabuleiroValido(tabuleiro, linha, coluna)) {
            return false;
        }
    }

    return true;
}

// Variável global para contar as soluções
int numSolucoes = 0;

bool resolver(vector<vector<string>>& tabuleiro, const vector<string>& cores, int linha = 0, int coluna = 0) {
    if (linha == tabuleiro.size()) {
        numSolucoes++;  // Incrementa o contador de soluções
        return true;  // Encontrou uma solução
    }

    int proximaLinha = (coluna == tabuleiro[0].size() - 1) ? linha + 1 : linha;
    int proximaColuna = (coluna + 1) % tabuleiro[0].size();

    if (linha == coluna) {
        for (const string& cor : cores) {
            if (posicaoValida(tabuleiro, linha, coluna, cor)) {
                tabuleiro[linha][coluna] = cor;
                tabuleiro[linha][tabuleiro[0].size() - coluna - 1] = cor;
                if (resolver(tabuleiro, cores, proximaLinha, proximaColuna)) {
                    return true; // Encontrou uma solução, não precisa continuar
                }
                tabuleiro[linha][coluna] = "";
                tabuleiro[linha][tabuleiro[0].size() - coluna - 1] = "";
            }
        }
        return false; 
    } else if (linha == tabuleiro.size() - coluna - 1) {
        return resolver(tabuleiro, cores, proximaLinha, proximaColuna);
    }

    for (const string& cor : cores) {
        if (posicaoValida(tabuleiro, linha, coluna, cor)) {
            tabuleiro[linha][coluna] = cor;
            if (resolver(tabuleiro, cores, proximaLinha, proximaColuna)) {
                return true; // Encontrou uma solução, não precisa continuar
            }
            tabuleiro[linha][coluna] = "";
        }
    }

    return false; // Nenhuma cor funcionou nesta posição
}

void imprimirTabuleiro(const vector<vector<string>>& tabuleiro) {
    for (const auto& linha : tabuleiro) {
        for (const string& celula : linha) {
            cout << celula << " ";
        }
        cout << endl;
    }
}

int main() {
    vector<string> cores = {"1", "2", "3", "4", "5"};
    int tamanho = 6;
    vector<vector<string>> tabuleiro(tamanho, vector<string>(tamanho, ""));

    if (resolver(tabuleiro, cores)) {
        imprimirTabuleiro(tabuleiro);
    } else {
        cout << "A quantidade de soluções é: " << numSolucoes << endl; 
    }

    return 0;
}
