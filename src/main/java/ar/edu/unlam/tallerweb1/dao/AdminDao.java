package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Productos;

public interface AdminDao {
	List<Productos> listarProductosDisponibles();
	void publicarProducto(Long id);
	void quitarProducto(Long id);
}
