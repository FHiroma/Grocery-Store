package ar.edu.unlam.tallerweb1.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Proveedor;
import ar.edu.unlam.tallerweb1.modelo.Recomendacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.DetalleVenta;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Localidades;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.OrdenCompra;
import ar.edu.unlam.tallerweb1.modelo.PedidoProducto;
import ar.edu.unlam.tallerweb1.modelo.ProductoOrdenCompra;

@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {

	@Inject
	private SessionFactory sessionFactory;
	@Inject
	private ServletContext context;
	
	@Override
	public List<Productos> listarProductosDisponibles() {
		final Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Productos> listaProductos = session.createCriteria(Productos.class)
			.add(Restrictions.eq("oferta", false)).list();
		return listaProductos;
	}

	@Override
	public void publicarProducto(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto = (Productos) session.createCriteria(Productos.class).add(Restrictions.eq("id", id))
				.uniqueResult();
		producto.setEstado(true);
	}

	@Override
	public void quitarProducto(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto = (Productos) session.createCriteria(Productos.class).add(Restrictions.eq("id", id))
				.uniqueResult();
		producto.setEstado(false);
	}

	@Override
	public List<Productos> verProductosOferta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarProducto(Productos producto, Long idCategoria, CommonsMultipartFile file) {
		final Session session = sessionFactory.getCurrentSession();
		Categoria categoria = (Categoria) session.createCriteria(Categoria.class)
				.add(Restrictions.eq("id", idCategoria)).uniqueResult();
		session.createCriteria(Productos.class).add(Restrictions.eq("descripcion", producto.getDescripcion()))
				.uniqueResult();
		InputStream inputStream = null;
	    OutputStream outputStream = null;
        String filename=file.getOriginalFilename();
        Path currentWorkingDir= Paths.get(context.getRealPath("images"));
        //System.out.println(Paths.get(context.getRealPath("images")));
        File nuevaImagen = new File(currentWorkingDir.toString(), filename);
        try {
            inputStream = file.getInputStream();

            if (!nuevaImagen.exists()) {
                nuevaImagen.createNewFile();
            }
            outputStream = new FileOutputStream(nuevaImagen);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String direccionDeImagen = "/images/"+filename;
        producto.setImagen(direccionDeImagen);
		producto.setCategoria(categoria);
		producto.setEstado(true);
		producto.setOferta(false);
		producto.setStock(0);
		producto.setStockDeOferta(0);
		session.save(producto);
	}

	@Override
	public Productos buscarProducto(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto = (Productos) session.createCriteria(Productos.class).add(Restrictions.eq("id", id))
				.uniqueResult();
		return producto;
	}

	@Override
	public void insertarStock(Compra stock, Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto= (Productos)session.createCriteria(Productos.class)
		.add(Restrictions.eq("id", id))
		.uniqueResult();
		if(producto != null) {
				stock.setProducto(producto);
				stock.setFechaIngreso();
				stock.setFechaVencimiento();
				stock.setOferta(false);
				stock.setVencido(false);
				session.save(stock);
			}
	}

	@Override
	public void aumentarStockProducto(Integer cantidad, Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Productos producto = (Productos) session.createCriteria(Productos.class).add(Restrictions.eq("id", id))
				.uniqueResult();
		producto.setStock(producto.getStock() + cantidad);
		final Session session1 = sessionFactory.getCurrentSession();
		session1.update(producto);
	}

	@Override
	public List<Notificacion> buscarNotificaciones() {
		final Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Notificacion> listaNotificaciones = session.createCriteria(Notificacion.class)
				.add(Restrictions.eq("estado", false)).list();
		return listaNotificaciones;
	}

	@Override
	public List<Compra> productoOferta() {
		final Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Compra> listaOfertas = session.createCriteria(Compra.class).add(Restrictions.eq("oferta", true)).list();
		return listaOfertas;
	}

	@Override
	public List<Categoria> listarCategorias() {
		@SuppressWarnings("unchecked")
		List<Categoria> listaCategorias = sessionFactory.getCurrentSession().createCriteria(Categoria.class).list();
		return listaCategorias;
	}

	@Override
	public List<Proveedor> listarProveedores() {
		@SuppressWarnings("unchecked")
		List<Proveedor> listaProveedores = sessionFactory.getCurrentSession().createCriteria(Proveedor.class).list();
		listaProveedores.size();
		return listaProveedores;
	}

	@Override
	public List<Notificacion> buscarProductosStockMinimo() {
		@SuppressWarnings("unchecked")
		List<Notificacion> productosStockMinimo= sessionFactory.getCurrentSession().createCriteria(Notificacion.class)
				.add(Restrictions.eq("descripcion", "Stock Minimo"))
				.add(Restrictions.eq("estado", false))
				.list();
		return productosStockMinimo;
	}

	@Override
	public List<Notificacion> buscarProductosVencidos() {
		@SuppressWarnings("unchecked")
		List<Notificacion> productosVencidos= sessionFactory.getCurrentSession().createCriteria(Notificacion.class)
				.add(Restrictions.eq("descripcion", "Producto Vencido")).list();
		return productosVencidos;
	}

	@Override
	public List<Notificacion> buscarProductosEnOferta() {
		@SuppressWarnings("unchecked")
		List<Notificacion> productosEnOferta= sessionFactory.getCurrentSession().createCriteria(Notificacion.class)
				.add(Restrictions.eq("descripcion", "Producto en oferta")).list();
		return productosEnOferta;
	}

	@Override
	public void insertarUsuarioAlCarrito(CarritoCompras carrito, Usuario usuario) {
		carrito.setUsuario(usuario);
		sessionFactory.getCurrentSession().update(carrito);
	}

	@Override
	public List<Localidades> listarLocalidades() {
		@SuppressWarnings("unchecked")
		List<Localidades> localidades = sessionFactory.getCurrentSession().createCriteria(Localidades.class).list();
		return localidades;
	}

	@Override
	public void agregarDireccionAlCarrito(CarritoCompras carrito, Direccion direccionTabla) {
		carrito.setDireccion(direccionTabla);
		sessionFactory.getCurrentSession().update(carrito);
	}

	@Override
	public Direccion guardarDireccionDeCompra(Direccion direccion, Long idLocalidad) {
		Localidades localidad= (Localidades) sessionFactory.getCurrentSession()
				.createCriteria(Localidades.class)
				.add(Restrictions.eq("id", idLocalidad))
				.uniqueResult();
		if (localidad != null) {
			direccion.setLocalidad(localidad);
			sessionFactory.getCurrentSession().save(direccion);
			return direccion;
		} else {
			return null;
		}
	}

	@Override
	public List<CarritoCompras> buscarCarritosCompra() {
		@SuppressWarnings("unchecked")
		List<CarritoCompras> listaCarritos= sessionFactory.getCurrentSession()
				.createCriteria(CarritoCompras.class)
				.add(Restrictions.isNull("estado"))
				.list();
		return listaCarritos;
	}

	@Override
	public List<DetalleVenta> listarDetallesDeVentaConIdCarrito(Long id) {
		CarritoCompras carrito=(CarritoCompras) sessionFactory.getCurrentSession()
				.createCriteria(CarritoCompras.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		if (carrito != null) {
			@SuppressWarnings("unchecked")
			List<DetalleVenta> listaDetallesVenta= sessionFactory.getCurrentSession()
					.createCriteria(DetalleVenta.class)
					.add(Restrictions.eq("carritoCompras", carrito))
					.list();
			return listaDetallesVenta;
		} else {
			return null;
		}
	}

	@Override
	public CarritoCompras buscarCarritoComprasConId(Long id) {
		CarritoCompras carrito= (CarritoCompras) sessionFactory.getCurrentSession()
				.createCriteria(CarritoCompras.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		return carrito;
	}

	@Override
	public Boolean enviarCarrito(Long id) {
		CarritoCompras carrito= (CarritoCompras) sessionFactory.getCurrentSession()
				.createCriteria(CarritoCompras.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		if(carrito != null) {
			@SuppressWarnings("unchecked")
			List<DetalleVenta> listaDetalle= sessionFactory.getCurrentSession()
					.createCriteria(DetalleVenta.class)
					.add(Restrictions.eq("carritoCompras", carrito))
					.list();
			if(listaDetalle.size() > 0) {
				for(DetalleVenta detalle: listaDetalle) {
					Productos productoDelDetalleDeVenta= detalle.getProducto();
					if(productoDelDetalleDeVenta.getOferta().equals(true)) {
						productoDelDetalleDeVenta.setStockDeOferta(productoDelDetalleDeVenta.getStockDeOferta() - detalle.getCantidad());
						System.out.println("paso por el true");
						sessionFactory.getCurrentSession().update(productoDelDetalleDeVenta);
					}  else {
						productoDelDetalleDeVenta.setStock(productoDelDetalleDeVenta.getStock() - detalle.getCantidad());
						sessionFactory.getCurrentSession().update(productoDelDetalleDeVenta);
					}
				}
			}
			carrito.setEstado(true);
			sessionFactory.getCurrentSession().update(carrito);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Productos> buscarProductosRecomendados(Recomendacion rec) {
		Categoria categoria =  (Categoria)sessionFactory.getCurrentSession().createCriteria(Categoria.class)
								.add(Restrictions.eq("descripcion", rec.getDescripcion()))
								.uniqueResult();
		Criterion stock = Restrictions.gt("stock", 0);
		Criterion stockOferta = Restrictions.gt("stockDeOferta", 0);
		@SuppressWarnings("unchecked")
		List<Productos> listaRec = sessionFactory.getCurrentSession().createCriteria(Productos.class)
				.add(Restrictions.eq("categoria", categoria))
				.add(Restrictions.or(stock, stockOferta))
				.list();
		return listaRec;
	}

	@Override
	public List<CarritoCompras> buscarCarritosCompraConfirmados() {
		@SuppressWarnings("unchecked")
		List<CarritoCompras> listaCarritos= sessionFactory.getCurrentSession()
				.createCriteria(CarritoCompras.class)
				.add(Restrictions.eq("estado", true))
				.list();
		return listaCarritos;
	}

	@Override
	public void crearOrdenesDeCompraEnBaseAListaPedidoProducto(ArrayList<PedidoProducto> list) {
		Session sesion = sessionFactory.getCurrentSession();
		for(PedidoProducto pedidoProducto: list){
			Productos producto = (Productos) sesion.createCriteria(Productos.class)
								.add(Restrictions.eq("id",pedidoProducto.getProducto()))
								.uniqueResult();
			
			Proveedor proveedor = (Proveedor) sesion.createCriteria(Proveedor.class)
								  .add(Restrictions.eq("id", pedidoProducto.getProveedor()))
								  .uniqueResult();
			
			OrdenCompra oc = (OrdenCompra) sesion.createCriteria(OrdenCompra.class)
					 .add(Restrictions.eq("proveedor", proveedor))
					 .add(Restrictions.eq("estado", true))
					 .uniqueResult();
			
			ProductoOrdenCompra pocTest = (ProductoOrdenCompra) sesion.createCriteria(ProductoOrdenCompra.class)
					  .add(Restrictions.eq("producto", producto))
					  .createAlias("ordenCompra", "ordenCompraAlias")
					  .add(Restrictions.eq("ordenCompraAlias.estado", true))
					  .add(Restrictions.eq("ordenCompraAlias.proveedor", proveedor))
					  .uniqueResult();
			if(oc == null){
				OrdenCompra ordenCompra = new OrdenCompra();
				ordenCompra.setEstado(true);
				ordenCompra.setFecha();
				ordenCompra.setProveedor(proveedor);
				sesion.save(ordenCompra);
					if(pocTest == null){
						ProductoOrdenCompra poc = new ProductoOrdenCompra();
						poc.setCantidad(pedidoProducto.getCantidad());
						poc.setProducto(producto);
						poc.setOrdenCompra(ordenCompra);
						sesion.save(poc);
					}
				}
			else{
				if(pocTest == null){
					ProductoOrdenCompra poc = new ProductoOrdenCompra();
					poc.setCantidad(pedidoProducto.getCantidad());
					poc.setProducto(producto);
					poc.setOrdenCompra(oc);
					sesion.save(poc);
				}
				else{
				pocTest.setCantidad(pocTest.getCantidad()+pedidoProducto.getCantidad());
				sesion.update(pocTest);
				}
			}
			Notificacion notif = (Notificacion) sesion.createCriteria(Notificacion.class)
					.add(Restrictions.like("descripcion", "Stock Minimo"))
					.add(Restrictions.eq("producto", producto))
					.add(Restrictions.eq("estado", false))
					.uniqueResult();
			notif.setEstado(true);
			sesion.update(notif);
		}
	}

	@Override
	public List<OrdenCompra> traerOrdenesDeCompra() {
		@SuppressWarnings("unchecked")
		List<OrdenCompra> lista = sessionFactory.getCurrentSession().createCriteria(OrdenCompra.class)
								  .addOrder(Order.desc("estado"))
								  .addOrder(Order.desc("id"))
								  .list();
		return lista;
	}

	@Override
	public void confirmarOrdenDeCompra(Long id) {
		OrdenCompra oc = (OrdenCompra)sessionFactory.getCurrentSession().createCriteria(OrdenCompra.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
		oc.setEstado(false);
		sessionFactory.getCurrentSession().save(oc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductoOrdenCompra> verDetallesDeOrdenDeCompra(Long id) {
		OrdenCompra oc = (OrdenCompra)sessionFactory.getCurrentSession().createCriteria(OrdenCompra.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
		return sessionFactory.getCurrentSession().createCriteria(ProductoOrdenCompra.class)
				.add(Restrictions.eq("ordenCompra",oc))
				.list();
	}

	@Override
	public OrdenCompra traerOrdenDeCompra(Long id) {
		OrdenCompra oc= (OrdenCompra)sessionFactory.getCurrentSession().createCriteria(OrdenCompra.class)
				  .add(Restrictions.eq("id", id))
				  .uniqueResult();
		return oc;
	}

}
