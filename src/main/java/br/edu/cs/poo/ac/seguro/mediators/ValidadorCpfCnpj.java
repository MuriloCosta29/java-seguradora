package br.edu.cs.poo.ac.seguro.mediators;

public class ValidadorCpfCnpj {
	public static boolean ehCnpjValido(String cnpj) {
		if (cnpj == null || cnpj.length() != 14 || !StringUtils.temSomenteNumeros(cnpj)) {
			return false;
		}
		int[] d = new int[14];
		for (int i = 0; i < 14; i++) {
			d[i] = cnpj.charAt(i) - '0';
		}
		int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int soma = 0;
		for (int i = 0; i < 12; i++) {
			soma += d[i] * pesos1[i];
		}
		int resto = soma % 11;
		int dv1 = (resto < 2) ? 0 : 11 - resto;
		if (dv1 != d[12]) {
			return false;
		}
		int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		soma = 0;
		for (int i = 0; i < 13; i++) {
			soma += d[i] * pesos2[i];
		}
		resto = soma % 11;
		int dv2 = (resto < 2) ? 0 : 11 - resto;
		return dv2 == d[13];
	}
	public static boolean ehCpfValido(String cpf) {
		if (cpf == null || cpf.length() != 11 || !StringUtils.temSomenteNumeros(cpf)) {
			return false;
		}
		int[] d = new int[11];
		for (int i = 0; i < 11; i++) {
			d[i] = cpf.charAt(i) - '0';
		}
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += d[i] * (10 - i);
		}
		int resto = soma % 11;
		int dv1 = (resto < 2) ? 0 : 11 - resto;
		if (dv1 != d[9]) {
			return false;
		}
		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += d[i] * (11 - i);
		}
		resto = soma % 11;
		int dv2 = (resto < 2) ? 0 : 11 - resto;
		return dv2 == d[10];
	}
}
