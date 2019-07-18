package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Proveedor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.DetalleVenta;
import ar.edu.unlam.tallerweb1.modelo.DistanciaTiempo;
import ar.edu.unlam.tallerweb1.modelo.ListPedidoProducto;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.OrdenCompra;
import ar.edu.unlam.tallerweb1.modelo.ProductoOrdenCompra;
import ar.edu.unlam.tallerweb1.servicios.ServicioAdmin;
import ar.edu.unlam.tallerweb1.servicios.ServicioGoogleApi;
import ar.edu.unlam.tallerweb1.servicios.ServicioUser;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class ControladorAdmin {
	
	@Inject
	private ServicioAdmin servicioAdmin;
	@Inject
	private ServicioUser servicioUser;
	@Inject
	private ServicioGoogleApi servicioGoogle;
	
	@RequestMapping("/listarProductos")
	public ModelAndView listarProductos(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		List<Productos> listaProductos=servicioAdmin.listarProductosDisponibles();
		modelo.put("lista", listaProductos);
		return new ModelAndView("listarProductos",modelo);
	}
	
	@RequestMapping(path="/publicar-producto")
	public ModelAndView publicarProducto(@RequestParam ("id") Long id, HttpServletRequest request){
		ModelMap modelo = new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		servicioAdmin.publicarProducto(id);
		List<Productos> listaProductos=servicioAdmin.listarProductosDisponibles();
		modelo.put("lista", listaProductos);
		return new ModelAndView("listarProductos",modelo);
	}
	
	@RequestMapping(path="/quitar-producto")
	public ModelAndView quitarProducto(@RequestParam ("id") Long id,HttpServletRequest request) {
		ModelMap modelo= new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		servicioAdmin.quitarProducto(id);
		List<Productos> listaProductos=servicioAdmin.listarProductosDisponibles();
		modelo.put("lista", listaProductos);
		return new ModelAndView("listarProductos",modelo);
	}
	
	@RequestMapping(path="/insertarProducto")
	public ModelAndView insertarProducto(HttpServletRequest request) {
		ModelMap modelo= new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		Productos producto= new Productos();
		modelo.put("producto", producto);
		List<Categoria> listaCategorias= servicioAdmin.listarCategorias();
		modelo.put("categorias", listaCategorias);
		return new ModelAndView("insertarProducto",modelo);
	}
	
	@RequestMapping(path="/guardarProducto", method = RequestMethod.POST)
	public ModelAndView guardarProducto( @RequestParam CommonsMultipartFile file,
										 @ModelAttribute("producto") Productos producto,
										 @ModelAttribute ("idCategoria") Long idCategoria,
										 HttpServletRequest request) {
		ModelMap modelo= new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		servicioAdmin.insertarProducto(producto, idCategoria, file);
		return new ModelAndView("exito",modelo);
	}
	
	@RequestMapping(path="/mostrar-formulario")
	public ModelAndView mostrarFormulario(@RequestParam ("id") Long id,HttpServletRequest request) {
		ModelMap modelo= new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		Compra stock = new Compra();
		modelo.put("stock", stock);
		modelo.put("productoId", id);
		return new ModelAndView("formularioStock",modelo);
	}
	
	@RequestMapping(path="/guardarStock")
	public ModelAndView guardarSock(@ModelAttribute("stock") Compra stock
									, @ModelAttribute("id") Long id
									, HttpServletRequest request) {
		ModelMap modelo= new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		servicioAdmin.insertarStock(stock, id);
		servicioAdmin.aumentarStockProducto(stock.getStock(),id);
		return new ModelAndView("exito",modelo);
	}
	
	@RequestMapping(path="/consultarNotificaciones")
	public ModelAndView listarProductosStockMinimo(HttpServletRequest request) {
		List<Notificacion> listaProductosPocoStock= servicioAdmin.buscarProductosStockMinimo();
		List<Notificacion> listaProductosVencidos= servicioAdmin.buscarProductosVencidos();
		List<Notificacion> listaProductosEnOferta= servicioAdmin.buscarProductosEnOferta();
		List<Proveedor> listaProveedores= servicioAdmin.listarProveedores();
		ModelMap modelo= new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		ListPedidoProducto listadoPedidoProducto	= servicioAdmin.devolverNotificacionesDePocoStockComoPedidos(listaProductosPocoStock);
		modelo.put("pedido", listadoPedidoProducto);
		modelo.put("NotificacionProductosVencidos", listaProductosVencidos);
		modelo.put("NotificacionProductoEnOferta", listaProductosEnOferta);
		modelo.put("proveedores", listaProveedores);
		return new ModelAndView("vistaNotificacionOfertasAdmin", modelo);
	}
	
	@RequestMapping(path="/organizarPedidos" , method = RequestMethod.POST)
	public ModelAndView confeccionarPedidos(@ModelAttribute ("pedido") ListPedidoProducto proveedor,HttpServletRequest request){
		servicioAdmin.crearOrdenesDeCompraEnBaseAListaPedidoProducto(proveedor.getPp());
		List<OrdenCompra> lista = servicioAdmin.traerOrdenesDeCompra();
		ModelMap modelo= new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		modelo.put("ordenCompra", lista);
		return new ModelAndView("vistaOrdenesDeCompra", modelo);
	}
	
	@RequestMapping(path="/verOrdenesDeCompra")
	public ModelAndView confeccionarPedidos(HttpServletRequest request){
		List<OrdenCompra> lista = servicioAdmin.traerOrdenesDeCompra();
		ModelMap modelo= new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		modelo.put("ordenCompra", lista);
		return new ModelAndView("vistaOrdenesDeCompra", modelo);
	}
	
	@RequestMapping(path="/listarCarritosCompraClientes")
	public ModelAndView listarCarritosCompraClientes(HttpServletRequest request) {
		List<CarritoCompras> carritos=servicioAdmin.buscarCarritosCompra();
		List<CarritoCompras> carritosConfirmados= servicioAdmin.buscarCarritosCompraConfirmados();
		ModelMap modelo= new ModelMap();
		Long id= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(id);
		if(u != null) {
			modelo.put("usuario", u);
		}
		modelo.put("carritos", carritos);
		modelo.put("carritosConfirmados", carritosConfirmados);
		return new ModelAndView("vista-carritos-compra-clientes", modelo);
	}
	
	@RequestMapping(path="/detalle-carrito")
	public ModelAndView detalleCarrito(@RequestParam ("id") Long id,HttpServletRequest request) {
		List<DetalleVenta> lista= servicioAdmin.listarDetallesDeVentaConIdCarrito(id);
		CarritoCompras carrito= servicioAdmin.buscarCarritoComprasConId(id);
		DistanciaTiempo dt = servicioGoogle.calcularDistanciaDeLaDireccion(carrito.getDireccion());
		ModelMap modelo= new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		modelo.put("distanciaTiempo", dt);
		modelo.put("listaDetalleVenta", lista);
		modelo.put("carrito", carrito);
		return new ModelAndView("vista-detalle-carrito", modelo);
	}
	
	@RequestMapping(path="/enviar-carrito")
	public ModelAndView enviarCarrito(@RequestParam ("id") Long id,HttpServletRequest request) {
		ModelMap modelo= new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		Boolean resultado= servicioAdmin.enviarCarrito(id);
		if(resultado == true) {
			return new ModelAndView("exito",modelo);
		} 
		return null;
	}
	@RequestMapping(path="/confirmarOrdenDeCompra")
	public ModelAndView confirmarOrdenDeCompra(@RequestParam ("id") Long id,HttpServletRequest request) {
		servicioAdmin.confirmarOrdenDeCompra(id);
		ModelMap modelo = new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		modelo.put("mensaje","OrdenConfirmada");
		return new ModelAndView("exito",modelo);
	}
	@RequestMapping(path="/verDetallesDeOrdenDeCompra", method = RequestMethod.POST)
	public ModelAndView verDetallesDeOrdenDeCompra(@RequestParam ("id") Long id,HttpServletRequest request) {
		OrdenCompra oc = servicioAdmin.traerOrdenDeCompra(id);
		List<ProductoOrdenCompra> list=servicioAdmin.verDetallesDeOrdenDeCompra(id);
		ModelMap modelo = new ModelMap();
		Long idU= (Long) request.getSession().getAttribute("id");
		Usuario u= servicioUser.buscarUsuarioPorId(idU);
		if(u != null) {
			modelo.put("usuario", u);
		}
		modelo.put("ordenCompra", oc);
		modelo.put("list",list);
		return new ModelAndView("vistaDetalleOrdenDeCompra",modelo);
	}
	
	public void setServicioAdmin(ServicioAdmin servicioAdmin) {
		this.servicioAdmin=servicioAdmin;
	}
	
	public ServicioAdmin getServicioAdmin() {
		return this.servicioAdmin;
	}

	public ServicioUser getServicioUser() {
		return servicioUser;
	}

	public void setServicioUser(ServicioUser servicioUser) {
		this.servicioUser = servicioUser;
	}
}
