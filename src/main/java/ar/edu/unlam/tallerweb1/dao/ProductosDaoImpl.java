package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Productos;

@Repository("productosDao")
public class ProductosDaoImpl implements ProductosDao{

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public void setFechaVencimiento() {
		// TODO Auto-generated method stub
		
	}
	


}
