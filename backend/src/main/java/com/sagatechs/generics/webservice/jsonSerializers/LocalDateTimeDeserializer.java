package com.sagatechs.generics.webservice.jsonSerializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {


    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        String date = jsonParser.getText();

        if (StringUtils.isEmpty(date)) return null;
        //2019-11-19T00:00:00.000Z

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        return LocalDateTime.parse(date, formatter);
    }
}