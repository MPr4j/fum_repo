package com.reservation.fum.components;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.reservation.fum.models.ServiceType;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ServiceTypeConverter implements Converter<String,ServiceType> {

    @Override
    @Nullable
    public ServiceType convert(String arg0) {
        log.info("Convert request from " + arg0);
        return ServiceType.valueOf(arg0);
    }

    
}
