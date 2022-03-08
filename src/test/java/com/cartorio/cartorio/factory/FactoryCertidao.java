package com.cartorio.cartorio.factory;

import com.cartorio.cartorio.dtos.CertidaoDTO;
import com.cartorio.cartorio.entities.Certidao;


public class FactoryCertidao {

    public static CertidaoDTO createdProduct() {
        Certidao certidao = new Certidao(1L, "2° Via de Certidão de Casamento",null);
        return new CertidaoDTO(certidao);
    }
}
