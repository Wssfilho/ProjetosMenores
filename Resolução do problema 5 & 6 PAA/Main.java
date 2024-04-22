import java.util.*;

class Sorte {
    String primerio;
    int segundo;

    Sorte(String primerio, int segundo) {
        this.primerio = primerio;
        this.segundo = segundo;
    }
}

public class Main {
    static void merge(Sorte[] vetor, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        Sorte[] esquerdo = new Sorte[n1];
        Sorte[] direito = new Sorte[n2];

        for (int i = 0; i < n1; i++)
            esquerdo[i] = vetor[p + i];
        for (int j = 0; j < n2; j++)
            direito[j] = vetor[q + j + 1];

        int i = 0, j = 0;

        for (int k = p; k <= r; k++) {
            if (i < n1 && (j >= n2 || esquerdo[i].primerio.compareTo(direito[j].primerio) <= 0)) {
                vetor[k] = esquerdo[i];
                if (j < n2 && esquerdo[i].primerio.equals(direito[j].primerio)) {
                    vetor[k].segundo += direito[j].segundo;
                    j++;
                }
                i++;
            } else {
                vetor[k] = direito[j];
                j++;
            }
        }
    }

    static void mergeSort(Sorte[] vetor, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(vetor, p, q);
            mergeSort(vetor, q + 1, r);
            merge(vetor, p, q, r);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Sorte[] vetor = new Sorte[n];

        for (int i = 0; i < n; i++) {
            vetor[i] = new Sorte(scanner.nextLine(), 1);
        }

        for (Sorte s : vetor)
            System.out.printf("(%s, %d) ", s.primerio, s.segundo);

        mergeSort(vetor, 0, n - 1);

        System.out.println();

        for (Sorte s : vetor)
            System.out.printf("(%s, %d) ", s.primerio, s.segundo);
    }
}
