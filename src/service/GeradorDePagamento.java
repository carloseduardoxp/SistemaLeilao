package service;

import java.util.Calendar;
import java.util.List;

public class GeradorDePagamento {
	
	private final RepositorioDePagamentos pagamentos;
	
	private final RepositorioDeLeiloes leiloes;
	
	private final Avaliador avaliador;
	
	private final Relogio relogio;

	public GeradorDePagamento(RepositorioDeLeiloes leiloes,
			RepositorioDePagamentos pagamentos, Avaliador avaliador,
			Relogio relogio) {		
		this.pagamentos = pagamentos;
		this.leiloes = leiloes;
		this.avaliador = avaliador;
		this.relogio = relogio;
	}
	
	public GeradorDePagamento(RepositorioDeLeiloes leiloes,
			RepositorioDePagamentos pagamentos, Avaliador avaliador) {		
		this(leiloes,pagamentos,avaliador,new RelogioDoSistema());
	}
	
	public void gera() {
		List<Leilao> leiloesEncerrados = leiloes.encerrados();
		for (Leilao leilao: leiloesEncerrados) {
			avaliador.avalia(leilao);
			Pagamento novoPagamento = 
					new Pagamento(avaliador.getMaiorLance(),primeiroDiaUtil());
			pagamentos.salva(novoPagamento);
		}			
	}

	private Calendar primeiroDiaUtil() {
		Calendar data = relogio.hoje();
		int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
		
		if (diaDaSemana == Calendar.SATURDAY) {
			data.add(Calendar.DAY_OF_MONTH,2);
		}
		if (diaDaSemana == Calendar.SUNDAY) {
			data.add(Calendar.DAY_OF_MONTH,1);
		}
		return data;
	}

}
