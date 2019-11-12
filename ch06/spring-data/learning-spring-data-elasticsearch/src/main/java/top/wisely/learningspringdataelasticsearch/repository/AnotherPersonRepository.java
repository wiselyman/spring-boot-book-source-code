package top.wisely.learningspringdataelasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import top.wisely.learningspringdataelasticsearch.domain.model.AnotherPerson;

public interface AnotherPersonRepository extends ElasticsearchRepository<AnotherPerson,String> {
}
