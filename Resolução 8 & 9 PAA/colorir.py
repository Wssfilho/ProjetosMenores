def subtabuleiro_valido(tabuleiro, linha, coluna):
    """Verifica se um subtabuleiro 2x2 tem todas as cores diferentes."""
    cores = set()
    for i in range(linha, linha + 2):
        for j in range(coluna, coluna + 2):
            cores.add(tabuleiro[i][j])
    return len(cores) == 4

def posicao_valida(tabuleiro, linha, coluna, cor):
    """Verifica se uma cor pode ser colocada em uma posição,
    considerando adjacências e subtabuleiros 2x2."""

    # Verifica se a posição está dentro dos limites do tabuleiro e se está vazia
    if not (0 <= linha < len(tabuleiro) and 0 <= coluna < len(tabuleiro[0]) and tabuleiro[linha][coluna] == ""):
        return False

    # Verifica se a cor não está presente nas células adjacentes (cima, baixo, esquerda, direita)
    for l, c in [(linha - 1, coluna), (linha + 1, coluna), (linha, coluna - 1), (linha, coluna + 1)]:
        if 0 <= l < len(tabuleiro) and 0 <= c < len(tabuleiro[0]) and tabuleiro[l][c] == cor:
            return False

    # Verifica se a inserção da cor não invalida o subtabuleiro 2x2
    if linha < len(tabuleiro) - 1 and coluna < len(tabuleiro[0]) - 1:
        if not subtabuleiro_valido(tabuleiro, linha, coluna):
            return False

    return True

def resolver(tabuleiro, cores, linha=0, coluna=0, solucoes=0):
    """Resolve o tabuleiro recursivamente com backtracking, contando as soluções."""

    # Caso base: Se a linha for igual ao comprimento do tabuleiro, encontrou uma solução completa
    if linha == len(tabuleiro):
        return solucoes + 1  # Incrementa o número de soluções

    # Calcula a próxima posição no tabuleiro
    proxima_linha = linha + 1 if coluna == len(tabuleiro[0]) - 1 else linha
    proxima_coluna = (coluna + 1) % len(tabuleiro[0])

    # Se a célula está na diagonal principal
    if linha == coluna:
        for cor in cores:  # Tenta cada cor
            if posicao_valida(tabuleiro, linha, coluna, cor):  # Verifica se a cor é válida na posição
                tabuleiro[linha][coluna] = cor  # Atribui a cor à posição
                tabuleiro[linha][-coluna - 1] = cor  # Atribui a mesma cor na posição simétrica
                solucoes = resolver(tabuleiro, cores, proxima_linha, proxima_coluna, solucoes)  # Chama recursivamente
                tabuleiro[linha][coluna] = ""  # Backtrack: remove a cor
                tabuleiro[linha][-coluna - 1] = ""  # Backtrack: remove a cor da posição simétrica
        return solucoes  # Retorna o número de soluções encontradas até agora
    # Se a célula está na diagonal secundária
    elif linha == len(tabuleiro) - coluna - 1:
        return resolver(tabuleiro, cores, proxima_linha, proxima_coluna, solucoes)

    for cor in cores:  # Tenta cada cor
        if posicao_valida(tabuleiro, linha, coluna, cor):  # Verifica se a cor é válida na posição
            tabuleiro[linha][coluna] = cor  # Atribui a cor à posição
            solucoes = resolver(tabuleiro, cores, proxima_linha, proxima_coluna, solucoes)  # Chama recursivamente
            tabuleiro[linha][coluna] = ""  # Backtrack: remove a cor

    return solucoes  # Retorna o número de soluções encontradas até agora

def imprimir_tabuleiro(tabuleiro):
    """Imprime o tabuleiro de forma legível."""
    for linha in tabuleiro:
        print(" ".join(linha))

def main():
    cores = ["1", "2", "3", "4", "5", "6", "7", "8", "9"]
    tamanho = 7
    tabuleiro = [[" " for _ in range(tamanho)] for _ in range(tamanho)]

    num_solucoes = resolver(tabuleiro, cores)  # Chama resolver para obter o número de soluções
    if num_solucoes > 0:
        imprimir_tabuleiro(tabuleiro)
    else:
        print("A quantidade de soluções é:", num_solucoes)  # Imprime o número de soluções

if __name__ == "__main__":
    main()
