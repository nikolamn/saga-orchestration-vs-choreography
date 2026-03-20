package com.booking.account.mapper;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.booking.account.domain.Account;
import com.booking.account.domain.Address;
import com.booking.account.dto.common.AccountDTO;
import com.booking.account.dto.common.AddressDTO;
import com.booking.account.dto.common.UpdateAccountDTO;
import com.booking.account.dto.common.UpdateAddressDTO;
import com.booking.account.enums.EGender;
import com.booking.grpc.stubs.AccountCreationRequest;
import com.booking.grpc.stubs.AccountUpdateRequest;
import com.booking.grpc.stubs.AddressUpdate;

@Component
public class AccountMapper {

	public Account toDomain(AccountDTO dto) {
		Address address = Address.builder()
				.country(dto.getAdress().getCountry())
				.city(dto.getAdress().getCity())
				.street(dto.getAdress().getStreet())
				.number(dto.getAdress().getNumber())
				.build();

		return Account.builder()
				.userId(dto.getUserId())
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				.gender(EGender.toEnum(dto.getGender()))
				.birthdate(LocalDate.parse(dto.getBirthdate()))
				.email(dto.getEmail())
				.address(address)
				.build();
	}

	public AccountDTO grpcToDTO(AccountCreationRequest req) {
		AddressDTO address = AddressDTO.builder()
				.country(req.getAddress().getCountry())
				.city(req.getAddress().getCity())
				.street(req.getAddress().getStreet())
				.number(Integer.parseInt(req.getAddress().getNumber()))
				.build();
		
		return AccountDTO.builder()
				.userId(UUID.fromString(req.getAuthUserId()))
				.firstName(req.getFirstName())
				.lastName(req.getLastName())
				.gender(req.getGender())
				.birthdate(req.getBirthdate())
				.email(req.getEmail())
				.adress(address)
				.build();
	};
	
	public UpdateAccountDTO grpcToUpdateDTO(AccountUpdateRequest req) {
        UpdateAccountDTO dto = new UpdateAccountDTO();
        
        dto.setUserId(UUID.fromString(req.getAuthUserId()));

        if (req.hasFirstName()) dto.setFirstName(req.getFirstName());
        if (req.hasLastName()) dto.setLastName(req.getLastName());
        if (req.hasGender()) dto.setGender(req.getGender());
        if (req.hasBirthdate()) dto.setBirthdate(req.getBirthdate());
        if (req.hasEmail()) dto.setEmail(req.getEmail());

        if (req.hasAddress()) {
            AddressUpdate addrReq = req.getAddress();
            UpdateAddressDTO addrDto = new UpdateAddressDTO();
            if (addrReq.hasCountry()) addrDto.setCountry(addrReq.getCountry());
            if (addrReq.hasCity()) addrDto.setCity(addrReq.getCity());
            if (addrReq.hasStreet()) addrDto.setStreet(addrReq.getStreet());
            if (addrReq.hasNumber()) addrDto.setNumber(Integer.parseInt(addrReq.getNumber()));
            dto.setAdress(addrDto);
        }

        return dto;
    }
	
	public Account fromUpdateDTOtoDomain(UpdateAccountDTO dto, Account entity) {
        if (dto.getFirstName() != null) entity.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) entity.setLastName(dto.getLastName());
        if (dto.getGender() != null) entity.setGender(EGender.toEnum(dto.getGender()));
        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
        
        if (dto.getBirthdate() != null) {
            entity.setBirthdate(LocalDate.parse(dto.getBirthdate()));
        }

        if (dto.getAdress() != null) {
            entity.setAddress(updateAddressEntity(dto.getAdress(), entity.getAddress()));
        }

        return entity;
    }

    private Address updateAddressEntity(UpdateAddressDTO dto, Address address) {
        if (dto.getCountry() != null) address.setCountry(dto.getCountry());
        if (dto.getCity() != null) address.setCity(dto.getCity());
        if (dto.getStreet() != null) address.setStreet(dto.getStreet());
        if (dto.getNumber() != 0) address.setNumber(dto.getNumber());
        
        return address; 
    }
}