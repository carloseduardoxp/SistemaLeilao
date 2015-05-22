package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Avaliador {
	
	private Double maiorDeTodos = Double.NEGATIVE_INFINITY;
	
	private Double menorDeTodos = Double.POSITIVE_INFINITY;
	
	private List<Lance> maioresLances;
	
	public void avalia(Leilao leilao) {
		for (Lance lance: leilao.getLances()) {
			if (lance.getValor() > maiorDeTodos) {
				maiorDeTodos = lance.getValor();
			} 
			if (lance.getValor() < menorDeTodos) {
				menorDeTodos = lance.getValor();
			}
		}		
		pegaOsMaioresNo(leilao);
	}
	
	private void pegaOsMaioresNo(Leilao leilao) {
		maioresLances = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(maioresLances, new Comparator<Lance>() {			
			@Override
			public int compare(Lance o1, Lance o2) {
				return o2.getValor().compareTo(o1.getValor());
			}
		});
		maioresLances = maioresLances.subList(0, maioresLances.size() >= 3? 3: maioresLances.size());
	}

	public Double getMenorLance() {
		return menorDeTodos;
	}
	
	public Double getMaiorLance() {
		return maiorDeTodos;
	}

	public List<Lance> getMaioresLances() {
		return maioresLances;
	}

}
