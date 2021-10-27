package br.com.serrabank;

import java.io.IOException;
import java.util.Scanner;

public class Sistemainterno {

	public static void main(String[] args) throws Exception, IOException {

		Scanner leitor = new Scanner(System.in);
		double saldoInserido = 0;
		int agenciaInserida = 0;
		String tipodeContaInserida = null;
		Menus menu = new Menus();
		Dados dados = new Dados();
		dados.puxardados();

		boolean isNull = false;

		do {
			try {
				System.out.println("--------- Bem Vindo ao Serra Bank! ---------");
				System.out.println("Digite seu cpf: ");
				String cpfTemp = leitor.next();

				System.out.println("Digite sua senha: ");
				String senhaInserida = leitor.next();
				Cliente clienteLogado = Dados.logar(cpfTemp, senhaInserida);

				if (clienteLogado.equals(null)) {
					isNull = true;
				} else {
					isNull = false;
				}

				Double saldocliente = Dados.getContaCliente(cpfTemp, saldoInserido);
				int agenciacliente = Dados.getAgencia(cpfTemp, agenciaInserida);
				String tipo = Dados.getTipoConta(cpfTemp, tipodeContaInserida);

				menu.menuConta(clienteLogado, saldocliente, agenciacliente, tipo);
			} catch (NullPointerException e) {
				System.err.println("\nCPF ou Senha incorreto.\n");
			}

		} while (isNull != true);

	}
}
