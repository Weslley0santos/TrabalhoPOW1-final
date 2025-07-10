package br.ufsm.csi.controller;

import br.ufsm.csi.model.Usuario;
import br.ufsm.csi.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
@SessionAttributes("usuarioLogado")
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping // Trata requisições GET para /login
    public String showLoginForm(Model model) {

        if (model.containsAttribute("mensagem")) {

        }
        return "login-form";
    }

    @PostMapping
    public String processLogin(
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
            model.addAttribute("erro", "Email e senha são obrigatórios!");
            return "login-form"; // <--- ALTERADO AQUI!
        }

        Usuario usuario = usuarioService.autenticar(email, senha);

        if (usuario != null) {
            session.setAttribute("usuarioLogado", usuario);
            // .addFlashAttribute("sucesso", "Login realizado com sucesso!");
            return "redirect:/principal";
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos, ou usuário inativo!");
            return "login-form"; // <--- ALTERADO AQUI!
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("mensagem", "Sessão encerrada com sucesso!");
        return "redirect:/login";
    }
}