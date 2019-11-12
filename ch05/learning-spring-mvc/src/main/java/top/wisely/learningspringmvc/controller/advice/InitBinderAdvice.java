package top.wisely.learningspringmvc.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import top.wisely.learningspringmvc.domain.Person;
import top.wisely.learningspringmvc.converter.PersonEditor;
import top.wisely.learningspringmvc.converter.PersonFormatter;
import top.wisely.learningspringmvc.validator.PersonValidator;

@ControllerAdvice
@Slf4j
public class InitBinderAdvice {

//    @InitBinder
    public void registerPersonEditor(WebDataBinder binder, @RequestBody String person){
        log.info("在InitBinder中为字符串：" + person);
        binder.registerCustomEditor(Person.class, new PersonEditor());
    }
//    @InitBinder
    public void addPersonFormatter(WebDataBinder binder){
       binder.addCustomFormatter(new PersonFormatter());
    }

//    @InitBinder
    public void setPersonValidator(WebDataBinder binder){
        binder.setValidator(new PersonValidator());
    }

}
