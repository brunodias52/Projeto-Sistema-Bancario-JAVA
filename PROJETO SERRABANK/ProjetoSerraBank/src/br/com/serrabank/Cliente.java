package br.com.serrabank;

public class Cliente {

	protected String nome;
	protected String cpf;
	protected String senha;
	protected Cargo tipocargo;

	public Cliente(Cargo tipocargoInserido, String nomeInserido, String cpfInserido, String senhaInserida) {
		super();
		this.nome = nomeInserido;
		this.cpf = cpfInserido;
		this.senha = senhaInserida;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeInserido) {
		this.nome = nomeInserido;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpfInserido) {
		this.cpf = cpfInserido;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senhaInserida) {
		this.senha = senhaInserida;
	}

}
