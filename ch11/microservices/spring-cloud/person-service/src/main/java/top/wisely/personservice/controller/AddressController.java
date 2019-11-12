package top.wisely.personservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wisely.personservice.domain.model.Address;
import top.wisely.personservice.repository.AddressRepository;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping
    public List<Address> findAll(){
        return addressRepository.findAll();
    }
}
