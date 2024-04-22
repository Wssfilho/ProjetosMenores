// Incluindo as bibliotecas necessárias
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Definindo a estrutura Intervalo
typedef struct {
    char cor[50]; // Nome da cor
    int inicio;   // Início do intervalo
    int fim;      // Fim do intervalo
} Intervalo;

// Função para ordenar os intervalos em ordem crescente de tempo de início
void ordenarIntervalos(Intervalo* intervalos, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            // Se o início do intervalo atual é maior que o início do próximo intervalo, troca os intervalos
            if (intervalos[i].inicio > intervalos[j].inicio) {
                Intervalo temp = intervalos[i];
                intervalos[i] = intervalos[j];
                intervalos[j] = temp;
            }
        }
    }
}

// Função para atribuir cores aos intervalos
void colorirIntervalos(Intervalo* intervalos, char** cores, int n, int num_cores) {
    // Ordena os intervalos
    ordenarIntervalos(intervalos, n);

    // Atribui cores aos intervalos
    for (int i = 0; i < n; i++) {
        // Atribui a cor atual ao intervalo
        strcpy(intervalos[i].cor, cores[i % num_cores]);
        for (int j = 0; j < i; j++) {
            // Se a cor atual já foi atribuída a um intervalo que se sobrepõe ao intervalo atual, atribui a próxima cor
            if (strcmp(intervalos[j].cor, intervalos[i].cor) == 0 && intervalos[j].fim > intervalos[i].inicio) {
                strcpy(intervalos[i].cor, cores[(i+1) % num_cores]);
            }
        }
    }
}

// Função principal
int main() {
    // Define o número de intervalos e cria um array de intervalos
    int n = 4;
    Intervalo intervalos[] = {{"", 1, 6}, {"", 3, 5}, {"",7, 9}, {"", 5, 8}};

    // Define o número de cores e cria um array de cores
    char* cores[] = {"azul", "verde", "vermelho", "amarelo", "laranja", "roxo"};
    int num_cores = sizeof(cores) / sizeof(char*);

    // Chama a função para colorir os intervalos
    colorirIntervalos(intervalos, cores, n, num_cores);

    // Imprime os intervalos e suas cores
    for (int i = 0; i < n; i++) {
        printf("Intervalo %d: cor = %s, inicio = %d, fim = %d\n", i, intervalos[i].cor, intervalos[i].inicio, intervalos[i].fim);
    }

    return 0;
}
