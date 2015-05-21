package service;

public class TesteDoAvaliador {

	public static void main(String[] args) {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 novo");
		
		leilao.propoe(new Lance(joao,300.0));
		leilao.propoe(new Lance(jose,400.0));
		leilao.propoe(new Lance(maria,250.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//imprime 400
		System.out.println(leiloeiro.getMaiorLance());
		
		//imprime 250
		System.out.println(leiloeiro.getMenorLance());
	}

}
