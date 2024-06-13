#include<stdio.h>
#define INF 999999
#define N 4

int todos_visitados = (1<<N) - 1;

int dp[1025][N];
int dist[N][N];
int valor[N];
int max_cost = 90;  // Define o custo máximo permitido

int min(int a, int b) {
    if(a < b)
        return a;
    else
        return b;
}

int max(int a, int b) {
    if(a > b)
        return a;
    else
        return b;
}

int tsp(int mascara, int pos, int custo_atual) {

    if(custo_atual > max_cost){
        return -INF;
    }

    if(mascara==todos_visitados){
        return valor[pos];
    }
    if(dp[mascara][pos]!=-1){
       return dp[mascara][pos];
    }

    int retorno = -INF;

    for(int cidade=0;cidade<N;cidade++){

        if((mascara&(1<<cidade))==0){

            int novaResposta = valor[cidade] + tsp( mascara|(1<<cidade), cidade, custo_atual + dist[pos][cidade]);
            retorno = max(retorno, novaResposta);

        }

    }

    return dp[mascara][pos] = retorno;

}

int main(){
   
   dist[0][0] = 0; dist[0][1] = 10; dist[0][2] = 15; dist[0][3] = 20;
   dist[1][0] = 10; dist[1][1] = 0; dist[1][2] = 35; dist[1][3] = 25;
   dist[2][0] = 15; dist[2][1] = 35; dist[2][2] = 0; dist[2][3] = 30;
   dist[3][0] = 20; dist[3][1] = 25; dist[3][2] = 30; dist[3][3] = 0;

   valor[0] = 1; valor[1] = 2; valor[2] = 3; valor[3] = 4;

    for(int i=0;i<(1<<N);i++){
        for(int j=0;j<N;j++){
            dp[i][j] = -1;
        }
    }
    int resultado = tsp(1,0,0);
    if (resultado >= 0) {
        printf("O valor máximo é %d\n", resultado);
    } else {
        printf("Não há solução com o custo máximo de %d\n", max_cost);
    }

    return 0;
}
