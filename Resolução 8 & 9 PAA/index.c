#include <stdio.h>
#include <stdlib.h>

void inteiro(int *a)
{
    a++;
}

int main(void)
{
    int b;
    b = 4;
    printf("%d\n", b);
    printf("ola mundo");
    scanf("%d", &b);
    inteiro(&b);
    printf("%d", b);
}