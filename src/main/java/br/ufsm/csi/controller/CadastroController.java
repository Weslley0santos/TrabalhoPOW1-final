package br.ufsm.csi.controller;

import br.ufsm.csi.model.Usuario;
import br.ufsm.csi.service.UsuarioService; // Importa o UsuarioService
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    private final UsuarioService usuarioService; // Altera para UsuarioService

    public CadastroController(UsuarioService usuarioService) { // Ajusta o construtor
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String showCadastroForm() {
        return "cadastro";
    }

    @PostMapping
    public String processCadastroForm(
            @RequestParam("nome") String nome,
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            @RequestParam("confirmarSenha") String confirmarSenha,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Validações básicas antes de enviar para o serviço
        if (nome == null || nome.isEmpty() ||
                email == null || email.isEmpty() ||
                senha == null || senha.isEmpty() ||
                confirmarSenha == null || confirmarSenha.isEmpty()) {

            model.addAttribute("erro", "Todos os campos são obrigatórios!");
            return "cadastro";
        }

        if (!senha.equals(confirmarSenha)) {
            model.addAttribute("erro", "As senhas não coincidem!");
            return "cadastro";
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);
        novoUsuario.setAtivo(true); // O serviço pode conter lógica para ativar/desativar

        // Chama o service para inserir/salvar o usuário
        if (usuarioService.inserirOuAtualizarUsuario(novoUsuario)) {
            redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso! Faça login.");
            return "redirect:/login";
        } else {
            // O service já loga o erro, aqui apenas exibe uma mensagem genérica ou específica
            model.addAttribute("erro", "Erro ao cadastrar usuário. Tente novamente.");
            return "cadastro";
        }
    }
}