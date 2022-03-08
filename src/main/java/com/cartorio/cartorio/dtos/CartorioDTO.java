package com.cartorio.cartorio.dtos;

import com.cartorio.cartorio.entities.Cartorio;
import com.cartorio.cartorio.entities.Certidao;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CartorioDTO {

    private Long id;
    private String nome;
    private String endereco;
    private List<CertidaoDTO> certidoes = new ArrayList<>();

    public CartorioDTO(Cartorio entity){
        id = entity.getId();
        nome = entity.getNome();
        endereco = entity.getEndereco();
    }

    public CartorioDTO(Cartorio entity, Set<Certidao> categories) {
        this(entity);
        categories.forEach(cat -> this.certidoes.add(new CertidaoDTO(cat)));
    }
}
