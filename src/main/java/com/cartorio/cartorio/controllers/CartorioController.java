package com.cartorio.cartorio.controllers;

import com.cartorio.cartorio.dtos.CartorioDTO;
import com.cartorio.cartorio.entities.Cartorio;
import com.cartorio.cartorio.services.CartorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cartorios")
public class CartorioController {

    @Autowired
    private CartorioService service;

    @GetMapping
    public ResponseEntity<Page<CartorioDTO>> findAllpage( Pageable pageable) {
        Page<CartorioDTO> list = service.findAllPage(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<Cartorio>> findAll() {
        List<Cartorio> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CartorioDTO> findById(@PathVariable Long id) {
        CartorioDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CartorioDTO> insert(@RequestBody CartorioDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CartorioDTO> update(@PathVariable Long id, @RequestBody CartorioDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
