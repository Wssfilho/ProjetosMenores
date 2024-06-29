class Grafo:
    def __init__(self, matriz_adj, valores_vertices):
        self.matriz_adj = matriz_adj
        self.valores_vertices = valores_vertices
        self.num_vertices = len(valores_vertices)
        self.melhor_lucro = 0
        self.melhor_rota = []

    def dfs(self, vertice_atual, custo_atual, lucro_atual, vertices_visitados, orçamento):
        if custo_atual > orçamento:
            return

        # Imprimindo as tuplas dos vértices visitados e o valor atual
        vertices_visitados_nomes = [chr(97 + v) for v in vertices_visitados]  # Convertendo índices para letras (a=97)
        print(f"{vertices_visitados_nomes} - Custo: {custo_atual} - Lucro: {lucro_atual}")
        
        # Verificar se todos os vértices foram visitados e podemos voltar ao ponto de partida
        if len(vertices_visitados) == self.num_vertices:
            custo_retorno = self.matriz_adj[vertice_atual][0]  # Custo para retornar ao início
            if custo_atual + custo_retorno <= orçamento:
                lucro_total = lucro_atual + self.valores_vertices[0]  # Adicionar lucro do vértice de início
                if lucro_total > self.melhor_lucro:
                    self.melhor_lucro = lucro_total
                    self.melhor_rota = vertices_visitados[:] + [0]
                print(f"{vertices_visitados_nomes + ['a']} - Custo: {custo_atual + custo_retorno} - Lucro: {lucro_total}")
            return
        
        for vizinho in range(1, self.num_vertices):  # Começar de 1 para evitar revisitar o vértice inicial prematuramente
            if self.matriz_adj[vertice_atual][vizinho] > 0 and vizinho not in vertices_visitados:
                custo_aresta = self.matriz_adj[vertice_atual][vizinho]
                vertices_visitados.append(vizinho)
                lucro_novo = lucro_atual + self.valores_vertices[vizinho]
                self.dfs(vizinho, custo_atual + custo_aresta, lucro_novo, vertices_visitados, orçamento)
                vertices_visitados.pop()

    def explorar_todas_rotas(self, orçamento):
        # Começar pelo vértice 'a' que é o índice 0
        self.dfs(0, 0, self.valores_vertices[0], [0], orçamento)
        # Imprimir a melhor rota encontrada
        melhor_rota_nomes = [chr(97 + v) for v in self.melhor_rota]
        print(f"Maior lucro: {self.melhor_lucro} - Rota: {melhor_rota_nomes}")

# Valores fornecidos
matriz_adj = [
    [0, 1, 2, 5],
    [1, 0, 6, 4],
    [2, 6, 0, 4],
    [5, 4, 4, 0]
]

valores_vertices = [10, 20, 30, 40]
orcamento = 10

grafo = Grafo(matriz_adj, valores_vertices)
grafo.explorar_todas_rotas(orcamento)
