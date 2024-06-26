import java.util.HashSet;
import java.util.Set;

public class colorir {
    static int[][] directions = {{-1, 0}, {0, -1}, {-1, -1}, {0, 0}};
    static int[][] adjDirections = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void addSubgrid(int[][] grid, int row, int col, Set<String> subgrids) {
        if (row > 0 && col > 0) {
            String subgrid = createSubgridString(grid, row, col);
            subgrids.add(subgrid);
        }
    }
    
    public static void removeSubgrid(int[][] grid, int row, int col, Set<String> subgrids) {
        if (row > 0 && col > 0) {
            String subgrid = createSubgridString(grid, row, col);
            subgrids.remove(subgrid);
        }
    }
    
    public static String createSubgridString(int[][] grid, int row, int col) {
        return grid[row - 1][col] + "," + grid[row][col - 1] + "," + grid[row - 1][col - 1] + "," + grid[row][col];
    }
    
    public static int[] findEmptyCell(int[][] grid, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
    
    public static void displayGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static boolean isPlacementValid(int[][] grid, int color, int row, int col, int size, Set<String> subgrids) {
        
        for (int[] dir : adjDirections) {
            int adjRow = row + dir[0];
            int adjCol = col + dir[1];
            if (0 <= adjRow && adjRow < size && 0 <= adjCol && adjCol < size && grid[adjRow][adjCol] == color) {
                return false;
            }
        }
        
        if ((row == col && grid[row][size - 1 - row] != color && grid[row][size - 1 - row] != 0) ||
            (row + col == size - 1 && grid[row][row] != color && grid[row][row] != 0)) {
            return false;
        }
        
        if (row > 0 && col > 0) {
            grid[row][col] = color;
            String subgrid = createSubgridString(grid, row, col);
            grid[row][col] = 0;
            if (!subgrid.equals("0,0,0,0") && subgrids.contains(subgrid)) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void fillGrid(int[][] grid, int[] colors, int[] solutions, int size, Set<String> subgrids) {
        int[] emptyCell = findEmptyCell(grid, size);
        int row = emptyCell[0];
        int col = emptyCell[1];
        
        if (row == -1 && col == -1) {
            solutions[0]++;
            System.out.println("Solution " + solutions[0] + ":");
            displayGrid(grid);
            return;
        }
        
        for (int color : colors) {
            if (isPlacementValid(grid, color, row, col, size, subgrids)) {
                grid[row][col] = color;
                addSubgrid(grid, row, col, subgrids);
                
                fillGrid(grid, colors, solutions, size, subgrids);
                
                removeSubgrid(grid, row, col, subgrids);
                grid[row][col] = 0;
            }
        }
    }
    
    public static int calculateTotalSolutions(int size, int[] colors) {
        int[][] grid = new int[size][size];
        int[] solutions = {0};
        Set<String> subgrids = new HashSet<>();
        
        fillGrid(grid, colors, solutions, size, subgrids);
        return solutions[0];
    }
    
    public static void main(String[] args) {
        int size = 3;
        int numColors = 3;
        int[] colors = new int[numColors];
        for (int i = 0; i < numColors; i++) {
            colors[i] = i + 1;
        }
        int totalSolutions = calculateTotalSolutions(size, colors);
        System.out.println("Total solutions found for a " + size + "x" + size + " grid with " + numColors + " colors: " + totalSolutions);
    }
}
