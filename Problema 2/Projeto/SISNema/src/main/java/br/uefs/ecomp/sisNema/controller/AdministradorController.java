package br.uefs.ecomp.sisNema.controller;

/*******************************************************************************
Autor: Gustavo Henrique.
Componente Curricular: MI - Programa��o.
Conclu�do em: 05/03/2016
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum
trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo
de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte
do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
******************************************************************************************/

import br.uefs.ecomp.sisNema.exceptions.CampoObrigatorioInexistenteException;
import br.uefs.ecomp.sisNema.exceptions.CinemaNaoEncontradoException;
import br.uefs.ecomp.sisNema.exceptions.CinemaNuloException;
import br.uefs.ecomp.sisNema.exceptions.CompradorNaoEncontradoException;
import br.uefs.ecomp.sisNema.exceptions.CompradorNuloException;
import br.uefs.ecomp.sisNema.exceptions.FanHabilitadoInexistenteException;
import br.uefs.ecomp.sisNema.exceptions.HorarioNaoPermitidoException;
import br.uefs.ecomp.sisNema.exceptions.IntervaloMinimoInsuficienteException;
import br.uefs.ecomp.sisNema.exceptions.LimiteSalasExcedidoException;
import br.uefs.ecomp.sisNema.exceptions.RemocaoNaoPermitidaException;
import br.uefs.ecomp.sisNema.exceptions.SalaNaoEncontradaException;
import br.uefs.ecomp.sisNema.exceptions.SalaNulaException;
import br.uefs.ecomp.sisNema.exceptions.SessaoNaoEncontradaException;
import br.uefs.ecomp.sisNema.exceptions.SessaoNulaException;
import br.uefs.ecomp.sisNema.model.Cinema;
import br.uefs.ecomp.sisNema.model.Comprador;
import br.uefs.ecomp.sisNema.model.CompradorFan;
import br.uefs.ecomp.sisNema.model.Sala;
import br.uefs.ecomp.sisNema.model.Sessao;
import br.uefs.ecomp.sisNema.util.Fila;
import br.uefs.ecomp.sisNema.util.IIterador;
import br.uefs.ecomp.sisNema.util.Lista;

/**
 * Classe controller do administrador, respons�vel pelo gerenciamento do cinema.
 * @author Gustavo Henrique.
 * @since 18 de Fevereiro de 2016.
 *
 */
public class AdministradorController {

	private Lista listaCinema = new Lista();// Lista de cinemas cadastrados.
	private Lista listaComprador = new Lista();// Lista de compradores cadastrados(Estaram cadastrados os compradores normais e ou compradoresFan).
	private Fila filaCompradoresFan = new Fila();// Fila de concorrentes as camisas.
	private static AdministradorController instance;// Inst�ncia est�tica do controller.

	/**
	 * Construtor padr�o privado, pois por ser o controller ser� inst�nciado apenas uma vez.
	 */
	private AdministradorController(){

	}

	/**
	 * Retorna uma �nica inst�ncia da classe.
	 * @return instance - �nica inst�ncia da classe.
	 */

	public static AdministradorController getInstance(){
		if(instance == null){// Caso o controller ainda n�o tenha sido inst�nciado.
			instance = new AdministradorController();// � criado um controller.
		}
		return instance;// Retorna o controller existente.
	}

	/**
	 * Zera a inst�ncia da classe.
	 */
	public static void zerarSingleton(){
		instance = null;// Zera o singleton, pois retira a refer�ncia para o objeto inst�nciado.
	}

	/**
	 * M�todo respons�vel por cadastrar cinemas.
	 * @param cine - Cinema a ser cadastrado.
	 * @return cine - Retorna o cinema cadastrado.
	 * @throws CinemaNuloException - Caso o cinema a ser cadastrado seja nulo.
	 * @throws CampoObrigatorioInexistenteException - Caso exista algum campo inexistente.
	 */
	public Cinema cadastrarCinema (Cinema cine) throws CinemaNuloException, CampoObrigatorioInexistenteException{// Cadastra cinema.

		if(cine == null){// Caso o cinema a ser cadastrado ser nulo, vai lan�ar a seguinte exce��o.
			throw new CinemaNuloException();
		}
		else if(verificaObjeto(cine.getNome()) || verificaObjeto(cine.getEndereco()) || cine.getQtdSalas() <= 0){// Verifica se o nome/objeto existe ou � aceito.
			throw new CampoObrigatorioInexistenteException();
		}
		else{
			listaCinema.inserirInicio(cine);// Caso n�o exista restri��o, o cinema � registrado.
		}
		return cine;// Retorna o cinema recebido;
	}

