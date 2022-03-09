package com.cartorio.cartorio.services;

import com.cartorio.cartorio.dtos.CartorioDTO;
import com.cartorio.cartorio.entities.Cartorio;
import com.cartorio.cartorio.entities.Certidao;
import com.cartorio.cartorio.factory.Factory;
import com.cartorio.cartorio.repositories.CartorioRepository;
import com.cartorio.cartorio.repositories.CertidaRepository;
import com.cartorio.cartorio.services.exceptions.DatabaseException;
import com.cartorio.cartorio.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class CartorioServiceTests {

    @InjectMocks
    private CartorioService service;

    @Mock
    private CartorioRepository repository;

    @Mock
    private CertidaRepository categoryRepository;

    private long existingId;
    private long nonExistingId;
    private long dependentId;
    private PageImpl<Cartorio> page;
    private Cartorio cartorio;
    private Certidao certidao;
    private CartorioDTO cartorioDTO;

    @BeforeEach
    public void setup()  {
        existingId = 1L;
        nonExistingId = 1000L;
        dependentId = 4L;
        cartorio = Factory.createdCartorio();
        certidao = Factory.createdCertidao();
        page = new PageImpl<>(List.of(cartorio));
        cartorioDTO = Factory.createdCartorioDTO();

        Mockito.when(repository.findAll((Pageable) any())).thenReturn(page);

        Mockito.when(repository.getOne(existingId)).thenReturn(cartorio);
        Mockito.when(repository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);

        Mockito.when(categoryRepository.getOne(existingId)).thenReturn(certidao);
        Mockito.when(categoryRepository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);

        Mockito.when(repository.save(any())).thenReturn(cartorio);
        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(cartorio));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());


        Mockito.doNothing().when(repository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);

    }

    @Test
    public void findByIdShouldReturnProductDTOWhenIdExists(){

        CartorioDTO result = service.findById(existingId);
        Assertions.assertNotNull(result);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists(){

        Assertions.assertThrows(ResourceNotFoundException.class, ()-> {

            service.findById(nonExistingId);

        });
    }

    @Test
    public void updateShouldReturnProductDTOWhenIdExists(){


        CartorioDTO result = service.update(existingId, cartorioDTO);
        Assertions.assertNotNull(result);
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists(){

        Assertions.assertThrows(ResourceNotFoundException.class, ()-> {

            service.update(nonExistingId, cartorioDTO);

        });
    }

    @Test
    public void deleteShouldDoNothingWhenIdExist() {

        Assertions.assertDoesNotThrow(() -> {
            service.delete(existingId);
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowEmptyResultResourceNotFoundExceptionWhenIdDoesNotExist(){

        Assertions.assertThrows(ResourceNotFoundException.class, ()->{
            service.delete((nonExistingId));
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId);
    }

    @Test
    public void deleteShouldThrowEmptyResultDatabaseExcptionWhenIdDoesNotExist(){

        Assertions.assertThrows(DatabaseException.class, ()->{
            service.delete((dependentId));
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(dependentId);
    }

}
