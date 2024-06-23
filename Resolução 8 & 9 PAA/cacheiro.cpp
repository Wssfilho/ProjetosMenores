#include <iostream>
#include <vector>
#include <limits.h>

#define NUM_CIDADES 4
#define MAX_CUSTO 10

using namespace std;

int grafo[][NUM_CIDADES] = {
    {0, 1, 2, 5},
    {1, 0, 6, 4},
    {2, 6, 0, 4},
    {5, 4, 4, 0}
};

int dp[1 << NUM_CIDADES][NUM_CIDADES];  // Memoização para programação dinâmica

// Função recursiva para encontrar o caminho mínimo
int tsp(int mask, int pos) {
    // Caso base: todas as cidades foram visitadas, retornar ao ponto de partida
    if (mask == (1 << NUM_CIDADES) - 1) {
        return grafo[pos][0]; // Custo de retornar à cidade inicial
    }

    // Verificar se o resultado já foi calculado
    if (dp[mask][pos] != -1) {
        return dp[mask][pos];
    }

    int ans = INT_MAX;

    // Tentar visitar todas as cidades ainda não visitadas
    for (int cidade = 0; cidade < NUM_CIDADES; cidade++) {
        if ((mask & (1 << cidade)) == 0) { // Se a cidade ainda não foi visitada
            int novoCusto = grafo[pos][cidade] + tsp(mask | (1 << cidade), cidade);
            ans = min(ans, novoCusto);
        }
    }

    return dp[mask][pos] = ans;
}

int main() {
    // Inicializar a tabela de memoização com -1
    for (int i = 0; i < (1 << NUM_CIDADES); i++) {
        for (int j = 0; j < NUM_CIDADES; j++) {
            dp[i][j] = -1;
        }
    }

    int custoMinimo = tsp(0, 0); // Começar da cidade 0 (máscara 1)

    if (custoMinimo <= MAX_CUSTO) {
        cout << "Custo mínimo do caminho: " << custoMinimo << endl;
    } else {
        cout << "Não há caminho com custo menor ou igual a " << MAX_CUSTO << endl;
    }

    return 0;
}
