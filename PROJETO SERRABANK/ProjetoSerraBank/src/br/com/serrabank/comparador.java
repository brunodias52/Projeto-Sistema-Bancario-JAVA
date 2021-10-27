package br.com.serrabank;

import java.util.Comparator;

public class comparador implements Comparator<Cliente> {
	public int compare(Cliente cliente1, Cliente cliente2) {
		if (cliente1.getNome().compareTo(cliente2.getNome()) > 0) {

			return 1;
		}
		return -1;

	}
}
