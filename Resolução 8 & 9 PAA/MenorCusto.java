import java.util.*;

public class MenorCusto {

    static class CaminhoInfo {
        List<String> caminho;
        int lucro;
        int custo;

        public CaminhoInfo(List<String> caminho, int lucro, int custo) {
            this.caminho = caminho;
            this.lucro = lucro;
            this.custo = custo;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] C = {
            {0, 1, 2, 5},
            {1, 0, 6, 4},
            {2, 6, 0, 4},
            {5, 4, 4, 0}
        };
        int[] valores = {10, 20, 30, 40};
        String[] vertices = {"a", "b", "c", "d"};

        System.out.print("Digite o valor do orçamento: ");
        int orcamento = scanner.nextInt();

        CaminhoInfo resultado = menorCusto(C, vertices, valores, orcamento);

        List<String> caminhoCompleto = new ArrayList<>(resultado.caminho);
        caminhoCompleto.add(0, "a");
        caminhoCompleto.add("a");

        System.out.println("Máximo lucro possível dentro do orçamento: " + resultado.lucro);
        System.out.print("Melhor caminho: ");
        for (String v : caminhoCompleto) {
            System.out.print(v + " -> ");
        }
        System.out.println();
        System.out.println("Custo: " + resultado.custo);
    }

    public static CaminhoInfo menorCusto(int[][] C, String[] vertices, int[] valores, int orcamento) {
        int n = C.length;

        // Mapa para armazenar os custos entre os vértices
        Map<String, Integer> paths = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                paths.put(vertices[i] + vertices[j], C[i][j]);
            }
        }

        // Array para armazenar o melhor caminho até cada vértice
        Map<String, CaminhoInfo> melhorCaminho = new HashMap<>();
        melhorCaminho.put("a", new CaminhoInfo(new ArrayList<>(), 0, 0)); // Caminho inicial vazio

        // Processamento dinâmico para encontrar o caminho com maior lucro dentro do orçamento
        for (int i = 1; i < n; i++) {
            String vertice = vertices[i];
            CaminhoInfo melhorAtual = melhorCaminho.getOrDefault(vertice, new CaminhoInfo(new ArrayList<>(), 0, Integer.MAX_VALUE));

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    String origem = vertices[j];
                    CaminhoInfo caminhoOrigem = melhorCaminho.get(origem);

                    if (caminhoOrigem != null) {
                        int custoTotal = caminhoOrigem.custo + paths.get(origem + vertice);

                        if (custoTotal <= orcamento) {
                            int lucro = caminhoOrigem.lucro + valores[j];
                            List<String> novoCaminho = new ArrayList<>(caminhoOrigem.caminho);
                            novoCaminho.add(origem);

                            // Atualiza o melhor caminho se o lucro for maior ou igual e o custo for menor
                            if (lucro > melhorAtual.lucro || (lucro == melhorAtual.lucro && custoTotal < melhorAtual.custo)) {
                                melhorAtual.lucro = lucro;
                                melhorAtual.caminho = novoCaminho;
                                melhorAtual.custo = custoTotal;
                            }
                        }
                    }
                }
            }

            melhorCaminho.put(vertice, melhorAtual);
        }

        // Encontra o melhor caminho global
        CaminhoInfo melhorGlobal = new CaminhoInfo(new ArrayList<>(), 0, Integer.MAX_VALUE);
        for (Map.Entry<String, CaminhoInfo> entry : melhorCaminho.entrySet()) {
            CaminhoInfo info = entry.getValue();
            if (info.lucro > melhorGlobal.lucro || (info.lucro == melhorGlobal.lucro && info.custo < melhorGlobal.custo)) {
                melhorGlobal = info;
            }
        }

        return melhorGlobal;
    }
}
