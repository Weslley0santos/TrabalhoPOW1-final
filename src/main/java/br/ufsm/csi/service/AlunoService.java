package br.ufsm.csi.service;

import br.ufsm.csi.dao.AlunoRepository;
import br.ufsm.csi.dao.CursoRepository;
import br.ufsm.csi.model.Aluno;
import br.ufsm.csi.model.Curso;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository; // Necessário para buscar o Curso completo

    public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado.");
        } else {
            System.out.println("Lista de alunos encontrada com sucesso:");
            for (Aluno aluno : alunos) {
                System.out.println(aluno);
            }
        }
        return alunos;
    }

    public Optional<Aluno> buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public boolean inserirOuAtualizarAluno(Aluno aluno) {
        if (aluno.getNome() == null || aluno.getNome().isEmpty() ||
                aluno.getMatricula() == null || aluno.getMatricula().isEmpty() ||
                aluno.getCurso() == null || aluno.getCurso().getId() == null) { // Valida o relacionamento com Curso
            System.out.println("Erro: Nome, Matrícula e Curso do aluno são obrigatórios.");
            return false;
        }

        try {
            // Se o Aluno.curso foi setado apenas com o ID, precisamos buscar o Curso completo.
            // Isso é importante para o JPA gerenciar corretamente o relacionamento.
            Optional<Curso> cursoOptional = cursoRepository.findById(aluno.getCurso().getId());
            if (cursoOptional.isPresent()) {
                aluno.setCurso(cursoOptional.get()); // Garante que o objeto Curso esteja gerenciado pelo JPA
            } else {
                System.out.println("Erro: Curso com ID " + aluno.getCurso().getId() + " não encontrado.");
                return false;
            }

            alunoRepository.save(aluno);
            System.out.println("Aluno salvo com sucesso.");
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao salvar aluno: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirAluno(Long id) {
        try {
            if (alunoRepository.existsById(id)) {
                alunoRepository.deleteById(id);
                System.out.println("Aluno excluído com sucesso.");
                return true;
            } else {
                System.out.println("Erro: Aluno com ID " + id + " não encontrado para exclusão.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Erro ao excluir aluno: " + e.getMessage());
            return false;
        }
    }
}