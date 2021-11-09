package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Aluno;
import entidades.Grupo;
import utils.Constantes;

class TesteGrupo {

	Grupo grupo;

	@BeforeEach
	void setUp() throws Exception {
		grupo = new Grupo("Estudos", 3);
	}

	@Test
	void testAdicionaAluno() {

		ArrayList<Aluno> alunos = new ArrayList<>();
		alunos.add(new Aluno("123", "Welbber Vital", "Ciência Computação"));
		alunos.add(new Aluno("124", "João José", "Ciência Computação"));
		alunos.add(new Aluno("125", "Pedro Soares", "Engenharia Civil"));
		grupo.adicionaAluno(alunos.get(0));
		grupo.adicionaAluno(alunos.get(1));
		grupo.adicionaAluno(alunos.get(2));

		for (Aluno aluno : grupo.getAlunos())
			assertEquals(true, alunos.contains(aluno));
	}

	@Test
	void testLimiteGrupo() {
		grupo.adicionaAluno(new Aluno("123", "Welbber Vital", "Ciência Computação"));
		grupo.adicionaAluno(new Aluno("124", "João José", "Ciência Computação"));
		grupo.adicionaAluno(new Aluno("125", "Pedro Soares", "Engenharia Civil"));
		try {
			grupo.adicionaAluno(new Aluno("126", "Pedro Silva", "Engenharia Civil"));
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_GRUPO_CHEIO, e.getMessage());
		}
	}

	@Test
	void testInserirAlunoExistenteGrupo() {
		Aluno aluno = new Aluno("123", "Welbber Vital", "Ciência Computação");
		grupo.adicionaAluno(aluno);
		try {
			grupo.adicionaAluno(aluno);
		} catch (IllegalArgumentException e) {
			assertEquals(Constantes.MENSAGEM_ALUNO_JA_CADASTRADO_GRUPO, e.getMessage());
		}
	}

}
