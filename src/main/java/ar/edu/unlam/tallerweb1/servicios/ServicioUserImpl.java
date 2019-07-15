package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Localidades;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service("servicioUser")
@Transactional
public class ServicioUserImpl implements ServicioUser {

	@Inject
	private UsuarioDao servicioUsuarioDao;

	@Override
	public List<Productos> verProductosDisponibles() {
		return servicioUsuarioDao.verProductosDisponibles();
	}

	@Override
	public List<Compra> verProductosEnOferta() {
		return servicioUsuarioDao.verProductosEnOferta();
	}

	@Override
	public void guardarCarritoVacio(CarritoCompras carrito) {
		servicioUsuarioDao.guardarCarritoVacio(carrito);
	}

	@Override
	public Usuario buscarUsuarioPorId(Long id) {
		return servicioUsuarioDao.buscarUsuarioPorId(id);
	}

	@Override
	public List<Productos> listarProductosDeLaCategoriaDeId(Long id) {
		return servicioUsuarioDao.listarProductosDeLaCategoriaDeId(id);
	}

	@Override
	public void subirContadorDeUsuarioRecomendacion(Long id, Usuario usuario) {
		servicioUsuarioDao.subirContadorDeUsuarioRecomendacion(id, usuario);
		
	}

	@Override
	public boolean registrarUsuario(Usuario usuario) {
		return servicioUsuarioDao.registrarUsuario(usuario);
		
	}

	@Override
	public Localidades buscarLocalidadPorId(Long id) {
		return servicioUsuarioDao.buscarLocalidadPorId(id);
	}

	@Override
	public Direccion crearDireccion(Long localidad, String calle, Integer numero) {
		return servicioUsuarioDao.crearDireccion(localidad, calle, numero);
	}
}
