package top.wisely.learningspringsecurityinbattle.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(targetEntity = SysAuthority.class)
    private Set<SysAuthority> authorities;

    public SysRole(String name, Set<SysAuthority> authorities) {
        this.name = name;
        this.authorities = authorities;
    }
}
