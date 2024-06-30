def menor_custo_dp(custos, vertices, valores, orcamento):
    num_vertices = len(vertices)
    dp = [[[float('-inf'), [], 0] for _ in range(orcamento + 1)] for _ in range(num_vertices)]

    # Inicializa o custo zero para cada vértice
    for v in range(num_vertices):
        dp[v][0] = [0, [vertices[v]], valores[v]]

    for custo_atual in range(1, orcamento + 1):
        for vertice_atual in range(num_vertices):
            for vertice_anterior in range(num_vertices):
                if custos[vertice_anterior][vertice_atual] <= custo_atual:
                    novo_custo = dp[vertice_anterior][custo_atual - custos[vertice_anterior][vertice_atual]][0] + custos[vertice_anterior][vertice_atual]
                    novo_valor = dp[vertice_anterior][custo_atual - custos[vertice_anterior][vertice_atual]][2] 
                    # Verifica se o novo custo não excede o orçamento
                    if novo_custo <= orcamento and novo_valor > dp[vertice_atual][custo_atual][2]:
                        dp[vertice_atual][custo_atual] = ([novo_custo, dp[vertice_anterior][custo_atual - custos[vertice_anterior][vertice_atual]][1] + [vertices[vertice_atual]], novo_valor])
    print(dp)
    # Encontra o melhor resultado dentro do orçamento
    melhor_valor = float('-inf')
    melhor_caminho = []
    for vertice in range(num_vertices):
        for custo in range(orcamento + 1):
            if dp[vertice][custo][0] <= orcamento:
                melhor_valor = dp[vertice][custo][2]
                melhor_caminho = dp[vertice][custo][1]
                melhor_custo = dp[vertice][custo][0]

    return melhor_valor, melhor_caminho, melhor_custo

def main():
    # Exemplo de uso: matriz de custos
    custos = [[0, 1, 2, 5],
              [1, 0, 6, 4],
              [2, 6, 0, 4],
              [5, 4, 4, 0]]
    # Valores de cada vértice
    valores = [10, 20, 30, 40]
    # Nomes dos vértices
    vertices = ['a', 'b', 'c', 'd']
    # Solicita o valor do orçamento ao usuário
    orcamento = int(input("Digite o valor do orçamento: "))

    # Calcula o lucro e o caminho com menor custo
    lucro, caminho, custo = menor_custo_dp(custos, vertices, valores, orcamento)
    # Imprime o lucro máximo possível e o melhor caminho
    print("Máximo lucro possível dentro do orçamento:", lucro)
    print("Melhor caminho:", ' -> '.join(caminho))
    print("Custo:", custo)

if __name__ == "__main__":
    # Chama a função principal
    main()
