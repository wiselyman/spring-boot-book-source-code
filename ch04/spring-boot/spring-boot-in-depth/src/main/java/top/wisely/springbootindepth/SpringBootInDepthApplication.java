package top.wisely.springbootindepth;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.wiselyman.service.DemoService2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.info.GitProperties;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.wisely.springbootindepth.author.AuthorProperties;
import top.wisely.springbootindepth.listener.MyListener;
import top.wisely.springbootindepth.service.DemoService;
import top.wisely.springbootindepth.task.AsyncTask;

import java.util.Map;
import java.util.stream.Stream;

//@SpringBootApplication(scanBasePackages = {"io.github.wiselyman",
//		                                   "top.wisely.springbootindepth"})
//@SpringBootApplication(exclude = SpringApplicationAdminJmxAutoConfiguration.class)
@SpringBootApplication
@RestController
@EnableAsync
@EnableScheduling
@ConfigurationPropertiesScan({ "com.some.other", "top.wisely" })
public class SpringBootInDepthApplication {

	@RequestMapping("/")
	public String testDemo(DemoService demoService){
		return demoService.doSomething();
	}

	public static void main(String[] args) {


		SpringApplication.run(SpringBootInDepthApplication.class, args);

//		SpringApplication app = new SpringApplication(SpringBootInDepthApplication.class);
//		app.setBannerMode(Banner.Mode.OFF);
//		app.addListeners(new MyListener());
//		app.run(args);
//
//		new SpringApplicationBuilder()
//				.bannerMode(Banner.Mode.OFF)
//				.listeners(new MyListener())
//				.sources(SpringBootInDepthApplication.class)
//				.build(args)
//				.run();

	}


//	@Bean
//	CommandLineRunner commandLineRunner(DemoService demoService, DemoService2 demoService2){
//		return args -> {
//			demoService.doSomething();
//			demoService2.doSomething();
//		};
//	}

//	@Value("${server.ip}")
//	private String serverIp;
//
//	@Value("${some.value}")
//	private String someValue;
//
//	@Value("${some.number}")
//	private String someNumber;
//
//	@Bean
//	CommandLineRunner commandLineRunner(@Value("${server.port}") String serverPort){
//		return args -> {
//			Stream.of(args).forEach(System.out::println);
//			System.out.println(serverPort);
//			System.out.println(serverIp);
//			System.out.println(someValue);
//			System.out.println(someNumber);
//		};
//	}




//	@Bean
	CommandLineRunner placeholderClr(@Value("${app.name}") String name,
									 @Value("${app.desc}") String desc){
		return args -> {
			System.out.println(name);
			System.out.println(desc);
		};
	}



//	@Bean
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		return builder.indentOutput(true).createXmlMapper(false).build();
	}

//	@Bean
	CommandLineRunner reBeanClr(ObjectMapper jacksonObjectMapper){
		return args -> {
			AuthorProperties.Book book = new AuthorProperties.Book();
			book.setName("book3");
			book.setPrice(119);
			String content = jacksonObjectMapper.writeValueAsString(book);
			System.out.println(content);
		};
	}

//	@Bean
	CommandLineRunner environmentPostProcessorClr(@Value("${key1}") String value1,
												  @Value("${key2}") String value2){
		return args -> {
			System.out.println(value1);
			System.out.println(value2);
		};
	}

	@Bean
	ThreadPoolTaskExecutor customTaskExecutor(TaskExecutorBuilder taskExecutorBuilder){
		return taskExecutorBuilder
				.threadNamePrefix("customTask-")
				.corePoolSize(5)
				.build();
	}

	@Bean
	ThreadPoolTaskExecutor myTaskExecutor(TaskExecutorBuilder taskExecutorBuilder){
		return taskExecutorBuilder
				.threadNamePrefix("myTask-")
				.corePoolSize(6)
				.build();
	}

//	@Bean
	CommandLineRunner asyncTaskClr(AsyncTask asyncTask){
		return args -> {
			for(int i = 0; i < 10 ;i++){
				asyncTask.loopPrint(i);
			}

			for(int i = 0; i < 10 ;i++){
				asyncTask.loopPrint2(i);
			}
		};
	}

}
