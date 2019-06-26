package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.servicios.ServicioBusqueda;

@Controller
public class ControladorProducto {
	@Inject
	private ServicioBusqueda servicioBusqueda;
	
	@RequestMapping(value="/producto/{nombreDeProducto}", method= RequestMethod.GET)
	public ModelAndView verDetallesDeProducto (@PathVariable ("nombreDeProducto") String descripcion){
		Productos producto = servicioBusqueda.traerUnProducto(descripcion);
		ModelMap modelo = new ModelMap();
		modelo.put("producto", producto);
		return new ModelAndView("detalleProducto",modelo);
	}
}
