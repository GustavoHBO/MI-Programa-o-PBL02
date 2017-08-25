package br.uefs.ecomp.sisNema.util;

/**
 * @author Gustavo Henrique
 * @since 21 de fevereiro de 2016.
 */
public class NoPilha {

	private Object objeto = null; 	// Objeto do n�.
	private NoPilha next = null;	//Pr�ximo n� da lista. 

	/**
	 * Construtor - Cria um n� com o objeto recebido.
	 * @param objeto - Objeto a ser inserido no n�.
	 */
	public NoPilha(Object objeto){// Cria um n� com o objeto recebido.
		this.setObjeto(objeto);
	}

	/**
	 * Retorna o objeto do n�.
	 * @return objeto - Objeto do n�.
	 */
	public Object getObjeto() {
		return objeto;
	}

	/**
	 * Altera o objeto do n�.
	 * @param objeto - Objeto a ser inserido no n�;
	 */
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

	/**
	 * Retorna a referencia para o pr�ximo n�.
	 * @return next - Referencia para o pr�ximo n�.
	 */
	public NoPilha getNext() {
		return next;
	}

	/**
	 * Altera a referencia para o pr�ximo n�.
	 * @param next - Referencia para o novo n�.
	 */
	public void setNext(NoPilha next) {
		this.next = next;
	}
}
