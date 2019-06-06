package ar.edu.unlam.tallerweb1.modelo;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Productos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	private Integer stock;
	private Boolean estado;
	private Calendar fechaVencimiento;
	private Boolean oferta;
	private Integer stockOferta;
	private Integer diasCaducidad;
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Calendar getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento() {
		Calendar fecha= Calendar.getInstance();
		fecha.add(Calendar.DATE, this.diasCaducidad);
		this.fechaVencimiento=fecha;
	}
	public Boolean getOferta() {
		return oferta;
	}
	public void setOferta(Boolean oferta) {
		this.oferta = oferta;
	}
	public Integer getStockOferta() {
		return stockOferta;
	}
	public void setStockOferta(Integer stockOferta) {
		this.stockOferta = stockOferta;
	}
	public Integer getDiasCaducidad() {
		return diasCaducidad;
	}
	public void setDiasCaducidad(Integer diasCaducidad) {
		this.diasCaducidad = diasCaducidad;
	}
}
