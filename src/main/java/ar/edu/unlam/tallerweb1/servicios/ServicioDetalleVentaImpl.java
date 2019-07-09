package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.DetalleVentaDao;
import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.DetalleVenta;
import ar.edu.unlam.tallerweb1.modelo.Productos;

@Service("servicioDetalleVenta")
@Transactional
public class ServicioDetalleVentaImpl implements ServicioDetalleVenta {
	
	@Inject
	private DetalleVentaDao servicioDetalleVentaDao;

	@Override
	public void registrarDetalle(DetalleVenta detalle) {
		servicioDetalleVentaDao.registrarDetalle(detalle);
	}

	@Override
	public Boolean existe(CarritoCompras carrito, Productos producto) {
		return servicioDetalleVentaDao.existe(carrito, producto);
	}

	@Override
	public DetalleVenta buscarDetalleVentaConCarritoProducto(CarritoCompras carrito, Productos producto) {
		return servicioDetalleVentaDao.buscarDetalleVentaConCarritoProducto(carrito, producto);
	}

	@Override
	public void actualizarDetalleVenta(DetalleVenta detalle) {
		servicioDetalleVentaDao.actualizarDetalleVenta(detalle);
	}

	@Override
	public List<DetalleVenta> traerCarritoCompras(CarritoCompras carrito) {
		return servicioDetalleVentaDao.traerCarritoCompras(carrito);
	}

	@Override
	public void eliminarDetalleVenta(Productos producto, CarritoCompras carrito) {
		servicioDetalleVentaDao.eliminarDetalleVenta(producto, carrito);
	}

	@Override
	public Boolean modificarCantidadDeUnProductoDelCarrito(Productos producto, CarritoCompras carrito, Integer cantidad) {
		 return servicioDetalleVentaDao.modificarCantidadDeUnProductoDelCarrito(producto, carrito, cantidad);
	}

}
