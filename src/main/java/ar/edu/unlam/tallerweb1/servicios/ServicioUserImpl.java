package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.Compra;
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
	public void registrarUsuario(Usuario usuario) {
		servicioUsuarioDao.registrarUsuario(usuario);
		
	}
}
