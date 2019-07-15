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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.DetalleVenta;
import ar.edu.unlam.tallerweb1.modelo.Localidades;
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
		List<Compra> listaDeOfertas= servicioUser.verProductosEnOferta();
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
	
	@RequestMapping("/registro")
	public ModelAndView irARegistrarNuevaCuenta(){
		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		List<Localidades> localidades = servicioAdmin.listarLocalidades();
		modelo.put("usuario", usuario);
		modelo.put("localidades", localidades);
		return new ModelAndView("registrarUsuario", modelo);
	}
	
	@RequestMapping(path="/registrarCuenta", method = RequestMethod.POST)
	public ModelAndView registrarNuevaCuenta(@RequestParam Long localidad,
			 @ModelAttribute("usuario") Usuario usuario){
		
		ModelMap modelo = new ModelMap();
		List<Localidades> localidades = servicioAdmin.listarLocalidades();
		modelo.put("usuario", usuario);
		modelo.put("localidades", localidades);
		return new ModelAndView("registrarUsuario", modelo);
	}
	
	@RequestMapping("/MiCuenta")
	public ModelAndView verInformacionPersonal(HttpServletRequest request){
		ModelMap model= new ModelMap();
		Long id= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(id);
		if(u != null) {
			model.put("usuario", u);
			HttpSession session = request.getSession();
			String rol = (String) request.getSession().getAttribute("rol");
			if (rol == null) {
				session.invalidate();
				return new ModelAndView("redirect:/login");
			}
			if ("user".equals(rol)) {
				return new ModelAndView("informacionDeLaCuenta", model);
			}
		} else {
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login2", model);
	}
}
