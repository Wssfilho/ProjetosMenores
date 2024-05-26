// quickSort2.java
// demonstra o quick sort com particionamento pela mediana de três
// para executar este programa: C>java QuickSort2App
////////////////////////////////////////////////////////////////
class OrdenacaoArray {
   private long[] array; // referência para o array array
   private int numElementos; // número de itens de dados
   private int comparacoes; // contador de comparações
   private int trocas; // contador de trocas

   //--------------------------------------------------------------
   public OrdenacaoArray(int max) { // construtor
       array = new long[max]; // cria o array
       numElementos = 0; // ainda sem itens
       comparacoes = 0; // inicializa o contador de comparações
       trocas = 0; // inicializa o contador de trocas
   }

   //--------------------------------------------------------------
   public void inserir(long valor) { // insere elemento no array
       array[numElementos] = valor; // insere
       numElementos++; // incrementa tamanho
   }

   //--------------------------------------------------------------
   public void exibir() { // exibe conteúdo do array
       System.out.print("A=");
       for (int j = 0; j < numElementos; j++) // para cada elemento,
           System.out.print(array[j] + " "); // exibe
       System.out.println("");
   }

   //--------------------------------------------------------------
   public void quickSort() {
       recQuickSort(0, numElementos - 1);
   }

   //--------------------------------------------------------------
   public void recQuickSort(int esquerda, int direita) {
       int tamanho = direita - esquerda + 1;
       if (tamanho <= 3) // ordenação manual se pequeno
           ordenacaoManual(esquerda, direita);
       else { // quicksort se grande
           long mediana = medianaDe3(esquerda, direita);
           int particao = particionar(esquerda, direita, mediana);
           recQuickSort(esquerda, particao - 1);
           recQuickSort(particao + 1, direita);
       }
   } // fim recQuickSort()

   //--------------------------------------------------------------
   public long medianaDe3(int esquerda, int direita) {
       int centro = (esquerda + direita) / 2;
       // ordena esquerda e centro
       if (array[esquerda] > array[centro]) {
           trocar(esquerda, centro);
           comparacoes++;
       }
       // ordena esquerda e direita
       if (array[esquerda] > array[direita]) {
           trocar(esquerda, direita);
           comparacoes++;
       }
       // ordena centro e direita
       if (array[centro] > array[direita]) {
           trocar(centro, direita);
           comparacoes++;
       }

       trocar(centro, direita - 1); // coloca pivo à direita
       trocas++;
       return array[direita - 1]; // retorna valor da mediana
   } // fim medianaDe3()

   //--------------------------------------------------------------
   public void trocar(int dex1, int dex2) { // troca dois elementos
       long temp = array[dex1]; // A para temp
       array[dex1] = array[dex2]; // B para A
       array[dex2] = temp; // temp para B
       trocas++;
   } // fim trocar()

   //--------------------------------------------------------------
   public int particionar(int esquerda, int direita, long pivo) {
       int ponteiroEsquerda = esquerda; // à direita do primeiro elem
       int ponteiroDireita = direita - 1; // à esquerda do pivo

       while (true) {
           while (array[++ponteiroEsquerda] < pivo) { // encontra maior
               comparacoes++;
           }
           while (array[--ponteiroDireita] > pivo) { // encontra menor
               comparacoes++;
           }
           if (ponteiroEsquerda >= ponteiroDireita) { // se ponteiros cruzarem,
               comparacoes++;
               break; // particionamento concluído
           } else { // não cruzaram, então
               trocar(ponteiroEsquerda, ponteiroDireita); // troca elementos
           }
       } // fim while(true)
       trocar(ponteiroEsquerda, direita - 1); // restaura pivo
       return ponteiroEsquerda; // retorna localização do pivo
   } // fim particionar()

   //--------------------------------------------------------------
   public void ordenacaoManual(int esquerda, int direita) {
       int tamanho = direita - esquerda + 1;
       if (tamanho <= 1)
           return; // ordenação não necessária
       if (tamanho == 2) { // ordena esquerda e direita
           if (array[esquerda] > array[direita]) {
               trocar(esquerda, direita);
               comparacoes++;
           }
           return;
       } else { // tamanho é 3
           // ordena esquerda, centro e direita
           if (array[esquerda] > array[direita - 1]) {
               trocar(esquerda, direita - 1); // esquerda, centro
               comparacoes++;
           }
           if (array[esquerda] > array[direita]) {
               trocar(esquerda, direita); // esquerda, direita
               comparacoes++;
           }
           if (array[direita - 1] > array[direita]) {
               trocar(direita - 1, direita); // centro, direita
               comparacoes++;
           }
       }
   } // fim ordenacaoManual()

   //--------------------------------------------------------------
   // Métodos para obter o número de comparações e trocas
   public int getComparacoes() {
       return comparacoes;
   }

   public int getTrocas() {
       return trocas;
   }
} // fim da classe OrdenacaoArray

////////////////////////////////////////////////////////////////
class QuickSort2App {
   public static void main(String[] args) {
       int tamanhoMax = 16; // tamanho do array
       OrdenacaoArray arr; // referência para o array
       arr = new OrdenacaoArray(tamanhoMax); // cria o array

       // Inserir os valores fornecidos manualmente no array
       long[] valores = {69, 0, 70, 6, 38, 38, 24, 56, 44, 26, 73, 77, 30, 45, 97, 65};
       for (long valor : valores) {
           arr.inserir(valor);
       }

       arr.exibir(); // exibe itens
       arr.quickSort(); // ordena com quicksort
       arr.exibir(); // exibe novamente

       // Exibe o número de comparações e trocas
       System.out.println("Número de comparações: " + arr.getComparacoes());
       System.out.println("Número de trocas: " + arr.getTrocas());
   } // fim main()
} // fim da classe QuickSort2App
////////////////////////////////////////////////////////////////
