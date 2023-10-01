package JogoVelha;

public class JogoDaVelha
{
        private char[][] tabuleiro;
        private char jogadorAtual;

        JogoDaVelha() {
            tabuleiro = new char[3][3];
            jogadorAtual = 'X';
            limparTabuleiro();
        }

        public void limparTabuleiro() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tabuleiro[i][j] = '.';
                }
            }
        }

        public boolean jogar(int linha, int coluna) {
            if (tabuleiro[linha][coluna] == '.') {
                tabuleiro[linha][coluna] = jogadorAtual;
                return true;
            }
            return false;
        }

        public void proximoJogador() {
            if (jogadorAtual == 'X') {
                jogadorAtual = 'O';
            } else {
                jogadorAtual = 'X';
            }
        }

        public boolean avaliarVencedor() {
            // Verifica linhas
            for (int i = 0; i < 3; i++) {
                if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) {
                    return true;
                }
            }

            // Verifica colunas
            for (int i = 0; i < 3; i++) {
                if (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual) {
                    return true;
                }
            }

            // Verifica diagonais
            if (tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) {
                return true;
            }

            if (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual) {
                return true;
            }

            return false;
        }



}
