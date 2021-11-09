package entidades;

public class Aluno {

	private String matricula;

	private String nome;

	private String curso;

	public Aluno(String matricula, String nome, String curso) {
		this.matricula = matricula;
		this.nome = nome;
		this.curso = curso;
	}

	/**
	 * metodo que retorna informacoes do aluno formata
	 */
	@Override
	public String toString() {
		return String.format("Aluno: %s - %s - %s", this.matricula, this.nome, this.curso);
	}

	public String getMatricula() {
		return matricula;
	}
}
