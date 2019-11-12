package top.wisely.learningspringmvc.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import top.wisely.learningspringmvc.domain.AnotherPerson;
import top.wisely.learningspringmvc.domain.Person;
import top.wisely.learningspringmvc.service.TaskService;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncController {

    private static Map<Long, DeferredResult<String>> deferredResultMap = new HashMap<>();
    private static Map<Long, ResponseBodyEmitter> responseBodyEmitterMap = new HashMap<>();
    private static Map<Long, SseEmitter> sseEmitterMap = new HashMap<>();


    @Autowired
    private TaskExecutor deferredTaskExecutor;

    private TaskService taskService;

    public AsyncController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/callable")
    public Callable<String> callable(){
        log.info("+++++servlet线程已释放+++++");
        return taskService::callableTask;
//        return () -> {
//            Thread.sleep(5000);
//            log.info("+++++Callable数据返回+++++");
//            return "result from Callable";
//        };
    }


    @GetMapping("/{id}/deferred")
    public DeferredResult<String> deferred(@PathVariable Long id) throws InterruptedException {
        log.info("+++++servlet线程已释放+++++");
        DeferredResult<String> deferredResult = new DeferredResult();
        deferredResultMap.put(id, deferredResult);
        return deferredResult;
    }

    @GetMapping("/{id}/invoke-deferred")
    public void  invokeDeferred(@PathVariable Long id){
        DeferredResult<String> deferredResult = deferredResultMap.get(id);
        CompletableFuture.supplyAsync(taskService::deferredTask, deferredTaskExecutor)
                .whenCompleteAsync((result,throwable) -> deferredResult.setResult(result));
        deferredResultMap.remove(id);
    }

    @GetMapping(value = "/{id}/rbe")
    public ResponseEntity<ResponseBodyEmitter> responseBodyEmitter(@PathVariable Long id){
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        responseBodyEmitterMap.put(id, emitter);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_HTML)
                .body(emitter);

    }

    @GetMapping("/{id}/invoke-rbe")
    public void invokeResponseBodyEmitter(@PathVariable Long id) throws Exception {
        ResponseBodyEmitter emitter = responseBodyEmitterMap.get(id);
        emitter.send("Hello World", MediaType.TEXT_PLAIN);
        Thread.sleep(1000);
        emitter.send(new Person(1l, "wyf", 35), MediaType.APPLICATION_JSON);
        Thread.sleep(1000);
        emitter.send(new Person(2l,"foo", 40), MediaType.APPLICATION_XML);
        Thread.sleep(1000);
        emitter.send(new AnotherPerson(3l,"bar", 50), new MediaType("application","another-person"));
    }

    @GetMapping("/{id}/close-rbe")
    public void closeResponseBodyEmitter(@PathVariable Long id) throws IOException {
        ResponseBodyEmitter emitter = responseBodyEmitterMap.get(id);
        emitter.complete();
        responseBodyEmitterMap.remove(id);
    }


    @GetMapping(value = "/{id}/sse", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter sseEmitter(@PathVariable Long id){
        SseEmitter emitter = new SseEmitter();
        sseEmitterMap.put(id, emitter);
        return emitter;
    }

    @GetMapping("/{id}/invoke-sse")
    public void invokeSseEmitter(@PathVariable Long id) throws Exception {
        SseEmitter emitter = sseEmitterMap.get(id);
        emitter.send(new AnotherPerson(3l,"bar", 50), new MediaType("application","another-person"));
        Thread.sleep(1000);
        emitter.send(new Person(1l, "wyf", 35), MediaType.APPLICATION_JSON);
        Thread.sleep(1000);
        emitter.send(new Person(2l,"foo", 40), MediaType.APPLICATION_XML);
        Thread.sleep(1000);
        emitter.send("Hello World", MediaType.TEXT_PLAIN);
    }

    @GetMapping("/{id}/close-sse")
    public void closeSseEmitter(@PathVariable Long id) throws IOException {
        SseEmitter emitter = sseEmitterMap.get(id);
        emitter.complete();
        responseBodyEmitterMap.remove(id);
    }

    @Value("classpath:wyn.jpg")
    private Resource image;

    @GetMapping("/img")
    public ResponseEntity<StreamingResponseBody> streamingResponseBody() {
       return ResponseEntity.ok()
               .contentType(MediaType.IMAGE_JPEG)
               .body(outputStream -> {
                   IOUtils.copy(image.getInputStream(), outputStream);
               });
    }

    @GetMapping("/sync-img")
    public ResponseEntity<Resource>  syncImage() {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

    @Value("classpath:/static/push/push.html")
    private Resource pushPage;

    @GetMapping(value = "/http2-push")
    public Resource http2Push(PushBuilder pushBuilder){
        pushBuilder
                .path("push/wyn.jpg")
//                .addHeader("content-type", MediaType.IMAGE_JPEG_VALUE)
                .push();
        pushBuilder
                .path("push/push.css")
//                .addHeader("content-type", "text/css")
                .push();
        pushBuilder
                .path("push/push.js")
//                .addHeader("content-type", "application/javascript")
                .push();
        return pushPage;
    }

    @GetMapping("/x")
    public String x(RequestEntity entity){
        return "x";
    }

}
