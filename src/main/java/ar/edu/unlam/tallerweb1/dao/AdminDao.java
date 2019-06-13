package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;

public interface AdminDao {
	List<Productos> listarProductosDisponibles();
	void publicarProducto(Long id);
	void quitarProducto(Long id);
	List<Productos> verProductosOferta();
	void insertarProducto(Productos producto);
	Productos buscarProducto(Long id);
	void insertarStock(Compra stock, Long id);
	void aumentarStockProducto(Integer cantidad, Long id);
	List<Notificacion> buscarNotificaciones();
}
