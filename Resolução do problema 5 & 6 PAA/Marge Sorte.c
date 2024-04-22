#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//definicao de estrutura
typedef struct {
    char primerio[100];
    int segundo;
} Sorte;
//comeco da funcao marge
void merge(Sorte vetor[], int p, int q, int r) {
    int n1 = q - p + 1;
    int n2 = r - q;
    Sorte esquerdo[n1], direito[n2];

    for (int i = 0; i < n1; i++)
        esquerdo[i] = vetor[p + i];
    for (int j = 0; j < n2; j++)
        direito[j] = vetor[q + j + 1];

    int i = 0, j = 0, k = p;

    for (; k <= r; k++) {
        if (i < n1 && (j >= n2 || strcmp(esquerdo[i].primerio, direito[j].primerio) <= 0)) {
            vetor[k] = esquerdo[i];
            if (j < n2 && strcmp(esquerdo[i].primerio, direito[j].primerio) == 0) {
                vetor[k].segundo += direito[j].segundo;
                direito[j].segundo = 0;
            }
            i++;
        } else if (j < n2) {
            vetor[k] = direito[j];
            j++;
        }
    }
}

void mergeSort(Sorte vetor[], int p, int r) {
    if (p < r) {
        int q = (p + r) / 2;
        mergeSort(vetor, p, q);
        mergeSort(vetor, q + 1, r);
        merge(vetor, p, q, r);
    }
}

int main() {
    int n;
    scanf("%d\n", &n);
    Sorte vetor[n];

    for (int i = 0; i < n; i++) {
        scanf("%99s", vetor[i].primerio);
        vetor[i].segundo = 1;
    }

    mergeSort(vetor, 0, n - 1);

    for (int i = 0; i < n; i++)
        if (vetor[i].segundo > 0)
            printf("(%s, %d) ", vetor[i].primerio, vetor[i].segundo);

    return 0;
}
