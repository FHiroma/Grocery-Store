package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.DetalleVenta;
import ar.edu.unlam.tallerweb1.modelo.Productos;

public interface ServicioDetalleVenta {
	
	void registrarDetalle(DetalleVenta detalle);
	Boolean existe(CarritoCompras carrito, Productos producto);
	DetalleVenta buscarDetalleVentaConCarritoProducto(CarritoCompras carrito, Productos producto);
	void actualizarDetalleVenta(DetalleVenta detalle);
	List<DetalleVenta> traerCarritoCompras(CarritoCompras carrito);
	void eliminarDetalleVenta(Long id, CarritoCompras carrito);
	Boolean modificarCantidadDeUnProductoDelCarrito(Long id, CarritoCompras carrito, Integer cantidad);

}
