package br.com.serrabank;

public class SeguroDeVida {

	private double TRIBUTO_SEGURO = 0.2;
	private double valorSegurado;
	private double valorTaxado;

	public SeguroDeVida(double valorInserido) {
		if (valorInserido > 0) {
			this.valorSegurado = debitaTaxa(valorInserido);
			this.valorTaxado = valorTaxado(valorInserido);

		} else {
			System.out.println("Não é possivel contratar seguro para o valor informado!");
		}
	}

	public double getValorSegurado() {
		return this.valorSegurado;
	}

	public double getValorTaxado() {
		return this.valorTaxado;
	}

	private double debitaTaxa(double valorInserido) {
		return valorInserido - (valorInserido * TRIBUTO_SEGURO);
	}

	private double valorTaxado(double valorInserido) {
		return valorInserido * TRIBUTO_SEGURO;
	}

	public boolean getImprimeSegurodeVida(double valorInserido, double saldoInserido) {
		if ((saldoInserido - valorInserido) >= 0) {
			saldoInserido -= valorInserido;
			saldoInserido -= 0.10;

			System.out.println("-----------------------------");
			System.out.println("Seguro contratado com sucesso!");
			System.out.println("Valor do seguro: " + valorSegurado);
			System.out.println("Taxa de contratação do seguro: " + valorTaxado);
			System.out.println("-----------------------------");

			return true;
		} else {
			System.out.println("-------------------");
			System.out.println("   Saldo insuficiente!");
			System.out.println("Para contratação do serviço");
			System.out.println("-------------------");
			return false;
		}
	}
}
