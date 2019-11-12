package top.wisely.learningspringmvc.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.wisely.learningspringmvc.controller.DemoController;
import top.wisely.learningspringmvc.controller.PeopleController;
import top.wisely.learningspringmvc.exception.PersonNameNotFoundException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(PersonNameNotFoundException.class)
    public ResponseEntity<String> exceptionHandler(PersonNameNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getName() + "没有找到！");
    }

}
