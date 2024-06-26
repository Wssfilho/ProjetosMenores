from itertools import product

subgrid_directions = [(-1, 0), (0, -1), (-1, -1), (0, 0)]

def add_subgrids(board, row, col, unique_subgrids):
    if row > 0 and col > 0:
        subgrid = (board[row - 1][col], board[row][col - 1], board[row - 1][col - 1], board[row][col])
        unique_subgrids.add(subgrid)

def remove_subgrids(board, row, col, unique_subgrids):
    if row > 0 and col > 0:
        subgrid = (board[row - 1][col], board[row][col - 1], board[row - 1][col - 1], board[row][col])
        unique_subgrids.remove(subgrid)

def find_next(board, n):
    for i in range(n):
        for j in range(n):
            if board[i][j] == 0:
                return (i, j)
    return (-1, -1)

def print_board(board):
    for row in board:
        print(" ".join(map(str, row)))
    print()

def is_valid(board, color, row, col, n, subgrids):
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    # Check adjacent cells
    for dir in directions:
        adj_row, adj_col = row + dir[0], col + dir[1]
        if 0 <= adj_row < n and 0 <= adj_col < n and board[adj_row][adj_col] == color:
            return False

    # Check diagonals
    if row == col and board[row][n - 1 - row] != color and board[row][n - 1 - row] != 0:
        return False
    if row + col == n - 1 and board[row][row] != color and board[row][row] != 0:
        return False

    # Check 2x2 subgrid
    if row > 0 and col > 0:
        board[row][col] = color  # Temporarily paint the current position
        subgrid = (board[row - 1][col], board[row][col - 1], board[row - 1][col - 1], board[row][col])
        board[row][col] = 0  # Undo the painting
        if subgrid != (0, 0, 0, 0) and subgrid in subgrids:
            return False

    return True

def color_board(board, colors, solutions, n, subgrids):
    row, col = find_next(board, n)
    if row == -1 and col == -1:
        solutions[0] += 1
        print(f"Solution {solutions[0]}:")
        print_board(board)
        return

    for color in colors:
        if is_valid(board, color, row, col, n, subgrids):
            board[row][col] = color
            add_subgrids(board, row, col, subgrids)

            color_board(board, colors, solutions, n, subgrids)

            remove_subgrids(board, row, col, subgrids)
            board[row][col] = 0  # backtracking

def find_all_solutions(n, colors):
    board = [[0] * n for _ in range(n)]
    solutions = [0]
    subgrids = set()

    color_board(board, colors, solutions, n, subgrids)
    return solutions[0]


n = 5  # Size of the board
k = 5 # Number of colors
colors = list(range(1, k + 1))
total_solutions = find_all_solutions(n, colors)
print(f"Total solutions found for a {n}x{n} board with {k} colors: {total_solutions}")

