/*Se desea leer y procesar informaci�n de precipitaciones del mes de enero. Para ello se dispone de un
archivo de texto (llamado precipitaciones.txt) con el siguiente formato:
0-2-0-0-7-22-11-0- . . . -0-
Por cada de los 31 d�as se tiene un n�mero entero indicando los mil�metros llovidos, seguido de un
gui�n medio (-) como delimitador. Escriba un programa que lea la informaci�n del archivo y
derermine el d�a con mayor precipitaci�n. Para evaluar el programa, genere un archivo con el
formato establecido utilizando un editor de texto plano (por ejemplo: Bloc de notas o Notepad++).
Nota: puede utilizar la funci�n fscanf para procesar cada valor de precipitaci�n.
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
