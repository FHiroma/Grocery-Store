package ar.edu.unlam.tallerweb1.servicios;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.DistanciaTiempo;

@Service("servicioGoogleApi")
@Transactional
public class ServicioGoogleApiImpl implements ServicioGoogleApi{
	 private static final String API_KEY = "AIzaSyBz6jBeiDEAJcC3790Xusbdn9vUnT8NcSI";

	@Override
	public DistanciaTiempo calcularDistanciaDeLaDireccion(Direccion dir){
	String[] direccionCliente = {dir.getCalle()+" "+dir.getNumero().toString()+" "+dir.getLocalidad().getDescripcion()};
	String[] direccionEstablecimiento = {"Florencio Varela 1903","San Justo"};
	GeoApiContext geo = new GeoApiContext.Builder().apiKey(API_KEY).build();
	DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(geo);
	try {
		DistanceMatrix result = req.origins(direccionEstablecimiento)
				.destinations(direccionCliente)
				.mode(TravelMode.DRIVING)
				.await();
		DistanciaTiempo DT = new DistanciaTiempo();
		System.out.println(result.destinationAddresses[0]);
		Long metros = result.rows[0].elements[0].distance.inMeters;
		Long segundos = result.rows[0].elements[0].duration.inSeconds;
		Double kilometros = (metros.doubleValue()/1000);
		Double minutos = (segundos.doubleValue()/60);
		DT.setDistancia(kilometros);
		DT.setSegundos(minutos);
		return DT;
	} catch (ApiException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
}
