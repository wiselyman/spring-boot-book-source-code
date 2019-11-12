package top.wisely.learningspringdatajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import static org.springframework.data.domain.ExampleMatcher.*;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import top.wisely.learningspringdatajpa.domain.model.*;
import top.wisely.learningspringdatajpa.domain.projection.PersonDto;
import top.wisely.learningspringdatajpa.domain.projection.PersonProjectionInterface;
import static top.wisely.learningspringdatajpa.domain.specification.CustomSpecs.*;
import top.wisely.learningspringdatajpa.domain.type.Gender;
import top.wisely.learningspringdatajpa.repository.AnotherPersonRepository;
import top.wisely.learningspringdatajpa.repository.EmployeeRepository;
import top.wisely.learningspringdatajpa.repository.PersonRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;



@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
@EnableSpringDataWebSupport
@Slf4j
public class LearningSpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringDataJpaApplication.class, args);
	}

	@Bean
	AuditorAware<String> auditorProvider(){
		return () -> Optional.of("wyf");
	}

//	@Bean
	CommandLineRunner saveOne(PersonRepository personRepository){
		return args -> {
			Address address = new Address("hefei", "Anhui");
			Collection<Child> children = Arrays.asList(new Child("wyn", Gender.FEMALE), new Child("wbe", Gender.MALE));
			Person person = new Person("wyf", 35, address, children);
			Person savedPerson = personRepository.save(person);
			log.info("-----" + person.toString() + "-----");
		};
	}

//	@Bean
	CommandLineRunner saveAll(PersonRepository personRepository){
		return args -> {
			Address address1 = new Address("beijing", "Beijing");
			Address address2 = new Address("shanghai", "Shanghai");
			Collection<Child> children1 = Arrays.asList(new Child("aaa", Gender.FEMALE), new Child("bbb", Gender.MALE));
			Collection<Child> children2 = Arrays.asList(new Child("ccc", Gender.FEMALE), new Child("ddd", Gender.MALE));
			Person person1 = new Person("foo", 36, address1, children1);
			Person person2 = new Person("bar", 34, address2, children1);
			List<Person> savedPeople = personRepository.saveAll(Arrays.asList(person1, person2));
			savedPeople.forEach(p ->{
				log.info(p.toString());
			});
		};
	}
//	@Bean
	CommandLineRunner delete(PersonRepository personRepository){
		return args -> {
			personRepository.deleteById(1l);
			log.info("-----剩余数量为" + personRepository.count() + "------");
			personRepository.delete(personRepository.getOne(2l));
			log.info("-----剩余数量为" + personRepository.count() + "------");
			personRepository.deleteAll();
			log.info("-----剩余数量为" + personRepository.count() + "------");
		};
	}

//	@Bean
	CommandLineRunner derivedQuery(PersonRepository personRepository){
		return args -> {
			List<Person> people1 = personRepository.findByName("wyf");
			List<Person> people2 = personRepository.findByNameAndAge("foo", 36);
			List<Person> people3 = personRepository.findDistinctPersonByName("bar");
			List<Person> people4 = personRepository.findByAddress_City("hefei");
			List<Person> people5 = personRepository.findByAddressCity("hefei");
			List<Person> people6 = personRepository.findByChildren_Name("ccc");
			List<Person> people7 = personRepository.findByChildrenName("ccc");
			people1.forEach(System.out::println);
			System.out.println("--------------");
			people2.forEach(System.out::println);
			System.out.println("--------------");
			people3.forEach(System.out::println);
			System.out.println("--------------");
			people4.forEach(System.out::println);
			System.out.println("--------------");
			people5.forEach(System.out::println);
			System.out.println("--------------");
			people6.forEach(System.out::println);
			System.out.println("--------------");
			people7.forEach(System.out::println);
			System.out.println("--------------");
		};
	}

//	@Bean
	CommandLineRunner namedQuery(PersonRepository personRepository){
		return args -> {
			List<Person> people1 = personRepository.findByNameWyf();
			people1.forEach(System.out::println);
		};
	}

//	@Bean
	CommandLineRunner jpqlQuery(PersonRepository personRepository){
		return args -> {
			List<Person> people1 = personRepository.findByJpql("wyf");
			people1.forEach(System.out::println);
		};
	}

//	@Bean
	CommandLineRunner nativeQuery(PersonRepository personRepository){
		return args -> {
			List<Person> people1 = personRepository.findBySql("wyf");
			people1.forEach(System.out::println);
		};
	}

//	@Bean
	CommandLineRunner sortQuery(PersonRepository personRepository){
		return args -> {
			List<Person> people1 = personRepository.findByAgeLessThan(40, Sort.by("name"));
			List<Person> people2 = personRepository.findByAgeLessThanWithJqal(40, JpaSort.by(Sort.Direction.DESC, "name"));
			List<Person> people3 = personRepository.findAll(Sort.by("address.city"));
			List<Person> people4 = personRepository.findAll(JpaSort.by(Sort.Direction.DESC, "age"));
			Page<Person> people5 = personRepository.findByAgeLessThan(40, PageRequest.of(0, 2, Sort.by("age")));
 			Page<Person> people6 = personRepository.findAll(PageRequest.of(0, 2, Sort.by("name")));

			people1.forEach(System.out::println);
			System.out.println("--------------");
			people2.forEach(System.out::println);
			System.out.println("--------------");
			people3.forEach(System.out::println);
			System.out.println("--------------");
			people4.forEach(System.out::println);
			System.out.println("--------------");
			people5.forEach(System.out::println);
			System.out.println("--------------");
			people6.forEach(System.out::println);
 		};
	}

