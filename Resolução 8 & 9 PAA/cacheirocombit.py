def encontrar_melhor_rota(distancias, custos_cidades, orcamento):
    """
    Encontra as melhores rotas que visitam a cidade A e outras duas cidades, respeitando o orçamento.

    Args:
        distancias: Matriz de distâncias entre as cidades.
        custos_cidades: Lista com os custos de cada cidade.
        orcamento: Orçamento máximo para a viagem.

    Returns:
        Uma lista de tuplas (rota, custo_viagem, custo_total) ordenadas por custo total.
        Cada rota é uma lista de cidades (em letras) e os custos são valores numéricos.
        Retorna uma lista vazia se não houver rotas válidas.
    """

    from itertools import permutations  # Para gerar permutações de cidades

    n = len(distancias)  # Número de cidades
    #all_visited = (1 << n) - 1  # Representação binária para todas as cidades visitadas
    tabela = [[float('inf')] * n for _ in range(1 << n)]  # Tabela para programação dinâmica
    tabela[1][0] = 0  # Custo inicial para chegar à cidade A é zero

    # Programação Dinâmica para calcular custos mínimos
    for ponto in range(1 << n):  # Itera sobre todos os estados de visitação
        for cidade in range(n):  # Para cada cidade 'u'
            if ponto & (1 << cidade):  # Se a cidade 'u' foi visitada no estado atual
                for cidade2 in range(n):  # Para cada cidade 'v'
                    if not ponto & (1 << cidade2):  # Se a cidade 'v' não foi visitada
                        n_point = ponto | (1 << cidade2)  # Novo estado com 'v' visitada
                        # Atualiza o custo mínimo para chegar a 'v' no novo estado
                        tabela[n_point][cidade2] = min(tabela[n_point][cidade2], tabela[ponto][cidade] + distancias[cidade][cidade2])

    rotas_validas = []  # Lista para armazenar as rotas válidas

    # Geração e Filtragem de Rotas
    for perm in permutations(range(1, n), 2):  # Gera permutações de duas cidades (excluindo A)
        rota = [0] + list(perm) + [0]  # Adiciona a cidade A no início e no fim da rota
        custo_viagem = sum(distancias[rota[i]][rota[i + 1]] for i in range(len(rota) - 1))  # Calcula o custo da viagem
        custo_cidades = sum(custos_cidades[cidade] for cidade in rota if cidade != 0)  # Calcula o custo das cidades
        #inclua o custo da cidade a no custo total somado com o custo cidades
        custo_total = custo_cidades + custos_cidades[0] # Calcula o custo total (de todas as cidades até o retorno da cidade A)

        if custo_viagem <= orcamento:  # Verifica se a rota respeita o orçamento
            rotas_validas.append((rota, custo_viagem, custo_total))  # Adiciona a rota válida

    rotas_validas.sort(key=lambda x: x[2])  # Ordena as rotas pelo custo total
    # Impressão das Rotas Válidas
    for rota, custo_viagem, custo_total in rotas_validas:
        rota_letras = [chr(cidade + ord('A')) for cidade in rota]  # Converte os números das cidades em letras
        print("Rota:", rota_letras)
        print("Custo de Viagem:", custo_viagem)
        print("Custo Total (com cidades):", custo_total)
        print("----")

    return rotas_validas  # Retorna a lista de rotas válidas


# Dados do problema (exemplo)
distancias = [
    [0, 1, 2, 5],
    [1, 0, 6, 4],
    [2, 6, 0, 4],
    [5, 4, 4, 0]
]
custos_cidades = [10, 20, 30, 40]
orcamento = 10

# Encontra e imprime as rotas válidas
encontrar_melhor_rota(distancias, custos_cidades, orcamento)
