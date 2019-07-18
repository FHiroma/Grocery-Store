package ar.edu.unlam.tallerweb1.modelo;

public class PedidoProducto{

	private Long proveedor;
	private Long producto;
	private Integer cantidad;
	private Notificacion notificacion;

	public Notificacion getNotificacion() {
		return notificacion;
	}
	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}
	public Long getProveedor() {
		return proveedor;
	}
	public void setProveedor(Long proveedor) {
		this.proveedor = proveedor;
	}
	public Long getProducto() {
		return producto;
	}
	public void setProducto(Long producto) {
		this.producto = producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}
