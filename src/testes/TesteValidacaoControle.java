package testes;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Aluno;
import entidades.Grupo;
import utils.Constantes;
import validacao.TiposCampo;
import validacao.ValidacaoControle;

class TesteValidacaoControle {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testValidaCampoNumerico() {
		ValidacaoControle.validaCampo("123", TiposCampo.NUMERICO);

		try {
			ValidacaoControle.validaCampo("123a", TiposCampo.NUMERICO);
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_SOMENTE_NUMEROS, e.getMessage());
		}
	}

	@Test
	void testValidaCampoAlfabeto() {
		ValidacaoControle.validaCampo("Welbber Vital", TiposCampo.ALFABETO);
		ValidacaoControle.validaCampo("Programção II", TiposCampo.ALFABETO);
		ValidacaoControle.validaCampo("Teste", TiposCampo.ALFABETO);
		try {
			ValidacaoControle.validaCampo("Test3ndo a validação", TiposCampo.ALFABETO);
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_SOMENTE_LETRAS, e.getMessage());
		}
	}

	@Test
	void testValidaCampoAlfabetoCaracteres() {
		ValidacaoControle.validaCampo("Welbber Vital", TiposCampo.ALFABETO);
		ValidacaoControle.validaCampo("Programção II", TiposCampo.ALFABETO);
		ValidacaoControle.validaCampo("Teste", TiposCampo.ALFABETO);
		try {
			ValidacaoControle.validaCampo("Testendo a validação.", TiposCampo.ALFABETO);
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_SOMENTE_LETRAS, e.getMessage());
		}
	}

	@Test
	void testValidaCampoVazio() {
		ValidacaoControle.validaCampo("Welbber Vital", TiposCampo.ALFABETO);
		try {
			ValidacaoControle.validaCampo("       ", TiposCampo.ALFABETO);
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
	}

	@Test
	void testValidaCampoNulo() {
		ValidacaoControle.validaCampo("Welbber Vital", TiposCampo.ALFABETO);
		try {
			ValidacaoControle.validaCampo(null, TiposCampo.ALFABETO);
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
	}

	@Test
	void testValidaExistenciaGrupo() {
		Grupo grupo = new Grupo("Estudos", 5);
		HashMap<String, Grupo> grupos = new HashMap<String, Grupo>();
		grupos.put(grupo.getNome(), grupo);
		try {
			ValidacaoControle.validaExistenciaGrupo(grupos, grupo.getNome(), false);
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_GRUPO_EXISTENTE, e.getMessage());
		}
	}

	@Test
	void testValidaExistenciaAluno() {
		Aluno aluno = new Aluno("123", "Welbber Vital", "Ciência computação");
		HashMap<String, Aluno> alunos = new HashMap<String, Aluno>();
		alunos.put("123", aluno);
		try {
			ValidacaoControle.validaExistenciaAluno(alunos, "123", false);
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_MATRICULA_EXISTENTE, e.getMessage());
		}
	}

	@Test
	void testAlunoJarespondeuQuadro() {
		Aluno aluno = new Aluno("123", "Welbber Vital", "Ciência computação");
		HashSet<Aluno> alunos = new HashSet<Aluno>();
		alunos.add(aluno);
		try {
			ValidacaoControle.alunoJarespondeuQuadro(alunos, aluno);
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_ALUNO_RESPONDEU_QUADRO, e.getMessage());
		}
	}

}
