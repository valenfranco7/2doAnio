 /*7) Escriba un programa que copie el contenido de un archivo de texto en otro nuevo.
a) Utilizando las funciones fgetc y fputc.
b) Utilizando las funciones fgets y fputs.
c) Utilizando las funciones fread y fwrite.
*/

#include <stdio.h>
#include <stdlib.h>
int main()
{
    FILE* f=fopen("deEste.txt","r");//abrimos para lectura
    FILE* a=fopen("aEste.txt","w");// creo un archivo para escritura. si existe, se sobreescribe
    if ((f==NULL)||(a==NULL)){
        printf("error al cargar los archivos");
        return 1;
    }
    //con fgetc y fputs
    char c;
    int terminar=0;//para leer 2 lineas
    c=fgetc(f);
    if (c=='\n') terminar++;
    while ((!feof(f))&&(terminar!=2)){
        fputc(c,a);//mando a archivo que cree
        c=fgetc(f);
        if (c=='\n') terminar++;
    }
    fputc('\n',a);//le mando un salto de linea pq lo saque en wl while y no lo mande;
    //con fgets y fputs
    //fgets lee lineas enteras hasta el salto o fin de programa
    terminar=0;
    char palabra[1024];
    fgets(palabra,120,f);
    fputs(palabra,a);
    fgets(palabra,120,f);
    fputs(palabra,a);
    //no uso while pero si quisiera usar, es con !feof(f) leyendo afuera y adentro abajo del todo

    //con fread y fwrite las dos lineas que quedan. Fread no frena en saltos de linea
    //le indico la cantidad de bytes y solo para si hay un error o es el fin del programa, entonces no es necesario un while
    //si la cantidad de bytes es demasiado grande.
    int cant=fread(palabra,sizeof(char),1024,f);
    //fread no agrega el caracter nulo, la tengo que gregar para que sea una cadena valida en C.
    palabra[cant]='\0';
    fwrite(palabra,sizeof(char),cant,a);

    fclose(a);
    fclose(f);
    return 0;
}
