package com.lab.aluguelveiculos.controllers;

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
import com.lab.aluguelveiculos.models.Aluguel;
import com.lab.aluguelveiculos.models.Cliente;
import com.lab.aluguelveiculos.repositories.AluguelRepository;
import com.lab.aluguelveiculos.repositories.ClienteRepository;

@Controller
@RequestMapping(value = "/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AluguelRepository aluguelRepository;

    @GetMapping("")
    public ModelAndView index() {

        List<Cliente> clientes = this.clienteRepository.findAll();

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
    public String create(Cliente cliente) {
        this.clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        if (cliente.isPresent()) {
            Cliente c = cliente.get();
            ModelAndView mv = new ModelAndView("clientes/show");
            mv.addObject("cliente", c);
            return mv;
        } else {
            return new ModelAndView("redirect:/clientes");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, Cliente cl) {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        if (cliente.isPresent()) {
            Cliente c = cliente.get();
            ModelAndView mv = new ModelAndView("clientes/edit");
            mv.addObject("cliente", c);
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

    @GetMapping("/{id}/aluguel")
    public ModelAndView indexAluguel(@PathVariable Long id) {

        List<Aluguel> todosAlugueis = this.aluguelRepository.findAll();
        List<Aluguel> alugueis = todosAlugueis.stream().filter(a -> a.getIdCliente()
        == id).toList();

        ModelAndView mv = new ModelAndView("aluguel/index");
        mv.addObject("alugueis", alugueis);
        mv.addObject("idCliente", id);

        return mv;
    }

    @GetMapping("/{id}/aluguel/new")
    public ModelAndView newAluguel(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("aluguel/new");
        mv.addObject("idCliente", id);

        return mv;
    }

    @PostMapping("/{id}/aluguel")
    public String createAluguel(Aluguel a, @PathVariable Long id) {
        a.setIdCliente(id);
        a.setId(null);
        this.aluguelRepository.save(a);
        return "redirect:/clientes/{id}/aluguel/";
    }

}
