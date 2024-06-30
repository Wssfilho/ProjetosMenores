def menor_custo_dp(custos, V_ertices, valores, orcamento):
    """
    Encontra o caminho de maior lucro em um grafo, respeitando um orçamento.

    Args:
        custos: Matriz de custos entre vértices.
        V_ertices: Lista de nomes dos vértices.
        valores: Lista de valores (lucros) associados a cada vértice.
        orcamento: Limite máximo de custo permitido.

    Returns:
        Tupla contendo o lucro máximo, o melhor caminho e o custo do melhor caminho.
    """
    num_vertices = len(V_ertices)
    # Tabela DP para armazenar resultados intermediários (lucro e caminho)
    dp = [[[float('inf'), []] for i in range(orcamento + 1)] for _ in range(num_vertices)]  
    # Inicializa lucro zero para custo zero em todos os vértices
    for v in range(num_vertices): 
        dp[v][0][0] = 0

    # Itera sobre todos os custos e vértices
    for custo_atual in range(1, orcamento + 1):
        for vertice_atual in range(num_vertices):
            # Verifica todos os vértices anteriores
            for vertice_anterior in range(num_vertices):
                if custos[vertice_anterior][vertice_atual] <= custo_atual:
                    # Calcula o novo lucro possível
                    novo_custo = dp[vertice_anterior][custo_atual - custos[vertice_anterior][vertice_atual]][0] + valores[vertice_atual]
                    # Atualiza se o novo lucro for maior
                    if novo_custo <= dp[vertice_atual][custo_atual][0] and min(dp[vertice_atual][custo_atual][0], novo_custo) != float('inf'): 
                        dp[vertice_atual][custo_atual][0] = novo_custo
                        # Armazena o novo caminho
                        dp[vertice_atual][custo_atual][1] = dp[vertice_anterior][custo_atual - custos[vertice_anterior][vertice_atual]][1] + [V_ertices[vertice_atual]]  
    # Encontra o melhor caminho que termina no vértice inicial (0)
    melhor_lucro = float('-inf')
    melhor_caminho = []
    custo_melhor_caminho = 0
    for vertice in range(num_vertices):
        if vertice != 0 and custos[vertice][0] <= orcamento:
            lucro_total = dp[vertice][orcamento - custos[vertice][0]][0] + valores[0]
            if lucro_total > melhor_lucro:
                melhor_lucro = lucro_total
                melhor_caminho = [V_ertices[0]] + dp[vertice][orcamento - custos[vertice][0]][1] + [V_ertices[0]]
                custo_melhor_caminho = orcamento - custos[vertice][1]

    return melhor_lucro, melhor_caminho, custo_melhor_caminho

def main():
    # Dados de exemplo
    custos = [[0, 1, 2, 5], [1, 0, 6, 4], [2, 6, 0, 4], [5, 4, 4, 0]]
    valores = [10, 20, 30, 40]
    vertices = ['a', 'b', 'c', 'd']
    orcamento = 10

    # Chama a função para encontrar a solução
    lucro, caminho, custo_caminho = menor_custo_dp(custos, vertices, valores, orcamento)
    
    # Imprime os resultados
    print("Máximo lucro possível dentro do orçamento:", lucro)
    print("Melhor caminho:", ' -> '.join(caminho))
    print("Custo do melhor caminho:", custo_caminho)

if __name__ == "__main__":
    main()
