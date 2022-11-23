package com.lab.sistemaestudantil.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lab.sistemaestudantil.models.Usuario;
import com.lab.sistemaestudantil.repositories.UsuarioRepository;

@Controller
public class HomeController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/login")
    public String login(Usuario usuario, BindingResult result, RedirectAttributes redirectAttrs) {
        List<Usuario> list = this.usuarioRepository.findByNome(usuario.getNome());
        if (!list.isEmpty()) {
            Usuario user = list.get(0);
            if (user.getSenha().equals(usuario.getSenha()) && user.getClass().getSimpleName().equals("Aluno")) {
                return "redirect:/alunos/" + user.getId();
            } else if (user.getSenha().equals(usuario.getSenha()) && user.getClass().getSimpleName().equals("Empresa")) {
                return "redirect:/empresas/" + user.getId();
            } else if (user.getSenha().equals(usuario.getSenha()) && user.getClass().getSimpleName().equals("Professor")) {
                return "redirect:/professores/" + user.getId();
            }
        }

        redirectAttrs.addFlashAttribute("error", "Usuário ou senha incorretos!");
        return "redirect:/";
    }
}
