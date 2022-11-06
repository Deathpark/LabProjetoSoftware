package com.lab.sistemaestudantil.controllers;

import java.util.ArrayList;
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
import com.lab.sistemaestudantil.models.Professor;
import com.lab.sistemaestudantil.models.Aluno;
import com.lab.sistemaestudantil.repositories.ProfessorRepository;
import com.lab.sistemaestudantil.repositories.AlunoRepository;

@Controller
@RequestMapping(value = "/professores")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("")
    public ModelAndView index() {

        List<Professor> professores = this.professorRepository.findAll();

        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew() {
        ModelAndView mv = new ModelAndView("professores/new");

        return mv;
    }

    @PostMapping("")
    public String create(Professor p) {
        this.professorRepository.save(p);
        return "redirect:/professores";
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        Optional<Professor> p = this.professorRepository.findById(id);
        if (p.isPresent()) {
            Professor professor = p.get();
            ModelAndView mv = new ModelAndView("professores/show");
            mv.addObject("professor", professor);
            return mv;
        } else {
            return new ModelAndView("redirect:/professores");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, Professor pl) {
        Optional<Professor> optional = this.professorRepository.findById(id);
        if (optional.isPresent()) {
            Professor professor = optional.get();
            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("professor", professor);
            return mv;
        } else {
            return new ModelAndView("redirect:/professores");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, Professor professor) {
        Optional<Professor> optional = this.professorRepository.findById(id);
        if (optional.isPresent()) {
            Professor p = optional.get();
            p.setNome(professor.getNome());
            p.setSenha(professor.getSenha());
            p.setInstituicaoEnsino(professor.getInstituicaoEnsino());

            this.professorRepository.save(p);
            ModelAndView mv = new ModelAndView("redirect:/professores/" + p.getId().toString());
            return mv;
        } else {
            return new ModelAndView("redirect:/professores");
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        try {
            this.professorRepository.deleteById(id);
            return "redirect:/professores";
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            return "redirect:/professores";
        }

    }

    @GetMapping("/{id}/transferirMoedas")
    public String transferirMoedas(@PathVariable Long id, int quantidade, long alunoId) {
        Optional<Professor> p = this.professorRepository.findById(id);
        Optional<Aluno> a = this.alunoRepository.findById(alunoId);
        int qnt = -1;

        if(p.isPresent() && a.isPresent()) {
            Professor professor = p.get();
            Aluno aluno = a.get();
            qnt = professor.transferirMoedas(quantidade);
            aluno.setMoedas(aluno.getMoedas()+qnt);
        }
        if (qnt == -1){
            return "redirect:/professores/{id}";
        } else {
            return "redirect:/professores/{id}";
        }
    }

    @GetMapping("/{id}/consultarExtrato")
    public ModelAndView consultarExtrato(@PathVariable Long id) {
        Optional<Professor> p = this.professorRepository.findById(id);
        if (p.isPresent()) {
            Professor professor = p.get();
            ArrayList<Integer> historico = professor.getHistorico();
            ModelAndView mv = new ModelAndView("professores/show");
            mv.addObject("extrato", historico);
            return mv;
        } else {
            return new ModelAndView("redirect:/professores");
        }
    }
}
