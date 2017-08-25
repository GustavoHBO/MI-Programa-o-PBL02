package br.uefs.ecomp.sisNema.util;

/**
 * Classe No - Define os nó's da lista duplamente encadeada.
 * @author Gustavo Henrique.
 * @since 26 de Dezembro de 2015.
 */
public class No {

	private Object objeto = null; 	// Objeto do nó.
	private No next = null;			//Próximo nó da lista. 
	private No after = null;		//Anterior nó da lista.
	
	/**
	 * Construtor - Cria um nó com o objeto recebido.
	 * @param objeto - Objeto a ser inserido no nó.
	 */
	public No(Object objeto){// Cria um nó com o objeto recebido.
		this.setObjeto(objeto);
	}

	/**
	 * Retorna o objeto do nó.
	 * @return objeto - Objeto do nó.
	 */
	public Object getObjeto() {
		return objeto;
	}

	/**
	 * Altera o objeto do nó.
	 * @param objeto - Objeto a ser inserido no nó;
	 */
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

	/**
	 * Retorna a referencia para o próximo nó.
	 * @return next - Referencia para o próximo nó.
	 */
	public No getNext() {
		return next;
	}

	/**
	 * Altera a referencia para o próximo nó.
	 * @param next - Referencia para o novo nó.
	 */
	public void setNext(No next) {
		this.next = next;
	}

	/**
	 * Retorna a referencia para o nó anterior.
	 * @return after - Referencia para o nó anterior.
	 */
	public No getAfter() {
		return after;
	}

	/**
	 * Altera a referencia para o nó anterior.
	 * @param after - Referencia para o novo nó.
	 */
	public void setAfter(No after) {
		this.after = after;
	}
}
