package service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AvaliadorTest {
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 novo");
		
		leilao.propoe(new Lance(maria,250.0));
		leilao.propoe(new Lance(joao,300.0));
		leilao.propoe(new Lance(jose,400.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//imprime 400
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		assertEquals(maiorEsperado,leiloeiro.getMaiorLance(),0.0001);
		
		assertEquals(menorEsperado,leiloeiro.getMenorLance(),0.0001);						
	}
	
	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 novo");
		
		leilao.propoe(new Lance(jose,400.0));		
		leilao.propoe(new Lance(joao,300.0));
		leilao.propoe(new Lance(maria,250.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//imprime 400
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		assertEquals(maiorEsperado,leiloeiro.getMaiorLance(),0.0001);
		
		assertEquals(menorEsperado,leiloeiro.getMenorLance(),0.0001);						
	}
	
	@Test
	public void deveEntenderLancesSemNenhumaOrdem() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 novo");
		
		leilao.propoe(new Lance(jose,400.0));
		leilao.propoe(new Lance(maria,250.0));
		leilao.propoe(new Lance(joao,300.0));	
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//imprime 400
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		assertEquals(maiorEsperado,leiloeiro.getMaiorLance(),0.0001);
		
		assertEquals(menorEsperado,leiloeiro.getMenorLance(),0.0001);						
	}
	
	@Test
	public void deveEntenderApenasUmLance() {
		Usuario jose = new Usuario("Jose");

		Leilao leilao = new Leilao("Playstation 3 novo");
		
		leilao.propoe(new Lance(jose,400.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//imprime 400
		double maiorEsperado = 400;
		double menorEsperado = 400;
		
		assertEquals(maiorEsperado,leiloeiro.getMaiorLance(),0.0001);
		
		assertEquals(menorEsperado,leiloeiro.getMenorLance(),0.0001);						
	}
	
	@Test
	public void deveRetornar3MaioresLances() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 novo");
		
		leilao.propoe(new Lance(jose,400.0));
		leilao.propoe(new Lance(maria,250.0));
		leilao.propoe(new Lance(joao,300.0));	
		leilao.propoe(new Lance(joao,450.0));
		leilao.propoe(new Lance(jose,600.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maioresLances = leiloeiro.getMaioresLances();
		
		assertEquals(3,maioresLances.size());
		assertEquals(600,maioresLances.get(0).getValor(),0.0001);
		assertEquals(450,maioresLances.get(1).getValor(),0.0001);
		assertEquals(400,maioresLances.get(2).getValor(),0.0001);
								
	}


}
