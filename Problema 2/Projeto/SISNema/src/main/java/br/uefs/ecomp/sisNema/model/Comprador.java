package br.uefs.ecomp.sisNema.model;

import br.uefs.ecomp.sisNema.util.Lista;
import br.uefs.ecomp.sisNema.util.Comparable;

/**
 * Classe que define o usuário.
 * @author Gustavo Henrique
 * @since 25 de Dezembro de 2015.
 */
public class Comprador implements Comparable{

	private String nome;// Nome do usuário.
	private Endereco endereco;// Endereço do usuário.
	private String telefone, email;// Telefone e e-mail.
	private int documento;//Número de documento do comprador.
	private Lista listaIngressos = new Lista();// Criei uma lista de ingressos para poder unir cinemas, salas e sessões, sem precisar uma lista para cada.

	/**
	 * Construtor do usuário, como todos os campos são de preenchimento obrigatório, foi definido um construtor padrão.
	 * @param nome - Nome do usuário.
	 * @param telefone - Telefone do usuário.
	 * @param email - E-mail do usuário.
	 * @param documento - Documento de identificação do usuário.
	 * @param registroFan - Caso o usuário faça parte de um fan-clube.
	 * @param endereco - Endereço do usuário.
	 */
	public Comprador(){
	}
	/**
	 * Retorna o nome do usuário.
	 * @return nome - Nome do usuário.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Altera o nome do usuário.
	 * @param nome - Novo nome do usuário.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o endereço do usuário.
	 * @return endereco - Endereço do usuário.
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * Altera o endereço do usuário.
	 * @param endereco - Novo endereço do usuário.
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	/**
	 * Retorna o telefone do usuário.
	 * @return telefone - Telefone do usuário.
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Altera o telefone do usuário.
	 * @param telefone - Novo telefone do usuário.
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Retorna o e-mail do usuário.
	 * @return email - E-mail do usuário.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Altera o e-mail do usuário.
	 * @param email - Novo e-mail do usuário.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna o documento do usuário.
	 * @return documento - Documento do usuário.
	 */
	public int getDocumento() {
		return documento;
	}

	/**
	 * Altera o documento do usuário.
	 * @param i - Novo documento do usuário.
	 */
	public void setDocumento(int i) {
		this.documento = i;
	}

	/**
	 * Retorna uma lista de ingressos aos quais o usuário possui.
	 * @return listaCinema - Lista de cinemas.
	 */
	public Lista getListaIngressos() {
		return listaIngressos;
	}

	/**
	 * Altera a lista de ingressos que o usuário possui.
	 * @param listaCinema - Lista de cinemas.
	 */
	public void setListaIngressos(Lista listaIngressos) {
		this.listaIngressos = listaIngressos;
	}
	
	
	public int compare(Object o) {
		Comprador comp = (Comprador)o;
		return nome.compareTo(comp.getNome());
	}
}
