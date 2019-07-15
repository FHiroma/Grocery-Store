package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.DetalleVenta;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAdmin;
import ar.edu.unlam.tallerweb1.servicios.ServicioUser;

@Controller
public class ControladorUser {
	
	@Inject
	private ServicioUser servicioUser;
	@Inject
	private ServicioAdmin servicioAdmin;
	
	@RequestMapping("/ver-productos-en-oferta")
	public ModelAndView verProductosEnOferta(HttpServletRequest request) {
		List<Productos> listaDeOfertas= servicioUser.verProductosEnOferta();
		ModelMap model= new ModelMap();
		Long id= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(id);
		if(u != null) {
			model.put("usuario", u);
		}
		CarritoCompras carrito = (CarritoCompras) request.getSession().getAttribute("carrito");
		if(carrito != null) {
			List<DetalleVenta> lista= servicioAdmin.listarDetallesDeVentaConIdCarrito(carrito.getId());
			Integer cantidad= lista.size();
			model.put("cantidad", cantidad);
			model.put("carrito", carrito);
		}
		model.put("listaOfertas", listaDeOfertas);
		return new ModelAndView("vistaOfertasUsuario", model);
	}
	
	@RequestMapping("/categoria")
	public ModelAndView verProductosDeLaCategoria(@RequestParam ("id") Long id, HttpServletRequest request) {
		ModelMap modelo= new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		List<Productos> listaProductos= servicioUser.listarProductosDeLaCategoriaDeId(id);
		modelo.put("listaProductos", listaProductos);
		return new ModelAndView("vista-productos-una-categoria", modelo);
	}

}
