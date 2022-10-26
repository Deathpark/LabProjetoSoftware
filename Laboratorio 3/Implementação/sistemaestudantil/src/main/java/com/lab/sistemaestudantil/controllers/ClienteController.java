package com.lab.sistemaestudantil.controllers;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.lab.sistemaestudantil.models.Cliente;
import com.lab.sistemaestudantil.models.Empresa;
import com.lab.sistemaestudantil.repositories.ClienteRepository;

@Controller
@RequestMapping(value = "/empresas")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("")
    public ModelAndView index() {

        List<Empresa> clientes = this.clienteRepository.findAll();

        ModelAndView mv = new ModelAndView("clientes/index");
        mv.addObject("clientes", clientes);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew() {
        ModelAndView mv = new ModelAndView("clientes/new");

        return mv;
    }

    @PostMapping("")
    public String create(Empresa cliente) {
        this.clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        Optional<Empresa> cliente = this.clienteRepository.findById(id);
        if (cliente.isPresent()) {
            Empresa c = cliente.get();
            ModelAndView mv = new ModelAndView("clientes/show");
            mv.addObject("cliente", c);
            return mv;
        } else {
            return new ModelAndView("redirect:/clientes");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, Cliente cl) {
        Optional<Empresa> optional = this.clienteRepository.findById(id);
        if (optional.isPresent()) {
            Empresa cliente = optional.get();
            ModelAndView mv = new ModelAndView("clientes/edit");
            mv.addObject("cliente", cliente);
            return mv;
        } else {
            return new ModelAndView("redirect:/clientes");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, Empresa cliente) {
        Optional<Empresa> optional = this.clienteRepository.findById(id);
        if (optional.isPresent()) {
            Empresa c = optional.get();
            c.setNome(cliente.getNome());
            c.setSenha(cliente.getSenha());
            c.setVantagens(cliente.getVantagens());

            this.clienteRepository.save(c);
            ModelAndView mv = new ModelAndView("redirect:/clientes/" + c.getId().toString());
            return mv;
        } else {
            return new ModelAndView("redirect:/clientes");
        }

    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        try {
            this.clienteRepository.deleteById(id);
            return "redirect:/clientes";
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            return "redirect:/clientes";
        }

    }
}
