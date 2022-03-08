package com.cartorio.cartorio.client;

import com.cartorio.cartorio.dtos.CertidaoDTO;
import com.cartorio.cartorio.entities.Certidao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "certidoes", url = "https://docketdesafiobackend.herokuapp.com/api/v1")
public interface CertidaoClient {

    @GetMapping(value = "/certidoes")
    List<CertidaoDTO> findAll();

    @GetMapping("/certidoes/{id}")
    CertidaoDTO findById (@PathVariable("id") Long id);

    @GetMapping("/certidoes/{id}")
    Certidao findBy(@PathVariable("id") Long id);
}
