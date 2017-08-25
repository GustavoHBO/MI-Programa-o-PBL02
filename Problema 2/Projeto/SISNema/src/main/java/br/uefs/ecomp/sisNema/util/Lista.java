package br.uefs.ecomp.sisNema.util;

public class Lista implements ILista {

	private No primeiro = null; 	// Referência para o primeiro objeto da lista.
	private No ultimo = null;   	// Referência para o ultimo objeto da lista.
	private int qNo = 0;		 	// Quantidade de nó's da lista.

	/**
	 * Construtor padrão da lista.
	 */
	public Lista(){
		
	}
	
	/**
	 * Verifica se a lista está vazia, retorna true caso vazia ou false caso não.
	 * @return True - Caso vazia, False - Caso não esteja vazia.
	 */
	@Override
	public boolean estaVazia() {
		return primeiro == null;// Verifica se o primeiro nó existe.
	}

	/**
	 * Retorna o tamanho da lista
	 * @return qNo - Quantidade de nó's da lista.
	 */
	@Override
	public int obterTamanho() {
		return qNo;// Retorna o tamanho da lista.
	}

	/**
	 * Insere um objeto no inicio da lista.
	 * @param o - Objeto a ser inserido no nó.
	 */
	@Override
	public void inserirInicio(Object o) {
		No novo = new No(o);// Cria o novo nó e adiciona o objeto.
		if(estaVazia()){// Verifica se a lista está vazia.
			primeiro = novo;// Caso esteja vazia faz o primeiro e ultimo nó referenciar o novo nó.
			ultimo = primeiro;
		}
		else{//Caso não esteja vazia.
			novo.setNext(primeiro);// O novo nó referencia o primeiro.
			primeiro.setAfter(novo);// O primeiro referencia o anterior para o novo nó.
			primeiro = novo;// O primeiro referencia o novo nó.
		}
		qNo++;// Incrementa a quantidade de nó's da lista.
	}

	/**
	 * Insere um objeto no final da lista.
	 * @param o - Objeto a ser inserido no nó.
	 */
	@Override
	public void inserirFinal(Object o) {

		if(estaVazia()){// Verifica se a lista está vazia.
			inserirInicio(o);// Caso esteja insere no inicio.
		}
		else{// Caso a lista não esteja vazia
			No novo = new No(o);// Cria um nó.
			ultimo.setNext(novo);// Faz o ultimo nó referenciar o novo.
			novo.setAfter(ultimo);// Faz o novo referenciar o ultimo.
			ultimo = novo;// Ultimo referencia o novo.
			qNo++;// Incrementa a quantidade de nó's.
		}
	}
	/**
	 * Remove um objeto do inicio da lista e retorna-o.
	 * @return null - Caso a lista esteja vazia, objRemover - Retorna o objeto removido.
	 */
	@Override
	public Object removerInicio() {// Remove um nó no inicio da lista.

		if(estaVazia()){// Verifica se esta vazia.
			return null;// Retorna null caso a lista esteja vazia.
		}
		else if(qNo == 1){// Caso exista apenas um nó na lista
			Object objRemover = primeiro.getObjeto();// Recebe o objeto a ser removido.
			primeiro = ultimo = null;// Faz a lista ficar vazia.
			qNo--;
			return objRemover;// Retorna o objeto removido.
		}
		else{// Caso não esteja vazia
			Object objRemover = primeiro.getObjeto();// Recebe o objeto a ser removido.
			No remover = primeiro;// Cria um nó para poder retirar a referencia do next e torna-la nulo.
			primeiro = primeiro.getNext();// O primeiro nó referencia o próximo.
			primeiro.setAfter(null);// A referencia anterior do nó é nula, ja que vai ser removido o nó anterior.
			remover.setNext(null);// Retira a referencia do próximo.
			qNo--;// Decrementa a quantidade de nó's existentes.
			return objRemover;// Retorna o objeto removido.
		}
	}

	/**
	 * Remove um objeto no fim da lista e retorna-o.
	 * @return null - Caso a lista esteja vazia, Remover - Retorna o objeto removido.
	 */
	@Override
	public Object removerFinal() {

		if(estaVazia()){// Verifica se a lista esta vazia.
			return null;// Caso esteja retorna null.
		}
		else if (qNo == 1){// Verifica se existe apenas um nó na lista.
			return removerInicio();// Remove o nó do inicio, para retirar a referencia do primeiro e ultimo.
		}
		else{// Caso exista mais de um nó.
			No remover = ultimo;// Cria um nó para retirar a referencia do next desse nó.
			ultimo = ultimo.getAfter();// Ultimo se torna o anterior a ele.
			ultimo.setNext(null);// Apaga a referencia para o nó a ser removido.
			remover.setAfter(null);// Retira a referencia para o ultimo.
			qNo--;//Decrementa a quantidade de nó's da lista.
			return remover.getObjeto();// Retorna o objeto removido.
		}
	}

