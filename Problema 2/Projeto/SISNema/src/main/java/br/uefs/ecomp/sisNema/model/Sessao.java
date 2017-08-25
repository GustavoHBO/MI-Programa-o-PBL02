package br.uefs.ecomp.sisNema.model;

/**
 * Define os par�metros da sess�o.
 * @author Gustavo Henrique.
 * @since 25 de Dezembro de 2015.
 */
public class Sessao {

	private int horaInicio;// Hor�rio de inicio da sess�o.
	private int horaFim;// Hor�rio de fim da sess�o.
	private int qtdIngressosVendidos = 0;// Quantidade de ingressos vendidos.
	
	
	/**
	 * Construtor padr�o de sess�o.
	 */
	public Sessao(){
		
	}
	
	/**
	 * Retorna o hor�rio de inicio da sess�o.
	 * @return horaInicio - Hor�rio da sess�o.
	 */
	public int getHoraInicio() {// Retorna o hor�rio.
		return horaInicio;
	}
	
	/**
	 * Altera o hor�rio de inicio da sess�o.
	 * @param horaInicio - Novo hor�rio de inicio.
	 */
	public void setHoraInicio(int i) {// Altera o hor�rio.
		this.horaInicio = i;
	}

	/**
	 * Retorna o hor�rio de inicio da sess�o.
	 * @return horaFim - Hor�rio da sess�o.
	 */
	public int getHoraFim() {// Retorna o hor�rio do fim da sess�o.
		return horaFim;
	}

	/**
	 * Altera o hor�rio de termino da sess�o.
	 * @param horaFim - Hor�rio do fim da sess�o.
	 */
	public void setHoraFim(int horaFim) {// Altera o hor�rio do fim da sess�o.
		this.horaFim = horaFim;
	}
	/**
	 * Retorna a quantidade de ingressos vendidos.
	 * @return qtdIngressosVendidos - Quantidade de ingressos vendidos.
	 */
	public int getQtdIngressosVendidos() {
		return qtdIngressosVendidos;
	}

	/**
	 * Altera a quantidade de ingressos vendidos.
	 * @param qtdIngressosVendidos - Quantidade de ingressos vendidos.
	 */
	public void setQtdIngressosVendidos(int qtdIngressosVendidos) {
		this.qtdIngressosVendidos = qtdIngressosVendidos;
	}
}
