
/*
        Os métodos `get` e `set` são usados para acessar e modificar os valores dos atributos de um objeto, respectivamente. Eles são uma parte fundamental do princípio de encapsulamento em Java, que é uma das principais características da programação orientada a objetos.

No seu caso, se você quiser acessar ou modificar os atributos de um objeto `ItemCompra` fora da classe `ItemCompra`, você precisará dos métodos `get` e `set`. Por exemplo, no código que forneci anteriormente para imprimir os detalhes de um item, usei os métodos `get` para acessar os valores dos atributos do item.

No entanto, se você não precisa modificar os atributos depois que um objeto `ItemCompra` é criado, e você só está acessando os atributos dentro da própria classe `ItemCompra`, você pode não precisar dos métodos `set`. Da mesma forma, se você está apenas modificando os atributos dentro da própria classe `ItemCompra`, e não precisa acessar os atributos fora da classe `ItemCompra`, você pode não precisar dos métodos `get`.

Em resumo, a necessidade dos métodos `get` e `set` depende de como você pretende usar seus objetos.
         */


package exercicio12;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = 2;
        ItemCompra [] itens = new ItemCompra[2];
        for (int i = 0; i < n; i++)
        {
            //itens[i].setItemdec();
            System.out.println("Insira o valor, a qtd, e o desc: " + i);
            itens[i] = new ItemCompra("tec", s.nextFloat(), s.nextInt(), s.nextFloat());
            itens[i].ValorTitem();
            itens[i].ValorDes();
        }
        for (int i = 0; i<n; i++)
        {
            System.out.println("Item: " + 1);
            System.out.println("Desc: " + itens[i].getItemdec());
            System.out.println("Valor: " + itens[i].getValor());
            System.out.println("Quantidade: " + itens[i].getQtd());
            System.out.println("Desc: " + itens[i].getPerDesconto());
        }


    }
}