package top.wisely.learningspringdatajpa.domain.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;

//@Converter(autoApply = true)
public class SchoolConverter implements AttributeConverter<Object, String> {


    @Override
    public String convertToDatabaseColumn(Object attribute) {
        return new ObjectMapper().convertValue(attribute, String.class);
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        return new ObjectMapper().convertValue(dbData, Object.class);
    }
}
