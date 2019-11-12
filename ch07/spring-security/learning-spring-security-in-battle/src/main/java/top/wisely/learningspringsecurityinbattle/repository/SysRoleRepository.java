package top.wisely.learningspringsecurityinbattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.wisely.learningspringsecurityinbattle.domain.model.SysRole;

public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
}
