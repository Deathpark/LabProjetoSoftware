package com.lab.sistemaestudantil.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.lab.sistemaestudantil.models.Aluno;
import com.lab.sistemaestudantil.models.ComprarVantagemModel;
import com.lab.sistemaestudantil.models.Historico;
import com.lab.sistemaestudantil.models.Vantagem;
import com.lab.sistemaestudantil.models.Empresa;
import com.lab.sistemaestudantil.repositories.AlunoRepository;
import com.lab.sistemaestudantil.repositories.HistoricoRepository;
import com.lab.sistemaestudantil.repositories.VantagemRepository;
import com.lab.sistemaestudantil.repositories.EmpresaRepository;

@Controller
@RequestMapping(value = "/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private HistoricoRepository historicoRepository;
    @Autowired
    private VantagemRepository vantagemRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

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
            //Aluno
            Aluno aluno = a.get();
            ModelAndView mv = new ModelAndView("alunos/show");
            mv.addObject("aluno", aluno);

            //Historico
            List<Historico> todasTransacoes = this.historicoRepository.findAll();
            List<Historico> historico = todasTransacoes.stream().filter(
                h -> h.getIdDestinatario() == aluno.getId() || h.getIdRemetente() == aluno.getId()).toList();
            mv.addObject("historico", historico);
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

    @GetMapping("/{id}/comprarVantagemForm")
    public ModelAndView comprarVantagemForm(@PathVariable long id) {
        Optional<Aluno> a = this.alunoRepository.findById(id);
        if (a.isPresent()) {
            Aluno aluno = a.get();
            ModelAndView mv = new ModelAndView("alunos/comprarVantagemForm");
            mv.addObject("aluno", aluno);
            return mv;
        } else {
            return new ModelAndView("redirect:/alunos");
        }
    }

    @ModelAttribute("vantagemDropDown") 
    public List<Vantagem> populateList() {
        List<Vantagem> vantagens = this.vantagemRepository.findAll();

        return vantagens;
    }

    @PostMapping("/{alunoId}/comprarVantagem")
    public String transferirMoedas(@PathVariable Long alunoId, ComprarVantagemModel compra) {
        Optional<Aluno> a = this.alunoRepository.findById(alunoId);
        Optional<Vantagem> v = this.vantagemRepository.findById(compra.getVantagemId());
        int qnt = -1;
        
        if(a.isPresent() && v.isPresent()) {
            Aluno aluno = a.get();
            Vantagem vantagem = v.get();
            
            boolean podeComprar = aluno.consultarMoedas(vantagem.getCustoVantagem());
            if(podeComprar) {
                qnt = aluno.comprarVantagem(vantagem.getCustoVantagem());
                
                if(qnt > -1) {
                    this.alunoRepository.save(aluno);
                    
                    //criação de histórico
                    Optional<Empresa> e = this.empresaRepository.findById(vantagem.getEmpresaId());
                    Empresa empresa = e.get();
                    Historico historico = new Historico(
                        aluno.getId(), 
                        aluno.getNome(), 
                        vantagem.getEmpresaId(),
                        empresa.getNome(),
                        vantagem.getCustoVantagem()
                    );
                    this.historicoRepository.save(historico);
                }
            }
            if (qnt == -1){
                return "redirect:/alunos/{alunoId}";
            } else {
                return "redirect:/alunos/{alunoId}";
            }
        }

        return "redirect:/alunos/{alunoId}";
    }
}
