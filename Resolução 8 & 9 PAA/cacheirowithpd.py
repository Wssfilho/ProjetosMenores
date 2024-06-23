def encontrar_melhor_rota(distancias, orcamento):
    n = len(distancias)
    inf = float('inf')

    # Inicializa a tabela de memoização
    dp = [[inf] * (1 << n) for _ in range(n)]
    dp[0][1] = 0  # Custo para visitar apenas a cidade A

    # Itera sobre todas as máscaras
    for mask in range(1, 1 << n):
        for cidade in range(n):
            if mask & (1 << cidade):  # Verifica se a cidade está na máscara
                for proxima_cidade in range(n):
                    if proxima_cidade != cidade and mask & (1 << proxima_cidade):
                        nova_mascara = mask ^ (1 << proxima_cidade)
                        dp[cidade][mask] = min(dp[cidade][mask], distancias[cidade][proxima_cidade] + dp[proxima_cidade][nova_mascara])

    # Encontra a melhor rota
    melhor_rota = []
    menor_custo_total = inf
    for cidade in range(1, n):
        custo_viagem = distancias[cidade][0] + dp[cidade][(1 << n) - 1]
        if custo_viagem <= orcamento and custo_viagem < menor_custo_total:
            menor_custo_total = custo_viagem
            melhor_rota = []
            mask = (1 << n) - 1
            while mask:
                for proxima_cidade in range(n):
                    if mask & (1 << proxima_cidade) and dp[cidade][mask] == distancias[cidade][proxima_cidade] + dp[proxima_cidade][mask ^ (1 << proxima_cidade)]:
                        melhor_rota.append(chr(cidade + ord('A')))
                        mask ^= (1 << cidade)
                        cidade = proxima_cidade
                        break
            melhor_rota.append('A')
            melhor_rota.reverse()

    return melhor_rota, menor_custo_total

# Dados do problema
distancias = [
    [0, 1, 2, 5],  # Distâncias da cidade A para as outras
    [1, 0, 6, 4],  # Distâncias da cidade B para as outras
    [2, 6, 0, 4],  # Distâncias da cidade C para as outras
    [5, 4, 4, 0]   # Distâncias da cidade D para as outras
]
orcamento = 10  # Orçamento máximo para a viagem

# Encontrar a melhor rota
resultado = encontrar_melhor_rota(distancias, orcamento)

# Imprimir o resultado

print("Melhor Rota:", resultado[0])
print("Custo Mínimo de Viagem:", resultado[1])
