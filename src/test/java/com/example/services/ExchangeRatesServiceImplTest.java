package com.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations="classpath:application_test.properties")
class ExchangeRatesServiceImplTest {

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void startServer() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
    }

    @AfterAll
    static void stopServer() {
        wireMockServer.stop();
    }

    @Test
    void testCurrencyFromFileAndFromService() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File fileWithJson = ResourceUtils.getFile("classpath:rate05.06.2022.json");
        Double rateFromFile = mapper.readTree(fileWithJson).get("rates").get("RUB").asDouble();
        wireMockServer.stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "  \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
                                "  \"license\": \"https://openexchangerates.org/license\",\n" +
                                "  \"timestamp\": 1610323198,\n" +
                                "  \"base\": \"USD\",\n" +
                                "  \"rates\": {\n" +
                                "    \"EUR\": 0.932444,\n" +
                                "    \"RUB\": 63.250003\n" +
                                "  }\n" +
                                "}")));
        Double rateFromService = exchangeRatesService.getDateRate("RUB", "2022-06-05");
        Assertions.assertEquals(rateFromFile, rateFromService);
    }
}