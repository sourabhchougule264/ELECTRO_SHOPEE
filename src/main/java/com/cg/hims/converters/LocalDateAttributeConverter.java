package com.cg.hims.converters;

import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, String> {

    @Override
    public String convertToDatabaseColumn(LocalDate locDate) {
        return locDate == null ? null : locDate.toString();
    }

    @Override
    public LocalDate convertToEntityAttribute(String sqlDate) {
        return sqlDate == null ? null : LocalDate.parse(sqlDate);
    }
}