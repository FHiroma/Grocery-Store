package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DistanciaTiempo;

public interface ServicioGoogleApi {
	DistanciaTiempo calcularDistanciaDeLaDireccion(Direccion dir);
}
