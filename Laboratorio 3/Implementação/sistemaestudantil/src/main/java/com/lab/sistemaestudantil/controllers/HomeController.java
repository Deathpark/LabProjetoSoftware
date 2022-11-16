package com.lab.sistemaestudantil.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public String login(Usuario usuario) {
        List<Usuario> list = this.usuarioRepository.findByNome(usuario.getNome());
        if (!list.isEmpty()) {
            Usuario user = list.get(0);
            if (user.getSenha().equals(usuario.getSenha())) {
                return "redirect:/alunos";
            }
        }
        return "redirect:/";
    }
}
