package top.wisely.addressservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import top.wisely.addressservice.event.AddressSavedEvent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String province;
    private String city;

    @DomainEvents
    Collection<Object> domainEvents(){
        List<Object> events= new ArrayList<Object>();
        events.add(new AddressSavedEvent(this.id, this.province, this.city));
        return events;
    }

    @AfterDomainEventPublication
    void callbackMethod() {
        domainEvents().clear();
    }
}
