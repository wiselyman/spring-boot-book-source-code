package top.wisely.learningspringdatajpa.domain.specification;

import org.springframework.data.jpa.domain.Specification;
import top.wisely.learningspringdatajpa.domain.model.Person;


import javax.persistence.criteria.Predicate;

public class CustomSpecs {
    public static Specification<Person> nameEqual(String name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Person> ageLessThanAndNameLike(Integer age, String name){
        return (root, query, criteriaBuilder) -> {
           Predicate ageLessThanPredicate = criteriaBuilder.lessThan(root.get("age"), age);
           Predicate nameLikePredicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");
           query.where(ageLessThanPredicate, nameLikePredicate);
           return query.getRestriction();
        };
    }
}
