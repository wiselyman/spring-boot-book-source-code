package top.wisely.learningspringmvc.controller;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import top.wisely.learningspringmvc.annotation.*;
import top.wisely.learningspringmvc.domain.AnotherPerson;
import top.wisely.learningspringmvc.domain.Person;
import top.wisely.learningspringmvc.domain.SecondPerson;
import top.wisely.learningspringmvc.exception.PersonNameNotFoundException;
import top.wisely.learningspringmvc.repository.PersonRepository;
import top.wisely.learningspringmvc.service.DemoService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/people")
@Slf4j
public class PeopleController {
    private PersonRepository personRepository;

    public PeopleController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Person save(@RequestBody Person person){
//        personRepository.save(person);
//        return person;
//    }

    @PostMapping
    public ResponseEntity<Person> save(RequestEntity<Person> personRequestEntity){
        Person p = personRepository.save(personRequestEntity.getBody());
        return new ResponseEntity<Person>(p, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Person getOne(@PathVariable Long id){
        return personRepository.findOne(id);
    }

    @PutMapping("/{id}")
    public Person replace(@PathVariable Long id, @RequestBody Person person){
        return personRepository.replace(id, person);
    }

    @PatchMapping("/{id}")
    public Person patch(@PathVariable Long id, @RequestBody Person person) {
        return personRepository.patched(id, person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        personRepository.delete(id);
    }


    @GetMapping("/findByName")
    public Person findByName(@RequestParam String name){
        return personRepository.findByName(name);
    }

    @GetMapping("/getRequestHeaders")
    public String[] getHeaders(@RequestHeader HttpHeaders httpHeaders,
                               @RequestHeader Map<String, String> headerMap,
                               @RequestHeader MultiValueMap<String, String> multiValueMap,
                               @RequestHeader("my-header") String myHeader,
                               RequestEntity requestEntity) {
        String userAgent = httpHeaders.getFirst("user-agent");
        String host = headerMap.get("host");
        String cacheControl = multiValueMap.getFirst("cache-control");
        HttpHeaders sameHttpHeaders = requestEntity.getHeaders();
        return new String[]{userAgent, host, cacheControl, myHeader};
    }

    @GetMapping("/responseEntityShowcase")
    public ResponseEntity<Person> responseEntityShowcase(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("my-header", "hello header");
        return new ResponseEntity<Person>(new Person(1234l,"bar", 22),
                responseHeaders,
                HttpStatus.OK);
//        return ResponseEntity.ok()
//                .header("my-header", "hello header")
//                .body(new Person(1234l,"bar", 22));
    }

    @GetMapping("/getCookie")
    public String[] getCookie(@CookieValue("myCookie") String myCookie,
                            @CookieValue("anotherCookie") String anotherCookie) {
        return new String[]{myCookie, anotherCookie};
    }

    @GetMapping("/methodArguments")
    public void methodArguments(WebRequest request,
                                  NativeWebRequest nativeWebRequest,
                                  ServletRequest servletRequest,
                                  ServletResponse servletResponse,
                                  HttpSession httpSession,
                                  Locale locale,
                                  TimeZone timeZone,
                                  ZoneId zoneId,
                                  DemoService demoService) throws Exception{

        System.out.println(request);
        System.out.println(nativeWebRequest);
        System.out.println(servletRequest);
        System.out.println(servletResponse);
        System.out.println(httpSession);
        System.out.println(locale);
        System.out.println(timeZone);
        System.out.println(zoneId);
        System.out.println(demoService.sayHello());
    }

    @PostMapping("/upload")
    public ResponseEntity<Resource> upload(MultipartFile file,
                                           @RequestPart("file") MultipartFile file2,
                                           @RequestParam("file") MultipartFile file3,
                                           @RequestParam("file")Part part
                                           ) throws Exception{

        System.out.println(file.equals(file2));
        System.out.println(file.equals(file3));
        System.out.println(file.getSize() == part.getSize());

        Resource imageResource = new InputStreamResource(file.getInputStream());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageResource);

    }

    @PostMapping("/multipleUpload")
    public String uploads(@RequestParam Map<String, MultipartFile> fileMap){
        StringBuilder info = new StringBuilder();
        fileMap.forEach((key, file) -> {
         info.append(file.getName()).append("'s length is ").append(file.getSize()).append("\n");
        });
        return info.toString();
    }

    @PostMapping("/validate")
    public ResponseEntity validate(@Valid @RequestBody Person person, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest()
                    .body(result.getAllErrors());
        }
        return ResponseEntity.ok()
                .body("everything is fine");
    }

    @GetMapping("/exceptions")
    public void exceptions(String name)  {
        throw new PersonNameNotFoundException(name);
    }

    @GetMapping("/propertyEditor")
    public Person propertyEditor(@RequestParam Person person){
        log.info("经过InitBinder注册的PropertyEditor转换后为对象：" + person);
        return person;
    }


    @GetMapping("/formatter")
    public Person formatter(@RequestParam Person person){
        return person;
    }

    @GetMapping("/modifyBodies")
    @ProcessTag
    public Person modifyBodies(@ProcessTag @RequestBody Person person){
        return person;
    }

    @GetMapping("/jsonView")
    @JsonView(Person.WithoutIdView.class)
    public Person jsonView(@RequestBody Person person){
        return person;
    }

    @GetMapping("/json")
    public SecondPerson json(@RequestBody SecondPerson person){
        return person;
    }

    @GetMapping("/converter")
    public AnotherPerson converter(@RequestBody AnotherPerson person){
        return person;
    }

//    @GetMapping(value = "/http_message_converter", produces = {"application/another-person"})
//    public AnotherPerson http_message_converter(@RequestBody AnotherPerson person){
//        return person;
//    }


    @GetMapping("/{id}/convert")
    public Map<String, Object> convert(@PathVariable Long id,
                                       @RequestParam Boolean testBool,
                                       @RequestBody Person person){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("date", testBool);
        return map;
    }

    @GetMapping("/convert")
    public Integer convert(@StrLength Integer person){
        return person;
    }

    @GetMapping("/annoFormatter")
    public Map<String, Object> annoFormatter(@DateTimeFormat(pattern = "dd/MM/yyyy")Date date,
                              @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
                              @NumberFormat(style = NumberFormat.Style.CURRENCY) BigDecimal num,
                              @NumberFormat(style = NumberFormat.Style.PERCENT) BigDecimal num1
                          ){
        Map<String, Object> map = new HashMap<>();
        map.put("date", date);
        map.put("date1", date1);
        map.put("number", num);
        map.put("number1", num1);
        return map;
    }

    @GetMapping("/customAnnoFormatter")
    public Map<String, Person> customAnnoFormatter(@PersonFormat @RequestParam Person person,
                                      @PersonFormat(style = false) @RequestParam Person person1){
        Map<String, Person> map = new HashMap<>();
        map.put("person", person);
        map.put("person1", person1);
        return map;
    }

    @GetMapping("/content")
    public AnotherPerson content(@RequestBody AnotherPerson person){
        return person;
    }


}
