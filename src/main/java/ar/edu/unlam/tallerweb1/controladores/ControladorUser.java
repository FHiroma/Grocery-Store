package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.servicios.ServicioUser;

@Controller
public class ControladorUser {
	
	@Inject
	private ServicioUser servicioUser;
	
	@RequestMapping("/ver-productos-en-oferta")
	public ModelAndView verProductosEnOferta() {
		List<Compra> listaDeOfertas= servicioUser.verProductosEnOferta();
		ModelMap modelo= new ModelMap();
		modelo.put("listaOfertas", listaDeOfertas);
		return new ModelAndView("vistaOfertasUsuario", modelo);
	}

}
