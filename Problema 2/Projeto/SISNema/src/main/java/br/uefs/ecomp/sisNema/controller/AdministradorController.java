package br.uefs.ecomp.sisNema.controller;

/*******************************************************************************
Autor: Gustavo Henrique.
Componente Curricular: MI - Programação.
Concluído em: 05/03/2016
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
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
 * Classe controller do administrador, responsável pelo gerenciamento do cinema.
 * @author Gustavo Henrique.
 * @since 18 de Fevereiro de 2016.
 *
 */
public class AdministradorController {

	private Lista listaCinema = new Lista();// Lista de cinemas cadastrados.
	private Lista listaComprador = new Lista();// Lista de compradores cadastrados(Estaram cadastrados os compradores normais e ou compradoresFan).
	private Fila filaCompradoresFan = new Fila();// Fila de concorrentes as camisas.
	private static AdministradorController instance;// Instância estática do controller.

	/**
	 * Construtor padrão privado, pois por ser o controller será instânciado apenas uma vez.
	 */
	private AdministradorController(){

	}

	/**
	 * Retorna uma única instância da classe.
	 * @return instance - Única instância da classe.
	 */

	public static AdministradorController getInstance(){
		if(instance == null){// Caso o controller ainda não tenha sido instânciado.
			instance = new AdministradorController();// É criado um controller.
		}
		return instance;// Retorna o controller existente.
	}

	/**
	 * Zera a instância da classe.
	 */
	public static void zerarSingleton(){
		instance = null;// Zera o singleton, pois retira a referência para o objeto instânciado.
	}

	/**
	 * Método responsável por cadastrar cinemas.
	 * @param cine - Cinema a ser cadastrado.
	 * @return cine - Retorna o cinema cadastrado.
	 * @throws CinemaNuloException - Caso o cinema a ser cadastrado seja nulo.
	 * @throws CampoObrigatorioInexistenteException - Caso exista algum campo inexistente.
	 */
	public Cinema cadastrarCinema (Cinema cine) throws CinemaNuloException, CampoObrigatorioInexistenteException{// Cadastra cinema.

		if(cine == null){// Caso o cinema a ser cadastrado ser nulo, vai lançar a seguinte exceção.
			throw new CinemaNuloException();
		}
		else if(verificaObjeto(cine.getNome()) || verificaObjeto(cine.getEndereco()) || cine.getQtdSalas() <= 0){// Verifica se o nome/objeto existe ou é aceito.
			throw new CampoObrigatorioInexistenteException();
		}
		else{
			listaCinema.inserirInicio(cine);// Caso não exista restrição, o cinema é registrado.
		}
		return cine;// Retorna o cinema recebido;
	}

	/**
	 * Método responsável por alterar o cinema, só não é possível alterar o ID.
	 * @param cinema - Cinema a ser alterado.
	 * @throws CinemaNuloException - Se o cinema recebido for nulo.
	 * @throws CinemaNaoEncontradoException - Caso o cinema não exista.
	 * @throws CampoObrigatorioInexistenteException - Caso algum campo obrigatório da alteração não seja preenchido corretamente.
	 */

