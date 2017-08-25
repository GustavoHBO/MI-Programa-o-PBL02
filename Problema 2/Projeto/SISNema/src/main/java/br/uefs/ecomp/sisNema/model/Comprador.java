package br.uefs.ecomp.sisNema.model;

import br.uefs.ecomp.sisNema.util.Lista;
import br.uefs.ecomp.sisNema.util.Comparable;

/**
 * Classe que define o usu�rio.
 * @author Gustavo Henrique
 * @since 25 de Dezembro de 2015.
 */
public class Comprador implements Comparable{

	private String nome;// Nome do usu�rio.
	private Endereco endereco;// Endere�o do usu�rio.
	private String telefone, email;// Telefone e e-mail.
	private int documento;//N�mero de documento do comprador.
	private Lista listaIngressos = new Lista();// Criei uma lista de ingressos para poder unir cinemas, salas e sess�es, sem precisar uma lista para cada.

	/**
	 * Construtor do usu�rio, como todos os campos s�o de preenchimento obrigat�rio, foi definido um construtor padr�o.
	 * @param nome - Nome do usu�rio.
	 * @param telefone - Telefone do usu�rio.
	 * @param email - E-mail do usu�rio.
	 * @param documento - Documento de identifica��o do usu�rio.
	 * @param registroFan - Caso o usu�rio fa�a parte de um fan-clube.
	 * @param endereco - Endere�o do usu�rio.
	 */
	public Comprador(){
	}
	/**
	 * Retorna o nome do usu�rio.
	 * @return nome - Nome do usu�rio.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Altera o nome do usu�rio.
	 * @param nome - Novo nome do usu�rio.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o endere�o do usu�rio.
	 * @return endereco - Endere�o do usu�rio.
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * Altera o endere�o do usu�rio.
	 * @param endereco - Novo endere�o do usu�rio.
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	/**
	 * Retorna o telefone do usu�rio.
	 * @return telefone - Telefone do usu�rio.
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Altera o telefone do usu�rio.
	 * @param telefone - Novo telefone do usu�rio.
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Retorna o e-mail do usu�rio.
	 * @return email - E-mail do usu�rio.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Altera o e-mail do usu�rio.
	 * @param email - Novo e-mail do usu�rio.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna o documento do usu�rio.
	 * @return documento - Documento do usu�rio.
	 */
	public int getDocumento() {
		return documento;
	}

	/**
	 * Altera o documento do usu�rio.
	 * @param i - Novo documento do usu�rio.
	 */
	public void setDocumento(int i) {
		this.documento = i;
	}

	/**
	 * Retorna uma lista de ingressos aos quais o usu�rio possui.
	 * @return listaCinema - Lista de cinemas.
	 */
	public Lista getListaIngressos() {
		return listaIngressos;
	}

	/**
	 * Altera a lista de ingressos que o usu�rio possui.
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
