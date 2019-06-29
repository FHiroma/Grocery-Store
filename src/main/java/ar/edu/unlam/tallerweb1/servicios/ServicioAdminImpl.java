package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.dao.AdminDao;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Proveedor;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;

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
	public void insertarProducto(Productos producto, Long idCategoria, Long idProveedor) {
		servicioAdminDao.insertarProducto(producto, idCategoria, idProveedor);
	}
}
