// quickSort2.java
// demonstra o quick sort com particionamento pela mediana de três
// para executar este programa: C>java QuickSort2App
////////////////////////////////////////////////////////////////
class OrdenacaoArray
   {
   private long[] array;          // referência para o array array
   private int numElementos;               // número de itens de dados
//--------------------------------------------------------------
   public OrdenacaoArray(int max)          // construtor
      {
      array = new long[max];      // cria o array
      numElementos = 0;                    // ainda sem itens
      }
//--------------------------------------------------------------
   public void inserir(long valor)    // insere elemento no array
      {
      array[numElementos] = valor;      // insere
      numElementos++;                      // incrementa tamanho
      }
//--------------------------------------------------------------
   public void exibir()             // exibe conteúdo do array
      {
      System.out.print("A=");
      for(int j=0; j<numElementos; j++)    // para cada elemento,
         System.out.print(array[j] + " ");  // exibe
      System.out.println("");
      }
//--------------------------------------------------------------
   public void quickSort()
      {
      recQuickSort(0, numElementos-1);
      }
//--------------------------------------------------------------
   public void recQuickSort(int esquerda, int direita)
      {
      int tamanho = direita-esquerda+1;
      if(tamanho <= 3)                  // ordenação manual se pequeno
         ordenacaoManual(esquerda, direita);
      else                           // quicksort se grande
         {
         long mediana = medianaDe3(esquerda, direita);
         int particao = particionar(esquerda, direita, mediana);
         recQuickSort(esquerda, particao-1);
         recQuickSort(particao+1, direita);
         }
      }  // fim recQuickSort()
//--------------------------------------------------------------
   public long medianaDe3(int esquerda, int direita)
      {
      int centro = (esquerda+direita)/2;
                                         // ordena esquerda e centro
      if( array[esquerda] > array[centro] )
         trocar(esquerda, centro);
                                         // ordena esquerda e direita
      if( array[esquerda] > array[direita] )
         trocar(esquerda, direita);
                                         // ordena centro e direita
      if( array[centro] > array[direita] )
         trocar(centro, direita);

      trocar(centro, direita-1);             // coloca pivo à direita
      return array[direita-1];          // retorna valor da mediana
      }  // fim medianaDe3()
//--------------------------------------------------------------
   public void trocar(int dex1, int dex2)  // troca dois elementos
      {
      long temp = array[dex1];        // A para temp
      array[dex1] = array[dex2];   // B para A
      array[dex2] = temp;             // temp para B
      }  // fim trocar()
//--------------------------------------------------------------
    public int particionar(int esquerda, int direita, long pivo)
       {
       int ponteiroEsquerda = esquerda;             // à direita do primeiro elem
       int ponteiroDireita = direita - 1;       // à esquerda do pivo

       while(true)
          {
          while( array[++ponteiroEsquerda] < pivo )  // encontra maior
             ;                                  //    (nop)
          while( array[--ponteiroDireita] > pivo ) // encontra menor
             ;                                  //    (nop)
          if(ponteiroEsquerda >= ponteiroDireita)      // se ponteiros cruzarem,
             break;                    //    particionamento concluído
          else                         // não cruzaram, então
             trocar(ponteiroEsquerda, ponteiroDireita);  // troca elementos
          }  // fim while(true)
       trocar(ponteiroEsquerda, direita-1);         // restaura pivo
       return ponteiroEsquerda;                 // retorna localização do pivo
       }  // fim particionar()
//--------------------------------------------------------------
   public void ordenacaoManual(int esquerda, int direita)
      {
      int tamanho = direita-esquerda+1;
      if(tamanho <= 1)
         return;         // ordenação não necessária
      if(tamanho == 2)
         {               // ordena esquerda e direita
         if( array[esquerda] > array[direita] )
            trocar(esquerda, direita);
         return;
         }
      else               // tamanho é 3
         {               // ordena esquerda, centro e direita
         if( array[esquerda] > array[direita-1] )
            trocar(esquerda, direita-1);                // esquerda, centro
         if( array[esquerda] > array[direita] )
            trocar(esquerda, direita);                  // esquerda, direita
         if( array[direita-1] > array[direita] )
            trocar(direita-1, direita);               // centro, direita
         }
      }  // fim ordenacaoManual()
//--------------------------------------------------------------
   }  // fim da classe OrdenacaoArray
////////////////////////////////////////////////////////////////
class QuickSort2App
   {
   public static void main(String[] args)
      {
      int tamanhoMax = 16;             // tamanho do array
      OrdenacaoArraycomment arr;                 // referência para o array
      arr = new OrdenacaoArraycomment(tamanhoMax);  // cria o array

      for(int j=0; j<tamanhoMax; j++)  // preenche o array com
         {                          // números aleatórios
         long n = (int)(java.lang.Math.random()*99);
         arr.inserir(n);
         }
      arr.exibir();                // exibe itens
      arr.quickSort();              // ordena com quicksort
      arr.exibir();                // exibe novamente
      }  // fim main()
   }  // fim da classe QuickSort2App
////////////////////////////////////////////////////////////////
