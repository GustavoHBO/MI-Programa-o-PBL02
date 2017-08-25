package br.uefs.ecomp.sisNema.model;

/**
 * Classe que define o endereço.
 * @author Gustavo Henrique.
 * @since 25 de Dezembro de 2015.
 */
public class Endereco {

	private String rua, bairro, pais, estado, cep, cidade, complemento;
	private int numero;
	
	/**
	 * Construtor, define os parâmetros do endereço.
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
	 * Retorna o nome do país.
	 * @return pais - Nome do país.
	 */
	public String getPais() {// Retorna o nome do país.
		return pais;
	}

	/**
	 * Altera o nome do país.
	 * @param pais - Novo nome do país.
	 */
	public void setPais(String pais) {// Altera o nome do país.
		this.pais = pais;
	}

	/**
	 * Retorna o número.
	 * @return numero - Número.
	 */
	public int getNumero() {// Retorna o número.
		return numero;
	}

	/**
	 * Altera o número.
	 * @param numero - Novo número.
	 */
	public void setNumero(int numero) {// Altera o número.
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
	 * Retorna o complemento do endereço.
	 * @return complemento - Complemento do endereço.
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * Altera o complemento do endereço.
	 * @param complemento - Complemento do endereço.
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
