package br.uefs.ecomp.sisNema.model;

/**
 * Define os parâmetros da sessão.
 * @author Gustavo Henrique.
 * @since 25 de Dezembro de 2015.
 */
public class Sessao {

	private int horaInicio;// Horário de inicio da sessão.
	private int horaFim;// Horário de fim da sessão.
	private int qtdIngressosVendidos = 0;// Quantidade de ingressos vendidos.
	
	
	/**
	 * Construtor padrão de sessão.
	 */
	public Sessao(){
		
	}
	
	/**
	 * Retorna o horário de inicio da sessão.
	 * @return horaInicio - Horário da sessão.
	 */
	public int getHoraInicio() {// Retorna o horário.
		return horaInicio;
	}
	
	/**
	 * Altera o horário de inicio da sessão.
	 * @param horaInicio - Novo horário de inicio.
	 */
	public void setHoraInicio(int i) {// Altera o horário.
		this.horaInicio = i;
	}

	/**
	 * Retorna o horário de inicio da sessão.
	 * @return horaFim - Horário da sessão.
	 */
	public int getHoraFim() {// Retorna o horário do fim da sessão.
		return horaFim;
	}

	/**
	 * Altera o horário de termino da sessão.
	 * @param horaFim - Horário do fim da sessão.
	 */
	public void setHoraFim(int horaFim) {// Altera o horário do fim da sessão.
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
