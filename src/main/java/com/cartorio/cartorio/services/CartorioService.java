package com.cartorio.cartorio.services;

import com.cartorio.cartorio.client.CertidaoClient;
import com.cartorio.cartorio.dtos.CartorioDTO;
import com.cartorio.cartorio.dtos.CertidaoDTO;
import com.cartorio.cartorio.entities.Cartorio;
import com.cartorio.cartorio.entities.Certidao;
import com.cartorio.cartorio.repositories.CartorioRepository;
import com.cartorio.cartorio.services.exceptions.DatabaseException;
import com.cartorio.cartorio.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class CartorioService {

    @Autowired
    private CartorioRepository repository;

    @Autowired
    private CertidaoClient client;

    @Transactional(readOnly = true)
    public Page<CartorioDTO> findAllPaged(Long categoryId, String name, Pageable pageable) {
        List<Certidao> categories = (categoryId == 0) ? null : Arrays.asList(categoryRepository.getOne(categoryId));
        Page<Product> page = repository.find(categories, name, pageable);
        repository.findProductsWithCategories(page.getContent());
        return page.map(x -> new ProductDTO(x, x.getCategories()));
    }

    @Transactional(readOnly = true)
    public CartorioDTO findById(Long id) {
        Optional<Cartorio> obj = repository.findById(id);
        Cartorio entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CartorioDTO(entity);
    }

    @Transactional
    public CartorioDTO insert(CartorioDTO dto) {
        Cartorio entity = new Cartorio();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CartorioDTO(entity, entity.getCertidoes());
    }

    @Transactional
    public CartorioDTO update(Long id, CartorioDTO dto) {
        try {
            Cartorio entity = repository.getOne(id);;
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new CartorioDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }
//
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(CartorioDTO dto, Cartorio entity) {
        entity.setCertidoes(new HashSet<>());
        entity.setNome(dto.getNome());
        entity.setEndereco(dto.getEndereco());
        for (CertidaoDTO catDto : dto.getCertidoes()) {
            Certidao certidao = new Certidao();
            certidao.setNome(catDto.getNome());
            entity.getCertidoes().add(certidao);
        }
    }
}