	/**
	 * M�todo respons�vel por alterar o cinema, s� n�o � poss�vel alterar o ID.
	 * @param cinema - Cinema a ser alterado.
	 * @throws CinemaNuloException - Se o cinema recebido for nulo.
	 * @throws CinemaNaoEncontradoException - Caso o cinema n�o exista.
	 * @throws CampoObrigatorioInexistenteException - Caso algum campo obrigat�rio da altera��o n�o seja preenchido corretamente.
	 */

	public void alterarCinema(Cinema cinema) throws CinemaNuloException, CinemaNaoEncontradoException, CampoObrigatorioInexistenteException{
		Cinema cine;
		if(cinema == null){
			throw new CinemaNuloException();
		}
		cine = recuperarCinema(cinema.getId());// Pode lan�ar a exce��o CinemaNaoEncontradoException;

		if(cinema.getEndereco() == null || cinema.getNome().trim().isEmpty() || cinema.getQtdSalas() <= 0){
			throw new CampoObrigatorioInexistenteException();
		}
		else{
			cine.setEndereco(cinema.getEndereco());
			cine.setNome(cinema.getNome());
			cine.setListaIngressos(cinema.getListaIngressos());
			cine.setQtdSalas(cinema.getQtdSalas());
			cine.setSalas(cinema.getSalas());
		}
	}

	/**
	 * Remove o cinema a partir do id, s� � removido se n�o existir salas cadastradas.
	 * @param id - Id do cinema a ser removido.
	 * @throws CinemaNaoEncontradoException - Caso o cinema n�o seja encontrado.
	 * @throws RemocaoNaoPermitidaException - Caso o cinema n�o possa ser removido.
	 */
	public void removerCinema(int id) throws CinemaNaoEncontradoException, RemocaoNaoPermitidaException{
		IIterador it = listaCinema.iterador();
		Cinema cinema;
		int cont = 0;

		while(it.temProximo()){
			cinema = (Cinema)it.obterProximo();
			if(cinema.getId() == id){
				if(cinema.getListaIngressos().estaVazia()){// S� exclui se n�o tiver nenhum ingresso cadastrado.
					listaCinema.remover(cont);
					return;
				}else{
					throw new RemocaoNaoPermitidaException();
				}

			}
			cont++;
		}
		throw new CinemaNaoEncontradoException();
	}

	/**
	 * Retorna a lista de cinemas cadastrados.
	 * @return listaCinema - Lista de cinemas cadastrados.
	 */

	public Lista listarCinemas(){
		return listaCinema;
	}

	/**
	 * M�todo que cadastra sala.
	 * @param cine - Cinema em que a sala ser� cadastrada.
	 * @param sala - Sala a ser cadastrada.
	 * @throws CinemaNuloException - Caso o cinema recebido seja nulo.
	 * @throws SalaNulaException - Caso a sala recebida seja nula.
	 * @throws CinemaNaoEncontradoException  - Caso o cinema recebido n�o exista.
	 * @throws CampoObrigatorioInexistenteException - Caso a sala possua um campo inv�lido.
	 * @throws LimiteSalasExcedidoException - Caso o limite de salas seja excedido.
	 */

	public void cadastrarSala(Cinema cine, Sala sala) throws CinemaNuloException, SalaNulaException, CinemaNaoEncontradoException, CampoObrigatorioInexistenteException, LimiteSalasExcedidoException{

		Cinema cinema;
		if(cine == null){// Caso o cinema recebido seja nulo vai lan�ar a seguinte exce��o.
			throw new CinemaNuloException();
		}
		else if(sala == null){// Caso o cinema recebido seja nulo vai lan�ar a seguinte exce��o.
			throw new SalaNulaException();
		}
		else if (sala.getQtdCadeiras() <= 0 || sala.getNumeroSala() <= 0){
			throw new CampoObrigatorioInexistenteException();
		}
		cinema = recuperarCinema(cine.getId());
		if(cinema.getQtdSalas() > cinema.getSalas().obterTamanho()){
			cinema.getSalas().inserirInicio(sala);
			return;
		}
		else{
			throw new LimiteSalasExcedidoException();
		}
	}

