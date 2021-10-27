package br.com.serrabank;

public abstract class Funcionario extends Cliente {
	protected Cargo tipocargo;

	public Funcionario(Cargo tipocargoInserido, String nomeInserido, String cpfInserido, String senhaInserida) {
		super(tipocargoInserido, nomeInserido, cpfInserido, senhaInserida);

	}
}
