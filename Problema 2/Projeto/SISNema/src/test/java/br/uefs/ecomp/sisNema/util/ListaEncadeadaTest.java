package br.uefs.ecomp.sisNema.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.sisNema.model.Comprador;

public class ListaEncadeadaTest {

	Lista lista;
	@Before
	public void setUp() throws Exception {
		lista = new Lista();
	}

	@Test
	public void removerIndexTest() {
		
		Comprador comprador = new Comprador();
		lista.inserirInicio(comprador);
		lista.inserirInicio(comprador);
		lista.inserirInicio(comprador);
		lista.inserirInicio(comprador);
		lista.inserirInicio(comprador);
		
		assertFalse(lista.estaVazia());
		
		lista.remover(3);
		lista.remover(2);
		lista.remover(2);
		lista.remover(0);
		lista.remover(0);
		
		assertTrue(lista.estaVazia());
	}

}
