package ar.edu.unlam.tallerweb1.dao;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Compra;

@Repository("promoDao")
public class PromocionesDaoImpl implements PromocionesDao{
	

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public void autoPromocionar() {
		Integer i = 432000000;
		Calendar fechaLimite = Calendar.getInstance();
		fechaLimite.add(Calendar.MILLISECOND, i);
		Session sesion = sessionFactory.getCurrentSession();
		List<Compra> lista = sesion.createCriteria(Compra.class)
				.add(Restrictions.le("fechaVencimiento", fechaLimite))
				.list();
		for(Compra compra: lista){
			compra.setOferta(true);
			sesion.update(compra);
		}
	}

}
