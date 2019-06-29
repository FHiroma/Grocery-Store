package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;

@Repository("adminDao")
public class AdminDaoImpl implements AdminDao{
	
	@Inject
    private SessionFactory sessionFactory;

	@Override
	public List<Productos> listarProductosDisponibles() {
		final Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Productos> listaProductos= session.createCriteria(Productos.class)
		.list();
		return listaProductos;
	}

	@Override
	public void publicarProducto(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto= (Productos) session.createCriteria(Productos.class)
		.add(Restrictions.eq("id", id))
		.uniqueResult();
		producto.setEstado(true);
	}

	@Override
	public void quitarProducto(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto= (Productos) session.createCriteria(Productos.class)
		.add(Restrictions.eq("id", id))
		.uniqueResult();
		producto.setEstado(false);
	}

	@Override
	public List<Productos> verProductosOferta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarProducto(Productos producto, Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Categoria categoria= (Categoria) session.createCriteria(Categoria.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		session.createCriteria(Productos.class)
				.add(Restrictions.eq("descripcion", producto.getDescripcion()))
				.uniqueResult();
		producto.setCategoria(categoria);
		producto.setEstado(true);
		producto.setStock(0);
		session.save(producto);
	}

	@Override
	public Productos buscarProducto(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto=(Productos) session.createCriteria(Productos.class)
			.add(Restrictions.eq("id", id))
			.uniqueResult();
		return producto;
	}

	@Override
	public void insertarStock(Compra stock, Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto=(Productos) session.createCriteria(Productos.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		stock.setProducto(producto);	
		stock.setFechaVencimiento();
		stock.setOferta(false);
		stock.setFechaIngreso();
		stock.setVencido(false);
		session.save(stock);
	}

	@Override
	public void aumentarStockProducto(Integer cantidad, Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto=(Productos) session.createCriteria(Productos.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		producto.setStock(producto.getStock()+cantidad);
		final Session session1 = sessionFactory.getCurrentSession();
		session1.update(producto);
	}

	@Override
	public List<Notificacion> buscarNotificaciones() {
		final Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Notificacion> listaNotificaciones=session.createCriteria(Notificacion.class)
				.add(Restrictions.eq("estado", false))
				.list();
		return listaNotificaciones;
	}

	@Override
	public List<Compra> productoOferta() {
		final Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Compra> listaOfertas= session.createCriteria(Compra.class)
				.add(Restrictions.eq("oferta", true))
				.list();
		return listaOfertas;
	}

	@Override
	public List<Categoria> listarCategorias() {
		@SuppressWarnings("unchecked")
		List<Categoria> listaCategorias= sessionFactory.getCurrentSession()
				.createCriteria(Categoria.class)
				.list();
		return listaCategorias;
	}
}
