package br.com.serrabank;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Dados {

	static List<Cliente> listaCliente = new ArrayList<Cliente>();
	static List<Conta> listaConta = new ArrayList<Conta>();

	public void puxardados() {

		File arquivo = new File("C:\\temp\\dadosgerais.txt");

		if (arquivo.exists()) {

			try {

				Scanner leitor = new Scanner(arquivo);
				leitor.useDelimiter(";");

				while (leitor.hasNext()) {
					String tipousuarioInserido = leitor.next();
					String nomeInserido = leitor.next();
					String cpfInserido = leitor.next();
					String senhaInserida = leitor.next();
					double saldoInserido = leitor.nextDouble();
					int agenciaInserida = leitor.nextInt();
					String tipodecontaInserida = leitor.nextLine().substring(1);

					switch (tipousuarioInserido) {

					case "CLIENTE":

						Cliente usuario = new Cliente(Cargo.CLIENTE, nomeInserido, cpfInserido, senhaInserida);
						listaCliente.add(usuario);
						if (tipodecontaInserida.equals("contacorrente")) {

							listaConta.add(
									new contaCorrente(usuario, saldoInserido, agenciaInserida, tipodecontaInserida));

						} else if (tipodecontaInserida.equals("contapoupanca")) {
							listaConta.add(
									new contaPoupanca(usuario, saldoInserido, agenciaInserida, tipodecontaInserida));

						}
						break;
					case "GERENTE":

						Gerente gerente = new Gerente(Cargo.GERENTE, nomeInserido, cpfInserido, senhaInserida,
								agenciaInserida);
						listaCliente.add(gerente);
						if (tipodecontaInserida.equals("contacorrente")) {

							listaConta.add(
									new contaCorrente(gerente, saldoInserido, agenciaInserida, tipodecontaInserida));

						} else if (tipodecontaInserida.equals("contapoupanca")) {
							listaConta.add(
									new contaPoupanca(gerente, saldoInserido, agenciaInserida, tipodecontaInserida));

						}
						break;
					case "DIRETOR":

						Diretor diretor = new Diretor(Cargo.DIRETOR, nomeInserido, cpfInserido, senhaInserida);
						listaCliente.add(diretor);
						if (tipodecontaInserida.equals("contacorrente")) {
							listaConta.add(
									new contaCorrente(diretor, saldoInserido, agenciaInserida, tipodecontaInserida));

						} else if (tipodecontaInserida.equals("contapoupanca")) {
							listaConta.add(
									new contaPoupanca(diretor, saldoInserido, agenciaInserida, tipodecontaInserida));

						}
						break;
					case "PRESIDENTE":
						Presidente presidente = new Presidente(Cargo.PRESIDENTE, nomeInserido, cpfInserido,
								senhaInserida);
						listaCliente.add(presidente);
						if (tipodecontaInserida.equals("contacorrente")) {
							listaConta.add(
									new contaCorrente(presidente, saldoInserido, agenciaInserida, tipodecontaInserida));

						} else if (tipodecontaInserida.equals("contapoupanca")) {
							listaConta.add(
									new contaPoupanca(presidente, saldoInserido, agenciaInserida, tipodecontaInserida));

						}
						break;

					default:
						throw new IllegalArgumentException("Valor referente ao cargo não existe.");

					}

				}
				leitor.close();

			} catch (Exception e) {
				System.out.println("Erro ao inserir os dados.");
			}

		}

	}

	public static Cliente getClientePorCpf(String cpfInserido) {
		for (Cliente clienteAtual : listaCliente) {
			if (cpfInserido.equals(clienteAtual.cpf)) {
				return clienteAtual;
			}
		}
		return null;
	}

	public static Cliente logar(String cpfInserido, String senhaInserida) throws IOException, Exception {
		Cliente clienteTemp = getClientePorCpf(cpfInserido);
		if (clienteTemp.equals(null)) {
			System.out.println("Registro não encontrado.");
			return null;
		}
		if (senhaInserida.equals(clienteTemp.senha)) {
			return clienteTemp;

		}
		System.out.println("Senha incorreta");
		return null;
	}

	public static Double getContaCliente(String cpfInserido, double saldoInserido) {
		Cliente clienteAtual = getClientePorCpf(cpfInserido);
		for (Conta contaCliente : listaConta) {
			if (contaCliente.getCpf().equals(clienteAtual)) {
				saldoInserido = contaCliente.getSaldo();
				return saldoInserido;
			}

		}
		return (double) 0;
	}

	public static int getAgencia(String cpfInserido, int agenciaInserida) {
		Cliente clienteAtual = getClientePorCpf(cpfInserido);
		for (Conta contaCliente : listaConta) {
			if (contaCliente.getCpf().equals(clienteAtual)) {
				agenciaInserida = contaCliente.getAgencia();
				return agenciaInserida;
			}

		}
		return 0;
	}

	public static String getTipoConta(String cpfInserido, String tipodeContaInserida) {

		Cliente clienteAtual = getClientePorCpf(cpfInserido);
		for (Conta contaCliente : listaConta) {
			if (contaCliente.getCpf().equals(clienteAtual)) {
				tipodeContaInserida = contaCliente.getTipodeConta();
				return tipodeContaInserida;
			}

		}
		return null;
	}

	public void relatorioGerente(int agenciaInserida) throws IOException {
		FileWriter arq = new FileWriter("C:\\temp\\relatorioGerente.txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		for (Conta contaCliente : listaConta) {
			if (contaCliente.getAgencia() == agenciaInserida) {
				String contaTemp = contaCliente.getTipodeConta();
				Cliente cpfTemp = contaCliente.getCpf();

				System.out.println("Cliente: " + cpfTemp.getNome());
				System.out.println("Cpf: " + cpfTemp.getCpf());
				System.out.println("Tipo de Conta: " + contaTemp);
				System.out.println("------------------------------");

				gravarArq.println("-----------------------------");
				gravarArq.println("Cliente: " + cpfTemp.getNome());
				gravarArq.println("Cpf: " + cpfTemp.getCpf());
				gravarArq.println("Tipo de Conta: " + contaTemp);
				gravarArq.println("Agencia: " + agenciaInserida);
				gravarArq.println("------------------------------");

			}
		}
		gravarArq.close();
	}

	public void relatorioDiretor() throws IOException {
		Collections.sort(listaCliente, new comparador());
		FileWriter arq = new FileWriter("C:\\temp\\relatorioDiretor.txt");
		PrintWriter gravarArq = new PrintWriter(arq);

		for (Cliente ordenar : listaCliente) {
			System.out.println("------------------------------");
			System.out.println("NOME: " + ordenar.nome);
			System.out.println("CPF: " + ordenar.cpf);

			gravarArq.println("-----------------------------");
			gravarArq.println("NOME: " + ordenar.nome);
			gravarArq.println("CPF: " + ordenar.cpf);

			for (Conta contaCliente : listaConta) {
				String cpf = contaCliente.getCpf().cpf;
				if (ordenar.cpf.equals(cpf)) {
					int agenciaSerial = contaCliente.getAgencia();
					System.out.println("Agencia: 00" + agenciaSerial);

					gravarArq.println("Agencia: 00" + agenciaSerial);
					gravarArq.println("------------------------------");

				}
			}
		}
		gravarArq.close();
	}

	public void relatorioPresidente() throws IOException {
		double saldoTotal = 0;

		FileWriter arq = new FileWriter("C:\\temp\\relatorioPresidente.txt");
		PrintWriter gravarArq = new PrintWriter(arq);

		for (Conta contaCliente : listaConta) {
			double saldoTemp = contaCliente.getSaldo();
			saldoTotal = saldoTemp + saldoTotal;
		}

		gravarArq.println("-----------------------------------------");
		gravarArq.println("O capital total no banco é: " + saldoTotal);
		gravarArq.println("-----------------------------------------");
		gravarArq.close();

		System.out.println("-----------------------------------------");
		System.out.println("O capital total no banco é: " + saldoTotal);
		System.out.println("-----------------------------------------");
	}

}
