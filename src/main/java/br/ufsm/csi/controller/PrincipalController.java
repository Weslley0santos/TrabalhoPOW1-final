package br.ufsm.csi.controller;

import br.ufsm.csi.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

    @GetMapping
    public String showPrincipalPage(
            @SessionAttribute(name = "usuarioLogado", required = false) Usuario usuarioLogado,
            Model model,
            HttpSession session) {

        if (usuarioLogado == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuarioLogado);
        return "principal";
    }
}