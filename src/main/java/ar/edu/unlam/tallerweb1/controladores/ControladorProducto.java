package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioBusqueda;
import ar.edu.unlam.tallerweb1.servicios.ServicioUser;

@Controller
public class ControladorProducto {
	@Inject
	private ServicioBusqueda servicioBusqueda;
	@Inject
	private ServicioUser servicioUser;
	
	@RequestMapping(value="/producto", method= RequestMethod.GET)
	public ModelAndView verDetallesDeProducto (@RequestParam ("producto") String descripcion,HttpServletRequest request){
		ModelMap modelo = new ModelMap();
		Productos producto = servicioBusqueda.traerUnProducto(descripcion);
		if(producto == null){
			modelo.put("error", "Producto no encontrado");
			return new ModelAndView("error",modelo);
		}
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
			servicioUser.subirContadorDeUsuarioRecomendacion(producto.getCategoria().getDescripcion(), u);
		}
		modelo.put("producto", producto);
		return new ModelAndView("detalleProducto",modelo);
	}
	
	
	public ServicioBusqueda getServicioBusqueda() {
		return servicioBusqueda;
	}
	
	public void setServicioBUsqueda(ServicioBusqueda servicioBusqueda) {
		this.servicioBusqueda= servicioBusqueda;
	}
}
