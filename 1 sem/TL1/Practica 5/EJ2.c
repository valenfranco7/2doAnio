#include <stdio.h>
#include <stdlib.h>
/*Escriba un programa que procese un archivo de texto e informe la cantidad de caracteres
minúsculas, mayúsculas y dígitos que posee.
 */


#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE* arch; //archivo a file
    int minus,mayus,dig;
    minus=0;;
    mayus=0;
    dig=0;
    char c;
    arch= fopen("C:/Users/val3n/Downloads/ArchivoD.txt", "r"); //lo abro para lecutra
    if (arch==NULL){
        printf("no se pudo abrir\n");
        return 1;
    }
    c=fgetc(arch);
    while (!feof(arch)){
        if ((c>='a')&&(c<='z')){minus++;}
        else if ((c>='A')&&(c<='Z')){mayus++;}
        else if (isdigit(c)){dig++;}
        c=fgetc(arch);
    }
    printf("cantidad de mayus: %d \n cantidad de minus: %d \n cantidad de digitos: %d",mayus, minus, dig);


    return 0;
}
