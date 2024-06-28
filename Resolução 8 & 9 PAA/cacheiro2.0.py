# Importa a função permutations do módulo itertools para gerar permutações de cidades
from itertools import permutations

# Define a função encontrar_melhor_rota que calcula a melhor rota dado um orçamento
def encontrar_melhor_rota(distancias, custos_cidades, orcamento):
    # Número de cidades baseado no tamanho da matriz de distâncias
    n = len(distancias)
    # Tabela para armazenar os custos mínimos de viagem entre cidades usando programação dinâmica
    tabela = [[float('inf')] * n for _ in range(n)]
    # Define o custo inicial para chegar à primeira cidade como zero
    tabela[1][0] = 0

    # Função interna para gerar todos os estados possíveis de visitação das cidades
    def gerar_estados(n, estado_atual=[], pos=0):
        # Caso base: se todas as cidades foram consideradas, retorna o estado atual
        if pos == n:
            return [estado_atual]
        estados = []
        # Gera estados onde a cidade atual não é visitada
        estados.extend(gerar_estados(n, estado_atual + [False], pos + 1))
        # Gera estados onde a cidade atual é visitada
        estados.extend(gerar_estados(n, estado_atual + [True], pos + 1))
        return estados

    # Gera todos os estados possíveis de visitação
    estados = gerar_estados(n)

    # Mapeia cada estado para um índice único para facilitar o acesso
    estado_para_indice = {tuple(estado): idx for idx, estado in enumerate(estados)}

    # Inicializa a tabela de custos mínimos com infinito para todos os estados e cidades
    tabela = [[float('inf')] * n for _ in estados]

    # Itera sobre todos os estados possíveis
    for estado in estados:
        estado_idx = estado_para_indice[tuple(estado)]
        # Itera sobre todas as cidades para o estado atual
        for cidade_atual in range(n):
            if estado[cidade_atual]:  # Se a cidade atual foi visitada
                # Itera sobre todas as cidades possíveis para visitar a seguir
                for prox_cidade in range(n):
                    if not estado[prox_cidade]:  # Se a próxima cidade não foi visitada
                        novo_estado = list(estado)
                        novo_estado[prox_cidade] = True  # Marca a próxima cidade como visitada
                        novo_estado_idx = estado_para_indice[tuple(novo_estado)]
                        # Atualiza o custo mínimo para o novo estado
                        tabela[novo_estado_idx][prox_cidade] = min(tabela[novo_estado_idx][prox_cidade], tabela[estado_idx][cidade_atual] + distancias[cidade_atual][prox_cidade])

    # Lista para armazenar as rotas válidas dentro do orçamento
    rotas_validas = []

    # Gera permutações de todas as cidades exceto a primeira para encontrar rotas possíveis
    for perm in permutations(range(1, n), 2):
        # Constrói a rota adicionando a primeira cidade no início e no fim
        rota = [0] + list(perm) + [0]
        # Calcula o custo da viagem somando as distâncias entre as cidades consecutivas na rota
        custo_viagem = sum(distancias[rota[i]][rota[i + 1]] for i in range(len(rota) - 1))
        # Calcula o custo total incluindo o custo de visitação das cidades
        custo_cidades = sum(custos_cidades[cidade] for cidade in rota if cidade != 0)
        # Adiciona o custo da primeira cidade ao custo total
        custo_total = custo_cidades + custos_cidades[0]

        # Se o custo da viagem está dentro do orçamento, adiciona a rota à lista de rotas válidas
        if custo_viagem <= orcamento:
            rotas_validas.append((rota, custo_viagem, custo_total))

    # Ordena as rotas válidas pelo custo total de forma decrescente
    rotas_validas.sort(key=lambda x: x[2], reverse=True)

    # Encontra a rota com o maior custo total que ainda está dentro do orçamento
    for rota, custo_viagem, custo_total in rotas_validas:
        if custo_viagem <= orcamento:
            # Converte os índices das cidades para letras para melhor visualização
            rota_letras = [chr(cidade + ord('A')) for cidade in rota]
            # Imprime a rota, o custo mínimo da viagem e o custo total
            print("Rota:", rota_letras)
            print("Custo mínimo", custo_viagem)
            print("Custo Total (com cidades):", custo_total)
            break  # Interrompe após encontrar a primeira rota válida

    # Retorna a lista de rotas válidas
    return rotas_validas

# Dados de exemplo para testar a função
distancias = [
    [0, 1, 2, 5],
    [1, 0, 6, 4],
    [2, 6, 0, 4],
    [5, 4, 4, 0]
]
custos_cidades = [10, 20, 30, 40]
orcamento = 10

# Chama a função com os dados de exemplo e imprime as rotas válidas
encontrar_melhor_rota(distancias, custos_cidades, orcamento)