	public void alterarCinema(Cinema cinema) throws CinemaNuloException, CinemaNaoEncontradoException, CampoObrigatorioInexistenteException{
		Cinema cine;
		if(cinema == null){
			throw new CinemaNuloException();
		}
		cine = recuperarCinema(cinema.getId());// Pode lançar a exceção CinemaNaoEncontradoException;

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
	 * Remove o cinema a partir do id, só é removido se não existir salas cadastradas.
	 * @param id - Id do cinema a ser removido.
	 * @throws CinemaNaoEncontradoException - Caso o cinema não seja encontrado.
	 * @throws RemocaoNaoPermitidaException - Caso o cinema não possa ser removido.
	 */
	public void removerCinema(int id) throws CinemaNaoEncontradoException, RemocaoNaoPermitidaException{
		IIterador it = listaCinema.iterador();
		Cinema cinema;
		int cont = 0;

		while(it.temProximo()){
			cinema = (Cinema)it.obterProximo();
			if(cinema.getId() == id){
				if(cinema.getListaIngressos().estaVazia()){// Só exclui se não tiver nenhum ingresso cadastrado.
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
	 * Método que cadastra sala.
	 * @param cine - Cinema em que a sala será cadastrada.
	 * @param sala - Sala a ser cadastrada.
	 * @throws CinemaNuloException - Caso o cinema recebido seja nulo.
	 * @throws SalaNulaException - Caso a sala recebida seja nula.
	 * @throws CinemaNaoEncontradoException  - Caso o cinema recebido não exista.
	 * @throws CampoObrigatorioInexistenteException - Caso a sala possua um campo inválido.
	 * @throws LimiteSalasExcedidoException - Caso o limite de salas seja excedido.
	 */

	public void cadastrarSala(Cinema cine, Sala sala) throws CinemaNuloException, SalaNulaException, CinemaNaoEncontradoException, CampoObrigatorioInexistenteException, LimiteSalasExcedidoException{

		Cinema cinema;
		if(cine == null){// Caso o cinema recebido seja nulo vai lançar a seguinte exceção.
			throw new CinemaNuloException();
		}
		else if(sala == null){// Caso o cinema recebido seja nulo vai lançar a seguinte exceção.
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
	 * Método responsável por alterar a sessão.
	 * @param cine - Cinema da sala.
	 * @param sala - Sala da sessão.
	 * @param hInicioAntigo - Horário de inicio antigo da sessão.
	 * @param hFimAntigo - Horário de término antigo da sessão.
	 * @param hInicioNovo - Novo horário de inicio da sessão.
	 * @param hFimNovo - Novo horário de término da sessão.
	 * @throws CinemaNuloException - Caso o cinema recebido seja nulo.
	 * @throws SalaNulaException - Caso a sala recebida seja nula.
	 * @throws SalaNaoEncontradaException - Caso a sala não seja encontrada.
	 * @throws CinemaNaoEncontradoException - Caso o cinema não seja encontrado.
	 * @throws CampoObrigatorioInexistenteException - Caso exista um campo obrigatório inexistente ou não aceitável.
	 * @throws SessaoNaoEncontradaException - Caso a sessão não seja encontrada.
	 * @throws IntervaloMinimoInsuficienteException - Caso o intervalo mínimo seja atingido.
	 */
	public void alterarSessao(Cinema cine, Sala sala, int hInicioAntigo, int hFimAntigo, int hInicioNovo, int hFimNovo) throws CinemaNuloException, SalaNulaException, CinemaNaoEncontradoException, SalaNaoEncontradaException, CampoObrigatorioInexistenteException, SessaoNaoEncontradaException, IntervaloMinimoInsuficienteException{

		if(cine == null){
			throw new CinemaNuloException();
		}
		else if(sala == null){
			throw new SalaNulaException();
		}
		else{
			Sala sa = recuperarSala(cine, sala.getNumeroSala());// Pode lançar a exceção SalaNaoEncontradaException ou CinemaNaoEncontradoException.
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
					if((sessaoR.getHoraInicio() != hInicioAntigo && sessaoR.getHoraFim() != hFimAntigo)){// Considero que vai coincidir com a sessão cadastrada.
						throw new IntervaloMinimoInsuficienteException(); // Caso exista conflito de horário, a exceção é lançada.
					}
				}
			}
			sessao.setHoraInicio(hInicioNovo);
			sessao.setHoraFim(hFimNovo);
		}
	}

	/**
	 * 
	 * Método que cadastra a sessão e considera a hora em 24hrs e as sessões não podem iniciar em um dia e terminar em outro.
	 * 
	 * @param cine - Cinema em que a sessão vai ser cadastrada.
	 * @param sala - Sala em que a sessão vai ser cadastrada.
	 * @param sessao - Sessão a ser cadastrada.
	 * @throws CinemaNuloException - Caso o cinema seja nulo.
	 * @throws SalaNulaException - Caso a sala seja nula.
	 * @throws SessaoNulaException - Caso a sessão seja nula.
	 * @throws CampoObrigatorioInexistenteException - Caso os campos obrigatórios sejam preenchidos de forma errônea.
	 * @throws CinemaNaoEncontradoException - Caso o cinema não seja encontrado.
	 * @throws SalaNaoEncontradaException - Caso a sala não seja encontrada.
	 * @throws IntervaloMinimoInsuficienteException - Caso exista conflito entre sessões.
	 * @throws HorarioNaoPermitidoException - Caso o horário seja maior que 24 horas;
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
		else if(sessao.getHoraInicio() <= 0 || sessao.getHoraFim() <= 0){// Considera que as sessões podem iniciar de número menores de zero mas não podem terminar em zero.
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
					throw new IntervaloMinimoInsuficienteException(); // Caso exista conflito de horário, a exceção é lançada.
				}
			}
			salaR.getListaSessoes().inserirInicio(sessao);
		}
	}

	/**
	 * Método responsável por retornar a sessão a partir do horário de inicio de horário de fim da sessão.
	 * @param hInicio - Horário de inicio da sessão.
	 * @param hFim - Horário de termino da sessão.
	 * @param cinema - Cinema que possui a sessão.
	 * @param sala - Sala que possui a sessão.
	 * @return sessao - Sessão encontrada.
	 * @throws CinemaNaoEncontradoException - Caso o cinema recebido não exista.
	 * @throws CinemaNuloException - Caso o cinema recebido seja nulo.
	 * @throws SalaNulaException - Caso a sala seja nula.
	 * @throws CampoObrigatorioInexistenteException - Caso o horário recebido da sessão não seja valido.
	 * @throws SalaNaoEncontradaException - Caso a sala recebido não seja encontrada.
	 * @throws SessaoNaoEncontradaException - Caso a sessão não seja encontrada.
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
	 * Método responsável por alterar a sala.
	 * @param cinema - Cinema da sala.
	 * @param sala - Sala a ser editada.
	 * @throws CinemaNuloException - Caso o cinema recebido seja nulo.
	 * @throws SalaNulaException - Caso a sala recebida seja nula.
	 * @throws CinemaNaoEncontradoException - Caso o recebido não seja encontrado.
	 * @throws CampoObrigatorioInexistenteException - Caso a quantidade de cadeiras seja menor ou igual a zero.
	 * @throws SalaNaoEncontradaException - Caso a sala recebida não seja encontrada.
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
			cine = recuperarCinema(cinema.getId());// Possível lançamento da exceção CinemaNaoEncontradoExceptio();
			if(sala.getNumeroSala() <= 0){
				throw new CampoObrigatorioInexistenteException();
			}
			it = cine.getSalas().iterador();
			while(it.temProximo()){
				sa = (Sala)it.obterProximo();
				if(sa.getNumeroSala() == sala.getNumeroSala()){
					if(sala.getQtdCadeiras() <= 0){// Só comparo a quantidade de cadeiras porque se achou a sala significa que o campo já é preenchido corretamente.
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
	 * @throws CinemaNaoEncontradoException - Caso o cinema não seja encontrado.
	 */

	public Lista listarSalas(Cinema cine) throws CinemaNuloException, CinemaNaoEncontradoException{

		if(cine == null){
			throw new CinemaNuloException();
		}
		Cinema cinema = recuperarCinema(cine.getId());// Pode lançar a exceção CinemaNaoEncontradoException

		return cinema.getSalas();
	}

	/**
	 * Retorna o tamanho da lista de sessões da sala.
	 * @param cinema - Cinema que contém a sala.
	 * @param sala - Sala que contém a sessão.
	 * @return listaSessoes - Lista de sessões da sala.
	 * @throws CinemaNuloException - Caso o cinema recebido seja nulo.
	 * @throws SalaNulaException - Caso a sala recebida seja nula.
	 * @throws CinemaNaoEncontradoException - Caso o cinema não seja encontrado.
	 * @throws SalaNaoEncontradaException - Caso a sala não seja encontrada.
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
	 * Cadastra o comprador e verifica se o comprador é fan ou não.
	 * @param comprador - Comprador a ser cadastrado.
	 * @throws CompradorNuloException - Caso o comprador seja nulo.
	 * @throws CampoObrigatorioInexistenteException - Caso campos obrigatórios não sejam preenchidos.
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
		else if(verificaObjeto(comprador.getNome())){// Não aceita nomes nulos ou com apenas caracteres ' '(espaço).
			throw new CampoObrigatorioInexistenteException();
		}
		else if(verificaObjeto(comprador.getEmail())){// Não aceita email nulo ou com apenas caracteres ' '(espaço).
			throw new CampoObrigatorioInexistenteException();
		}
		else if(verificaObjeto(comprador.getTelefone())){// Não aceita telefone nulos ou com apenas caracteres ' '(espaço).
			throw new CampoObrigatorioInexistenteException();
		}
		else{
			if(comprador instanceof CompradorFan){// Caso seja um comprador fan, é necessário verificar se o registro do fan-clube é válido.
				CompradorFan compradorFan = (CompradorFan) comprador;
				if(compradorFan.getRegistro() <= 0){// Novamente considero que o número de registro não pode ser menor ou igual a zero.
					throw new CampoObrigatorioInexistenteException();
				}
			}
			listaComprador.inserirInicio(comprador);// Caso não lance nenhuma das exceptions anteriores o comprador será cadastrado.
		}
	}

	/**
	 * Método que recupera o comprador através do documento.
	 * @param documento - Documento do usuário a ser recuperado.
	 * @return comprador - Comprador recuperado.
	 * @throws CompradorNaoEncontradoException - Caso o comprador procurado não seja encontrado.
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
	 * Método responsável por retornar a lista de compradores.
	 * @return listaComprador - Lista de compradores;
	 */

	public Lista listarCompradores(){
		return listaComprador;
	}

	/**
	 * Método que remove o comprador atráves do documento do comprador.
	 * @param documento - Documento do comprador.
	 * @throws RemocaoNaoPermitidaException - Caso o comprador não possa ser removido.
	 * @throws CompradorNaoEncontradoException - Caso o comprador não seja encontrado.
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
	 * Método responsável por recuperar a sala através do cinema e o id da sala.
	 * @param cinema - Cinema que contém a sala.
	 * @param id - id da sala a ser procurado.
	 * @param sala - Caso a sala seja encontrada.
	 * @throws CinemaNuloException - Caso o cinema recebido seja nulo.
	 * @throws CinemaNaoEncontradoException - Caso o cinema não sela encontrado.
	 * @throws SalaNaoEncontradaException - Caso a sala não seja encontrada.
	 */

	public Sala recuperarSala(Cinema cinema, int id) throws CinemaNuloException, CinemaNaoEncontradoException, SalaNaoEncontradaException{

		if(cinema == null){
			throw new CinemaNuloException();
		}
		else{
			Cinema cine = recuperarCinema(cinema.getId());// Pode lançar a exceção CinemaNaoEncontradoException.
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
	 * Método responsável por alterar o comprador.
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
			comp = recuperarComprador(comprador.getDocumento());// Pode lançar a exceção CompradorNaoEncontradoException.
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
	 * @throws CinemaNaoEncontradoException - Caso não exista cinema com o id recebido.
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
	 * Método que retorna a fila de fans habilitados.
	 * @return fila - Fila de fans habilitados.
	 */

	public Fila recuperarFansHabilitados(){
		return filaCompradoresFan;
	}

	/**
	 * Método responsável por realizar a distribuição de camisas.
	 * @param quantidade - Quantidade de camisas distribuídas.
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
	 * Método que verifica se o objeto é valido, caso seja uma string verifica se é valida.
	 * @param object - Objeto a ser comparado.
	 * @return true - Caso seja um objeto valido, false - Caso o objeto não seja valido.
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