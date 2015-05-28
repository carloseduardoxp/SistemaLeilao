package service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import util.LeilaoException;

public class AvaliadorTest {
	
	private Avaliador leiloeiro;
	
	private Usuario joao;
	
	private Usuario jose;
	
	private Usuario maria;
	
	@Before
	public void criaObjetos() {
		leiloeiro = new Avaliador();
		joao = new Usuario("Joao");
		jose = new Usuario("Jose");
		maria = new Usuario("Maria");
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {		
		//1 - montar o cenário
		Leilao leilao = new CriadorDeLeilao()
					.para("Playstation 3 novo")
					.lance(maria,250.0)
					.lance(joao,300.0)
					.lance(jose,400.0)
					.constroi();
		
		//2 - executar a ação
		leiloeiro.avalia(leilao);
		
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		//3 - efetuar a validação 
		assertEquals(maiorEsperado,leiloeiro.getMaiorLance(),0.0001);		
		assertEquals(menorEsperado,leiloeiro.getMenorLance(),0.0001);						
	}
	
	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {
		Leilao leilao = new CriadorDeLeilao()
						.para("Playstation 3 novo")
						.lance(jose,400.0)
						.lance(joao,300.0)
						.lance(maria,250.0)												
						.constroi();
		
		leiloeiro.avalia(leilao);
		
		//imprime 400
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		assertEquals(maiorEsperado,leiloeiro.getMaiorLance(),0.0001);
		
		assertEquals(menorEsperado,leiloeiro.getMenorLance(),0.0001);						
	}
	
	@Test
	public void deveEntenderLancesSemNenhumaOrdem() {
		Leilao leilao = new CriadorDeLeilao()
						.para("Playstation 3 novo")
						.lance(jose,400.0)						
						.lance(maria,250.0)	
						.lance(joao,300.0)
						.constroi();
		
		leiloeiro.avalia(leilao);
		
		//imprime 400
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		assertEquals(maiorEsperado,leiloeiro.getMaiorLance(),0.0001);
		
		assertEquals(menorEsperado,leiloeiro.getMenorLance(),0.0001);						
	}
	
	@Test
	public void deveEntenderApenasUmLance() {
		Leilao leilao = new CriadorDeLeilao()
						.para("Playstation 3 novo")
						.lance(jose,400.0)								
						.constroi();
		
		leiloeiro.avalia(leilao);
		
		//imprime 400
		double maiorEsperado = 400;
		double menorEsperado = 400;
		
		assertEquals(maiorEsperado,leiloeiro.getMaiorLance(),0.0001);
		
		assertEquals(menorEsperado,leiloeiro.getMenorLance(),0.0001);						
	}
	
	@Test
	public void deveRetornar3MaioresLances() {
		Leilao leilao = new CriadorDeLeilao()
						.para("Playstation 3 novo")
						.lance(jose,400.0)						
						.lance(maria,250.0)	
						.lance(joao,300.0)
						.lance(joao,450.0)
						.lance(jose,600.0)
						.constroi();
				
		leiloeiro.avalia(leilao);
		
		List<Lance> maioresLances = leiloeiro.getMaioresLances();
		
		assertEquals(3,maioresLances.size());
		assertEquals(600,maioresLances.get(0).getValor(),0.0001);
		assertEquals(450,maioresLances.get(1).getValor(),0.0001);
		assertEquals(400,maioresLances.get(2).getValor(),0.0001);		
	}
	
	@Test(expected=LeilaoException.class)
	public void deveLancarExcecao() {
		Leilao leilao = new CriadorDeLeilao()
						.para("Playstation 3 novo")
						.constroi();
				
		leiloeiro.avalia(leilao);								
	}


}
