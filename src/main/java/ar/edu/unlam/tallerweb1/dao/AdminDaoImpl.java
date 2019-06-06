package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Compra;

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
	public void insertarProducto(Productos producto) {
		final Session session = sessionFactory.getCurrentSession();
		session.createCriteria(Productos.class)
				.add(Restrictions.eq("descripcion", producto.getDescripcion()))
				.uniqueResult();
		producto.setEstado(true);
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
		session.save(stock);
	}
}
