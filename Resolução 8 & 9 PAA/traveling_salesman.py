def menor_custo(C, vertices, valores, orcamento):
    n = len(vertices)
    dp = [[-1 for _ in range(orcamento + 1)] for _ in range(n)]
    parent = [[-1 for _ in range(orcamento + 1)] for _ in range(n)]
    
    # Inicialização: custo zero para o vértice inicial (vertice 'a')
    dp[0][0] = valores[0]
    
    # Preenchendo a tabela dp
    for u in range(n):
        for b in range(orcamento + 1):
            if dp[u][b] == -1:
                continue
            for v in range(n):
                if u != v:
                    custo = C[u][v]
                    if b + custo <= orcamento:
                        lucro = dp[u][b] + valores[v]
                        if lucro > dp[v][b + custo]:
                            dp[v][b + custo] = lucro
                            parent[v][b + custo] = u
    print(dp)
    # Encontrar o melhor lucro possível
    melhor_lucro = 0
    melhor_custo = 0
    melhor_vertice_final = -1
    for b in range(orcamento + 1):
        if dp[0][b] >= melhor_lucro:
            melhor_lucro = dp[0][b]
            melhor_custo = b
            melhor_vertice_final = 0
    
    # Reconstrução do caminho
    caminho = []
    b = melhor_custo
    v = melhor_vertice_final
    while v != -1:
        caminho.append(vertices[v])
        u = parent[v][b]
        if u == -1:
            break
        b -= C[u][v]
        v = u
    caminho.reverse()
    
    return melhor_lucro, caminho, melhor_custo

def main():
    # Exemplo de uso
    C = [[0, 1, 2, 5],
         [1, 0, 6, 4],
         [2, 6, 0, 4],
         [5, 4, 4, 0]]
    valores = [10, 20, 30, 40]
    vertices = ['a', 'b', 'c', 'd']
    orcamento = int(input("Digite o valor do orçamento: "))

    lucro, caminho, custo = menor_custo(C, vertices, valores, orcamento)
    caminho_completo = caminho + ['a']  # Caminho incluindo a volta para 'a'
    print("Máximo lucro possível dentro do orçamento:", lucro)
    print("Melhor caminho:", ' -> '.join(caminho_completo))
    print("Custo:", custo)

if __name__ == "__main__":
    main()
