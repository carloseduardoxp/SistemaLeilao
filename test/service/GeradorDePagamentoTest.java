package service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Calendar;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class GeradorDePagamentoTest {
	
	@Test
	public void deveGerarPagamentoParaUmLeilaoEncerrado() {
		RepositorioDeLeiloes leiloes = mock(RepositorioDeLeiloes.class);
		RepositorioDePagamentos pagamentos = mock(RepositorioDePagamentos.class);
		Avaliador avaliador = mock(Avaliador.class);
		
		Leilao leilao = new CriadorDeLeilao()
			.para("Playstation")
			.lance(new Usuario("José da Silva"), 2000.0)
			.lance(new Usuario("Maria Pereira"), 2500.0)
			.constroi();
		
		when(leiloes.encerrados()).thenReturn(Arrays.asList(leilao));
		when(avaliador.getMaiorLance()).thenReturn(2500.0);
		
		GeradorDePagamento gerador = new GeradorDePagamento(leiloes, pagamentos, avaliador);
		gerador.gera();

		//como fazer assert no pagamento gerado
		//criamos o Argument Captor que sabe capturar um argumento
		ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
		//capturamos o argumento que foi passado para o método salvar
		verify(pagamentos).salva(argumento.capture());
		
		Pagamento pagamentoGerado = argumento.getValue();
		assertEquals(2500.0,pagamentoGerado.getValor(),0.001);
	}
	
	@Test
	public void deveEmpurrarParaOProximoDiaUtil() {
		RepositorioDeLeiloes leiloes = mock(RepositorioDeLeiloes.class);
		RepositorioDePagamentos pagamentos = mock(RepositorioDePagamentos.class);
		Avaliador avaliador = mock(Avaliador.class);
		
		Leilao leilao = new CriadorDeLeilao()
			.para("Playstation")
			.lance(new Usuario("José da Silva"), 2000.0)
			.lance(new Usuario("Maria Pereira"), 2500.0)
			.constroi();
		
		when(leiloes.encerrados()).thenReturn(Arrays.asList(leilao));
		when(avaliador.getMaiorLance()).thenReturn(2500.0);
		
		Relogio relogio = new Relogio() {
			
			@Override
			public Calendar hoje() {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH,20);
				calendar.set(Calendar.MONTH,5);
				calendar.set(Calendar.YEAR,2015);
				return calendar;
			}
		};
		
		GeradorDePagamento gerador = new GeradorDePagamento(leiloes, pagamentos, avaliador,relogio);
		gerador.gera();

		//como fazer assert no pagamento gerado
		//criamos o Argument Captor que sabe capturar um argumento
		ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
		//capturamos o argumento que foi passado para o método salvar
		verify(pagamentos).salva(argumento.capture());
		
		Pagamento pagamentoGerado = argumento.getValue();
		assertEquals(Calendar.MONDAY, pagamentoGerado.getData().get(Calendar.DAY_OF_WEEK));
		
		assertEquals(2500.0,pagamentoGerado.getValor(),0.001);
	}


}
