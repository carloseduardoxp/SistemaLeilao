package service;

import java.util.Calendar;

public class CriadorDeLeilao {
	
	private Leilao leilao; 
	
	public CriadorDeLeilao para(String objetoLeiloado) {
		this.leilao = new Leilao(objetoLeiloado);
		return this;
	}
	
	public CriadorDeLeilao naData(Calendar data) {
		this.leilao.setData(data);
		return this;
	}
	
	public CriadorDeLeilao lance(Usuario usuario, Double valor) {
		this.leilao.propoe(new Lance(usuario,valor));
		return this;
	}
	
	public Leilao constroi() {
		return leilao;
	}
	
	

}
