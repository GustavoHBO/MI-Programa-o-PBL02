package br.uefs.ecomp.sisNema.extras;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.sisNema.controller.AdministradorController;
import br.uefs.ecomp.sisNema.exceptions.CampoObrigatorioInexistenteException;
import br.uefs.ecomp.sisNema.exceptions.CinemaNaoEncontradoException;
import br.uefs.ecomp.sisNema.exceptions.CinemaNuloException;
import br.uefs.ecomp.sisNema.exceptions.HorarioNaoPermitidoException;
import br.uefs.ecomp.sisNema.exceptions.IntervaloMinimoInsuficienteException;
import br.uefs.ecomp.sisNema.exceptions.LimiteSalasExcedidoException;
import br.uefs.ecomp.sisNema.exceptions.SalaNaoEncontradaException;
import br.uefs.ecomp.sisNema.exceptions.SalaNulaException;
import br.uefs.ecomp.sisNema.exceptions.SessaoNulaException;
import br.uefs.ecomp.sisNema.model.Cinema;
import br.uefs.ecomp.sisNema.model.Sala;
import br.uefs.ecomp.sisNema.model.Sessao;
import br.uefs.ecomp.sisNema.util.CriarObjetos;

public class SessaoTest {

	AdministradorController controller;
	@Before
	public void setUp() throws Exception {
		AdministradorController.zerarSingleton();
		controller = AdministradorController.getInstance();
	}

	@Test
	public void cadastrarSessaoSucesso() {

		int id = 0;

		try {
			id = CriarObjetos.criarCinema(controller);
		} catch (CinemaNuloException e1) {
			fail();
		} catch (CampoObrigatorioInexistenteException e1) {
			fail();
		}
		Cinema cinema = null;
		try {
			cinema = controller.recuperarCinema(id);
		} catch (CinemaNaoEncontradoException e1) {
			fail();
		}
		assertNotNull(cinema);

		Sala sala = new Sala();
		sala.setNumeroSala(101);
		sala.setQtdCadeiras(147);

		try {
			controller.cadastrarSala(cinema, sala);
		} catch (CinemaNuloException e) {
			fail();
		} catch (SalaNulaException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (LimiteSalasExcedidoException e) {
			fail();
		}

		Sala salaR = null;

		try {
			salaR = controller.recuperarSala(cinema, 101);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		}
		assertNotNull(salaR);
		assertEquals(salaR.getNumeroSala(), 101);
		assertEquals(salaR.getQtdCadeiras(), 147);

		/* Cadastrando a primeira sessão */
		Sessao sessao = new Sessao();
		sessao.setHoraInicio(8);
		sessao.setHoraFim(10);

		try {
			controller.cadastrarSessao(cinema, salaR, sessao);
		} catch (CinemaNuloException e) {
			fail();
		} catch (SalaNulaException e) {
			fail();
		} catch (SessaoNulaException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		} catch (IntervaloMinimoInsuficienteException e) {
			fail();
		} catch (HorarioNaoPermitidoException e) {
			fail();
		}

		/* Cadastrando a segunda sessão */

		Sessao sessao2 = new Sessao();
		sessao2.setHoraInicio(12);
		sessao2.setHoraFim(14);

		try {
			controller.cadastrarSessao(cinema, salaR, sessao2);
		} catch (CinemaNuloException e) {
			fail();
		} catch (SalaNulaException e) {
			fail();
		} catch (SessaoNulaException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		} catch (IntervaloMinimoInsuficienteException e) {
			fail();
		} catch (HorarioNaoPermitidoException e) {
			fail();
		}
	}

	@Test
	public void cadastrarSessaoErro(){


		int id = 0;

		try {
			id = CriarObjetos.criarCinema(controller);
		} catch (CinemaNuloException e1) {
			fail();
		} catch (CampoObrigatorioInexistenteException e1) {
			fail();
		}
		Cinema cinema = null;
		try {
			cinema = controller.recuperarCinema(id);
		} catch (CinemaNaoEncontradoException e1) {
			fail();
		}
		assertNotNull(cinema);

		Sala sala = new Sala();
		sala.setNumeroSala(101);
		sala.setQtdCadeiras(147);

		try {
			controller.cadastrarSala(cinema, sala);
		} catch (CinemaNuloException e) {
			fail();
		} catch (SalaNulaException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (LimiteSalasExcedidoException e) {
			fail();
		}

		Sala salaR = null;

		try {
			salaR = controller.recuperarSala(cinema, 101);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		}
		assertNotNull(salaR);
		assertEquals(salaR.getNumeroSala(), 101);
		assertEquals(salaR.getQtdCadeiras(), 147);

		/* Cadastrando a primeira sessão */
		Sessao sessao = new Sessao();
		sessao.setHoraInicio(8);
		sessao.setHoraFim(10);

		try {
			controller.cadastrarSessao(cinema, salaR, sessao);
		} catch (CinemaNuloException e) {
			fail();
		} catch (SalaNulaException e) {
			fail();
		} catch (SessaoNulaException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		} catch (IntervaloMinimoInsuficienteException e) {
			fail();
		} catch (HorarioNaoPermitidoException e) {
			fail();
		}

		/* Cadastrando a segunda sessão */

		Sessao sessao2 = new Sessao();
		sessao2.setHoraInicio(10);
		sessao2.setHoraFim(12);

		try {
			controller.cadastrarSessao(cinema, salaR, sessao2);
			fail();
		} catch (CinemaNuloException e) {
			fail();
		} catch (SalaNulaException e) {
			fail();
		} catch (SessaoNulaException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		} catch (IntervaloMinimoInsuficienteException e) {
			assertTrue(true);
		} catch (HorarioNaoPermitidoException e) {
			fail();
		}

		/* Cadastrando a terceira sessão */

		Sessao sessao3 = new Sessao();
		sessao3.setHoraInicio(8);
		sessao3.setHoraFim(10);

		try {
			controller.cadastrarSessao(cinema, salaR, sessao3);
			fail();
		} catch (CinemaNuloException e) {
			fail();
		} catch (SalaNulaException e) {
			fail();
		} catch (SessaoNulaException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		} catch (IntervaloMinimoInsuficienteException e) {
			assertTrue(true);
		} catch (HorarioNaoPermitidoException e) {
			fail();
		}

		/* Cadastrando a quarta sessão */

		Sessao sessao4 = new Sessao();
		sessao4.setHoraInicio(9);
		sessao4.setHoraFim(11);

		try {
			controller.cadastrarSessao(cinema, salaR, sessao4);
			fail();
		} catch (CinemaNuloException e) {
			fail();
		} catch (SalaNulaException e) {
			fail();
		} catch (SessaoNulaException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		} catch (IntervaloMinimoInsuficienteException e) {
			assertTrue(true);
		} catch (HorarioNaoPermitidoException e) {
			fail();
		}

		/* Cadastrando a quinta sessão */

		Sessao sessao5 = new Sessao();
		sessao5.setHoraInicio(6);
		sessao5.setHoraFim(8);

		try {
			controller.cadastrarSessao(cinema, salaR, sessao5);
			fail();
		} catch (CinemaNuloException e) {
			fail();
		} catch (SalaNulaException e) {
			fail();
		} catch (SessaoNulaException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		} catch (IntervaloMinimoInsuficienteException e) {
			assertTrue(true);
		} catch (HorarioNaoPermitidoException e) {
			fail();
		}
	}

}
