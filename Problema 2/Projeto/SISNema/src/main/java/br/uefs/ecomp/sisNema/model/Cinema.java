package br.uefs.ecomp.sisNema.model;

import br.uefs.ecomp.sisNema.model.Endereco;
import br.uefs.ecomp.sisNema.util.Lista;

/**
 * Classe que define um cinema.
 * @author Gustavo Henrique
 * @since 25 de Dezembro de 2015.
 */
public class Cinema {

	private String nome;// Nome do cinema.
	private Endereco endereco;// Endereço do cinema.
	private int qtdSalas = 0;// Quantidade de salas do cinema.
	private int id; // ID do cinema.
	private static int idd = 0;// Usado para definir o ID do cinema.
	private Lista salas = new Lista();// Lista de salas do cinema.
	private Lista listaIngressos = new Lista();// Lista de ingressos vendidos do cinema.
	
	public Cinema(){
		this.id = ++idd;// Define o id do cinema.
	}
	/**
	 * Retorna o nome do cinema.
	 * @return nome - Nome do cinema.
	 */
	public String getNome() {// Retorna o nome do cinema.
		return nome;
	}
	
	/**
	 * Altera o nome do cinema.
	 * @param nome - Novo nome do cinema.
	 */
	public void setNome(String nome) {// Altera o nome do cinema.
		this.nome = nome;
	}

	/**
	 * Retorna o endereço do cinema.
	 * @return endereco - Endereço do cinema.
	 */
	public Endereco getEndereco() {// Retorna o endereço do cinema.
		return endereco;
	}

	/**
	 * Altera o endereço do cinema.
	 * @param endereco - Novo endereço do cinema.
	 */
	public void setEndereco(Endereco endereco) {// Altera o nome do cinema.
		this.endereco = endereco;
	}

	/**
	 * Retorna a quantidade de salas do cinema.
	 * @return qSalas - Quantidade de salas do cinema.
	 */
	public int getQtdSalas() {// Retorna a quantidade de salas do cinema.
		return qtdSalas;
	}

	/**
	 * Altera a quantidade de salas do cinema.
	 * @param qSalas - Nova quantidade de salas do cinema.
	 */
	public void setQtdSalas(int qtdSalas) {// Altera a quantidade de salas do cinema.
		this.qtdSalas = qtdSalas;
	}

	/**
	 * Retorna o número de identificação do cinema.
	 * @return id - Número de identificação do cinema.
	 */
	public int getId() {// Retorna o número de identificação do cinema.
		return id;
	}

	/**
	 * Altera o número de identificação do cinema.
	 * @param id - Novo número de identificação do cinema.
	 */
	public void setId(int id) {// Altera o número de identificação do cinema.
		this.id = id;
	}

	/**
	 * Retorna uma lista com todas as salas do cinema.
	 * @return listaSalas - Lista de salas do cinema.
	 */
	public Lista getSalas() {// Retorna uma lista encadeada com todas as salas do cinema.
		return salas;
	}

	/**
	 * Altera a lista de salas do cinema.
	 * @param salas - Lista de salas.
	 */
	public void setSalas(Lista salas) {
		this.salas = salas;
	}
	/**
	 * Retorna a lista de ingressos vendidos do cinema.
	 * @return listaIngressos - Lista de Ingressos.
	 */
	public Lista getListaIngressos() {
		return listaIngressos;
	}
	/**
	 * Altera a lista de ingressos vendidos do cinema.
	 * @param listaIngressos - Lista de Ingressos.
	 */
	public void setListaIngressos(Lista listaIngressos) {
		this.listaIngressos = listaIngressos;
	}
}
