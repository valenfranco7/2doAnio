 /*5) Escriba un programa que permita a un usuario consultar si un conjunto de palabras existe o no en
un diccionario. El usuario ingresa de a una palabra y la consulta finaliza cuando ingresa la palabra
“ZZZ”. Para cada palabra ingresada se debe informar si la misma pertenece o no al diccionario.
El diccionario consiste en un archivo de texto y las palabras se encuentran ordenadas en forma
ascendente (una por línea). Se desea generar una estructura de datos dinámica (memoria RAM) en
la cual se almacenen las palabras de todo el diccionario. Luego, verifique la pertenencia de las
palabras ingresadas por el usuario utilizando dicha estructura en lugar del archivo.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Nodo{
    char palabra[60];
    struct Nodo* sig;
};
typedef struct Nodo* listaEnlazada;

void eliminarLista(listaEnlazada* cabeza);
void inicializarLista(listaEnlazada* cabeza);
void agregarInicio(listaEnlazada* cabeza, char dato[60]);
int comparar(listaEnlazada cabeza,char dato[60]);
int main()
{
    FILE *f=fopen("diccionario.txt","r");
    if (f==NULL){
        printf("error al abrir");
        return 1;
    }
      listaEnlazada lista;
      inicializarLista(&lista);
    //uso fscanf
    //fgets lee hasta un cambio de linea
            //si quiero leer hasta cierta condicion puedo usar fscanf con un caracter de escape
            //fgets lee el \n y lo incluye !
    char palabra[60];
   /* podria usar esto como sugiere la catedra:
    fscanf(f, "%s", palabra);//mejor que usar fgets porque pone bien el \0
    while (!feof(f)){//no olvidarse del !
        agregarInicio(&lista,palabra);
        fscanf(f,"%s",palabra);
    } pero es mejor practica esto otro:*/

    while (fscanf(f,"%s",palabra)==1){//fscanf devuelve el numero de elementos leidos, si es 1, esta bien. si hay un error da EOF
        agregarInicio(&lista,palabra);
    }
    //armada la lista
     fclose(f);//no usamos mas el archivo
    printf("ingrese palabras, fin con ZZZ\n");
    char palabraAct[60];
    scanf("%s",palabraAct);
    while (strcmp(palabraAct,"ZZZ"))//mientras no sea igual a ZZZ
    {
        if (comparar(lista,palabraAct)) {//comparar retorna 0 si no son iguales
            printf("la palabra esta en el diccionario\n");
        }
        else printf("la palabra no esta en el diccionario\n");
        scanf("%s",palabraAct);
    }
    eliminarLista(lista);
    return 0;

}
void inicializarLista(listaEnlazada* cabeza){
    *cabeza=NULL;
}
void agregarInicio(listaEnlazada* cabeza,char dato[60]){
    listaEnlazada nuevoNodo= malloc(sizeof(**cabeza));//reservo mem para un nodo, **cabeza es nodo
    if (nuevoNodo==NULL){
        printf("error al reservar memoria dinamica");
        exit(EXIT_FAILURE);//salir del programa si estamos sin memoria o pasa algun error
    }
    strcpy(nuevoNodo->palabra,dato);//  nuevoNodo->palabra=dato;
    nuevoNodo->sig=*cabeza;
    *cabeza=nuevoNodo;//actualizo la direc de inicio
}
int comparar(listaEnlazada cabeza,char dato[60]){
    while (cabeza!=NULL){
    if (!strcmp(cabeza->palabra,dato)){// si son iguales str retorna 0
        return 1;
    }
    cabeza=cabeza->sig;
    }
    return 0;//no esta la palanbra
    }

void eliminarLista(listaEnlazada* cabeza) {
    listaEnlazada actual = *cabeza;
    listaEnlazada siguiente;

    while (actual != NULL) {
        siguiente = actual->sig;
        free(actual);
        actual = siguiente;
    }

    *cabeza = NULL;
}


