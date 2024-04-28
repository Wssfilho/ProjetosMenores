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
    void insereNoInicio(No novoNo) {
        if (cabeca != null) {
            novoNo.proximo = cabeca;
            cabeca.anterior = novoNo;
        }
        cabeca = novoNo;
    }

    void insereNoFim(No novoNo) {
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

    void exibe(JTextArea areaTexto) {
        No temp = cabeca;
        while (temp != null) {
            areaTexto.append(temp.valor + " ");
            temp = temp.proximo;
        }
    }
}

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame quadro = new JFrame();
                quadro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                quadro.setSize(400, 300);
                quadro.setLayout(new GridLayout(10, 2)); // Define o layout do painel como uma grade com o número de linhas igual ao número de botões e 2 colunas.

                ListaLigada lista = new ListaLigada();
                JTextField campoTexto = new JTextField(10);
                quadro.add(campoTexto);
                JTextArea areaTexto = new JTextArea(2, 10);
                JScrollPane painelRolagem = new JScrollPane(areaTexto);
                quadro.add(painelRolagem);
                JButton botaoInicio = new JButton("Inserir no Início");

                botaoInicio.addActionListener(e -> {
                    if(campoTexto.getText().isEmpty())
                    {
                       JOptionPane.showMessageDialog(null, "Campo vazio");
                    }
                    else {
                        int valor = Integer.parseInt(campoTexto.getText());
                        No novoNo = new No(valor);
                        lista.insereNoInicio(novoNo);
                        areaTexto.setText("");
                        campoTexto.setText("");
                        lista.exibe(areaTexto);
                    }


                });
                quadro.add(botaoInicio);

                JButton botaoFim = new JButton("Inserir no Fim");
                botaoFim.addActionListener(e -> {
                    int valor = Integer.parseInt(campoTexto.getText());
                    No novoNo = new No(valor);
                    lista.insereNoFim(novoNo);
                    areaTexto.setText("");
                    campoTexto.setText("");
                    lista.exibe(areaTexto);
                });
                quadro.add(botaoFim);
                quadro.setVisible(true);
            }
        });
    }
}
