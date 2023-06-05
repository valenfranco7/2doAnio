#include stdio.h
#include stdlib.h
#include time.h
#define DEBUG 1   cambiar a 0 para desactivar la depuración

#define SIZE 1000

int encontrar(int array, int size, int num) {
    int count = 0;
    for (int i = 0; i  size; i++) {
        count++;
        if (array[i] == num) {
            #if DEBUG
            printf(Se accedio al arreglo %d veces.n, count);
            #endif
            return i;
        }
    }
    #if DEBUG
    printf(Se accedio al arreglo %d veces.n, count);
    #endif
    return -1;
}

int main() {
    srand(time(NULL));

    int arr[SIZE];
    for (int i = 0; i  SIZE; i++) {
        arr[i] = rand() % 100 + 1;
    }

    int numero = rand() % 100 + 1;
    printf(Buscando el numero %d en el arreglo...n, numero);

    int position = encontrar(arr, SIZE, numero);
    if (position == -1) {
        printf(El numero %d no se encuentra en el arreglo.n, numero);
    } else {
        printf(El numero %d se encuentra en la posición %d del arreglo.n, numero, position);
    }

    return 0;
}
