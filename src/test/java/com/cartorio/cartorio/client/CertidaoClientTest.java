package com.cartorio.cartorio.client;

import com.cartorio.cartorio.dtos.CertidaoDTO;
import com.cartorio.cartorio.factory.Factory;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.util.List;

public class CertidaoClientTest {

    private static String urlBase = "https://docketdesafiobackend.herokuapp.com/api/v1";
    private static String response = "[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"nome\": \"2° Via de Certidão de Casamento\"\n" +
            "    }]";
    private CertidaoDTO dto;
    private CertidaoClient client;

    @BeforeEach
    public void setup() {
        dto = Factory.createdCertidaoDTO();
    }

    private void builderClientOpenFeingClient(MockClient mockClient){
        client = Feign.builder()
                .client(mockClient)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringMvcContract())
                .target(CertidaoClient.class, urlBase);
    }

    @Test
    public void whenGetCertidaoClientTheReturnOk(){
        this.builderClientOpenFeingClient(new MockClient().ok(
                HttpMethod.GET, urlBase.concat("/certidoes"),
                response
        ));

        List<CertidaoDTO> list = client.findAll();
        Assertions.assertFalse(list.isEmpty());
        Assertions.assertEquals(list.get(0),dto);
    }
}
