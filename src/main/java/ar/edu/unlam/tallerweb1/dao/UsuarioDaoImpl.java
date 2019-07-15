package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.inject.Inject;

// implelemtacion del DAO de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("usuarioDao")
public class UsuarioDaoImpl implements UsuarioDao {

	// Como todo dao maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	@Inject
    private SessionFactory sessionFactory;

	@Override
	public Usuario consultarUsuario(Usuario usuario) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del objeto recibido como parametro
		// uniqueResult da error si se encuentran más de un resultado en la busqueda.
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword()))
				.uniqueResult();
	}

	@Override
	public List<Productos> verProductosDisponibles() {
		final Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Productos> listaProductos= session.createCriteria(Productos.class)
		.add(Restrictions.gt("stock",0))
		.add(Restrictions.eq("estado", true))
		.list();
		return listaProductos;
	}

	@Override
	public List<Productos> verProductosEnOferta() {
		final Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Productos> listaDeOfertas= session.createCriteria(Productos.class)
				.add(Restrictions.gt("stockDeOferta",0))
				.add(Restrictions.eq("estado", true))
				.list();
		return listaDeOfertas;
	}

	@Override
	public void guardarCarritoVacio(CarritoCompras carrito) {
		sessionFactory.getCurrentSession().save(carrito);
	}

	@Override
	public Usuario buscarUsuarioPorId(Long id) {
		Usuario u= (Usuario) sessionFactory.getCurrentSession()
				.createCriteria(Usuario.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		return u;
	}

	@Override
	public List<Productos> listarProductosDeLaCategoriaDeId(Long id) {
		Categoria categoria= (Categoria) sessionFactory.getCurrentSession()
				.createCriteria(Categoria.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		if(categoria != null) {
			@SuppressWarnings("unchecked")
			List<Productos> lista= sessionFactory.getCurrentSession()
					.createCriteria(Productos.class)
					.add(Restrictions.eq("categoria", categoria))
					.list();
			return lista;
		} else {
			return null;
		}
	}
}
