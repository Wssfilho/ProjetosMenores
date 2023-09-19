# Definição da estrutura de dados da fila
class Fila:
    def __init__(self):
        self.fila = []

    def enfileirar(self, valor):
        self.fila.append(valor)

    def desenfileirar(self):
        if not self.fila:
            return None

        maior_valor = self.fila[0]
        for i in range(1, len(self.fila)):
            if self.fila[i] > maior_valor:
                maior_valor = self.fila[i]

        self.fila.remove(maior_valor)
        return maior_valor


# Implementação do algoritmo
def main():
    # Leitura da entrada
    entrada = input()
    while entrada != '-':
        # Se for uma operação de enfileiramento
        if entrada[0] == 'E':
            valor = entrada[1]
            # Enfileirar o valor
            fila.enfileirar(valor)

        # Se for uma operação de desenfileiramento
        else:
            # Desenfileirar o valor de maior valor
            print(fila.desenfileirar())

        # Leitura da próxima operação
        entrada = input()


# Início da execução
fila = Fila()
main()
