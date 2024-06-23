def subtabuleiro_valido(tabuleiro, linha, coluna):
    """Verifica se um subtabuleiro 2x2 tem todas as cores diferentes."""
    cores = set()  # Cria um conjunto (set) para armazenar as cores encontradas
    for i in range(linha, linha + 2):      # Itera pelas linhas do subtabuleiro (2 linhas)
        for j in range(coluna, coluna + 2):  # Itera pelas colunas do subtabuleiro (2 colunas)
            cores.add(tabuleiro[i][j])     # Adiciona a cor da célula atual ao conjunto
    return len(cores) == 4                 # Retorna True se o conjunto tiver 4 cores (todas diferentes)


def posicao_valida(tabuleiro, linha, coluna, cor):
    """Verifica se uma cor pode ser colocada em uma posição,
    considerando adjacências e subtabuleiros 2x2."""

    # Verifica se a posição está dentro dos limites do tabuleiro e se a célula está vazia
    if not (0 <= linha < len(tabuleiro) and 0 <= coluna < len(tabuleiro[0]) and tabuleiro[linha][coluna] == ""):
        return False

    # Verifica cores adjacentes (cima, baixo, esquerda, direita)
    for l, c in [(linha - 1, coluna), (linha + 1, coluna), (linha, coluna - 1), (linha, coluna + 1)]:
        if 0 <= l < len(tabuleiro) and 0 <= c < len(tabuleiro[0]) and tabuleiro[l][c] == cor:
            return False

    # Verifica subtabuleiro 2x2 se aplicável (a posição não está na borda)
    if linha < len(tabuleiro) - 1 and coluna < len(tabuleiro[0]) - 1:
        if not subtabuleiro_valido(tabuleiro, linha, coluna):
            return False

    return True  # Se passou por todas as verificações, a posição é válida


def resolver(tabuleiro, cores, linha=0, coluna=0):
    """Resolve o tabuleiro recursivamente com backtracking."""

    if linha == len(tabuleiro):  # Caso base: todas as linhas foram preenchidas
        return True

    # Calcula a próxima posição a ser preenchida (passa para a próxima linha se a coluna for a última)
    proxima_linha = linha + 1 if coluna == len(tabuleiro[0]) - 1 else linha
    proxima_coluna = (coluna + 1) % len(tabuleiro[0])

    # Preenchimento simultâneo das diagonais
    if linha == coluna:  # Diagonal principal
        for cor in cores:  # Tenta todas as cores na posição atual
            if posicao_valida(tabuleiro, linha, coluna, cor):
                tabuleiro[linha][coluna] = cor          # Preenche a diagonal principal
                tabuleiro[linha][-coluna - 1] = cor    # Preenche a diagonal secundária
                if resolver(tabuleiro, cores, proxima_linha, proxima_coluna):  # Chama recursivamente
                    return True                        # Se encontrar solução, retorna True
                tabuleiro[linha][coluna] = ""          # Backtracking: desfaz a escolha
                tabuleiro[linha][-coluna - 1] = ""    # Backtracking: desfaz a escolha
        return False  # Nenhuma cor funcionou na diagonal principal
    elif linha == len(tabuleiro) - coluna - 1:  # Diagonal secundária (já preenchida)
        return resolver(tabuleiro, cores, proxima_linha, proxima_coluna)  # Pula para próxima posição

    # Para as demais posições
    for cor in cores:
        if posicao_valida(tabuleiro, linha, coluna, cor):
            tabuleiro[linha][coluna] = cor
            if resolver(tabuleiro, cores, proxima_linha, proxima_coluna):
                return True
            tabuleiro[linha][coluna] = ""  # Backtracking

    return False  # Nenhuma cor funcionou nesta posição


# ... (resto do código - imprimir_tabuleiro e main - sem alterações) ...

def imprimir_tabuleiro(tabuleiro):
    """Imprime o tabuleiro de forma legível."""
    for linha in tabuleiro:
        print(" ".join(linha))

def main():
    cores = ["1", "2", "3", "4", "5"]  # Cores disponíveis
    tamanho = 6  # Tamanho do tabuleiro (6x6)
    tabuleiro = [[" " for _ in range(tamanho)] for _ in range(tamanho)]  # Tabuleiro vazio

    if resolver(tabuleiro, cores):
        imprimir_tabuleiro(tabuleiro)
    else:
        print("Não há solução.")

if __name__ == "__main__":
    main()
