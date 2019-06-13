package ar.edu.unlam.tallerweb1.dao;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Productos;

@Repository("promoDao")
public class PromocionesDaoImpl implements PromocionesDao{
	

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public void autoPromocionar() {
		Integer i = 432000000;
		Calendar fechaLimite = Calendar.getInstance();
		fechaLimite.add(Calendar.MILLISECOND, i);
		Session sesion = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Compra> lista = sesion.createCriteria(Compra.class)
				.add(Restrictions.le("fechaVencimiento", fechaLimite))
				.list();
		for(Compra compra: lista){
			@SuppressWarnings("unchecked")
			List<Notificacion> listaNotificacion= sesion.createCriteria(Notificacion.class)
			.add(Restrictions.eq("producto", compra.getProducto()))
			.add(Restrictions.eq("descripcion", "Producto en oferta"))
			.list();
			if(listaNotificacion.size()==0) {
				compra.setOferta(true);
				sesion.update(compra);
				Notificacion notificacion = new Notificacion();
				notificacion.setDescripcion("Producto en oferta");
				notificacion.setEstado(false);
				notificacion.setProducto(compra.getProducto());
				sesion.save(notificacion);
			
				}
			}
	}

	@Override
	public void productosPocoStock() {
		Session sesion = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Productos> listaProductos= sesion.createCriteria(Productos.class)
			.list();
		for(Productos producto: listaProductos) {
			if(producto.getStock()<=5) {
				Notificacion n= new Notificacion();
				n.setProducto(producto);
				n.setEstado(false);
				n.setDescripcion("Stock Minimo");
				Session sesion1 = sessionFactory.getCurrentSession();
				@SuppressWarnings("unchecked")
				List<Notificacion> listaNotificacion= sesion1.createCriteria(Notificacion.class)
				.add(Restrictions.eq("producto", producto))
				.add(Restrictions.eq("descripcion", "Stock Minimo"))
				.list();
				if(listaNotificacion.size()==0) {
					Session sesion2 = sessionFactory.getCurrentSession();
					sesion2.save(n);
				}
			}
		}
	}

}
