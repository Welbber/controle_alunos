package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Aluno;

class TesteAluno {
	
	Aluno aluno;
	
	@BeforeEach
	void setUp() throws Exception {
		aluno = new Aluno("123", "Welbber Vital", "Ciência Computação");
	}

	@Test
	void testToString() {
		assertEquals("Aluno: 123 - Welbber Vital - Ciência Computação", aluno.toString());
	}

}
