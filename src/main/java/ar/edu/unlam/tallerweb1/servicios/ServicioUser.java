package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.Productos;

public interface ServicioUser {
	List<Productos> verProductosDisponibles();
	List<Compra> verProductosEnOferta();
	void guardarCarritoVacio(CarritoCompras carrito);
}
