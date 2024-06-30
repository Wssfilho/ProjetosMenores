def menor_custo(C, vertices, valores, orcamento):
    r = range(len(C))
    paths = {(vertices[i], vertices[j]): C[i][j] for i in r for j in r}
    
    melhor_lucro = 0
    melhor_caminho = []
    melhor_custo = float('inf')

    for i in range(1, len(vertices)):
        print()
        vertice_inicial = vertices[i] #verticie recebe 1
        caminhos = [[vertice_inicial]]
        custos = [paths[(vertices[0], vertice_inicial)]]
        lucros = [valores[vertices.index(vertice_inicial)]]
        
        while caminhos:
            novo_caminho = []
            novo_custo = []
            novo_lucro = []
            for j, caminho in enumerate(caminhos):
                custo_atual = custos[j]
                lucro_atual = lucros[j]
                print(f'Caminho: {caminho}, Custo: {custo_atual}, Lucro: {lucro_atual}')
                
                custo_total = custo_atual + paths[(vertices[0], caminho[0])]
                if custo_total <= orcamento:
                    lucro = valores[0] + sum(valores[vertices.index(v)] for v in caminho)
                    if lucro > melhor_lucro or (lucro == melhor_lucro and custo_total < melhor_custo):
                        melhor_lucro = lucro
                        melhor_caminho = caminho
                        melhor_custo = custo_total
                
                for k in range(1, len(vertices)):
                    vertice = vertices[k]
                    if vertice not in caminho:
                        custo = paths[(vertice, caminho[0])] + custo_atual
                        if custo <= orcamento:
                            novo_caminho.append([vertice] + caminho)
                            novo_custo.append(custo)
                            
                            lucro = lucro_atual + valores[vertices.index(vertice)]
                            novo_lucro.append(lucro)
                            
            caminhos = novo_caminho
            custos = novo_custo
            lucros = novo_lucro
        print()
    return melhor_lucro, melhor_caminho, melhor_custo

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
    caminho_completo = ['a'] + caminho + ['a']  # Caminho incluindo a volta para 'a'
    print("Máximo lucro possível dentro do orçamento:", lucro)
    print("Melhor caminho:", ' -> '.join(caminho_completo))
    print("Custo:", custo)

if __name__ == "__main__":
    main()
