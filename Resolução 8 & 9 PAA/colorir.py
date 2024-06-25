N = 5  # Tamanho do tabuleiro (7x7)
cont = 0
C = 5

def verificacoes(board, x, y, color):
    for i in range(N):
        if board[x][i] == color or board[i][y] == color:
            return False

    # Verifica se a cor já está presente na diagonal principal
    for i in range(N):
        if board[i][i] == color:
            return False

    # Verifica se a cor já está presente na diagonal secundária
    for i in range(N):
        if board[i][N - 1 - i] == color:
            return False

    return True

def solveBoard(board, row, col):
    global cont
    if col == N:
        row += 1
        cont += 1
        col = 0

    if row == N:
        return True

    for color in range(1, C + 1):
        if verificacoes(board, row, col, color):
            board[row][col] = color

            if solveBoard(board, row, col + 1):
                return True
            board[row][col] = 0

    return False

if __name__ == "__main__":
    board = [[0] * N for _ in range(N)]  # Inicializa o tabuleiro com zeros
    if solveBoard(board, 0, 0):
        print("Tabuleiro colorido:")
        for i in range(N):
            print(*board[i])
        print(f"Número de soluções: {cont}")
    else:
        print("Não há solução.")
