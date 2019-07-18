package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;

public class ListPedidoProducto {
	private ArrayList<PedidoProducto> pp;

	public void addPedidoProducto(PedidoProducto pedidoProducto){
		pp.add(pedidoProducto);
	}
	
	public ArrayList<PedidoProducto> getPp() {
		return pp;
	}

	public void setPp(ArrayList<PedidoProducto> pp) {
		this.pp = pp;
	}
	
}