	/**
	 * M�todo respons�vel por alterar a sess�o.
	 * @param cine - Cinema da sala.
	 * @param sala - Sala da sess�o.
	 * @param hInicioAntigo - Hor�rio de inicio antigo da sess�o.
	 * @param hFimAntigo - Hor�rio de t�rmino antigo da sess�o.
	 * @param hInicioNovo - Novo hor�rio de inicio da sess�o.
	 * @param hFimNovo - Novo hor�rio de t�rmino da sess�o.
	 * @throws CinemaNuloException - Caso o cinema recebido seja nulo.
	 * @throws SalaNulaException - Caso a sala recebida seja nula.
	 * @throws SalaNaoEncontradaException - Caso a sala n�o seja encontrada.
	 * @throws CinemaNaoEncontradoException - Caso o cinema n�o seja encontrado.
	 * @throws CampoObrigatorioInexistenteException - Caso exista um campo obrigat�rio inexistente ou n�o aceit�vel.
	 * @throws SessaoNaoEncontradaException - Caso a sess�o n�o seja encontrada.
	 * @throws IntervaloMinimoInsuficienteException - Caso o intervalo m�nimo seja atingido.
	 */
	public void alterarSessao(Cinema cine, Sala sala, int hInicioAntigo, int hFimAntigo, int hInicioNovo, int hFimNovo) throws CinemaNuloException, SalaNulaException, CinemaNaoEncontradoException, SalaNaoEncontradaException, CampoObrigatorioInexistenteException, SessaoNaoEncontradaException, IntervaloMinimoInsuficienteException{

		if(cine == null){
			throw new CinemaNuloException();
		}
		else if(sala == null){
			throw new SalaNulaException();
		}
		else{
			Sala sa = recuperarSala(cine, sala.getNumeroSala());// Pode lan�ar a exce��o SalaNaoEncontradaException ou CinemaNaoEncontradoException.
			IIterador it;
			if(hInicioNovo < 1 || hFimNovo <= 1 || hInicioNovo >= 23 || hFimNovo > 23){
				throw new CampoObrigatorioInexistenteException();
			}
			it = sa.getListaSessoes().iterador();
			Sessao sessao = recuperarSessao(cine, sala, hInicioAntigo, hFimAntigo);
			Sessao sessaoR;
			while(it.temProximo()){
				sessaoR = (Sessao)it.obterProximo();
				if(!(sessaoR.getHoraInicio() - 3 <= hInicioNovo && sessaoR.getHoraInicio() + 3 <= hInicioNovo)){
					if((sessaoR.getHoraInicio() != hInicioAntigo && sessaoR.getHoraFim() != hFimAntigo)){// Considero que vai coincidir com a sess�o cadastrada.
						throw new IntervaloMinimoInsuficienteException(); // Caso exista conflito de hor�rio, a exce��o � lan�ada.
					}
				}
			}
			sessao.setHoraInicio(hInicioNovo);
			sessao.setHoraFim(hFimNovo);
		}
	}

