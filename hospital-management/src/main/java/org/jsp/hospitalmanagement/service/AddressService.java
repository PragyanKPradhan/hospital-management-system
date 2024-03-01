package org.jsp.hospitalmanagement.service;

import java.util.Optional;

import org.jsp.hospitalmanagement.dao.AddressDao;
import org.jsp.hospitalmanagement.dto.Address;
import org.jsp.hospitalmanagement.dto.ResponseStructure;
import org.jsp.hospitalmanagement.exception.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AddressService {
	private AddressDao addressDao;
	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		address = addressDao.saveAddress(address);
		structure.setData(address);
		structure.setMessage("Address added successfully " + address.getId());
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address address) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		address = addressDao.updateAddress(address);
		structure.setData(address);
		structure.setMessage("Address updated successfully");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<Address>> findById(int id) {
		Optional<Address> recAddress = addressDao.findById(id);

		ResponseStructure<Address> structure = new ResponseStructure<>();
		if (recAddress.isPresent()) {
			structure.setData(recAddress.get());
			structure.setMessage("Address found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		}

		throw new IdNotFoundException();
	}

}

