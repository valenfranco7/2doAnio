package prog3.grafos.utiles;

import prog3.grafos.Arista;
import prog3.grafos.Grafo;
import prog3.grafos.Vertice;
import prog3.listagenerica.*;

public class Delta {
	
	public int maxIslasDistintas(Grafo<String> grafo) {
		int recorridoMax=-1;
		if((grafo!=null)&&(!grafo.esVacio())) {
			boolean[] visitados= new boolean[grafo.listaDeVertices().tamanio()];
			grafo.listaDeVertices().comenzar();
			Vertice<String> muellePrincipal= grafo.listaDeVertices().proximo();
			recorridoMax=maxIslasDistintas(muellePrincipal,muellePrincipal,visitados,grafo);//no es +1para no contar el muelle
		}
		
		return recorridoMax;
	}
	
	private int maxIslasDistintas(Vertice<String>vAct,Vertice<String> muelle,boolean[] visitados,Grafo<String> g) {
		int max=0;
		visitados[vAct.posicion()]=true;
		ListaGenerica<Arista<String>> adyacentes= g.listaDeAdyacentes(vAct);
		adyacentes.comenzar();
		while(!adyacentes.fin()) {
			Vertice<String> vSig= adyacentes.proximo().verticeDestino();
			if(!visitados[vSig.posicion()]) {
				int islas=1+maxIslasDistintas(vSig,muelle,visitados,g);
				if (islas>max){
					max=islas;
				};//sumamos una isla al avanzar
			}
		}
		visitados[vAct.posicion()]=false;
		return max;
	}
	
	public RutaMinima<String> caminoMasCorto(Grafo<String> grafo,String islaO,String islaD) {
		RutaMinima<String> resultado= new RutaMinima<>();
		if((grafo!=null)&&(!grafo.esVacio())) {
			//veo si estan las islas
			ListaGenerica<Vertice<String>> vertices= grafo.listaDeVertices();
			Vertice<String> vInicial=null;
			Vertice<String> vFinal=null;
			vertices.comenzar();
			while((!vertices.fin())&&(vInicial==null || vFinal==null)) {
				Vertice<String> vAct=vertices.proximo();
				if (vAct.dato().endsWith(islaO)) vInicial=vAct;
				else if ((vAct.dato().endsWith(islaD))) vFinal=vAct;
				
			}
			//si existen
			if(vInicial!=null && vFinal!=null) {
				vertices.comenzar();
				Vertice<String>vMuelle= vertices.proximo();
				Peso metrosMin= new Peso();
				metrosMin.setPeso(9999999);
				boolean[] visitados=new boolean[grafo.listaDeVertices().tamanio()];
				ListaGenerica<Vertice<String>> caminoAct= new ListaGenericaEnlazada<Vertice<String>>();
				caminoMasCorto(vInicial,vFinal,vMuelle,resultado,caminoAct,visitados,grafo,0,metrosMin);
			}
			
		}
		
		return resultado;
	}
	
	private void caminoMasCorto(Vertice<String> vAct,Vertice<String> vFinal,Vertice<String> muelle,RutaMinima<String> resultado,ListaGenerica<Vertice<String>> caminoAct,boolean[] visitados,Grafo<String> g,int metrosAct,Peso metrosMin) {
		visitados[vAct.posicion()]=true;
		caminoAct.agregarFinal(vAct);
		if (vAct.dato().equals(vFinal.dato())&&(metrosAct<metrosMin.getPeso())) {
			resultado.setCaminoCorto(caminoAct.clonar());//mando una nueva referencia, asi n apuntan a lo mismo.
			metrosMin.setPeso(metrosAct);
		}
		else {ListaGenerica<Arista<String>> adyacentes=g.listaDeAdyacentes(vAct);//en un else para no seguir recorriendo cuando se llegue a destino
		adyacentes.comenzar();
		while(!adyacentes.fin()) {
			Arista<String> arista=adyacentes.proximo();
			Vertice<String> vSig=arista.verticeDestino();
			if(!visitados[vSig.posicion()]) {
				if (vSig.dato().equals(muelle.dato()))
					resultado.setBoleto(false);//esta inicializado en true, false si se requiere otro boleto
				caminoMasCorto(vSig,vFinal,muelle,resultado,caminoAct,visitados,g,metrosAct+arista.peso(),metrosMin);
			}
			
		}
		}
		visitados[vAct.posicion()]=false;
		caminoAct.eliminarEn(caminoAct.tamanio()-1);
	}
	

}
