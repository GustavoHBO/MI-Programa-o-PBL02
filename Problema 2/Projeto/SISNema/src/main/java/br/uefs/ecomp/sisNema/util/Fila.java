package br.uefs.ecomp.sisNema.util;

/**
 * Classe fila.
 * @author Gustavo Henrique.
 * @since 15 de Janeiro de 2016.
 *
 */
public class Fila implements IFila {

	private No primeiro = null;// Primeiro n� da fila.
	private No ultimo = null;// Ultimo n� da fila.
	private int qNo = 0;// Quantidade de n�'s da fila.

	/**
	 * Verifica se a lista esta vazia.
	 * @see util.IFila#estaVazia()
	 * @return true - Caso vazia, false - Caso n�o.
	 */
	@Override
	public boolean estaVazia() {
		return primeiro == null;// Verifica se o primeiro n� existe.
	}

	/** M�todo que retorna o tamanho da fila.
	 * @see util.IFila#obterTamanho()
	 * @return int - Tamanho da fila.
	 */
	@Override
	public int obterTamanho() {
		return qNo;// Retorna o tamanho do n�.
	}

	/** M�todo que insere o objeto no final da fila.
	 * @see util.IFila#inserirFinal(java.lang.Object)
	 * @param o - Objeto a ser inserido no final da fila.
	 */
	@Override
	public void inserirFinal(Object o) {
		No novo = new No(o);
		if(estaVazia()){// Caso n�o exista objetos na fila.
			ultimo = novo;// Insere no inicio e no final, pois � o primeiro e ultimo objeto.
			primeiro = novo;
			qNo++;// Incrementa a quantidade de n�'s da fila.
		}
		else{// Caso exista objetos na lista.
			ultimo.setNext(novo);// Insere o objeto no final.
			ultimo = novo;// E ultimo passa a ser o novo objeto.
			qNo++;// Incrementa a quantidade de no's da fila.
		}

	}

	/** M�todo que remove um objeto no inicio da fila.
	 * @see util.IFila#removerInicio().
	 * @return objeto - Objeto removido do inicio.
	 */
	@Override
	public Object removerInicio() {
		Object objeto = null;
		if(estaVazia()){
			return null;
		}
		else{
			objeto = primeiro.getObjeto();
			primeiro = primeiro.getNext();
			if(qNo == 1){
				primeiro = null;
				ultimo = null;
			}
			qNo--;
		}
		
		return objeto;
	}

	/** M�todo que retorna o primeiro objeto da fila.
	 * @see util.IFila#recuperarInicio()
	 * @return objeto - Primeiro objeto da fila.
	 */
	@Override
	public Object recuperarInicio() {
		Object objeto = primeiro.getObjeto();// Cria o objeto e recebe a referencia do primeiro.
		return objeto;// Retorna o primeiro objeto da fila.
	}

	/**
	 * Retorna o iterador da fila.
	 * @return iterador - Iterador da fila.
	 */

	public Iterador iterador(){
		Iterador iterador = new Iterador(primeiro);
		return iterador;
	}

}
