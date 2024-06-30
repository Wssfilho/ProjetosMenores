def menor_custo_dp(custos, V_ertices, valores, orcamento):
    # Obtém o número de vértices
    num_vertices = len(V_ertices)
    
    # Inicializa a tabela dp com infinito e caminhos vazios
    dp = [[[float('inf'), []] for i in range(orcamento + 1)] for _ in range(num_vertices)]

    # Base: custo zero, caminho vazio
    for v in range(num_vertices):
        dp[v][0][0] = 0

    # Itera sobre cada valor de orçamento de 1 até o orçamento total
    for custo_atual in range(1, orcamento + 1):
        for vertice_atual in range(num_vertices):
            for vertice_anterior in range(num_vertices):
                # Verifica se há um caminho de vertice_anterior para vertice_atual com custo dentro do orçamento
                if custos[vertice_anterior][vertice_atual] <= custo_atual:
                    # Calcula o novo custo para chegar em vertice_atual via vertice_anterior
                    novo_custo = dp[vertice_anterior][custo_atual - custos[vertice_anterior][vertice_atual]][0] + valores[vertice_atual]
                    #novo_valor = dp[vertice_anterior][custo_atual - custos[vertice_anterior][vertice_atual]][1] + custos[vertice_anterior][vertice_atual]
                    
                    # Atualiza dp se o novo custo for menor
                    if novo_custo <= dp[vertice_atual][custo_atual][0]:
                        dp[vertice_atual][custo_atual][0] = novo_custo
                        dp[vertice_atual][custo_atual][1] = dp[vertice_anterior][orcamento - custos[vertice_anterior][vertice_atual]][1] + [V_ertices[vertice_atual]]
                    

    
    # Encontra o melhor caminho de volta ao vértice inicial 'a'
    melhor_lucro = float(30)
    melhor_caminho = []
    for vertice in range(num_vertices):
        # Verifica se a volta para 'a' é possível
        if vertice != 0 and custos[vertice][0] <= orcamento:
            # Calcula o lucro total
            lucro_total = dp[vertice][orcamento - custos[vertice][0]][0] + valores[0]
            # Atualiza se encontrar um lucro melhor
            if lucro_total > melhor_lucro:
                melhor_lucro = lucro_total
                melhor_caminho = dp[vertice][orcamento - custos[vertice][0]][1] + [V_ertices[0]]  # Inclui 'a' no final
    print(melhor_caminho)
    # Retorna o melhor lucro e o melhor caminho
    return melhor_lucro, melhor_caminho

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
    orcamento = 10

    # Calcula o lucro e o caminho com menor custo
    lucro, caminho = menor_custo_dp(custos, vertices, valores, orcamento)
    # Monta o caminho completo incluindo a volta para 'a'
    caminho_completo = caminho 
    # Imprime o lucro máximo possível e o melhor caminho
    print("Máximo lucro possível dentro do orçamento:", lucro)
    print("Melhor caminho:", ' -> '.join(caminho_completo))

if __name__ == "__main__":
    # Chama a função principal
    main()
