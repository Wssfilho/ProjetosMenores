// Incluindo as bibliotecas necessárias
#include <stdio.h>
#include <stdlib.h>

// Definindo a estrutura Intervalo
typedef struct {
    char* cor;   // Campo para armazenar o nome da cor
    int inicio;  // Campo para armazenar o início do intervalo
    int fim;     // Campo para armazenar o fim do intervalo
} Intervalo;

// Função para ordenar os intervalos em ordem crescente de tempo de início
void ordenarIntervalos(Intervalo* intervalos, int n) {
    for (int i = 0; i < n; i++) {  // Loop para cada intervalo
        for (int j = i + 1; j < n; j++) {  // Loop para cada intervalo não ordenado
            // Se o início do intervalo atual é maior que o início do próximo intervalo, troca os intervalos
            if (intervalos[i].inicio > intervalos[j].inicio) {
                Intervalo temp = intervalos[i];
                intervalos[i] = intervalos[j];
                intervalos[j] = temp;
            }
        }
    }
}

// Função para verificar se um intervalo se sobrepõe a qualquer intervalo anterior com a mesma cor
int sobreposicao(Intervalo * intervalos, int atual) {
    for (int i = 0; i < atual; i++) {  // Loop para cada intervalo anterior
        // Se a cor atual já foi atribuída a um intervalo que se sobrepõe ao intervalo atual, retorna 1
        if (intervalos[i].cor == intervalos[atual].cor && intervalos[i].fim > intervalos[atual].inicio) {
            return 1;
        }
    }
    return 0;  // Se não houver sobreposição, retorna 0
}

// Função para atribuir cores aos intervalos
void colorirIntervalos(Intervalo* intervalos, char** cores, int n, int num_cores) {
    ordenarIntervalos(intervalos, n);  // Ordena os intervalos

    for (int i = 0; i < n; i++) {  // Loop para cada intervalo
        for (int j = 0; j < num_cores; j++) {  // Loop para cada cor
            intervalos[i].cor = cores[j];  // Atribui a cor atual ao intervalo
            // Se não houver sobreposição, interrompe o loop
            if (!sobreposicao(intervalos, i)) {
                break;
            }
        }
    }
}

// Função principal
int main(void) {
    int n = 8;  // Define o número de intervalos
    // Cria um array de intervalos
    Intervalo intervalos[] = {{"", 0, 6}, {"", 1, 4}, {"", 3, 5}, {"", 3, 8}, {"", 4, 7}, {"",5, 9}, {"", 6, 10}, {"", 7, 11}};
    // Define o número de cores e cria um array de cores
    char* cores[] =  {"amarelo", "vermelho", "azul", "verde"};
    int num_cores = sizeof(cores) / sizeof(char*);

    colorirIntervalos(intervalos, cores, n, num_cores);  // Chama a função para colorir os intervalos

    // Imprime os intervalos e suas cores
    for (int i = 0; i < n; i++) {
        printf("Intervalo %d: cor = %s, inicio = %d, fim = %d\n", i, intervalos[i].cor, intervalos[i].inicio, intervalos[i].fim);
    }

    return 0;  // Termina o programa
}
