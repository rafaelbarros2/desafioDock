package com.cartorio.cartorio.entities;

import java.util.Objects;

public class Certidao {

    private Long id;
    private String name;

    public Certidao() {
    }

    public Certidao(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
