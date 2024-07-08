import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//make a change to test my new brach
public class MenorCusto {

    public static int[] menorCusto(int[][] C, String[] vertices, int[] valores, int orcamento) {
        int n = C.length;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[1] = 0;

        for (int mask = 1; mask < (1 << n); mask += 2) {
            for (int i = 1; i < n; i++) {
                if ((mask & (1 << i)) != 0) 
                {
                    int custo_total = 0;
                    for (int j = 0; j < n; j++) {
                        if (j != i && (mask & (1 << j)) != 0) {
                            custo_total += C[i][j];
                        }
                    }
                    if (custo_total <= orcamento) {
                        dp[mask] = Math.max(dp[mask], dp[mask ^ (1 << i)] + valores[i]);
                    }
                }
            }
        }

        int melhorLucro = 0;
        int melhorCusto = Integer.MAX_VALUE;
        int melhorMask = 0;

        for (int mask = 1; mask < (1 << n); mask += 2) {
            int custo = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    for (int j = i + 1; j < n; j++) {
                        if ((mask & (1 << j)) != 0) {
                            custo += C[i][j];
                        }
                    }
                }
            }
            if (custo <= orcamento && dp[mask] > melhorLucro) {
                melhorLucro = dp[mask];
                melhorCusto = custo;
                melhorMask = mask;
            }
        }

        List<String> melhorCaminho = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if ((melhorMask & (1 << i)) != 0) {
                melhorCaminho.add(vertices[i]);
            }
        }

        System.out.println("Máximo lucro possível dentro do orçamento: " + melhorLucro);
        System.out.print("Melhor caminho: ");
        for (String v : melhorCaminho) {
            System.out.print(v + " -> ");
        }
        System.out.println(vertices[0]);
        System.out.println("Custo: " + melhorCusto);

        return new int[] { melhorLucro, melhorCaminho.size(), melhorCusto };
    }

    public static void main(String[] args) {

        int[][] C = {
            {0, 1, 2, 5},
            {1, 0, 6, 4},
            {2, 6, 0, 4},
            {5, 4, 4, 0}
        };

        int[] valores = {10, 20, 30, 40};
        String[] vertices = {"a", "b", "c", "d"};

        int orcamento = 10;

        menorCusto(C, vertices, valores, orcamento);
    }
}
