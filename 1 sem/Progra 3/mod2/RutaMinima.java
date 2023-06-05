package prog3.grafos.utiles;
import prog3.grafos.Vertice;
import prog3.listagenerica.*;

public class RutaMinima <T>{
	private ListaGenerica<Vertice<T>> caminoCorto=new ListaGenericaEnlazada<Vertice<T>>(); 
	private boolean boleto=true;//true si se puede realizar con un unico boleto
	
	public ListaGenerica<Vertice<T>> getCaminoCorto() {
		return caminoCorto;
	}
	public void setCaminoCorto(ListaGenerica<Vertice<T>> caminoCorto) {
		this.caminoCorto = caminoCorto;
	}
	public boolean isBoleto() {
		return boleto;
	}
	public void setBoleto(boolean boleto) {
		this.boleto = boleto;
	}

}
