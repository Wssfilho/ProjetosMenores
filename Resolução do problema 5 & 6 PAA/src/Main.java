import java.util.*; // Importando todas as classes do pacote java.util

class Sorte { // Definindo uma classe chamada Sorte
    String primerio; // Declarando uma variável String chamada primerio
    int segundo; // Declarando uma variável inteira chamada segundo

    Sorte(String primerio, int segundo) { // Construtor para a classe Sorte
        this.primerio = primerio; // Atribuindo o valor de primerio à variável de instância
        this.segundo = segundo; // Atribuindo o valor de segundo à variável de instância
    }
}

public class Main { // Definindo a classe principal
    static void merge(Sorte[] vetor, int p, int q, int r) { // Definindo a função de mesclagem para o merge sort
        int n1 = q - p + 1; // Calculando o número de elementos na primeira metade (esquerda) do array
        int n2 = r - q; // Calculando o número de elementos na segunda metade (direita) do array
        Sorte[] esquerdo = new Sorte[n1]; // Criando um novo array para a metade esquerda
        Sorte[] direito = new Sorte[n2]; // Criando um novo array para a metade direita

        for (int i = 0; i < n1; i++) // Copiando os elementos para o array esquerdo
            esquerdo[i] = vetor[p + i];
        for (int j = 0; j < n2; j++) // Copiando os elementos para o array direito
            direito[j] = vetor[q + j + 1];

        int i = 0, j = 0, k = p; // Inicializando contadores para o processo de mesclagem

        for (; k <= r; k++) { // Mesclando as duas metades de volta no array original
            if (i < n1 && (j >= n2 || esquerdo[i].primerio.compareTo(direito[j].primerio) <= 0)) {
                vetor[k] = esquerdo[i]; // Se o elemento no array esquerdo for menor, adicione-o ao array ordenado
                if (j < n2 && esquerdo[i].primerio.equals(direito[j].primerio)) {
                    vetor[k].segundo += direito[j].segundo; // Se os elementos forem iguais, adicione as contagens
                    direito[j].segundo = 0; // Redefina a contagem no array direito
                }
                i++; // Incrementa o contador para o array esquerdo
            } else if (j < n2) {
                vetor[k] = direito[j]; // Se o elemento no array direito for menor, adicione-o ao array ordenado
                j++; // Incrementa o contador para o array direito
            }
        }
    }
    //this function was used to sort the array
    static void mergeSort(Sorte[] vetor, int p, int r) { // Definindo a função merge sort
        if (p < r) { // Se o array tiver mais de um elemento
            int q = (p + r) / 2; // Encontre o ponto médio do array
            mergeSort(vetor, p, q); // Ordene recursivamente a metade esquerda
            mergeSort(vetor, q + 1, r); // Ordene recursivamente a metade direita
            merge(vetor, p, q, r); // Mescla as duas metades
        }
    }

    public static void main(String[] args) { // A função principal
        Scanner scanner = new Scanner(System.in); // Criando um objeto Scanner para entrada
        int n = scanner.nextInt(); // Lendo o número de elementos
        scanner.nextLine(); // Consumindo a nova linha

        Sorte[] vetor = new Sorte[n]; // Criando um array de objetos Sorte

        for (int i = 0; i < n; i++) { // Lendo os elementos
            String primerio = scanner.nextLine(); // Lendo uma linha de entrada
            vetor[i] = new Sorte(primerio, 1); // Criando um novo objeto Sorte e adicionando-o ao array
        }

        mergeSort(vetor, 0, n - 1); // Ordenando o array usando merge sort

        for (Sorte s : vetor) // Imprimindo o array ordenado
            if (s.segundo > 0) // Se a contagem for maior que zero
                System.out.printf("(%s, %d) ", s.primerio, s.segundo); // Imprime o elemento e sua contagem
    }
}
