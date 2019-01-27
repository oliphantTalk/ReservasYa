package com.ttps.reservasya.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

@Component
public class CustomObjectMapper {

    private static ObjectMapper mapper; // ya es el de jackson

    public  static ObjectMapper getMapper() {

        if (mapper == null) {
            new CustomObjectMapper(new ObjectMapper());
        }
        return mapper;
    }

    private CustomObjectMapper(ObjectMapper objMapper) {
        mapper = objMapper;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.findAndRegisterModules();

    }


}
