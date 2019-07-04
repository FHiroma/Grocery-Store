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
		DetalleVenta detalle= (DetalleVenta) sessionFactory.getCurrentSession()
				.createCriteria(DetalleVenta.class)
				.add(Restrictions.eq("carritoCompras", carrito))
				.add(Restrictions.eq("producto", producto))
				.uniqueResult();
		if(detalle != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public DetalleVenta buscarDetalleVentaConCarritoProducto(CarritoCompras carrito, Productos producto) {
		DetalleVenta detalleVenta= (DetalleVenta) sessionFactory.getCurrentSession()
				.createCriteria(DetalleVenta.class)
				.add(Restrictions.eq("producto", producto))
				.add(Restrictions.eq("carritoCompras", carrito))
				.uniqueResult();
		return detalleVenta;
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
	public void eliminarDetalleVenta(Productos producto, CarritoCompras carrito) {
		DetalleVenta detalle=(DetalleVenta) sessionFactory.getCurrentSession()
				.createCriteria(DetalleVenta.class)
				.add(Restrictions.eq("carritoCompras", carrito))
				.add(Restrictions.eq("producto", producto))
				.uniqueResult();
		if(detalle != null) {
			sessionFactory.getCurrentSession().delete(detalle);
		}
	}

	@Override
	public DetalleVenta disminuirProductoCarrito(Productos producto, CarritoCompras carrito) {
		DetalleVenta detalle=(DetalleVenta) sessionFactory.getCurrentSession()
				.createCriteria(DetalleVenta.class)
				.add(Restrictions.eq("carritoCompras", carrito))
				.add(Restrictions.eq("producto", producto))
				.uniqueResult();
		if(detalle != null) {
			detalle.setCantidad(detalle.getCantidad() - 1);
			detalle.setSubtotal(detalle.getSubtotal() - producto.getPrecio());
			sessionFactory.getCurrentSession().update(detalle);
			return detalle;
		}
		return null;
	}

}
