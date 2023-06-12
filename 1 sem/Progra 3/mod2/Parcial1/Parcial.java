package parciales.grafos;

import prog3.grafos.Arista;
import prog3.grafos.Grafo;
import prog3.grafos.Vertice;
import prog3.listagenerica.*;

public class Parcial {
	
	int resolver(Grafo<Ciudad> ciudades,String origen, String destino, int maxControles) {
		int dias=0;
		if(ciudades!=null && !ciudades.esVacio()) {
			//encuentro vertices
			Vertice<Ciudad> vIni=null,vFinal=null;
			ListaGenerica<Vertice<Ciudad>> vertices=ciudades.listaDeVertices();
			vertices.comenzar();
			while((vIni==null && vFinal==null)|| vertices.fin()) {
				Vertice<Ciudad> vAct=vertices.proximo();
				if(vIni.dato().getCiudad().equals(origen))
					vIni=vAct;
				else if(vFinal.dato().getCiudad().equals(destino))
					vFinal=vAct;
			}
			//si los encontre
			if(vIni!=null && vFinal!=null) {
				boolean[] visitados=new boolean[ciudades.listaDeVertices().tamanio()];
		        dias= resolver(vIni,vFinal,maxControles,visitados,ciudades,0); //el 0 es para islas actual
			}

		}
		
		
		return dias;
	}

	private int resolver(Vertice<Ciudad> vAct, Vertice<Ciudad> vFinal,int maxControles, boolean[] visitados, Grafo<Ciudad> g, int diasAct) {
	    visitados[vAct.posicion()] = true;
	    int diasMax = 0; // inicializamos a 0

	    if (vAct.dato().getCiudad().equals(vFinal.dato().getCiudad())) {
	        diasMax = diasAct + vAct.dato().getDias(); // si llego al destino, acumulo los días
	    } else {
	        ListaGenerica<Arista<Ciudad>> adyacentes = g.listaDeAdyacentes(vAct);
	        adyacentes.comenzar();
	        while (!adyacentes.fin()) {
	            Arista<Ciudad> aristaAct = adyacentes.proximo();
	            Vertice<Ciudad> vSig = aristaAct.verticeDestino();
	            if (!visitados[vSig.posicion()] && aristaAct.peso() <= maxControles) {
	                int diasPorEsteCamino = resolver(vSig, vFinal, maxControles, visitados, g, diasAct + vAct.dato().getDias());
	                if (diasPorEsteCamino>diasMax) {
	                	diasMax=diasPorEsteCamino;
	                }
	            }
	        }
	    }

	    visitados[vAct.posicion()] = false;
	    return diasMax;
	}


}
