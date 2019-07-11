package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Proveedor;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.PedidoProducto;
import ar.edu.unlam.tallerweb1.servicios.ServicioAdmin;
import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
	
	@RequestMapping(path="/insertarProducto")
	public ModelAndView insertarProducto() {
		ModelMap modelo= new ModelMap();
		Productos producto= new Productos();
		modelo.put("producto", producto);
		List<Categoria> listaCategorias= servicioAdmin.listarCategorias();
		modelo.put("categorias", listaCategorias);
		return new ModelAndView("insertarProducto",modelo);
	}
	
	@RequestMapping(path="/guardarProducto", method = RequestMethod.POST)
	public ModelAndView guardarProducto( @RequestParam CommonsMultipartFile file,
										 @ModelAttribute("producto") Productos producto,
										 @ModelAttribute ("idCategoria") Long idCategoria) 
									{
		servicioAdmin.insertarProducto(producto, idCategoria, file);
		return new ModelAndView("exito");
	}
	
	@RequestMapping(path="/mostrar-formulario")
	public ModelAndView mostrarFormulario(@RequestParam ("id") Long id) {
		ModelMap modelo= new ModelMap();
		Compra stock = new Compra();
		modelo.put("stock", stock);
		modelo.put("producto", id);
		return new ModelAndView("formularioStock",modelo);
	}
	
	@RequestMapping(path="/guardarStock")
	public ModelAndView guardarSock(@ModelAttribute("stock") Compra stock
									, @ModelAttribute("id") Long id) {
		servicioAdmin.insertarStock(stock, id);
		servicioAdmin.aumentarStockProducto(stock.getStock(),id);
		return new ModelAndView("exito");
	}
	
	@RequestMapping(path="/consultarNotificaciones")
	public ModelAndView listarProductosStockMinimo() {
		List<Notificacion> listaProductosPocoStock= servicioAdmin.buscarProductosStockMinimo();
		List<Notificacion> listaProductosVencidos= servicioAdmin.buscarProductosVencidos();
		List<Notificacion> listaProductosEnOferta= servicioAdmin.buscarProductosEnOferta();
		List<Proveedor> listaProveedores= servicioAdmin.listarProveedores();
		ModelMap modelo= new ModelMap();
		List<PedidoProducto> listadoPedidoProducto	= servicioAdmin.devolverNotificacionesDePocoStockComoPedidos(listaProductosPocoStock);
		modelo.put("NotificacionStockMinimo", listadoPedidoProducto);
		modelo.put("NotificacionProductosVencidos", listaProductosVencidos);
		modelo.put("NotificacionProductoEnOferta", listaProductosEnOferta);
		modelo.put("proveedores", listaProveedores);
		return new ModelAndView("vistaNotificacionOfertasAdmin", modelo);
	}
}
