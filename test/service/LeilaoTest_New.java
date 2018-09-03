/* Cavanha CavanhaMan */
package service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeilaoTest_New {
	
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Notibuque Positivo");
		assertEquals(0,leilao.getLances().size());
		leilao.propoe(new Lance(new Usuario("Carlos"),2000d));
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000d,leilao.getLances().get(0).getValor(),0.0001);				
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Notibuque Positivo");
		assertEquals(0,leilao.getLances().size());
		leilao.propoe(new Lance(new Usuario("Carlos"),2000d));
		leilao.propoe(new Lance(new Usuario("Cléber"),4000d));
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000d,leilao.getLances().get(0).getValor(),0.0001);				
		assertEquals(4000d,leilao.getLances().get(1).getValor(),0.0001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Notibuque Positivo");
		assertEquals(0,leilao.getLances().size());
		leilao.propoe(new Lance(new Usuario("Carlos"),2000d));
		leilao.propoe(new Lance(new Usuario("Carlos"),3000d));
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000d,leilao.getLances().get(0).getValor(),0.0001);				
	}

	@Test
	public void naoDeveAceitarMaisDoQue5LancesDoMesmoUsuario() {
		Leilao leilao = new Leilao("Notibuque Positivo");
		assertEquals(0,leilao.getLances().size());
		Usuario carlos = new Usuario("Carlos");
		Usuario cavanha = new Usuario("Cavanha");
		
		leilao.propoe(new Lance(carlos,200d));
		leilao.propoe(new Lance(cavanha,250d));
		leilao.propoe(new Lance(carlos,300d));		
		leilao.propoe(new Lance(cavanha,350d));
		leilao.propoe(new Lance(carlos,400d));		
		leilao.propoe(new Lance(cavanha,450d));
		leilao.propoe(new Lance(carlos,500d));		
		leilao.propoe(new Lance(cavanha,550d));
		leilao.propoe(new Lance(carlos,600d));		
		leilao.propoe(new Lance(cavanha,650d));
		leilao.propoe(new Lance(carlos,700d));
		
		assertEquals(10, leilao.getLances().size());
		Lance ultimoLance = leilao.getLances().get(leilao.getLances().size() - 1);
		assertEquals(6500d,ultimoLance.getValor(),0.0001);
	}
	
	
	
	
	
	
	
	
	
}
