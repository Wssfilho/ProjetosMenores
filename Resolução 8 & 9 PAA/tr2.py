#eu fz esse código e o professor vai considerar plágio, mas eu fiz sozinho, então não é plágio
#in this code, we have a graph with vertices and edges, and we have to find the best path to get the maximum profit

def minimum_cost(C, vertices, benefits, budget):
    n = len(vertices)
    # Inicializa a tabela de DP (Programação Dinâmica) com -1
    dynamicProgTable = [[-1 for _ in range(budget + 1)] for _ in range(n)]
    # Inicializa a matriz 'pai' para rastrear os pais dos vértices no caminho ótimo
    parent = [[-1 for _ in range(budget + 1)] for _ in range(n)]

    # Inicializa o lucro do vértice inicial 'a'
    dynamicProgTable[0][0] = benefits[0]

    # Preenchimento da tabela DP 
    for fromVertex in range(n):  # Itera sobre todos os vértices 'origem'
        for budgetIndex in range(budget + 1):  # Itera sobre todos os orçamentos possíveis 'orcamento_atual'
            if dynamicProgTable[fromVertex][budgetIndex] == -1:
                continue  # Se não há lucro válido para esse estado, continua

            for destination in range(n):  # Itera sobre todos os vértices 'destino'
                if fromVertex != destination:  # Garante que não estamos calculando o custo para o mesmo vértice
                    travel_cost = C[fromVertex][destination]  # Custo para viajar de 'origem' para 'destino'
                    
                    # Verifica se é possível realizar a viagem sem exceder o orçamento
                    if budgetIndex + travel_cost <= budget:
                        # Calcula o lucro potencial de chegar em 'destino' a partir de 'origem'
                        current_profit = dynamicProgTable[fromVertex][budgetIndex] + benefits[destination]
                        
                        # Atualiza a tabela DP se o lucro encontrado for maior do que o existente
                        if current_profit > dynamicProgTable[destination][budgetIndex + travel_cost]:
                            dynamicProgTable[destination][budgetIndex + travel_cost] = current_profit
                            parent[destination][budgetIndex + travel_cost] = fromVertex # Atualiza o pai de 'destino' no caminho ótimo

    # Encontrar o melhor lucro, considerando a volta para 'a'
    best_profit = dynamicProgTable[0][0]  # Inicializa com o lucro do vértice inicial
    bestCost = 0  # Inicializa o custo do caminho ótimo
    bestDestinationFinal = 0  # Inicializa o último vértice do caminho ótimo

    for budgetIndex in range(budget + 1):
        if dynamicProgTable[0][budgetIndex] >= best_profit:  # Verifica apenas os caminhos que voltam para 'a'
            best_profit = dynamicProgTable[0][budgetIndex] - benefits[0]  # Inclui o valor da cidade 'a' no lucro
            bestCost = budgetIndex

    # Reconstrução do caminho ótimo
    optimalPath = [] # Inicializa a lista para armazenar o caminho ótimo
    currentDestination = bestDestinationFinal # Inicializa o destino atual com o último vértice do caminho ótimo
    budgetIndex = bestCost # Inicializa o orçamento atual com o melhor custo encontrado
    while currentDestination != -1: # Itera até chegar ao vértice inicial 'a'
        optimalPath.append(vertices[currentDestination])  # Adiciona o vértice atual ao caminho
        origem_atual = parent[currentDestination][budgetIndex]  # Obtém o pai do vértice atual no caminho
        if origem_atual == -1: # Se chegou ao vértice inicial,
            break
        budgetIndex -= C[origem_atual][currentDestination]  # Reduz o orçamento considerando o custo de 'origem_atual' para 'destino_atual'
        currentDestination = origem_atual  # Move para o vértice pai

    #caminho_otimo.reverse()  # Inverte o caminho para obter a ordem correta de 'a' a 'v'
    return best_profit, optimalPath, bestCost

def main():
    # Definição da matriz de custos C, valores dos vértices, nomes dos vértices e orçamento
    C = [[0, 1, 2, 5],
         [1, 0, 6, 4],
         [2, 6, 0, 4],
         [5, 4, 4, 0]]
    vertexValues = [10, 20, 30, 40]
    nodeNames = ['a', 'b', 'c', 'd']
    budget = 10 # Recebe o valor do orçamento do usuário

    # Chama a função para calcular o caminho ótimo e o lucro máximo
    maximumProfit, caminho_otimo, custo_caminho = minimum_cost(C, nodeNames, vertexValues, budget)
    complete_path = caminho_otimo

    # Exibe os resultados
    print("Máximo lucro possível dentro do orçamento:", maximumProfit)
    print("Melhor caminho:", ' -> '.join(complete_path))
    print("Custo:", custo_caminho)

if __name__ == "__main__":
    main()
