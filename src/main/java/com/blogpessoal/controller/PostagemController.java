package com.blogpessoal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.blogpessoal.model.Postagem;

@Controller
public class PostagemController {
	
	 @GetMapping("/postagens")
	    public ModelAndView listarPostagens() {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("postagem/listar");
	        // Aqui você adicionará a lista de postagens depois
	        return mv;
	    }
	    
	    @GetMapping("/postagens/nova")
	    public ModelAndView novaPostagem() {
	        ModelAndView mv = new ModelAndView();
	        mv.addObject("postagem", new Postagem());
	        mv.setViewName("postagem/form");
	        return mv;
	    }

}