//	@Bean
	CommandLineRunner namedParamQuery(PersonRepository personRepository){
		return args -> {
			List<Person> people = personRepository.findByJpqlWithNamedParameter("wyf");
			people.forEach(System.out::println);
		};
	}

//	@Bean
	CommandLineRunner modifyingQuery(PersonRepository personRepository){
		return args -> {
			int result = personRepository.updatePersonName("foooo", "foo");
			List<Person> people = personRepository.findByName("foooo");
			people.forEach(System.out::println);

		};
	}

//	@Bean
	CommandLineRunner interfaceProjectionQuery(PersonRepository personRepository){
		return args -> {
			List<PersonProjectionInterface> people = personRepository.findByNameIs("wyf");
			people.forEach(person -> {
				System.out.println(person.getName());
				System.out.println(person.getAddress());
				System.out.println(person.getAgeDesc());
				System.out.println(person.getCityDesc());
				System.out.println(person.getInfo());
				System.out.println(person.getHello("Hello"));
			});
		};
	}

//	@Bean
	CommandLineRunner classProjectionQuery(PersonRepository personRepository){
		return args -> {
			List<PersonDto> people = personRepository.findByNameEquals("wyf");
			people.forEach(System.out::println);
		};
	}

//	@Bean
	CommandLineRunner dynamicProjectionQuery(PersonRepository personRepository){
		return args -> {
			Collection<Person> people1 = personRepository.findByNameAndAge("wyf", 35, Person.class);
			Collection<PersonProjectionInterface> people2 = personRepository.findByNameAndAge("wyf", 35, PersonProjectionInterface.class);
			Collection<PersonDto> people3 = personRepository.findByNameAndAge("wyf", 35, PersonDto.class);
			people1.forEach(person -> {
				System.out.println(person.getName());
				System.out.println(person.getAddress());
			});
			people2.forEach(personProjectionInterface -> {
				System.out.println(personProjectionInterface.getHello("Hello"));
				System.out.println(personProjectionInterface.getInfo());
			});
			people3.forEach(personDto -> {
				System.out.println(personDto.getName());
				System.out.println(personDto.getAge());
			});
		};
	}

//	@Bean
	CommandLineRunner storedProcrdureQuery(PersonRepository personRepository){
		return args -> {
			System.out.println(personRepository.getPrefixName("yyy"));
		};
	}

//	@Bean
	CommandLineRunner specificationQuery(PersonRepository personRepository){
		return args -> {
			List<Person> people1 = personRepository.findAll(nameEqual("wyf"));
			people1.forEach(System.out::println);
			List<Person> people2 = personRepository.findAll(ageLessThanAndNameLike(37,"o"));
			people2.forEach(System.out::println);
			List<Person> people3 = personRepository.findAll(nameEqual("bar").or(ageLessThanAndNameLike(37, "o")));
			people3.forEach(System.out::println);
		};
	}

//	@Bean
	CommandLineRunner queryByExample(PersonRepository personRepository){
		return args -> {
			Person person = new Person();
			person.setName("Y");

			ExampleMatcher matcher = ExampleMatcher.matching()
					.withIgnoreCase("name")
					.withStringMatcher(StringMatcher.CONTAINING);

			Example<Person> example = Example.of(person, matcher);

			List<Person> people = personRepository.findAll(example);
			people.forEach(System.out::println);

		};
	}

//	@Bean
	CommandLineRunner entityListeners(PersonRepository personRepository){
		return args -> {
			Address address = new Address("wuhan","Hubei");
			Collection<Child> children = Arrays.asList(new Child("xxx", Gender.FEMALE));
			Person savedPerson = personRepository.save(new Person("www", 33, address, children));
			savedPerson.setAge(34);
			Person updatedPerson =  personRepository.save(savedPerson);
			personRepository.delete(updatedPerson);
		};
	}

//	@Bean
	CommandLineRunner domainEvents(PersonRepository personRepository,
								   EmployeeRepository employeeRepository){
		return args -> {
			Address address = new Address("nanjing","Jiangsu");
			Collection<Child> children = Arrays.asList(new Child("xxxx", Gender.FEMALE));
			Person savedPerson = personRepository.save(new Person("wwww", 33, address, children));
			Thread.sleep(100);
			List<Employee> employees1 = employeeRepository.findByName("wwww");
			employees1.forEach(System.out::println);
			savedPerson.setName("wwwww");
			personRepository.save(savedPerson);
			Thread.sleep(100);
			List<Employee> employees2 = employeeRepository.findByName("wwwww");
			employees2.forEach(System.out::println);
		};
	}

//	@Bean
	CommandLineRunner audit(PersonRepository personRepository,
								   EmployeeRepository employeeRepository){
		return args -> {
			Address address = new Address("zhengzhou","Henan");
			Collection<Child> children = Arrays.asList(new Child("ppp", Gender.FEMALE));
			Person savedPerson = personRepository.save(new Person("zzz", 33, address, children));
			Thread.sleep(1000);
			savedPerson.setName("zzzz");
			Person updatedPerson = personRepository.save(savedPerson);
			System.out.println("创建时间:" + updatedPerson.getCreateTime());
			System.out.println("创建人:" + updatedPerson.getCreatedUser());
			System.out.println("最后更新时间:" + updatedPerson.getUpdateTime());
			System.out.println("最后更新人" + updatedPerson.getUpdatedUser());
		};
	}

//	@Bean
	CommandLineRunner validateSqlScripts(AnotherPersonRepository anotherPersonRepository){
		return args -> {
			List<AnotherPerson> people = anotherPersonRepository.findAll();
			people.forEach(System.out::println);
		};
	}
}