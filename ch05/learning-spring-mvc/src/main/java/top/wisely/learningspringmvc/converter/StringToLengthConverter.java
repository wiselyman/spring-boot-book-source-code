package top.wisely.learningspringmvc.converter;


import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.Converter;
import top.wisely.learningspringmvc.annotation.StrLength;

import java.lang.Integer;

public class StringToLengthConverter implements Converter<String, Integer> , ConditionalConverter {
    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source.length());
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return targetType.hasAnnotation(StrLength.class);
    }
}
