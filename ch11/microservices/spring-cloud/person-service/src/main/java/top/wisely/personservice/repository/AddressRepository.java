package top.wisely.personservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import top.wisely.personservice.domain.model.Address;

public interface AddressRepository extends MongoRepository<Address, Long> {
}
