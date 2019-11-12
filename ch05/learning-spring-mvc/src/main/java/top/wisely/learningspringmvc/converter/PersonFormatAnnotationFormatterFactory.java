package top.wisely.learningspringmvc.converter;

import org.springframework.context.support.EmbeddedValueResolutionSupport;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import top.wisely.learningspringmvc.annotation.PersonFormat;
import top.wisely.learningspringmvc.domain.Person;

import java.util.*;

public class PersonFormatAnnotationFormatterFactory extends EmbeddedValueResolutionSupport
        implements AnnotationFormatterFactory<PersonFormat> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> fieldTypes = new HashSet<>(1);
        fieldTypes.add(Person.class);
        return  Collections.unmodifiableSet(fieldTypes);
    }

    @Override
    public Parser<?> getParser(PersonFormat annotation, Class<?> fieldType) {
        if(annotation.style())
            return new PersonFormatter();
        else
            return new AnotherPersonFormatter();
    }

    @Override
    public Printer<?> getPrinter(PersonFormat annotation, Class<?> fieldType) {
        if(annotation.style())
            return new PersonFormatter();
        else
            return new AnotherPersonFormatter();
    }


}
