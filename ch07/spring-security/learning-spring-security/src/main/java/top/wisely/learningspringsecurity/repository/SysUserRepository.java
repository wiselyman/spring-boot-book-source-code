package top.wisely.learningspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.wisely.learningspringsecurity.domain.model.SysUser;

import java.util.Optional;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
   Optional<SysUser> findByUsername(String username);
   @Query("select u from SysUser u where u.id = ?#{principal?.id} and true=?#{hasRole('ROLE_ADMIN')}")
   Optional<SysUser> findRoleAdminMyself();
}
