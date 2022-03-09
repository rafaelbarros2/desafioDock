package com.cartorio.cartorio.controllers;

import com.cartorio.cartorio.client.CertidaoClient;
import com.cartorio.cartorio.dtos.CertidaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/certidoes")
public class ReportController {

    @Autowired
    private CertidaoClient certidaoClient;

    @GetMapping
    public List<CertidaoDTO> findAll(){
        return certidaoClient.findAll();
    }

    @GetMapping(value = "{id}")
    public Optional<CertidaoDTO> findById(Long id){
         Optional<CertidaoDTO> obj = Optional.ofNullable(certidaoClient.findById(id));
         return  obj;
    }
}
