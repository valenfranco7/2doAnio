/*Se desea leer y procesar información de precipitaciones del mes de enero. Para ello se dispone de un
archivo de texto (llamado precipitaciones.txt) con el siguiente formato:
0-2-0-0-7-22-11-0- . . . -0-
Por cada de los 31 días se tiene un número entero indicando los milímetros llovidos, seguido de un
guión medio (-) como delimitador. Escriba un programa que lea la información del archivo y
derermine el día con mayor precipitación. Para evaluar el programa, genere un archivo con el
formato establecido utilizando un editor de texto plano (por ejemplo: Bloc de notas o Notepad++).
Nota: puede utilizar la función fscanf para procesar cada valor de precipitación.
*/

#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE* f= fopen("precipitaciones.txt","r");//lo abro para lectura
    if (f==NULL){
        printf("el archivo no se pudo abrir\n");
        return 1;
    }
    int maxDia,maxPreci,preci;
    for (int i=1;i<=31;i++){
        fscanf(f,"%d-",&preci);//lee todo hasta el -
        if (preci>maxPreci){
            maxPreci=preci;
            maxDia=i;
        }
    }
    printf("dia con mayor precipitacion: %d", maxDia);

    fclose(f);
    return 0;
}
