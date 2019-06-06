package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.dao.AdminDao;
import ar.edu.unlam.tallerweb1.modelo.Productos;

@Service("servicioAdmin")
@Transactional
public class ServicioAdminImpl implements ServicioAdmin {

	@Inject
	private AdminDao servicioAdminDao;
	
	@Override
	public List<Productos> listarProductosDisponibles() {
		return servicioAdminDao.listarProductosDisponibles();
	}

	@Override
	public void publicarProducto(Long id) {
		servicioAdminDao.publicarProducto(id);
	}

	@Override
	public void quitarProducto(Long id) {
		servicioAdminDao.quitarProducto(id);
	}

	@Override
	public List<Productos> verProductosOferta() {
		return servicioAdminDao.verProductosOferta();
	}

	@Override
	public void insertarProducto(Productos producto) {
		servicioAdminDao.insertarProducto(producto);
	}

}
