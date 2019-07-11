package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del DAO de Usuarios.
public interface UsuarioDao {
	
	Usuario consultarUsuario (Usuario usuario);
	List<Productos> verProductosDisponibles();
	List<Compra> verProductosEnOferta();
	void guardarCarritoVacio(CarritoCompras carrito);
	Usuario buscarUsuarioPorId(Long id);
	List<Productos> listarProductosDeLaCategoriaDeId(Long id);
}
