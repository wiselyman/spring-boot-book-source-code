package top.wisely.learningspringdataelasticsearch;



import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilter;
import org.elasticsearch.search.aggregations.bucket.range.ParsedRange;
import org.elasticsearch.search.aggregations.metrics.avg.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.max.ParsedMax;
import org.elasticsearch.search.aggregations.metrics.min.ParsedMin;
import org.elasticsearch.search.aggregations.metrics.stats.ParsedStats;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.valuecount.ParsedValueCount;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import top.wisely.learningspringdataelasticsearch.domain.model.Address;
import top.wisely.learningspringdataelasticsearch.domain.model.AnotherPerson;
import top.wisely.learningspringdataelasticsearch.domain.model.Child;
import top.wisely.learningspringdataelasticsearch.domain.model.Person;
import top.wisely.learningspringdataelasticsearch.domain.type.Gender;
import top.wisely.learningspringdataelasticsearch.repository.AnotherPersonRepository;
import top.wisely.learningspringdataelasticsearch.repository.PersonRepository;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.*;

@SpringBootApplication
public class LearningSpringDataElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringDataElasticsearchApplication.class, args);
	}


//	@Bean
	CommandLineRunner saveAll(PersonRepository personRepository){
		return args -> {
			Address address = new Address("he fei", "Anhui");
			Collection<Child> children = Arrays.asList(new Child("wyn", Gender.FEMALE),
											           new Child("wbe", Gender.MALE));
			Person person = new Person("wyf", 35, address, children);

			Address address1 = new Address("bei jing", "Beijing");
			Collection<Child> children1 = Arrays.asList(new Child("aaa", Gender.FEMALE),
					new Child("bbb", Gender.MALE));
			Person person1 = new Person("foo", 34, address1, children1);

			Address address2 = new Address("shang hai", "Shanghai");
			Collection<Child> children2 = Arrays.asList(new Child("ccc", Gender.FEMALE),
					new Child("ddd", Gender.MALE));
			Person person2 = new Person("bar", 36, address2, children2);

			personRepository.saveAll(Arrays.asList(person, person1, person2));
		};

	}

//	@Bean
	CommandLineRunner query(PersonRepository personRepository){
		return args -> {
			List<Person> people1 = personRepository.findByName("wyf");
			List<Person> people2 = personRepository.findByAddress_City("bei jing");
			List<Person> people3 = personRepository.findByChildren_Name("ccc");
			Page<Person> personPage = personRepository.findByAgeRange(30, 40,
					PageRequest.of(0,3, Sort.by(Sort.Direction.DESC,"age")));
			people1.forEach(System.out::println);
			people2.forEach(System.out::println);
			people3.forEach(System.out::println);
			System.out.println("总数为: " + personPage.getTotalElements()
					+ " 总页数为：" + personPage.getTotalPages());
			personPage.forEach(System.out::println);
		};
	}

//	@Bean
	CommandLineRunner queryBuilderAndSearchQuery(PersonRepository personRepository){
		return args -> {
			QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();

			SearchQuery searchQuery = new NativeSearchQueryBuilder()
					.withQuery(queryBuilder)
					.withPageable(PageRequest.of(0, 3, Sort.by("age")))
					.build();
			Page<Person> personPage1 = personRepository.search(queryBuilder, PageRequest.of(0, 3, Sort.by("age")));
			Page<Person> personPage2 = personRepository.search(searchQuery);
			System.out.println("personPage1总数为: " + personPage1.getTotalElements()
					+ " 总页数为：" + personPage1.getTotalPages());
			personPage1.forEach(System.out::println);
			System.out.println("personPage2总数为: " + personPage2.getTotalElements()
					+ " 总页数为：" + personPage2.getTotalPages());
			personPage2.forEach(System.out::println);

		};
	}

