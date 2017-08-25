package br.uefs.ecomp.sisNema.util;

import br.uefs.ecomp.sisNema.util.IPilha;

/**
 * @author Gustavo Henrique.
 * @since 21 de Fevereiro de 2016.
 *
 */
public class Pilha implements IPilha {

	private NoPilha top; // Refer�ncia para o topo da pilha.
	private int qNo = 0;// Quantidade de no's da pilha.

	
	/** M�todo que retorna o tamanho da pilha.
	 * @see br.uefs.ecomp.sisNema.util.IPilha#obtertTamanho().
	 * @return qNo - Quantidade de n�'s empilhados.
	 */
	@Override
	public int obtertTamanho() {
		return qNo;
	}

	/**
	 * M�todo que verifica se a pilha est� vazia.
	 * @see br.uefs.ecomp.sisNema.util.IPilha#estaVazia().
	 * @return true - Caso vazia, false - Caso n�o esteja vazia.
	 */
	@Override
	public boolean estaVazia() {
		return qNo == 0;
	}

	@Override
	/**
	 * M�todo que desempilha os objetos da pilha.
	 * @return obj - Objeto desempilhado.
	 */
	public Object removerTopo() {

		if(estaVazia()){
			return null;
		}
		else{
			Object obj = top.getObjeto();
			top = top.getNext();
			qNo--;
			return obj;
		}
	}

	@Override
	/**
	 * M�todo respons�vel por inserir objetos no topo da pilha.
	 * @param obj - Objeto a ser empilhado.
	 */
	public void inserirTopo(Object obj) {
		NoPilha no = new NoPilha(obj);
		no.setNext(top);
		top = no;
		qNo++;
	}

	@Override
	/**
	 * Retorna o topo da pilha.
	 * @return top - Topo da pilha.
	 */
	public Object recuperarTopo() {
		return top;
	}
}
