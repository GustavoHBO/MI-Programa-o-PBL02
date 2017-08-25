package br.uefs.ecomp.sisNema.controller;

import br.uefs.ecomp.sisNema.exceptions.CinemaNaoEncontradoException;
import br.uefs.ecomp.sisNema.exceptions.CompradorNaoEncontradoException;
import br.uefs.ecomp.sisNema.exceptions.CompradorNuloException;
import br.uefs.ecomp.sisNema.exceptions.LimiteIngressosExcedidoException;
import br.uefs.ecomp.sisNema.exceptions.SalaNaoEncontradaException;
import br.uefs.ecomp.sisNema.exceptions.SessaoNaoEncontradaException;
import br.uefs.ecomp.sisNema.model.Cinema;
import br.uefs.ecomp.sisNema.model.Comprador;
import br.uefs.ecomp.sisNema.model.CompradorFan;
import br.uefs.ecomp.sisNema.model.Ingresso;
import br.uefs.ecomp.sisNema.model.Sala;
import br.uefs.ecomp.sisNema.model.Sessao;
import br.uefs.ecomp.sisNema.util.IIterador;
import br.uefs.ecomp.sisNema.util.Lista;

/**
 * Classe controller comprador.
 * @author Gustavo Henrique.
 * @since 21 de fevereiro de 2015.
 *
 */
public class CompradorController {

	private AdministradorController administradorController = AdministradorController.getInstance();
	private static CompradorController instance;
	
	private CompradorController (){
		
	}
	
	/**
	 * Retorna uma única instância da classe.
	 * @return instance - Única instância da classe.
	 */
	public static CompradorController getInstance(){
		
		if(instance == null){
			instance = new CompradorController();
		}
		return instance;
	}
	
	/**
	 * Método responsável por zerar a instância da classe.
	 */
	public static void zerarSingleton(){
		instance = null;
	}
	
	/**
	 * Método responsável por realizar a compra do ingresso.
	 * @param documento - Documento do comprador.
	 * @param id - Id do cinema.
	 * @param idSala - Id da sala.
	 * @param horarioSessao - Horário da sessão.
	 * @param qtdIngressos - Quantidade de ingressos a serem comprados.
	 * @param preco - Preço pago.
	 * @throws CinemaNaoEncontradoException - Caso o cinema não exista.
	 * @throws SalaNaoEncontradaException - Caso a sala não exista.
	 * @throws SessaoNaoEncontradaException - Caso a sessão não exista.
	 * @throws LimiteIngressosExcedidoException - Caso o limite de ingressos tenha sido atingido.
	 * @throws CompradorNaoEncontradoException - Caso o comprador não tenha sido encontrado.
	 */
	public void comprarIngresso(int documento, int id, int idSala, int horarioSessao, int qtdIngressos, double preco) throws CinemaNaoEncontradoException, SalaNaoEncontradaException, SessaoNaoEncontradaException, LimiteIngressosExcedidoException, CompradorNaoEncontradoException{
		
		Cinema cinema = administradorController.recuperarCinema(id);// Possível exceção CinemaNaoEncontradoException();
		IIterador it = cinema.getSalas().iterador(), it2;
		Sala sala;
		Sessao sessao;
		Ingresso ingresso;
		Comprador comprador = recuperarComprador(documento);// Possível exceção CompradorNaoEncontradoException();
		
		while (it.temProximo()){// Verifica se existem salas cadastradas.
			sala = (Sala) it.obterProximo();
			if(sala.getNumeroSala() == idSala){// Verifica se das salas cadastradas existe alguma com o id recebido.
				it2 = sala.getListaSessoes().iterador();
				while(it2.temProximo()){// Verifica se tem sessões cadastradas na sala escolhida.
					sessao = (Sessao)it2.obterProximo();
					if(sessao.getHoraInicio() == horarioSessao){// Verifica se existe a sessão no horário escolhido.
						if(sessao.getQtdIngressosVendidos() + qtdIngressos <= sala.getQtdCadeiras()){// Verifica se na sessão escolhida existem ingressos a serem vendidos.
							if(comprador instanceof CompradorFan){
								administradorController.getFilaCompradoresFan().inserirFinal(comprador);// Insere o comprador que é fan na fila para concorrer a camisa.
							}
							sessao.setQtdIngressosVendidos(sessao.getQtdIngressosVendidos() + qtdIngressos);// Adiciona a quantidade de ingressos vendidos a sessão.
							for(int i = 0; i < qtdIngressos; i++){// Cria a quantidade de ingressos vendidos e os adiciona na lista de ingressos do cinema.
								ingresso = new Ingresso(cinema, sala, sessao);// Cria ingresso.
								comprador.getListaIngressos().inserirInicio(ingresso);// Adiciona ao comprador os ingressos comprados.
								cinema.getListaIngressos().inserirInicio(ingresso);// Coloca os ingressos na lista do cinema.
							}
							return;// Caso o ingresso possa ser comprado, o método retorna a quem o chamou.
						}
						else{
							throw new LimiteIngressosExcedidoException();// Caso o limite de ingressos vendidos seja atingido.
						}
					}
				}
				throw new SessaoNaoEncontradaException();// Caso não encontre a sessão.
			}
		}
		throw new SalaNaoEncontradaException();// Caso não encontre a sala.
	}
	
	
	/**
	 * Método que recupera um comprador cadastrado no AdministradorController a partir do documento de inscrição.
	 * @param documento - Número do documento do comprador.
	 * @throws CompradorNaoEncontradoException - Caso o comprador não seja encontrado.
	 */
	public Comprador recuperarComprador(int documento) throws CompradorNaoEncontradoException{
		
		IIterador it = administradorController.getListaComprador().iterador();
		Comprador comprador;
		
		while(it.temProximo()){
			comprador = (Comprador) it.obterProximo();
			if(comprador.getDocumento() == documento){// Procura o comprador com mesmo documento.
				return comprador;
			}
		}
		throw new CompradorNaoEncontradoException();
	}
	
	/**
	 * Método responsável por retornar a lista de ingressos vendidos para determinado comprador.
	 * @param comprador - Comprador escolhido.
	 * @return listaIngressos - Lista de ingressos vendidos.
	 * @throws CompradorNaoEncontradoException - Caso o comprador não exista.
	 * @throws CompradorNuloException - Caso o comprador recebido seja nulo.
	 */
	public Lista recuperarIngressos(Comprador comprador) throws CompradorNaoEncontradoException, CompradorNuloException{
		
		Comprador comprador2;
		IIterador iterador;
		
		if(comprador == null){
			throw new CompradorNuloException();
		}
		iterador = administradorController.getListaComprador().iterador();
		
		while(iterador.temProximo()){
			comprador2 = (Comprador)iterador.obterProximo();
			if(comprador2.getDocumento() == comprador.getDocumento()){
				return comprador2.getListaIngressos();
			}
		}
		throw new CompradorNaoEncontradoException();
	}
}
