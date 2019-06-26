package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.BusquedaDao;
import ar.edu.unlam.tallerweb1.modelo.Productos;
@Service("servicioBusqueda")
@Transactional
public class ServicioBusquedaImpl implements ServicioBusqueda{

	@Inject
	private BusquedaDao busquedaDao; 
	
	@Override
	public List<Productos> listadoPosiblesResultados(String nombre) {
		return busquedaDao.listadoPosiblesResultados(nombre);
	}

	@Override
	public Productos traerUnProducto(String nombre) {
		return busquedaDao.traerUnProducto(nombre);
	}

}
