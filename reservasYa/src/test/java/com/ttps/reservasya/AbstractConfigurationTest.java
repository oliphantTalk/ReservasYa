package com.ttps.reservasya;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.ObjectMapperConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.mapper.factory.Jackson2ObjectMapperFactory;
import com.ttps.reservasya.utils.CustomObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;

import org.junit.Before;

abstract public class AbstractConfigurationTest {

    private String url = "http://buses.despegar.it/buses-dbs";
    private ObjectMapper objectMapper = CustomObjectMapper.getMapper();
    private String uowKey = RandomStringUtils.randomAlphabetic(10);


    public String getUrl() {
        return url;
    }

    public String getUowKey(){
        return uowKey;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Before
    public void setUp() {

        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                new ObjectMapperConfig().jackson2ObjectMapperFactory(new Jackson2ObjectMapperFactory() {
                    @Override
                    public ObjectMapper create(Class aClass, String s) {
                        return AbstractConfigurationTest.this.objectMapper;
                    }
                }));

    }


}
