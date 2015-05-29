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
		if (lances.isEmpty() || podeDarLance(lance.getUsuario())) {
			this.lances.add(lance);
		}		
	}
	
	private boolean podeDarLance(Usuario usuario) {
		return !usuario.equals(ultimoLanceDado().getUsuario())
				&& qtdLancesDo(usuario) < 5;
	}
	
	private int qtdLancesDo(Usuario usuario) {
		int total = 0;
		for (Lance lanceJaRecebido: lances) {
			if (lanceJaRecebido.getUsuario().equals(usuario)) {
				total++;
			}
		}
		return total;
	}
	
	private Lance ultimoLanceDado() {
		return lances.get(lances.size() - 1);
	}

	public List<Lance> getLances() {
		return lances;
	}

	@Override
	public String toString() {
		return "Leilao [objetoLeiloado=" + objetoLeiloado + ", lances="
				+ lances + "]";
	}

}
