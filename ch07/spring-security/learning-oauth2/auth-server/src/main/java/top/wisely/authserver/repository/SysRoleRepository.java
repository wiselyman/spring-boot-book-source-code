package top.wisely.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.wisely.authserver.domain.model.SysRole;

public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
}
