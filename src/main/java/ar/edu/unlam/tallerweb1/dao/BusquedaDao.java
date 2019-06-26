package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Productos;

public interface BusquedaDao {

	public List<Productos> listadoPosiblesResultados(String nombre);
	public Productos traerUnProducto(String nombre);
}
