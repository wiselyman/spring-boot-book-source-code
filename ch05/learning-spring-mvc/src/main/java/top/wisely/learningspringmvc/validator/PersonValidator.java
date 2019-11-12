package top.wisely.learningspringmvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import top.wisely.learningspringmvc.domain.Person;


public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        validateId(person, errors);
        validateName(person, errors);
        validateAge(person, errors);
    }
    private void validateId(Person person, Errors errors){
        ValidationUtils.rejectIfEmpty(errors,"id", "person.code", "id不能为空-自定义");
    }
    private void validateName(Person person, Errors errors){
        int nameLength = person.getName().length();
        if (nameLength < 3 || nameLength > 5)
            errors.rejectValue("name", "person.name", "name在3到5个字符之间-自定义");
    }
    private void validateAge(Person person, Errors errors){
        if (person.getAge() < 18)
            errors.rejectValue("age", "person.age", "age不能低于18岁-自定义");
    }
}
