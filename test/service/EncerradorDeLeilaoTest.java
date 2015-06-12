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
		antiga.set(1999,1,20);
		
		Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
				.naData(antiga).constroi();
		Leilao leilao2 = new CriadorDeLeilao().para("Geladeira")
				.naData(antiga).constroi();
		List<Leilao> leiloesAntigos = Arrays.asList(leilao1,leilao2);
		
		LeilaoDao daoFalso = mock(LeilaoDao.class);
		
		when(daoFalso.correntes()).thenReturn(leiloesAntigos);
		
		EncerradorDeLeilao encerrador = new EncerradorDeLeilao();
		encerrador.encerra();
		
		assertTrue(leilao1.isEncerrado());
		assertTrue(leilao2.isEncerrado());
		
		assertEquals(2,encerrador.getQuantidadeDeEncerrados());
	}

}
