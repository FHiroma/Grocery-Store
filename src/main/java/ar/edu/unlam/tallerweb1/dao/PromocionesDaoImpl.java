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
import ar.edu.unlam.tallerweb1.modelo.ProductoOrdenCompra;
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
			Productos producto= compra.getProducto();
			if(listaNotificacion.size()==0) {
				Productos productoOferta= new Productos();
				productoOferta.setDescripcion(compra.getProducto().getDescripcion() + "Oferta");
				productoOferta.setOferta(true);
				productoOferta.setStockDeOferta(compra.getStock());
				productoOferta.setDiasCaducidad(compra.getProducto().getDiasCaducidad());
				productoOferta.setEstado(compra.getProducto().getEstado());
				productoOferta.setPrecio((compra.getProducto().getPrecio()/2));
				productoOferta.setImagen(compra.getProducto().getImagen());
				productoOferta.setCategoria(compra.getProducto().getCategoria());
				sessionFactory.getCurrentSession().save(productoOferta);
				compra.setOferta(true);
				compra.setProducto(productoOferta);
				producto.setStock(producto.getStock() - productoOferta.getStockDeOferta());
				sesion.update(compra);
				sesion.update(producto);
				Notificacion notificacion = new Notificacion();
				notificacion.setDescripcion("Producto en oferta");
				notificacion.setEstado(false);
				notificacion.setProducto(productoOferta);
				sesion.save(notificacion);
				}
			}
	}

	@Override
	public void productosPocoStock() {
		Session sesion = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Productos> listaProductos = sesion.createCriteria(Productos.class)
						.add(Restrictions.eq("oferta", false))
						.list();
		for (Productos producto : listaProductos) {
			if (producto.getStock() <= 5) {
				Notificacion n = new Notificacion();
				n.setProducto(producto);
				n.setEstado(false);
				n.setDescripcion("Stock Minimo");
				Session sesion1 = sessionFactory.getCurrentSession();
				@SuppressWarnings("unchecked")
				List<Notificacion> listaNotificacion = sesion1.createCriteria(Notificacion.class)
						.add(Restrictions.eq("producto", producto))
						.add(Restrictions.eq("descripcion", "Stock Minimo"))
						.add(Restrictions.eq("estado",false))
						.list();
				List<ProductoOrdenCompra> poc = sesion1.createCriteria(ProductoOrdenCompra.class)
												.add(Restrictions.eq("producto", producto))
												.createAlias("ordenCompra", "ordenCompraAlias")
												.add(Restrictions.eq("ordenCompraAlias.estado", true))
												.list();
				if (listaNotificacion.size() == 0 && poc.size() == 0) {
					Session sesion2 = sessionFactory.getCurrentSession();
					sesion2.save(n);
				}
			}
		}
	}

	@Override
	public void productosVencidos() {
		Session sesion = sessionFactory.getCurrentSession();
		Calendar fecha= Calendar.getInstance();
		fecha.set(Calendar.HOUR_OF_DAY, 0);
		fecha.set(Calendar.MINUTE, 0);
		fecha.set(Calendar.SECOND, 0);
		fecha.set(Calendar.MILLISECOND, 0);
		@SuppressWarnings("unchecked")
		List<Compra> listaCompras= sesion.createCriteria(Compra.class)
				.list();
		for(Compra i:listaCompras) {
			if(i.getFechaVencimiento().equals(fecha)) {
				Compra compra= (Compra) sesion.createCriteria(Compra.class)
						.add(Restrictions.eq("id", i.getId()))
						.add(Restrictions.eq("vencido", false))
						.uniqueResult();
				if(compra != null) {
				compra.setVencido(true);
				Productos producto= compra.getProducto();
				if(producto.getOferta() == true) {
					producto.setStockDeOferta(producto.getStockDeOferta() - compra.getStock());
					sessionFactory.getCurrentSession().update(producto);
					sessionFactory.getCurrentSession().update(compra);
				}
				@SuppressWarnings("unchecked")
				List<Notificacion> lista=(List<Notificacion>) sessionFactory.getCurrentSession()
						.createCriteria(Notificacion.class)
						.add(Restrictions.eq("producto", producto))
						.add(Restrictions.eq("descripcion", "Producto Vencido"))
						.list();
				if(lista.size()==0) {
					Notificacion n= new Notificacion();
					n.setDescripcion("Producto vencido");
					n.setEstado(false);
					n.setProducto(compra.getProducto());
					sessionFactory.getCurrentSession().save(n);
				}
//				ProductosVencidos productoVencido=  new ProductosVencidos();
//				productoVencido.setProducto(compra.getProducto());
//				productoVencido.setCantidad(compra.getStock());
//				productoVencido.setFechaCompra(compra.getFechaIngreso());
//				sessionFactory.getCurrentSession().save(productoVencido);
				}
			}	
		}
	}
}
