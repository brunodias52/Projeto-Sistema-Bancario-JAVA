package br.com.serrabank;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public abstract class Conta {

	private Cliente cpfcliente;
	private double saldo;
	private int agencia;
	private String tipodeConta;
	private double tributos = 0;

	public Conta(Cliente cpfclienteInserido, double saldoInserido, int agenciaInserida, String tipodeContaInserida) {
		super();
		this.cpfcliente = cpfclienteInserido;
		this.saldo = saldoInserido;
		this.agencia = agenciaInserida;
		this.tipodeConta = tipodeContaInserida;
	}

	Scanner leitor = new Scanner(System.in);
	LocalDateTime hoje = LocalDateTime.now();
	LocalTime agora = LocalTime.now();

	public Cliente getCpf() {
		return cpfcliente;
	}

	public void setCpf(Cliente cpfclienteInserido) {
		this.cpfcliente = cpfclienteInserido;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldoInserido) {
		this.saldo = saldoInserido;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agenciaInserida) {
		this.agencia = agenciaInserida;
	}

	public String getTipodeConta() {
		return tipodeConta;
	}
	
	public void setTipodeConta(String tipodeContaInserida) {
		this.tipodeConta = tipodeContaInserida;
	}

	public void depositar(double valorInserido) {
		saldo += valorInserido;
		saldo -= 0.10;
		tributos += 0.10;

		System.out.println("----------------------------");
		System.out.printf("Saldo anterior: R$%.2f\n", (saldo - valorInserido));
		System.out.printf("Valor Depositado: R$%.2f\n", valorInserido);
		System.out.printf("Saldo atual: R$%.2f\n", saldo);
		System.out.println("-----------------------------");

	}

	public boolean sacar(double valorInserido) {
		if ((saldo - valorInserido) >= 0) {
			saldo -= valorInserido;
			saldo -= 0.10;
			tributos += 0.10;

			System.out.println("-----------------------------");
			System.out.printf("Saldo anterior: R$%.2f\n", (saldo + valorInserido));
			System.out.printf("Valor do saque: R$%.2f\n", valorInserido);
			System.out.printf("Saldo atual: R$%.2f\n", saldo);
			System.out.println("-----------------------------");

			return true;
		} else {
			System.out.println("-------------------");
			System.out.println("Saldo insuficiente!");
			System.out.println("-------------------");
			return false;
		}
	}

	public boolean transferencia(double valorInserido, Conta destinatario) {
		if ((saldo - valorInserido - 0.20) >= 0) {
			saldo -= valorInserido;
			destinatario.saldo += valorInserido;
			saldo = saldo - 0.20;
			tributos += 0.20;

			System.out.println("-----------------------------");
			System.out.printf("O valor a ser transferido é: R$%.2f\n", valorInserido);
			System.out.println("Destinatario: " + destinatario.getCpf().nome);
			System.out.printf("O seu saldo atual é: R$%.2f\n", saldo);
			System.out.println("-----------------------------");

			return true;
		} else {
			System.out.println("-------------------");
			System.out.println("Saldo insuficiente!");
			System.out.println("-------------------");
			return false;
		}
	}

	public void tributosconta() throws IOException {

		FileWriter arq = new FileWriter("C:\\temp\\tributos.txt");
		PrintWriter gravarArq = new PrintWriter(arq);

		gravarArq.println("--------------------------------------------------------");
		gravarArq.printf("O total de tributos que voce pagou foi de: R$%.2f\n", tributos);
		gravarArq.println("--------------------------------------------------------");
		gravarArq.close();

		System.out.println("--------------------------------------------------------");
		System.out.printf("O total de tributos que voce pagou foi de: R$%.2f\n", tributos);
		System.out.println("--------------------------------------------------------");

	}

}
