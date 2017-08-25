package br.uefs.ecomp.sisNema.util;

/**
 * Interface da lista encadeada.
 * @author Fernanda Castelo.
 *
 */

public interface ILista {

	/**
	 * Verifica se a lista está vazia.
	 * @return true - Caso a lista esteja vazia, false - Caso a lista não esteja vazia.
	 */
    public boolean estaVazia();

    /**
     * Retorna o tamanho da lista.
     * @return tamanho - Quantidade de elementos na lista.
     */
    public int obterTamanho();

    /**
     * Insere um objeto no inicio da lista.
     * @param o - Objeto a ser inserido na lista.
     */
    public void inserirInicio(Object o);

    /**
     * Insere um objeto no fim da lista.
     * @param o - Objeto a ser inserido na lista.
     */
    public void inserirFinal(Object o);

    /**
     * Remove um objeto do inicio da lista e retorna o objeto removido.
     * @return objeto - Objeto removido da lista.
     */
    public Object removerInicio();

    /**
     * Remove um objeto do fim da lista e retorna o objeto removido.
     * @return objeto - Objeto removido da lista.
     */
    public Object removerFinal();

    /**
     * Remove um objeto na posição indicada e retorna o objeto removido.
     * @param index - Posição a ser removida.
     * @return objeto - Objeto removido.
     */
    public Object remover(int index);
    
    /**
     * Recupera um objeto na lista na posição indicada.
     * @param index - Posição a ser recuperada.
     * @return objeto - Objeto recuperado.
     */
    public Object recuperar(int index);

    /**
     * Retorna um iterador da lista.
     * @return iterador - Iterador da lista.
     */
    public IIterador iterador();
}
