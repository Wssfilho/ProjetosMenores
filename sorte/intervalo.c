#include <stdio.h>
#include <stdlib.h>

typedef struct {
    char* cor;
    int inicio;
    int fim;
} Intervalo;

void verificarSobreposicao(Intervalo* intervalos, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (intervalos[i].fim > intervalos[j].inicio && intervalos[i].inicio <= intervalos[j].inicio) {
                printf("%s primeira cor sobrepõe %s segunda cor nos intervalos %d a %d\n", intervalos[i].cor, intervalos[j].cor, intervalos[i].inicio, intervalos[i].fim);
            }
        }
    }
}

int main() {
    Intervalo intervalos[] = {{"azul", 1, 6}, {"verde", 3, 5}};
    int tamanho = sizeof(intervalos) / sizeof(Intervalo);

    verificarSobreposicao(intervalos, tamanho);

    return 0;
}
