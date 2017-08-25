package br.uefs.ecomp.sisNema.model;

/**
 * Classe que define o endere�o.
 * @author Gustavo Henrique.
 * @since 25 de Dezembro de 2015.
 */
public class Endereco {

	private String rua, bairro, pais, estado, cep, cidade, complemento;
	private int numero;
	
	/**
	 * Construtor, define os par�metros do endere�o.
	 */
	public Endereco(){
		
	}
	
	/**
	 * Retorna o nome da rua.
	 * @return rua - Nome da rua.
	 */
	public String getRua() {// Retorna o nome da rua.
		return rua;
	}

	/**
	 * Modifica o nome da rua.
	 * @param rua - Novo nome da rua.
	 */
	public void setRua(String rua) {// Altera o nome da rua.
		this.rua = rua;
	}

	/**
	 * Retorna o nome do bairro.
	 * @return bairro - Nome do bairro.
	 */
	public String getBairro() {//Retorna o nome do bairro.
		return bairro;
	}

	/**
	 * Altera o nome do bairro.
	 * @param bairro - Novo nome do bairro.
	 */
	public void setBairro(String bairro) {// Altera o nome do bairro.
		this.bairro = bairro;
	}

	/**
	 * Retorna o nome do pa�s.
	 * @return pais - Nome do pa�s.
	 */
	public String getPais() {// Retorna o nome do pa�s.
		return pais;
	}

	/**
	 * Altera o nome do pa�s.
	 * @param pais - Novo nome do pa�s.
	 */
	public void setPais(String pais) {// Altera o nome do pa�s.
		this.pais = pais;
	}

	/**
	 * Retorna o n�mero.
	 * @return numero - N�mero.
	 */
	public int getNumero() {// Retorna o n�mero.
		return numero;
	}

	/**
	 * Altera o n�mero.
	 * @param numero - Novo n�mero.
	 */
	public void setNumero(int numero) {// Altera o n�mero.
		this.numero = numero;
	}

	/**
	 * Retorna o nome do estado.
	 * @return estado - Nome do estado.
	 */
	public String getEstado() {// Retorna o nome do estado.
		return estado;
	}

	/**
	 * Altera o nome do estado.
	 * @param estado - Novo nome do estado.
	 */
	public void setEstado(String estado) {// Altera o nome do estado.
		this.estado = estado;
	}

	/**
	 * Retorna o CEP.
	 * @return cep - CEP.
	 */
	public String getCep() {// Retorna no CEP.
		return cep;
	}

	/**
	 * Altera o CEP.
	 * @param cep - Novo CEP.
	 */
	public void setCep(String cep) {// Altera o CEP.
		this.cep = cep;
	}

	/**
	 * Retorna o nome da cidade.
	 * @return cidade - Nome da cidade.
	 */
	public String getCidade() {// Retorna o nome da cidade.
		return cidade;
	}

	/**
	 * Altera o nome da cidade.
	 * @param cidade - Novo nome da cidade.
	 */
	public void setCidade(String cidade) {// Altera o nome da cidade.
		this.cidade = cidade;
	}

	/**
	 * Retorna o complemento do endere�o.
	 * @return complemento - Complemento do endere�o.
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * Altera o complemento do endere�o.
	 * @param complemento - Complemento do endere�o.
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
