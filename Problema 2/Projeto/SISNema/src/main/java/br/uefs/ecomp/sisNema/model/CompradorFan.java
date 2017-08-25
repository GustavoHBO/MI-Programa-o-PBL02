package br.uefs.ecomp.sisNema.model;

public class CompradorFan extends Comprador{

	private int registroFan;// N�mero de registro do fan no f�-clube.
	
	/**
	 * Caso o usu�rio participe do f�-clube, ira retornar o n�mero do registro do f�-clube.
	 * @return registroFan - N�mero de registro no f�-clube.
	 */
	public int getRegistro() {
		return registroFan;
	}

	/**
	 * Caso o usu�rio participe do f�-clube, ira alterar o n�mero do registro do f�-clube.
	 * @param i - Novo n�mero de registro do f�-clube.
	 */
	public void setRegistro(int i) {
		this.registroFan = i;
	}
}
