package com.lab.aluguelveiculos.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerHello {

    @GetMapping("/hello")
    public ModelAndView Hello() {
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("nome", "test2");
        return mv;
    }

}
