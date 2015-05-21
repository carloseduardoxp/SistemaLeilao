package service;

public class Lance {
	
	private Usuario usuario;
	
	private Double valor;

	public Lance(Usuario usuario, Double valor) {
		this.usuario = usuario;
		this.valor = valor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Double getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "Lance [usuario=" + usuario + ", valor=" + valor + "]";
	}
	
	
	

}
