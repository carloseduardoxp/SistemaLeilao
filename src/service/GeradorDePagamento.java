package service;

import java.util.Calendar;
import java.util.List;

public class GeradorDePagamento {
	
	private final RepositorioDePagamentos pagamentos;
	
	private final RepositorioDeLeiloes leiloes;
	
	private final Avaliador avaliador;

	public GeradorDePagamento(RepositorioDeLeiloes leiloes,
			RepositorioDePagamentos pagamentos, Avaliador avaliador) {
		super();
		this.pagamentos = pagamentos;
		this.leiloes = leiloes;
		this.avaliador = avaliador;
	}
	
	public void gera() {
		List<Leilao> leiloesEncerrados = leiloes.encerrados();
		for (Leilao leilao: leiloesEncerrados) {
			avaliador.avalia(leilao);
			Pagamento novoPagamento = 
					new Pagamento(avaliador.getMaiorLance(),Calendar.getInstance());
			pagamentos.salva(novoPagamento);
		}			
	}

}
