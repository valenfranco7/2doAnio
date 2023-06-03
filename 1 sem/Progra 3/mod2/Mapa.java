package prog3.grafos.utiles;
import prog3.grafos.*;
import prog3.listagenerica.*;
public class Mapa<T> {
	private Grafo<String> mapaCiudades= new GrafoImplListAdy<String>();
	
	public Mapa(Grafo<String> g) {
		mapaCiudades=g;
	}
	
	public ListaGenerica<String> devolverCamino(String ciudad1, String ciudad2){
		ListaGenerica<String> caminoResult= new ListaGenericaEnlazada<String>();
		if ((!mapaCiudades.esVacio())&&(mapaCiudades!=null)) {
			//verifico si existen
			Vertice<String> vInicial=null,vFinal=null;
			//recorro todos los vertices
			ListaGenerica<Vertice<String>> vertices= mapaCiudades.listaDeVertices();
			vertices.comenzar();
			while ((!vertices.fin())&&((vInicial==null)||(vFinal==null))){
				Vertice<String> vertice=vertices.proximo();
				if(vertice.dato().equals(ciudad1)) {
					vInicial=vertice;
				}
				else if (vertice.dato().equals(ciudad2)) {
					vFinal=vertice;
				}
			}
				//si existen ambos, recorremos
			if ((vInicial!=null)&&(vFinal!=null)) {
				ListaGenerica<String> caminoAct=new ListaGenericaEnlazada<String>();
				boolean[] visitados=new boolean[mapaCiudades.listaDeVertices().tamanio()];
				devolverCamino(vInicial,vFinal,caminoAct,caminoResult, mapaCiudades,visitados);
			
			}

		}
		
		return caminoResult;
	}
	
	private void devolverCamino(Vertice<String> vInicial,Vertice<String> vFinal,ListaGenerica<String> caminoAct,ListaGenerica<String> caminoResult,Grafo<String> grafo,boolean [] visitados) {
		visitados[vInicial.posicion()]=true;
		caminoAct.agregarFinal(vInicial.dato());
		if (vInicial.dato().equals(vFinal.dato())) {
			clonar(caminoAct,caminoResult);
		}
		ListaGenerica<Arista<String>> adyacentes= grafo.listaDeAdyacentes(vInicial);
		adyacentes.comenzar();
		while(!adyacentes.fin()) {
			Vertice<String> vSiguiente= adyacentes.proximo().verticeDestino(); //adyacentes . proximo devuelve una arista. arista.verticedescitno el vertice destino
			if (!visitados[vSiguiente.posicion()]) {
				devolverCamino(vSiguiente, vFinal,caminoAct,caminoResult,grafo,visitados);
			}
		}
		visitados[vInicial.posicion()]=false;
		caminoAct.eliminarEn(caminoAct.tamanio()-1);
	}

    private void clonar(ListaGenerica<String>camino,ListaGenerica<String> resultado) {
    	while (!resultado.esVacia()) {
    		resultado.eliminarEn(0);
    	}
    	camino.comenzar();
    	while(!camino.fin()) {
    		resultado.agregarFinal(camino.proximo());
    	}
    }

	

}
