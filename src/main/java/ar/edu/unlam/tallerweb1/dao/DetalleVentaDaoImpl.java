package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.DetalleVenta;
import ar.edu.unlam.tallerweb1.modelo.Productos;

@Repository("detalleVentaDao")
public class DetalleVentaDaoImpl implements DetalleVentaDao {
	
	@Inject
    private SessionFactory sessionFactory;

	@Override
	public void registrarDetalle(DetalleVenta detalle) {
		sessionFactory.getCurrentSession().save(detalle);
	}

	@Override
	public Boolean existe(CarritoCompras carrito, Productos producto) {
		@SuppressWarnings("unchecked")
		List<DetalleVenta> Listadetalle= sessionFactory.getCurrentSession()
				.createCriteria(DetalleVenta.class)
				.add(Restrictions.eq("carritoCompras", carrito))
				.add(Restrictions.eq("producto", producto))
				.list();
		if(Listadetalle != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public DetalleVenta buscarDetalleVentaConCarritoProducto(CarritoCompras carrito, Productos producto) {
		@SuppressWarnings("unchecked")
		List<DetalleVenta> detalleVenta= (List<DetalleVenta>) sessionFactory.getCurrentSession()
				.createCriteria(DetalleVenta.class)
				.add(Restrictions.eq("producto", producto))
				.add(Restrictions.eq("carritoCompras", carrito))
				.list();
		return detalleVenta.get(0);
	}

	@Override
	public void actualizarDetalleVenta(DetalleVenta detalle) {
		sessionFactory.getCurrentSession().update(detalle);
	}

	@Override
	public List<DetalleVenta> traerCarritoCompras(CarritoCompras carrito) {
		@SuppressWarnings("unchecked")
		List<DetalleVenta> lista= sessionFactory.getCurrentSession()
				.createCriteria(DetalleVenta.class)
				.add(Restrictions.eq("carritoCompras", carrito))
				.list();
		return lista;
	}

	@Override
	public void eliminarDetalleVenta(Long id, CarritoCompras carrito) {
		DetalleVenta detalle=(DetalleVenta) sessionFactory.getCurrentSession()
				.createCriteria(DetalleVenta.class)
				.add(Restrictions.eq("carritoCompras", carrito))
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		if(detalle != null) {
			sessionFactory.getCurrentSession().delete(detalle);
		}
	}

	@Override
	public Boolean modificarCantidadDeUnProductoDelCarrito(Long id, CarritoCompras carrito, Integer cantidad) {
		DetalleVenta detalle = (DetalleVenta) sessionFactory.getCurrentSession().createCriteria(DetalleVenta.class)
				.add(Restrictions.eq("carritoCompras", carrito)).add(Restrictions.eq("id", id)).uniqueResult();
		@SuppressWarnings("unchecked")
		DetalleVenta detalleVenta = (DetalleVenta) sessionFactory.getCurrentSession()
				.createCriteria(DetalleVenta.class).add(Restrictions.eq("producto", detalle.getProducto()))
				.add(Restrictions.eq("oferta", true)).add(Restrictions.eq("carritoCompras", carrito))
				.uniqueResult();
		Productos producto = detalle.getProducto();
		if(detalleVenta != null && cantidad >= producto.getStock()) {
			detalle.setCantidad(cantidad);
			detalle.setOferta(false);
			detalle.setSubtotal(detalle.getCantidad() * detalle.getProducto().getPrecio());
			sessionFactory.getCurrentSession().update(detalle);
			return true;
		}
		if (detalle != null && cantidad <= producto.getStock() + producto.getStockDeOferta()) {
			Integer dif = cantidad;
			if (detalleVenta == null && detalle.getOferta()==false) {
				if (cantidad >= producto.getStockDeOferta()) {
					dif = cantidad - producto.getStockDeOferta();
					DetalleVenta detalleVentaOferta = new DetalleVenta();
					detalleVentaOferta.setProducto(producto);
					detalleVentaOferta.setCantidad(producto.getStockDeOferta());
					detalleVentaOferta.setCarritoCompras(carrito);
					detalleVentaOferta.setOferta(true);
					detalleVentaOferta.setSubtotal((detalle.getProducto().getPrecio() * detalleVentaOferta.getCantidad()) / 2);
					sessionFactory.getCurrentSession().save(detalleVentaOferta);
				}
			}
			if (detalleVenta != null && cantidad >= producto.getStockDeOferta()) {
				detalleVenta.setProducto(producto);
				detalleVenta.setCantidad(producto.getStockDeOferta());
				detalleVenta.setCarritoCompras(carrito);
				detalleVenta.setSubtotal((detalle.getProducto().getPrecio() * detalleVenta.getCantidad()) / 2);
				sessionFactory.getCurrentSession().update(detalleVenta);
			}
			if (dif > 0) {
				detalle.setCantidad(dif);
				detalle.setOferta(false);
				detalle.setSubtotal(detalle.getCantidad() * detalle.getProducto().getPrecio());
				sessionFactory.getCurrentSession().update(detalle);
			} else {
				sessionFactory.getCurrentSession().delete(detalle);
			}
			return true;
		} else {
			return false;
		}
	}
}