//	@Bean
	CommandLineRunner search(PersonRepository personRepository){
		return args -> {
			SearchQuery queryByAgeRangeAndNameRegex = new NativeSearchQueryBuilder()
					.withQuery(rangeQuery("age").from(30).to(35))
					.withFilter(matchQuery("name", "wyf"))
					.build();

			SearchQuery fuzzyQuery = new NativeSearchQueryBuilder()
					.withQuery(fuzzyQuery("address.city", "shang").fuzziness(Fuzziness.AUTO))
					.build();

			SearchQuery anotherFuzzyQuery = new NativeSearchQueryBuilder()
					.withQuery(matchQuery("address.city", "jing")
					           .fuzziness(Fuzziness.AUTO))
					.build();

			Page<Person> personPage1 = personRepository.search(queryByAgeRangeAndNameRegex);
			Page<Person> personPage2 = personRepository.search(fuzzyQuery);
			Page<Person> personPage3 = personRepository.search(anotherFuzzyQuery);

			personPage1.forEach(System.out::println);
			System.out.println("--------------");
			personPage2.forEach(System.out::println);
			System.out.println("--------------");
			personPage3.forEach(System.out::println);
			System.out.println("--------------");

		};
	}

//	@Bean
	CommandLineRunner searchSimilar(PersonRepository personRepository){
		return args -> {
//			Address address = new Address("he fei", "Anhui");
//			Collection<Child> children = Arrays.asList(new Child("wyn", Gender.FEMALE),
//					new Child("wbe", Gender.MALE));
//			Person person1 = new Person("wyf2", 37, address, children);
//			personRepository.save(person1);
			Person person = personRepository.findByName("wyf").get(0);
			Page<Person> personPage = personRepository.searchSimilar(person, new String[]{"name"} , PageRequest.of(0,3));
			personPage.forEach(System.out::println);
		};
	}

	@Bean
	CommandLineRunner aggregateQuery(ElasticsearchOperations elasticsearchOperations){

		return args -> {

			SearchQuery aggregateQuery = new NativeSearchQueryBuilder().withIndices("person")
					.addAggregation(AggregationBuilders.sum("sumAge").field("age"))
					.addAggregation(AggregationBuilders.max("maxAge").field("age"))
					.addAggregation(AggregationBuilders.min("minAge").field("age"))
					.addAggregation(AggregationBuilders.count("countAge").field("age"))
					.addAggregation(AggregationBuilders.avg("avgAge").field("age"))
					.addAggregation(AggregationBuilders.stats("ageInfo").field("age"))
					.addAggregation(AggregationBuilders.range("ageRange")
							                           .field("age")
							                           .addRange(30,35))
					.addAggregation(AggregationBuilders.filter("leftPerson",
															 rangeQuery("age").from(30).to(35))
												       .subAggregation(AggregationBuilders.sum("leftSum").field("age")))
					.build();

			Aggregations aggs = elasticsearchOperations.query(aggregateQuery, searchResponse -> {
				Aggregations aggregations = searchResponse.getAggregations();
				return aggregations;
			});

			double sum = ((ParsedSum)aggs.get("sumAge")).getValue();
			System.out.println("sum age is " + sum);
			double max = ((ParsedMax)aggs.get("maxAge")).getValue();
			System.out.println("max age is " + max);
			double min = ((ParsedMin)aggs.get("minAge")).getValue();
			System.out.println("min age is " + min);
			double count = ((ParsedValueCount)aggs.get("countAge")).getValue();
			System.out.println("count is " + count);
			double avg = ((ParsedAvg)aggs.get("avgAge")).getValue();
			System.out.println("avg age is " + avg);

			ParsedStats stats = ((ParsedStats)aggs.get("ageInfo"));
			System.out.println("stats sum age is " + stats.getSumAsString());
			System.out.println("stats max age is " + stats.getMaxAsString());
			System.out.println("stats min age is " + stats.getMinAsString());
			System.out.println("stats avg age is " + stats.getAvgAsString());
			System.out.println("stats count is " + stats.getCount());

			((ParsedRange)aggs.get("ageRange")).getBuckets().forEach(bucket -> {
				System.out.println(bucket.getFromAsString()
								   + "到" + bucket.getToAsString()
						           + "数量为: " + bucket.getDocCount());
			});

			Aggregations filterAggs = ((ParsedFilter)aggs.get("leftPerson")).getAggregations();
			double leftSum = ((ParsedSum)filterAggs.get("leftSum")).getValue();
			System.out.println("left sum is " + leftSum);


		};
	}

}
