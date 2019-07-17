package ar.edu.unlam.tallerweb1.servicios;

import com.google.maps.model.DistanceMatrix;

import ar.edu.unlam.tallerweb1.modelo.Direccion;

public interface ServicioGoogleApi {
	DistanceMatrix calcularDistanciaDeLaDireccion(Direccion dir);
}
