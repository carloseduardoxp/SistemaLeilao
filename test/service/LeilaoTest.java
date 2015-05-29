package service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeilaoTest {
	
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		assertEquals(0,leilao.getLances().size());
		leilao.propoe(new Lance(new Usuario("Darcius"),2000d));
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000d,leilao.getLances().get(0).getValor(),0.0001);				
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		assertEquals(0,leilao.getLances().size());
		leilao.propoe(new Lance(new Usuario("Darcius"),2000d));
		leilao.propoe(new Lance(new Usuario("Cléber"),4000d));
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000d,leilao.getLances().get(0).getValor(),0.0001);				
		assertEquals(4000d,leilao.getLances().get(1).getValor(),0.0001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		assertEquals(0,leilao.getLances().size());
		leilao.propoe(new Lance(new Usuario("Darcius"),2000d));
		leilao.propoe(new Lance(new Usuario("Darcius"),3000d));
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000d,leilao.getLances().get(0).getValor(),0.0001);				
	}

	@Test
	public void naoDeveAceitarMaisDoQue5LancesDoMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		assertEquals(0,leilao.getLances().size());
		Usuario darcius = new Usuario("Darcius");
		Usuario rodrigo = new Usuario("Rodrigo");
		
		leilao.propoe(new Lance(darcius,2000d));
		leilao.propoe(new Lance(rodrigo,2500d));
		leilao.propoe(new Lance(darcius,3000d));		
		leilao.propoe(new Lance(rodrigo,3500d));
		leilao.propoe(new Lance(darcius,4000d));		
		leilao.propoe(new Lance(rodrigo,4500d));
		leilao.propoe(new Lance(darcius,5000d));		
		leilao.propoe(new Lance(rodrigo,5500d));
		leilao.propoe(new Lance(darcius,6000d));		
		leilao.propoe(new Lance(rodrigo,6500d));
		leilao.propoe(new Lance(darcius,7000d));
		
		assertEquals(10, leilao.getLances().size());
		Lance ultimoLance = leilao.getLances().get(leilao.getLances().size() - 1);
		assertEquals(6500d,ultimoLance.getValor(),0.0001);
	}
	
	
	
	
	
	
	
	
	
}
