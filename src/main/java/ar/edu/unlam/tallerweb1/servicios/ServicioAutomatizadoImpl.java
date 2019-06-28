package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PromocionesDao;

@Service("ServicioAutomatizado")
@EnableScheduling
@Transactional
public class ServicioAutomatizadoImpl implements ServicioAutomatizado{
	@Inject
	private PromocionesDao promoDao;
	
	@Scheduled(fixedDelay = 60000)
	public void autoPromocionar(){
		promoDao.autoPromocionar();
		System.out.println("Promociones");
	}

	@Scheduled(fixedDelay = 60000)
	public void productosPocoStock() {
		promoDao.productosPocoStock();
		System.out.println("Busca Notificaciones");
	}

	@Scheduled(fixedDelay = 60000)
	public void productosVencidos() {
		promoDao.productosVencidos();
	}
}
