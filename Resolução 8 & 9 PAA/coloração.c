#include <stdio.h>

#define TAM_TABULEIRO 6 // Tamanho do tabuleiro (6x6)
#define NUM_CORES 5      // Número de cores disponíveis

// Definição de cores (opcional)
enum Cor {
    VERMELHO, AZUL, VERDE, AMARELO, ROXO
};

// Tabuleiro para armazenar as cores das casas
int tabuleiro[TAM_TABULEIRO][TAM_TABULEIRO];

// Lista de cores disponíveis
int cores[NUM_CORES];

// Função para imprimir o tabuleiro
void imprimirTabuleiro(int tab[TAM_TABULEIRO][TAM_TABULEIRO]) {
    for (int i = 0; i < TAM_TABULEIRO; i++) {
        for (int j = 0; j < TAM_TABULEIRO; j++) {
            printf("%d ", tab[i][j]);
        }
        printf("\n");
    }
}

// Função para verificar se a cor é válida na posição
int is_valid(int cor, int linha, int coluna) {
    // Verifica se a cor já existe na mesma linha
    for (int j = 0; j < TAM_TABULEIRO; j++) {
        if (tabuleiro[linha][j] == cor) return 0;
    }

    // Verifica se a cor já existe na mesma coluna
    for (int i = 0; i < TAM_TABULEIRO; i++) {
        if (tabuleiro[i][coluna] == cor) return 0;
    }

    // Adicione aqui outras regras de validação conforme necessário

    // Se passar por todas as verificações, a cor é válida
    return 1;
}

// Função recursiva para resolver o problema
void solve(int posicaoAtual) {
    // Base da recursão (todas as casas preenchidas e regras válidas)
    if (posicaoAtual == TAM_TABULEIRO * TAM_TABULEIRO) {
        // Imprime o tabuleiro preenchido
        imprimirTabuleiro(tabuleiro);
        return;
    }

    int linha = posicaoAtual / TAM_TABULEIRO;
    int coluna = posicaoAtual % TAM_TABULEIRO;

    // Passo de recursão (tentar cada cor disponível)
    for (int cor = 0; cor < NUM_CORES; cor++) {
        // Verifica se a cor é válida na posição atual
        if (is_valid(cor, linha, coluna)) {
            // Coloca a cor na posição atual
            tabuleiro[linha][coluna] = cor;

            // Chama a função recursivamente com a nova configuração
            solve(posicaoAtual + 1);

            // Remove a cor da posição atual (backtrack)
            tabuleiro[linha][coluna] = -1; // Valor neutro para indicar casa vazia
        }
    }
}

int main() {
    // Inicializa o tabuleiro com casas vazias
    for (int i = 0; i < TAM_TABULEIRO; i++) {
        for (int j = 0; j < TAM_TABULEIRO; j++) {
            tabuleiro[i][j] = -1; // Valor neutro para indicar casa vazia
        }
    }

    // Inicializa a lista de cores disponíveis
    for (int i = 0; i < NUM_CORES; i++) {
        cores[i] = i; // Valor da enumeração da cor
    }

    // Chama a função solve para encontrar as soluções e imprimir os tabuleiros preenchidos
    solve(0);

    return 0;
}
