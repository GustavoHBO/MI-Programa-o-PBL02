package br.uefs.ecomp.sisNema.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.sisNema.model.Comprador;

public class ListaTest {

	Lista lista;

	@Before
	public void setUp() throws Exception {
		lista = new Lista();
	}

	//Verifica se os objetos estão sendo inseridos no inicio da lista.
	@Test
	public void inserirInicioTest() {
		Comprador comprar = new Comprador();

		comprar.setNome("Gustavo");
		lista.inserirInicio(comprar);
		assertEquals(comprar, lista.recuperar(0));

		comprar = new Comprador();
		comprar.setNome("Bustavo");
		lista.inserirInicio(comprar);
		assertEquals(comprar, lista.recuperar(0));

		comprar = new Comprador();
		comprar.setNome("Tustavo");
		lista.inserirInicio(comprar);
		assertEquals(comprar, lista.recuperar(0));

		comprar = new Comprador();
		comprar.setNome("Austavo");
		lista.inserirInicio(comprar);
		assertEquals(comprar, lista.recuperar(0));

		comprar = new Comprador();
		comprar.setNome("Qustavo");
		lista.inserirInicio(comprar);
		assertEquals(comprar, lista.recuperar(0));
	}
	
	//Verifica se retorna realmente o tamanho exato da lista.

	@Test
	public void obterTamanhoTest(){


		Comprador comprador = new Comprador();

		lista.inserirInicio(comprador);
		assertEquals(1, lista.obterTamanho());

		comprador = new Comprador();
		lista.inserirInicio(comprador);
		assertEquals(2, lista.obterTamanho());

		comprador = new Comprador();
		lista.inserirInicio(comprador);
		assertEquals(3, lista.obterTamanho());

		comprador = new Comprador();
		lista.inserirInicio(comprador);
		assertEquals(4, lista.obterTamanho());

		comprador = new Comprador();
		lista.inserirInicio(comprador);
		assertEquals(5, lista.obterTamanho());
	}

