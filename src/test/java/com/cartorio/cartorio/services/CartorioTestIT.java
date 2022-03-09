package com.cartorio.cartorio.services;


import com.cartorio.cartorio.dtos.CartorioDTO;
import com.cartorio.cartorio.repositories.CartorioRepository;
import com.cartorio.cartorio.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CartorioTestIT {

    @Autowired
    private CartorioService service;

    @Autowired
    private CartorioRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalCartorios;

    @BeforeEach
    public void setup()  {
        existingId = 1L;
        nonExistingId = 100000L;
        countTotalCartorios = 2L;
    }

    public void deleteShouldDeleteResourceWhenIdExists(){
        service.delete(existingId);
        Assertions.assertEquals(countTotalCartorios -1, repository.count());

    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists(){
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> {

            service.delete(nonExistingId);

        });
    }

    @Test
    public void findAllPagedShouldReturnWhenPage0Size10(){

        Pageable pePageable = PageRequest.of(0, 10);
        PageRequest  pageRequest =  PageRequest.of(0,10);
        Page<CartorioDTO> result = service.findAllPage(pePageable);

        Assertions.assertEquals(0,result.getNumber());
        Assertions.assertEquals(10,result.getSize());

    }

    @Test
    public void findAllPagedShouldReturnResultEmptyPageWhenPageDoesNotExists(){

        PageRequest  pageRequest =  PageRequest.of(50,10 );
        Page<CartorioDTO> result = service.findAllPage( pageRequest);

        Assertions.assertTrue(result.isEmpty());

    }

}
