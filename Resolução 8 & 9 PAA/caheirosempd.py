def encontrar_melhor_rota(distancias, custos_cidades, orcamento):
    """
    Encontra a melhor rota que visita a cidade A e outras duas cidades, 
    respeitando o orçamento e minimizando o custo total.

    Args:
        distancias: Matriz de distâncias entre as cidades.
        custos_cidades: Lista com os custos de cada cidade.
        orcamento: Orçamento máximo para a viagem.

    Returns:
        Uma tupla contendo:
            - A melhor rota (lista de cidades em letras, ex: ['A', 'B', 'C', 'A']).
            - O menor custo de viagem.
            - O menor custo total (viagem + cidades).
        Se não houver rota válida, retorna None.
    """

    melhor_rota = None  # Inicializa a melhor rota como None
    menor_custo_viagem = float('inf')  # Inicializa o menor custo de viagem como infinito
    menor_custo_total = float('inf')    # Inicializa o menor custo total como infinito

    # Itera sobre todas as combinações possíveis de duas cidades (excluindo a cidade A)
    for cidade1 in range(1, len(distancias)):
        for cidade2 in range(1, len(distancias)):
            if cidade1 != cidade2:  # Evita rotas com a mesma cidade duas vezes
                rota = [0, cidade1, cidade2, 0]  # 0 representa a cidade A (início e fim)

                # Calcula o custo da viagem somando as distâncias entre as cidades da rota
                custo_viagem = sum(distancias[rota[i]][rota[i + 1]] for i in range(len(rota) - 1))

                # Calcula o custo das cidades visitadas (excluindo a cidade A)
                custo_cidades = custos_cidades[cidade1] + custos_cidades[cidade2]

                # Calcula o custo total da viagem (viagem + cidades)
                custo_total = custos_cidades[0] + custo_cidades

                # Verifica se a rota é válida (custo de viagem dentro do orçamento) e se é melhor que a atual
                if custo_viagem <= orcamento and (
                    custo_total < menor_custo_total or 
                    (custo_total == menor_custo_total and custo_viagem < menor_custo_viagem)  # Desempate pelo custo de viagem
                ):
                    menor_custo_viagem = custo_viagem
                    menor_custo_total = custo_total
                    melhor_rota = [chr(cidade + ord('A')) for cidade in rota]  # Converte a rota para letras

    return melhor_rota, menor_custo_viagem, menor_custo_total  # Retorna a melhor rota, o menor custo de viagem e o menor custo total


# Dados do problema
distancias = [
    [0, 1, 2, 5],  # Distâncias da cidade A para as outras
    [1, 0, 6, 4],  # Distâncias da cidade B para as outras
    [2, 6, 0, 4],  # Distâncias da cidade C para as outras
    [5, 4, 4, 0]   # Distâncias da cidade D para as outras
]
custos_cidades = [10, 20, 30, 40]  # Custos de cada cidade
orcamento = 10  # Orçamento máximo para a viagem

# Encontrar a melhor rota
resultado = encontrar_melhor_rota(distancias, custos_cidades, orcamento)

# Imprimir o resultado
if resultado[0]:  # Verifica se encontrou uma rota válida
    print("Melhor Rota:", resultado[0])
    print("Custo Mínimo de Viagem:", resultado[1])
    print("Custo Total (com cidades):", resultado[2])
else:
    print("Não há rota que satisfaça o orçamento.")
