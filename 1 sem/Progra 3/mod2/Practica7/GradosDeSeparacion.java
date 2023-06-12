package prog3.grafos.utiles;

import prog3.grafos.Arista;
import prog3.grafos.Grafo;
import prog3.grafos.Vertice;
import prog3.listagenerica.*;

public class GradosDeSeparacion {
	
	
	public int maximoGradoDeSeparacion(Grafo<String> grafo) {
		int grado=0;
		if(grafo!=null && !grafo.esVacio()) {
			boolean[] visitados=new boolean[grafo.listaDeVertices().tamanio()];
			ListaGenerica<Vertice<String>> vertices= grafo.listaDeVertices();
			vertices.comenzar();
			//llamo desde cualquier vertice 
		    Vertice<String> vInicial=vertices.proximo();
		    grado=maximoGradoDeSeparacion(vInicial,visitados,grafo);
			while(!vertices.fin()) {
				if(visitados[vertices.proximo().posicion()])
				   return 0;// si hay uno no visitado, no estan todos conectados
					
			}
		}
		
		return grado;
	}
	
	private int maximoGradoDeSeparacion(Vertice<String> vInicial,boolean[] visitados,Grafo<String> g ) {
		ColaGenerica<Vertice<String>> cola= new ColaGenerica<>();
		cola.encolar(vInicial);
		cola.encolar(null);
		int grado = 0;
		visitados[vInicial.posicion()]=true;
		while(!cola.esVacia()){
			Vertice<String> vAct= cola.desencolar();
			if(vAct!=null) {
			   ListaGenerica<Arista<String>> adyacentes= g.listaDeAdyacentes(vAct);
			   adyacentes.comenzar();
			   while (!adyacentes.fin()) {
				  Vertice<String> vSig=adyacentes.proximo().verticeDestino();
				  if(!visitados[vSig.posicion()]) {
					  visitados[vSig.posicion()]= true;
					  cola.encolar(vSig);
				  }
				
			   }
		   }else if(!cola.esVacia()) {
			   cola.encolar(null);
			   grado++; //cambio de "lvl"
		   }
		   } 
		return grado;
			
		
	}


}
