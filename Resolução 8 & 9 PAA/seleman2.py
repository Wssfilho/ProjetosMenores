def caixeiro_viajante_dp(custos, valores, orcamento):
    """
    Encontra o caminho de maior valor total em um grafo, respeitando um orçamento.

    Args:
        custos: Matriz de custos entre vértices.
        valores: Lista de valores associados a cada vértice.
        orcamento: Limite máximo de custo permitido.

    Returns:
        Tupla contendo o valor total máximo, o melhor caminho e o custo do melhor caminho.
    """
    num_vertices = len(valores)

    # Tabela DP para armazenar resultados intermediários (valor total e caminho)
    dp = [[[0, []] for _ in range(orcamento + 1)] for _ in range(1 << num_vertices)]

    # Itera sobre todos os subconjuntos de vértices e orçamentos
    for mask in range(1 << num_vertices):
        for custo_atual in range(orcamento + 1):
            # Verifica todos os vértices finais possíveis
            for vertice_final in range(num_vertices):
                if mask & (1 << vertice_final):  # Verifica se o vértice está no subconjunto
                    prev_mask = mask ^ (1 << vertice_final)  # Remove o vértice final
                    for vertice_anterior in range(num_vertices):
                        if prev_mask & (1 << vertice_anterior) and custos[vertice_anterior][vertice_final] <= custo_atual:
                            # Calcula o novo valor total possível
                            novo_valor = dp[prev_mask][custo_atual - custos[vertice_anterior][vertice_final]][0] + valores[vertice_final]
                            # Atualiza se o novo valor for maior
                            if novo_valor > dp[mask][custo_atual][0]:
                                dp[mask][custo_atual][0] = novo_valor
                                dp[mask][custo_atual][1] = dp[prev_mask][custo_atual - custos[vertice_anterior][vertice_final]][1] + [vertice_final]

    # Encontra o melhor caminho que visita todos os vértices
    melhor_valor = dp[(1 << num_vertices) - 1][orcamento][0]
    melhor_caminho = [i for i in dp[(1 << num_vertices) - 1][orcamento][1]]
    custo_melhor_caminho = orcamento

    return melhor_valor, melhor_caminho, custo_melhor_caminho

# Exemplo de uso
custos = [[0, 1, 2, 5], [1, 0, 6, 4], [2, 6, 0, 4], [5, 4, 4, 0]]
valores = [10, 20, 30, 40]
orcamento = 10

valor_total, caminho, custo_caminho = caixeiro_viajante_dp(custos, valores, orcamento)

print("Valor total máximo:", valor_total)
print("Melhor caminho:", caminho)  # Imprime os índices dos vértices
print("Custo do melhor caminho:", custo_caminho)
