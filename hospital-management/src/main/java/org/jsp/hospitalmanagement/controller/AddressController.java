package org.jsp.hospitalmanagement.controller;

import org.jsp.hospitalmanagement.dto.Address;
import org.jsp.hospitalmanagement.dto.ResponseStructure;
import org.jsp.hospitalmanagement.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
	@Autowired
	private AddressService addressService;

	@PostMapping("/address")
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestBody Address address) {
		return addressService.saveAddress(address);
	}

	@PutMapping("/address")
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestBody Address address) {
		return addressService.updateAddress(address);
	}

	@GetMapping("/address/{id}")
	public ResponseEntity<ResponseStructure<Address>> findById(@PathVariable int id) {
		return addressService.findById(id);
	}
}

