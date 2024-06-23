def is_valid(board, row, col, color):
    """Verifica se uma cor pode ser colocada em uma posição."""

    # Verifica limites do tabuleiro e se a célula está vazia
    if not (0 <= row < len(board) and 0 <= col < len(board[0]) and board[row][col] == ""):
        return False

    # Verifica vizinhos adjacentes (cima, baixo, esquerda, direita)
    for r, c in [(row - 1, col), (row + 1, col), (row, col - 1), (row, col + 1)]:
        if 0 <= r < len(board) and 0 <= c < len(board[0]) and board[r][c] == color:
            return False

    return True  # Posição válida


def solve(board, colors, row=0, col=0):
    """Resolve o tabuleiro recursivamente."""

    if row == len(board):
        return True  # Todas as linhas preenchidas

    next_row = row + 1 if col == len(board[0]) - 1 else row
    next_col = (col + 1) % len(board[0])

    for color in colors:
        if is_valid(board, row, col, color):
            board[row][col] = color

            if solve(board, colors, next_row, next_col):
                return True

            board[row][col] = ""  # Backtracking

    return False  # Nenhuma cor válida encontrada


def print_board(board):
    """Imprime o tabuleiro de forma legível."""
    for row in board:
        print(" ".join(row))


def main():
    colors = ["1", "2", "3", "4", "5"]
    board = [[" " for _ in range(6)] for _ in range(6)]  # Tabuleiro vazio 6x6

    if solve(board, colors):
        print_board(board)
    else:
        print("Não há solução.")


if __name__ == "__main__":
    main()
