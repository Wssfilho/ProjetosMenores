def posicao_valida(tabuleiro, linha, coluna, cor):
    """Verifica se uma cor pode ser colocada em uma determinada posição."""

    # Verifica se a posição está dentro dos limites do tabuleiro e se a célula está vazia
    if not (0 <= linha < len(tabuleiro) and 0 <= coluna < len(tabuleiro[0]) and tabuleiro[linha][coluna] == ""):
        return False

    # Verifica as células adjacentes (cima, baixo, esquerda, direita)
    for l, c in [(linha - 1, coluna), (linha + 1, coluna), (linha, coluna - 1), (linha, coluna + 1)]:
        if 0 <= l < len(tabuleiro) and 0 <= c < len(tabuleiro[0]) and tabuleiro[l][c] == cor:
            return False

    return True  # A posição é válida


def resolver(tabuleiro, cores, linha=0, coluna=0):
    """Resolve o tabuleiro recursivamente."""

    if linha == len(tabuleiro):
        return True  # Todas as linhas foram preenchidas com sucesso

    # Calcula a próxima posição a ser preenchida
    proxima_linha = linha + 1 if coluna == len(tabuleiro[0]) - 1 else linha
    proxima_coluna = (coluna + 1) % len(tabuleiro[0])

    for cor in cores:
        if posicao_valida(tabuleiro, linha, coluna, cor):
            tabuleiro[linha][coluna] = cor  # Tenta colocar a cor

            if resolver(tabuleiro, cores, proxima_linha, proxima_coluna):
                return True  # Se a solução for encontrada, retorna True

            tabuleiro[linha][coluna] = ""  # Backtracking: remove a cor se não levar à solução

    return False  # Nenhuma cor funcionou nesta posição


def imprimir_tabuleiro(tabuleiro):
    """Imprime o tabuleiro de forma legível."""
    for linha in tabuleiro:
        print(" ".join(linha))


def main():
    cores = ["1", "2", "3", "4", "5"]
    tabuleiro = [[" " for _ in range(6)] for _ in range(6)]  # Tabuleiro 6x6 vazio

    if resolver(tabuleiro, cores):
        imprimir_tabuleiro(tabuleiro)
    else:
        print("Não há solução.")


if __name__ == "__main__":
    main()
