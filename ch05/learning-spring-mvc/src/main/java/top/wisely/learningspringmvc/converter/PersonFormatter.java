package top.wisely.learningspringmvc.converter;

import org.springframework.format.Formatter;
import top.wisely.learningspringmvc.domain.Person;

import java.text.ParseException;
import java.util.Locale;

public class PersonFormatter implements Formatter<Person> {

    @Override
    public Person parse(String text, Locale locale) throws ParseException {
        String[] personStr = text.split("\\|");
        Long id = Long.valueOf(personStr[0]);
        String name = personStr[1];
        Integer age = Integer.valueOf(personStr[2]);
        return new Person(id, name, age);
    }

    @Override
    public String print(Person object, Locale locale) {
        return object.toString();
    }
}
