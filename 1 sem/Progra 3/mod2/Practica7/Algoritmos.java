package prog3.grafos.utiles;

import prog3.grafos.Arista;
import prog3.grafos.Grafo;
import prog3.grafos.Vertice;
import prog3.listagenerica.ListaGenerica;

public class Algoritmos<T> {

    public boolean subgrafoCuadrado(Grafo<T> grafo) {
        if (grafo == null && grafo.esVacio()) {
            return false;
        }

        boolean[] visitados = new boolean[grafo.listaDeVertices().tamanio()];
        ListaGenerica<Vertice<T>> vertices = grafo.listaDeVertices();
        vertices.comenzar();
        
        while (!vertices.fin()) {
            Vertice<T> vInicial = vertices.proximo();
            if (!visitados[vInicial.posicion()]) {
                if (subgrafoCuadrado(vInicial, vInicial, visitados, grafo, 0)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    private boolean subgrafoCuadrado(Vertice<T> vActual, Vertice<T> vFinal, boolean[] visitados, Grafo<T> grafo, int longitud) {
        visitados[vActual.posicion()] = true;
        ListaGenerica<Arista<T>> adyacentes = grafo.listaDeAdyacentes(vActual);
        adyacentes.comenzar();
        
        while (!adyacentes.fin()) {
            Vertice<T> vSiguiente = adyacentes.proximo().verticeDestino();
            
            if (!visitados[vSiguiente.posicion()]) {
                if (subgrafoCuadrado(vSiguiente, vFinal, visitados, grafo, longitud + 1)) {
                    return true;
                }
           }else if (vSiguiente.dato().equals(vFinal.dato()) && (longitud+1) == 4) { // longitud seria 3 si falta completar el ciclo
                return true;
            }
        }

        visitados[vActual.posicion()] = false;
        return false;
    }
    
    public int getGrado(Grafo<T> grafo) {
    	int grado = -1;
    	if(grafo!=null && !grafo.esVacio()) {
    		ListaGenerica<Vertice<T>> vertices=grafo.listaDeVertices();
    		vertices.comenzar();
    	    while(!vertices.fin()) {
    	    	int gradoAct=getGrado(vertices.proximo(),vertices,grafo);
    	    	if (gradoAct>grado) {
    	    		grado=gradoAct;
    	    	}
    	    }
    	}
    
    return grado;
    }
    
    private int getGrado(Vertice<T> vAct,ListaGenerica<Vertice<T>> vertices,Grafo<T>g ) {
    	int grado=g.listaDeAdyacentes(vAct).tamanio();//todas lkas aristas quie salen de el
    	vertices.comenzar();
    	while(!vertices.fin()) {
    	    if (g.esAdyacente(vertices.proximo(), vAct)) {//todas las aristas que entran en el. 
    	    	grado++; //true si origen es adyancente a destino.
    	    }
    	}
    	return grado;
    }
    
    public boolean tieneCiclo(Grafo<T> grafo) {
    	if((grafo!=null)&&(!grafo.esVacio())) {
    		ListaGenerica<Vertice<T>> vertices= grafo.listaDeVertices();
    		vertices.comenzar();
    		boolean[] visitados= new boolean[grafo.listaDeVertices().tamanio()];
    		while(!vertices.fin()) {
    			Vertice<T> vInicial=vertices.proximo();
    			if(tieneCiclo(vInicial,vInicial,visitados,grafo)) {
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
    private boolean tieneCiclo(Vertice<T>vAct,Vertice<T>vFinal,boolean[] visitados,Grafo<T>g) {
    	visitados[vAct.posicion()]=true;
    	
    	ListaGenerica<Arista<T>> adyacentes= g.listaDeAdyacentes(vAct);
    	adyacentes.comenzar();
    	while(!adyacentes.fin()) {
    		Vertice<T> vSig=adyacentes.proximo().verticeDestino();
    		if (!visitados[vSig.posicion()]) {
    			if( tieneCiclo(vSig,vFinal,visitados,g)) {
    				return true;
    			}
    		}else if (vSig.dato().equals(vFinal.dato()))
    				return true; //si quiere ir a uno ya visitado, es ciclo
    	}
    	
    	visitados[vAct.posicion()]=false;
    	return false;
    }
    
    
}
