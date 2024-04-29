// Importando as classes necessárias para a interface gráfica e manipulação de eventos.
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

// Classe No representa um nó em uma lista ligada. Cada nó tem um valor e referências para o próximo e o nó anterior.
class No {
    int valor;
    No proximo;
    No anterior;

    // Construtor para a classe No.
    No(int valor) {
        this.valor = valor;
        this.proximo = null;
        this.anterior = null;
    }
}

// Classe ListaLigada representa uma lista ligada. Ela tem um nó cabeça e métodos para inserir nós no início e no fim, exibir a lista e buscar um nó.
class ListaLigada {
    No cabeca;

    // Método para inserir um nó no início da lista.
    public void insereNoInicio(No novoNo) {
        if (cabeca != null) {
            novoNo.proximo = cabeca;
            cabeca.anterior = novoNo;
        }
        cabeca = novoNo;
    }

    // Método para inserir um nó no fim da lista.
    public void insereNoFim(No novoNo) {
        if (cabeca == null) {
            cabeca = novoNo;
        } else {
            No ultimo = cabeca;
            while (ultimo.proximo != null) {
                ultimo = ultimo.proximo;
            }
            ultimo.proximo = novoNo;
            novoNo.anterior = ultimo;
        }
    }

    // Método para exibir a lista na área de texto.
    public void exibe(JTextArea areaTexto) {
        No temp = cabeca;
        while (temp != null) {
            areaTexto.append(temp.valor + " ");
            temp = temp.proximo;
        }
    }

    // Método para buscar um nó na lista.
    public int buscarNo(int valor) {
        No alvo = cabeca;
        while (alvo != null && alvo.valor != valor) {
            alvo = alvo.proximo;
        }

        return Objects.requireNonNull(alvo).valor;
    }
}

// Classe Principal contém o método main e cria a interface gráfica.
public class Principal {
    public static void main(String[] args) {
        // Criação dos componentes da interface gráfica.
        JButton botaoInicio, botaoFim, botaobusca;
        JTextField campoTexto, campoBusca;
        JLabel lbl;
        JTextArea areaTexto;
        JScrollPane painelRolagem;
        JFrame quadro = new JFrame();
        quadro.setTitle("Lista encadeada");
        quadro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quadro.setSize(400, 440);
        quadro.setLayout(new GridLayout(10, 2)); // Define o layout do painel como uma grade com o número de linhas igual ao número de botões e 2 colunas.
        botaoInicio = new JButton("Inserir no Início");
        botaoFim = new JButton("Inserir no Fim");
        ListaLigada lista = new ListaLigada();
        campoTexto = new JTextField(10);
        campoBusca = new JTextField(10);
        quadro.add(campoTexto);
        quadro.add(botaoInicio);
        quadro.add(botaoFim);
        areaTexto = new JTextArea(2, 10);
        painelRolagem = new JScrollPane(areaTexto);
        quadro.add(painelRolagem);

        // Adicionando ações aos botões.
        botaoInicio.addActionListener(e -> {
            if (campoTexto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo vazio");
            } else {
                int valor = Integer.parseInt(campoTexto.getText());
                No novoNo = new No(valor);
                lista.insereNoInicio(novoNo);
                areaTexto.setText("");
                campoTexto.setText("");
                lista.exibe(areaTexto);
            }
        });
        botaoFim.addActionListener(e -> {
            if (campoTexto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo vazio");
            } else {
                int valor = Integer.parseInt(campoTexto.getText());
                No novoNo = new No(valor);
                lista.insereNoFim(novoNo);
                areaTexto.setText("");
                campoTexto.setText("");
                lista.exibe(areaTexto);
            }
        });
        quadro.setVisible(true);
        botaobusca = new JButton("Buscar elemento");
        botaobusca.addActionListener(e ->
        {
            try {
                if (campoBusca.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Campo vazio");
                    return;
                }
                int valor = Integer.parseInt(campoBusca.getText());
                JOptionPane.showMessageDialog(null, "Valor: " + lista.buscarNo(valor));
            } catch (NullPointerException E) {
                JOptionPane.showMessageDialog(null, "Valor não encontrado");
            }

        });
        lbl = new JLabel("Buscar: ");
        quadro.add(lbl);
        quadro.add(campoBusca);
        quadro.add(botaobusca);

    }

}
