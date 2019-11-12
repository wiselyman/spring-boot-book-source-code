package top.wisely.learningspringsecurityinbattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.wisely.learningspringsecurityinbattle.domain.model.SysUser;

import java.util.Optional;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    Optional<SysUser> findByUsername(String username);
}
