#include<stdio.h>  // Inclui a biblioteca padrão de entrada e saída

#define INF 9999  // Define um valor constante para infinito
#define N 4  // Define o número de cidades

int visitados = (1<<N) - 1;  // Calcula a máscara que representa todas as cidades visitadas(usando bit)
int max_cost = 90;  // Define o custo máximo permitido

int dp[1025][N];  // Declara uma matriz para memoização (programação dinâmica)
int dist[N][N] = {  // Declara e inicializa a matriz de distâncias entre as cidades
    {0, 29, 20, 21},
    {29, 0, 15, 17},
    {20, 15, 0, 28},
    {21, 17, 28, 0}
};

int min(int a, int b) {  // Função que retorna o menor valor entre a e b
    return (a < b) ? a : b;
}

int tsp(int mascara, int pos, int custo_atual) {  // Função que resolve o problema do caixeiro viajante
    if (custo_atual > max_cost) {  // Se o custo atual excede o custo máximo permitido
        return INF;  // Retorna infinito (caminho inválido)
    }

    if (mascara == visitados) {  // Se todas as cidades foram visitadas
        return dist[pos][0];  // Retorna o custo para voltar à cidade de origem
    }
    if (dp[mascara][pos] != -1) {  // Se o valor já foi calculado (memoização)
        return dp[mascara][pos];  // Retorna o valor armazenado
    }

    int retorno = INF;  // Inicializa a resposta como infinito
    for (int cidade = 0; cidade < N; cidade++) {  // Para cada cidade
        if ((mascara & (1 << cidade)) == 0) {  // Se a cidade ainda não foi visitada
            int novaResposta = dist[pos][cidade] + tsp(mascara | (1 << cidade), cidade, custo_atual + dist[pos][cidade]);  // Calcula o novo custo
            retorno = min(retorno, novaResposta);  // Atualiza a resposta com o menor custo encontrado
        }
    }

    return dp[mascara][pos] = retorno;  // Armazena a resposta e a retorna
}

int main(void) {
    for (int i = 0; i < (1 << N); i++) {  // Inicializa a matriz de memoização com -1
        for (int j = 0; j < N; j++) {
            dp[i][j] = -1;
        }
    }
    int resultado = tsp(1, 0, 0);  // Chama a função tsp com a máscara inicial, posição inicial e custo inicial
    if (resultado <= max_cost) {  // Se o resultado é menor ou igual ao custo máximo permitido
        printf("O custo mínimo é %d\n", resultado);  // Imprime o custo mínimo encontrado
    } else {  // Caso contrário
        printf("Não há solução com o custo máximo de %d\n", max_cost);  // Informa que não há solução dentro do custo máximo permitido
    }

    return 0;  // Retorna 0 indicando que o programa terminou corretamente
}
