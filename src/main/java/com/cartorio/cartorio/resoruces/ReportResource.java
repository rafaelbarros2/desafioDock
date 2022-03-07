package com.cartorio.cartorio.resoruces;

import com.cartorio.cartorio.client.CertidaoClient;
import com.cartorio.cartorio.dtos.CertidaoDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/certidoes")
public class ReportResource {

    @Autowired
    private CertidaoClient certidaoClient;

    @GetMapping
    public List<CertidaoDTO> findAll(){
        return certidaoClient.findAll();
    }
}
