package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.modelo.Productos;

@Repository("busquedaDao")
public class BusquedaDaoImpl implements BusquedaDao{


	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public List<Productos> listadoPosiblesResultados(String nombre) {
		final Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Productos> producto= session.createCriteria(Productos.class)
		.add(Restrictions.like("descripcion", nombre, MatchMode.ANYWHERE))
		.add(Restrictions.eq("estado", true))
		.list();
		return producto;
	}

	@Override
	public Productos traerUnProducto(String nombre) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto= (Productos) session.createCriteria(Productos.class)
		.add(Restrictions.like("descripcion", nombre, MatchMode.START))
		.add(Restrictions.eq("estado", true))
		.uniqueResult();
		return producto;
	}
	

}
