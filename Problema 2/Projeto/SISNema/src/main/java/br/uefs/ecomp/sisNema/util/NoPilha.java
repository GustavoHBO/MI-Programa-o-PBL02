package br.uefs.ecomp.sisNema.util;

/**
 * @author Gustavo Henrique
 * @since 21 de fevereiro de 2016.
 */
public class NoPilha {

	private Object objeto = null; 	// Objeto do nó.
	private NoPilha next = null;	//Próximo nó da lista. 

	/**
	 * Construtor - Cria um nó com o objeto recebido.
	 * @param objeto - Objeto a ser inserido no nó.
	 */
	public NoPilha(Object objeto){// Cria um nó com o objeto recebido.
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
	public NoPilha getNext() {
		return next;
	}

	/**
	 * Altera a referencia para o próximo nó.
	 * @param next - Referencia para o novo nó.
	 */
	public void setNext(NoPilha next) {
		this.next = next;
	}
}
