package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioBusqueda;
import ar.edu.unlam.tallerweb1.servicios.ServicioUser;

@Controller
public class ControladorBusqueda {
	
	@Inject
	private ServicioBusqueda servicioBusqueda;
	@Inject
	private ServicioUser servicioUser;
	
	@RequestMapping (value="/autocompletado", method= RequestMethod.GET)
	public @ResponseBody List<Productos> traerPosiblesResultados (@RequestParam String nombreProducto){
		return servicioBusqueda.listadoPosiblesResultados(nombreProducto);
	}
	
	@RequestMapping (value="/busqueda", method= RequestMethod.GET)
	public ModelAndView busquedaDeProductos (@RequestParam String busqueda
											 , HttpServletRequest request){
		List<Productos> listado = servicioBusqueda.listadoPosiblesResultados(busqueda);
		if(listado.size()==1){
			return new ModelAndView("redirect:/producto?producto="+listado.get(0).getDescripcion());
		}
		ModelMap modelo = new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		modelo.put("lista", listado);
		return new ModelAndView("listaResultadoBusqueda", modelo);
	}
	
}
