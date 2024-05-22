// partition.java
// demonstra a partição de um array
// para executar este programa: C>java PartitionApp
////////////////////////////////////////////////////////////////

class ArrayParticao
   {
   private long[] oArray;          // ref para o array oArray
   private int numElementos;       // número de itens de dados
//--------------------------------------------------------------
   public ArrayParticao(int max)   // construtor
      {
      oArray = new long[max];      // cria o array
      numElementos = 0;            // ainda sem itens
      }
//--------------------------------------------------------------
   public void inserir(long valor) // insere elemento no array
      {
      oArray[numElementos] = valor; // insere
      numElementos++;               // incrementa tamanho
      }
//--------------------------------------------------------------
   public int tamanho()            // retorna número de itens
      { return numElementos; }
//--------------------------------------------------------------
   public void exibir()            // exibe conteúdo do array
      {
      System.out.print("A=");
      for(int j=0; j<numElementos; j++) // para cada elemento,
         System.out.print(oArray[j] + " "); // exibe
      System.out.println("");
      }
//--------------------------------------------------------------
    public int particionar(int esquerda, int direita, long pivo)
       {
       int ponteiroEsquerda = esquerda - 1; // à direita do primeiro elem
       int ponteiroDireita = direita + 1;   // à esquerda do pivo
       while(true)
          {
          while(ponteiroEsquerda < direita && // encontra item maior
                oArray[++ponteiroEsquerda] < pivo)
             ; // (nop)

          while(ponteiroDireita > esquerda && // encontra item menor
                oArray[--ponteiroDireita] > pivo)
             ; // (nop)
          if(ponteiroEsquerda >= ponteiroDireita) // se ponteiros cruzarem,
             break;                               // partição concluída
          else                                    // não cruzou, então
             trocar(ponteiroEsquerda, ponteiroDireita); // troca elementos
          }  // fim do while(true)
       return ponteiroEsquerda;                  // retorna partição
       }  // fim do particionar()
//--------------------------------------------------------------
   public void trocar(int indice1, int indice2) // troca dois elementos
      {
      long temp;
      temp = oArray[indice1];        // A para temp
      oArray[indice1] = oArray[indice2]; // B para A
      oArray[indice2] = temp;        // temp para B
      }  // fim do trocar()
//--------------------------------------------------------------
   }  // fim da classe ArrayParticao
////////////////////////////////////////////////////////////////
class PartitionApp
   {
   public static void main(String[] args)
      {
      int tamanhoMax = 16;          // tamanho do array
      ArrayParticao1 arr;            // referência para o array
      arr = new ArrayParticao1(tamanhoMax); // cria o array

      for(int j=0; j<tamanhoMax; j++) // preenche o array com
         {                            // números aleatórios
         long n = (int)(java.lang.Math.random()*199);
         arr.inserir(n);
         }
      arr.exibir();                  // exibe array não ordenado

      long pivo = 99;                // valor do pivo
      System.out.print("Pivo é " + pivo);
      int tamanho = arr.tamanho();
                                      // particiona o array
      int indiceParticao = arr.particionar(0, tamanho-1, pivo);

      System.out.println(", Partição está no índice " + indiceParticao);
      arr.exibir();                  // exibe array particionado
      }  // fim do main()
   }  // fim da classe PartitionApp
////////////////////////////////////////////////////////////////
