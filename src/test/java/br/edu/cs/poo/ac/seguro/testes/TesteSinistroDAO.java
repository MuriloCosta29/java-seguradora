package br.edu.cs.poo.ac.seguro.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.daos.SinistroDAO;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;
import br.edu.cs.poo.ac.seguro.entidades.TipoSinistro;

public class TesteSinistroDAO extends TesteDAO {
	private SinistroDAO dao = new SinistroDAO();
	protected Class getClasse() {
		return Sinistro.class;
	}

	@Test
	public void teste01() {
		String numero = "00000000";
		cadastro.incluir(new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
				"USUARIO1", new BigDecimal("1000.00"), TipoSinistro.COLISAO), numero);
		Sinistro si = dao.buscar(numero);
		Assertions.assertNotNull(si);
	}
	@Test
	public void teste02() {
		String numero = "10000000";
		cadastro.incluir(new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
				"USUARIO2", new BigDecimal("1001.00"), TipoSinistro.COLISAO), numero);
		Sinistro si = dao.buscar("11000000");
		Assertions.assertNull(si);
	}
	@Test
	public void teste03() {
		String numero = "20000000";
		cadastro.incluir(new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
				"USUARIO3", new BigDecimal("1002.00"), TipoSinistro.COLISAO), numero);
		boolean ret = dao.excluir(numero);
		Assertions.assertTrue(ret);
	}
	@Test
	public void teste04() {
		String numero = "30000000";
		cadastro.incluir(new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
				"USUARIO4", new BigDecimal("1003.00"), TipoSinistro.COLISAO), numero);
		boolean ret = dao.excluir("31000000");
		Assertions.assertFalse(ret);
	}
	@Test
	public void teste05() {
		String numero = "40000000";
		boolean ret = dao.incluir(new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
				"USUARIO5", new BigDecimal("1004.00"), TipoSinistro.COLISAO));
		Assertions.assertTrue(ret);
		Sinistro si = dao.buscar(numero);
		Assertions.assertNotNull(si);
	}

	@Test
	public void teste06() {
		String numero = "50000000";
		Sinistro si = new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
				"USUARIO6", new BigDecimal("1005.00"), TipoSinistro.COLISAO);
		cadastro.incluir(si, numero);
		boolean ret = dao.incluir(si);
		Assertions.assertFalse(ret);
	}
	@Test
	public void teste07() {
		String numero = "60000000";
		boolean ret = dao.alterar(new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
				"USUARIO7", new BigDecimal("1006.00"), TipoSinistro.COLISAO));
		Assertions.assertFalse(ret);
		Sinistro si = dao.buscar(numero);
		Assertions.assertNull(si);
	}

	@Test
	public void teste08() {
		String numero = "70000000";
		Sinistro si = new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
				"USUARIO8", new BigDecimal("1007.00"), TipoSinistro.COLISAO);
		cadastro.incluir(si, numero);
		si = new Sinistro(numero, null, LocalDateTime.now(), LocalDateTime.now(),
				"USUARIO9", new BigDecimal("1008.00"), TipoSinistro.FURTO);
		boolean ret = dao.alterar(si);
		Assertions.assertTrue(ret);
	}
}
