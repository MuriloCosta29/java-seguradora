package br.edu.cs.poo.ac.seguro.testes;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.daos.ApoliceDAO;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;

public class TesteApoliceDAO extends TesteDAO {
	private ApoliceDAO dao = new ApoliceDAO();
	protected Class getClasse() {
		return Apolice.class;
	}

	// Apolice nao recebe "numero" no construtor (exigencia da especificacao),
	// entao esse helper monta a apolice e atribui o numero via setNumero,
	// igual seria feito de verdade por quem emite a apolice.
	private Apolice criar(String numero, BigDecimal valorPremio) {
		Apolice ap = new Apolice(null, new BigDecimal("500.00"), valorPremio, new BigDecimal("50000.00"));
		ap.setNumero(numero);
		return ap;
	}

	@Test
	public void teste01() {
		String numero = "00000000";
		Apolice ap = criar(numero, new BigDecimal("1000.00"));
		cadastro.incluir(ap, numero);
		Apolice apBuscada = dao.buscar(numero);
		Assertions.assertNotNull(apBuscada);
	}
	@Test
	public void teste02() {
		String numero = "10000000";
		Apolice ap = criar(numero, new BigDecimal("1001.00"));
		cadastro.incluir(ap, numero);
		Apolice apBuscada = dao.buscar("11000000");
		Assertions.assertNull(apBuscada);
	}
	@Test
	public void teste03() {
		String numero = "20000000";
		Apolice ap = criar(numero, new BigDecimal("1002.00"));
		cadastro.incluir(ap, numero);
		boolean ret = dao.excluir(numero);
		Assertions.assertTrue(ret);
	}
	@Test
	public void teste04() {
		String numero = "30000000";
		Apolice ap = criar(numero, new BigDecimal("1003.00"));
		cadastro.incluir(ap, numero);
		boolean ret = dao.excluir("31000000");
		Assertions.assertFalse(ret);
	}
	@Test
	public void teste05() {
		String numero = "40000000";
		Apolice ap = criar(numero, new BigDecimal("1004.00"));
		boolean ret = dao.incluir(ap);
		Assertions.assertTrue(ret);
		Apolice apBuscada = dao.buscar(numero);
		Assertions.assertNotNull(apBuscada);
	}

	@Test
	public void teste06() {
		String numero = "50000000";
		Apolice ap = criar(numero, new BigDecimal("1005.00"));
		cadastro.incluir(ap, numero);
		boolean ret = dao.incluir(ap);
		Assertions.assertFalse(ret);
	}
	@Test
	public void teste07() {
		String numero = "60000000";
		Apolice ap = criar(numero, new BigDecimal("1006.00"));
		boolean ret = dao.alterar(ap);
		Assertions.assertFalse(ret);
		Apolice apBuscada = dao.buscar(numero);
		Assertions.assertNull(apBuscada);
	}

	@Test
	public void teste08() {
		String numero = "70000000";
		Apolice ap = criar(numero, new BigDecimal("1007.00"));
		cadastro.incluir(ap, numero);
		Apolice apAlterada = criar(numero, new BigDecimal("1008.00"));
		boolean ret = dao.alterar(apAlterada);
		Assertions.assertTrue(ret);
	}
}
