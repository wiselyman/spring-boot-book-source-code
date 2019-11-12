package top.wisely.personservice.service.in;

import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import top.wisely.personservice.config.KafkaBindings;
import top.wisely.personservice.domain.model.Address;
import top.wisely.personservice.event.AddressSavedEvent;
import top.wisely.personservice.repository.AddressRepository;

@Service
public class AddressService {

    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @StreamListener(target = KafkaBindings.ADDRESS_IN)
    public void updateAddress(AddressSavedEvent event){
        Address address = new Address();
        BeanUtils.copyProperties(event, address);
        addressRepository.save(address);
    }
}
