package br.com.serrabank;

import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Menus {

	int opcaoCliente;
	double saldo = 0;
	int agencia = 0;
	String tipodeContaInserida = null;
	Scanner leitor = new Scanner(System.in);
	Dados dados = new Dados();
	LocalDateTime hoje = LocalDateTime.now();
	LocalTime agora = LocalTime.now();

	public void menuConta(Cliente clienteLogado, Double saldocliente, int agenciacliente, String tipo)
			throws InterruptedException, IOException {

		FileWriter arq = new FileWriter("C:\\temp\\clienteLogado.txt");
		PrintWriter gravarArq = new PrintWriter(arq);

		gravarArq.println("==============================");
		gravarArq.println("Cliente: " + clienteLogado.nome);
		gravarArq.println(
				" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute() + ":" + agora.getSecond());

		System.out.println("--------------------------------");
		System.out.println(" Bem-vindo, " + clienteLogado.nome);
		System.out.println(
				" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute() + ":" + agora.getSecond());
		System.out.println("--------------------------------");
		Thread.sleep(2000);

		if (tipo.equals("contapoupanca")) {
			contaPoupanca conta = new contaPoupanca(clienteLogado, saldocliente, agenciacliente, tipo);

			do {
				System.out.println("Opções de movimentação na conta: ");
				System.out.println("1 - Saque");
				System.out.println("2 - Depósito");
				System.out.println("3 - Transferência para outra conta");
				System.out.println("4 - Saldo");
				System.out.println("5 - Relatorio de tributação de conta corrente.");
				System.out.println("6 - Simulação de rendimento da conta poupança");
				System.out.println("7 - Seguro de vida.");
				if (clienteLogado instanceof Gerente) {
					System.out.println("8 - Menu Administrativo");
				}
				if (clienteLogado instanceof Diretor) {
					System.out.println("8 - Menu Administrativo");
				}
				if (clienteLogado instanceof Presidente) {
					System.out.println("8 - Menu Administrativo");
				}
				System.out.println("0 - Sair");

				opcaoCliente = leitor.nextInt();
				switch (opcaoCliente) {

				case 1:
					double saldo1 = conta.getSaldo();
					gravarArq.println("--> Opção selecionada: Saque.");
					System.out.printf("Seu saldo é: R$%.2f\n", conta.getSaldo());
					System.out.println("Quanto voce quer sacar? ");
					double valor = leitor.nextDouble();
					conta.sacar(valor);
					if (saldo1 == (conta.getSaldo() + valor + 0.10)) {
						gravarArq.printf("Valor sacado: R$%.2f\n", valor);
					}
					Thread.sleep(5000);
					continue;
				case 2:
					gravarArq.println("--> Opção selecionada: Deposito.");
					System.out.println("Digite a quantia a ser depositada.");
					double valordeposito = leitor.nextDouble();
					conta.depositar(valordeposito);
					gravarArq.printf("Valor depositado: R$%.2f\n", valordeposito);
					Thread.sleep(5000);
					continue;
				case 3:
					double valortransf = conta.getSaldo();
					System.out.println("Digite o cpf do destinatario: ");
					String cpfrecpetor = leitor.next();
					Cliente receptor = Dados.getClientePorCpf(cpfrecpetor);
					Double saldoreceptor = Dados.getContaCliente(cpfrecpetor, saldo);
					int agenciadocarinha = Dados.getAgencia(cpfrecpetor, agencia);
					String tipodocarinha = Dados.getTipoConta(cpfrecpetor, tipodeContaInserida);
					contaCorrente receber = new contaCorrente(receptor, saldoreceptor, agenciadocarinha, tipodocarinha);
					System.out.println("Digite a quantia: ");
					double valor2 = leitor.nextDouble();
					conta.transferencia(valor2, receber);
					if (valortransf == (valor2 + conta.getSaldo() + 0.20)) {
						gravarArq.println("--> Opção selecionada: Transferencia.");
						gravarArq.printf("O seu saldo é: R$%.2f\n", valortransf);
						gravarArq.printf("O valor a ser transferido é: R$%.2f\n", valor2);
						gravarArq.printf("Destinatario: R$%.2f\n", receber.getCpf().nome);
						gravarArq.printf("O seu saldo atual é: R$%.2f\n", conta.getSaldo());
					
					}

					Thread.sleep(5000);
					continue;
				case 4:
					System.out.println("--------------------------------");
					System.out.printf("Seu saldo é: R$%.2f\n", conta.getSaldo());
					System.out.println(" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
					System.out.println("--------------------------------");
					Thread.sleep(5000);
					continue;
				case 5:
					conta.tributosconta();
					System.out.println(" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
					System.out.println("--------------------------------");
					Thread.sleep(2000);
					continue;
				case 6:
					contaPoupanca.simularpoupanca();
					System.out.println("--------------------------------");
					System.out.println(" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
					System.out.println("--------------------------------");
					Thread.sleep(5000);
					continue;
				case 7:
					System.out.println("Digite o valor inicial do seguro: ");
					double valordoseguro = leitor.nextDouble();
					SeguroDeVida seg = new SeguroDeVida(valordoseguro);
					seg.getImprimeSegurodeVida(valordoseguro, conta.getSaldo());
					conta.setSaldo(conta.getSaldo() - valordoseguro);
					System.out.println("Seu saldo é: R$%.2f\n" + conta.getSaldo());
					Thread.sleep(5000);
					continue;
				case 8:
					if (clienteLogado instanceof Gerente) {
						System.out.println("1 - Relatorio de contas em uma agencia.");
						int opcaoadm = leitor.nextInt();
						switch (opcaoadm) {
						case 1:
							dados.relatorioGerente(agenciacliente);
							System.out.println("Relatorio gerado:");
							System.out.println(
									" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("------------------------------");
							Thread.sleep(5000);
							continue;
						default:
							System.out.println("--------------------------------");
							System.out.println("Opção invalida!");
							System.out.println(
									" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("--------------------------------");
							continue;
						}
					}
					if (clienteLogado instanceof Diretor) {
						System.out.println("Opções Administrativas: ");
						System.out.println("1 - Relatorio de contas em uma agencia.");
						System.out.println("2 - Relatorio de todos os usuarios.");
						int opcaoadm = leitor.nextInt();
						switch (opcaoadm) {
						case 1:
							System.out.println("Digite a agencia: ");
							int agenciaTemp = leitor.nextInt();
							dados.relatorioGerente(agenciaTemp);
							Thread.sleep(5000);
							System.out.println("Relatorio gerado:");
							System.out.println(
									" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("------------------------------");
							continue;
						case 2:
							dados.relatorioDiretor();
							System.out.println("Relatorio gerado:");
							System.out.println(
									" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("------------------------------");
							Thread.sleep(5000);
							continue;
						default:
							System.out.println("--------------------------------");
							System.out.println("Opção invalida!");
							System.out.println(
									" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("--------------------------------");
							continue;

						}
					}
					if (clienteLogado instanceof Presidente) {
						System.out.println("Opções Administrativas: ");
						System.out.println("1 - Relatorio de contas em uma agencia.");
						System.out.println("2 - Relatorio de todos os usuarios.");
						System.out.println("3 - Relatorio de todo capital no banco");
						int opcaoadm = leitor.nextInt();
						switch (opcaoadm) {
						case 1:
							System.out.println("Digite a agencia: ");
							int agenciaTemp = leitor.nextInt();
							dados.relatorioGerente(agenciaTemp);
							System.out.println("Relatorio gerado:");
							System.out.println(
									" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("------------------------------");
							Thread.sleep(5000);
							continue;
						case 2:
							dados.relatorioDiretor();
							System.out.println("Relatorio gerado:");
							System.out.println(
									" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("------------------------------");
							Thread.sleep(5000);

							continue;
						case 3:
							dados.relatorioPresidente();
							System.out.println("Relatorio gerado:");
							System.out.println(
									" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("------------------------------");
							Thread.sleep(5000);
							continue;
						default:
							System.out.println("--------------------------------");
							System.out.println("Opção invalida!");
							System.out.println(" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("--------------------------------");
							continue;
						}
					}
				case 0:
					System.out.println("--------------------------------");
					System.out.println("O Serra Bank te deseja um bom dia!");
					System.out.println("     " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
					System.out.println("     Serviço finalizado!");
					System.out.println("--------------------------------");
					gravarArq.println("     " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
					gravarArq.println("==============================");
					gravarArq.close();
					continue;
				default:
					System.out.println("--------------------------------");
					System.out.println("Opção invalida!");
					System.out.println(" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
					System.out.println("--------------------------------");
					break;
				}

			} while (opcaoCliente != 0);
			
		}
		if (tipo.equals("contacorrente")) {
			contaCorrente conta = new contaCorrente(clienteLogado, saldocliente, agenciacliente, tipo);
			do {
				System.out.println("Opções de movimentação na conta: ");
				System.out.println("1 - Saque");
				System.out.println("2 - Depósito");
				System.out.println("3 - Transferência para outra conta");
				System.out.println("4 - Saldo");
				System.out.println("5 - Relatorio de tributação de conta corrente.");
				System.out.println("6 - Seguro de vida.");
				if (clienteLogado instanceof Gerente) {
					System.out.println("7 - Menu Administrativo");
				}
				if (clienteLogado instanceof Diretor) {
					System.out.println("7 - Menu Administrativo");
				}
				if (clienteLogado instanceof Presidente) {
					System.out.println("7 - Menu Administrativo");
				}
				System.out.println("0 - Sair.");
				opcaoCliente = leitor.nextInt();
				switch (opcaoCliente) {

				case 1:
					double saldo1 = conta.getSaldo();
					gravarArq.println("--> Opção selecionada: Saque.");
					System.out.printf("Seu saldo é: R$%.2f\n", conta.getSaldo());
					System.out.println("Quanto voce quer sacar? ");
					double valor = leitor.nextDouble();
					conta.sacar(valor);
					if (saldo1 == (conta.getSaldo() + valor + 0.10)) {
						gravarArq.printf("Valor sacado: R$%.2f\n", valor);
					}
					Thread.sleep(5000);
					continue;
				case 2:
					gravarArq.println("--> Opção selecionada: Deposito.");
					System.out.println("Digite a quantia a ser depositada.");
					double valordeposito = leitor.nextDouble();
					conta.depositar(valordeposito);
					gravarArq.printf("Valor depositado: R$%.2f\n", valordeposito);
					Thread.sleep(5000);
					continue;
				case 3:
					double valortransf = conta.getSaldo();
					System.out.println("Digite o cpf do destinatario: ");
					String cpfrecpetor = leitor.next();
					Cliente receptor = Dados.getClientePorCpf(cpfrecpetor);
					Double saldoreceptor = Dados.getContaCliente(cpfrecpetor, saldo);
					int agenciadocarinha = Dados.getAgencia(cpfrecpetor, agencia);
					String tipodocarinha = Dados.getTipoConta(cpfrecpetor, tipodeContaInserida);
					contaCorrente receber = new contaCorrente(receptor, saldoreceptor, agenciadocarinha, tipodocarinha);
					System.out.println("Digite a quantia: ");
					double valor2 = leitor.nextDouble();
					conta.transferencia(valor2, receber);
					if (valortransf == (valor2 + conta.getSaldo() + 0.20)) {
						gravarArq.println("--> Opção selecionada: Transferencia.");
						gravarArq.printf("O seu saldo é: R$%.2f\n", valortransf);
						gravarArq.printf("O valor a ser transferido é: R$%.2f\n", valor2);
						gravarArq.println("Destinatario: " + receber.getCpf().nome);
						gravarArq.printf("O seu saldo atual é: R$%.2f\n", conta.getSaldo());
					}

					Thread.sleep(5000);
					continue;
				case 4:
					System.out.println("--------------------------------");
					System.out.printf("Seu saldo é: R$%.2f\n", conta.getSaldo());
					System.out.println(" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
					System.out.println("--------------------------------");
					Thread.sleep(5000);
					continue;
				case 5:
					conta.tributosconta();
					System.out.println(" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
					System.out.println("--------------------------------");
					Thread.sleep(2000);
					continue;
				case 6:
					System.out.println("Digite o valor inicial do seguro: ");
					double valordoseguro = leitor.nextDouble();
					SeguroDeVida seg = new SeguroDeVida(valordoseguro);
					seg.getImprimeSegurodeVida(valordoseguro, conta.getSaldo());
					conta.setSaldo(conta.getSaldo() - valordoseguro);
					System.out.printf("Seu saldo é: R$%.2f\n", conta.getSaldo());
					Thread.sleep(5000);
					continue;
				case 7:
					if (clienteLogado instanceof Gerente) {
						System.out.println("1 - Relatorio de contas em uma agencia.");
						int opcaoadm = leitor.nextInt();
						switch (opcaoadm) {
						case 1:
							dados.relatorioGerente(agenciacliente);
							System.out.println("Relatorio gerado:");
							System.out.println(
									" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("------------------------------");
							Thread.sleep(5000);
							continue;
						default:
							System.out.println("--------------------------------");
							System.out.println("Opção invalida!");
							System.out.println(" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("--------------------------------");
							continue;
						}
					}
					if (clienteLogado instanceof Diretor) {
						System.out.println("Opções Administrativas: ");
						System.out.println("1 - Relatorio de contas em uma agencia.");
						System.out.println("2 - Relatorio de todos os usuarios.");
						int opcaoadm = leitor.nextInt();
						switch (opcaoadm) {
						case 1:
							System.out.println("Digite a agencia: ");
							int agenciaTemp = leitor.nextInt();
							dados.relatorioGerente(agenciaTemp);
							System.out.println("Relatorio gerado:");
							System.out.println(" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("------------------------------");
							Thread.sleep(5000);
							continue;
						case 2:
							dados.relatorioDiretor();
							System.out.println("Relatorio gerado:");
							System.out.println(" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("------------------------------");
							Thread.sleep(5000);
							continue;
						default:
							System.out.println("--------------------------------");
							System.out.println("Opção invalida!");
							System.out.println(" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("--------------------------------");
							Thread.sleep(5000);
							continue;

						}
					}
					if (clienteLogado instanceof Presidente) {
						System.out.println("Opções Administrativas: ");
						System.out.println("1 - Relatorio de contas em uma agencia.");
						System.out.println("2 - Relatorio de todos os usuarios.");
						System.out.println("3 - Relatorio de todo capital no banco");
						int opcaoadm = leitor.nextInt();
						switch (opcaoadm) {
						case 1:
							System.out.println("Digite a agencia: ");
							int agenciaTemp = leitor.nextInt();
							dados.relatorioGerente(agenciaTemp);
							System.out.println("Relatorio gerado:");
							System.out.println(
									" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("------------------------------");
							Thread.sleep(5000);
							continue;
						case 2:
							dados.relatorioDiretor();
							System.out.println("Relatorio gerado:");
							System.out.println(
									" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("------------------------------");
							Thread.sleep(5000);
							continue;

						case 3:
							dados.relatorioPresidente();
							System.out.println("Relatorio gerado:");
							System.out.println(
									" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("------------------------------");
							Thread.sleep(5000);
							continue;
						default:
							System.out.println("--------------------------------");
							System.out.println("Opção invalida!");
							System.out.println(
									" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
							System.out.println("--------------------------------");
							continue;
						}
					}
				case 0:
					System.out.println("--------------------------------");
					System.out.println("O Serra Bank te deseja um bom dia!");
					System.out.println("     " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
					System.out.println("     Serviço finalizado!");
					System.out.println("--------------------------------");
					gravarArq.println("     " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
					gravarArq.println("==============================");
					gravarArq.close();
					continue;

				default:
					System.out.println("--------------------------------");
					System.out.println("Opção invalida!");
					System.out.println(" " + hoje.toLocalDate() + "   " + agora.getHour() + ":" + agora.getMinute());
					System.out.println("--------------------------------");
					gravarArq.close();
					break;
				}

			} while (opcaoCliente != 0);

		}
	}
}

