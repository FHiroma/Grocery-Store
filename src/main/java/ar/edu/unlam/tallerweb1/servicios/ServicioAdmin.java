package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Productos;

public interface ServicioAdmin {
	List<Productos> listarProductosDisponibles();
	void publicarProducto(Long id);
	void quitarProducto(Long id);
	List<Productos> verProductosOferta();
	void insertarProducto(Productos producto);
}
