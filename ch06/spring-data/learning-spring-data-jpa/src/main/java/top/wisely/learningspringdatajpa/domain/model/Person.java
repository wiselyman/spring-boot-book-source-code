package top.wisely.learningspringdatajpa.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import top.wisely.learningspringdatajpa.domain.domain_event.PersonSaved;
import top.wisely.learningspringdatajpa.domain.listener.PersonListener;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "tb_person")
@EntityListeners({AuditingEntityListener.class,PersonListener.class})
@NamedQuery(name = "Person.findByNameWyf",
    query = "select p from Person p where p.name = 'wyf'")
public class Person {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 10)
    @Column(name = "name", length = 10)
    private String name;

    private Integer age;

    private Boolean active = true;

    @Embedded
    private Address address;

    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<Child> children;


    @CreatedDate
    private LocalDateTime createTime;

    @CreatedBy
    private String createdUser;

    @LastModifiedDate
    private LocalDateTime updateTime;

    @LastModifiedBy
    private String updatedUser;

    @Version
    Integer version;

    public Person(@NotNull @Size(min = 3, max = 10) String name, Integer age, Address address, Collection<Child> children) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.children = children;
    }


//    @PrePersist
//    public void prePersist(){
//        System.out.println("prePersist:" + this);
//    }
//
//    @PostPersist
//    public void postPersist(){
//        System.out.println("postPersist:" + this);
//    }
//
//    @PreRemove
//    public void preRemove(){
//        System.out.println("preRemove:" + this);
//    }
//
//    @PostRemove
//    public void postRemove(){
//        System.out.println("postRemove:" + this);
//    }
//
//    @PreUpdate
//    public void preUpdate(){
//        System.out.println("preUpdate:" + this);
//    }
//
//    @PostUpdate
//    public void postUpdate(){
//        System.out.println("postUpdate:" + this);
//    }
//
//    @PostLoad
//    public void postLoad(){
//        System.out.println("postLoad:" + this);
//    }

    @DomainEvents
    Collection<Object> domainEvents(){
        List<Object> events= new ArrayList<Object>();
        events.add(new PersonSaved(this.id, this.name, this.age));
        return events;
    }

    @AfterDomainEventPublication
    void callbackMethod() {
       domainEvents().clear();
    }
}
