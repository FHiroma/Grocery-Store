package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Productos;

public interface ServicioBusqueda {
	public List<Productos> listadoPosiblesResultados(String nombre);
	public Productos traerUnProducto(String nombre);
}
