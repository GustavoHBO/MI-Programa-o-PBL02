package br.uefs.ecomp.sisNema.model;

import br.uefs.ecomp.sisNema.util.Lista;

/**
 * Classe que define uma sala.
 * @author Gustavo Henrique.
 * @since 25 de Dezembro de 2015.
 */

public class Sala {// Classe que define os atributos da sala.

	private int qtdCadeiras = 0;// Quantidade de cadeiras da sala.
	private int numeroSala = 0; // Id da sala, para associar com a sessão.
	private Lista listaSessoes = new Lista();// Lista de sessões da sala.

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
	 * Retorna o número de identificação da sala.
	 * @return id - Número de identificação da sala.
	 */
	public int getNumeroSala() {
		return numeroSala;
	}

	/**
	 * Altera o número de identificação da sala.
	 * @param id - Novo número de identificação da sala.
	 */
	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	/**
	 * Retorna a lista de sessões cadastradas da a sala.
	 * @return salaSessoes - Lista de sessões cadastradas.
	 */
	public Lista getListaSessoes() {
		return listaSessoes;
	}

	/**
	 * Altera a lista de sessões cadastradas da sala.
	 * @param salaSessoes - Lista de sessões.
	 */
	public void setListaSessoes(Lista listaSessoes) {
		this.listaSessoes = listaSessoes;
	}
}
