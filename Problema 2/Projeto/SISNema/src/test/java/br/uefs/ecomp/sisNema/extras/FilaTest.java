package br.uefs.ecomp.sisNema.extras;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.sisNema.model.Comprador;
import br.uefs.ecomp.sisNema.model.Endereco;
import br.uefs.ecomp.sisNema.util.Fila;

public class FilaTest {

	Fila fila;
	@Before
	public void setUp() throws Exception {
		fila = new Fila();
	}

	@Test
	public void filaRemoverSucesso() {

		Endereco end = criarEndereco();
		
		Comprador comprador1 = new Comprador();
		comprador1.setDocumento(1234);
		comprador1.setEmail("fulano1@test.uefs");
		comprador1.setEndereco(end);
		comprador1.setNome("Fulano1 que mora ali");
		comprador1.setTelefone("(075) 98412-1015");
		
		Comprador comprador2 = new Comprador();
		comprador2.setDocumento(4321);
		comprador2.setEmail("fulano2@test.uefs");
		comprador2.setEndereco(end);
		comprador2.setNome("Fulano2 que mora ali");
		comprador2.setTelefone("(075) 99800-1213");
		
		fila.inserirFinal(comprador1);
		fila.inserirFinal(comprador2);
		
		Comprador compradorTest;
		
		compradorTest = (Comprador)fila.removerInicio();
		assertEquals(comprador1.getDocumento(), compradorTest.getDocumento());
		assertEquals(comprador1.getTelefone(), compradorTest.getTelefone());
		assertEquals(comprador1.getEmail(), compradorTest.getEmail());
		assertEquals(comprador1.getEndereco(), compradorTest.getEndereco());
		assertEquals(comprador1.getNome(), compradorTest.getNome());
		
		compradorTest = (Comprador)fila.removerInicio();
		assertEquals(comprador2.getDocumento(), compradorTest.getDocumento());
		assertEquals(comprador2.getTelefone(), compradorTest.getTelefone());
		assertEquals(comprador2.getEmail(), compradorTest.getEmail());
		assertEquals(comprador2.getEndereco(), compradorTest.getEndereco());
		assertEquals(comprador2.getNome(), compradorTest.getNome());
		
	}
	
	@Test
	public void filaRecuperarInicioSucesso(){
		
Endereco end = criarEndereco();
		
		Comprador comprador1 = new Comprador();
		comprador1.setDocumento(1234);
		comprador1.setEmail("fulano1@test.uefs");
		comprador1.setEndereco(end);
		comprador1.setNome("Fulano1 que mora ali");
		comprador1.setTelefone("(075) 98412-1015");
		
		Comprador comprador2 = new Comprador();
		comprador2.setDocumento(4321);
		comprador2.setEmail("fulano2@test.uefs");
		comprador2.setEndereco(end);
		comprador2.setNome("Fulano2 que mora ali");
		comprador2.setTelefone("(075) 99800-1213");
		
		assertTrue(fila.estaVazia());
		fila.inserirFinal(comprador1);
		fila.inserirFinal(comprador2);
		
		Comprador compradorTest;
		
		compradorTest = (Comprador)fila.recuperarInicio();
		assertEquals(comprador1.getDocumento(), compradorTest.getDocumento());
		assertEquals(comprador1.getTelefone(), compradorTest.getTelefone());
		assertEquals(comprador1.getEmail(), compradorTest.getEmail());
		assertEquals(comprador1.getEndereco(), compradorTest.getEndereco());
		assertEquals(comprador1.getNome(), compradorTest.getNome());
		
		assertEquals(2, fila.obterTamanho());
		fila.removerInicio();
		
		compradorTest = (Comprador)fila.recuperarInicio();
		assertEquals(comprador2.getDocumento(), compradorTest.getDocumento());
		assertEquals(comprador2.getTelefone(), compradorTest.getTelefone());
		assertEquals(comprador2.getEmail(), compradorTest.getEmail());
		assertEquals(comprador2.getEndereco(), compradorTest.getEndereco());
		assertEquals(comprador2.getNome(), compradorTest.getNome());
		
	}
	
	
	
	private Endereco criarEndereco(){
		
		Endereco end = new Endereco();
		end.setBairro("Caraibas");
		end.setCep("40013-15");
		end.setCidade("Anguera");
		end.setComplemento("Px Serra Preta");
		end.setEstado("Bahia");
		end.setNumero(200);
		end.setPais("Brasil");
		end.setRua("Sem nome");
		return end;
	}

}
