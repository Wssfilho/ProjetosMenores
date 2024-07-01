def menor_custo(C, vertices, valores, orcamento):
    n = len(vertices)
    # Inicializa a tabela de DP (Programação Dinâmica) com -1
    tabela = [[-1 for _ in range(orcamento + 1)] for _ in range(n)]
    # Inicializa a matriz 'parent' para rastrear os pais dos vértices no caminho ótimo
    pai = [[-1 for _ in range(orcamento + 1)] for _ in range(n)]

    # Inicializa o lucro do vértice inicial 'a'
    tabela[0][0] = valores[0]

    # Preenchimento da tabela DP
    for origem in range(n):  # Itera sobre todos os vértices 'origem'
        for orcamento_atual in range(orcamento + 1):  # Itera sobre todos os orçamentos possíveis 'orcamento_atual'
            if tabela[origem][orcamento_atual] == -1:
                continue  # Se não há lucro válido para esse estado, continua

            for destino in range(n):  # Itera sobre todos os vértices 'destino'
                if origem != destino:  # Garante que não estamos calculando o custo para o mesmo vértice
                    custo_viagem = C[origem][destino]  # Custo para viajar de 'origem' para 'destino'
                    
                    # Verifica se é possível realizar a viagem sem exceder o orçamento
                    if orcamento_atual + custo_viagem <= orcamento:
                        # Calcula o lucro potencial de chegar em 'destino' a partir de 'origem'
                        lucro_atual = tabela[origem][orcamento_atual] + valores[destino]
                        
                        # Atualiza a tabela DP se o lucro encontrado for maior do que o existente
                        if lucro_atual > tabela[destino][orcamento_atual + custo_viagem]:
                            tabela[destino][orcamento_atual + custo_viagem] = lucro_atual
                            pai[destino][orcamento_atual + custo_viagem] = origem

    # Encontrar o melhor lucro, considerando a volta para 'a'
    melhor_lucro = tabela[0][0]  # Inicializa com o lucro do vértice inicial
    melhor_custo = 0  # Inicializa o custo do caminho ótimo
    melhor_destino_final = 0  # Inicializa o último vértice do caminho ótimo

    for orcamento_atual in range(orcamento + 1):
        if tabela[0][orcamento_atual] >= melhor_lucro:  # Verifica apenas os caminhos que voltam para 'a'
            melhor_lucro = tabela[0][orcamento_atual] - valores[0]  # Inclui o valor da cidade 'a' no lucro
            melhor_custo = orcamento_atual

    # Reconstrução do caminho ótimo
    caminho_otimo = []
    destino_atual = melhor_destino_final
    orcamento_atual = melhor_custo
    while destino_atual != -1:
        caminho_otimo.append(vertices[destino_atual])  # Adiciona o vértice atual ao caminho
        origem_atual = pai[destino_atual][orcamento_atual]  # Obtém o pai do vértice atual no caminho
        if origem_atual == -1:
            break
        orcamento_atual -= C[origem_atual][destino_atual]  # Reduz o orçamento considerando o custo de 'origem_atual' para 'destino_atual'
        destino_atual = origem_atual  # Move para o vértice pai

    #caminho_otimo.reverse()  # Inverte o caminho para obter a ordem correta de 'a' a 'v'
    return melhor_lucro, caminho_otimo, melhor_custo

def main():
    # Definição da matriz de custos C, valores dos vértices, nomes dos vértices e orçamento
    C = [[0, 1, 2, 5],
         [1, 0, 6, 4],
         [2, 6, 0, 4],
         [5, 4, 4, 0]]
    valores = [10, 20, 30, 40]
    vertices = ['a', 'b', 'c', 'd']
    orcamento = 10 # Recebe o valor do orçamento do usuário

    # Chama a função para calcular o caminho ótimo e o lucro máximo
    lucro_maximo, caminho_otimo, custo_caminho = menor_custo(C, vertices, valores, orcamento)
    caminho_completo = caminho_otimo

    # Exibe os resultados
    print("Máximo lucro possível dentro do orçamento:", lucro_maximo)
    print("Melhor caminho:", ' -> '.join(caminho_completo))
    print("Custo:", custo_caminho)

if __name__ == "__main__":
    main()
