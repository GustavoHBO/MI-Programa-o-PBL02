package br.uefs.ecomp.sisNema.extras;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.sisNema.controller.AdministradorController;
import br.uefs.ecomp.sisNema.exceptions.CampoObrigatorioInexistenteException;
import br.uefs.ecomp.sisNema.exceptions.CinemaNaoEncontradoException;
import br.uefs.ecomp.sisNema.exceptions.CinemaNuloException;
import br.uefs.ecomp.sisNema.model.Cinema;
import br.uefs.ecomp.sisNema.model.Endereco;

public class CadastrarCinemaSucessoTest {


	AdministradorController controllerAdministrador;
	@Before
	public void setUp() throws Exception {
		
		AdministradorController.zerarSingleton();
		controllerAdministrador = AdministradorController.getInstance();
	}

	@Test
	public void cadastrarCinemaSucesso() {

		Endereco end = criarEndereco();

		Cinema cinema = new Cinema();
		cinema.setEndereco(end);
		cinema.setId(1);
		cinema.setNome("Cine Place");
		cinema.setQtdSalas(20);

		try {
			controllerAdministrador.cadastrarCinema(cinema);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}

		try {
			cinema = controllerAdministrador.recuperarCinema(1);
		} catch (CinemaNaoEncontradoException e) {
			fail();
		}
		assertNotNull(cinema);

		cinema = new Cinema();
		cinema.setEndereco(end);
		cinema.setId(2);
		cinema.setNome("Cine Place 2");
		cinema.setQtdSalas(30);

		try {
			controllerAdministrador.cadastrarCinema(cinema);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}

		try {
			cinema = controllerAdministrador.recuperarCinema(2);
		} catch (CinemaNaoEncontradoException e) {
			fail();
		}
		assertNotNull(cinema);

	}

	@Test
	public void cadastrarCinemaError(){

		Endereco end = criarEndereco();

		Cinema cinema = new Cinema();
		cinema.setEndereco(end);
		cinema.setId(1);
		cinema.setNome("Cine Place");
		cinema.setQtdSalas(0);

		try {
			controllerAdministrador.cadastrarCinema(cinema);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		}

		try {
			cinema = controllerAdministrador.recuperarCinema(1);
		} catch (CinemaNaoEncontradoException e) {
			assertTrue(true);
		}
		assertNotNull(cinema);

		cinema.setEndereco(end);
		cinema.setId(1);
		cinema.setNome("       ");
		cinema.setQtdSalas(12);

		try {
			controllerAdministrador.cadastrarCinema(cinema);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		}
		cinema = null;
		try {
			cinema = controllerAdministrador.recuperarCinema(12);
		} catch (CinemaNaoEncontradoException e) {
			assertTrue(true);
		}
		assertNull(cinema);

		cinema = new Cinema();
		cinema.setEndereco(end);
		cinema.setId(0);
		cinema.setNome("Cine Place");
		cinema.setQtdSalas(12);

		try {
			controllerAdministrador.cadastrarCinema(cinema);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		}

		try {
			cinema = controllerAdministrador.recuperarCinema(0);
		} catch (CinemaNaoEncontradoException e) {
			assertTrue(true);
		}
		assertNotNull(cinema);

		cinema.setEndereco(null);
		cinema.setId(13);
		cinema.setNome("Cine Place");
		cinema.setQtdSalas(12);

		try {
			controllerAdministrador.cadastrarCinema(cinema);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		}

		try {
			cinema = controllerAdministrador.recuperarCinema(13);
		} catch (CinemaNaoEncontradoException e) {
			assertTrue(true);
		}
		assertNotNull(cinema);
		
		cinema = null;
		try {
			controllerAdministrador.cadastrarCinema(cinema);
		} catch (CinemaNuloException e) {
			assertTrue(true);
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}

	}

	@Test
	public void alterarCinemaSucesso(){

		Endereco end = criarEndereco();
		Cinema cinema = new Cinema();
		cinema.setEndereco(end);
		cinema.setId(1);
		cinema.setNome("MI Cine Place");
		cinema.setQtdSalas(32);

		try {
			controllerAdministrador.cadastrarCinema(cinema);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}

		cinema = new Cinema();
		cinema.setId(1);
		cinema.setEndereco(end);
		cinema.setNome("MI Cine Place 2");
		cinema.setQtdSalas(30);

		try {
			controllerAdministrador.alterarCinema(cinema);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}

		Cinema cine = null;
		try {
			cine = controllerAdministrador.recuperarCinema(1);
		} catch (CinemaNaoEncontradoException e) {
			fail();
		}
		assertNotNull(cine);

		cinema.setEndereco(end);
		cinema.setId(2);
		cinema.setNome("MI Cine Place 2");
		cinema.setQtdSalas(32);

		try {
			controllerAdministrador.cadastrarCinema(cinema);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}

		cinema = new Cinema();
		cinema.setId(2);
		cinema.setEndereco(end);
		cinema.setNome("MI Cine Place 3");
		cinema.setQtdSalas(50);

		try {
			controllerAdministrador.alterarCinema(cinema);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}

		cine = null;
		try {
			cine = controllerAdministrador.recuperarCinema(1);
		} catch (CinemaNaoEncontradoException e) {
			fail();
		}
		assertNotNull(cine);
	}

