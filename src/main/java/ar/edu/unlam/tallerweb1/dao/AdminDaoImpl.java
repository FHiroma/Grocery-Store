package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Proveedor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Localidades;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;

@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {

	@Inject
	private SessionFactory sessionFactory;

	@Override
	public List<Productos> listarProductosDisponibles() {
		final Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Productos> listaProductos = session.createCriteria(Productos.class).list();
		return listaProductos;
	}

	@Override
	public void publicarProducto(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto = (Productos) session.createCriteria(Productos.class).add(Restrictions.eq("id", id))
				.uniqueResult();
		producto.setEstado(true);
	}

	@Override
	public void quitarProducto(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto = (Productos) session.createCriteria(Productos.class).add(Restrictions.eq("id", id))
				.uniqueResult();
		producto.setEstado(false);
	}

	@Override
	public List<Productos> verProductosOferta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarProducto(Productos producto, Long idCategoria) {
		final Session session = sessionFactory.getCurrentSession();
		Categoria categoria = (Categoria) session.createCriteria(Categoria.class)
				.add(Restrictions.eq("id", idCategoria)).uniqueResult();
		session.createCriteria(Productos.class).add(Restrictions.eq("descripcion", producto.getDescripcion()))
				.uniqueResult();
		producto.setCategoria(categoria);
		producto.setEstado(true);
		producto.setStock(0);
		session.save(producto);
	}

	@Override
	public Productos buscarProducto(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto = (Productos) session.createCriteria(Productos.class).add(Restrictions.eq("id", id))
				.uniqueResult();
		return producto;
	}

	@Override
	public void insertarStock(Compra stock, Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto= (Productos)session.createCriteria(Productos.class)
		.add(Restrictions.eq("id", id))
		.uniqueResult();
		if(producto != null) {
				stock.setProducto(producto);
				stock.setFechaIngreso();
				stock.setFechaVencimiento();
				stock.setOferta(false);
				stock.setVencido(false);
				session.save(stock);
			}
	}

	@Override
	public void aumentarStockProducto(Integer cantidad, Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto = (Productos) session.createCriteria(Productos.class).add(Restrictions.eq("id", id))
				.uniqueResult();
		producto.setStock(producto.getStock() + cantidad);
		final Session session1 = sessionFactory.getCurrentSession();
		session1.update(producto);
	}

	@Override
	public List<Notificacion> buscarNotificaciones() {
		final Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Notificacion> listaNotificaciones = session.createCriteria(Notificacion.class)
				.add(Restrictions.eq("estado", false)).list();
		return listaNotificaciones;
	}

	@Override
	public List<Compra> productoOferta() {
		final Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Compra> listaOfertas = session.createCriteria(Compra.class).add(Restrictions.eq("oferta", true)).list();
		return listaOfertas;
	}

	@Override
	public List<Categoria> listarCategorias() {
		@SuppressWarnings("unchecked")
		List<Categoria> listaCategorias = sessionFactory.getCurrentSession().createCriteria(Categoria.class).list();
		return listaCategorias;
	}

	@Override
	public List<Proveedor> listarProveedores() {
		@SuppressWarnings("unchecked")
		List<Proveedor> listaProveedores = sessionFactory.getCurrentSession().createCriteria(Proveedor.class).list();
		listaProveedores.size();
		return listaProveedores;
	}

	@Override
	public List<Notificacion> buscarProductosStockMinimo() {
		@SuppressWarnings("unchecked")
		List<Notificacion> productosStockMinimo= sessionFactory.getCurrentSession().createCriteria(Notificacion.class)
				.add(Restrictions.eq("descripcion", "Stock Minimo")).list();
		return productosStockMinimo;
	}

	@Override
	public List<Notificacion> buscarProductosVencidos() {
		@SuppressWarnings("unchecked")
		List<Notificacion> productosVencidos= sessionFactory.getCurrentSession().createCriteria(Notificacion.class)
				.add(Restrictions.eq("descripcion", "Producto Vencido")).list();
		return productosVencidos;
	}

	@Override
	public List<Notificacion> buscarProductosEnOferta() {
		@SuppressWarnings("unchecked")
		List<Notificacion> productosEnOferta= sessionFactory.getCurrentSession().createCriteria(Notificacion.class)
				.add(Restrictions.eq("descripcion", "Producto en oferta")).list();
		return productosEnOferta;
	}

	@Override
	public void insertarUsuarioAlCarrito(CarritoCompras carrito, Usuario usuario) {
		carrito.setUsuario(usuario);
		sessionFactory.getCurrentSession().update(carrito);
	}

	@Override
	public List<Localidades> listarLocalidades() {
		@SuppressWarnings("unchecked")
		List<Localidades> localidades = sessionFactory.getCurrentSession().createCriteria(Localidades.class).list();
		return localidades;
	}

	@Override
	public void agregarDireccionAlCarrito(CarritoCompras carrito, Direccion direccionTabla) {
		carrito.setDireccion(direccionTabla);
		sessionFactory.getCurrentSession().update(carrito);
	}

	@Override
	public Direccion guardarDireccionDeCompra(Direccion direccion, Long idLocalidad) {
		Localidades localidad= (Localidades) sessionFactory.getCurrentSession()
				.createCriteria(Localidades.class)
				.add(Restrictions.eq("id", idLocalidad))
				.uniqueResult();
		if (localidad != null) {
			direccion.setLocalidad(localidad);
			sessionFactory.getCurrentSession().save(direccion);
			return direccion;
		} else {
			return null;
		}
	}
}
