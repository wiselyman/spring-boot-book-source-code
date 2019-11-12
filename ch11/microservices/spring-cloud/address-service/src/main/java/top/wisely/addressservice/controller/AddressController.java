package top.wisely.addressservice.controller;

import org.springframework.web.bind.annotation.*;
import top.wisely.addressservice.domain.model.Address;
import top.wisely.addressservice.repository.AddressRepository;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @PostMapping
    public Address save(@RequestBody Address address){
        return addressRepository.save(address);
    }
}