	/**
	 * 
	 * M�todo que cadastra a sess�o e considera a hora em 24hrs e as sess�es n�o podem iniciar em um dia e terminar em outro.
	 * 
	 * @param cine - Cinema em que a sess�o vai ser cadastrada.
	 * @param sala - Sala em que a sess�o vai ser cadastrada.
	 * @param sessao - Sess�o a ser cadastrada.
	 * @throws CinemaNuloException - Caso o cinema seja nulo.
	 * @throws SalaNulaException - Caso a sala seja nula.
	 * @throws SessaoNulaException - Caso a sess�o seja nula.
	 * @throws CampoObrigatorioInexistenteException - Caso os campos obrigat�rios sejam preenchidos de forma err�nea.
	 * @throws CinemaNaoEncontradoException - Caso o cinema n�o seja encontrado.
	 * @throws SalaNaoEncontradaException - Caso a sala n�o seja encontrada.
	 * @throws IntervaloMinimoInsuficienteException - Caso exista conflito entre sess�es.
	 * @throws HorarioNaoPermitidoException - Caso o hor�rio seja maior que 24 horas;
	 */
	public void cadastrarSessao(Cinema cine, Sala sala, Sessao sessao) throws CinemaNuloException, SalaNulaException, SessaoNulaException, CampoObrigatorioInexistenteException, CinemaNaoEncontradoException, SalaNaoEncontradaException, IntervaloMinimoInsuficienteException, HorarioNaoPermitidoException{

		if(cine == null){
			throw new CinemaNuloException();
		}
		else if(sala == null){
			throw new SalaNulaException();
		}
		else if(sessao == null){
			throw new SessaoNulaException();
		}
		else if(sessao.getHoraInicio() <= 0 || sessao.getHoraFim() <= 0){// Considera que as sess�es podem iniciar de n�mero menores de zero mas n�o podem terminar em zero.
			throw new CampoObrigatorioInexistenteException();
		}
		else if(sessao.getHoraInicio() >= 23 || sessao.getHoraFim() > 23){
			throw new HorarioNaoPermitidoException();
		}
		else{
			Cinema cinemaR = recuperarCinema(cine.getId());
			Sala salaR = recuperarSala(cinemaR, sala.getNumeroSala());
			IIterador it = salaR.getListaSessoes().iterador();
			Sessao sessaoR;

			while(it.temProximo()){
				sessaoR = (Sessao)it.obterProximo();
				if(!(sessaoR.getHoraInicio() - 3 <= sessao.getHoraInicio() && sessaoR.getHoraInicio() + 3 <= sessao.getHoraInicio())) {
					throw new IntervaloMinimoInsuficienteException(); // Caso exista conflito de hor�rio, a exce��o � lan�ada.
				}
			}
			salaR.getListaSessoes().inserirInicio(sessao);
		}
	}

	/**
	 * M�todo respons�vel por retornar a sess�o a partir do hor�rio de inicio de hor�rio de fim da sess�o.
	 * @param hInicio - Hor�rio de inicio da sess�o.
	 * @param hFim - Hor�rio de termino da sess�o.
	 * @param cinema - Cinema que possui a sess�o.
	 * @param sala - Sala que possui a sess�o.
	 * @return sessao - Sess�o encontrada.
	 * @throws CinemaNaoEncontradoException - Caso o cinema recebido n�o exista.
	 * @throws CinemaNuloException - Caso o cinema recebido seja nulo.
	 * @throws SalaNulaException - Caso a sala seja nula.
	 * @throws CampoObrigatorioInexistenteException - Caso o hor�rio recebido da sess�o n�o seja valido.
	 * @throws SalaNaoEncontradaException - Caso a sala recebido n�o seja encontrada.
	 * @throws SessaoNaoEncontradaException - Caso a sess�o n�o seja encontrada.
	 */
	public Sessao recuperarSessao(Cinema cinema, Sala sala, int hInicio, int hFim) throws CinemaNaoEncontradoException, CinemaNuloException, SalaNulaException, CampoObrigatorioInexistenteException, SalaNaoEncontradaException, SessaoNaoEncontradaException{

		if( cinema == null ){
			throw new CinemaNuloException();
		}
		else if( sala == null ){
			throw new SalaNulaException();
		}
		else if(hInicio <= 1 || hFim >= 23){
			throw new CampoObrigatorioInexistenteException();
		}
		else{
			Cinema cine = recuperarCinema(cinema.getId());
			Sala sala2 = recuperarSala(cine, sala.getNumeroSala());
			IIterador iter = sala2.getListaSessoes().iterador();
			Sessao sessao;
			while(iter.temProximo()){
				sessao = (Sessao)iter.obterProximo();
				if(sessao.getHoraInicio() == hInicio && sessao.getHoraFim() == hFim){
					return sessao;
				}
			}
			throw new SessaoNaoEncontradaException();
		}
	}

	/**
	 * M�todo respons�vel por alterar a sala.
	 * @param cinema - Cinema da sala.
	 * @param sala - Sala a ser editada.
	 * @throws CinemaNuloException - Caso o cinema recebido seja nulo.
	 * @throws SalaNulaException - Caso a sala recebida seja nula.
	 * @throws CinemaNaoEncontradoException - Caso o recebido n�o seja encontrado.
	 * @throws CampoObrigatorioInexistenteException - Caso a quantidade de cadeiras seja menor ou igual a zero.
	 * @throws SalaNaoEncontradaException - Caso a sala recebida n�o seja encontrada.
	 */

