package com.cartorio.cartorio.dtos;

import com.cartorio.cartorio.entities.Certidao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CertidaoDTO {

    private Long id;
    private String nome;

    public CertidaoDTO(Certidao entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
    }
}
