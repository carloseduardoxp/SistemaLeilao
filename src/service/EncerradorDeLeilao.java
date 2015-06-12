package service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import dao.LeilaoDao;

public class EncerradorDeLeilao {
	
	private int total = 0;
	
	private LeilaoDao dao;
	
	public EncerradorDeLeilao(LeilaoDao leilaoDao) {
		this.dao = leilaoDao;
	}
	
	public void encerra() {	
		List<Leilao> todosLeiloesCorrentes = dao.correntes();		
		for (Leilao leilao: todosLeiloesCorrentes) {
			if (comecouSemanaPassada(leilao)) {
				leilao.encerra();
				total++;
				dao.atualiza(leilao);
			}
		}		
	}

	private boolean comecouSemanaPassada(Leilao leilao) {
		Calendar fim = Calendar.getInstance();
		fim.add(Calendar.HOUR, 1);
		return diasEntre(leilao.getData(),fim) >= 7;
	}
	
	private int diasEntre(Calendar inicio,Calendar fim) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(sdf.format(inicio.getTime()));
		System.out.println(sdf.format(fim.getTime()));
		Calendar data = (Calendar)inicio.clone();
		int diasNoIntervalo = 0;
		while (data.before(fim)) {
			data.add(Calendar.DAY_OF_MONTH,1);
			diasNoIntervalo++;
		}
		return diasNoIntervalo;
	}
	
	public int getQuantidadeDeEncerrados() {
		return total;
	}
	
	
	

}
