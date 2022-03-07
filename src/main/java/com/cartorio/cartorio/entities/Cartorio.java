package com.cartorio.cartorio.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Cartorio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    @JoinColumn(name="certidao_id")
    private List<Certidao> certidaos;
    public Cartorio() {
    }
    public Cartorio(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Certidao> getCertidaos() {
        return certidaos;
    }

    public void setCertidaos(List<Certidao> certidaos) {
        this.certidaos = certidaos;
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
