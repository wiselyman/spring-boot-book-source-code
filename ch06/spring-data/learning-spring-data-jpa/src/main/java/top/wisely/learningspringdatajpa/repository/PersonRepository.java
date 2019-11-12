package top.wisely.learningspringdatajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import top.wisely.learningspringdatajpa.domain.model.Person;
import top.wisely.learningspringdatajpa.domain.projection.PersonDto;
import top.wisely.learningspringdatajpa.domain.projection.PersonProjectionInterface;
import top.wisely.learningspringdatajpa.domain.type.Gender;

import java.util.Collection;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor {

    List<Person> findByName(String name);

    List<Person> findDistinctPersonByName(String name);
    List<Person> findByAddress_City(String city);
    List<Person> findByAddressCity(String city);
    List<Person> findByChildren_Name(String name);
    List<Person> findByChildrenName(String name);

    List<Person> queryTop5ByName(String name);

    List<Person> getFirst10ByName(String name);

    List<Person> findByNameAndAge(String name, Integer age);

    List<Person> findByNameWyf();

    @Query("select p from Person p where p.name = ?1")
    List<Person> findByJpql(String name);

    @Query(value = "select * from person where name = ?1", nativeQuery = true)
    List<Person> findBySql(String name);

    List<Person> findByAgeLessThan(Integer age, Sort sort);

    @Query("select p from Person p where p.age < ?1")
    List<Person> findByAgeLessThanWithJqal(Integer age, Sort sort);

    Page<Person> findByAgeLessThan(Integer age, Pageable pageable);

    @Query("select p from Person p where p.name = :name")
    List<Person> findByJpqlWithNamedParameter(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("update Person p set p.name = ?1 where p.name =?2")
    int updatePersonName(String newName, String oldName);

    List<PersonProjectionInterface> findByNameIs(String name);

    List<PersonDto> findByNameEquals(String name);

    <T> Collection<T> findByNameAndAge(String name, Integer age, Class<T> type);


    @Procedure(procedureName = "add_name_prefix")
    String getPrefixName(String name);


    long countByName(String name);

    long deleteByName(String name);

    List<Person> removeByName(String name);

}
