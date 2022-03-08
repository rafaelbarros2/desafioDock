package com.cartorio.cartorio.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Cartorio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nome;
    private String endereco;
    @OneToMany(mappedBy = "cartorio",cascade = CascadeType.ALL)
    private Set<Certidao> certidoes = new HashSet<>();

    public void setCertidoes(Set<Certidao> certidoes) {
        this.certidoes = certidoes;

        for(Certidao b : certidoes) {
            b.setCartorio(this);
        }
    }
}
