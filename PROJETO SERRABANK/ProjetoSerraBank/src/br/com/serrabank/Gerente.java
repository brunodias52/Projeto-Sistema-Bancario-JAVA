package br.com.serrabank;

public class Gerente extends Funcionario {

	private int idagencia;

	public Gerente(Cargo tipocargoInserido, String nomeInserido, String cpfInserido, String senhaInserida, int idagenciaInserida) {
		super(tipocargoInserido,nomeInserido, cpfInserido, senhaInserida);
		this.idagencia = idagenciaInserida;

	}

}
