package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Localidades;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Recomendacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioUser {
	List<Productos> verProductosDisponibles();

	List<Productos> verProductosEnOferta();
	void guardarCarritoVacio(CarritoCompras carrito);
	Usuario buscarUsuarioPorId(Long id);
	List<Productos> listarProductosDeLaCategoriaDeId( Long id);
	void subirContadorDeUsuarioRecomendacion( String string, Usuario usuario);
	boolean registrarUsuario(Usuario usuario);
	Localidades buscarLocalidadPorId(Long id);
	Direccion crearDireccion(Long localidad, String calle, Integer numero);
	Recomendacion buscarRecomendacionDelUsuario(Usuario u);
}
