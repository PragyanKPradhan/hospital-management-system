package org.jsp.hospitalmanagement.controller;

import java.util.List;

import org.jsp.hospitalmanagement.dto.Admin;
import org.jsp.hospitalmanagement.dto.ResponseStructure;
import org.jsp.hospitalmanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admins")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseStructure<Admin> saveAdmin(@RequestBody Admin admin){
		return adminService.saveAdmin(admin);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody Admin admin){
		return adminService.updateAdmin(admin);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Admin>> findById(@PathVariable(name = "id") int id){
		return adminService.findById(id);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable(name = "id") int id){
		return adminService.deleteById(id);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Admin>>> findAll(){
		return adminService.findAll();
	}
	
	@PostMapping("verify-by-phone")
	public ResponseEntity<ResponseStructure<Admin>> verifyAdmin(@RequestParam long phone,@RequestParam String password){
		return adminService.verifyAdmin(phone,password);
	}
	
	@GetMapping(value = "/verify-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<Admin>>> verifyByName(@PathVariable String name){
		return adminService.findByName(name);
	}
}
