package com.cartorio.cartorio.repositories;

import com.cartorio.cartorio.entities.Cartorio;
import com.cartorio.cartorio.entities.Certidao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartorioRepository extends JpaRepository<Cartorio, Long> {


}
