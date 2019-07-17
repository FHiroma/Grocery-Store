package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;

import ar.edu.unlam.tallerweb1.modelo.Direccion;

@Service("servicioGoogleApi")
@Transactional
public class ServicioGoogleApiImpl implements ServicioGoogleApi{
	 private static final String API_KEY = "AIzaSyBz6jBeiDEAJcC3790Xusbdn9vUnT8NcSI";

	@Override
	public DistanceMatrix calcularDistanciaDeLaDireccion(Direccion dir){
	String[] direccionCliente = {dir.getCalle(),dir.getNumero().toString(),dir.getLocalidad().getDescripcion()};
	String[] direccionEstablecimiento = {"Florencio Varela","1903","San Justo"};
	GeoApiContext geo = new GeoApiContext().setApiKey(API_KEY);
	DistanceMatrixApiRequest request= DistanceMatrixApi.newRequest(geo);
	request= DistanceMatrixApi.getDistanceMatrix(geo,direccionEstablecimiento,direccionCliente);
	request.mode(TravelMode.DRIVING);
	try {
		return request.await();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
}
