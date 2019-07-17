package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ar.edu.unlam.tallerweb1.dao.AdminDao;
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
import ar.edu.unlam.tallerweb1.modelo.PedidoProducto;

@Service("servicioAdmin")
@Transactional
public class ServicioAdminImpl implements ServicioAdmin {

	@Inject
	private AdminDao servicioAdminDao;
	
	@Override
	public List<Productos> listarProductosDisponibles() {
		return servicioAdminDao.listarProductosDisponibles();
	}

	@Override
	public void publicarProducto(Long id) {
		servicioAdminDao.publicarProducto(id);
	}

	@Override
	public void quitarProducto(Long id) {
		servicioAdminDao.quitarProducto(id);
	}

	@Override
	public List<Productos> verProductosOferta() {
		return servicioAdminDao.verProductosOferta();
	}

	@Override
	public Productos buscarProducto(Long id) {
		return servicioAdminDao.buscarProducto(id);
	}

	@Override
	public void insertarStock(Compra stock, Long id) {
		servicioAdminDao.insertarStock(stock, id);
	}

	@Override
	public void aumentarStockProducto(Integer cantidad, Long id) {
		servicioAdminDao.aumentarStockProducto(cantidad, id);
	}

	@Override
	public List<Notificacion> buscarNotificaciones() {
		return servicioAdminDao.buscarNotificaciones();
	}

	@Override
	public List<Compra> productoOferta() {
		return servicioAdminDao.productoOferta();
	}

	@Override
	public List<Categoria> listarCategorias() {
		return servicioAdminDao.listarCategorias();
	}

	@Override
	public List<Proveedor> listarProveedores() {
		return servicioAdminDao.listarProveedores();
	}

	@Override
	public void insertarProducto(Productos producto, Long idCategoria, CommonsMultipartFile file) {
		servicioAdminDao.insertarProducto(producto, idCategoria,file);
	}

	@Override
	public List<Notificacion> buscarProductosStockMinimo() {
		return servicioAdminDao.buscarProductosStockMinimo();
	}

	@Override
	public List<Notificacion> buscarProductosVencidos() {
		return servicioAdminDao.buscarProductosVencidos();
	}

	@Override
	public List<Notificacion> buscarProductosEnOferta() {
		return servicioAdminDao.buscarProductosEnOferta();
	}

	@Override
	public void insertarUsuarioAlCarrito(CarritoCompras carrito, Usuario usuario) {
		servicioAdminDao.insertarUsuarioAlCarrito(carrito, usuario);
	}

	@Override
	public List<Localidades> listarLocalidades() {
		return servicioAdminDao.listarLocalidades();
	}

	@Override
	public Direccion guardarDireccionDeCompra(Direccion direccion, Long idLocalidad) {
		return servicioAdminDao.guardarDireccionDeCompra(direccion, idLocalidad);
	}

	@Override
	public void agregarDireccionAlCarrito(CarritoCompras carrito, Direccion direccionTabla) {
		servicioAdminDao.agregarDireccionAlCarrito(carrito, direccionTabla);
	}

	@Override
	public List<CarritoCompras> buscarCarritosCompra() {
		return servicioAdminDao.buscarCarritosCompra();
	}

	@Override
	public List<DetalleVenta> listarDetallesDeVentaConIdCarrito(Long id) {
		return servicioAdminDao.listarDetallesDeVentaConIdCarrito(id);
	}

	@Override
	public CarritoCompras buscarCarritoComprasConId(Long id) {
		return servicioAdminDao.buscarCarritoComprasConId(id);
	}
	@Override
	public List<PedidoProducto> devolverNotificacionesDePocoStockComoPedidos(List<Notificacion> lista) {
		List<PedidoProducto> listadoPedidoProductos = new ArrayList<>();
		for(Notificacion notificacion: lista){
			PedidoProducto pp = new PedidoProducto(notificacion.getProducto().getId(),notificacion);
			listadoPedidoProductos.add(pp);
		}
		return listadoPedidoProductos;
	}

	@Override
	public Boolean enviarCarrito(Long id) {
		return servicioAdminDao.enviarCarrito(id);
	}

	@Override
	public List<Productos> buscarProductosRecomendados(Recomendacion rec) {
		return servicioAdminDao.buscarProductosRecomendados(rec);
	}

	@Override
	public List<CarritoCompras> buscarCarritosCompraConfirmados() {
		return servicioAdminDao.buscarCarritosCompraConfirmados();
	}

}
