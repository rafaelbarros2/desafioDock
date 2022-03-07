package com.cartorio.cartorio.entities;

import java.util.Objects;

public class Cartorio {

    private Long id;
    private String name;

    public Cartorio() {
    }

    public Cartorio(Long id, String name) {
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
        if (!(o instanceof Cartorio)) return false;
        Cartorio cartorio = (Cartorio) o;
        return id.equals(cartorio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