	@Test
	public void alterarCinemaErro(){

		Endereco end = criarEndereco();

		Cinema cinema = new Cinema();
		cinema.setEndereco(end);
		cinema.setId(1);
		cinema.setNome("MI Programacao Cine");
		cinema.setQtdSalas(100);

		try {
			controllerAdministrador.cadastrarCinema(cinema);
		} catch (CinemaNuloException | CampoObrigatorioInexistenteException e) {
			fail();
		}

		Cinema cinema2 = null;

		try {
			cinema2 = controllerAdministrador.recuperarCinema(1);
		} catch (CinemaNaoEncontradoException e) {
			fail();
		}

		assertNotNull(cinema2);

		cinema2.setNome("    ");
		try {
			controllerAdministrador.alterarCinema(cinema2);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		}

		cinema2 = null;

		try {
			cinema2 = controllerAdministrador.recuperarCinema(1);
		} catch (CinemaNaoEncontradoException e) {
			fail();
		}

		assertNotNull(cinema2);

		cinema2.setQtdSalas(0);
		try {
			controllerAdministrador.alterarCinema(cinema2);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		}
		
		cinema2 = null;

		try {
			cinema2 = controllerAdministrador.recuperarCinema(1);
		} catch (CinemaNaoEncontradoException e) {
			fail();
		}

		assertNotNull(cinema2);

		cinema2.setEndereco(null);
		try {
			controllerAdministrador.alterarCinema(cinema2);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		}
		
		cinema2 = null;
		try {
			controllerAdministrador.alterarCinema(cinema2);
		} catch (CinemaNuloException e) {
			assertTrue(true);
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		cinema2 = new Cinema();
		try {
			controllerAdministrador.alterarCinema(cinema2);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			assertTrue(true);
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}

	}

	/**
	 * Teste que verifica se o método recuperar cinema está funcionando de acordo com o que é esperado.
	 */
	@Test
	public void recuperarCinemaSucesso(){
		
		Endereco end = criarEndereco();

		Cinema cinema = new Cinema();
		cinema.setEndereco(end);
		cinema.setNome("MI Programacao Cine");
		cinema.setQtdSalas(100);
		
		try {
			controllerAdministrador.cadastrarCinema(cinema);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		Cinema cinema2 = null;
		
		try {
			cinema2 = controllerAdministrador.recuperarCinema(cinema.getId());
		} catch (CinemaNaoEncontradoException e) {
			fail();
		}
		assertNotNull(cinema2);
		assertEquals(end, cinema2.getEndereco());
		assertEquals(1, cinema2.getId());
		assertEquals("MI Programacao Cine", cinema2.getNome());
		assertEquals(100, cinema2.getQtdSalas());
		
		
		Cinema cinema3 = new Cinema();
		cinema3.setEndereco(end);
		cinema3.setNome("MI Programacao Cine 2");
		cinema3.setQtdSalas(10);
		
		try {
			controllerAdministrador.cadastrarCinema(cinema3);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		Cinema cinema4 = null;
		
		try {
			cinema4 = controllerAdministrador.recuperarCinema(2);// Id era 4, mas só cria dois cinemas.
		} catch (CinemaNaoEncontradoException e) {
			fail();
		}
		assertNotNull(cinema4);
		assertEquals(end, cinema4.getEndereco());
		assertEquals(2, cinema4.getId());// Queria que o id do cinema fosse dois, mas só cria dois cinemas.
		assertEquals("MI Programacao Cine 2", cinema4.getNome());
		assertEquals(10, cinema4.getQtdSalas());
	}

	/**
	 * Cria o endereço a ser utilizado nos testes.
	 * @return end - Endereço.
	 */
	public Endereco criarEndereco(){

		Endereco end = new Endereco();
		end.setBairro("Brasilia");
		end.setCep("44010-610");
		end.setCidade("Feira de Santana");
		end.setEstado("Bahia");
		end.setNumero(14);
		end.setPais("Brasil");
		end.setRua("Tranqueira");
		return end;
	}

}
