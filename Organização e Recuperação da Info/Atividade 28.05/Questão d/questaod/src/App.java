import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OrdenacaoArray {
   private long[] arranjo;
   private int numElementos;
   public int comparacoes;
   public int trocas;

   public OrdenacaoArray(int max) {
      arranjo = new long[max];
      numElementos = 0;
      comparacoes = 0;
      trocas = 0;
   }

   public void inserir(long valor) {
      arranjo[numElementos] = valor;
      numElementos++;
   }

   public void exibir() {
      System.out.print("A=");
      for (int j = 0; j < numElementos; j++)
         System.out.print(arranjo[j] + " ");
      System.out.println("");
   }

   public void quickSort() {
      recQuickSort(0, numElementos - 1);
   }

   public void recQuickSort(int esquerda, int direita) {
      if (direita - esquerda <= 0)
         return;
      else {
         long pivo = arranjo[direita];
         int particao = particionar(esquerda, direita, pivo);
         recQuickSort(esquerda, particao - 1);
         recQuickSort(particao + 1, direita);
      }
   }

   public int particionar(int esquerda, int direita, long pivo) {
      int pontEsquerda = esquerda - 1;
      int pontDireita = direita;
      while (true) {
         while (arranjo[++pontEsquerda] < pivo)
            comparacoes++;
         while (pontDireita > 0 && arranjo[--pontDireita] > pivo)
            comparacoes++;

         if (pontEsquerda >= pontDireita)
            break;
         else {
            trocar(pontEsquerda, pontDireita);
            trocas++;
         }
      }
      trocar(pontEsquerda, direita);
      trocas++;
      return pontEsquerda;
   }

   public void trocar(int dex1, int dex2) {
      long temp = arranjo[dex1];
      arranjo[dex1] = arranjo[dex2];
      arranjo[dex2] = temp;
   }

   public int getComparacoes() {
      return comparacoes;
   }

   public int getTrocas() {
      return trocas;
   }
}

class QuickSort1App {
   public static void main(String[] args) {
      int tamanhoMax = 16;
      OrdenacaoArray arr = new OrdenacaoArray(tamanhoMax);

      for (int j = 0; j < tamanhoMax; j++) {
         long n = (int) (java.lang.Math.random() * 99);
         arr.inserir(n);
      }
      arr.exibir();

      // Criação da interface gráfica
      JFrame frame = new JFrame("QuickSort GUI");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(300, 200);
      frame.setLayout(new FlowLayout());

      JButton sortButton = new JButton("Ordenar");
      JLabel comparacoesLabel = new JLabel("Número de comparações: 0");
      JLabel trocasLabel = new JLabel("Número de trocas: 0");

      sortButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            arr.quickSort();
            comparacoesLabel.setText("Número de comparações: " + arr.getComparacoes());
            trocasLabel.setText("Número de trocas: " + arr.getTrocas());
            arr.exibir();
         }
      });

      frame.add(sortButton);
      frame.add(comparacoesLabel);
      frame.add(trocasLabel);

      frame.setVisible(true);
   }
}
