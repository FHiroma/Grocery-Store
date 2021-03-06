package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAdmin;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioUser;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.DetalleVenta;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Recomendacion;

@Controller
public class ControladorLogin {

	// La anotacion @Inject indica a Spring que en este atributo se debe setear (inyeccion de dependencias)
	// un objeto de una clase que implemente la interface ServicioLogin, dicha clase debe estar anotada como
	// @Service o @Repository y debe estar en un paquete de los indicados en applicationContext.xml
	@Inject
	private ServicioLogin servicioLogin;
	@Inject
	private ServicioUser servicioUser;
	@Inject
	private ServicioAdmin servicioAdmin;

	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es invocada por metodo http GET
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		// Se agrega al modelo un objeto del tipo Usuario con key 'usuario' para que el mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma  dentro del modelo
		return new ModelAndView("login2", modelo);
	}

	// Este metodo escucha la URL validar-login siempre y cuando se invoque con metodo http POST
	// El método recibe un objeto Usuario el que tiene los datos ingresados en el form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		// invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
		// hace una llamada a otro action a través de la URL correspondiente a ésta
		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		if (usuarioBuscado != null) {
			request.getSession().setAttribute("id", usuarioBuscado.getId());
			request.getSession().setAttribute("rol", usuarioBuscado.getRol());
			model.put("sesion", request);
			if("user".equals(usuarioBuscado.getRol())){
				return new ModelAndView("redirect:/homeUser",model);
				}
			if("admin".equals(usuarioBuscado.getRol())){
				return new ModelAndView("redirect:/homeAdmin", model);
				}
			} else {
				// si el usuario no existe agrega un mensaje de error en el modelo.
				model.put("error", "Usuario o clave incorrecta");
			}
			return new ModelAndView("login2", model);
		}
	
	@RequestMapping(path = "/homeUser", method = RequestMethod.GET)
	public ModelAndView irAHomeUser(HttpServletRequest request) {
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
			if (!"user".equals(rol)) {
				return new ModelAndView("redirect:/homeAdmin");
			}
			List<Categoria> listaCategorias=servicioAdmin.listarCategorias();
			List<Productos> listaProductos=servicioUser.verProductosDisponibles();
			model.put("listaCategorias", listaCategorias);
			model.put("listaProductos", listaProductos);
			return new ModelAndView("index",model);
		} else {
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login2", model);
	}

	@RequestMapping(path = "/homeAdmin", method = RequestMethod.GET)
	public ModelAndView irAHomeAdmin(HttpServletRequest request) {
		ModelMap model= new ModelMap();
		Long id= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(id);
		if(u != null) {
			model.put("usuario", u);
			String rol = (String) request.getSession().getAttribute("rol");
			HttpSession session = request.getSession();
			if (rol == null) {
				session.invalidate();
				return new ModelAndView("redirect:/login");
			}
			if (!"admin".equals(rol)) {
				return new ModelAndView("redirect:/homeUser");
			}
			List<Notificacion> listaNotificaciones=servicioAdmin.buscarNotificaciones();
			model.put("listaNotificaciones", listaNotificaciones);
			return new ModelAndView("homeAdmin", model);
		} else {
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login2", model);
	}

	// Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio(HttpServletRequest request) {
		ModelMap model= new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			String rol = (String) request.getSession().getAttribute("rol");
			if (!"user".equals(rol)) {
				return new ModelAndView("redirect:/homeAdmin");
				}
			model.put("usuario", u);
			Recomendacion rec= servicioUser.buscarRecomendacionDelUsuario(u);
			if(rec != null){
			List<Productos> listaRecomendados= servicioAdmin.buscarProductosRecomendados(rec);
			model.put("listaRecomendados", listaRecomendados);
			}
		} 
		CarritoCompras carrito = (CarritoCompras) request.getSession().getAttribute("carrito");
		if(carrito != null) {
			List<DetalleVenta> lista= servicioAdmin.listarDetallesDeVentaConIdCarrito(carrito.getId());
			Integer cantidad= lista.size();
			model.put("cantidad", cantidad);
			model.put("carrito", carrito);
		}
		List<Categoria> listaCategorias=servicioAdmin.listarCategorias();
		model.put("listaCategorias", listaCategorias);
		List<Productos> listaProductos=servicioUser.verProductosDisponibles();
		model.put("listaProductos", listaProductos);
		return new ModelAndView("index",model);
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		if( session != null){
		request.removeAttribute("id");
		request.removeAttribute("rol");
		session.invalidate();
		}	
		return new ModelAndView("redirect:/");
	}
}