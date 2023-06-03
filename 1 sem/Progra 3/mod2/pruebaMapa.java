package prog3.grafos.utiles;
import prog3.grafos.*;
import prog3.listagenerica.*;

public class pruebaMapa {

	public static void main(String[] args) {
		
	//	   Buenos Aires ---- Rosario
//	        |            /    \
//	        |           /      Cordoba
//	        |          /
//	      La Plata  --  
//	        |
//	      Mar del Plata

		// Crear un nuevo grafo
        Grafo<String> grafo = new GrafoImplListAdy<>();

        // Crear vertices (ciudades)
        Vertice<String> buenosAires = new VerticeImplListAdy<>("Buenos Aires");
        Vertice<String> rosario = new VerticeImplListAdy<>("Rosario");
        Vertice<String> cordoba = new VerticeImplListAdy<>("Cordoba");
        Vertice<String> laPlata = new VerticeImplListAdy<>("La Plata");
        Vertice<String> marDelPlata = new VerticeImplListAdy<>("Mar del Plata");

        // Agregar vertices al grafo
        grafo.agregarVertice(buenosAires);
        grafo.agregarVertice(rosario);
        grafo.agregarVertice(cordoba);
        grafo.agregarVertice(laPlata);
        grafo.agregarVertice(marDelPlata);

        // Crear aristas (rutas)
        grafo.conectar(buenosAires, rosario);
        grafo.conectar(buenosAires, laPlata);
        grafo.conectar(rosario, laPlata);
        grafo.conectar(rosario, cordoba);
        grafo.conectar(laPlata, marDelPlata);

        // Crear el mapa
        Mapa<String> mapa = new Mapa<>(grafo);

        // Consultar una ruta
        ListaGenerica<String> camino = mapa.devolverCamino("Buenos Aires", "Cordoba");

        // Imprimir el camino
        camino.comenzar();
        while (!camino.fin()) {
            System.out.print(camino.proximo());
            if (!camino.fin()) {
                System.out.print(" -> ");
            }
        }
        System.out.println("pito");
    }
}

	

