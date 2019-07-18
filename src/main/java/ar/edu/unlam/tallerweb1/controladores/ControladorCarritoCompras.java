package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mercadopago.resources.Preference;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.DetalleVenta;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Localidades;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAdmin;
import ar.edu.unlam.tallerweb1.servicios.ServicioDetalleVenta;
import ar.edu.unlam.tallerweb1.servicios.ServicioMercadoPago;
import ar.edu.unlam.tallerweb1.servicios.ServicioUser;

@Controller
public class ControladorCarritoCompras {
	
	@Inject
	private ServicioAdmin servicioAdmin;
	@Inject
	private ServicioDetalleVenta servicioDetalleVenta;
	@Inject
	private ServicioUser servicioUsuario;
	@Inject
	private ServicioMercadoPago servicioMercadoPago;
	
	@RequestMapping("/agregar-carrito")
	public ModelAndView agregarAlCarrito(HttpServletRequest request, @RequestParam ("id") Long id) {
		ModelMap model= new ModelMap();
		Productos producto= servicioAdmin.buscarProducto(id);
		if (request.getSession().getAttribute("carrito") == null) {
			Long idU= (Long) request.getSession().getAttribute("id");
			Usuario u= servicioUsuario.buscarUsuarioPorId(idU);
			if(u != null) {
				model.put("usuario", u);
			}
			CarritoCompras carrito = new CarritoCompras();
			servicioUsuario.guardarCarritoVacio(carrito);
			DetalleVenta detalle = new DetalleVenta();
			detalle.setCarritoCompras(carrito);
			detalle.setProducto(producto);
			detalle.setSubtotal(producto.getPrecio());
			detalle.setCantidad(1);
			servicioDetalleVenta.registrarDetalle(detalle);
			List<DetalleVenta> lista= servicioDetalleVenta.traerCarritoCompras(carrito);
			model.put("carrito", lista);
			Integer cantidad= lista.size();
			model.put("cantidad", cantidad);
			request.getSession().setAttribute("carrito", carrito);
		}else {
			CarritoCompras carrito = (CarritoCompras) request.getSession().getAttribute("carrito");
			Boolean estado=servicioDetalleVenta.existe(carrito, producto);
			if(estado == false) {
				Long idU= (Long) request.getSession().getAttribute("id");
				Usuario u= servicioUsuario.buscarUsuarioPorId(idU);
				if(u != null) {
					model.put("usuario", u);
				}
				DetalleVenta detalle = new DetalleVenta();
				detalle.setCarritoCompras(carrito);
				detalle.setProducto(producto);
				detalle.setSubtotal(producto.getPrecio());
				detalle.setCantidad(1);
				servicioDetalleVenta.registrarDetalle(detalle);
				List<DetalleVenta> lista= servicioDetalleVenta.traerCarritoCompras(carrito);
				Integer cantidad= lista.size();
				model.put("cantidad", cantidad);
				model.put("carrito", lista);
			} else {
				Long idU= (Long) request.getSession().getAttribute("id");
				Usuario u= servicioUsuario.buscarUsuarioPorId(idU);
				if(u != null) {
					model.put("usuario", u);
				}
				DetalleVenta detalle= servicioDetalleVenta.buscarDetalleVentaConCarritoProducto(carrito, producto);
				detalle.setCantidad(detalle.getCantidad()+1);
				detalle.setSubtotal(detalle.getSubtotal()+producto.getPrecio());
				servicioDetalleVenta.actualizarDetalleVenta(detalle);	
				List<DetalleVenta> lista= servicioDetalleVenta.traerCarritoCompras(carrito);
				Integer cantidad= lista.size();
				model.put("cantidad", cantidad);
				model.put("carrito", lista);
			}
		}
			return new ModelAndView("lala", model);
	}
	
