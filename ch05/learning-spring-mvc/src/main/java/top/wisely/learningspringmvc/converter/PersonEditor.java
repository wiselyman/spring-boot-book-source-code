package top.wisely.learningspringmvc.converter;

import top.wisely.learningspringmvc.domain.Person;

import java.beans.PropertyEditorSupport;

public class PersonEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] personStr = text.split("-");
        Long id = Long.valueOf(personStr[0]);
        String name = personStr[1];
        Integer age = Integer.valueOf(personStr[2]);
        setValue(new Person(id, name, age));
    }

}
