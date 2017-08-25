package br.uefs.ecomp.sisNema.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.sisNema.exceptions.CampoObrigatorioInexistenteException;
import br.uefs.ecomp.sisNema.exceptions.CinemaNaoEncontradoException;
import br.uefs.ecomp.sisNema.exceptions.CinemaNuloException;
import br.uefs.ecomp.sisNema.exceptions.CompradorNaoEncontradoException;
import br.uefs.ecomp.sisNema.exceptions.CompradorNuloException;
import br.uefs.ecomp.sisNema.exceptions.FanHabilitadoInexistenteException;
import br.uefs.ecomp.sisNema.exceptions.HorarioNaoPermitidoException;
import br.uefs.ecomp.sisNema.exceptions.IntervaloMinimoInsuficienteException;
import br.uefs.ecomp.sisNema.exceptions.LimiteIngressosExcedidoException;
import br.uefs.ecomp.sisNema.exceptions.LimiteSalasExcedidoException;
import br.uefs.ecomp.sisNema.exceptions.SalaNaoEncontradaException;
import br.uefs.ecomp.sisNema.exceptions.SalaNulaException;
import br.uefs.ecomp.sisNema.exceptions.SessaoNaoEncontradaException;
import br.uefs.ecomp.sisNema.exceptions.SessaoNulaException;
import br.uefs.ecomp.sisNema.model.Cinema;
import br.uefs.ecomp.sisNema.model.Comprador;
import br.uefs.ecomp.sisNema.model.CompradorFan;
import br.uefs.ecomp.sisNema.model.Endereco;
import br.uefs.ecomp.sisNema.model.Sala;
import br.uefs.ecomp.sisNema.model.Sessao;
import br.uefs.ecomp.sisNema.util.CriarObjetos;
import br.uefs.ecomp.sisNema.util.Iterador;

public class DistribuicaoCamisasTest {

	private AdministradorController controllerAdministrador;
	private CompradorController controllerComprador;

	@Before
	public void setUp() throws Exception {
		AdministradorController.zerarSingleton();
		CompradorController.zerarSingleton();
		controllerAdministrador = AdministradorController.getInstance();
		controllerComprador = CompradorController.getInstance();
	}