	@RequestMapping(value = "eliminar-producto-carrito", method = RequestMethod.GET)
	public ModelAndView remove(@RequestParam("id") Long id, HttpServletRequest request) {
		ModelMap modelo= new ModelMap();
		Productos producto= servicioAdmin.buscarProducto(id);
		CarritoCompras carrito=(CarritoCompras) request.getSession().getAttribute("carrito");
		servicioDetalleVenta.eliminarDetalleVenta(producto, carrito);
		List<DetalleVenta> lista= servicioDetalleVenta.traerCarritoCompras(carrito);
		modelo.put("carrito", lista);
		Integer cantidad= lista.size();
		modelo.put("cantidad", cantidad);
		return new ModelAndView("lala", modelo);
	}
	
	@RequestMapping(value = "modificar-cantidad-producto", method = RequestMethod.GET)
	public ModelAndView disminuir(@RequestParam("id") Long id,@RequestParam("cantidad") Integer cantidad, HttpServletRequest request) {
		ModelMap modelo= new ModelMap();
		Productos producto= servicioAdmin.buscarProducto(id);
		CarritoCompras carrito=(CarritoCompras) request.getSession().getAttribute("carrito");
		Boolean valor= servicioDetalleVenta.modificarCantidadDeUnProductoDelCarrito(producto, carrito, cantidad);
		if(valor == true) {
			List<DetalleVenta> lista= servicioDetalleVenta.traerCarritoCompras(carrito);
			Integer cantidadP= lista.size();
			modelo.put("cantidad", cantidadP);
			modelo.put("carrito", lista);
		} else {
			List<DetalleVenta> lista= servicioDetalleVenta.traerCarritoCompras(carrito);
			modelo.put("carrito", lista);
			Integer cantidadP= lista.size();
			modelo.put("producto", producto);
			modelo.put("cantidad", cantidadP);
			modelo.put("error", "Stock insuficiente");
		}
		return new ModelAndView("lala", modelo);
	}
	
	@RequestMapping(value = "entrega", method = RequestMethod.GET)
	public ModelAndView verificarSesion(HttpServletRequest request) {
		Long id= (Long) request.getSession().getAttribute("id");
		ModelMap modelo= new ModelMap();
		if(id != null) {
			Usuario usuario=servicioUsuario.buscarUsuarioPorId(id);
			CarritoCompras carrito=(CarritoCompras) request.getSession().getAttribute("carrito");
			servicioAdmin.insertarUsuarioAlCarrito(carrito, usuario);
			Direccion direccion= new Direccion();
			modelo.put("direccion", direccion);
			List<Localidades> localidades= servicioAdmin.listarLocalidades();
			modelo.put("localidades", localidades);
		} else {
			Direccion direccion= new Direccion();
			modelo.put("direccion", direccion);
			List<Localidades> localidades= servicioAdmin.listarLocalidades();
			modelo.put("localidades", localidades);
		}
		return new ModelAndView("entrega", modelo);
	}
	
	@RequestMapping(value = "verificar-direccion", method = RequestMethod.POST)
    public ModelAndView verificarDireccion(@ModelAttribute ("direccion") Direccion direccion
                                            , @ModelAttribute ("idLocalidad") Long idLocalidad
                                            , HttpServletRequest request) {
        Direccion direccionTabla=servicioAdmin.guardarDireccionDeCompra(direccion, idLocalidad);
        ModelMap modelo = new ModelMap();
        CarritoCompras carrito=(CarritoCompras) request.getSession().getAttribute("carrito");
        servicioAdmin.agregarDireccionAlCarrito(carrito, direccionTabla);
        List<DetalleVenta> lista= servicioDetalleVenta.traerCarritoCompras(carrito);
        Preference p = servicioMercadoPago.traerPreferenciasParaMercadoPago(lista,carrito);
        modelo.put("mercadopago", p);
        HttpSession session = request.getSession();
		if( session != null){
		request.removeAttribute("carrito");
		session.invalidate();
		}
        return new ModelAndView("vistaMercadoPago",modelo);
    }
}
