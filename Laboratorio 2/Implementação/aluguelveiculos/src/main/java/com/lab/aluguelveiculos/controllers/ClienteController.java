package com.lab.aluguelveiculos.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lab.aluguelveiculos.models.Cliente;
import com.lab.aluguelveiculos.repositories.ClienteRepository;

@Controller
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public ModelAndView index() {

        List<Cliente> clientes = this.clienteRepository.findAll();

        ModelAndView mv = new ModelAndView("clientes/index");
        mv.addObject("clientes", clientes);

        return mv;
    }

    @GetMapping("cliente/new")
    public ModelAndView nnew() {
        ModelAndView mv = new ModelAndView("clientes/new");

        return mv;
    }

    @PostMapping("/clientes")
    public String create(Cliente cliente){
        return "redirect:/clientes";
    }
}
