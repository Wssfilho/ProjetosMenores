def encontrar_melhor_rota_dp(distancias, custos_cidades, orcamento):
    """
    Encontra a melhor rota que visita a cidade A e outras duas cidades,
    respeitando o orçamento e minimizando o custo total.

    Args:
        distancias: Matriz de distâncias entre as cidades.
        custos_cidades: Lista com os custos de cada cidade.
        orcamento: Orçamento máximo para a viagem.

    Returns:
        Uma tupla contendo:
            - A melhor rota (lista de cidades em letras, ex: ['A', 'B', 'C', 'A']).
            - O menor custo de viagem.
            - O menor custo total (viagem + cidades).
        Se não houver rota válida, retorna None.
    """

    n = len(distancias)
    A = 0  # Representa a cidade A

    # Inicializa uma tabela DP para armazenar os menores custos de viagem com orçamentos restantes
    dp = [[[float('inf'), -1, -1, -1] for _ in range(orcamento + 1)] for _ in range(n)]

    # Inicializa a cidade A (índice 0)
    dp[A][0] = [0, -1, -1, -1]

    # Preenchimento da tabela dp
    for b in range(1, n):
        for c in range(1, n):
            for d in range(1, n):
                if b != c != d:
                    for orcamento_restante in range(orcamento):
                        if orcamento_restante > distancias[A][b] + distancias[A][c] + distancias[A][d]:
                            custo_viagem = distancias[A][b] + distancias[A][c] + distancias[A][d]
                            custo_total = custo_viagem + custos_cidades[b] + custos_cidades[c] + custos_cidades[A]
                            if custo_total < dp[c][orcamento_restante][0]:
                                dp[c][orcamento_restante] = [custo_total, A, b, c, d]

    # Encontrar o menor custo total dentro do orçamento
    menor_custo_total = float('inf')
    melhor_rota = None
    for orcamento_restante in range(orcamento + 1):
        for c in range(1, n):
            if dp[c][orcamento_restante][0] < menor_custo_total:
                menor_custo_total = dp[c][orcamento_restante][0]
                melhor_rota = dp[c][orcamento_restante][1:]

    if melhor_rota is None:
        return None  # Não há rota válida

    # Reconstruir a rota
    rota_indices = [melhor_rota[0], melhor_rota[1], melhor_rota[2], melhor_rota[0]]
    melhor_rota_letras = [chr(cidade + ord('A')) for cidade in rota_indices]

    # O menor custo de viagem é o custo da viagem sem considerar os custos das cidades
    menor_custo_viagem = distancias[melhor_rota[0]][melhor_rota[1]] + distancias[melhor_rota[1]][melhor_rota[2]] + distancias[melhor_rota[2]][melhor_rota[0]]

    return melhor_rota_letras, menor_custo_viagem, menor_custo_total


# Dados do problema
distancias = [
    [0, 1, 2, 5],  # Distâncias da cidade A para as outras
    [1, 0, 6, 4],  # Distâncias da cidade B para as outras
    [2, 6, 0, 4],  # Distâncias da cidade C para as outras
    [5, 4, 4, 0]   # Distâncias da cidade D para as outras
]
custos_cidades = [10, 20, 30, 40]  # Custos de cada cidade
orcamento = 10  # Orçamento máximo para a viagem

# Encontrar a melhor rota
resultado_dp = encontrar_melhor_rota_dp(distancias, custos_cidades, orcamento)

# Imprimir o resultado
if resultado_dp:
    print("Melhor Rota (com programação dinâmica):", resultado_dp[0])
    print("Custo Mínimo de Viagem:", resultado_dp[1])
    print("Custo Total (com cidades):", resultado_dp[2])
else:
    print("Não há rota que satisfaça o orçamento.")
