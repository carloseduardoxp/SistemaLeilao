package service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import dao.LeilaoDao;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
public class EncerradorDeLeilaoTest {
	
	@Test
	public void deveEncerrarLeiloesQueComecaramUmaSemanaAtras() {
		Calendar antiga = Calendar.getInstance();
		antiga.set(2015,5,01);
		
		Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
				.naData(antiga).constroi();
		Leilao leilao2 = new CriadorDeLeilao().para("Geladeira")
				.naData(antiga).constroi();
		List<Leilao> leiloesAntigos = Arrays.asList(leilao1,leilao2);
		
		LeilaoDao daoFalso = mock(LeilaoDao.class);
		
		when(daoFalso.correntes()).thenReturn(leiloesAntigos);
		
		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso);
		encerrador.encerra();
		
		assertTrue(leilao1.isEncerrado());
		assertTrue(leilao2.isEncerrado());
		
		assertEquals(2,encerrador.getQuantidadeDeEncerrados());
		
		verify(daoFalso,atMost(1)).atualiza(leilao1);
		verify(daoFalso,times(1)).atualiza(leilao2);
	}
	
	@Test
	public void naoDeveEncerrarNenhumLeilao() {
		Calendar antiga = Calendar.getInstance();
		//antiga.set(2015,5,12);
		
		Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
				.naData(antiga).constroi();
		Leilao leilao2 = new CriadorDeLeilao().para("Geladeira")
				.naData(antiga).constroi();
		List<Leilao> leiloesAntigos = Arrays.asList(leilao1,leilao2);
		
		LeilaoDao daoFalso = mock(LeilaoDao.class);
		
		when(daoFalso.correntes()).thenReturn(leiloesAntigos);
		
		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso);
		encerrador.encerra();
		
		assertFalse(leilao1.isEncerrado());
		assertFalse(leilao2.isEncerrado());
		
		assertEquals(0,encerrador.getQuantidadeDeEncerrados());
	}
	
	@Test
	public void leilaoComUmDiaAntesDeSerEncerrado() {
		Calendar antiga = Calendar.getInstance();
		antiga.add(Calendar.DATE,-5);
		
		Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
				.naData(antiga).constroi();
		
		List<Leilao> leiloesAntigos = Arrays.asList(leilao1);
		
		LeilaoDao daoFalso = mock(LeilaoDao.class);
		
		when(daoFalso.correntes()).thenReturn(leiloesAntigos);
		
		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso);
		encerrador.encerra();
		
		assertFalse(leilao1.isEncerrado());
		
		assertEquals(0,encerrador.getQuantidadeDeEncerrados());
	}
	
	@Test
	public void leilaoNoExatoDiaDeSerEncerrado() {
		Calendar antiga = Calendar.getInstance();
		antiga.add(Calendar.DATE,-6);
		
		Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
				.naData(antiga).constroi();
		
		List<Leilao> leiloesAntigos = Arrays.asList(leilao1);
		
		LeilaoDao daoFalso = mock(LeilaoDao.class);
		
		when(daoFalso.correntes()).thenReturn(leiloesAntigos);
		
		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso);
		encerrador.encerra();
		
		assertTrue("Leilão 1 está encerrado? "+leilao1.isEncerrado(),leilao1.isEncerrado());
		
		assertEquals("Quantidade de leilões encerrados "+encerrador.getQuantidadeDeEncerrados(),
				1,encerrador.getQuantidadeDeEncerrados());
	}

}
