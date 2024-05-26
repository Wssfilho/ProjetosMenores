// quickSort1.java
// demonstra a versão simples do quick sort
// para executar este programa: C>java QuickSort1App
////////////////////////////////////////////////////////////////
class OrdenacaoArray {
   private long[] arranjo; // referência para o array arranjo
   private int numElementos; // número de itens de dados
   public int comparacoes; // Contador de comparações
   public int trocas; // Contador de trocas
   // --------------------------------------------------------------

   public OrdenacaoArray(int max) // construtor
   {
      arranjo = new long[max]; // cria o array
      numElementos = 0; // ainda sem itens
      comparacoes = 0; // Inicializa o contador de comparações
      trocas = 0; // Inicializa o contador de trocas
   }

   // --------------------------------------------------------------
   public void inserir(long valor) // insere elemento no array
   {
      arranjo[numElementos] = valor; // insere
      numElementos++; // incrementa tamanho
   }

   // --------------------------------------------------------------
   public void exibir() // exibe conteúdo do array
   {
      System.out.print("A=");
      for (int j = 0; j < numElementos; j++) // para cada elemento,
         System.out.print(arranjo[j] + " "); // exibe
      System.out.println("");
   }

   // --------------------------------------------------------------
   public void quickSort() {
      recQuickSort(0, numElementos - 1);
   }

   // --------------------------------------------------------------
   public void recQuickSort(int esquerda, int direita) {
      if (direita - esquerda <= 0) // se tamanho <= 1,
         return; // já está ordenado
      else // tamanho é 2 ou maior
      {
         long pivo = arranjo[direita]; // item mais à direita
                                       // particiona intervalo
         int particao = particionar(esquerda, direita, pivo);
         recQuickSort(esquerda, particao - 1); // ordena lado esquerdo
         recQuickSort(particao + 1, direita); // ordena lado direito
      }
   } // fim recQuickSort()
   // --------------------------------------------------------------

   public int particionar(int esquerda, int direita, long pivo) {
      int pontEsquerda = esquerda - 1; // esquerda (após ++)
      int pontDireita = direita; // direita-1 (após --)
      while (true) { // encontra item maior
         while (arranjo[++pontEsquerda] < pivo)
            comparacoes++
            ; 
              // encontra item menor
         while (pontDireita > 0 && arranjo[--pontDireita] > pivo)
            comparacoes++
            ; // (nop)

         if (pontEsquerda >= pontDireita)
         {
            comparacoes++;
          // se ponteiros cruzarem,
            break; // particionamento concluído
         }

         else
         {
            trocas++;
            trocar(pontEsquerda, pontDireita); // troca elementos
         
         } // se não cruzaram, então
      } // fim while(true)
      trocar(pontEsquerda, direita); // restaura pivo
      trocas++;
      return pontEsquerda; // retorna localização do pivo
   } // fim particionar()
   // --------------------------------------------------------------

   public void trocar(int dex1, int dex2) // troca dois elementos
   {
      long temp = arranjo[dex1]; // A para temp
      arranjo[dex1] = arranjo[dex2]; // B para A
      arranjo[dex2] = temp; // temp para B
   } // fim trocar()
   protected int getComparacoes() {
      return comparacoes;
   }
   protected int getTrocas() {
      return trocas;
   }
   // --------------------------------------------------------------
} // fim da classe OrdenacaoArray
////////////////////////////////////////////////////////////////

class QuickSort1App {
   public static void main(String[] args) {
      int tamanhoMax = 16; // tamanho do array
      OrdenacaoArray arr;
      arr = new OrdenacaoArray(tamanhoMax); // cria array
      long [] valores = { 69, 0, 70, 6, 38, 38, 24, 56, 44, 26, 73, 77, 30, 45, 97, 65};
     for (long valor :  valores)
     {
         arr.inserir(valor);
     }
      arr.exibir(); // exibe itens
      arr.quickSort(); // realiza quicksort
      arr.exibir(); // exibe novamente
      System.out.println("Número de comparações: " + arr.getComparacoes());
      System.out.println("Número de trocas: " + arr.getTrocas());
   } // fim main()
} // fim da classe QuickSort1App
////////////////////////////////////////////////////////////////
