package br.uefs.ecomp.sisNema.util;


public interface IFila {
  
    public boolean estaVazia();// Verifica se a fila esta vazia.

    public int obterTamanho();// Retorna o tamanho da fila.

    public void inserirFinal(Object o);// Insere o objeto recebido no final da fila.

    public Object removerInicio();// Remove o primeiro objeto da fila e o retorna.

    public Object recuperarInicio();// Retorna o primeiro objeto da fila.
}
