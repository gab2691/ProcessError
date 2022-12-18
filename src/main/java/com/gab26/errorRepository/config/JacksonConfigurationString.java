package com.gab26.errorRepository.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfigurationString extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser parser, DeserializationContext ctx){
        try {
            return parser.getText().isEmpty() ? null : parser.getText();
        } catch (Exception e) {
            return null;
        }
    }

}
