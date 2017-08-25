package br.uefs.ecomp.sisNema.model;

import br.uefs.ecomp.sisNema.util.Lista;

/**
 * Classe que define uma sala.
 * @author Gustavo Henrique.
 * @since 25 de Dezembro de 2015.
 */

public class Sala {// Classe que define os atributos da sala.

	private int qtdCadeiras = 0;// Quantidade de cadeiras da sala.
	private int numeroSala = 0; // Id da sala, para associar com a sess�o.
	private Lista listaSessoes = new Lista();// Lista de sess�es da sala.

	/**
	 * Retorna a quantidade de cadeiras da sala.
	 * @return qCadeiras - Quantidade de cadeiras da sala.
	 */
	public int getQtdCadeiras() {// Retorna a quantidade de cadeiras da sala.
		return qtdCadeiras;
	}

	/**
	 * Altera a quantidade de cadeiras da sala.
	 * @param qCadeiras - Nova quantidade de cadeiras da sala.
	 */
	public void setQtdCadeiras(int qtdCadeiras) {// Altera a quantidade de cadeiras da sala.
		this.qtdCadeiras = qtdCadeiras;
	}

	/**
	 * Retorna o n�mero de identifica��o da sala.
	 * @return id - N�mero de identifica��o da sala.
	 */
	public int getNumeroSala() {
		return numeroSala;
	}

	/**
	 * Altera o n�mero de identifica��o da sala.
	 * @param id - Novo n�mero de identifica��o da sala.
	 */
	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	/**
	 * Retorna a lista de sess�es cadastradas da a sala.
	 * @return salaSessoes - Lista de sess�es cadastradas.
	 */
	public Lista getListaSessoes() {
		return listaSessoes;
	}

	/**
	 * Altera a lista de sess�es cadastradas da sala.
	 * @param salaSessoes - Lista de sess�es.
	 */
	public void setListaSessoes(Lista listaSessoes) {
		this.listaSessoes = listaSessoes;
	}
}
