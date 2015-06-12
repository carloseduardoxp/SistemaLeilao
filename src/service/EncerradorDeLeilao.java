package service;

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
		return diasEntre(leilao.getData(),Calendar.getInstance()) >= 7;
	}
	
	private int diasEntre(Calendar inicio,Calendar fim) {		
		Calendar data = (Calendar)inicio.clone();
		int diasNoIntervalo = 0;
		while (data.before(fim) || data.equals(fim)) {
			data.add(Calendar.DAY_OF_MONTH,1);
			diasNoIntervalo++;
		}
		return diasNoIntervalo;
	}
	
	public int getQuantidadeDeEncerrados() {
		return total;
	}
	
	
	

}
