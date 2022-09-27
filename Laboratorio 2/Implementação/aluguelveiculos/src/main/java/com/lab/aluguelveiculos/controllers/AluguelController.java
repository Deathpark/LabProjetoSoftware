package com.lab.aluguelveiculos.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.lab.aluguelveiculos.models.Aluguel;
import com.lab.aluguelveiculos.models.Cliente;
import com.lab.aluguelveiculos.repositories.AluguelRepository;

@Controller
@RequestMapping(value = "/aluguel")
public class AluguelController {
    @Autowired
    private AluguelRepository aluguelRepository;

    @GetMapping("")
    public ModelAndView index() {

        List<Aluguel> alugueis = this.aluguelRepository.findAll();

        ModelAndView mv = new ModelAndView("aluguel/index");
        mv.addObject("alugueis", alugueis);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew() {
        ModelAndView mv = new ModelAndView("aluguel/new");

        return mv;
    }

    @PostMapping("")
    public String create(Aluguel a) {
        a.setContrato(false);
        this.aluguelRepository.save(a);
        return "redirect:/aluguel";
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        Optional<Aluguel> a = this.aluguelRepository.findById(id);
        if (a.isPresent()) {
            Aluguel c = a.get();
            ModelAndView mv = new ModelAndView("aluguel/show");
            mv.addObject("aluguel", c);
            return mv;
        } else {
            return new ModelAndView("redirect:/aluguel");
        }
    }
}
