package com.reservation.fum.components;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StringToLocalTimeConverter implements Converter<String,LocalTime> {

    @Override
    @Nullable
    public LocalTime convert(String source) {
        log.info("Converting String" + source + " to LocalTime");
        return LocalTime.parse(source, DateTimeFormatter.ofPattern("HH:mm"));
    }
    
}
