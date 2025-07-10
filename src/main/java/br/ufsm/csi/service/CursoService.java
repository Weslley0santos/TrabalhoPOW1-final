package br.ufsm.csi.service;

import br.ufsm.csi.dao.CursoRepository;
import br.ufsm.csi.model.Curso;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso encontrado.");
        } else {
            System.out.println("Lista de cursos encontrada com sucesso:");
            for (Curso curso : cursos) {
                System.out.println(curso);
            }
        }
        return cursos;
    }

    public Optional<Curso> buscarCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public boolean inserirOuAtualizarCurso(Curso curso) {
        if (curso.getNome() == null || curso.getNome().isEmpty() ||
                curso.getProfessor() == null || curso.getProfessor().isEmpty()) {
            System.out.println("Erro: Nome e Professor do curso são obrigatórios.");
            return false;
        }

        try {
            cursoRepository.save(curso);
            System.out.println("Curso salvo com sucesso.");
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao salvar curso: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirCurso(Long id) {
        try {
            if (cursoRepository.existsById(id)) { // Verifica se o curso existe antes de tentar excluir
                cursoRepository.deleteById(id);
                System.out.println("Curso excluído com sucesso.");
                return true;
            } else {
                System.out.println("Erro: Curso com ID " + id + " não encontrado para exclusão.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Erro ao excluir curso: " + e.getMessage());
            return false;
        }
    }
}