// Incluindo as bibliotecas necessárias
#include <stdio.h>
#include <stdlib.h>

// Definindo a estrutura Intervalo
typedef struct
{
    char *cor;  // Campo para armazenar o nome da cor
    int inicio; // Campo para armazenar o início do intervalo
    int fim;    // Campo para armazenar o fim do intervalo
} Intervalo;

// Função de comparação para qsort
int comparar(const void *a, const void *b)
{
    Intervalo *intervalo1 = (Intervalo *)a;
    Intervalo *intervalo2 = (Intervalo *)b;
    if (intervalo1->inicio == intervalo2->inicio)
    {
        return intervalo1->fim - intervalo2 ->fim;
    }
    else{
        return intervalo1->inicio - intervalo2->inicio;
        
    }
}

// Função para verificar se um intervalo se sobrepõe a qualquer intervalo anterior com a mesma cor
int sobreposicao(Intervalo *intervalos, int atual)
{
    for (int i = 0; i < atual; i++)
    { // Loop para cada intervalo anterior
        // Se a cor atual já foi atribuída a um intervalo que se sobrepõe ao intervalo atual, retorna 1
        if (intervalos[i].cor == intervalos[atual].cor && intervalos[i].fim > intervalos[atual].inicio)
        {
            return 1;
        }
    }
    return 0; // Se não houver sobreposição, retorna 0
}

// Função para atribuir cores aos intervalos
void colorirIntervalos(Intervalo *intervalos, char **cores, int n, int num_cores)
{
    qsort(intervalos, n, sizeof(Intervalo), comparar); // Ordena os intervalos

    for (int i = 0; i < n; i++)
    { // Loop para cada intervalo
        for (int j = 0; j < num_cores; j++)
        {                                 // Loop para cada cor
            intervalos[i].cor = cores[j]; // Atribui a cor atual ao intervalo
            // Se não houver sobreposição, interrompe o loop
            if (!sobreposicao(intervalos, i))
            {
                break;
            }
        }
    }
}

// Função principal
int main(void)
{
    int n; // Variável para armazenar o número de intervalos
    printf("Digite o número de intervalos: ");
    scanf("%d", &n); // Lê o número de intervalos do usuário

    // Cria um array de intervalos
    Intervalo *intervalos = malloc(n * sizeof(Intervalo));
    // Define o número de cores e cria um array de cores
    char *cores[] = {"vermelho", "amarelo", "azul", "verde"};
    int num_cores = sizeof(cores) / sizeof(char *);

    // Lê os intervalos do usuário
    for (int i = 0; i < n; i++)
    {
        printf("Digite o início e o fim do intervalo %d: ", i + 1);
        scanf("%d %d", &intervalos[i].inicio, &intervalos[i].fim);
        intervalos[i].cor = ""; // Inicializa a cor como vazia
    }

    colorirIntervalos(intervalos, cores, n, num_cores); // Chama a função para colorir os intervalos

    // Imprime os intervalos e suas cores
    for (int i = 0; i < n; i++)
    {
        printf("Intervalo %d: cor = %s, inicio = %d, fim = %d\n", i, intervalos[i].cor, intervalos[i].inicio, intervalos[i].fim);
    }

    free(intervalos); // Libera a memória alocada para os intervalos
    return 0;         // Termina o programa
}
