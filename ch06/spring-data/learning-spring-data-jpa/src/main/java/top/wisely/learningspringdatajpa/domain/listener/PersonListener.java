package top.wisely.learningspringdatajpa.domain.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import top.wisely.learningspringdatajpa.domain.model.Person;
import top.wisely.learningspringdatajpa.domain.service.ListenerService;

import javax.persistence.*;
@Configurable
public class PersonListener {
    private ListenerService listenerService;

    public PersonListener(ListenerService listenerService) {
        this.listenerService = listenerService;
    }

    @PrePersist
    public void prePersist(Person person){
        listenerService.process("prePersist:" + person);
    }

    @PostPersist
    public void postPersist(Person person){
        listenerService.process("postPersist:"+ person);
    }

    @PreRemove
    public void preRemove(Person person){
        listenerService.process("preRemove:" + person);
    }
    @PostRemove
    public void postRemove(Person person){
        listenerService.process("postRemove:" + person);
    }

    @PreUpdate
    public void preUpdate(Person person){
        listenerService.process("preUpdate:" + person);
    }

    @PostUpdate
    public void postUpdate(Person person){
        listenerService.process("postUpdate:" + person);
    }

    @PostLoad
    public void postLoad(Person person){
        listenerService.process("postLoad:" + person);
    }
}
