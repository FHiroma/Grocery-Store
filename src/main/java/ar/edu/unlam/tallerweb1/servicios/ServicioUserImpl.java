package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Productos;

@Service("servicioUser")
@Transactional
public class ServicioUserImpl implements ServicioUser {

	@Inject
	private UsuarioDao servicioUsuarioDao;

	@Override
	public List<Productos> verProductosDisponibles() {
		return servicioUsuarioDao.verProductosDisponibles();
	}
}