	public void alterarSala(Cinema cinema, Sala sala) throws CinemaNuloException, SalaNulaException, CinemaNaoEncontradoException, CampoObrigatorioInexistenteException, SalaNaoEncontradaException{

		Cinema cine;
		Sala sa;
		IIterador it;

		if(cinema == null){
			throw new CinemaNuloException();
		}
		else if(sala == null){
			throw new SalaNulaException();
		}
		else{
			cine = recuperarCinema(cinema.getId());// Poss�vel lan�amento da exce��o CinemaNaoEncontradoExceptio();
			if(sala.getNumeroSala() <= 0){
				throw new CampoObrigatorioInexistenteException();
			}
			it = cine.getSalas().iterador();
			while(it.temProximo()){
				sa = (Sala)it.obterProximo();
				if(sa.getNumeroSala() == sala.getNumeroSala()){
					if(sala.getQtdCadeiras() <= 0){// S� comparo a quantidade de cadeiras porque se achou a sala significa que o campo j� � preenchido corretamente.
						throw new CampoObrigatorioInexistenteException();
					}
					else{
						sa.setQtdCadeiras(sala.getQtdCadeiras());
						return;
					}
				}
			}
			throw new SalaNaoEncontradaException();
		}

	}

	/**
	 * Retorna a lista de salas do cinema.
	 * @param cine - Cinema que contem as salas.
	 * @return listaSalas - Lista de salas do cinema.
	 * @throws CinemaNuloException - Caso o cinema recebido seja nulo.
	 * @throws CinemaNaoEncontradoException - Caso o cinema n�o seja encontrado.
	 */

	public Lista listarSalas(Cinema cine) throws CinemaNuloException, CinemaNaoEncontradoException{

		if(cine == null){
			throw new CinemaNuloException();
		}
		Cinema cinema = recuperarCinema(cine.getId());// Pode lan�ar a exce��o CinemaNaoEncontradoException

		return cinema.getSalas();
	}

	/**
	 * Retorna o tamanho da lista de sess�es da sala.
	 * @param cinema - Cinema que cont�m a sala.
	 * @param sala - Sala que cont�m a sess�o.
	 * @return listaSessoes - Lista de sess�es da sala.
	 * @throws CinemaNuloException - Caso o cinema recebido seja nulo.
	 * @throws SalaNulaException - Caso a sala recebida seja nula.
	 * @throws CinemaNaoEncontradoException - Caso o cinema n�o seja encontrado.
	 * @throws SalaNaoEncontradaException - Caso a sala n�o seja encontrada.
	 */
	public Lista listarSessoes(Cinema cinema, Sala sala) throws CinemaNuloException, SalaNulaException, CinemaNaoEncontradoException, SalaNaoEncontradaException{

		if(sala == null){
			throw new SalaNulaException();
		}
		else{
			Sala sa = recuperarSala(cinema, sala.getNumeroSala());
			return sa.getListaSessoes();
		}
	}

	/**
	 * Cadastra o comprador e verifica se o comprador � fan ou n�o.
	 * @param comprador - Comprador a ser cadastrado.
	 * @throws CompradorNuloException - Caso o comprador seja nulo.
	 * @throws CampoObrigatorioInexistenteException - Caso campos obrigat�rios n�o sejam preenchidos.
	 */

	public void cadastrarComprador(Comprador comprador) throws CompradorNuloException, CampoObrigatorioInexistenteException{

		if(comprador == null){
			throw new CompradorNuloException();
		}
		else if(comprador.getEndereco() == null){
			throw new CampoObrigatorioInexistenteException();
		}
		else if(comprador.getDocumento() <= 0){//Considera que o documento vai ser maior que zero.
			throw new CampoObrigatorioInexistenteException();
		}
		else if(verificaObjeto(comprador.getNome())){// N�o aceita nomes nulos ou com apenas caracteres ' '(espa�o).
			throw new CampoObrigatorioInexistenteException();
		}
		else if(verificaObjeto(comprador.getEmail())){// N�o aceita email nulo ou com apenas caracteres ' '(espa�o).
			throw new CampoObrigatorioInexistenteException();
		}
		else if(verificaObjeto(comprador.getTelefone())){// N�o aceita telefone nulos ou com apenas caracteres ' '(espa�o).
			throw new CampoObrigatorioInexistenteException();
		}
		else{
			if(comprador instanceof CompradorFan){// Caso seja um comprador fan, � necess�rio verificar se o registro do fan-clube � v�lido.
				CompradorFan compradorFan = (CompradorFan) comprador;
				if(compradorFan.getRegistro() <= 0){// Novamente considero que o n�mero de registro n�o pode ser menor ou igual a zero.
					throw new CampoObrigatorioInexistenteException();
				}
			}
			listaComprador.inserirInicio(comprador);// Caso n�o lance nenhuma das exceptions anteriores o comprador ser� cadastrado.
		}
	}

