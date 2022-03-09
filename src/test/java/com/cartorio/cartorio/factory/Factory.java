package com.cartorio.cartorio.factory;

import com.cartorio.cartorio.dtos.CartorioDTO;
import com.cartorio.cartorio.dtos.CertidaoDTO;
import com.cartorio.cartorio.entities.Cartorio;
import com.cartorio.cartorio.entities.Certidao;


public class Factory {

    public static CertidaoDTO createdCertidaoDTO() {
        Certidao certidao = new Certidao(1L, "2° Via de Certidão de Casamento",null);
        return new CertidaoDTO(certidao);
    }

    public static Certidao createdCertidao() {
        Certidao certidao = new Certidao(1L, "2° Via de Certidão de Casamento",null);
        return certidao;
    }

    public static Cartorio createdCartorio() {
        Cartorio cartorio = new Cartorio(1L, "Alguem", "bem ali");
        cartorio.getCertidoes().add(createdCertidao());
        return cartorio;
    }

    public static CartorioDTO createdCartorioDTO(){
        Cartorio cartorio = createdCartorio();
        return new CartorioDTO(cartorio, cartorio.getCertidoes());
    }
}
