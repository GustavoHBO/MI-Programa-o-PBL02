package br.uefs.ecomp.sisNema.model;

public class CompradorFan extends Comprador{

	private int registroFan;// Número de registro do fan no fã-clube.
	
	/**
	 * Caso o usuário participe do fã-clube, ira retornar o número do registro do fã-clube.
	 * @return registroFan - Número de registro no fã-clube.
	 */
	public int getRegistro() {
		return registroFan;
	}

	/**
	 * Caso o usuário participe do fã-clube, ira alterar o número do registro do fã-clube.
	 * @param i - Novo número de registro do fã-clube.
	 */
	public void setRegistro(int i) {
		this.registroFan = i;
	}
}
