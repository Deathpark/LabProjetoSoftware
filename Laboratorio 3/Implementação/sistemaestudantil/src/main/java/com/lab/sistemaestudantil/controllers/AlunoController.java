package com.lab.sistemaestudantil.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.lab.sistemaestudantil.models.Aluno;
import com.lab.sistemaestudantil.repositories.AlunoRepository;

@Controller
@RequestMapping(value = "/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("")
    public ModelAndView index() {

        List<Aluno> alunos = this.alunoRepository.findAll();

        ModelAndView mv = new ModelAndView("alunos/index");
        mv.addObject("alunos", alunos);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew() {
        ModelAndView mv = new ModelAndView("alunos/new");

        return mv;
    }

    @PostMapping("")
    public String create(Aluno a) {
        this.alunoRepository.save(a);
        return "redirect:/alunos";
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        Optional<Aluno> a = this.alunoRepository.findById(id);
        if (a.isPresent()) {
            Aluno c = a.get();
            ModelAndView mv = new ModelAndView("alunos/show");
            mv.addObject("aluno", c);
            return mv;
        } else {
            return new ModelAndView("redirect:/alunos");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, Aluno cl) {
        Optional<Aluno> optional = this.alunoRepository.findById(id);
        if (optional.isPresent()) {
            Aluno aluno = optional.get();
            ModelAndView mv = new ModelAndView("alunos/edit");
            mv.addObject("aluno", aluno);
            return mv;
        } else {
            return new ModelAndView("redirect:/alunos");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, Aluno aluno) {
        Optional<Aluno> optional = this.alunoRepository.findById(id);
        if (optional.isPresent()) {
            Aluno c = optional.get();
            c.setNome(aluno.getNome());
            c.setSenha(aluno.getSenha());
            c.setEmail(aluno.getEmail());

            this.alunoRepository.save(c);
            ModelAndView mv = new ModelAndView("redirect:/alunos/" + c.getId().toString());
            return mv;
        } else {
            return new ModelAndView("redirect:/alunos");
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        try {
            this.alunoRepository.deleteById(id);
            return "redirect:/alunos";
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            return "redirect:/alunos";
        }

    }
}
