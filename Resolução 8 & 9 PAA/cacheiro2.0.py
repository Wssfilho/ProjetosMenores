def TSP_com_orcamento(distancias, custos_cidades, orcamento):
    n = len(distancias)
    memo = {}

    def visitar_cidade(visitadas, ultima, custo_atual):
        if (tuple(visitadas), ultima) in memo:
            return memo[(tuple(visitadas), ultima)]
        if len(visitadas) == n:
            return (custo_atual, [ultima])
        
        melhor_custo = float('inf')
        melhor_caminho = []
        for prox in range(n):
            if prox not in visitadas:
                novo_custo = custo_atual + distancias[ultima][prox] + custos_cidades[prox]
                if novo_custo <= orcamento:
                    novo_visitadas = visitadas | {prox}
                    custo, caminho = visitar_cidade(novo_visitadas, prox, novo_custo)
                    if custo < melhor_custo:
                        melhor_custo = custo
                        melhor_caminho = [ultima] + caminho
        
        memo[(tuple(visitadas), ultima)] = (melhor_custo, melhor_caminho)
        return memo[(tuple(visitadas), ultima)]

    melhor_custo_global, melhor_caminho_global = float('inf'), []
    for cidade_inicial in range(n):
        custo, caminho = visitar_cidade({cidade_inicial}, cidade_inicial, custos_cidades[cidade_inicial])
        if custo < melhor_custo_global:
            melhor_custo_global = custo
            melhor_caminho_global = caminho

    if melhor_custo_global <= orcamento:
        print(f"Rota: {melhor_caminho_global}")
        print(f"Custo Total (com cidades): {melhor_custo_global}")
    else:
        print(f"Rota: {melhor_caminho_global}")
        print(f"Custo Total (com cidades): {melhor_custo_global}")

# Exemplo de entrada
distancias = [
    [0, 1, 2, 5],
    [1, 0, 6, 4],
    [2, 6, 0, 4],
    [5, 4, 4, 0]
]
custos_cidades = [10, 20, 30, 40]
orcamento = 10

# Chamada da função
TSP_com_orcamento(distancias, custos_cidades, orcamento)