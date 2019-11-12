package top.wisely.addressservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.wisely.addressservice.domain.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
