package top.wisely.learningspringdataelasticsearch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;
import top.wisely.learningspringdataelasticsearch.domain.model.Person;

import java.util.List;

public interface PersonRepository extends ElasticsearchRepository<Person,String> {
    List<Person> findByName(String name);

    List<Person> findByAddress_City(String city);

    List<Person> findByChildren_Name(String childName);

    @Query("{\"bool\" : {\"must\" : {\"range\" : {\"age\" : {\"gte\" : \"?0\", \"lte\" : \"?1\"}}}}}")
    Page<Person> findByAgeRange(Integer startAge, Integer endAge, Pageable pageable);
}