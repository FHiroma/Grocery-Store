package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.DetalleVenta;
import ar.edu.unlam.tallerweb1.modelo.Productos;

public interface DetalleVentaDao {
	
	void registrarDetalle(DetalleVenta detalle);
	Boolean existe(CarritoCompras carrito, Productos producto);
	DetalleVenta buscarDetalleVentaConCarritoProducto(CarritoCompras carrito, Productos producto);
	void actualizarDetalleVenta(DetalleVenta detalle);
	List<DetalleVenta> traerCarritoCompras(CarritoCompras carrito);
	void eliminarDetalleVenta(Productos producto, CarritoCompras carrito);
	Boolean modificarCantidadDeUnProductoDelCarrito(Productos producto, CarritoCompras carrito, Integer cantidad);

}
