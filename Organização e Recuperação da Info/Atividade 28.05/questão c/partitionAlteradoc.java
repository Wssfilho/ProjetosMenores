// Classe que gerencia um array de longos e oferece métodos para inserir elementos, exibir o array e particionar o array.
class ArrayParticao {
    private long[] oArray; // Array para armazenar os elementos
    private int numElementos; // Número de elementos no array
    private int comparacoes; // Contador de comparações
    private int trocas; // Contador de trocas

    // Construtor da classe
    public ArrayParticao(int max) {
        oArray = new long[max]; // Cria o array com o tamanho máximo fornecido
        numElementos = 0; // Inicializa o número de elementos como 0
        comparacoes = 0; // Inicializa o contador de comparações
        trocas = 0; // Inicializa o contador de trocas
    }

    // Método para inserir um elemento no array
    public void inserir(long valor) {
        oArray[numElementos] = valor; // Insere o valor no próximo índice disponível
        numElementos++; // Incrementa o número de elementos
    }

    // Método para obter o número de elementos no array
    public int tamanho() {
        return numElementos;
    }

    // Método para exibir os elementos do array
    public void exibir() {
        System.out.print("A=");
        for (int j = 0; j < numElementos; j++) // Para cada elemento,
            System.out.print(oArray[j] + " "); // imprime o elemento
        System.out.println("");
    }

    // Método para particionar o array usando o último elemento como pivô
    public int particionar(int esquerda, int direita) {
        long pivo = oArray[direita]; // Pivô é o elemento mais à direita
        int ponteiroEsquerda = esquerda - 1; // À direita do primeiro elemento
        int ponteiroDireita = direita; // À esquerda do pivô

        while (true) {
            // Encontra o item maior que o pivô
            while (oArray[++ponteiroEsquerda] < pivo) {
                comparacoes++;
            }

            // Encontra o item menor que o pivô
            while (ponteiroDireita > 0 && oArray[--ponteiroDireita] > pivo) {
                comparacoes++;
            }

            // Se os ponteiros cruzarem, a partição está concluída
            if (ponteiroEsquerda >= ponteiroDireita) {
                comparacoes++;
                break;
            } else { // Se não cruzou, troca os elementos
                trocar(ponteiroEsquerda, ponteiroDireita);
                trocas++;
            }
        }
        // Restaura o pivô para sua posição correta
        trocar(ponteiroEsquerda, direita);
        trocas++;
        return ponteiroEsquerda; // Retorna a posição do pivô
    }

    // Método para trocar dois elementos no array
    public void trocar(int indice1, int indice2) {
        long temp = oArray[indice1]; // Armazena o primeiro elemento em temp
        oArray[indice1] = oArray[indice2]; // Move o segundo elemento para a posição do primeiro
        oArray[indice2] = temp; // Move temp (primeiro elemento) para a posição do segundo
    }

    // Método para obter o número de comparações
    public int getComparacoes() {
        return comparacoes;
    }

    // Método para obter o número de trocas
    public int getTrocas() {
        return trocas;
    }
}

// Classe principal que usa a classe ArrayParticao
class PartitionApp {
    public static void main(String[] args) {
        int tamanhoMax = 16; // Tamanho máximo do array
        ArrayParticao arr = new ArrayParticao(tamanhoMax); // Cria o array

        // Inserir os valores fornecidos manualmente no array
        long[] valores = {69, 0, 70, 6, 38, 38, 24, 56, 44, 26, 73, 77, 30, 45, 97, 65};
        for (long valor : valores) {
            arr.inserir(valor);
        }
        arr.exibir(); // Exibe o array não ordenado

        int tamanho = arr.tamanho(); // Obtém o número de elementos no array
        // Particiona o array usando o último elemento como pivô
        int indiceParticao = arr.particionar(0, tamanho - 1);

        // Exibe a posição do pivô após a partição
        System.out.println("Partição está no índice " + indiceParticao);
        arr.exibir(); // Exibe o array após a partição

        // Exibe o número de comparações e trocas
        System.out.println("Número de comparações: " + arr.getComparacoes());
        System.out.println("Número de trocas: " + arr.getTrocas());
    }
}
