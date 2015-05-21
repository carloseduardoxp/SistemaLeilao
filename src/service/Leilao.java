package service;
import java.util.ArrayList;
import java.util.List;


public class Leilao {
	
	private String objetoLeiloado;
	
	private List<Lance> lances;

	public Leilao(String objetoLeiloado) {
		this.objetoLeiloado = objetoLeiloado;
		this.lances = new ArrayList<Lance>();
	}

	public String getObjetoLeiloado() {
		return objetoLeiloado;
	}

	public void propoe(Lance lance) {
		this.lances.add(lance);
	}

	public List<Lance> getLances() {
		return lances;
	}
	

}
