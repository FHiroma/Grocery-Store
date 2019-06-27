package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.DetalleVenta;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.servicios.ServicioAdmin;
import ar.edu.unlam.tallerweb1.servicios.ServicioDetalleVenta;
import ar.edu.unlam.tallerweb1.servicios.ServicioUser;

@Controller
public class ControladorCarritoCompras {
	
	@Inject
	private ServicioAdmin servicioAdmin;
	@Inject
	private ServicioDetalleVenta servicioDetalleVenta;
	@Inject
	private ServicioUser servicioUsuario;
	
	@RequestMapping("/agregar-carrito")
	public ModelAndView agregarAlCarrito(HttpServletRequest request, @RequestParam ("id") Long id, @RequestParam ("cantidad") Integer cantidad) {
		ModelMap model= new ModelMap();
		Productos producto= servicioAdmin.buscarProducto(id);
		if (request.getSession().getAttribute("carrito") == null) {
			CarritoCompras carrito = new CarritoCompras();
			servicioUsuario.guardarCarritoVacio(carrito);
			DetalleVenta detalle = new DetalleVenta();
			detalle.setCarritoCompras(carrito);
			detalle.setProducto(producto);
			detalle.setCantidad(cantidad);
			servicioDetalleVenta.registrarDetalle(detalle);
			List<DetalleVenta> lista= servicioDetalleVenta.traerCarritoCompras(carrito);
			model.put("carrito", lista);
			request.getSession().setAttribute("carrito", carrito);
		}else {
			CarritoCompras carrito = (CarritoCompras) request.getSession().getAttribute("carrito");
			Boolean estado=servicioDetalleVenta.existe(carrito, producto);
			if(estado == false) {
				DetalleVenta detalle = new DetalleVenta();
				detalle.setCarritoCompras(carrito);
				detalle.setProducto(producto);
				detalle.setCantidad(cantidad);
				servicioDetalleVenta.registrarDetalle(detalle);
				List<DetalleVenta> lista= servicioDetalleVenta.traerCarritoCompras(carrito);
				model.put("carrito", lista);
			} else {
				DetalleVenta detalle= servicioDetalleVenta.buscarDetalleVentaConCarritoProducto(carrito, producto);
				detalle.setCantidad(detalle.getCantidad() + cantidad);
				servicioDetalleVenta.actualizarDetalleVenta(detalle);	
				List<DetalleVenta> lista= servicioDetalleVenta.traerCarritoCompras(carrito);
				model.put("carrito", lista);
			}
		}
			return new ModelAndView("vista-carrito", model);
	}
	
	@RequestMapping(value = "eliminar-producto-carrito", method = RequestMethod.GET)
	public ModelAndView remove(@RequestParam("id") Long id, HttpServletRequest request) {
		ModelMap modelo= new ModelMap();
		Productos producto= servicioAdmin.buscarProducto(id);
		CarritoCompras carrito=(CarritoCompras) request.getSession().getAttribute("carrito");
		servicioDetalleVenta.eliminarDetalleVenta(producto, carrito);
		List<DetalleVenta> lista= servicioDetalleVenta.traerCarritoCompras(carrito);
		modelo.put("carrito", lista);
		return new ModelAndView("vista-carrito", modelo);
	}
}
