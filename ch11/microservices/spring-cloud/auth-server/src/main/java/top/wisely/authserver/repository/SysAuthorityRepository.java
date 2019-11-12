package top.wisely.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.wisely.authserver.domain.model.SysAuthority;

public interface SysAuthorityRepository extends JpaRepository<SysAuthority, Long> {
}