	@Test
	public void testDistribuirCamisasSucesso() {
		Endereco end = CriarObjetos.criarEndereco();

		Cinema cine = new Cinema();
		cine.setNome("ORIENT Cineplace Boulevard Shopping");
		cine.setQtdSalas(9);
		cine.setEndereco(end);

		Sala sala = new Sala();
		sala.setNumeroSala(1);
		sala.setQtdCadeiras(300);

		Sessao sessao = new Sessao();
		sessao.setHoraInicio(15);
		sessao.setHoraFim(17);

		Comprador comprador = new Comprador();
		comprador.setNome("Fulano de Tal");
		comprador.setEndereco(end);
		comprador.setTelefone("(75)3489-1263");
		comprador.setEmail("fulano@tal.com.br");
		comprador.setDocumento(1234);

		CompradorFan comprador2 = new CompradorFan();
		comprador2.setNome("Fulano de Tal 2");
		comprador2.setEndereco(end);
		comprador2.setTelefone("(75)3489-1263");
		comprador2.setEmail("fulano2@tal.com.br");
		comprador2.setDocumento(1236);
		comprador2.setRegistro(2098);

		CompradorFan comprador3 = new CompradorFan();
		comprador3.setNome("Fulano de Tal 3");
		comprador3.setEndereco(end);
		comprador3.setTelefone("(75)3489-1263");
		comprador3.setEmail("fulano3@tal.com.br");
		comprador3.setDocumento(1238);
		comprador3.setRegistro(2099);

		CompradorFan comprador4 = new CompradorFan();//Criei mais um comprador, pois ele distribui 4 camisas e só registra 2.
		comprador4.setNome("Fulano de Tal 4");
		comprador4.setEndereco(end);
		comprador4.setTelefone("(75)3489-2893");
		comprador4.setEmail("fulano4@tal.com.br");
		comprador4.setDocumento(4433);
		comprador4.setRegistro(2017);

		CompradorFan comprador5 = new CompradorFan();// Cria o outro comprador para comprar o ingresso e ir para a fila de fans habilitados.
		comprador5.setNome("Fulano de Tal 5");
		comprador5.setEndereco(end);
		comprador5.setTelefone("(75)3434-1823");
		comprador5.setEmail("fulano5@tal.com.br");
		comprador5.setDocumento(2315);
		comprador5.setRegistro(2193);

		try {
			cine = controllerAdministrador.cadastrarCinema(cine);
		} catch (CinemaNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}

		try {
			controllerAdministrador.cadastrarSala(cine,sala);
		} catch (SalaNulaException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (CinemaNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (LimiteSalasExcedidoException e) {
			fail();
		}

		try {
			controllerAdministrador.cadastrarSessao(cine,sala,sessao);
		} catch (CinemaNuloException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SessaoNulaException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (IntervaloMinimoInsuficienteException e) {
			fail();
		} catch (SalaNulaException e) {
			fail();
		} catch (HorarioNaoPermitidoException e) {// Não tratava esse erro.
			fail();
		}

		try {
			controllerAdministrador.cadastrarComprador(comprador);
		} catch (CompradorNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}

		try {
			controllerAdministrador.cadastrarComprador(comprador2);
		} catch (CompradorNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}

		try {
			controllerAdministrador.cadastrarComprador(comprador3);
		} catch (CompradorNuloException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}

		try {
			controllerAdministrador.cadastrarComprador(comprador4);// Cadastrando o comprador criado.
		} catch (CompradorNuloException e1) {
			fail();
		} catch (CampoObrigatorioInexistenteException e1) {
			fail();
		}
		
		try {
			controllerAdministrador.cadastrarComprador(comprador5);// Cadastrando o comprador criado.
		} catch (CompradorNuloException e1) {
			fail();
		} catch (CampoObrigatorioInexistenteException e1) {
			fail();
		}


		int qtdIngressos = 3;

		try {
			controllerComprador.comprarIngresso(comprador.getDocumento(),cine.getId(),1,15,qtdIngressos,60.00);
		} catch (CompradorNaoEncontradoException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		} catch (SessaoNaoEncontradaException e) {
			fail();
		} catch (LimiteIngressosExcedidoException e) {
			fail();
		}

		try {
			controllerComprador.comprarIngresso(comprador2.getDocumento(),cine.getId(),1,15,qtdIngressos,60.00);
		} catch (CompradorNaoEncontradoException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		} catch (SessaoNaoEncontradaException e) {
			fail();
		} catch (LimiteIngressosExcedidoException e) {
			fail();
		}

		try {
			controllerComprador.comprarIngresso(comprador3.getDocumento(),cine.getId(),1,15,qtdIngressos, 60.00);
		} catch (CompradorNaoEncontradoException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		} catch (SessaoNaoEncontradaException e) {
			fail();
		} catch (LimiteIngressosExcedidoException e) {
			fail();
		}
		
		try {
			controllerComprador.comprarIngresso(comprador4.getDocumento(),cine.getId(),1,15,qtdIngressos, 60.00);
		} catch (CompradorNaoEncontradoException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		} catch (SessaoNaoEncontradaException e) {
			fail();
		} catch (LimiteIngressosExcedidoException e) {
			fail();
		}
		
		try {
			controllerComprador.comprarIngresso(comprador5.getDocumento(),cine.getId(),1,15,qtdIngressos, 60.00);
		} catch (CompradorNaoEncontradoException e) {
			fail();
		} catch (CinemaNaoEncontradoException e) {
			fail();
		} catch (SalaNaoEncontradaException e) {
			fail();
		} catch (SessaoNaoEncontradaException e) {
			fail();
		} catch (LimiteIngressosExcedidoException e) {
			fail();
		}

		assertEquals(4,controllerAdministrador.recuperarFansHabilitados().obterTamanho());// Alterei de 2 para 4 pois é a quantidade de fans habilitados cadastrados.

		Iterador i = controllerAdministrador.recuperarFansHabilitados().iterador();

		assertEquals(true,i.temProximo());
		CompradorFan compradorTeste = (CompradorFan)i.obterProximo();
		assertEquals(2098,compradorTeste.getRegistro());

		assertEquals(true,i.temProximo());
		compradorTeste = (CompradorFan)i.obterProximo();
		assertEquals(2099,compradorTeste.getRegistro());

		assertEquals(true,i.temProximo());// Verifico se o iterador ainda possui fans
		compradorTeste = (CompradorFan)i.obterProximo();// Recupero o compradorFan.
		assertEquals(2017,compradorTeste.getRegistro());// Verifico se o comprador recuperado é o mesmo que o esperado.

		assertEquals(true,i.temProximo());
		compradorTeste = (CompradorFan)i.obterProximo();
		assertEquals(2193,compradorTeste.getRegistro());

		assertEquals(false,i.temProximo());

		try {
			controllerAdministrador.distribuirCamisas(1);
		} catch (FanHabilitadoInexistenteException e) {// Não tratava essa exceção.
			fail();
		}

		i = controllerAdministrador.recuperarFansHabilitados().iterador();

		assertEquals(true,i.temProximo());
		compradorTeste = (CompradorFan)i.obterProximo();
		assertEquals(2099,compradorTeste.getRegistro());

		assertEquals(true,i.temProximo());
		compradorTeste = (CompradorFan)i.obterProximo();//Verifica se a camisa distribuída foi para o primeiro fan cadastrado.
		assertEquals(2017,compradorTeste.getRegistro());

		assertEquals(true,i.temProximo());
		compradorTeste = (CompradorFan)i.obterProximo();//Verifica se a camisa distribuída foi para o primeiro fan cadastrado.
		assertEquals(2193,compradorTeste.getRegistro());

		assertEquals(false,i.temProximo());

		try {
			controllerAdministrador.distribuirCamisas(3);
		} catch (FanHabilitadoInexistenteException e) {// Não tratava essa exceção
			fail();
		}

		i = controllerAdministrador.recuperarFansHabilitados().iterador();

		assertEquals(false,i.temProximo());
	}
}