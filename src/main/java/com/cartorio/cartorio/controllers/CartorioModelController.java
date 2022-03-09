package com.cartorio.cartorio.controllers;

import com.cartorio.cartorio.dtos.CartorioDTO;
import com.cartorio.cartorio.entities.Cartorio;
import com.cartorio.cartorio.services.CartorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

public class CartorioModelController {

    @Autowired
    private CartorioService service;

    @GetMapping("/")
    public String Index(Model model){

        List<CartorioDTO> cartorios = service.findAll();
        model.addAttribute("cartorios", cartorios);
        return "index";
    }

    @GetMapping(value = "/{id}")
    public String userView(@PathVariable("id") Long id, Model model){
        CartorioDTO cartorio = service.findById(id);
        model.addAttribute("cartorios", cartorio);
        return "index";
    }

    @GetMapping("/Cadastrar")
    public String Cadastrar(Model model){
        CartorioDTO cartorios = new CartorioDTO();
        model.addAttribute("cartorio",cartorios);
        return "cadastrar-editar/cadastro";
    }

    @GetMapping(value = "/Cadastrar/Sucesso")
    public String SucessForm(){
        return "cadastrar-editar/Sucesso";
    }

    @GetMapping(value = "/editar/{id}")
    public String EditForm(@PathVariable("id") Long id,Model model){
        CartorioDTO cartorio = service.findById(id);
        model.addAttribute("pessoaobj", cartorio);

        return "cadastrar-editar/editar";
    }

    @GetMapping(value = "/remove/{id}")
    public String deleteForm(@PathVariable("id") Long id){
      service.delete(id);
        return "redirect:/";
    }

    @PostMapping(value = "/salvar")
    public String CadastroForm(@ModelAttribute("cartorio") CartorioDTO cartorio){
      service.insert(cartorio);
        return "redirect:Cadastrar/Sucesso";
    }

    @PostMapping(value = "/editar")
    public String EditarForm(@ModelAttribute ("cartorio") CartorioDTO cartorio){
        service.insert(cartorio);
        return "redirect:/";
    }
}
