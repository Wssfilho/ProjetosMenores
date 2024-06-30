#include <stdio.h>
#include <limits.h>

#define MAX_VERTICES 100

int melhor_lucro = 0;
int melhor_caminho[MAX_VERTICES];
int melhor_custo = INT_MAX;

void menor_custo(int C[MAX_VERTICES][MAX_VERTICES], int num_vertices, int valores[], int vertices[], int orcamento) {
    int caminhos[MAX_VERTICES][MAX_VERTICES];
    int custos[MAX_VERTICES];
    int lucros[MAX_VERTICES];
    
    for (int i = 1; i < num_vertices; i++) {
        printf("\n");
        int vertice_inicial = vertices[i];
        int caminho_atual[MAX_VERTICES] = { vertice_inicial };
        int custo_atual = C[vertices[0]][vertice_inicial];
        int lucro_atual = valores[i];

        int num_caminhos = 1;
        int tamanho_caminho_atual = 1;

        while (num_caminhos > 0) {
            int novo_caminho[MAX_VERTICES][MAX_VERTICES];
            int novo_custo[MAX_VERTICES];
            int novo_lucro[MAX_VERTICES];
            int novo_num_caminhos = 0;

            for (int j = 0; j < num_caminhos; j++) {
                int custo_total = custo_atual + C[vertices[0]][caminho_atual[0]];
                if (custo_total <= orcamento) {
                    int lucro = valores[0];
                    for (int k = 0; k < tamanho_caminho_atual; k++) {
                        lucro += valores[vertices[caminho_atual[k]]];
                    }
                    if (lucro > melhor_lucro || (lucro == melhor_lucro && custo_total < melhor_custo)) {
                        melhor_lucro = lucro;
                        for (int k = 0; k < tamanho_caminho_atual; k++) {
                            melhor_caminho[k] = caminho_atual[k];
                        }
                        melhor_custo = custo_total;
                    }
                }

                for (int k = 1; k < num_vertices; k++) {
                    int vertice = vertices[k];
                    int encontrado = 0;
                    for (int l = 0; l < tamanho_caminho_atual; l++) {
                        if (caminho_atual[l] == vertice) {
                            encontrado = 1;
                            break;
                        }
                    }
                    if (!encontrado) {
                        int novo_custo_total = C[vertice][caminho_atual[0]] + custo_atual;
                        if (novo_custo_total <= orcamento) {
                            for (int l = 0; l < tamanho_caminho_atual; l++) {
                                novo_caminho[novo_num_caminhos][l + 1] = caminho_atual[l];
                            }
                            novo_caminho[novo_num_caminhos][0] = vertice;
                            novo_custo[novo_num_caminhos] = novo_custo_total;
                            novo_lucro[novo_num_caminhos] = lucro_atual + valores[vertices[vertice]];
                            novo_num_caminhos++;
                        }
                    }
                }
            }

            num_caminhos = novo_num_caminhos;
            for (int j = 0; j < num_caminhos; j++) {
                for (int k = 0; k < MAX_VERTICES; k++) {
                    caminho_atual[k] = novo_caminho[j][k];
                }
                custo_atual = novo_custo[j];
                lucro_atual = novo_lucro[j];
            }
        }
        printf("\n");
    }
}

int main() {
    int C[MAX_VERTICES][MAX_VERTICES] = {
        {0, 1, 2, 5},
        {1, 0, 6, 4},
        {2, 6, 0, 4},
        {5, 4, 4, 0}
    };
    int valores[] = {10, 20, 30, 40};
    int vertices[] = {0, 1, 2, 3};
    int num_vertices = 4;
    int orcamento;

    printf("Digite o valor do orçamento: ");
    scanf("%d", &orcamento);

    menor_custo(C, num_vertices, valores, vertices, orcamento);

    printf("Máximo lucro possível dentro do orçamento: %d\n", melhor_lucro);
    printf("Melhor caminho: ");
    printf("a -> ");
    for (int i = 0; i < num_vertices; i++) {
        if (melhor_caminho[i] != 0) {
            printf("%c -> ", melhor_caminho[i] + 'a');
        }
    }
    printf("a\n");
    printf("Custo: %d\n", melhor_custo);

    return 0;
}