	//Verifico se o método recupera exatamente o objeto pedido.
	@Test
	public void recuperarTest(){

		Comprador comprador = new Comprador();

		comprador.setNome("Fafa");
		lista.inserirInicio(comprador);

		comprador = new Comprador();
		comprador.setNome("Fefe");
		lista.inserirInicio(comprador);

		comprador = new Comprador();
		comprador.setNome("Fifi");
		lista.inserirInicio(comprador);

		comprador = new Comprador();
		comprador.setNome("Fofo");
		lista.inserirInicio(comprador);

		comprador = new Comprador();
		comprador.setNome("Fufu");
		lista.inserirInicio(comprador);

		comprador = (Comprador) lista.recuperar(4);
		assertEquals("Fafa", comprador.getNome());

		comprador = (Comprador) lista.recuperar(3);
		assertEquals("Fefe", comprador.getNome());

		comprador = (Comprador) lista.recuperar(2);
		assertEquals("Fifi", comprador.getNome());

		comprador = (Comprador) lista.recuperar(1);
		assertEquals("Fofo", comprador.getNome());

		comprador = (Comprador) lista.recuperar(0);
		assertEquals("Fufu", comprador.getNome());
	}
	// Verifica se o método funciona corretamente.
	@Test
	public void estaVaziaTest(){

		Comprador comprador = new Comprador();
		comprador.setNome("Olavo");

		assertEquals(true, lista.estaVazia());

		lista.inserirInicio(comprador);

		assertFalse(lista.estaVazia());

		lista.removerInicio();

		assertTrue(lista.estaVazia());

		comprador = new Comprador();
		comprador.setNome("Olavo");

		lista.inserirInicio(comprador);

		comprador = new Comprador();
		comprador.setNome("Marco");

		lista.inserirInicio(comprador);

		comprador = new Comprador();
		comprador.setNome("Caco");

		lista.inserirInicio(comprador);

		assertFalse(lista.estaVazia());

		lista.removerInicio();
		assertFalse(lista.estaVazia());

		lista.removerInicio();
		assertFalse(lista.estaVazia());

		lista.removerInicio();
		assertTrue(lista.estaVazia());
	}
	//Verifica se o método merge, da lita funciona, concatenando as duas listas ordenando-as.
	@Test
	public void mergeTest(){

		Comprador comprador = new Comprador();
		Lista lista1 = new Lista();
		Lista lista2 = new Lista();
		Lista listaOrdenada = new Lista();

		/* Inserindo na lista1 */

		comprador.setNome("Anderson");
		lista1.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Ashley");
		lista1.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Barnney");
		lista1.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Bruna");
		lista1.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Marlley");
		lista1.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Ziraldo");
		lista1.inserirFinal(comprador);

		/* Inserindo na lista2 */

		comprador = new Comprador();
		comprador.setNome("Andre");
		lista2.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Anna");
		lista2.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Bilbo");
		lista2.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Carlinhos");
		lista2.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Terrys");
		lista2.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Zoye");
		lista2.inserirFinal(comprador);

		/* Criando a lista ordenada*/

		comprador = new Comprador();
		comprador.setNome("Anderson");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Andre");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Anna");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Ashley");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Barnney");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Bilbo");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Bruna");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Carlinhos");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Marlley");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Terrys");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Ziraldo");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Zoye");
		listaOrdenada.inserirFinal(comprador);

		/* Agora vamos comparar os resultados */

		IIterador it1, it2;
		Comprador comp1, comp2;
		lista = new Lista();
		lista.merge(lista, lista1, lista2);

		it1 = listaOrdenada.iterador();
		it2 = lista.iterador();

		while(it1.temProximo()){
			comp1 = (Comprador)it1.obterProximo();
			comp2 = (Comprador)it2.obterProximo();
			assertEquals(comp1.getNome(), comp2.getNome());
		}
	}

	//Testa a ordenação, crio uma lista com nomes aleatórios e crio uma ordenada e depois comparo o resultado.
	@Test
	public void mergeSortTest(){

		Comprador comprador = new Comprador();

		/* Preenchendo a lista */

		comprador.setNome("Anderson");
		lista.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Ashley");
		lista.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Barnney");
		lista.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Bruna");
		lista.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Marlley");
		lista.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Ziraldo");
		lista.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Andre");
		lista.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Anna");
		lista.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Bilbo");
		lista.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Carlinhos");
		lista.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Terrys");
		lista.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Zoye");
		lista.inserirFinal(comprador);

		lista.mergeSort(lista);

		/* Criando a lista ordenada*/

		Lista listaOrdenada = new Lista();

		comprador = new Comprador();
		comprador.setNome("Anderson");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Andre");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Anna");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Ashley");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Barnney");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Bilbo");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Bruna");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Carlinhos");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Marlley");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Terrys");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Ziraldo");
		listaOrdenada.inserirFinal(comprador);

		comprador = new Comprador();
		comprador.setNome("Zoye");
		listaOrdenada.inserirFinal(comprador);

		/* Ordenando a lista inicial */

		lista.mergeSort(listaOrdenada);

		/* Comparando os resultados encontrados */

		IIterador it1, it2;
		it1 = listaOrdenada.iterador();
		it2 = lista.iterador();
		Comprador comp1, comp2;

		while(it1.temProximo()){
			comp1 = (Comprador)it1.obterProximo();
			comp2 = (Comprador)it2.obterProximo();
			assertEquals(comp1.getNome(), comp2.getNome());
		}
	}
	//Verifica se a remoção funciona da forma esperada.
	@Test
	public void removerTest(){

		Comprador comprador = new Comprador();

		comprador.setNome("Fafa");
		lista.inserirInicio(comprador);

		comprador = new Comprador();
		comprador.setNome("Fefe");
		lista.inserirInicio(comprador);

		comprador = new Comprador();
		comprador.setNome("Fifi");
		lista.inserirInicio(comprador);

		comprador = new Comprador();
		comprador.setNome("Fofo");
		lista.inserirInicio(comprador);

		comprador = new Comprador();
		comprador.setNome("Fufu");
		lista.inserirInicio(comprador);

		assertEquals(5, lista.obterTamanho());
		comprador = (Comprador) lista.remover(4);
		assertEquals("Fafa", comprador.getNome());
		assertFalse(lista.estaVazia());

		assertEquals(4, lista.obterTamanho());
		comprador = (Comprador) lista.remover(3);
		assertEquals("Fefe", comprador.getNome());
		assertFalse(lista.estaVazia());

		assertEquals(3, lista.obterTamanho());
		comprador = (Comprador) lista.remover(2);
		assertEquals("Fifi", comprador.getNome());
		assertFalse(lista.estaVazia());

		assertEquals(2, lista.obterTamanho());
		comprador = (Comprador) lista.remover(1);
		assertEquals("Fofo", comprador.getNome());
		assertFalse(lista.estaVazia());

		assertEquals(1, lista.obterTamanho());
		comprador = (Comprador) lista.remover(0);
		assertEquals("Fufu", comprador.getNome());
		assertTrue(lista.estaVazia());
		assertEquals(0, lista.obterTamanho());

	}
}
