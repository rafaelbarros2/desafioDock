package com.cartorio.cartorio.controllers;

import com.cartorio.cartorio.client.CertidaoClient;
import com.cartorio.cartorio.dtos.CartorioDTO;
import com.cartorio.cartorio.dtos.CertidaoDTO;
import com.cartorio.cartorio.entities.Cartorio;
import com.cartorio.cartorio.services.CartorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class CartorioModelController {

    @Autowired
    private CartorioService service;

    @Autowired
    private CertidaoClient certidaoClient;

    @GetMapping("/login")
    public String Login(){
        return "login";
    }

    @GetMapping("/")
    public String Index(Model model){
        List<Cartorio> cartorios = service.findAll();
        model.addAttribute("cartorios", cartorios);
        return "index";
    }

    @GetMapping("/certidao")
    public ModelAndView certidao(Model model){
        ModelAndView mv = new ModelAndView("index");
        List<CertidaoDTO> certidoes = certidaoClient.findAll();
        model.addAttribute("index", certidoes);
        return mv;
    }

//    @GetMapping(value = "/{id}")
//    public String userView(@PathVariable("id") Long id, Model model){
//        CartorioDTO cartorio = service.findById(id);
//        model.addAttribute("cartorios", cartorio);
//        return "index";
//    }

    @GetMapping("/cadastro")
    public String Cadastrar(Model model){
        CartorioDTO cartorios = new CartorioDTO();
        model.addAttribute("cartorio",cartorios);

//        List<CertidaoDTO> certidoes = certidaoClient.findAll();
//        model.addAttribute("certidoes", certidoes);
        return "cadastro";
    }

    @GetMapping(value = "/Cadastrar/Sucesso")
    public String SucessForm(){
        return "cadastrar-editar/Sucesso";
    }

    @GetMapping(value = "/editar/{id}")
    public String EditForm(@PathVariable("id") Long id,Model model){
        CartorioDTO cartorio = service.findById(id);
        model.addAttribute("cartorio", cartorio);

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
