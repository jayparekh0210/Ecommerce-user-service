package com.ecom.ecomuser.adapters;


import com.ecom.ecomuser.dto.responses.AddressDTO;
import com.ecom.ecomuser.models.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {
    public AddressDTO addressModelToAddressResponse(Address address) {
        return AddressDTO.builder()
                .country(address.getCountry())
                .state(address.getState())
                .street(address.getStreet())
                .zipcode(address.getZipcode())
                .build();
    }

    public Address addressDTOToAddressModel(AddressDTO addressDTO) {
        return Address.builder()
                .country(addressDTO.getCountry())
                .state(addressDTO.getState())
                .street(addressDTO.getStreet())
                .zipcode(addressDTO.getZipcode())
                .build();
    }
}
