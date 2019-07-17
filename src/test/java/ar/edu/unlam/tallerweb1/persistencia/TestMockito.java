package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorAdmin;
import ar.edu.unlam.tallerweb1.controladores.ControladorUser;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Productos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUser;
import ar.edu.unlam.tallerweb1.servicios.ServicioAdmin;
import ar.edu.unlam.tallerweb1.servicios.ServicioBusqueda;

public class TestMockito {

	@Test
	@Transactional @Rollback(true)
	public void testQueElServicioUserCreaUnaDireccion() {
		ServicioUser MockServicioUser= mock(ServicioUser.class);
		ControladorUser controlador= new ControladorUser();
		controlador.setServicioUser(MockServicioUser);
		Direccion MockDireccion= mock(Direccion.class);
		when(MockServicioUser.crearDireccion((long) 1, "calle", 1)).thenReturn(MockDireccion);
		assertThat(controlador.getServicioUser().crearDireccion((long) 1, "calle", 1).equals(MockDireccion));
	}
	
	@Test
	@Transactional @Rollback(true)
	public void testQuePruebaQueServicioUserDevuelveUnUsuarioConUnaId() {
		ServicioUser MockServicioUser= mock(ServicioUser.class);
		ControladorUser controlador= new ControladorUser();
		controlador.setServicioUser(MockServicioUser);
		Usuario MockUsuario= mock(Usuario.class);
		when(MockServicioUser.buscarUsuarioPorId(((long) 1))).thenReturn(MockUsuario);
		assertThat(controlador.getServicioUser().buscarUsuarioPorId((long) 1).equals(MockUsuario));	
	}
	
	@Test
	@Transactional @Rollback(true)
	public void testQueElMetodoEnviarCarritoDeAdminControllerDevuelveLaVistaExito() {
		ControladorAdmin controlador= new ControladorAdmin();
		ServicioAdmin MockServicioAdmin= mock(ServicioAdmin.class);
		controlador.setServicioAdmin(MockServicioAdmin);
		Long id= (long) 1;
		when(MockServicioAdmin.enviarCarrito(id)).thenReturn(true);
		ModelAndView mav= controlador.enviarCarrito(id);
		assertThat(mav.getViewName()).isEqualTo("exito");
	}
	
}
