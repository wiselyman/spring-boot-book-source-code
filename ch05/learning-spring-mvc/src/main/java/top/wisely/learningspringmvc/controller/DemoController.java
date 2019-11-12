package top.wisely.learningspringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wisely.learningspringmvc.annotation.RequestPerson;
import top.wisely.learningspringmvc.annotation.ResponsePerson;
import top.wisely.learningspringmvc.domain.AnotherPerson;
import top.wisely.learningspringmvc.domain.Person;
import top.wisely.learningspringmvc.service.DemoService;

@Controller
@RequestMapping("/demo")
public class DemoController {

    private DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
       return "Hello World !!!";
    }


    @GetMapping("/bean-argument")
    public void argument(DemoService demoService){
        System.out.println("this.demoService和方法参数demoService是否相等：" + this.demoService.equals(demoService));
    }


    @GetMapping("/argument")
    @ResponsePerson
    public AnotherPerson argument(@RequestPerson AnotherPerson person){
        return person;
    }


}
