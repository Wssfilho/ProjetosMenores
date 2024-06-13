public class SolucionadorTabuleiro {
    private static final int TAMANHO = 6;
    private int[][] tabuleiro;
    private boolean[][] linhas, colunas, caixas;
    private int[] diagonais;

    public SolucionadorTabuleiro(int[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
        linhas = new boolean[TAMANHO][TAMANHO + 1];
        colunas = new boolean[TAMANHO][TAMANHO + 1];
        caixas = new boolean[TAMANHO][TAMANHO + 1];
        diagonais = new int[2];

        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (tabuleiro[i][j] != 0) {
                    int cor = tabuleiro[i][j];
                    linhas[i][cor] = true;
                    colunas[j][cor] = true;
                    caixas[i / 2 * 2 + j / 3][cor] = true;
                    if (i == j) {
                        diagonais[0] = cor;
                    }
                    if (i == TAMANHO - 1 - j) {
                        diagonais[1] = cor;
                    }
                }
            }
        }
    }

    private boolean ehValido(int r, int c, int cor) {
        if (linhas[r][cor] || colunas[c][cor] || caixas[r / 2 * 2 + c / 3][cor])
            return false;

        for (int dr = -1; dr <= 1; dr += 2) {
            for (int dc = -1; dc <= 1; dc += 2) {
                int nr = r + dr, nc = c + dc;
                if (nr >= 0 && nr < TAMANHO && nc >= 0 && nc < TAMANHO && tabuleiro[nr][nc] == cor)
                    return false;
            }
        }

        if ((r == c && cor != diagonais[0]) || (r == TAMANHO - 1 - c && cor != diagonais[1]))
            return false;

        return true;
    }

    public boolean resolve(int i, int j) {
        if (i == TAMANHO)
            return true;

        if (j == TAMANHO)
            return resolve(i + 1, 0);

        if (tabuleiro[i][j] != 0)
            return resolve(i, j + 1);

        for (int cor = 1; cor <= TAMANHO; cor++) {
            if (ehValido(i, j, cor)) {
                tabuleiro[i][j] = cor;
                linhas[i][cor] = true;
                colunas[j][cor] = true;
                caixas[i / 2 * 2 + j / 3][cor] = true;
                if (i == j) {
                    diagonais[0] = cor;
                }
                if (i == TAMANHO - 1 - j) {
                    diagonais[1] = cor;
                }

                if (resolve(i, j + 1))
                    return true;

                tabuleiro[i][j] = 0;
                linhas[i][cor] = false;
                colunas[j][cor] = false;
                caixas[i / 2 * 2 + j / 3][cor] = false;
                if (i == j) {
                    diagonais[0] = 0;
                }
                if (i == TAMANHO - 1 - j) {
                    diagonais[1] = 0;
                }
            }
        }

        return false;
    }

    public void imprime() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] tabuleiro = {
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
        };

        SolucionadorTabuleiro solucionador = new SolucionadorTabuleiro(tabuleiro);
        if (solucionador.resolve(0, 0)) {
            solucionador.imprime();
        } else {
            System.out.println("Nenhuma solução encontrada");
        }
    }
}
