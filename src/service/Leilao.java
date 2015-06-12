package service;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Leilao {
	
	private String objetoLeiloado;
	
	private List<Lance> lances;
	
	private Calendar data;
	
	private Boolean encerrado;

	public Leilao(String objetoLeiloado,Calendar data) {
		this.objetoLeiloado = objetoLeiloado;
		this.lances = new ArrayList<Lance>();
		this.data = data;
		this.encerrado = false;
	}
	
	public Leilao(String objetoLeiloado) {
		this(objetoLeiloado,Calendar.getInstance());
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

	public void setData(Calendar data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Leilao [objetoLeiloado=" + objetoLeiloado + ", lances="
				+ lances + "]";
	}

	public Calendar getData() {
		return data;
	}

	public void encerra() {
		encerrado = true;		
	}

	public Boolean isEncerrado() {
		return encerrado;
	}


}
