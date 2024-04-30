#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char* cor;
    int inicio;
    int fim;
} Intervalo;

void ordenarIntervalosPorTamanho(Intervalo* intervalos, int n) {
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            int duracaoI = intervalos[i].fim - intervalos[i].inicio;
            int duracaoJ = intervalos[j].fim - intervalos[j].inicio;
            if (duracaoI > duracaoJ) {
                Intervalo temp = intervalos[i];
                intervalos[i] = intervalos[j];
                intervalos[j] = temp;
            }
        }
    }
}

void colorirIntervalos(Intervalo* intervalos, char** cores, int n, int num_cores) {
    ordenarIntervalosPorTamanho(intervalos, n);
    int tempo_maximo = 0;
    for (int i = 0; i < n; i++) {
        if (intervalos[i].fim > tempo_maximo) {
            tempo_maximo = intervalos[i].fim;
        }
    }
    int usos_de_cor[tempo_maximo + 1][num_cores];
    memset(usos_de_cor, 0, sizeof(usos_de_cor));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < num_cores; j++) {
            int pode_usar = 1;
            for (int t = intervalos[i].inicio; t < intervalos[i].fim; t++) {
                if (usos_de_cor[t][j]) {
                    pode_usar = 0;
                    break;
                }
            }
            if (pode_usar) {
                intervalos[i].cor = cores[j];
                for (int t = intervalos[i].inicio; t < intervalos[i].fim; t++) {
                    usos_de_cor[t][j] = 1;
                }
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
