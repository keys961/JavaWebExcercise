#include <stdio.h>

int main()
{
    int r, n;
    scanf("%d", &r);
    for(int i = 0; i < r; i++)
    {
        scanf("%d", &n);
        for(int j = 0; j < n; j++)
            printf("%d ", j);
        printf("%d\n", n);
    }
    return 0;
}