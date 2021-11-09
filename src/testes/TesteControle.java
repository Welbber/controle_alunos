package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controle.Controle;
import entidades.Aluno;
import entidades.Grupo;
import utils.Constantes;

class TesteControle {
	Controle controle;

	@BeforeEach
	void setUp() throws Exception {
		controle = new Controle();
	}

	@Test
	void testCadastraAluno() {
		controle.cadastraAluno("123", "welbber vital", "Ciência da Computação");
		controle.cadastraAluno("12344", "João José", "Ciência da Computação");
		assertEquals("12344", controle.retornaAluno("12344").getMatricula());
		assertEquals("123", controle.retornaAluno("123").getMatricula());
	}

	@Test
	void testCadastraAlunoMastriculaRepetida() {
		controle.cadastraAluno("123", "welbber vital", "Ciência da Computação");
		try {
			controle.cadastraAluno("123", "João José", "Ciência da Computação");
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_MATRICULA_EXISTENTE, e.getMessage());
		}
	}

	@Test
	void testCadastraAlunoCampoInvalidado() {
		controle.cadastraAluno("123", "welbber vital", "Ciência da Computação");
		try {
			controle.cadastraAluno("123", "João José.", "Ciência da Computação");
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_SOMENTE_LETRAS, e.getMessage());
		}
		try {
			controle.cadastraAluno("123", "João José1", "Ciência da Computação");
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_SOMENTE_LETRAS, e.getMessage());
		}
		try {
			controle.cadastraAluno("123", "João.José", "Ciência da Computação");
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_SOMENTE_LETRAS, e.getMessage());
		}
		try {
			controle.cadastraAluno("123a", "João José", "Ciência da Computação");
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_SOMENTE_NUMEROS, e.getMessage());
		}
		try {
			controle.cadastraAluno("123.", "João José", "Ciência da Computação");
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_SOMENTE_NUMEROS, e.getMessage());
		}
		try {
			controle.cadastraAluno("123", "João José", "Ciência da Computação1");
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_SOMENTE_LETRAS, e.getMessage());
		}
		try {
			controle.cadastraAluno("123", "João José", "Ciência da Computação.");
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_SOMENTE_LETRAS, e.getMessage());
		}
	}

	@Test
	void testCadastraAlunoCampoNulo() {
		controle.cadastraAluno("123", "welbber vital", "Ciência da Computação");
		try {
			controle.cadastraAluno("123", null, "Ciência da Computação");
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
		try {
			controle.cadastraAluno(null, "welbber vital", "Ciência da Computação");
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
		try {
			controle.cadastraAluno("123", "welbber vital", null);
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
		
		try {
			controle.cadastraAluno("  ", "welbber vital", "Ciência Computação");
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
		
		try {
			controle.cadastraAluno("123", "   ", "Ciência Computação");
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
		
		try {
			controle.cadastraAluno("123", "welbber vital", "  ");
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
	}

	@Test
	void testRetornaAluno() {
		controle.cadastraAluno("123", "Welbber Vital", "Ciência Computação");
		try {
			controle.retornaAluno("12345");
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_MATRICULA_NAO_EXISTENTE, e.getMessage());
		}
		
		try {
			controle.retornaAluno("  ");
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
		
		assertEquals("123", controle.retornaAluno("123").getMatricula());
	}

	@Test
	void testCadastraGrupo() {
		controle.cadastraGrupo("Estudos", 10);

		try {
			controle.cadastraGrupo("Estudos", 10);
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_GRUPO_EXISTENTE, e.getMessage());
		}

		try {
			controle.cadastraGrupo("Prog", -1);
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_TAMANHO_GRUPO_INVALIDO, e.getMessage());
		}

		try {
			controle.cadastraGrupo(null, 10);
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
		
		try {
			controle.cadastraGrupo("   ", 10);
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
	}

	@Test
	void testAlocaAluno() {
		controle.cadastraAluno("123", "Welbber Vital", "Ciência da Computação");
		controle.cadastraGrupo("Estudos", 10);
		
		try {
			controle.alocaAluno("1234", "Estudos");
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_MATRICULA_NAO_EXISTENTE, e.getMessage());
		}
		try {
			controle.alocaAluno("123", "Estudoss");
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_GRUPO_NAO_EXISTENTE, e.getMessage());
		}
		try {
			controle.alocaAluno(null, "Estudoss");
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
		
		try {
			controle.alocaAluno("123", null);
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
		
		try {
			controle.alocaAluno("123", "   ");
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
		
		try {
			controle.alocaAluno("   ", "Estudos");
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
	}

	@Test
	void testPertinenciaGrupo() {
		controle.cadastraGrupo("Estudos", 10);
		controle.cadastraAluno("123", "Welbber Vital", "Ciência da Computação");
		controle.alocaAluno("123", "Estudos");

		try {
			controle.pertinenciaGrupo("123", "Estudosss");
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_GRUPO_NAO_EXISTENTE, e.getMessage());
		}
		try {
			controle.pertinenciaGrupo("1234", "Estudos");
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_MATRICULA_NAO_EXISTENTE, e.getMessage());
		}
		assertEquals(true, controle.pertinenciaGrupo("123", "Estudos"));
	}

	@Test
	void testCadastraAlunoRespondeQuadro() {
		controle.cadastraAluno("123", "Welbber Vital", "Ciência da Computação");
		
		try {
			controle.cadastraAlunoRespondeQuadro("1234");
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_MATRICULA_NAO_EXISTENTE, e.getMessage());
		}
		
		try {
			controle.cadastraAlunoRespondeQuadro(null);
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
		
		try {
			controle.cadastraAlunoRespondeQuadro("    ");
		} catch (NullPointerException e) {
			assertEquals(Constantes.MENSAGEM_CAMPO_VAZIO, e.getMessage());
		}
		
		controle.cadastraAlunoRespondeQuadro("123");
	}

	@Test
	void testGetAlunosRespondemQuadro() {
		controle.cadastraAluno("123", "Welbber Vital", "Ciência da Computação");
		
		Aluno alunoTeste = controle.retornaAluno("123");
		
		controle.cadastraAlunoRespondeQuadro("123");
		
		for(Aluno aluno : controle.getAlunosRespondemQuadro())
			assertEquals(true, aluno.equals(alunoTeste));
		
	}

	@Test
	void testGruposAlunoEsta() {
		controle.cadastraGrupo("Estudos", 10);
		controle.cadastraAluno("123", "Welbber Vital", "Ciência da Computação");
		controle.alocaAluno("123", "Estudos");
		
		for(Grupo grupo : controle.gruposAlunoEsta("123"))
			assertEquals("Estudos", grupo.getNome());
	}

}
