package top.wisely.learningspringsecurityinbattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.wisely.learningspringsecurityinbattle.domain.model.SysAuthority;

public interface SysAuthorityRepository extends JpaRepository<SysAuthority, Long> {
}
