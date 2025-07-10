package br.ufsm.csi.controller;

import br.ufsm.csi.model.Curso;
import br.ufsm.csi.service.CursoService; // Importa o CursoService
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/curso")
public class CursoController {

    private final CursoService cursoService; // Altera para CursoService

    public CursoController(CursoService cursoService) { // Ajusta o construtor
        this.cursoService = cursoService;
    }

    @GetMapping({"", "/listar"})
    public String listarCursos(Model model) {
        List<Curso> cursos = cursoService.listarCursos(); // Chama o service
        model.addAttribute("cursos", cursos);
        return "gerenciarCursos";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovoCurso(Model model) {
        model.addAttribute("curso", new Curso());
        return "formCursos";
    }

    @GetMapping("/editar")
    public String editarCurso(@RequestParam("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Curso> cursoOptional = cursoService.buscarCursoPorId(id); // Chama o service
        if (cursoOptional.isPresent()) {
            model.addAttribute("curso", cursoOptional.get());
            return "formCursos";
        } else {
            redirectAttributes.addFlashAttribute("erro", "Curso não encontrado para edição.");
            return "redirect:/curso/listar";
        }
    }

    @GetMapping("/excluir")
    public String excluirCurso(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        if (cursoService.excluirCurso(id)) { // Chama o service
            redirectAttributes.addFlashAttribute("mensagem", "Curso excluído com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir curso. Verifique se existe.");
        }
        return "redirect:/curso/listar";
    }

    @PostMapping("/inserir")
    public String inserirCurso(
            @RequestParam("nome") String nome,
            @RequestParam("professor") String professor,
            RedirectAttributes redirectAttributes) {

        Curso curso = new Curso();
        curso.setNome(nome);
        curso.setProfessor(professor);

        if (cursoService.inserirOuAtualizarCurso(curso)) { // Chama o service
            redirectAttributes.addFlashAttribute("mensagem", "Curso inserido com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Erro ao inserir curso. Verifique os dados.");
            return "redirect:/curso/novo"; // Redireciona de volta para o formulário de novo
        }
        return "redirect:/curso/listar";
    }

    @PostMapping("/atualizar")
    public String atualizarCurso(
            @RequestParam("id") Long id,
            @RequestParam("nome") String nome,
            @RequestParam("professor") String professor,
            RedirectAttributes redirectAttributes) {

        Curso curso = new Curso();
        curso.setId(id); // Importante para o service saber que é uma atualização
        curso.setNome(nome);
        curso.setProfessor(professor);

        if (cursoService.inserirOuAtualizarCurso(curso)) { // Chama o service
            redirectAttributes.addFlashAttribute("mensagem", "Curso atualizado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar curso. Verifique os dados.");
            return "redirect:/curso/editar?id=" + id; // Redireciona de volta para o formulário de edição
        }
        return "redirect:/curso/listar";
    }
}