	/**
	 * M�todo que recupera o comprador atrav�s do documento.
	 * @param documento - Documento do usu�rio a ser recuperado.
	 * @return comprador - Comprador recuperado.
	 * @throws CompradorNaoEncontradoException - Caso o comprador procurado n�o seja encontrado.
	 */

	public Comprador recuperarComprador(int documento) throws CompradorNaoEncontradoException{

		IIterador it = listaComprador.iterador();
		Comprador comprador;

		while(it.temProximo()){
			comprador = (Comprador)it.obterProximo();
			if(comprador.getDocumento() == documento){
				return comprador;
			}
		}
		throw new CompradorNaoEncontradoException();
	}

	/**
	 * M�todo respons�vel por retornar a lista de compradores.
	 * @return listaComprador - Lista de compradores;
	 */

	public Lista listarCompradores(){
		return listaComprador;
	}

	/**
	 * M�todo que remove o comprador atr�ves do documento do comprador.
	 * @param documento - Documento do comprador.
	 * @throws RemocaoNaoPermitidaException - Caso o comprador n�o possa ser removido.
	 * @throws CompradorNaoEncontradoException - Caso o comprador n�o seja encontrado.
	 */
	public Comprador removerComprador(int documento) throws RemocaoNaoPermitidaException, CompradorNaoEncontradoException{
		Comprador comprador = null;
		IIterador iterador = listaComprador.iterador();
		int cont = 0;
		while(iterador.temProximo()){
			comprador = (Comprador)iterador.obterProximo();
			if(comprador.getDocumento() == documento){
				if(comprador.getListaIngressos().estaVazia()){
					listaComprador.remover(cont);
					return comprador;
				}
				else{
					throw new RemocaoNaoPermitidaException();
				}
			}
			cont++;
		}
		throw new CompradorNaoEncontradoException();
	}

	/**
	 * M�todo respons�vel por recuperar a sala atrav�s do cinema e o id da sala.
	 * @param cinema - Cinema que cont�m a sala.
	 * @param id - id da sala a ser procurado.
	 * @param sala - Caso a sala seja encontrada.
	 * @throws CinemaNuloException - Caso o cinema recebido seja nulo.
	 * @throws CinemaNaoEncontradoException - Caso o cinema n�o sela encontrado.
	 * @throws SalaNaoEncontradaException - Caso a sala n�o seja encontrada.
	 */

	public Sala recuperarSala(Cinema cinema, int id) throws CinemaNuloException, CinemaNaoEncontradoException, SalaNaoEncontradaException{

		if(cinema == null){
			throw new CinemaNuloException();
		}
		else{
			Cinema cine = recuperarCinema(cinema.getId());// Pode lan�ar a exce��o CinemaNaoEncontradoException.
			IIterador it = cine.getSalas().iterador();
			Sala sa;
			while(it.temProximo()){
				sa = (Sala)it.obterProximo();
				if(sa.getNumeroSala() == id){
					return sa;
				}
			}
			throw new SalaNaoEncontradaException();
		}
	}

	/**
	 * M�todo respons�vel por alterar o comprador.
	 * @param comprador - Comprador a ser alterado.
	 * @return comprador - O comprador utilizado.
	 * @throws CompradorNuloException - Caso o comprador passado seja nulo. 
	 * @throws CompradorNaoEncontradoException 
	 * @throws CampoObrigatorioInexistenteException 
	 */

