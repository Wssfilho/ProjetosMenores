import javax.swing.*;
import java.awt.*;

class No {
    int valor;
    No proximo;
    No anterior;

    No(int valor) {
        this.valor = valor;
        this.proximo = null;
        this.anterior = null;
    }
}

class ListaLigada {
    No cabeca;

    public void insereNoInicio(No novoNo) {
        if (cabeca != null) {
            novoNo.proximo = cabeca;
            cabeca.anterior = novoNo;
        }
        cabeca = novoNo;
    }

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

    public void exibe(JTextArea areaTexto) {
        No temp = cabeca;
        while (temp != null) {
            areaTexto.append(temp.valor + " ");
            temp = temp.proximo;
        }
    }

    public int buscarNo(int valor) {
        No alvo = cabeca;
        while (alvo != null && alvo.valor != valor) {
            alvo = alvo.proximo;
        }

        return alvo.valor;
    }
}

public class Principal {
    public static void main(String[] args) {
        JButton botaoInicio, botaoFim, botaobusca;
        JTextField campoTexto, campoBusca;
        JTextArea areaTexto;
        JScrollPane painelRolagem;
        JFrame quadro = new JFrame();
        quadro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quadro.setSize(400, 300);
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
            int valor = Integer.parseInt(campoTexto.getText());
            No novoNo = new No(valor);
            lista.insereNoFim(novoNo);
            areaTexto.setText("");
            campoTexto.setText("");
            lista.exibe(areaTexto);
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
        quadro.add(campoBusca);
        quadro.add(botaobusca);

    }

}
