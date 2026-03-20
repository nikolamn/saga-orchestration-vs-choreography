package com.booking.auth.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.booking.auth.dto.common.AccountDTO;
import com.booking.auth.dto.common.UpdateAccountDTO;
import com.booking.auth.dto.common.UpdateAddressDTO;
import com.booking.grpc.stubs.AccountCreationRequest;
import com.booking.grpc.stubs.AccountUpdateRequest;
import com.booking.grpc.stubs.Address;
import com.booking.grpc.stubs.AddressUpdate;

@Component
public class AccountMapper {

	public AccountCreationRequest toGrpcDTO(AccountDTO dto, UUID userId) {
		Address address = Address.newBuilder()
				.setCountry(dto.getAddress().getCountry())
				.setCity(dto.getAddress().getCity())
				.setStreet(dto.getAddress().getStreet())
				.setNumber(dto.getAddress().getNumber())
				.build();
		
		return AccountCreationRequest.newBuilder()
				.setAuthUserId(userId.toString())
				.setFirstName(dto.getFirstName())
				.setLastName(dto.getLastName())
				.setEmail(dto.getEmail())
				.setGender(dto.getGender())
				.setBirthdate(dto.getBirthdate())
				.setAddress(address)
				.build();
	}
	
	public AccountUpdateRequest toGrpcUpdateDTO(UpdateAccountDTO dto, UUID userId) {
        AccountUpdateRequest.Builder builder = AccountUpdateRequest.newBuilder()
                .setAuthUserId(userId.toString()); 

        if (dto.getFirstName() != null) builder.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) builder.setLastName(dto.getLastName());
        if (dto.getEmail() != null) builder.setEmail(dto.getEmail());
        if (dto.getGender() != null) builder.setGender(dto.getGender());
        if (dto.getBirthdate() != null) builder.setBirthdate(dto.getBirthdate());

        if (dto.getAddress() != null) {
            builder.setAddress(toGrpcAddressUpdate(dto.getAddress()));
        }

        return builder.build();
    }

    private AddressUpdate toGrpcAddressUpdate(UpdateAddressDTO dto) {
        AddressUpdate.Builder builder = AddressUpdate.newBuilder();
        
        if (dto.getCountry() != null) builder.setCountry(dto.getCountry());
        if (dto.getCity() != null) builder.setCity(dto.getCity());
        if (dto.getStreet() != null) builder.setStreet(dto.getStreet());
        
        if (dto.getNumber() != null) {
            builder.setNumber(String.valueOf(dto.getNumber()));
        }
        
        return builder.build();
    }
}
