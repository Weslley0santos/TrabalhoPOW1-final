package br.ufsm.csi.controller;

import br.ufsm.csi.dao.CursoRepository; // Mantém para buscar cursos para o formulário
import br.ufsm.csi.model.Aluno;
import br.ufsm.csi.model.Curso;
import br.ufsm.csi.service.AlunoService; // Importa o AlunoService
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService alunoService; // Altera para AlunoService
    private final CursoRepository cursoRepository; // Mantém para listar cursos no formulário

    public AlunoController(AlunoService alunoService, CursoRepository cursoRepository) {
        this.alunoService = alunoService;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping({"", "/listar"})
    public String listarAlunos(Model model) {
        List<Aluno> alunos = alunoService.listarAlunos(); // Chama o service
        model.addAttribute("alunos", alunos);
        return "gerenciarAlunos";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovoAluno(Model model) {
        List<Curso> cursos = cursoRepository.findAll(); // Ainda precisa para o dropdown
        model.addAttribute("cursos", cursos);
        model.addAttribute("aluno", new Aluno());
        return "formAluno";
    }

    @GetMapping("/editar")
    public String editarAluno(@RequestParam("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Aluno> alunoOptional = alunoService.buscarAlunoPorId(id); // Chama o service
        if (alunoOptional.isPresent()) {
            model.addAttribute("aluno", alunoOptional.get());
            List<Curso> cursos = cursoRepository.findAll(); // Ainda precisa para o dropdown
            model.addAttribute("cursos", cursos);
            return "formAluno";
        } else {
            redirectAttributes.addFlashAttribute("erro", "Aluno não encontrado para edição.");
            return "redirect:/aluno/listar";
        }
    }

    @GetMapping("/excluir")
    public String excluirAluno(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        if (alunoService.excluirAluno(id)) { // Chama o service
            redirectAttributes.addFlashAttribute("mensagem", "Aluno excluído com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir aluno. Verifique se existe.");
        }
        return "redirect:/aluno/listar";
    }

    @PostMapping("/inserir")
    public String inserirAluno(
            @RequestParam("nome") String nome,
            @RequestParam("matricula") String matricula,
            @RequestParam("cursoId") Long cursoId,
            RedirectAttributes redirectAttributes) {

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setMatricula(matricula);
        // Cria um objeto Curso com apenas o ID para o service processar
        Curso curso = new Curso();
        curso.setId(cursoId);
        aluno.setCurso(curso);

        if (alunoService.inserirOuAtualizarAluno(aluno)) { // Chama o service
            redirectAttributes.addFlashAttribute("mensagem", "Aluno inserido com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Erro ao inserir aluno. Verifique os dados e o Curso.");
            return "redirect:/aluno/novo"; // Redireciona de volta para o formulário de novo
        }
        return "redirect:/aluno/listar";
    }

    @PostMapping("/atualizar")
    public String atualizarAluno(
            @RequestParam("id") Long id,
            @RequestParam("nome") String nome,
            @RequestParam("matricula") String matricula,
            @RequestParam("cursoId") Long cursoId,
            RedirectAttributes redirectAttributes) {

        Aluno aluno = new Aluno();
        aluno.setId(id); // Importante para o service saber que é uma atualização
        aluno.setNome(nome);
        aluno.setMatricula(matricula);

        // Cria um objeto Curso com apenas o ID para o service processar
        Curso curso = new Curso();
        curso.setId(cursoId);
        aluno.setCurso(curso);

        if (alunoService.inserirOuAtualizarAluno(aluno)) { // Chama o service
            redirectAttributes.addFlashAttribute("mensagem", "Aluno atualizado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar aluno. Verifique os dados e o Curso.");
            return "redirect:/aluno/editar?id=" + id; // Redireciona de volta para o formulário de edição
        }
        return "redirect:/aluno/listar";
    }
}