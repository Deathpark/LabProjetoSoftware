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

import com.lab.sistemaestudantil.models.Vantagem;
import com.lab.sistemaestudantil.models.Empresa;
import com.lab.sistemaestudantil.repositories.VantagemRepository;
import com.lab.sistemaestudantil.repositories.EmpresaRepository;

@Controller
@RequestMapping(value = "/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private VantagemRepository vantagemRepository;

    @GetMapping("")
    public ModelAndView index() {

        List<Empresa> empresas = this.empresaRepository.findAll();

        ModelAndView mv = new ModelAndView("empresas/index");
        mv.addObject("empresas", empresas);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew() {
        ModelAndView mv = new ModelAndView("empresas/new");

        return mv;
    }

    @PostMapping("")
    public String create(Empresa empresa) {
        this.empresaRepository.save(empresa);
        return "redirect:/empresas";
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        Optional<Empresa> empresa = this.empresaRepository.findById(id);
        if (empresa.isPresent()) {
            Empresa e = empresa.get();
            ModelAndView mv = new ModelAndView("empresas/show");
            mv.addObject("empresa", e);
            return mv;
        } else {
            return new ModelAndView("redirect:/empresas");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, Empresa cl) {
        Optional<Empresa> optional = this.empresaRepository.findById(id);
        if (optional.isPresent()) {
            Empresa empresa = optional.get();
            ModelAndView mv = new ModelAndView("empresas/edit");
            mv.addObject("empresa", empresa);
            return mv;
        } else {
            return new ModelAndView("redirect:/empresas");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, Empresa empresa) {
        Optional<Empresa> optional = this.empresaRepository.findById(id);
        if (optional.isPresent()) {
            Empresa e = optional.get();
            e.setNome(empresa.getNome());
            e.setSenha(empresa.getSenha());

            this.empresaRepository.save(e);
            ModelAndView mv = new ModelAndView("redirect:/empresas/" + e.getId().toString());
            return mv;
        } else {
            return new ModelAndView("redirect:/empresas");
        }

    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        try {
            this.empresaRepository.deleteById(id);
            return "redirect:/empresas";
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            return "redirect:/empresas";
        }

    }


    @GetMapping("/{id}/vantagem")
    public ModelAndView indexVantagem(@PathVariable long id) {
        List<Vantagem> todasVantagens = this.vantagemRepository.findAll();
        List<Vantagem> vantagens = todasVantagens.stream().filter(v -> v.getEmpresaId() == id).toList();
        Optional<Empresa> e = this.empresaRepository.findById(id);
        Empresa empresa = e.get();
        
        ModelAndView mv = new ModelAndView("vantagem/index");
        mv.addObject("vantagens", vantagens);
        mv.addObject("empresa", empresa);

        return mv;
    }

    @GetMapping("/{id}/vantagem/new")
    public ModelAndView newVantagem(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("vantagem/new");
        mv.addObject("idEmpresa", id);
        
        return mv;
    }

    @PostMapping("/{id}/vantagem")
    public String createVantagem(Vantagem v, @PathVariable Long id) {
        v.setEmpresaId(id);
        v.setId(null);
        this.vantagemRepository.save(v);
        return "redirect:/empresas/{id}/vantagem/";
    }

    @GetMapping("/{id}/vantagem/{vantagemId}/delete")
    public String deleteVantagem(@PathVariable Long id, @PathVariable Long vantagemId) {
        try {
            this.vantagemRepository.deleteById(vantagemId);
            return "redirect:/empresas/" + id.toString() + "/vantagem/";
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            return "redirect:/empresas/" + id.toString() + "/vantagem/";
        }

    }

    @GetMapping("/{id}/vantagem/{vantagemId}/edit")
    public ModelAndView editVantagem(@PathVariable Long id, @PathVariable Long vantagemId, Vantagem v) {
        Optional<Vantagem> optional = this.vantagemRepository.findById(vantagemId);
        if (optional.isPresent()) {
            Vantagem vantagem = optional.get();
            ModelAndView mv = new ModelAndView("vantagem/edit");
            mv.addObject("vantagem", vantagem);
            return mv;
        } else {
            return new ModelAndView("redirect:/empresas/" + id.toString() + "/vantagem/");
        }
    }

    @GetMapping("/{id}/vantagem/{vantagemId}")
    public ModelAndView showVantagem(@PathVariable Long id, @PathVariable Long vantagemId) {
        Optional<Vantagem> vantagem = this.vantagemRepository.findById(vantagemId);
        Optional<Empresa> empresa = this.empresaRepository.findById(id);
        if (vantagem.isPresent()) {
            Vantagem v = vantagem.get();
            Empresa e = empresa.get();
            ModelAndView mv = new ModelAndView("vantagem/show");
            mv.addObject("vantagem", v);
            mv.addObject("empresa", e);
            return mv;
        } else {
            return new ModelAndView("redirect:/vantagem");
        }
    }

    @PostMapping("/{id}/vantagem/{vantagemId}")
    public ModelAndView update(@PathVariable Long id, @PathVariable Long vantagemId, Vantagem vantagem) {
        Optional<Vantagem> optional = this.vantagemRepository.findById(vantagemId);
        if (optional.isPresent()) {
            Vantagem v = optional.get();
            v.setDescricao(vantagem.getDescricao());
            v.setValorVantagem(vantagem.getValorVantagem());
            v.setCustoVantagem(vantagem.getCustoVantagem());

            this.vantagemRepository.save(v);
            System.out.println("------------------------\n\n\n" + id + "\n\n\n-----------------------------");
            ModelAndView mv = new ModelAndView("redirect:/empresas/" + id.toString() + "/vantagem/");
            return mv;
        } else {
            return new ModelAndView("redirect:/empresas/" + id.toString() + "/vantagem/");
        }

    }
}
