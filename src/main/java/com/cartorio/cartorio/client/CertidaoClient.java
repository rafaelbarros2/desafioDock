package com.cartorio.cartorio.client;

import com.cartorio.cartorio.dtos.CertidaoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "certidoes", url = "https://docketdesafiobackend.herokuapp.com/api/v1")
public interface CertidaoClient {

    @GetMapping(value = "/certidoes")
    List<CertidaoDTO> findAll();
}
