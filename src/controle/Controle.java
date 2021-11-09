package controle;

import entidades.Aluno;
import entidades.Grupo;
import utils.Constantes;
import validacao.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class Controle {

	private HashMap<String, Aluno> mapaAlunos;
	private HashMap<String, Grupo> mapaGrupos;
	private HashSet<Aluno> alunosRespondemQuadro;

	public Controle() {
		this.mapaAlunos = new HashMap<>();
		this.mapaGrupos = new HashMap<>();
		this.alunosRespondemQuadro = new HashSet<>();
	}

	/**
	 * metodo que cadastra um aluno caso os campos estejam validos e nao existe um
	 * aluno com a matricula informada
	 *
	 * @param matricula matricula aluno
	 * @param nome      nome aluno
	 * @param curso     curso aluno
	 */
	public void cadastraAluno(String matricula, String nome, String curso) {

		// trecho para realizar validaÃ§Ã£o da entrada do usuario
		ValidacaoControle.validaCampo(matricula, TiposCampo.NUMERICO);
		ValidacaoControle.validaCampo(nome, TiposCampo.ALFABETO);
		ValidacaoControle.validaCampo(curso, TiposCampo.ALFABETO);

		// valida se já existe um aluno com matricula
		ValidacaoControle.validaExistenciaAluno(this.mapaAlunos, matricula, false);

		mapaAlunos.put(matricula, new Aluno(matricula, nome, curso));
	}

	/**
	 * Metodo que retorna um objeto aluno atraves da matricula, ou lança excessao
	 * caso nao exista
	 * 
	 * @param matricula do aluno a ser buscado
	 * @return objeto aluno que corresponde matricula
	 */
	public Aluno retornaAluno(String matricula) {
		
		ValidacaoControle.validaCampo(matricula, TiposCampo.NUMERICO);
		ValidacaoControle.validaExistenciaAluno(this.mapaAlunos, matricula, true);

		return mapaAlunos.get(matricula);
	}

	/**
	 * Metodo que cadastra um grupo, validando as informacoes recebidas e criando
	 * caso sejam validas. valida se o ja existe algum grupo com o nome informado
	 * 
	 * @param nome    nome do grupo a ser criado
	 * @param tamanho quantidade de alunos que o grupo deve contar no maximo
	 */
	public void cadastraGrupo(String nome, Integer tamanho) {

		// nao existe grupo com tamanho negativo ou zerado
		if (tamanho <= 0)
			throw new IllegalArgumentException(Constantes.MENSAGEM_TAMANHO_GRUPO_INVALIDO);
		ValidacaoControle.validaCampo(nome, TiposCampo.ALPHANUMERICO);
		ValidacaoControle.validaExistenciaGrupo(this.mapaGrupos, nome, false);

		mapaGrupos.put(nome.toLowerCase(Locale.ROOT), new Grupo(nome, tamanho));
	}

	/**
	 * metodo que aloca um aluno a um grupo, validando as informacoes repassadas e
	 * lancando excessao caso sejam invalidas
	 * 
	 * @param matricula matricula do aluno a ser alocado a um grupo
	 * @param nomeGrupo nome do grupo que o aluno sera alocado
	 */
	public void alocaAluno(String matricula, String nomeGrupo) {
		
		ValidacaoControle.validaCampo(matricula, TiposCampo.NUMERICO);
		ValidacaoControle.validaCampo(nomeGrupo, TiposCampo.ALPHANUMERICO);
		ValidacaoControle.validaExistenciaGrupo(this.mapaGrupos, nomeGrupo, true);

		Grupo grupo = mapaGrupos.get(nomeGrupo.toLowerCase());
		grupo.adicionaAluno(retornaAluno(matricula));
	}

	/**
	 * Metodo que verifica se existe um aluno a um grupo, valida a informacoes
	 * repassadas e lanca excessao se sao invalidas.
	 * 
	 * 
	 * @param matricula matricula do aluno a ser checado se pertence
	 * @param nomeGrupo que ser checado se o aluno pertence
	 * @return Caso o aluno pertença ao grupo e retornado true
	 */
	public boolean pertinenciaGrupo(String matricula, String nomeGrupo) {
		ValidacaoControle.validaCampo(matricula, TiposCampo.NUMERICO);

		ValidacaoControle.validaExistenciaGrupo(this.mapaGrupos, nomeGrupo, true);
		return mapaGrupos.get(nomeGrupo.toLowerCase()).getAlunos().contains(retornaAluno(matricula));
	}

	/**
	 * Marca os alunos que respondem ao quadro, caso ele ja tenha sido marcado e
	 * lancado uma excessao. caso matricula informada nao corresponda a algum aluno
	 * e lancado excessao
	 * 
	 * @param matricula matricula do aluno a ser marcado
	 */
	public void cadastraAlunoRespondeQuadro(String matricula) {
		ValidacaoControle.validaCampo(matricula, TiposCampo.NUMERICO);
		Aluno aluno = retornaAluno(matricula);
		ValidacaoControle.alunoJarespondeuQuadro(this.alunosRespondemQuadro, aluno);

		this.alunosRespondemQuadro.add(aluno);
	}

	/**
	 * Retorna os alunos que respondem ao quadro
	 * 
	 * @return HashSet do tipo Aluno
	 */
	public HashSet<Aluno> getAlunosRespondemQuadro() {
		return this.alunosRespondemQuadro;
	}

	/**
	 * Metodo que lista todos os grupos que o aluno esta presente. caso matricula
	 * informada nao pertenca aluno e lancado excessao.
	 * 
	 * @param matricula matricula do aluno que sera retornado lista de grupos
	 * @return HashSet tipo do grupo
	 */
	public HashSet<Grupo> gruposAlunoEsta(String matricula) {

		HashSet<Grupo> grupos = new HashSet<>();
		Aluno aluno = retornaAluno(matricula);
		for (String nomeGrupo : this.mapaGrupos.keySet()) {
			if (this.mapaGrupos.get(nomeGrupo).getAlunos().contains(aluno))
				grupos.add(this.mapaGrupos.get(nomeGrupo));
		}
		return grupos;
	}
}
