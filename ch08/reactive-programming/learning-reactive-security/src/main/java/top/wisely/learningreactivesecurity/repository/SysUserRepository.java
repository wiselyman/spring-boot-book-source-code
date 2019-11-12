package top.wisely.learningreactivesecurity.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;
import top.wisely.learningreactivesecurity.domain.model.SysUser;

public interface SysUserRepository extends ReactiveMongoRepository<SysUser, String> {
    Mono<UserDetails> findByUsername(String username);
}
