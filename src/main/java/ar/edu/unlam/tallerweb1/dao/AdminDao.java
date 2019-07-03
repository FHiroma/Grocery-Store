package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Proveedor;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;

public interface AdminDao {
	List<Productos> listarProductosDisponibles();
	void publicarProducto(Long id);
	void quitarProducto(Long id);
	List<Productos> verProductosOferta();
	void insertarProducto(Productos producto, Long idCategoria);
	Productos buscarProducto(Long id);
	void insertarStock(Compra stock, Long id);
	void aumentarStockProducto(Integer cantidad, Long id);
	List<Notificacion> buscarNotificaciones();
	List<Compra> productoOferta();
	List<Categoria> listarCategorias();
	List<Proveedor> listarProveedores();
	List<Notificacion> buscarProductosStockMinimo();
	List<Notificacion> buscarProductosVencidos();
	List<Notificacion> buscarProductosEnOferta();
}
