package com.blogpessoal.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.blogpessoal.model.Tema;

@Controller
public class TemaController {
	
	@GetMapping("/temas")
    public ModelAndView listarTemas() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tema/listar");
        // Adicionará a lista de temas depois
        return mv;
    }
    
    @GetMapping("/temas/novo")
    public ModelAndView novoTema() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("tema", new Tema());
        mv.setViewName("tema/form");
        return mv;
    }

}