	public Comprador alterarComprador(Comprador comprador) throws CompradorNuloException, CompradorNaoEncontradoException, CampoObrigatorioInexistenteException{

		Comprador comp;
		if(comprador == null){
			throw new CompradorNuloException();
		}
		if(verificaObjeto(comprador.getEmail()) || verificaObjeto(comprador.getEndereco()) || verificaObjeto(comprador.getTelefone()) || verificaObjeto(comprador.getNome()) || comprador.getDocumento() <= 0)
			throw new CampoObrigatorioInexistenteException();
		else{
			comp = recuperarComprador(comprador.getDocumento());// Pode lan�ar a exce��o CompradorNaoEncontradoException.
			if(comprador instanceof CompradorFan){
				CompradorFan comprar = (CompradorFan) comprador;
				if(comprar.getRegistro() <= 0){
					throw new CampoObrigatorioInexistenteException();
				}
			}
			comp.setEmail(comprador.getEmail());
			comp.setEndereco(comprador.getEndereco());
			comp.setNome(comprador.getNome());
			comp.setTelefone(comprador.getTelefone());
		}
		return comprador;
	}

	/**
	 * Recupera o cinema a partir do id recebido.
	 * @param id - id do cinema a ser procurado.
	 * @throws CinemaNaoEncontradoException - Caso n�o exista cinema com o id recebido.
	 */

	public Cinema recuperarCinema(int id) throws CinemaNaoEncontradoException{

		IIterador it = listaCinema.iterador();
		Cinema cinema;
		while(it.temProximo()){
			cinema = (Cinema)it.obterProximo();
			if(cinema.getId() == id){
				return cinema;
			}
		}
		throw new CinemaNaoEncontradoException();
	}

	/**
	 * M�todo que retorna a fila de fans habilitados.
	 * @return fila - Fila de fans habilitados.
	 */

	public Fila recuperarFansHabilitados(){
		return filaCompradoresFan;
	}

	/**
	 * M�todo respons�vel por realizar a distribui��o de camisas.
	 * @param quantidade - Quantidade de camisas distribu�das.
	 * @throws FanHabilitadoInexistenteException - Caso tente distribuir mais camisas do que fans existentes.
	 */
	public void distribuirCamisas(int quantidade) throws FanHabilitadoInexistenteException{
		if(quantidade > filaCompradoresFan.obterTamanho()){
			throw new FanHabilitadoInexistenteException();
		}
		for(int i = 0; i < quantidade; i++){
			filaCompradoresFan.removerInicio();
		}
	}

	/**
	 * Retorna a lista de cinemas cadastrados.
	 * @return listaCinema - Listas de cinemas.
	 */
	public Lista getListaCinema() {
		return listaCinema;
	}

	/**
	 * Altera a lista de cinemas cadastrados.
	 * @param listaCinema - Nova lista de cinemas.
	 */
	public void setListaCinema(Lista listaCinema) {
		this.listaCinema = listaCinema;
	}

	/**
	 * Retorna a lista de compradores cadastrados.
	 * @return listaComprador - Lista de compradores.
	 */
	public Lista getListaComprador() {
		return listaComprador;
	}

	/**
	 * Altera a lista de compradores.
	 * @param listaComprador - Nova lista de compradores.
	 */
	public void setListaComprador(Lista listaComprador) {
		this.listaComprador = listaComprador;
	}

	/**
	 * Retorna a fila de concorrentes as camisas.
	 * @return filaCompradoresFan - Fila de concorrentes a camisa.
	 */
	public Fila getFilaCompradoresFan() {
		return filaCompradoresFan;
	}

	/**
	 * Altera a fila de concorrentes as camisas.
	 * @param filaCompradoresFan - Nova fila de concorrentes as camisas.
	 */
	public void setFilaCompradoresFan(Fila filaCompradoresFan) {
		this.filaCompradoresFan = filaCompradoresFan;
	}

	/**
	 * M�todo que verifica se o objeto � valido, caso seja uma string verifica se � valida.
	 * @param object - Objeto a ser comparado.
	 * @return true - Caso seja um objeto valido, false - Caso o objeto n�o seja valido.
	 */

	private boolean verificaObjeto(Object object){

		if(object == null){
			return true;
		}
		else{
			if(object instanceof String){
				String string = (String)object;
				if(string.trim().isEmpty()){
					return true;
				}
			}
		}
		return false;
	}
}