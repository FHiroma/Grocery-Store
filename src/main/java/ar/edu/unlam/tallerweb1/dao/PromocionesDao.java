package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Compra;

public interface PromocionesDao {
	void autoPromocionar();
	void productosPocoStock();
}