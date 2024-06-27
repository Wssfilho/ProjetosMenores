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
    tabela = [[float('inf')] * n for _ in range(n)]  # Tabela para programação dinâmica
    tabela[1][0] = 0  # Custo inicial para chegar à cidade A é zero

    # Gerar tabelas
    def gerar_estados(n, estado_atual=[], pos=0):
        if pos == n:
            return [estado_atual]
        estados = []
        # Cidade não visitada
        estados.extend(gerar_estados(n, estado_atual + [False], pos + 1))
        # Cidade visitada
        estados.extend(gerar_estados(n, estado_atual + [True], pos + 1))
        return estados

    # Inicializa os estados de visitação
    estados = gerar_estados(n)

    # Mapeia cada estado para um índice
    estado_para_indice = {tuple(estado): idx for idx, estado in enumerate(estados)}

    # Inicializa a tabela de custos mínimos
    tabela = [[float('inf')] * n for _ in estados]

    # Itera sobre todos os estados de visitação
    for estado in estados:
        estado_idx = estado_para_indice[tuple(estado)]
        for cidade in range(n):  # Para cada cidade 'u'
            if estado[cidade]:  # Se a cidade foi visitada no estado atual
                for cidade2 in range(n):  # Para cada cidade 
                    if not estado[cidade2]:  # Se a cidade não foi visitada
                        novo_estado = list(estado)
                        novo_estado[cidade2] = True  # Marca como visitada
                        novo_estado_idx = estado_para_indice[tuple(novo_estado)]
                        # Atualiza o custo mínimo para chegar a no novo estado
                        tabela[novo_estado_idx][cidade2] = min(tabela[novo_estado_idx][cidade2], tabela[estado_idx][cidade] + distancias[cidade][cidade2])
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
