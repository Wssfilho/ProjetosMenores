#include <stdio.h>
#include <stdlib.h>

typedef struct {
    char* cor;
    int inicio;
    int fim;
} Intervalo;

int compararIntervalos(const void *a, const void *b) {
    Intervalo *intervaloA = (Intervalo *)a;
    Intervalo *intervaloB = (Intervalo *)b;
    int tamanhoA = intervaloA->fim - intervaloA->inicio;
    int tamanhoB = intervaloB->fim - intervaloB->inicio;

    if (tamanhoA < tamanhoB) return -1;
    if (tamanhoA > tamanhoB) return 1;

    if (intervaloA->inicio < intervaloB->inicio) return -1;
    if (intervaloA->inicio > intervaloB->inicio) return 1;

    return 0;
}

int sobreposicao(Intervalo * intervalos, int atual) {
    for (int i = 0; i < atual; i++) {
        if (intervalos[i].cor == intervalos[atual].cor && intervalos[i].fim > intervalos[atual].inicio) {
            return 1;
        }
    }
    return 0;
}

void colorirIntervalos(Intervalo* intervalos, char** cores, int n, int num_cores) {
    qsort(intervalos, n, sizeof(Intervalo), compararIntervalos);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < num_cores; j++) {
            intervalos[i].cor = cores[j];
            if (!sobreposicao(intervalos, i)) {
                break;
            }
        }
    }
}

int main(void) {
    int n = 8;
    Intervalo intervalos[] = {{"", 0, 6}, {"", 1, 4}, {"", 3, 5}, {"", 3, 8}, {"", 4, 7}, {"",5, 9}, {"", 6, 10}, {"", 7, 11}};
    char* cores[] =  {"vermelho", "amarelo", "azul", "verde"};
    int num_cores = sizeof(cores) / sizeof(char*);

    colorirIntervalos(intervalos, cores, n, num_cores);

    for (int i = 0; i < n; i++) {
        printf("Intervalo %d: cor = %s, inicio = %d, fim = %d\n", i, intervalos[i].cor, intervalos[i].inicio, intervalos[i].fim);
    }

    return 0;
}
