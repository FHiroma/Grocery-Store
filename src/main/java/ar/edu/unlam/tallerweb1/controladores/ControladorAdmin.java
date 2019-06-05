package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.servicios.ServicioAdmin;
import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorAdmin {
	
	@Inject
	private ServicioAdmin servicioAdmin;
	
	
	@RequestMapping("/listarProductos")
	public ModelAndView listarProductos() {
		ModelMap modelo = new ModelMap();
		List<Productos> listaProductos=servicioAdmin.listarProductosDisponibles();
		modelo.put("lista", listaProductos);
		return new ModelAndView("listarProductos",modelo);
	}
	
	@RequestMapping(path="/publicar-producto")
	public ModelAndView publicarProducto(@RequestParam ("id") Long id){
		ModelMap modelo = new ModelMap();
		servicioAdmin.publicarProducto(id);
		List<Productos> listaProductos=servicioAdmin.listarProductosDisponibles();
		modelo.put("lista", listaProductos);
		return new ModelAndView("listarProductos",modelo);
	}
	
	@RequestMapping(path="/quitar-producto")
	public ModelAndView quitarProducto(@RequestParam ("id") Long id) {
		ModelMap modelo= new ModelMap();
		servicioAdmin.quitarProducto(id);
		List<Productos> listaProductos=servicioAdmin.listarProductosDisponibles();
		modelo.put("lista", listaProductos);
		return new ModelAndView("listarProductos",modelo);
	}

}
