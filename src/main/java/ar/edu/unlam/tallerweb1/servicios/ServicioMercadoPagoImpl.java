package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercadopago.MercadoPago;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.exceptions.*;

import ar.edu.unlam.tallerweb1.modelo.CarritoCompras;
import ar.edu.unlam.tallerweb1.modelo.DetalleVenta;

@Service("servicioMercadoPago")
@Transactional
public class ServicioMercadoPagoImpl implements ServicioMercadoPago{

	
	@Override
	public Preference traerPreferenciasParaMercadoPago(List<DetalleVenta> lista, CarritoCompras carrito){
		ArrayList<Item> listaItems = new ArrayList<Item>();
		try {
			MercadoPago.SDK.setAccessToken("TEST-5704551589678248-071606-2ac913044a80a2ead2e0e099a9c46b7c-181649560");
			Preference preference = new Preference();
			for(DetalleVenta detalle: lista){
				Item item = new Item();
				item.setCategoryId(detalle.getProducto().getCategoria().getDescripcion())
				.setTitle(detalle.getProducto().getDescripcion())
				.setQuantity(detalle.getCantidad())
				.setUnitPrice(detalle.getProducto().getPrecio().floatValue())
				.setPictureUrl(detalle.getProducto().getImagen());
				listaItems.add(item);
			}
			Payer payer = new Payer();
			payer.setEmail(carrito.getUsuario().getEmail())
			.setName(carrito.getUsuario().getNombre())
			.setSurname(carrito.getUsuario().getApellido());
			preference.setPayer(payer);
			preference.setItems(listaItems);
			return preference.save();
		} catch (MPException e) {
			e.printStackTrace();
		}
		return null;
	}

}
