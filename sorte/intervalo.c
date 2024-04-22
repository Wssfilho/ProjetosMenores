#include <stdio.h>
#include <stdlib.h>

typedef struct 
{
    int comeco;
    int final;
}Intervalo;

void ordenar(Intervalo *inter, const int n)
{
    int i, j;
    for(i = 0; i < n; i++)
    {
        for(j = i + 1; j < n; j++)
        {
            if(inter[i].comeco > inter[j].comeco)
            {
                Intervalo temp = inter[i];
                inter[i] = inter[j];
                inter[j] = temp;
            }
        }
    }
}
void colorirIntervalos(Intervalo* inter, int * cores, const int n) {
    ordenar(inter, n);

    for (int i = 0; i < n; i++) {
        int cor = 1;
        for (int j = 0; j < i; j++) {
            if (inter[j].final <= inter[i].final) {
                if (cores[j] == cor) {
                    cor++;
                    j = -1;
                }
            }
        }
        cores[i] = cor;
    }
}

int main()
{
    int n = 4;
    Intervalo intervalos[] = {{1, 4}, {2, 3}, {5, 8}, {7, 9}};
    int * cores = (int*)malloc(n * sizeof(int));

    colorirIntervalos(intervalos, cores, n);

    for (int i = 0; i < n; i++) {
        printf("Intervalo %d: inicio = %d, fim = %d, cor = %d\n", i, intervalos[i].comeco, intervalos[i].final, cores[i]);
    }

    free(cores);
    return 0;

}