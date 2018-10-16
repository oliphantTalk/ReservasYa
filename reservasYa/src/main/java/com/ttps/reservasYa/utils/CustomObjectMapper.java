package com.ttps.reservasYa.utils;
import com.fasterxml.jackson.databind.*;

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
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }


}
