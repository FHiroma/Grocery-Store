package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PromocionesDao;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;

@Service("ServicioAutomatizado")
@EnableScheduling
@Transactional
public class ServicioAutomatizadoImpl implements ServicioAutomatizado{
	@Inject
	private PromocionesDao promoDao;
	
	@Scheduled(fixedDelay = 10800000)
	public void autoPromocionar(){
		promoDao.autoPromocionar();
	}

	@Scheduled(fixedDelay = 240000)
	public void productosPocoStock() {
		promoDao.productosPocoStock();
	}
}
