 /*6) Un archivo csv (Comma Separated Values) contiene información separada por comas. Cada renglón
(fila) contiene un registro de información. Cada columna contiene un campo particular de
información. La primera fila es especial ya que contiene los nombres de los campos.
Se desea obtener información a partir del archivo llamado vinos.csv (el cual se encuentra en la
Sección “Ing. Gral. y Contenidos” del curso de la cátedra en WebUNLP). El programa debe generar
un archivo de texto con un resumen que indique el valor máximo, mínimo y promedio para cada
uno de los campos del archivo. Este archivo debe llamarse reporte_vinos.txt y debe guardarse en la
misma ruta que el archivo vinos.csv.
*/

#include <stdio.h>
#include <stdlib.h>
#define CAMPOS 8
int main()
{
    FILE* f=fopen("vinos.csv","r");//abro para leer
    FILE* a=fopen("reporte_vinos.txt","a");//lo abro para escritura.
    if ((f==NULL)|(a==NULL)){
        printf("error al cargar los archivos");
        return 1;
    }
    char nombres[CAMPOS][60];//para los nombres de la primer columna
    float prom[CAMPOS]={0.0};
    int contador=0;//para dividir prom
    float min[CAMPOS]={99999.0};//valor muy grande
    float max[CAMPOS]={-1.0};
    //leo aparte la primer columna para guardar los nombres
    //los CVS pueden estar separados por coma opunto y coma, en teste caso por ;
    //explicacion de "%[^;];"
    //se lee hasta que se encuentra un punto y coma
    //los corchetes "[^;]" definen un conjunto de caracteres que fscanf debería leer
    //el "^"invierte el conjunto, por lo que fscanf leerá todos los caracteres que no sean un punto y coma
    for (int i=0;i<8;i++){
          if (fscanf(f,"%[^;];",nombres[i])!=1){printf("error al cargar los nombres");
          return 1;
          }
          //printf("i: %d, nombre: %s ",i,nombres[i]); p debug
    }
    //ahora leo lo demas
    float dato;
    char desperdicio[30];
    fgets(desperdicio,300,f);//la ultima columna es el tipo de vino, no nos interesa. uso gets para pasar de linea
    while (!feof(f)){
        for(int i = 0; i < 8; i++) {
           fscanf(f,"%f;",&dato);
           prom[i]+=dato;
           if (dato > max[i]) max[i] = dato;
           if ((dato < min[i]) min[i] = dato;
        }//cada vez q salga del for tiene q saltar de linea
        contador++;
        fgets(desperdicio,300,f);
    }

    //ahora cargo mi archivo
    fprintf(a,"%s","atributo  ");
    for (int i=0;i<8;i++){
        fprintf(a,"%s"," | ");
        fprintf(a,"%s  ",nombres[i]);
    }
    fprintf(a,"\n");
    fprintf(a,"%s","promedio  ");
    for (int i=0;i<8;i++){
        fprintf(a,"%s"," | ");
        fprintf(a,"%f",prom[i]/contador);
    }
    fprintf(a,"\n");
    fprintf(a,"%s","minimo  ");
    for (int i=0;i<8;i++){
        fprintf(a,"%s"," | ");
        fprintf(a,"%f",min[i]);
    }
    fprintf(a,"\n");
    fprintf(a,"%s","maximo  ");
    for (int i=0;i<8;i++){
        fprintf(a,"%s"," | ");
        fprintf(a,"%f",max[i]);
    }
    fclose(a);
    fclose(f);
    return 0;
}
