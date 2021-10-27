package br.com.serrabank;

public enum Cargo {

	CLIENTE(1), GERENTE(2), DIRETOR(3), PRESIDENTE(4);

	protected int idcargo;

	public int getIdcargo() {
		return idcargo;
	}

	public void setIdcargo(int idcargoInserido) {
		this.idcargo = idcargoInserido;
	}

	private Cargo(int idcargoInserido) {
		this.idcargo = idcargoInserido;
	}

}
