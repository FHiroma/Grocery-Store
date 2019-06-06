package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.ProductosDao;

@Service("servicioProductos")
@Transactional
public class ServicioProductosImpl implements ServicioProductos{

	@Inject
	private ProductosDao servicioProductosDao;
	
	@Override
	public void setFechaVencimiento() {
		servicioProductosDao.setFechaVencimiento();
	}

}
