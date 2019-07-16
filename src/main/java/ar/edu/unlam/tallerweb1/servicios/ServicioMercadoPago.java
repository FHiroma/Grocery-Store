package ar.edu.unlam.tallerweb1.servicios;
import java.util.List;

import com.mercadopago.resources.Preference;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.DetalleVenta;

public interface ServicioMercadoPago {
	Preference traerPreferenciasParaMercadoPago(List<DetalleVenta> lista, CarritoCompras carrito);
}
