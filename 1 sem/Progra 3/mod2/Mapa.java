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
		if ((mapaCiudades!=null)&&(!mapaCiudades.esVacio())) {
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

		}else System.out.println("error en el grafo");
		
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

	public ListaGenerica<String> devolverCaminoExceptuando (String ciudad1,String ciudad2,ListaGenerica<String> ciudades){
		ListaGenerica<String> caminoResult = new ListaGenericaEnlazada<String>();
		if((!mapaCiudades.esVacio())&&(mapaCiudades!=null)){
			//verifico q existan las ciudades
			ListaGenerica<Vertice<String>> vertices= mapaCiudades.listaDeVertices();
			Vertice<String> vInicial=null;
			Vertice<String> vFinal=null;
			vertices.comenzar();
			while ((!vertices.fin())&&((vInicial==null)||(vFinal==null))){
				Vertice<String> vAct=vertices.proximo();
				if (vAct.dato().equals(ciudad1)) {
					vInicial=vAct;
				}
				else if (vAct.dato().equals(ciudad2)) {
					vFinal=vAct;
				}		
			}
			//si existen los dos
			if ((vInicial!=null)&&(vFinal!=null)){
				ListaGenerica<String> caminoAct =new ListaGenericaEnlazada<String>();
				boolean[] visitados= new boolean[mapaCiudades.listaDeVertices().tamanio()];
				devolverCaminoExceptuando(vInicial,vFinal,caminoResult,caminoAct,mapaCiudades,visitados,ciudades);
			}
		}
		return caminoResult;
		
	}
	
	private void devolverCaminoExceptuando(Vertice<String> vInicial,Vertice<String> vFinal,ListaGenerica<String> caminoRes,ListaGenerica<String> caminoAct,Grafo<String> g,boolean[] visitados,ListaGenerica<String> ciudades) {
		visitados[vInicial.posicion()]=true;
		caminoAct.agregarFinal(vInicial.dato());
		if (vFinal.dato().equals(vInicial.dato())) {
			clonar(caminoAct,caminoRes);
		}
		ListaGenerica<Arista<String>> adyacentes= g.listaDeAdyacentes(vInicial);
		adyacentes.comenzar();
		while (!adyacentes.fin()) {
			Vertice<String> vSig=adyacentes.proximo().verticeDestino();
			if ((verificar(vSig,ciudades))&&(!visitados[vSig.posicion()])) //verifico que no este visitado ni que este en la lista de ciuaddes
				{devolverCaminoExceptuando(vSig,vFinal,caminoRes,caminoAct,g,visitados,ciudades);
				
			}
		}
		visitados[vInicial.posicion()]=false;
		caminoAct.eliminarEn(caminoAct.tamanio()-1);
	}
	private boolean verificar(Vertice<String> v,ListaGenerica<String> ciudades) {
		ciudades.comenzar();
		while (!ciudades.fin()) {
			if (v.dato().equals(ciudades.proximo())) {
				return false; //esta en la lista de exceptuados
			}
		}
		return true;//no esta en la lista
	}
	
	public ListaGenerica<String> caminoMasCorto(String ciudad1,String ciudad2){
		ListaGenerica<String> resultado= new ListaGenericaEnlazada<String>();
		if((mapaCiudades!=null)&&(!mapaCiudades.esVacio())) {
			ListaGenerica<Vertice<String>> vertices= mapaCiudades.listaDeVertices();
			Vertice<String> vInicial= null;
			Vertice<String> vFinal=null;
			vertices.comenzar();
			while (!vertices.fin()) {
				Vertice<String> vAct=vertices.proximo();
				if (vAct.dato().equals(ciudad1)) {
					vInicial=vAct;
				}
				else if (vAct.dato().equals(ciudad2)) {
					vFinal=vAct;
				}		
			}
			// si s ecumple empezamos a recorrer
			if (((vInicial!=null)&&(vFinal!=null))){
				ListaGenerica<String> caminoAct=new ListaGenericaEnlazada<String>();
				boolean[] visitados= new boolean[mapaCiudades.listaDeVertices().tamanio()];
				caminoSinCargarCombustible(vInicial,vFinal,caminoAct,resultado,visitados,mapaCiudades);
			}
		}
		return resultado;
	}
	
	private void caminoSinCargarCombustible(Vertice<String> vAct,Vertice<String> vFinal,ListaGenerica<String> caminoAct,ListaGenerica<String> resultado,boolean[] visitados, Grafo <String> g) {
		visitados[vAct.posicion()]=true;
		caminoAct.agregarFinal(vAct.dato());
		if ((vAct.dato().equals(vFinal.dato()))&&((caminoAct.tamanio()<resultado.tamanio()||(resultado.tamanio()==0)))){
			clonar(caminoAct,resultado);
		}
		//recorro
		ListaGenerica<Arista<String>> adyacentes= g.listaDeAdyacentes(vAct);
		adyacentes.comenzar();
		while(!adyacentes.fin()) {
			Vertice<String> vSig=adyacentes.proximo().verticeDestino();
			if(!visitados[vSig.posicion()]) {
				caminoSinCargarCombustible(vSig,vFinal,caminoAct,resultado,visitados,g);
			}
		}
		visitados[vAct.posicion()]=false;
		caminoAct.eliminarEn(caminoAct.tamanio()-1);
	}
	
	public ListaGenerica<String> caminoSinCargarCombustible(String ciudad1,String ciudad2, int tanqueAuto){
		ListaGenerica<String> resultado= new ListaGenericaEnlazada<String>();
		if ((mapaCiudades!=null)&&(!mapaCiudades.esVacio())) {
			ListaGenerica<Vertice<String>> vertices= mapaCiudades.listaDeVertices();
			Vertice<String> vInicial= null;
			Vertice<String> vFinal=null;
			vertices.comenzar();
			while (!vertices.fin()) {
				Vertice<String> vAct=vertices.proximo();
				if (vAct.dato().equals(ciudad1)) {
					vInicial=vAct;
				}
				else if (vAct.dato().equals(ciudad2)) {
					vFinal=vAct;
				}		
			}
			// si s ecumple empezamos a recorrer
			if (((vInicial!=null)&&(vFinal!=null))){
				ListaGenerica<String> caminoAct=new ListaGenericaEnlazada<String>();
				boolean[] visitados= new boolean[mapaCiudades.listaDeVertices().tamanio()];
				caminoSinCargarCombustible(vInicial,vFinal,caminoAct,resultado,visitados,mapaCiudades,tanqueAuto);
			}
		}
		return resultado;
	}
	
	private void caminoSinCargarCombustible(Vertice<String>vAct,Vertice<String>vFinal,ListaGenerica<String> caminoAct,ListaGenerica<String>resultado,boolean[] visitados, Grafo<String>g, int naftaActual) {
		visitados[vAct.posicion()]=true;
		caminoAct.agregarFinal(vAct.dato());
		if(vAct.dato().equals(vFinal.dato()))// si llego al destino es pq tenia nafta
			{ clonar(caminoAct,resultado);	
		}
		//recorro
		ListaGenerica<Arista<String>> adyacentes=g.listaDeAdyacentes(vAct);
		adyacentes.comenzar();
		while (!adyacentes.fin()) {
			Arista<String> aristaAct=adyacentes.proximo();
			if (naftaActual-aristaAct.peso()>0) {
				Vertice<String> vSig=aristaAct.verticeDestino();
				if(!visitados[vSig.posicion()]) {
					caminoSinCargarCombustible(vSig,vFinal,caminoAct,resultado,visitados,g,naftaActual-aristaAct.peso());
				}
			}
		}
		visitados[vAct.posicion()]=false;
		caminoAct.eliminarEn(caminoAct.tamanio()-1);
	}
	

}
