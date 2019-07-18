package ar.edu.unlam.tallerweb1.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Proveedor;
import ar.edu.unlam.tallerweb1.modelo.Recomendacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.DetalleVenta;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Localidades;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.OrdenCompra;
import ar.edu.unlam.tallerweb1.modelo.PedidoProducto;
import ar.edu.unlam.tallerweb1.modelo.ProductoOrdenCompra;

public interface AdminDao {
	List<Productos> listarProductosDisponibles();
	void publicarProducto(Long id);
	void quitarProducto(Long id);
	List<Productos> verProductosOferta();
	void insertarProducto(Productos producto, Long idCategoria, CommonsMultipartFile file);
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
	void insertarUsuarioAlCarrito(CarritoCompras carrito, Usuario usuario);
	List<Localidades> listarLocalidades();
	Direccion guardarDireccionDeCompra(Direccion direccion, Long idLocalidad);
	void agregarDireccionAlCarrito(CarritoCompras carrito, Direccion direccionTabla);
	List<CarritoCompras> buscarCarritosCompra();
	List<DetalleVenta> listarDetallesDeVentaConIdCarrito(Long id);
	CarritoCompras buscarCarritoComprasConId(Long id);
	Boolean enviarCarrito(Long id);
	List<Productos> buscarProductosRecomendados(Recomendacion rec);
	List<CarritoCompras> buscarCarritosCompraConfirmados();
	void crearOrdenesDeCompraEnBaseAListaPedidoProducto(ArrayList<PedidoProducto> list);
	List<OrdenCompra> traerOrdenesDeCompra();
	void confirmarOrdenDeCompra(Long id);
	List<ProductoOrdenCompra> verDetallesDeOrdenDeCompra(Long id);
	OrdenCompra traerOrdenDeCompra(Long id);
}
