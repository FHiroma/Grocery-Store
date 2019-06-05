package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.modelo.Productos;

@Repository("adminDao")
public class AdminDaoImpl implements AdminDao{
	
	@Inject
    private SessionFactory sessionFactory;

	@Override
	public List<Productos> listarProductosDisponibles() {
		final Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Productos> listaProductos= session.createCriteria(Productos.class)
		.add(Restrictions.gt("stock",0))
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
}
