package com.reservation.fum.components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    @Nullable
    public LocalDate convert(String source) {
        log.info("Converting String" + source + " to LocalDate");
        return LocalDate.parse(source, DateTimeFormatter.ISO_DATE);
    }

}
