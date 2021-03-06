package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Localidades;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Recomendacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del DAO de Usuarios.
public interface UsuarioDao {
	
	Usuario consultarUsuario (Usuario usuario);
	List<Productos> verProductosDisponibles();
	List<Productos> verProductosEnOferta();
	void guardarCarritoVacio(CarritoCompras carrito);
	Usuario buscarUsuarioPorId(Long id);
	List<Productos> listarProductosDeLaCategoriaDeId(Long id);
	void subirContadorDeUsuarioRecomendacion(String categoria, Usuario usuario);
	boolean registrarUsuario(Usuario usuario);
	Localidades buscarLocalidadPorId(Long id);
	Direccion crearDireccion(Long localidad, String calle, Integer numero);
	Recomendacion BuscarRecomendacionDelUsuario(Usuario u);
}
