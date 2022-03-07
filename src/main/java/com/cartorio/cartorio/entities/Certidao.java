package com.cartorio.cartorio.entities;

import java.util.Objects;

public class Certidao {

    private Long id;
    private String nome;

    public Certidao() {
    }

    public Certidao(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setName(String name) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certidao)) return false;
        Certidao certidao = (Certidao) o;
        return id.equals(certidao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