	/**
	 * Remove o objeto na posição recebida em relação a lista e retorna-o.
	 * @param index - Posição a ser removida.
	 * @return null - Caso a lista esteja vazia, Remover - Retorna o objeto removido.
	 */
	@Override
	public Object remover(int index) {// Remove um nó na posição indicada.
		if(estaVazia() || index > qNo - 1 || index < 0){// Verifica se a lista está vazia ou se a posição existe.
			return null;// Retorna nulo.
		}
		else if(index == 0){// Verifica se a posição a remover é no inicio.
			return removerInicio();// Retorna o objeto removido.
		}
		else if(index == qNo - 1){// Verifica se a posição a remover é no final.
			return removerFinal();// Retorna o objeto removido.
		}
		else{// Caso a posição exista, a lista não esteja vazia e a posição não seja no inicio nem no fim.
			No remover = primeiro.getNext();// Cria um nó para percorrer a lista e encontrar a posição a ser removida.
			No anterior, proximo;// Cria nó's para trocar as referencias.
			for(int cont = 1; cont != index; cont++){// For para percorrer a lista e encontrar o nó a ser removido.
				remover = remover.getNext();// remover percorre a lista.
			}
			anterior = remover.getAfter();// anterior referencia o nó anterior do nó a ser removido.
			proximo = remover.getNext();// Próximo referencia o próximo nó do nó a ser removido.
			anterior.setNext(proximo);// anterior referencia o próximo.
			proximo.setAfter(anterior);// Próximo referencia o anterior.
			qNo--;//Decrementa a quantidade de nó´s.
			return remover.getObjeto();// Retorna o objeto do nó removido.
		}
	}

	/**
	 * Retorna o objeto na posição recebida.
	 * @param index - Posição na lista.
	 * @return objeto - Objeto encontrado na posição.
	 */
	@Override
	public Object recuperar(int index) {
		if(estaVazia() || index < 0 || index > qNo - 1){// Verifica se a lista está vazia ou se a posição existe.
			return null;// Retorna null.
		}
		else{
			No aux = primeiro;// Cria um nó para percorrer a lista.
			for(int cont = 0; cont != index; cont++){// Percorre a lista.
				aux = aux.getNext();
			}
			return aux.getObjeto();// Retorna o objeto na posição recebida.
		}
	}

	/**
	 * Retorna o iterador da lista.
	 * @return iterador - Iterador da lista.
	 */
	@Override
	public Iterador iterador() {// Cria um iterador da lista
		Iterador iterador = new Iterador(primeiro);// Cria um iterador passando o inicio da lista.
		return iterador;// Retorna o iterador criado.
	}

	/**
	 * Método de ordenação mergeSort, ordena em ordem alfabética.
	 * @param lista - Lista a ser ordenada.
	 */

	public void mergeSort(Lista lista){

		if(lista.obterTamanho() == 1){
			return;
		}

		Lista lista1 = new Lista();

		for(int i = 0; i < lista.obterTamanho()/2; i++){
			lista1.inserirFinal(lista.removerInicio());
		}

		mergeSort(lista1);

		Lista lista2 = new Lista();
		

		while(!lista.estaVazia()){
			lista2.inserirFinal(lista.removerInicio());
		}
		mergeSort(lista2);
		
		merge(lista, lista1, lista2);
	}
	
	/**
	 * Método responsável por comparar duas listas e uni-las em uma só,
	 * ordenada alfabéticamente.
	 * @param lista - Lista Principal.
	 * @param lista1 - Lista 1.
	 * @param lista2 - Lista 2.
	 */
	public void merge(Lista lista, Lista lista1, Lista lista2){
		
		Comparable comp1, comp2;

		Lista listaf = new Lista();
		
		while(!lista1.estaVazia() || !lista2.estaVazia()){

			if(!lista1.estaVazia() && !lista2.estaVazia()){
				comp1 = (Comparable) lista1.recuperar(0);
				comp2 = (Comparable) lista2.recuperar(0);
				if(comp1 != null){
					if(comp1.compare(comp2) < 0){
						listaf.inserirFinal(lista1.removerInicio());
					}
					else{
						listaf.inserirFinal(lista2.removerInicio());
					}
				}
			}
			else if(lista1.estaVazia()){
				listaf.inserirFinal(lista2.removerInicio());
			}
			else if(lista2.estaVazia()){
				listaf.inserirFinal(lista1.removerInicio());
			}
		}

		while(!listaf.estaVazia()){
			lista.inserirFinal(listaf.removerInicio());
		}
	}
}
