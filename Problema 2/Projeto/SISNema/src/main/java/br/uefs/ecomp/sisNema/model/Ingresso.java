package br.uefs.ecomp.sisNema.model;

public class Ingresso {

	private Cinema cinema;
	private Sala sala;
	private Sessao sessao;
	private int id = 0;
	private static int idd = 0;
	
	/**
	 * Construtor do ingresso, define o cinema, a sala e a sessão ao qual o usuário efetuou a compra.
	 * @param cinema - Cinema ao qual o usuário fez a compra do ingresso.
	 * @param sala - Sala em que vai acontecer o filme.
	 * @param sessao - Sessão ao qual vai acontecer o filme.
	 */
	public Ingresso(Cinema cinema, Sala sala, Sessao sessao){
		this.setCinema(cinema);
		this.setSala(sala);
		this.setSessao(sessao);
		this.id = idd++;
	}

	/**
	 * Retorna o cinema da compra do ingresso.
	 * @return cinema - Cinema ao qual ocorreu a compra.
	 */
	public Cinema getCinema() {
		return cinema;
	}

	/**
	 * Altera o cinema da compra do ingresso.
	 * @param cinema - Cinema ao qual ocorreu a compra.
	 */
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	/**
	 * Retorna a sala em que vai ocorrer o filme.
	 * @return sala - Sala do filme.
	 */
	public Sala getSala() {
		return sala;
	}

	/**
	 * Altera a sala ao qual vai ocorrer o filme.
	 * @param sala - Sala do filme.
	 */
	public void setSala(Sala sala) {
		this.sala = sala;
	}

	/**
	 * Retorna a sessão ao qual vai ocorrer o filme.
	 * @return sessao - Sessão ao qual vai ocorrer o filme.
	 */
	public Sessao getSessao() {
		return sessao;
	}

	/**
	 * Altera a sessão ao qual vai ocorrer o filme.
	 * @param sessao - Sessão ao qual vai ocorrer o filme.
	 */
	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	/**
	 * Retorna o ID do ingresso.
	 * @return id - ID do ingresso.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Altera o ID do ingresso.
	 * @param id - ID do ingresso.
	 */
	public void setId(int id) {
		this.id = id;
	}
}
